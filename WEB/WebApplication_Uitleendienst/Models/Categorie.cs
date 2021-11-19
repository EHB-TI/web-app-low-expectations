namespace WebApplication_Uitleendienst
{
	public class Categorie
	{
		private string id;
		private string naam;
		private string omschrijving;

		public Categorie()
		{
		}

		public Categorie(string naam, string omschrijving)
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
			return "Categorie{" + "id='" + id + '\'' + ", naam='" + naam + '\'' + ", omschrijving='" + omschrijving + '\'' + '}';
		}
	}

}