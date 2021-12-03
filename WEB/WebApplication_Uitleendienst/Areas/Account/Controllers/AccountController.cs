using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Areas.Account.Models.ViewModels;
using WebApplication_Uitleendienst.Models.Appsettings;
using WebApplication_Uitleendienst.Models.ViewModels.Identity;
using WebApplication_Uitleendienst.Utilities;

namespace WebApplication_Uitleendienst.Areas.Account {
    [Area("Account")]
    [Authorize]
    public class AccountController : BaseController {
        private readonly CognitoUserManagement _cognitoManagement;
        private readonly Cognito _config;
        public AccountController(Cognito config) {
            _config = config;
            _cognitoManagement = new CognitoUserManagement(_config);
        }

        public IActionResult Index(AccountViewModel model = null) {
            return View(model);
        }

        public async Task<IActionResult> Manage(AccountViewModel model = null) {
            if (model == null)
                model = new AccountViewModel();
            model.User = await _cognitoManagement.GetUserAsync(UserInfo.Email);
            return View(model);
        }

        [HttpPost]
        public async Task<IActionResult> ManagePost(AccountViewModel model) {
            var updateModel = new UpdateProfileModel {
                AccessToken = UserInfo.Token,
                Address = model.User.Address,
                FamilyName = model.User.FamilyName,
                Name = model.User.Name,
                PhoneNumber = model.User.PhoneNumber
            };
            // update user with updated attributes
            var result = await _cognitoManagement.UpdateUserAttributesAsync(updateModel);

            model.Message = result.Message;

            if (result.IsSuccess) {
                model.Level = WebApplication_Uitleendienst.Models.ViewModels.InfoLevel.success;
                return RedirectToAction("Index", model);
            } else {
                model.Level = WebApplication_Uitleendienst.Models.ViewModels.InfoLevel.danger;
                return RedirectToAction("Manage", model);
            }
        }
    }
}
