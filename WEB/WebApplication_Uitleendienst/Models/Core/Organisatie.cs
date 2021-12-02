namespace WebApplication_Uitleendienst
{

	public class Organisatie
	{
		private string id;
		private string naam;
		private string adres;
		private string telefoon;
		private string email;
		private string opmerking;

		public Organisatie()
		{
		}

		public Organisatie(string naam, string adres, string telefoon, string email, string opmerking)
		{
			this.naam = naam;
			this.adres = adres;
			this.telefoon = telefoon;
			this.email = email;
			this.opmerking = opmerking;
		}

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


		public override string ToString()
		{
			return "Organisatie{" + "id='" + id + '\'' + ", naam='" + naam + '\'' + ", adres='" + adres + '\'' + ", telefoon='" + telefoon + '\'' + ", email='" + email + '\'' + ", opmerking='" + opmerking + '\'' + '}';
		}
	}

}