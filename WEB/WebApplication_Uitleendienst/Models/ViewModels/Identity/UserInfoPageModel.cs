using Amazon.CognitoIdentityProvider;
using Amazon.Extensions.CognitoAuthentication;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.IdentityModel.Protocols.OpenIdConnect;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Models.ViewModels.Identity {
    [Authorize]
    public class UserInfoPageModel : PageModel {

        private HttpContext httpContextAccessor;

        public UserInfoPageModel(HttpContext httpContextAccessor) {
            this.httpContextAccessor = httpContextAccessor;
            var x = httpContextAccessor.Request.Query["request_uri"];

        }
        public string Token {
            get {
                var x = GetToken().Result;
                return x;
            }
        }

        private async Task<string> GetToken() {
            string token, refreshtoken, idtoken;

            token = await httpContextAccessor.GetTokenAsync(OpenIdConnectParameterNames.AccessToken);
            refreshtoken = await httpContextAccessor.GetTokenAsync(OpenIdConnectParameterNames.RefreshToken);
            idtoken = await httpContextAccessor.GetTokenAsync(OpenIdConnectParameterNames.IdToken);

            return token;
        }

        public bool IsAdmin {
            get {
                return (bool)httpContextAccessor.User?.Claims.Where(s => s.Type.Equals("cognito:groups")).Any(s => s.Value.Equals("Admins-WebApplication"));
            }
        }

        public bool IsMagazijnVerantwoordelijke {
            get {
                var dependencyScope = httpContextAccessor.RequestServices;
                var persoonService = dependencyScope.GetService(typeof(IBaseService<Persoon>)) as IBaseService<Persoon>;
                var contactMagService = dependencyScope.GetService(typeof(IBaseService<ContactMagazijn>)) as IBaseService<ContactMagazijn>;
                try {
                    var persoon = persoonService.GetAll(propertyName: "username", propertyValue: Username, token: Token)?.FirstOrDefault();
                    if (persoon != null) {
                        return contactMagService.GetAll(propertyName: "persoonId", propertyValue: persoon.Id, token: Token)?.FirstOrDefault() != null ? true : false;
                    }
                } catch (Exception ex) {
                    //Return based on logic
                }
                return false;
            }
        }

        public string Email {
            get {
                return httpContextAccessor.User?.Claims.FirstOrDefault(c => c.Type.Equals("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress"))?.Value;
            }
        }

        public string Username {
            get {
                return httpContextAccessor.User?.Claims.FirstOrDefault(c => c.Type.Equals("cognito:username"))?.Value;
            }
        }

        public string Name {
            get {
                return httpContextAccessor.User?.Claims.FirstOrDefault(c => c.Type.Equals("name"))?.Value;
            }
        }

        public string Telephone {
            get {
                return httpContextAccessor.User?.Claims.FirstOrDefault(c => c.Type.Equals("phone_number"))?.Value;
            }
        }

        public string Adress {
            get {
                return httpContextAccessor.User?.Claims.FirstOrDefault(c => c.Type.Equals("address"))?.Value;
            }
        }

        public string FamilyName {
            get {
                return httpContextAccessor.User?.Claims.FirstOrDefault(c => c.Type.Equals("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname"))?.Value;
            }
        }
    }

}
