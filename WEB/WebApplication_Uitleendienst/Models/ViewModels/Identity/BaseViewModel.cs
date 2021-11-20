using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Identity {
    public class BaseViewModel {
        private readonly IHttpContextAccessor httpContextAccessor;
        public BaseViewModel(IHttpContextAccessor httpContextAccessor) {
            this.httpContextAccessor = httpContextAccessor;
        }
        private static UserInfoPageModel instance = null;
        public UserInfoPageModel UserInfo {
            get {
                if (instance == null)
                    instance = new UserInfoPageModel(httpContextAccessor);
                return instance;
            }
        }
    }
}
