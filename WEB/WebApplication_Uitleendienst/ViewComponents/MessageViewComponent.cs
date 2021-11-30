using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.ViewModels;

namespace WebApplication_Uitleendienst.ViewComponents {
    public class MessageViewComponent: ViewComponent {
        public async Task<IViewComponentResult> InvokeAsync(BaseViewModel baseViewModel){      
            return View("_Message", baseViewModel);
        }
    }
}
