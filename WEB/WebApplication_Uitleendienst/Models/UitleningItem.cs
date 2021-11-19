using System;

namespace WebApplication_Uitleendienst
{

	public class UitleningItem
	{
		private string id;
		private Uitlening uitlening;
		private UitleenbaarItem item;
		private int aantal;
		private DateTime teruggebrachtOp;
		private int aantalTeruggebracht;

		public UitleningItem()
		{
		}

		public UitleningItem(Uitlening uitlening, UitleenbaarItem item, int aantal, DateTime teruggebrachtOp, int aantalTeruggebracht)
		{
			this.uitlening = uitlening;
			this.item = item;
			this.aantal = aantal;
			this.teruggebrachtOp = teruggebrachtOp;
			this.aantalTeruggebracht = aantalTeruggebracht;
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

		public virtual Uitlening Uitlening
		{
			get
			{
				return uitlening;
			}
			set
			{
				this.uitlening = value;
			}
		}

		public virtual UitleenbaarItem Item
		{
			get
			{
				return item;
			}
			set
			{
				this.item = value;
			}
		}

		public virtual int Aantal
		{
			get
			{
				return aantal;
			}
			set
			{
				this.aantal = value;
			}
		}

		public virtual DateTime TeruggebrachtOp
		{
			get
			{
				return teruggebrachtOp;
			}
			set
			{
				this.teruggebrachtOp = value;
			}
		}
		public virtual int AantalTeruggebracht
		{
			get
			{
				return aantalTeruggebracht;
			}
			set
			{
				this.aantalTeruggebracht = value;
			}
		}


		public override string ToString()
		{
			return "UitleningItem{" + "id='" + id + '\'' + ", uitlening=" + uitlening + ", item=" + item + ", aantal=" + aantal + ", teruggebrachtOp=" + teruggebrachtOp + ", aantalTeruggebracht=" + aantalTeruggebracht + '}';
		}
	}

}