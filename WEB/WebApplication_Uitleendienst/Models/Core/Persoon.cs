using Newtonsoft.Json;

namespace WebApplication_Uitleendienst {

    public class Persoon {
        private string id;
        private string voornaam;
        private string familienaam;
        private string adres;
        private string telefoon;
        private string email;
        private string opmerking;
        private string username;

        public Persoon() {
        }
        [JsonProperty("id")]
        public virtual string Id {
            get {
                return id;
            }
            set {
                this.id = value;
            }
        }

        [JsonProperty("voornaam")]
        public virtual string Voornaam {
            get {
                return voornaam;
            }
            set {
                this.voornaam = value;
            }
        }
        [JsonProperty("username")]
        public virtual string Username {
            get {
                return username;
            }
            set {
                this.username = value;
            }
        }
        [JsonProperty("familienaam")]
        public virtual string Familienaam {
            get {
                return familienaam;
            }
            set {
                this.familienaam = value;
            }
        }
        [JsonProperty("adres")]
        public virtual string Adres {
            get {
                return adres;
            }
            set {
                this.adres = value;
            }
        }

        [JsonProperty("telefoon")]
        public virtual string Telefoon {
            get {
                return telefoon;
            }
            set {
                this.telefoon = value;
            }
        }



        [JsonProperty("email")]
        public virtual string Email {
            get {
                return email;
            }
            set {
                this.email = value;
            }
        }
        [JsonProperty("opmerking")]
        public virtual string Opmerking {
            get {
                return opmerking;
            }
            set {
                this.opmerking = value;
            }
        }


        public override string ToString() {
            return "Persoon{" + "id='" + id + '\'' + ", voornaam='" + voornaam + '\'' + ", familienaam='" + familienaam + '\'' + ", adres='" + adres + '\'' + ", telefoon='" + telefoon + '\'' + ", email='" + email + '\'' + ", opmerking='" + opmerking + '\'' + '}';
        }
    }

}