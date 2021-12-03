using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.ViewModels.Identity;

namespace WebApplication_Uitleendienst.Areas.Account {
    [Area("Account")]
    public class BaseController : Controller {
        public UserInfoPageModel UserInfo => new UserInfoPageModel(HttpContext);
    }
}
