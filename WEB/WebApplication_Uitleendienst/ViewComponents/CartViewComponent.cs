using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models;
using WebApplication_Uitleendienst.Models.ViewModels.Cart;

namespace WebApplication_Uitleendienst.ViewComponents {
    public class CartViewComponent : ViewComponent {
        public async Task<IViewComponentResult> InvokeAsync(){ 
            var model = new CartViewModel();
            var cart = HttpContext.Request.Cookies["Cart"];
            if(cart != null){
                model.Cart = JsonConvert.DeserializeObject<List<CartItem>>(cart);
            }
            return View("_CartCounter", model);
        }
    }
}
