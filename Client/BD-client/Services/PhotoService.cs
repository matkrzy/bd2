using BD_client.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using BD_client.Data.Photos;
using System.IO;
using BD_client.Services.Base;

namespace BD_client.Services
{
    public static class PhotoService
    {  
        public static async Task<List<Photo>> GetAllUserPhotos()
        {
            return await BaseService.GetAsync<List<Photo>>("api/v1/photos");
        }

        public static async Task<List<Photo>> GetUsersPhotosByCategoriesIds(bool all, params int[] categoriesIds)
        {
            var categories = string.Join(",", categoriesIds);
            var mode = all ? "all" : "any";
            var path = $"api/v1/photos/categories/{mode}/{categories}";
            return await BaseService.GetAsync<List<Photo>>(path);
        }

        public static async Task<int> AddPhoto(string name, string description, PhotoState photoState, ShareState shareState)
        {
            var body = new { name = name, description = description, photoState = photoState.ToString(), shareState = shareState.ToString() };
            var res = await ApiRequest.PostAsync("api/v1/photos", body);
            var content = await res.Content.ReadAsStringAsync();
            var dictionary = JsonConvert.DeserializeObject<Dictionary<string, int>>(content);
            //return await BaseService.PostAsync("api/v1/photos", body);
            return dictionary["id"];
        }

    }
}
