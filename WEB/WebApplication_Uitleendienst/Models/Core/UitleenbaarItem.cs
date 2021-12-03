using System;
using System.Collections.Generic;
using Newtonsoft.Json;

namespace WebApplication_Uitleendienst {
    public class UitleenbaarItem {
        private string id;
        private string naam;
        private int? eenheid;
        private float? prijs;
        private string periode;
        private string categorieId;

        public UitleenbaarItem() {
        }

        [JsonIgnore]
        public IEnumerable<Categorie> Categories { get; set; }

        [JsonProperty("id")]
        public string Id {
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

        [JsonProperty("eenheid")]
        public virtual int? Eenheid {
            get {
                return eenheid;
            }
            set {
                this.eenheid = value;
            }
        }

        [JsonProperty("prijs")]
        public virtual float? Prijs {
            get {
                return prijs;
            }
            set {
                this.prijs = value;
            }
        }

        [JsonProperty("periode")]
        public virtual string Periode {
            get {
                return periode;
            }
            set {
                this.periode = value;
            }
        }

        [JsonProperty("categorieId")]
        public virtual string CategorieId {
            get {
                return categorieId;
            }
            set {
                this.categorieId = value;
            }
        }

    }

}