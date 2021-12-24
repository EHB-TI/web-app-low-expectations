using Amazon.CognitoIdentityProvider.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.Models.ViewModels.User {
    public class UserViewModel : BaseViewModel {
        public Dictionary<UserTypeViewModel, List<GroupType>> Users { get; set; }
        public string Groupname { get; set; }
        public string Username { get; set; }
        public List<GroupType> Groups { get; set; }
        public IEnumerable<Magazijn> Magazijnen { get; set; }
        public string MagazijnId { get; set; }
    }

    public class UserTypeViewModel {
        public Guid UniqueId => Guid.NewGuid();
        public UserType UserType { get; set; }
        public bool IsMagazijnContact { get; set; }
        public ContactMagazijnInfoViewModel ContactMagazijnInfo { get; set; }
    }

    public class ContactMagazijnInfoViewModel {
        public ContactMagazijn ContactMagazijn { get; set; }
        public Magazijn Magazijn { get; set; }
    }

}
