using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.AuthorizeAttributes;
using WebApplication_Uitleendienst.Models.ViewModels.Identity;

namespace WebApplication_Uitleendienst.Areas.MagazijnContact.Controllers {

    [MagazijnContactAuthorize]
    public class BaseController : Controller {
         public UserInfoPageModel UserInfo => new UserInfoPageModel(HttpContext);
    }
}
