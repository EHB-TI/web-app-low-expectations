using Newtonsoft.Json;

namespace WebApplication_Uitleendienst
{
	public class Magazijn
	{
		private string id;
		private string naam;
		private string adres;
		private string telefoon;
		private string email;
		private string opmerking;

		public Magazijn()
		{
		}

		public Magazijn(string naam, string adres, string telefoon, string email, string opmerking)
		{
			this.naam = naam;
			this.adres = adres;
			this.telefoon = telefoon;
			this.email = email;
			this.opmerking = opmerking;
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

		[JsonProperty("naam")]
		public virtual string Naam
		{
			get
			{
				return naam;
			}
			set
			{
				this.naam = value;
			}
		}
		[JsonProperty("adres")]
		public virtual string Adres
		{
			get
			{
				return adres;
			}
			set
			{
				this.adres = value;
			}
		}

		[JsonProperty("telefoon")]
		public virtual string Telefoon
		{
			get
			{
				return telefoon;
			}
			set
			{
				this.telefoon = value;
			}
		}

		[JsonProperty("email")]
		public virtual string Email
		{
			get
			{
				return email;
			}
			set
			{
				this.email = value;
			}
		}
		[JsonProperty("opmerking")]
		public virtual string Opmerking
		{
			get
			{
				return opmerking;
			}
			set
			{
				this.opmerking = value;
			}
		}
	}

}