using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Areas.Identity {
    [Area("Account")]
    public class HomeController : Controller {
        public IActionResult Index() {
            return View();
        }
    }
}
