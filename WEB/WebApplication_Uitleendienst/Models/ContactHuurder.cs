namespace WebApplication_Uitleendienst
{
	public class ContactHuurder
	{
		private string id;
		private Persoon persoon;
		private Organisatie organisatie;
		private string opmerking;

		public ContactHuurder()
		{
		}

		public ContactHuurder(Persoon persoon, Organisatie organisatie, string opmerking)
		{
			this.persoon = persoon;
			this.organisatie = organisatie;
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

		public virtual Persoon Persoon
		{
			get
			{
				return persoon;
			}
			set
			{
				this.persoon = value;
			}
		}

		public virtual Organisatie Organisatie
		{
			get
			{
				return organisatie;
			}
			set
			{
				this.organisatie = value;
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
			return "ContactHuurder{" + "id='" + id + '\'' + ", persoon=" + persoon + ", organisatie=" + organisatie + ", opmerking='" + opmerking + '\'' + '}';
		}
	}

}