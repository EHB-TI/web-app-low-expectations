using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;
using WebApplication_Uitleendienst;
using WebApplication_Uitleendienst.Models.Cognito;

namespace WebApplication_Uitleendienst.Data {
    public class ApplicationDbContext : IdentityDbContext {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options) {
        }
        public DbSet<WebApplication_Uitleendienst.Categorie> Categorie { get; set; }
        public DbSet<WebApplication_Uitleendienst.Models.Cognito.UserProfileResponse> UserProfileResponse { get; set; }
    }
}
