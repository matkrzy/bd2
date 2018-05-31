using BD_client.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BD_client.Services.Base;

namespace BD_client.Services
{
    public static class CategoryService
    {
        public static async Task<List<Category>> GetUsersRootCategories()
        {
            return await BaseService.GetAsync<List<Category>>("api/v1/categories");
        } 

        public static async Task<List<Category>> GetCategoryChildren(int parentId)
        {
            return await BaseService.GetAsync<List<Category>>($"api/v1/categories/{parentId}");
        }
    }
}
