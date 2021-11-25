using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.ViewModels.Catalogue;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Controllers {
    public class CatalogueController : Controller {
        private readonly ILogger<HomeController> _logger;
        private readonly IBaseService<Categorie> _categorieService;
        private readonly IBaseService<UitleenbaarItem> _uitleenbaarItemService;
        public CatalogueController(IBaseService<Categorie> catService, IBaseService<UitleenbaarItem> uitleenbaarItemService) {
            _categorieService = catService;
            _uitleenbaarItemService = uitleenbaarItemService;
        }

        public IActionResult Catalogue(int categoryId) {
            var model = new CatalogueViewModel();
            model.Products = _uitleenbaarItemService.GetAll("catId", categoryId.ToString());
            return View(model);
        }
    }
}
