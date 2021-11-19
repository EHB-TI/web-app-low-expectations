namespace WebApplication_Uitleendienst
{
	public class User
	{
		private string id;
		private string username;
		private string password;
		private Rol rol;
		private Persoon persoon;

		public User()
		{
		}

		public User(string username, string password, Rol rol, Persoon persoon)
		{
			this.username = username;
			this.password = password;
			this.rol = rol;
			this.persoon = persoon;
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

		public virtual string Username
		{
			get
			{
				return username;
			}
			set
			{
				this.username = value;
			}
		}

		public virtual string Password
		{
			get
			{
				return password;
			}
			set
			{
				this.password = value;
			}
		}

		public virtual Rol Rol
		{
			get
			{
				return rol;
			}
			set
			{
				this.rol = value;
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


		public override string ToString()
		{
			return "User{" + "id='" + id + '\'' + ", username='" + username + '\'' + ", password='" + password + '\'' + ", rol=" + rol + ", persoon=" + persoon + '}';
		}
	}

}