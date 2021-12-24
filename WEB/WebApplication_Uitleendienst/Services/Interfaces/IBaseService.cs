using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models;

namespace WebApplication_Uitleendienst.Services.Interfaces {
    public interface IBaseService<TEntity> where TEntity : class {
        public void Update(IEnumerable<TEntity> Items, string token = null);
        public Task<TEntity> Update(TEntity item, string customEntity = null, string token = null);
        public Task<TEntity> Save(TEntity item, string customEntity = null, string token = null);
        public Task<List<TEntity>> SaveAll(List<TEntity> Items, string customEntity = null, string token = null);
        public TEntity Get(string propertyName = null, string propertyValue = null, bool cache = false, string token = null);
        public void Delete(string id, string token = null);
        public bool DeleteByProperty(Expression<Func<TEntity, bool>> predicate, string token = null);
        public IEnumerable<TEntity> GetAll(string propertyName = null, string propertyValue = null, bool cache = false, string token = null);

    }
}
