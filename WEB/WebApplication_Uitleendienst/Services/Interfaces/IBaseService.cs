using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using WebApplication_Uitleendienst.Models;

namespace WebApplication_Uitleendienst.Services.Interfaces {
    public interface IBaseService<TEntity> where TEntity : class {
        public void Update(IEnumerable<TEntity> Items);
        public void Update(TEntity Item);
        public Task<TEntity> Save(TEntity item, string customEntity = null);
        public void Save(IEnumerable<TEntity> Items);
        public TEntity Get(string propertyValue = null, bool cache = false);
        public void Delete<TEntity>(int id);
        public bool DeleteByProperty(Expression<Func<TEntity, bool>> predicate);
        public IEnumerable<TEntity> GetAll(string propertyName = null, string propertyValue = null, bool cache = false);

    }
}
