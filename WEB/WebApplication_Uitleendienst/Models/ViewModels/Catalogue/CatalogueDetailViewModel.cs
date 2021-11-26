using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Catalogue {
    public class CatalogueDetailViewModel {
        public IEnumerable<BeschikbaarItem> BeschikbareItems { get; set; }
        public UitleenbaarItem Product { get; set; }
        public int TotalStock { get; set; }
        public List<Magazijn> Magazijnen { get; set; }
    }
}
