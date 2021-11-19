namespace WebApplication_Uitleendienst
{

	public class VerantwoordelijkeMagazijn
	{
		private string id;
		private Persoon persoon;
		private Magazijn magazijn;

		public VerantwoordelijkeMagazijn()
		{
		}

		public VerantwoordelijkeMagazijn(Persoon persoon, Magazijn magazijn)
		{
			this.persoon = persoon;
			this.magazijn = magazijn;
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


		public override string ToString()
		{
			return "VerantwoordelijkeMagazijn{" + "id='" + id + '\'' + ", persoon=" + persoon + ", magazijn=" + magazijn + '}';
		}
	}

}