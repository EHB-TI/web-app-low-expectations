using Amazon;
using Amazon.CognitoIdentityProvider;
using Amazon.CognitoIdentityProvider.Model;
using Amazon.Extensions.CognitoAuthentication;
using Amazon.Runtime;
using Amazon.Runtime.CredentialManagement;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models.Appsettings;
using WebApplication_Uitleendienst.Models.Cognito;

namespace WebApplication_Uitleendienst.Utilities {
    public class CognitoUserManagement {
        private readonly Cognito _cognitoConfig;
        private readonly AmazonCognitoIdentityProviderClient _provider;
        private readonly CognitoUserPool _userPool;

        public CognitoUserManagement(Cognito cognitoSettings) {
            _cognitoConfig = cognitoSettings;
            _provider = new AmazonCognitoIdentityProviderClient(_cognitoConfig.AccesKeyId, _cognitoConfig.AccesSecretKey,
                RegionEndpoint.GetBySystemName(_cognitoConfig.Region));
            _userPool = new CognitoUserPool(_cognitoConfig.UserPoolId, _cognitoConfig.AppClientId, _provider);
        }


        private async Task<ListUsersResponse> FindUsersByEmailAddress(string emailAddress) {

            ListUsersRequest listUsersRequest = new ListUsersRequest {
                UserPoolId = _cognitoConfig.UserPoolId,
                Filter = $"email=\"{emailAddress}\""
            };
            return await _provider.ListUsersAsync(listUsersRequest);
        }




        public async Task<UserProfileResponse> GetUserAsync(string emailAddress) {
            // Find for users by emailAddress
            var users = await FindUsersByEmailAddress(emailAddress);

            // extract the first matching user
            // which technically is the current user
            var userResponse = users.Users.FirstOrDefault();

            // attributes property
            // contains all the values
            // that are associated with the user
            var attributes = userResponse.Attributes;

            // extract each property from the list
            // and assign them to the response model
            var response = new UserProfileResponse {
                EmailAddress = attributes.FirstOrDefault(s => s.Name.Equals("email")).Value,
                Name = attributes.FirstOrDefault(s => s.Name.Equals("name")).Value,
                FamilyName = attributes.FirstOrDefault(s => s.Name.Equals("family_name")).Value,
                PhoneNumber = attributes.FirstOrDefault(s => s.Name.Equals("phone_number")).Value,
                UserId = attributes.FirstOrDefault(s => s.Name.Equals("sub")).Value
            };

            // address is generally maintained
            // as a JSON of components
            // such as street_address, country, region, pincode and so on
            // based on the OpenID design guidelines

            response.Address = attributes.FirstOrDefault(s => s.Name.Equals("address")).Value;


            return response;
        }


        public async Task<UpdateProfileResponse> UpdateUserAttributesAsync(UpdateProfileModel model) {
            var userAttributesRequest = new UpdateUserAttributesRequest {
                AccessToken = model.AccessToken
            };

            userAttributesRequest.UserAttributes.Add(new AttributeType {
                Value = model.Name,
                Name = "name"
            });

            userAttributesRequest.UserAttributes.Add(new AttributeType {
                Value = model.FamilyName,
                Name = "family_name"
            });

            userAttributesRequest.UserAttributes.Add(new AttributeType {
                Value = model.PhoneNumber,
                Name = "phone_number"
            });

            // upload the incoming profile photo to user's S3 folder
            // and get the s3 url
            // add the s3 url to the profile_photo attribute of the userCognito
            // if (model.ProfilePhoto != null)
            // {
            //     var picUrl = await _storage.AddItem(model.ProfilePhoto, "profile");
            //     userAttributesRequest.UserAttributes.Add(new AttributeType
            //     {
            //         Value = picUrl,
            //         Name = "picture"
            //     });
            // }

            if (model.Gender != null) {
                userAttributesRequest.UserAttributes.Add(new AttributeType {
                    Value = model.Gender,
                    Name = "gender"
                });
            }



            userAttributesRequest.UserAttributes.Add(new AttributeType {
                Value = model.Address,
                Name = "address"
            });


            var response = await _provider.UpdateUserAttributesAsync(userAttributesRequest);
            if (response.HttpStatusCode == HttpStatusCode.OK)
                return new UpdateProfileResponse {
                    UserId = model.UserId,
                    Message = "Profile updated",
                    IsSuccess = true
                };
            return new UpdateProfileResponse {
                UserId = model.UserId,
                Message = "Profile not updated",
                IsSuccess = false
            };
        }

    }
}
