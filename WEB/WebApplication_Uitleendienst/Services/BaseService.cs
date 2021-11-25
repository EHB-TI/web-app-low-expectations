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
    public class BaseService<TEntity> : IBaseService<TEntity> where TEntity : class {
        private string BaseUrl => "http://" + Website.API_HOST + "/";

        public IMemoryCache _cache;
        public BaseService(IMemoryCache cache) {
            _cache = cache;
        }
        public void Delete<TEntity1>(int id) {
            throw new NotImplementedException();
        }

        public bool DeleteByProperty(Expression<Func<TEntity, bool>> predicate) {
            throw new NotImplementedException();
        }


        public TEntity Get(string propertyName = null, string propertyValue = null, bool cache = false) {
            var url = BaseUrl + typeof(TEntity).Name.ToLower();
            if (!string.IsNullOrEmpty(propertyName))
                url += $"?{propertyName}={propertyValue}";
            var key = typeof(TEntity).Name + "_Get_" + DateTime.Now.ToString("yy-MM-dd");

            if (!_cache.TryGetValue(key, out TEntity item) || !cache) {
                var request = WebRequest.Create(url);
                request.Method = "GET";
                var response = request.GetResponse();
                using (Stream dataStream = response.GetResponseStream()) {
                    // Open the stream using a StreamReader for easy access.
                    var reader = new StreamReader(dataStream);
                    // Read the content.
                    var responseFromServer = reader.ReadToEnd();
                    // convert to entity
                    _cache.Set(key, JsonConvert.DeserializeObject<TEntity>(responseFromServer));
                }
            }

            return _cache.Get<TEntity>(key);
        }

        public IEnumerable<TEntity> GetAll(string propertyName = null, string propertyValue = null, bool cache = false) {

            var url = BaseUrl + typeof(TEntity).Name.ToLower();
            if (!string.IsNullOrEmpty(propertyName))
                url += $"?{propertyName}={propertyValue}";

            var key = typeof(TEntity).Name + "_GetAll_" + DateTime.Now.ToString("yy-MM-dd");

            if (!_cache.TryGetValue(key, out IEnumerable<TEntity> items) || !cache) {
                var request = WebRequest.Create(url);
                request.Method = "GET";
                var response = request.GetResponse();
                using (Stream dataStream = response.GetResponseStream()) {
                    // Open the stream using a StreamReader for easy access.
                    var reader = new StreamReader(dataStream);
                    // Read the content.
                    var responseFromServer = reader.ReadToEnd();
                    // convert to entity
                    _cache.Set(key, JsonConvert.DeserializeObject<IEnumerable<TEntity>>(responseFromServer));
                }
            }

            return _cache.Get<IEnumerable<TEntity>>(key);
        }

        public async Task<TEntity> Save(TEntity item, string customEntity = null) {
            var url = BaseUrl;
            if (customEntity == null)
                url += typeof(TEntity).Name.ToLower();
            else
                url += customEntity;

            var JsonData = JsonConvert.SerializeObject(item);

            using (var client = new HttpClient()) {
                var response = await client.PostAsync(url, new StringContent(JsonData, Encoding.UTF8, "application/json"));
                var data = await response.Content.ReadAsStringAsync();
                if (data != null)
                    return JsonConvert.DeserializeObject<TEntity>(data);
            }
            return null;
        }

        public void Save(IEnumerable<TEntity> Items) {
            throw new NotImplementedException();
        }

        public void Update(IEnumerable<TEntity> Items) {
            throw new NotImplementedException();
        }

        public void Update(TEntity Item) {
            throw new NotImplementedException();
        }
    }
}
