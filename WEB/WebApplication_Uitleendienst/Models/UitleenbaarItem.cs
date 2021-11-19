using System;

namespace WebApplication_Uitleendienst
{
	public class UitleenbaarItem
	{
		private string id;
		private string naam;
		private int? eenheid;
		private float? prijs;
		private DateTime periode;
		private Categorie categorie;

		public UitleenbaarItem()
		{
		}

		public UitleenbaarItem(string naam, int? eenheid, float? prijs, DateTime periode, Categorie categorie)
		{
			this.naam = naam;
			this.eenheid = eenheid;
			this.prijs = prijs;
			this.periode = periode;
			this.categorie = categorie;
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


		public virtual int? Eenheid
		{
			get
			{
				return eenheid;
			}
			set
			{
				this.eenheid = value;
			}
		}


		public virtual float? Prijs
		{
			get
			{
				return prijs;
			}
			set
			{
				this.prijs = value;
			}
		}


		public virtual DateTime Periode
		{
			get
			{
				return periode;
			}
			set
			{
				this.periode = value;
			}
		}


		public virtual Categorie Categorie
		{
			get
			{
				return categorie;
			}
			set
			{
				this.categorie = value;
			}
		}


		public override string ToString()
		{
			return "UitleenbaarItem{" + "id='" + id + '\'' + ", naam='" + naam + '\'' + ", eenheid=" + eenheid + ", prijs=" + prijs + ", periode=" + periode + ", categorie=" + categorie + '}';
		}
	}

}