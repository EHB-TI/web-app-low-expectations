using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.AuthorizeAttributes;
using WebApplication_Uitleendienst.Models.ViewModels;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Areas.MagazijnContact.Controllers {
    [Area("MagazijnContact")]
    public class HomeController : BaseController {
        private readonly IBaseService<ContactMagazijn> _contactMagazijnService;
        private readonly IBaseService<Persoon> _persoonService;

        public HomeController(IBaseService<ContactMagazijn> contactMagService, IBaseService<Persoon> persoonService) {
            _persoonService = persoonService;
            _contactMagazijnService = contactMagService;
        }
        public IActionResult Index() {
            var model = new HomeViewModel(HttpContext);
            return View();
        }
    }
}
