using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Product {
    public class ProductViewModel : BaseViewModel {
        public IEnumerable<UitleenbaarItem> Products { get; set; }
        public UitleenbaarItem Product { get; set; }
        public IEnumerable<Magazijn> Magazijnen { get; set; }
        public BeschikbaarItem BeschikbaarItem { get; set; }
    }
}
