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
using WebApplication_Uitleendienst.Models;
using WebApplication_Uitleendienst.Models.ViewModels;

namespace WebApplication_Uitleendienst.Controllers {
    public class HomeController : Controller {
        private readonly ILogger<HomeController> _logger;
        private readonly IHttpContextAccessor _httpContext;
        public HomeController(ILogger<HomeController> logger, IHttpContextAccessor context) {
            _logger = logger;
            _httpContext = context;
        }

        public IActionResult Index() {
            var model = new HomeViewModel(_httpContext);
            return View(model);
        }

        [Authorize]
        public IActionResult Login() {
            var model = new HomeViewModel(_httpContext);
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
