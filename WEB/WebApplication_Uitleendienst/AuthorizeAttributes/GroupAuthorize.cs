using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication_Uitleendienst.AuthorizeAttributes {
    public class GroupAuthorize : AuthorizeAttribute, IAuthorizationFilter {
        private string GroupName { get; set; }

        public GroupAuthorize(string groupName) => GroupName = groupName;
        public void OnAuthorization(AuthorizationFilterContext context) {
            var userGroups = context.HttpContext.User?.Claims.Where(s => s.Type.Equals("cognito:groups"));
            if(userGroups != null) {
                // check wether the group matches the request
                if(userGroups.Any(s => s.Value == GroupName))
                    return;
            }

            //Return based on logic
            context.Result = new UnauthorizedResult();
        }

    }
}
