using BD_client.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using BD_client.Data.Photos;
using System.IO;

namespace BD_client.Services
{
    public static class PhotoService
    {

        public static async Task<PhotoCollection> GetUserPhotos()
        {
            var destination = System.IO.Directory.GetCurrentDirectory() + @"\..\..\tmp\own";
            var response = await ApiRequest.GetAsync("api/v1/photos");
            var stringifiedJson = await response.Content.ReadAsStringAsync();

            var photosDecription = JsonConvert.DeserializeObject<List<Photov2>>(stringifiedJson);
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
            return new PhotoCollection(destination);


        }
    }
}
