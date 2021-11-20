using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Identity {
    [Authorize]
    public class UserInfoPageModel : PageModel {
        public string Email {
            get {
                return User?.Claims.FirstOrDefault(c => c.Type.Equals("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress"))?.Value;
            }
        }

        public string Name { get { return User?.Claims.FirstOrDefault(c => c.Type.Equals("name"))?.Value; } }
    }

}
