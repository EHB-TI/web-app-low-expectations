using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Identity {
    public class BaseViewModel {
       public UserInfoPageModel UserInfo { get { return new UserInfoPageModel(); } }
    }
}
