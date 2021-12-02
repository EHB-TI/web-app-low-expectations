namespace WebApplication_Uitleendienst
{
	public class ContactHuurder
	{
		private string id;
		private string persoonId;
		private string organisatieId;
		private string opmerking;

		public ContactHuurder()
		{
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

		public virtual string PersoonId
		{
			get
			{
				return persoonId;
			}
			set
			{
				this.persoonId = value;
			}
		}

		public virtual string OrganisatieId
		{
			get
			{
				return organisatieId;
			}
			set
			{
				this.organisatieId = value;
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

	}

}