using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.Cognito {
    public class UserProfileResponse {
        [Key]
        public string UserId { get; set; }
        public string EmailAddress { get; set; }
        public string Name { get; set; }
        public string FamilyName { get; set; }
        public string PhoneNumber { get; set; }
        public string Gender { get; set; }
        public string Address { get; set; }
    }
}
