using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.Appsettings;
using WebApplication_Uitleendienst.Models.ViewModels.Magazijns;
using WebApplication_Uitleendienst.Models.ViewModels.User;
using WebApplication_Uitleendienst.Services.Interfaces;
using WebApplication_Uitleendienst.Utilities;

namespace WebApplication_Uitleendienst.Areas.Admin.Controllers {
    [Area("Admin")]
    public class UserController : BaseAdminController {
        private readonly CognitoUserManagement _cognitoManagement;
        private readonly IBaseService<Persoon> _persoonService;
        private readonly IBaseService<Magazijn> _magazijnService;
        private readonly IBaseService<ContactMagazijn> _contactMagazijnService;
        private readonly Cognito _config;
        public UserController(Cognito config, IBaseService<Persoon> persoonService, IBaseService<Magazijn> magazijnService, IBaseService<ContactMagazijn> contactMagazijnService) {
            _config = config;
            _cognitoManagement = new CognitoUserManagement(_config);
            _persoonService = persoonService;
            _magazijnService = magazijnService;
            _contactMagazijnService = contactMagazijnService;
        }

        public async Task<IActionResult> Index(UserViewModel model = null) {
            if (model == null) {
                model = new UserViewModel();
            }

            try {

                model.Users = new Dictionary<UserTypeViewModel, List<Amazon.CognitoIdentityProvider.Model.GroupType>>();

                var users = await _cognitoManagement.GetAllUsersAsync();

                if (users != null)
                    foreach (var user in users) {
                        var userType = new UserTypeViewModel();
                        userType.UserType = user;

                        // check if there exist a persooninfo object in our database based on username
                        var persoon = _persoonService.GetAll(propertyName: "username", propertyValue: user.Username, token: UserInfo.Token)?.FirstOrDefault();

                        // retrieve all contactmagazijn objects based on the id of the retrieved person
                        // if results found, contact is a magazijncontact

                        if (persoon != null) {
                            var contactMagazijn = _contactMagazijnService.GetAll(propertyName: "persoonId", propertyValue: persoon.Id)?.FirstOrDefault();
                            if (contactMagazijn != null) {
                                // define the information of the contactmagazijn object used in the view
                                userType.IsMagazijnContact = true;
                                userType.ContactMagazijnInfo = new ContactMagazijnInfoViewModel {
                                    ContactMagazijn = contactMagazijn,
                                    Magazijn = _magazijnService.Get(propertyValue: contactMagazijn.magazijnId)
                                };
                            }
                        }

                        var groupsUser = await _cognitoManagement.GetGroupsForUserAsync(user.Username);


                        model.Users.Add(userType, groupsUser);
                    }

            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.danger;
                return View(model);
            }

            return View(model);
        }


        public async Task<IActionResult> RemoveRole(string username, string groupname) {

            var model = new UserViewModel();

            var response = await _cognitoManagement.RemoveUserFromGroupAsync(username, groupname);

            if (response.HttpStatusCode == System.Net.HttpStatusCode.OK) {
                model.Message = "Rol is succesvol verwijderd.";
                model.Level = Models.ViewModels.InfoLevel.success;
            } else {
                model.Message = "Rol kan niet worden verwijderd.";
                model.Level = Models.ViewModels.InfoLevel.danger;
            }

            return RedirectToAction("Index", model);
        }

        public async Task<IActionResult> AddRole(string username) {

            var model = new UserViewModel();

            model.Groups = await _cognitoManagement.GetAllGroupsAsync();
            model.Username = username;

            return View(model);
        }

        public async Task<IActionResult> AddMagazijnContact(string username) {

            var model = new UserViewModel();

            model.Magazijnen = _magazijnService.GetAll();
            model.Username = username;

            return View(model);
        }




        [HttpPost]
        public async Task<IActionResult> AddMagazijnContactPost(string Username, string magazijnId) {

            var model = new UserViewModel();
            try {
                // check if there exist a persooninfo object in our database based on username
                var persoon = _persoonService.GetAll(propertyName: "username", propertyValue: Username, token: UserInfo.Token)?.FirstOrDefault();
                // if not, create dummy personInfo
                if (persoon == null) {
                    // retrieve correct information from user and store it already in our database              
                    persoon = await _persoonService.Save(new Persoon {
                        Username = UserInfo.Username,
                        Adres = UserInfo.Adress,
                        Email = UserInfo.Email,
                        Familienaam = UserInfo.FamilyName,
                        Voornaam = UserInfo.Name,
                        Telefoon = UserInfo.Telephone
                    }, token: UserInfo.Token);
                }

                // retrieve magazijnInfo
                var magazijn = _magazijnService.Get(propertyValue: magazijnId, token: UserInfo.Token);

                // save new contactMagazijnInfo
                var contactMagazijn = new ContactMagazijn {
                    magazijnId = magazijn.Id,
                    persoonId = persoon.Id
                };

                await _contactMagazijnService.Save(contactMagazijn, token: UserInfo.Token);


                model.Message = $"Gebruiker is nu een magazijncontact voor {magazijn.Naam}";
                model.Level = Models.ViewModels.InfoLevel.success;

            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.danger;
                RedirectToAction("Index", model);
            }

            return RedirectToAction("Index", model);
        }

