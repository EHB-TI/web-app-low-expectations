using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.AuthorizeAttributes;
using WebApplication_Uitleendienst.Models;
using WebApplication_Uitleendienst.Models.ViewModels;
using WebApplication_Uitleendienst.Models.ViewModels.Identity;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Controllers {
    public class HomeController : BaseController {
        private readonly ILogger<HomeController> _logger;
        private readonly IBaseService<Categorie> _categorieService;

        public HomeController(ILogger<HomeController> logger, IBaseService<Categorie> catService) {
            _logger = logger;
            _categorieService = catService;
        }

        public IActionResult Index() {
            var model = new HomeViewModel(HttpContext);
            var userInfo = new UserInfoPageModel(HttpContext);
            model.Categories = _categorieService.GetAll(cache: false, token: userInfo.Token);

            return View(model);
        }

        [Authorize]
        public IActionResult Login() {
            var model = new HomeViewModel(HttpContext);
            return RedirectToAction("Index");
        }

        [HttpGet]
        public async Task<IActionResult> Logout() {
            await HttpContext.SignOutAsync();
            return RedirectToAction("Index");
        }

        public IActionResult Privacy() {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error() {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
