﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels {
    public class HomeViewModel {
        IEnumerable<UitleenbaarItem> uitleenbaarItems { get; set; }
    }
}
