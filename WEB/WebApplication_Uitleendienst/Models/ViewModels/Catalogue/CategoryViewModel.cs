using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Catalogue {
    public class CatalogueViewModel : BaseViewModel {
        public List<Categorie> SelectedCategories { get; set; }
        public IEnumerable<UitleenbaarItem> Products { get; set; }
        public IEnumerable<Categorie> Categories { get; set; }
    }
}
