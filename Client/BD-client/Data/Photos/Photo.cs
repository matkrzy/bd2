using BD_client.Data.Photos;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media.Imaging;

namespace BD_client.Domain
{


    public class Photo
    {
        [Browsable(false)]
        [JsonProperty("id")]
        public int Index { get; set; }
        [JsonProperty("path")]
        public String Path { get; set; }
        [JsonProperty("name")]
        public String Name { get; set; }
        [JsonProperty("description")]
        public String Description { get; set; }
        [JsonProperty("photoState")]
        public PhotoState PhotoState { get; set; }
        [JsonProperty("shareState")]
        public ShareState ShareState { get; set; }
        [JsonProperty("uploadTime")]
        [Browsable(false)]
        public DateTime UploadTime { get; set; }
        [JsonProperty("tags")]
        [Browsable(false)]
        public List<Tag> Tags { get; set; }
        [Browsable(false)]
        public ExifMetadata ExifMetadata { get; set; }
        [Browsable(false)]
        public int LikeCount { get { return 45; } }
        [Browsable(false)]
        [JsonProperty("rate")]
        public int Rate { get; set;}
        [Browsable(false)]
        public BitmapFrame Image { get; set; }
        [Browsable(false)]
        public Uri Uri { get; set; }
        [Browsable(false)]
        [JsonProperty("category")]
        public Category Category { get; set; }


        public Photo(string path)
        {
            Path = path;
            Uri = new Uri(path);
            Image = BitmapFrame.Create(Uri);
            ExifMetadata = new ExifMetadata(Uri);
        }

        public Photo()
        {

        }
    }
}
