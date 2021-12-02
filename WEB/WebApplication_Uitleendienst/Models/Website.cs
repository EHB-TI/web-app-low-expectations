using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Utilities;

namespace WebApplication_Uitleendienst.Models {
    public class Website : Controller {
        public static string API_HOST => ConfigurationHelper.config.GetSection("connections:API_HOST").Value;
        public static string HOST_NAME => ConfigurationHelper.config.GetSection("Authentication:Website:Hostname").Value;
        public Website() {
            
        }
    }
}
