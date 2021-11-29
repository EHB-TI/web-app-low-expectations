using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models;
using WebApplication_Uitleendienst.Models.ViewModels.Catalogue;
using WebApplication_Uitleendienst.Models.ViewModels.Identity;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Controllers {
    public class CatalogueController : BaseController {
        private readonly ILogger<HomeController> _logger;
        private readonly IBaseService<Categorie> _categorieService;
        private readonly IBaseService<BeschikbaarItem> _beschikbaarItemService;
        private readonly IBaseService<UitleenbaarItem> _uitleenbaarItemService;
        private readonly IBaseService<Magazijn> _magazijnService;


        public CatalogueController(IBaseService<Categorie> catService, IBaseService<Magazijn> magazijnService, IBaseService<BeschikbaarItem> beschikbaarItemService, IBaseService<UitleenbaarItem> uitleenbaarItemService) {
            _categorieService = catService;
            _beschikbaarItemService = beschikbaarItemService;
            _uitleenbaarItemService = uitleenbaarItemService;
            _magazijnService = magazijnService;
        }

        public IActionResult Detail(UitleenbaarItem item) {
            var model = new CatalogueDetailViewModel();
            model.Product = item;
            model.Magazijnen = new List<Magazijn>();
            model.BeschikbareItems = _beschikbaarItemService.GetAll("uitleenbaarItemId", item.Id);
            model.BeschikbareItems.ToList().ForEach(s => {
                model.TotalStock += (int)s.AantalTotaal;
                model.Magazijnen.Add(_magazijnService.Get(s.MagazijnId));
            });

            return View(model);
        }
        public IActionResult Catalogue(string categoryId) {
            var model = new CatalogueViewModel();
            if (categoryId != null)
                model.Products = _uitleenbaarItemService.GetAll("categorieId", categoryId);
            else
                model.Products = _uitleenbaarItemService.GetAll();
            model.Categories = _categorieService.GetAll();
            return View(model);
        }

        public string AddToCart(string productId, string magazijnId, string amount) {
            if (!User.Identity.IsAuthenticated)
                return "not_authenticated";
            try {
                var cart = new List<CartItem>();
                var cartItem = new CartItem {
                    Amount = amount,
                    ProductId = productId,
                    MagazijnId = magazijnId,
                    UserId = new UserInfoPageModel(HttpContext).Username
                };
                var currentCart = HttpContext.Session.GetString("Cart");

                if (!string.IsNullOrEmpty(currentCart)) {
                    // when there is info in current cart
                    // use that cartobject as base
                    cart = JsonConvert.DeserializeObject<List<CartItem>>(currentCart);

                }

                // check for any existing occurences
                // if so, add the old amount to the new amount, and remove the occurence from list
                var existingCartItem = cart.Where(s => s.ProductId == cartItem.ProductId && s.MagazijnId == cartItem.MagazijnId).FirstOrDefault();
                if (existingCartItem != null){
                    cartItem.Amount = (int.Parse(cartItem.Amount) + int.Parse(existingCartItem.Amount)).ToString();
                    cart.Remove(existingCartItem);
                }
                cart.Add(cartItem);
                HttpContext.Session.SetString("Cart", JsonConvert.SerializeObject(cart));

                return "success";
            } catch (Exception ex) {
                return ex.Message;
            }

        }
    }
}
