using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.ViewModels;

namespace WebApplication_Uitleendienst.ViewComponents {
    public class AuthenticationViewComponent : ViewComponent {

        public AuthenticationViewComponent(){
        }

        public async Task<IViewComponentResult> InvokeAsync(){
           var model = new HomeViewModel();          
            return View("_LoginPartial", model);
        }
    }

}
