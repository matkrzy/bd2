using BD_client.Domain;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BD_client.Services.Base
{
    public static class BaseService
    {
        public static async Task<T> GetAsync<T>(string path)
        {
            var res = await ApiRequest.GetAsync(path);
            var stringifiedJson = await res.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<T>(stringifiedJson);
        }
    }
}
