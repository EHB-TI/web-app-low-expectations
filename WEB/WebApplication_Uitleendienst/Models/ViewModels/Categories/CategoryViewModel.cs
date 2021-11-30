using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Categories {
    public class CategoryViewModel : BaseViewModel {
        public IEnumerable<Categorie> Categories { get; set; }
    }
}
