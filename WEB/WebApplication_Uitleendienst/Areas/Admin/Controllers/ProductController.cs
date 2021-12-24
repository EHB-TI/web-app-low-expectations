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
        public IActionResult Index(ProductViewModel model = null) {
            if (model == null)
                model = new ProductViewModel();
            model.Products = _uitleenbaarItemService.GetAll();
            return View(model);
        }

        public IActionResult CreateUitleenbaarItem() {
            var model = new ProductViewModel();
            var categories = _categoryService.GetAll();
            if (categories != null) {
                var item = new UitleenbaarItem {
                    Categories = _categoryService.GetAll()
                };
                return View(item);
            } else {
                model.Message = $"Er zijn geen categorieën gevonden, gelieve deze eerst aan te maken alvorens een product te creëeren. <p><a href='{ Url.Action("Create", "Category", new { area = "Admin" }) }'>Aanmaken</a></p>";
                model.Level = Models.ViewModels.InfoLevel.warning;
                return RedirectToAction("Index", model);
            }


        }

        public IActionResult CreateBeschikbaarItem(string id) {
            var model = new ProductViewModel();
            model.Product = _uitleenbaarItemService.Get(propertyValue: id);
            model.Magazijnen = _magazijnItemService.GetAll(token: UserInfo.Token);
            return View(model);
        }

        public IActionResult Detail(string Id) {
            var prod = _uitleenbaarItemService.Get(propertyValue: Id);
            return View(prod);
        }

        public IActionResult Edit(string Id) {
           var model = new ProductViewModel();
            try {
                var prod = _uitleenbaarItemService.Get(propertyValue: Id, token: UserInfo.Token);
                if (prod != null)
                    prod.Categories = _categoryService.GetAll(token: UserInfo.Token);
                return View(prod);
            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.danger;
                return RedirectToAction("index", model);
            }
        }

        [HttpPost]
        public async Task<IActionResult> EditPost(UitleenbaarItem prod) {
            var model = new ProductViewModel();
            var item = await _uitleenbaarItemService.Update(prod, customEntity: prod.Id,  token: UserInfo.Token);
            if (item == null) {
                model.Message = "Item kon niet worden geupdated.";
                model.Level = Models.ViewModels.InfoLevel.danger;
            } else {
                model.Message = "Item is succesvol geupdated.";
                model.Level = Models.ViewModels.InfoLevel.success;
            }
            return RedirectToAction("Index", model);
        }

        [HttpPost]
        public async Task<IActionResult> CreatePost(UitleenbaarItem prod) {
            var model = new ProductViewModel();
            var entity = await _uitleenbaarItemService.Save(prod, token: UserInfo.Token);
            if (entity == null) {
                model.Message = "Item kon niet worden opgeslagen.";
                model.Level = Models.ViewModels.InfoLevel.danger;
            } else {
                model.Message = "Item is succesvol opgeslagen.";
                model.Level = Models.ViewModels.InfoLevel.success;
            }
            return RedirectToAction("Index", model);
        }

        public async Task<IActionResult> Delete(string id) {
            var model = new ProductViewModel();
            try {
                _uitleenbaarItemService.Delete(id, token: UserInfo.Token);
                model.Message = "Item is succesvol verwijderd.";
                model.Level = Models.ViewModels.InfoLevel.success;

            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.danger;
            }
            return RedirectToAction("Index", model);
        }

        [HttpPost]
        public async Task<IActionResult> CreatePostBeschikbaar(ProductViewModel model) {
            var entity = await _beschikbaarItemService.Save(model.BeschikbaarItem, token: UserInfo.Token);
            if (entity == null) {
                model.Message = "Beschikbaarheid kon niet worden opgeslagen.";
                model.Level = Models.ViewModels.InfoLevel.danger;
            } else {
                model.Message = "Beschikbaarheid is succesvol opgeslagen.";
                model.Level = Models.ViewModels.InfoLevel.success;
            }
            return RedirectToAction("Index", model);
        }
    }
}
