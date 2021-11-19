namespace WebApplication_Uitleendienst
{

	public class BeschikbaarItem
	{
		private string id;
		private UitleenbaarItem uitleenbaarItem;
		private Magazijn magazijn;
		private int aantalTotaal;
		private int aantalBeschikbaar;
		private int aantalGereserveerd;

		public BeschikbaarItem()
		{
		}

		public BeschikbaarItem(UitleenbaarItem uitleenbaarItem, Magazijn magazijn, int? aantalTotaal, int? aantalBeschikbaar, int? aantalGereserveerd)
		{
			this.uitleenbaarItem = uitleenbaarItem;
			this.magazijn = magazijn;
			this.aantalTotaal = aantalTotaal.Value;
			this.aantalBeschikbaar = aantalBeschikbaar.Value;
			this.aantalGereserveerd = aantalGereserveerd.Value;
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

		public virtual UitleenbaarItem UitleenbaarItem
		{
			get
			{
				return uitleenbaarItem;
			}
			set
			{
				this.uitleenbaarItem = value;
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


		public override string ToString()
		{
			return "BeschikbaarItem{" + "id='" + id + '\'' + ", uitleenbaarItem=" + uitleenbaarItem + ", magazijn=" + magazijn + ", aantalTotaal=" + aantalTotaal + ", aantalBeschikbaar=" + aantalBeschikbaar + ", aantalGereserveerd=" + aantalGereserveerd + '}';
		}
	}

}