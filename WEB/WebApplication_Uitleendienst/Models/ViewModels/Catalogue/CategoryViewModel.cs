using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Catalogue {
    public class CatalogueViewModel {
        public IEnumerable<UitleenbaarItem> Products { get; set; }
    }
}
