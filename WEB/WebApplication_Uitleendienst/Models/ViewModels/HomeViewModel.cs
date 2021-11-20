using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.ViewModels.Identity;

namespace WebApplication_Uitleendienst.Models.ViewModels {
    public class HomeViewModel : BaseViewModel {
        public IEnumerable<UitleenbaarItem> UitleenbaarItems { get; set; }
    }
}
