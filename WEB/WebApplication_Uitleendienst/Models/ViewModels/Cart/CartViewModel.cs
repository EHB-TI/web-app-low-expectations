using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.Cart {
    public class CartViewModel : BaseViewModel {
        public List<CartItem> Cart { get; set; }
        public int AmountItems => Cart != null ? Cart.Count : 0;
    }
}
