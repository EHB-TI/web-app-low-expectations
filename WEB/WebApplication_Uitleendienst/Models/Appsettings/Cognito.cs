using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.Appsettings {
    public class Cognito {
        public string AccesKeyId { get; set; }
        public string AccesSecretKey { get; set; }

        public string Region { get; set; }

        public string UserPoolId { get; set; }

        public string AppClientId { get; set; }

    }
}
