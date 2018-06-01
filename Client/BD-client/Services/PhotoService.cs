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

        /// <summary>
        /// To jest do poprawy
        /// </summary>
        /// <returns></returns>
        public static async Task<PhotoCollection> GetUserPhotos()
        {
            var destination = System.IO.Directory.GetCurrentDirectory() + @"\..\..\tmp\own";
            var response = await ApiRequest.GetAsync("api/v1/photos");
            var stringifiedJson = await response.Content.ReadAsStringAsync();

            var photosDecription = JsonConvert.DeserializeObject<List<Photo>>(stringifiedJson);
            foreach (var photo in photosDecription)
            {
                var res = await ApiRequest.GetAsync($"/api/v1/images/{photo.Id}");
                var completePath = $@"{destination}\{photo.Name}";
                if (!File.Exists(completePath))
                {
                    var fileStream = new FileStream(completePath, FileMode.Create, FileAccess.Write, FileShare.None);
                    await res.Content.CopyToAsync(fileStream);
                    fileStream.Close();
                }
            }
            MainWindow.MainVM.Photos = photosDecription;
            return new PhotoCollection(destination);
        }        

        public static async Task<List<Photov2>> GetUsersPhotosByCategoriesIds(bool all, params int[] categoriesIds)
        {
            var categories = string.Join(",", categoriesIds);
            var mode = all ? "all" : "any";
            var path = $"api/v1/photos/categories/{mode}/{categories}";
            return await BaseService.GetAsync<List<Photov2>>(path);
        }

    }
}
