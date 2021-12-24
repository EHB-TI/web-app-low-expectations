using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Magazijns {
    public class MagazijnViewModel : BaseViewModel {
        public IEnumerable<Magazijn> Magazijnen { get; set; }
        public Magazijn Magazijn { get; set; }
        public List<Uitlening> Uitleningen { get; set; }
        public List<UitleenbaarItem> Products { get; set; }
        public UitleenbaarItem Product { get; set; }
        public BeschikbaarItem BeschikbaarItem { get; set; }
    }
}
