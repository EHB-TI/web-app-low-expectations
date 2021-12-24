using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.ViewModels.Magazijns;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Areas.Admin.Controllers {
    [Area("Admin")]
    public class MagazijnController : BaseAdminController {
        private readonly IBaseService<Magazijn> _magazijnService;

        public MagazijnController(IBaseService<Magazijn> magazijnService) {
            _magazijnService = magazijnService;
        }
        public IActionResult Index(MagazijnViewModel model) {
            if (model == null)
                model = new MagazijnViewModel();
            model.Magazijnen = _magazijnService.GetAll(token: UserInfo.Token);
            return View(model);
        }

        public IActionResult Create() {
            return View();
        }

        public IActionResult Detail(string Id) {
            var magazijn = _magazijnService.Get(propertyValue: Id, token: UserInfo.Token);
            return View(magazijn);
        }

        public IActionResult Edit(string Id) {
            var magazijn = _magazijnService.Get(propertyValue: Id, token: UserInfo.Token);
            return View(magazijn);
        }

        [HttpPost]
        public async Task<IActionResult> CreatePost(Magazijn mag) {
            var entity = await _magazijnService.Save(mag, token: UserInfo.Token);
            return RedirectToAction("index");
        }

        [HttpPost]
        public async Task<IActionResult> EditPost(Magazijn mag) {
            var entity = await _magazijnService.Update(mag, customEntity: mag.Id, token: UserInfo.Token);
            return RedirectToAction("index");
        }

        public async Task<IActionResult> Delete(string id) {
            var model = new MagazijnViewModel();
            try {
                _magazijnService.Delete(id, token: UserInfo.Token);
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
