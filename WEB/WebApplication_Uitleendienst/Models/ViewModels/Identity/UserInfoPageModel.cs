using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Identity {
    [Authorize]
    public class UserInfoPageModel : PageModel {


        private  HttpContext httpContextAccessor;
        public UserInfoPageModel(HttpContext httpContextAccessor) {
            this.httpContextAccessor = httpContextAccessor;
        }
        public string Email {
            get {
                return httpContextAccessor.User?.Claims.FirstOrDefault(c => c.Type.Equals("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress"))?.Value;
            }
        }

        public string Name { get { return httpContextAccessor.User?.Claims.FirstOrDefault(c => c.Type.Equals("name"))?.Value; } }
    }

}
