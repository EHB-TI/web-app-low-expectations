using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.ViewModels.Identity;

namespace WebApplication_Uitleendienst.Models.ViewModels {
    public class HomeViewModel : BaseViewModel {
        public HomeViewModel(HttpContext context) : base(context){}
        public IEnumerable<UitleenbaarItem> UitleenbaarItems { get; set; }
    }
}
