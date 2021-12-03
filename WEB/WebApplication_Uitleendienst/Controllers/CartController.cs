using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models;
using WebApplication_Uitleendienst.Models.ViewModels.Cart;
using WebApplication_Uitleendienst.Models.ViewModels.Identity;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Controllers {
    public class CartController : BaseController {
        private readonly IBaseService<UitleenbaarItem> _uitleenbaarItemService;
        private readonly IBaseService<Magazijn> _magazijnService;
        private readonly IBaseService<Uitlening> _uitleningService;
        private readonly IBaseService<UitleningItem> _uitleningItemService;
        private readonly IBaseService<Persoon> _persoonService;

        public CartController(IBaseService<UitleenbaarItem> UitleenbaarItemService, IBaseService<UitleningItem> uitleningItemServicen, IBaseService<Magazijn> MagazijnService, IBaseService<Uitlening> UitleningService, IBaseService<Persoon> persoonService) {
            _uitleenbaarItemService = UitleenbaarItemService;
            _magazijnService = MagazijnService;
            _uitleningService = UitleningService;
            _uitleningItemService = uitleningItemServicen;
            _persoonService = persoonService;
        }

        public IActionResult Index(CartViewModel model = null) {
            if (model == null)
                model = new CartViewModel();
            // retrieve current cart from session
            var cartSession = HttpContext.Request.Cookies["Cart"];
            if (cartSession != null) {
                var cart = JsonConvert.DeserializeObject<List<CartItem>>(cartSession);
                // populate cart data with actual product and magazijn info
                cart.ForEach(c => {
                    c.Product = _uitleenbaarItemService.Get(propertyValue: c.ProductId, token: UserInfo.Token);
                    c.Magazijn = _magazijnService.Get(propertyValue: c.MagazijnId, token: UserInfo.Token);
                });

                model.Cart = cart;
            }
            return View(model);
        }

        public async Task<IActionResult> CreateUitlening() {
            var model = new CartViewModel();
            var userInfo = new UserInfoPageModel(HttpContext);

            if (!User.Identity.IsAuthenticated) {
                model.Message = "Gelieve in te loggen";
                model.Level = Models.ViewModels.InfoLevel.danger;
                RedirectToAction("Index", model);
            }

            try {

                var currentCart = HttpContext.Request.Cookies["Cart"];
                if (!string.IsNullOrEmpty(currentCart)) {
                    // when there is info in current cart
                    // use that cartobject as base
                    var cart = JsonConvert.DeserializeObject<List<CartItem>>(currentCart);

                    var person = new Persoon();
                    // First check if person doesn't exist in api based on username from identity
                    person = _persoonService.GetAll(propertyName: "username", propertyValue: userInfo.Username, token: UserInfo.Token)?.FirstOrDefault();

                    // if not =>
                    // create persoon based on User Identity information
                    if (person == null) {
                        person = await _persoonService.Save(new Persoon {
                            Username = userInfo.Username,
                            Adres = userInfo.Adress,
                            Email = userInfo.Email,
                            Familienaam = userInfo.FamilyName,
                            Voornaam = userInfo.Name,
                            Telefoon = userInfo.Telephone
                        }, token: UserInfo.Token);
                    }

                    // create uitlening 
                    if (person != null) {
                        var uitlening = new Uitlening {
                            MagazijnId = cart.FirstOrDefault().MagazijnId,
                            Start = DateTime.Now.ToShortDateString(),
                            Eind = DateTime.Now.AddDays(7).ToShortDateString(),
                            PersoonId = person.Id
                        };

                        uitlening = await _uitleningService.Save(uitlening, token: UserInfo.Token);

                        if (uitlening != null) {
                            // create uitleningitems for each cartitem with the received uitleningId
                            var uitleningItems = new List<UitleningItem>();
                            cart.ForEach(c => {
                                uitleningItems.Add(new UitleningItem {
                                    uitleenbaarItemId = c.ProductId,
                                    uitleningId = uitlening.Id,
                                    aantal = int.Parse(c.Amount)
                                });
                            });

                            uitleningItems = await _uitleningItemService.SaveAll(uitleningItems, token: UserInfo.Token);
                            if (uitleningItems != null) {
                                model.Message = "De ontlening is succesvol. Ga naar 'mijn ontleningen' om een overzicht te krijgen.";
                                model.Level = Models.ViewModels.InfoLevel.success;
                                HttpContext.Response.Cookies.Delete("Cart");
                            }
                        }
                    } else {
                        model.Message = "Geen user gevonden.";
                        model.Level = Models.ViewModels.InfoLevel.danger;
                    }
                } else {
                    model.Message = "Gelieve eerst items toe te voegen aan het winkelmandje.";
                    model.Level = Models.ViewModels.InfoLevel.info;
                }


                RedirectToAction("Index", model);
            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.info;

            }

            return RedirectToAction("Index", model);
        }

        public IActionResult GetCartViewComponent() {
            return ViewComponent("Cart");
        }
    }
}
