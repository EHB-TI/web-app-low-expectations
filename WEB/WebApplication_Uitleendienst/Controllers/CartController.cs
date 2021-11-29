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
        public CartController(IBaseService<UitleenbaarItem> UitleenbaarItemService, IBaseService<Magazijn> MagazijnService) {
            _uitleenbaarItemService = UitleenbaarItemService;
            _magazijnService = MagazijnService;
        }

        public IActionResult Index() {
            var model = new CartViewModel();
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

        public IActionResult GetCartViewComponent() {
            return ViewComponent("Cart");
        }
    }
}
