using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.ViewModels;

namespace WebApplication_Uitleendienst.ViewComponents {
    public class AuthenticationViewComponent : ViewComponent {
        private readonly IHttpContextAccessor _httpContext;

        public AuthenticationViewComponent(IHttpContextAccessor httpContextAccessor){
            _httpContext = httpContextAccessor;
        }

        public async Task<IViewComponentResult> InvokeAsync(){
           var model = new HomeViewModel(_httpContext);          
            return View("_LoginPartial", model);
        }
    }

}
