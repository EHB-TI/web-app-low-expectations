using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.ViewModels.Categories;
using WebApplication_Uitleendienst.Models.ViewModels.Product;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Areas.Admin.Controllers {
    [Area("Admin")]
    public class ProductController : BaseAdminController {
        private readonly IBaseService<Categorie> _categoryService;
        private readonly IBaseService<UitleenbaarItem> _uitleenbaarItemService;
        private readonly IBaseService<BeschikbaarItem> _beschikbaarItemService;
        private readonly IBaseService<Magazijn> _magazijnItemService;
        public ProductController(IBaseService<Categorie> catService, IBaseService<UitleenbaarItem> uitleenbaarItemService, IBaseService<BeschikbaarItem> beschikbaarItemService, IBaseService<Magazijn> magazijnItemService) {
            _categoryService = catService;
            _uitleenbaarItemService = uitleenbaarItemService;
            _beschikbaarItemService = beschikbaarItemService;
            _magazijnItemService = magazijnItemService;
        }
        public IActionResult Index() {
            var model = new ProductViewModel();
            model.Products = _uitleenbaarItemService.GetAll();
            return View(model);
        }

        public IActionResult CreateUitleenbaarItem() {
            return View();
        }

        public IActionResult CreateBeschikbaarItem() {
            var model = new ProductViewModel();
            model.Products = _uitleenbaarItemService.GetAll();
            model.Magazijnen = _magazijnItemService.GetAll();
            return View(model);
        }

        public IActionResult Detail(string Id) {
            var category = _uitleenbaarItemService.Get(propertyValue: Id);
            return View(category);
        }

        [HttpPost]
        public async Task<IActionResult> CreatePost(UitleenbaarItem prod) {
            var entity = await _uitleenbaarItemService.Save(prod);
            return RedirectToAction("index");
        }

        [HttpPost]
        public async Task<IActionResult> CreatePostBeschikbaar(ProductViewModel model) {
            var entity = await _beschikbaarItemService.Save(model.BeschikbaarItem);
            return RedirectToAction("index");
        }
    }
}
