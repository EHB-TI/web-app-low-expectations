namespace WebApplication_Uitleendienst.Utilities {
    public class UpdateProfileModel {
        public string UserId { get; set; }
        public string AccessToken { get; set; }
        public string Name { get; set; }

        public string FamilyName { get; set; }
        public string PhoneNumber { get; set; }
        public string Gender { get; set; }
        public string Address { get; set; }
        public string State { get; set; }
        public string Country { get; set; }
        public string PostalCode { get; set; }
    }
}