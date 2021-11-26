using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Identity {
    public class BaseViewModel {
        private HttpContext httpContextAccessor;
        public BaseViewModel(HttpContext httpContextAccessor) {
            this.httpContextAccessor = httpContextAccessor;
        }
        public UserInfoPageModel UserInfo {
            get {
                return new UserInfoPageModel(httpContextAccessor);
            }
        }
    }
}
