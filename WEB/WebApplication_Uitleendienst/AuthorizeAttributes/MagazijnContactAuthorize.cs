using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.IdentityModel.Protocols.OpenIdConnect;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.AuthorizeAttributes {
    public class MagazijnContactAuthorize : AuthorizeAttribute, IAuthorizationFilter {

        public MagazijnContactAuthorize() {

        }

        public async void OnAuthorization(AuthorizationFilterContext context) {

            var token = await context.HttpContext.GetTokenAsync(OpenIdConnectParameterNames.AccessToken);
            var username = context.HttpContext.User?.Claims.FirstOrDefault(c => c.Type.Equals("cognito:username"))?.Value;

            var dependencyScope = context.HttpContext.RequestServices;
            var persoonService = dependencyScope.GetService(typeof(IBaseService<Persoon>)) as IBaseService<Persoon>;
            var contactMagService = dependencyScope.GetService(typeof(IBaseService<ContactMagazijn>)) as IBaseService<ContactMagazijn>;
            try {
                var persoon = persoonService.GetAll(propertyName: "username", propertyValue: username, token: token)?.FirstOrDefault();
                if (persoon != null) {
                    var isContactMag = contactMagService.GetAll(propertyName: "persoonId", propertyValue: persoon.Id, token: token)?.FirstOrDefault() != null ? true : false;
                    if (isContactMag)
                        return;
                }
            } catch (Exception ex) {
                //Return based on logic
                context.Result = new UnauthorizedResult();
            }

            //Return based on logic
            context.Result = new UnauthorizedResult();
        }



    }
}