        public async Task<IActionResult> RemoveMagazijnContact(string contactMagazijnId) {

            var model = new UserViewModel();
            try {

                // get info from given entity
                var contactMag = _contactMagazijnService.Get(propertyValue: contactMagazijnId, token: UserInfo.Token);
                var mag = new Magazijn();
                if (contactMag != null) {
                    mag = _magazijnService.Get(propertyValue: contactMag.magazijnId, token: UserInfo.Token);
                    if (mag == null)
                        throw new Exception("Magazijn is niet gekend voor deze magazijnverantwoordelijke");
                } else
                    throw new Exception("Gebruiker is geen gekende magazijnverantwoordelijke");

                // delete the given contactmagazijnId
                _contactMagazijnService.Delete(contactMagazijnId, token: UserInfo.Token);

                model.Message = $"Gebruiker is geen magazijncontact meer voor {mag.Naam}";
                model.Level = Models.ViewModels.InfoLevel.success;

            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.danger;
                RedirectToAction("Index", model);
            }

            return RedirectToAction("Index", model);
        }


        public async Task<IActionResult> EditMagazijnContact(string contactMagazijnId) {

            var model = new UserViewModel();
            try {
                var editModel = new EditMagazijnContactViewModel();
                // get all info from given entity
                var contactMag = _contactMagazijnService.Get(propertyValue: contactMagazijnId, token: UserInfo.Token);
                var magazijnen = _magazijnService.GetAll(token: UserInfo.Token);

                if (contactMag != null) {
                    if (magazijnen == null)
                        throw new Exception("Magazijnen konden niet worden opgehaald");
                } else
                    throw new Exception("Gebruiker is geen gekende magazijnverantwoordelijke");

                editModel.Magazijnen = magazijnen;
                editModel.ContactMagazijn = contactMag;

                return View(editModel);

            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.danger;
                RedirectToAction("Index", model);
            }

            return RedirectToAction("Index", model);
        }

        [HttpPost]
        public async Task<IActionResult> EditMagazijnContactPost(ContactMagazijn contactMag) {

            var model = new UserViewModel();
            try {

                // retrieve persoon based on current User
                var persoon = _persoonService.GetAll("username", UserInfo.Username, token: UserInfo.Token)?.FirstOrDefault();
                // save/update the given contactmagazijnId
                await _contactMagazijnService.Update(item: contactMag, customEntity: contactMag.id, token: UserInfo.Token);

                model.Message = $"Magazijnverantwoordeijke is succesvol geupdated";
                model.Level = Models.ViewModels.InfoLevel.success;

            } catch (Exception ex) {
                model.Message = ex.Message;
                model.Level = Models.ViewModels.InfoLevel.danger;
                RedirectToAction("Index", model);
            }

            return RedirectToAction("Index", model);
        }



        [HttpPost]
        public async Task<IActionResult> AddRolePost(string Username, string Groupname) {

            var model = new UserViewModel();

            var response = await _cognitoManagement.AddUserToGroupAsync(Username, Groupname);

            if (response.HttpStatusCode == System.Net.HttpStatusCode.OK) {
                model.Message = $"Gebruiker is succesvol toegevoegd aan {Groupname}";
                model.Level = Models.ViewModels.InfoLevel.success;
            } else {
                model.Message = "Rol kan niet worden verwijderd.";
                model.Level = Models.ViewModels.InfoLevel.danger;
            }

            return RedirectToAction("Index", model);
        }

        public async Task<IActionResult> ToggleMagazijnContact(string username, bool isMagazijnContact) {
            dynamic response;
            if (!isMagazijnContact)
                response = await _cognitoManagement.AddUserToGroupAsync(username, "MagazijnContact");
            else
                response = await _cognitoManagement.RemoveUserFromGroupAsync(username, "MagazijnContact");

            return response.HttpStatusCode;
        }


    }
}
