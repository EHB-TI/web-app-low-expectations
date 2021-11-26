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

		public virtual string UitleenbaarItem
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
			return "BeschikbaarItem{" + "id='" + id + '\'' + ", uitleenbaarItem=" + uitleenbaarItemId + ", magazijn=" + magazijnId + ", aantalTotaal=" + aantalTotaal + ", aantalBeschikbaar=" + aantalBeschikbaar + ", aantalGereserveerd=" + aantalGereserveerd + '}';
		}
	}

}