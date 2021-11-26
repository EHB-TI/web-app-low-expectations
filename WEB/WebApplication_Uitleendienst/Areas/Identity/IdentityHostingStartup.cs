using System;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.UI;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using WebApplication_Uitleendienst.Data;

[assembly: HostingStartup(typeof(WebApplication_Uitleendienst.Areas.Identity.IdentityHostingStartup))]
namespace WebApplication_Uitleendienst.Areas.Identity
{
    public class IdentityHostingStartup : IHostingStartup
    {
        public void Configure(IWebHostBuilder builder)
        {
            builder.ConfigureServices((context, services) => {
                services.AddDbContext<WebApplication_UitleendienstContext>(options =>
                    options.UseSqlServer(
                        context.Configuration.GetConnectionString("WebApplication_UitleendienstContextConnection")));
            });
        }
    }
}