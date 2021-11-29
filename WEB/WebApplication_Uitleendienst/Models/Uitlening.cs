using System;

namespace WebApplication_Uitleendienst {

    public class Uitlening {
        private string id;
        private string organisatieId;
        private string magazijnId;
        private string start;
        private string eind;
        private string teruggebrachtOp;
        private string opmerking;

        public Uitlening() {
        }


        public virtual string Id {
            get {
                return id;
            }
            set {
                this.id = value;
            }
        }

        public virtual string OrganisatieId {
            get {
                return organisatieId;
            }
            set {
                this.organisatieId = value;
            }
        }

        public virtual string MagazijnId {
            get {
                return magazijnId;
            }
            set {
                this.magazijnId = value;
            }
        }


        public virtual string Start {
            get {
                return start;
            }
            set {
                this.start = value;
            }
        }

        public virtual string Eind {
            get {
                return eind;
            }
            set {
                this.eind = value;
            }
        }


        public virtual string TeruggebrachtOp {
            get {
                return teruggebrachtOp;
            }
            set {
                this.teruggebrachtOp = value;
            }
        }

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