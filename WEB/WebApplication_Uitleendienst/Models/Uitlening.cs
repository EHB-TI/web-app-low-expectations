using System;

namespace WebApplication_Uitleendienst
{

	public class Uitlening
	{
		private string id;
		private Organisatie organisatie;
		private Magazijn magazijn;
		private DateTime start;
		private DateTime eind;
		private DateTime teruggebrachtOp;
		private string opmerking;

		public Uitlening()
		{
		}

		public Uitlening(Organisatie organisatie, Magazijn magazijn, DateTime start, DateTime eind, DateTime teruggebrachtOp, string opmerking)
		{
			this.organisatie = organisatie;
			this.magazijn = magazijn;
			this.start = start;
			this.eind = eind;
			this.teruggebrachtOp = teruggebrachtOp;
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


		public virtual DateTime Start
		{
			get
			{
				return start;
			}
			set
			{
				this.start = value;
			}
		}

		public virtual DateTime Eind
		{
			get
			{
				return eind;
			}
			set
			{
				this.eind = value;
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
			return "Uitlening{" + "id='" + id + '\'' + ", organisatie=" + organisatie + ", magazijn=" + magazijn + ", start=" + start + ", eind=" + eind + ", teruggebrachtOp=" + teruggebrachtOp + ", opmerking='" + opmerking + '\'' + '}';
		}
	}

}