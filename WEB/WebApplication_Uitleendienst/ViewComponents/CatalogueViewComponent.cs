using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.ViewComponents {
    public class CatalogueViewComponent : ViewComponent {
        public async Task<IViewComponentResult> InvokeAsync(UitleenbaarItem uitleenbaarItem){      
            return View("_ProductCard", uitleenbaarItem);
        }
    }
}
