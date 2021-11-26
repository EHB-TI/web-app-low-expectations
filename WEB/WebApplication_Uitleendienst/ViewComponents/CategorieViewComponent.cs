using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.ViewComponents {
    public class CategorieViewComponent : ViewComponent {
        public async Task<IViewComponentResult> InvokeAsync(Categorie categorie){      
            return View("_CategorieCard", categorie);
        }
    }
}
