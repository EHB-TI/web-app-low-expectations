using Newtonsoft.Json;
using System;

namespace WebApplication_Uitleendienst {

    public class UitleningItem {
        public string id { get; set; }
        public string uitleningId { get; set; }
        public string uitleenbaarItemId { get; set; }
        public int aantal { get; set; }
        public string teruggebrachtOp { get; set; }
        public string aantalTeruggebracht { get; set; }

        [JsonIgnore]
        public UitleenbaarItem UitleenbaarItem { get; set; }

        [JsonIgnore]
        public float Total => UitleenbaarItem != null ? (float) UitleenbaarItem.Prijs * aantal : 0;
        public UitleningItem() {
        }
    }

}