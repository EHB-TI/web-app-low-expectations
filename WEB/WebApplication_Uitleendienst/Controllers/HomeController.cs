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
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Controllers {
    public class HomeController : BaseController {
        private readonly ILogger<HomeController> _logger;
        private readonly IBaseService<Categorie> _categorieService;
        private IEnumerable<Categorie> _categories => new List<Categorie>() {
                new Categorie {
                    Naam = "Geluid", 
                    Omschrijving = "Luidsprekers, Mengpanelen, Afspeelapparatuur, Microfoons, ...",
                    Image = "/images/categories/Geluid.jpg"
                },
                new Categorie {
                    Naam = "Licht", 
                    Omschrijving = "Conventionele spots, Ledspots, Lichteffecten, Dimmers, Sturingen/tafels",
                    Image = "/images/categories/licht.jpg"
                },
                new Categorie {
                    Naam = "Video",
                    Omschrijving = "Camera's, Afspeelapparatuur, Mengpanelen, Projectoren, Projectieschermen",
                    Image = "/images/categories/video.jpg"
                },
                new Categorie {
                    Naam="Kabels", 
                    Omschrijving = "XLR (licht/ geluid), Geluid, Licht, Video, Stroom",
                    Image = "/images/categories/kabels.jpg"
                }
            };
        
        
        public HomeController(ILogger<HomeController> logger, IBaseService<Categorie> catService) {
            _logger = logger;
            _categorieService = catService;
        }

        public IActionResult Index() {
            var model = new HomeViewModel(HttpContext);
            model.Categories = _categorieService.GetAll(cache: true);
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
