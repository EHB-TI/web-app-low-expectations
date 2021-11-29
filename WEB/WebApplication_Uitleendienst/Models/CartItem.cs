using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models {
    public class CartItem {
        public string UserId { get; set; }
        public string ProductId { get; set; }
        public string MagazijnId { get; set; }
        public string Amount { get; set; }
        public float Total => Product != null ? (float) Product. * float.Parse(Amount) : 0;
        public UitleenbaarItem Product { get; set; }
        public Magazijn Magazijn { get; set; }
    }
}
