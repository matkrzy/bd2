using BD_client.Domain;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BD_client.Data.Photos
{
    //TODO: category
    //TODO: tags
    //TODO: Photo a Photov2
    public class Photov2
    {
        [JsonProperty("photoID")]
        public int Id { get; set; }
        public string Name { get; set; }
        public string UserEmail { get; set; }
        public DateTime UploadTime { get; set; }
        public string Description { get; set; }
        public ShareState ShareState { get; set; }
        public PhotoState PhotoState { get; set; }
        public int Rate { get; set; }
        public object Tags { get; set; }
        public object Category { get; set; }
    }
}
