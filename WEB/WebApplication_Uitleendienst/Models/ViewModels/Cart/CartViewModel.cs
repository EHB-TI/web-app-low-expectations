using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Cart {
    public class CartViewModel {
        public List<CartItem> Cart { get; set; }
        public float? Total => Cart.Sum(s => s.Product.Prijs);
        public int AmountItems => Cart != null ? Cart.Count : 0;
    }
}
