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
        public IActionResult Index() {
            var model = new MagazijnViewModel(); 
            model.Magazijnen = _magazijnService.GetAll();
            return View(model);
        }

         public IActionResult Create() {
            return View();
        }

        public IActionResult Detail(string Id) {
            var category = _magazijnService.Get(propertyValue: Id);
            return View(category);
        }

        [HttpPost]
        public async Task<IActionResult> CreatePost(Magazijn mag) {
            var entity = await _magazijnService.Save(mag);
            return RedirectToAction("index");
        }

        [HttpPost]
        public async Task<IActionResult> EditPost(Magazijn mag) {
            var entity = await _magazijnService.Update(mag);
            return RedirectToAction("index");
        }

    }
}
