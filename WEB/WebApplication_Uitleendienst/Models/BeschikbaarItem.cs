using Newtonsoft.Json;

namespace WebApplication_Uitleendienst
{

	public class BeschikbaarItem
	{
		private string id;
		private string uitleenbaarItemId;
		private string magazijnId;
		private int aantalTotaal;
		private int aantalBeschikbaar;
		private int aantalGereserveerd;

		public BeschikbaarItem()
		{
		}

		public BeschikbaarItem(string UitleenbaarItemId, string magazijnId, int? aantalTotaal, int? aantalBeschikbaar, int? aantalGereserveerd)
		{
			this.uitleenbaarItemId = UitleenbaarItemId;
			this.magazijnId = magazijnId;
			this.aantalTotaal = aantalTotaal.Value;
			this.aantalBeschikbaar = aantalBeschikbaar.Value;
			this.aantalGereserveerd = aantalGereserveerd.Value;
		}
		[JsonProperty("id")]
		public virtual string Id
		{
			get
			{
				return id;
			}
			set
			{
				this.id = value;
			}
		}
		[JsonProperty("uitleenbaarItemId")]
		public virtual string UitleenbaarItemId
		{
			get
			{
				return uitleenbaarItemId;
			}
			set
			{
				this.uitleenbaarItemId = value;
			}
		}

		[JsonProperty("magazijnId")]
		public virtual string MagazijnId
		{
			get
			{
				return magazijnId;
			}
			set
			{
				this.magazijnId = value;
			}
		}
		[JsonProperty("aantalTotaal")]
		public virtual int? AantalTotaal
		{
			get
			{
				return aantalTotaal;
			}
			set
			{
				this.aantalTotaal = value.Value;
			}
		}
		[JsonProperty("aantalBeschikbaar")]
		public virtual int? AantalBeschikbaar
		{
			get
			{
				return aantalBeschikbaar;
			}
			set
			{
				this.aantalBeschikbaar = value.Value;
			}
		}

		[JsonProperty("aantalGereserveerd")]
		public virtual int? AantalGereserveerd
		{
			get
			{
				return aantalGereserveerd;
			}
			set
			{
				this.aantalGereserveerd = value.Value;
			}
		}

	}

}