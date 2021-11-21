using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Linq.Expressions;
using System.Net;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models;
using WebApplication_Uitleendienst.Services.Interfaces;

namespace WebApplication_Uitleendienst.Services {
    public class BaseService<TEntity> : IBaseService<TEntity> where TEntity : class {
        public void Delete<TEntity1>(int id) {
            throw new NotImplementedException();
        }

        public bool DeleteByProperty(Expression<Func<TEntity, bool>> predicate) {
            throw new NotImplementedException();
        }

        public TEntity Get(Expression<Func<TEntity, bool>> predicate, bool cache = false) {
            throw new NotImplementedException();
        }

        public IEnumerable<TEntity> GetAll(bool cache = false) {

            var url = "http://" + Website.API_HOST + "/" + typeof(TEntity).Name;

            WebRequest request = WebRequest.Create(url);
            request.Method = "GET";
            WebResponse response = request.GetResponse();

            using (Stream dataStream = response.GetResponseStream())
            {
                // Open the stream using a StreamReader for easy access.
                StreamReader reader = new StreamReader(dataStream);
                // Read the content.
                string responseFromServer = reader.ReadToEnd();
                // Display the content.
                Console.WriteLine(responseFromServer);
            }

            return null;
        }

        public TEntity Save(TEntity item) {
            throw new NotImplementedException();
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
