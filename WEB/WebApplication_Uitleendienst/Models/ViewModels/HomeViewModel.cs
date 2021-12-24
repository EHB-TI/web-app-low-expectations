using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;


namespace WebApplication_Uitleendienst.Models.ViewModels {
    public class HomeViewModel : WebApplication_Uitleendienst.Models.ViewModels.Identity.BaseViewModel {
        public HomeViewModel(HttpContext context) : base(context) { }
        public bool IsMagazijnContact { get; set; }
        public IEnumerable<Categorie> Categories { get; set; }
    }
}
