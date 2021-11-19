namespace WebApplication_Uitleendienst
{

	public class Persoon
	{
		private string id;
		private string voornaam;
		private string familienaam;
		private string adres;
		private string telefoon;
		private string email;
		private string opmerking;

		public Persoon()
		{
		}

		public Persoon(string voornaam, string familienaam, string adres, string telefoon, string email, string opmerking)
		{
			this.voornaam = voornaam;
			this.familienaam = familienaam;
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


		public virtual string Voornaam
		{
			get
			{
				return voornaam;
			}
			set
			{
				this.voornaam = value;
			}
		}

		public virtual string Familienaam
		{
			get
			{
				return familienaam;
			}
			set
			{
				this.familienaam = value;
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
			return "Persoon{" + "id='" + id + '\'' + ", voornaam='" + voornaam + '\'' + ", familienaam='" + familienaam + '\'' + ", adres='" + adres + '\'' + ", telefoon='" + telefoon + '\'' + ", email='" + email + '\'' + ", opmerking='" + opmerking + '\'' + '}';
		}
	}

}