using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models;
using WebApplication_Uitleendienst.Models.ViewModels.Cart;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Controllers {
    public class CartController : BaseController {
        private readonly IBaseService<UitleenbaarItem> _uitleenbaarItemService;
        private readonly IBaseService<Magazijn> _magazijnService;
        private readonly IBaseService<Uitlening> _uitleningService;
        public CartController(IBaseService<UitleenbaarItem> UitleenbaarItemService, IBaseService<Magazijn> MagazijnService, IBaseService<Uitlening> UitleningService) {
            _uitleenbaarItemService = UitleenbaarItemService;
            _magazijnService = MagazijnService;
            _uitleningService = UitleningService;
        }

        public IActionResult Index(CartViewModel model = null) {
            if (model == null)
                model = new CartViewModel();
            // retrieve current cart from session
            var cartSession = HttpContext.Session.GetString("Cart");
            if (cartSession != null) {
                var cart = JsonConvert.DeserializeObject<List<CartItem>>(cartSession);
                // populate cart data with actual product and magazijn info
                cart.ForEach(c => {
                    c.Product = _uitleenbaarItemService.Get(c.ProductId);
                    c.Magazijn = _magazijnService.Get(c.MagazijnId);
                });

                model.Cart = cart;
            }
            return View(model);
        }

        public IActionResult CreateUitlening() {
            var model = new CartViewModel();

            if (!User.Identity.IsAuthenticated) {
                model.Message = "Gelieve in te loggen";
                model.Level = Models.ViewModels.InfoLevel.danger;
                RedirectToAction("Index", model);
            }

            try {

                var currentCart = HttpContext.Session.GetString("Cart");
                if (!string.IsNullOrEmpty(currentCart)) {
                    // when there is info in current cart
                    // use that cartobject as base
                    var cart = JsonConvert.DeserializeObject<List<CartItem>>(currentCart);

                    // create uitlening 
                    _uitleningService.Save() // implementatie dient nog via put te gebeuren
                    // create uitleningitems for each cartitem with the received uitleningId
                } else {
                    model.Message = "Gelieve eerst items toe te voegen aan het winkelmandje.";
                    model.Level = Models.ViewModels.InfoLevel.info;
                }


                RedirectToAction("Index", model);
            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.info;
                RedirectToAction("Index", model);
            }
        }

        public IActionResult GetCartViewComponent() {
            return ViewComponent("Cart");
        }
    }
}
