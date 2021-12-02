namespace WebApplication_Uitleendienst
{

	public class Rol
	{
		private string id;
		private string naam;
		private string omschrijving;

		public Rol()
		{
		}

		public Rol(string naam, string omschrijving)
		{
			this.naam = naam;
			this.omschrijving = omschrijving;
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


		public virtual string Omschrijving
		{
			get
			{
				return omschrijving;
			}
			set
			{
				this.omschrijving = value;
			}
		}


		public override string ToString()
		{
			return "Rol{" + "id='" + id + '\'' + ", naam='" + naam + '\'' + ", omschrijving='" + omschrijving + '\'' + '}';
		}
	}

}