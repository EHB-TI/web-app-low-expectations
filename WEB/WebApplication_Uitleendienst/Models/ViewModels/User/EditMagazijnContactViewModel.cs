using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.User {
    public class EditMagazijnContactViewModel {
        public IEnumerable<Magazijn> Magazijnen { get; set; }
        public ContactMagazijn ContactMagazijn { get; set; }
    }
}
