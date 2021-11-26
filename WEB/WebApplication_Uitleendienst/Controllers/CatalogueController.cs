using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.ViewModels.Catalogue;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Controllers {
    public class CatalogueController : BaseController {
        private readonly ILogger<HomeController> _logger;
        private readonly IBaseService<Categorie> _categorieService;
        private readonly IBaseService<BeschikbaarItem> _beschikbaarItemService;
        private readonly IBaseService<UitleenbaarItem> _uitleenbaarItemService;
        private readonly IBaseService<Magazijn> _magazijnService;


        public CatalogueController(IBaseService<Categorie> catService, IBaseService<Magazijn> magazijnService, IBaseService<BeschikbaarItem> beschikbaarItemService, IBaseService<UitleenbaarItem> uitleenbaarItemService) {
            _categorieService = catService;
            _beschikbaarItemService = beschikbaarItemService;
            _uitleenbaarItemService = uitleenbaarItemService;
            _magazijnService = magazijnService;
        }

        public IActionResult Detail(UitleenbaarItem item) {
            var model = new CatalogueDetailViewModel();
            model.Product = item;
            model.Magazijnen = new List<Magazijn>();
            model.BeschikbareItems = _beschikbaarItemService.GetAll("uitleenbaarItemId", item.Id);
            model.BeschikbareItems.ToList().ForEach(s => {
                model.TotalStock += (int)s.AantalTotaal;
                model.Magazijnen.Add( _magazijnService.Get(s.MagazijnId));
            });

            return View(model);
        }
        public IActionResult Catalogue(string categoryId) {
            var model = new CatalogueViewModel();
            model.Products = _uitleenbaarItemService.GetAll("categorieId", categoryId);
            //TEST model.Products = ItemList;
            model.Categories = _categorieService.GetAll();
            return View(model);
        }
    }
}
