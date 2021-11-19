namespace WebApplication_Uitleendienst
{
	public class ContactMagazijn
	{
		private string id;
		private Persoon persoon;
		private Magazijn magazijn;
		private string opmerking;

		public ContactMagazijn()
		{
		}

		public ContactMagazijn(Persoon persoon, Magazijn magazijn, string opmerking)
		{
			this.persoon = persoon;
			this.magazijn = magazijn;
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

		public virtual Magazijn Magazijn
		{
			get
			{
				return magazijn;
			}
			set
			{
				this.magazijn = value;
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
			return "ContactMagazijn{" + "id='" + id + '\'' + ", persoon=" + persoon + ", magazijn=" + magazijn + ", opmerking='" + opmerking + '\'' + '}';
		}
	}

}