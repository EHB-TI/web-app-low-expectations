using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Identity {
    public class BaseViewModel {
        private static UserInfoPageModel instance = null;
        public UserInfoPageModel UserInfo {
            get {
                if (instance == null)
                    instance = new UserInfoPageModel();
                return instance;
            }
        }
    }
}
