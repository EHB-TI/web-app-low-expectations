using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.ViewComponents {
    public class CatalogueViewComponent : ViewComponent {
        public async Task<IViewComponentResult> InvokeAsync(BeschikbaarItem beschikbaarItem){      
            return View("_ProductCard", beschikbaarItem);
        }
    }
}
