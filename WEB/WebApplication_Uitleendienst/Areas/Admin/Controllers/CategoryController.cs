using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.ViewModels.Categories;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Areas.Admin.Controllers {
    [Area("Admin")]
    public class CategoryController : BaseAdminController {
        private readonly IBaseService<Categorie> _categoryService;
        public CategoryController(IBaseService<Categorie> catService) {
            _categoryService = catService;
        }
        public IActionResult Index() {
            var model = new CategoryViewModel();
            model.Categories = _categoryService.GetAll();
            return View(model);
        }

        public IActionResult Create() {
            return View();
        }

        public IActionResult Edit(string Id) {
            var category = _categoryService.Get(propertyValue: Id);
            if (category != null) {
                return View(category);
            } else {
                return RedirectToAction("index", new CategoryViewModel() {
                    Message = "Cannot edit category",
                    Level = Models.ViewModels.InfoLevel.warning
                });
            }

        }

        [HttpPost]
        public async Task<IActionResult> EditPost(Categorie cat) {
            var entity = await _categoryService.Update(cat, customEntity: cat.Id, token: UserInfo.Token);
            return RedirectToAction("index");
        }
        public IActionResult Detail(string Id) {
            var category = _categoryService.GetAll(cache: true).FirstOrDefault(s => s.Id == Id);
            return View(category);
        }

        [HttpPost]
        public async Task<IActionResult> CreatePost(Categorie cat) {
            var entity = await _categoryService.Save(item: cat, token: UserInfo.Token);
            return RedirectToAction("index");
        }

        public async Task<IActionResult> Delete(string id) {
            var model = new CategoryViewModel();
            try {
                _categoryService.Delete(id, token: UserInfo.Token);
                model.Message = "Item is succesvol verwijderd.";
                model.Level = Models.ViewModels.InfoLevel.success;

            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.danger;
            }
            return RedirectToAction("Index", model);
        }
    }
}
