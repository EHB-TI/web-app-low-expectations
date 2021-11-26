using Newtonsoft.Json;

namespace WebApplication_Uitleendienst {

    public class Categorie {
        private string id;
        private string naam;
        private string omschrijving;
        private string image = "/images/default.png";

        public Categorie() {
        }

        public Categorie(string naam, string omschrijving) {
            this.naam = naam;
            this.omschrijving = omschrijving;
        }

        [JsonIgnore]
        public virtual string Image {
            get {
                return image;
            }
            set {
                this.image = value;
            }
        }

        public virtual string Id {
            get {
                return id;
            }
            set {
                this.id = value;
            }
        }
        [JsonProperty("naam")]
        public virtual string Naam {
            get {
                return naam;
            }
            set {
                this.naam = value;
            }
        }
        [JsonProperty("omschrijving")]
        public virtual string Omschrijving {
            get {
                return omschrijving;
            }
            set {
                this.omschrijving = value;
            }
        }
        public override string ToString() {
            return "Categorie{" + "id='" + id + '\'' + ", naam='" + naam + '\'' + ", omschrijving='" + omschrijving + '\'' + '}';
        }
    }

}