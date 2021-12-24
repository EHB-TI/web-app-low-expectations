using Microsoft.Extensions.Caching.Memory;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Linq.Expressions;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Services {


    //INFO STATUSCODES
    // 401 -> geen auth header aanwezig terwijl api deze wel verwacht
    // 400 -> bad requests (params, caps..)
    // 403 -> wel auth header maar niet juiste groepen rechten om informatie krijgen

    public class BaseService<TEntity> : IBaseService<TEntity> where TEntity : class {
        private string BaseUrl => "http://" + Website.API_HOST + "/";

        public IMemoryCache _cache;
        public BaseService(IMemoryCache cache) {
            _cache = cache;
        }
        public void Delete(string id, string token = null) {

            var url = BaseUrl;
            url += $"{typeof(TEntity).Name.ToLower()}/{id}";

            var request = WebRequest.Create(url);
            request.Method = "DELETE";
            if (!string.IsNullOrEmpty(token))
                request.Headers.Add("Authorization", token);
            else
                request.Headers.Add("Authorization", "");
            request.Headers.Add("Origin", Website.HOST_NAME);


            var requestWriter = new StreamWriter(request.GetRequestStream(), System.Text.Encoding.ASCII);

            requestWriter.Close();

            try {
                var webResponse = request.GetResponse();
                var webStream = webResponse.GetResponseStream();
                var responseReader = new StreamReader(webStream);
                var response = responseReader.ReadToEnd();
                if (response == null)
                    throw new HttpRequestException("Bad response");
                responseReader.Close();

            } catch (Exception ex) {
                throw new HttpRequestException(ex.Message);
            }
        }

        public bool DeleteByProperty(Expression<Func<TEntity, bool>> predicate, string token = null) {
            throw new NotImplementedException();
        }


        public TEntity Get(string propertyName = null, string propertyValue = null, bool cache = false, string token = null) {

            var url = BaseUrl + typeof(TEntity).Name.ToLower();

            if (string.IsNullOrEmpty(propertyName) && !string.IsNullOrEmpty(propertyValue))
                url += $"/{propertyValue}";
            if (!string.IsNullOrEmpty(propertyName) && !string.IsNullOrEmpty(propertyValue))
                url += $"?{propertyName}={propertyValue}";

            var key = typeof(TEntity).Name + "_Get_" + DateTime.Now.ToString("yy-MM-dd");

            if (!_cache.TryGetValue(key, out TEntity item) || !cache) {
                try {
                    var request = WebRequest.Create(url);
                    request.Method = "GET";
                    if (!string.IsNullOrEmpty(token))
                        request.Headers.Add("Authorization", token);
                    else
                        request.Headers.Add("Authorization", "");
                    request.Headers.Add("Origin", Website.HOST_NAME);
                    var response = request.GetResponse();
                    using (Stream dataStream = response.GetResponseStream()) {
                        // Open the stream using a StreamReader for easy access.
                        var reader = new StreamReader(dataStream);
                        // Read the content.
                        var responseFromServer = reader.ReadToEnd();
                        // convert to entity
                        _cache.Set(key, JsonConvert.DeserializeObject<TEntity>(responseFromServer));
                    }
                } catch (Exception ex) {
                    throw new HttpRequestException(ex.Message);
                }
            }

            return _cache.Get<TEntity>(key);
        }

        public IEnumerable<TEntity> GetAll(string propertyName = null, string propertyValue = null, bool cache = false, string token = null) {

            var url = BaseUrl + typeof(TEntity).Name.ToLower();

            if (string.IsNullOrEmpty(propertyName) && !string.IsNullOrEmpty(propertyValue))
                url += $"/{propertyValue}";
            if (!string.IsNullOrEmpty(propertyName) && !string.IsNullOrEmpty(propertyValue))
                url += $"?{propertyName}={propertyValue}";

            var key = typeof(TEntity).Name + "_GetAll_" + DateTime.Now.ToString("yy-MM-dd");

            if (!_cache.TryGetValue(key, out IEnumerable<TEntity> items) || !cache) {
                try {
                    var request = WebRequest.Create(url);
                    request.Method = "GET";
                    if (!string.IsNullOrEmpty(token))
                        request.Headers.Add("Authorization", token);
                    else
                        request.Headers.Add("Authorization", "");
                    request.Headers.Add("Origin", Website.HOST_NAME);
                    var response = request.GetResponse();
                    using (Stream dataStream = response.GetResponseStream()) {
                        // Open the stream using a StreamReader for easy access.
                        var reader = new StreamReader(dataStream);
                        // Read the content.
                        var responseFromServer = reader.ReadToEnd();
                        // convert to entity
                        _cache.Set(key, JsonConvert.DeserializeObject<IEnumerable<TEntity>>(responseFromServer));
                    }
                } catch (Exception ex) {
                    if (ex.Message.Contains("404"))
                        return null;
                    else
                        throw;
                }
            }

            return _cache.Get<IEnumerable<TEntity>>(key);
        }

        public async Task<TEntity> Save(TEntity item, string customEntity = null, string token = null) {
            var url = BaseUrl;
            if (customEntity == null)
                url += typeof(TEntity).Name.ToLower();
            else
                url += customEntity;


            var request = WebRequest.Create(url);
            request.Method = "POST";
            if (!string.IsNullOrEmpty(token))
                request.Headers.Add("Authorization", token);
            else
                request.Headers.Add("Authorization", "");
            request.Headers.Add("Origin", Website.HOST_NAME);
            request.ContentType = "application/json";


            var JsonData = JsonConvert.SerializeObject(item);

            var requestWriter = new StreamWriter(request.GetRequestStream(), System.Text.Encoding.ASCII);
            requestWriter.Write(JsonData);
            requestWriter.Close();

            try {
                var webResponse = request.GetResponse();
                var webStream = webResponse.GetResponseStream();
                var responseReader = new StreamReader(webStream);
                var response = responseReader.ReadToEnd();
                if (response != null)
                    return JsonConvert.DeserializeObject<TEntity>(response);
                responseReader.Close();

            } catch (Exception ex) {
                return null;
            }

            return null;
        }

        public async Task<List<TEntity>> SaveAll(List<TEntity> Items, string customEntity = null, string token = null) {
            var url = BaseUrl;
            if (customEntity == null)
                url += typeof(TEntity).Name.ToLower();
            else
                url += customEntity;


            var request = WebRequest.Create(url);
            request.Method = "POST";
            if (!string.IsNullOrEmpty(token))
                request.Headers.Add("Authorization", token);
            else
                request.Headers.Add("Authorization", "");
            request.Headers.Add("Origin", Website.HOST_NAME);
            request.ContentType = "application/json";


            var JsonData = JsonConvert.SerializeObject(Items);

            var requestWriter = new StreamWriter(request.GetRequestStream(), System.Text.Encoding.ASCII);
            requestWriter.Write(JsonData);
            requestWriter.Close();

            try {
                var webResponse = request.GetResponse();
                var webStream = webResponse.GetResponseStream();
                var responseReader = new StreamReader(webStream);
                var response = responseReader.ReadToEnd();
                if (response != null)
                    return JsonConvert.DeserializeObject<List<TEntity>>(response);
                responseReader.Close();

            } catch (Exception ex) {
                return null;
            }

            return null;
        }

        public void Update(IEnumerable<TEntity> Items, string token = null) {
            throw new NotImplementedException();
        }

        public async Task<TEntity> Update(TEntity item, string customEntity = null, string token = null) {

            var url = BaseUrl + typeof(TEntity).Name.ToLower();

            if (customEntity != null)
                url += $"/{customEntity}";


            var request = WebRequest.Create(url);
            request.Method = "PUT";
            if (!string.IsNullOrEmpty(token))
                request.Headers.Add("Authorization", token);
            else
                request.Headers.Add("Authorization", "");
            request.Headers.Add("Origin", Website.HOST_NAME);
            request.ContentType = "application/json";


            var JsonData = JsonConvert.SerializeObject(item);


            var requestWriter = new StreamWriter(request.GetRequestStream(), System.Text.Encoding.ASCII);
            requestWriter.Write(JsonData);
            requestWriter.Close();

            try {
                var webResponse = request.GetResponse();
                var webStream = webResponse.GetResponseStream();
                var responseReader = new StreamReader(webStream);
                var response = responseReader.ReadToEnd();
                if (response != null)
                    return JsonConvert.DeserializeObject<TEntity>(response);
                responseReader.Close();

            } catch (Exception ex) {
                throw new Exception(ex.Message);
            }

            return null;
        }

    }
}
