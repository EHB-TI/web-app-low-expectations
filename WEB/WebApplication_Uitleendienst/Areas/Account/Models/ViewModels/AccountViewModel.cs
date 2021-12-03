using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.Cognito;
using WebApplication_Uitleendienst.Models.ViewModels;

namespace WebApplication_Uitleendienst.Areas.Account.Models.ViewModels {
    public class AccountViewModel : BaseViewModel {
        public UserProfileResponse User { get; set; }
    }
}
