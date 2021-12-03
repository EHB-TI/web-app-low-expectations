using System;
using Newtonsoft.Json;

namespace WebApplication_Uitleendienst {

    public class Uitlening {
        private string id;
        private string persoonId;
        private string magazijnId;
        private string start;
        private string eind;
        private string teruggebrachtOp;
        private string opmerking;

        [JsonIgnore]
        public Magazijn Magazijn { get; set; }

        public Uitlening() {
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
        [JsonProperty("persoonId")]
        public virtual string PersoonId {
            get {
                return persoonId;
            }
            set {
                this.persoonId = value;
            }
        }
        [JsonProperty("magazijnId")]
        public virtual string MagazijnId {
            get {
                return magazijnId;
            }
            set {
                this.magazijnId = value;
            }
        }

        [JsonProperty("start")]
        public virtual string Start {
            get {
                return start;
            }
            set {
                this.start = value;
            }
        }
        [JsonProperty("eind")]
        public virtual string Eind {
            get {
                return eind;
            }
            set {
                this.eind = value;
            }
        }

        [JsonProperty("teruggebrachtOp")]
        public virtual string TeruggebrachtOp {
            get {
                return teruggebrachtOp;
            }
            set {
                this.teruggebrachtOp = value;
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

    }

}