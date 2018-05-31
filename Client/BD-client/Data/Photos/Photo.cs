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
        [JsonProperty("photoID")]
        public int Id { get; set; }
        [JsonProperty("name")]
        public string Name { get; set; }
        [JsonProperty("user")]
        public string UserEmail { get; set; }
        [Browsable(false)]
        [JsonProperty("uploadTime")]
        public DateTime UploadTime { get; set; }
        [JsonProperty("description")]
        public string Description { get; set; }
        [JsonProperty("shareState")]
        public ShareState ShareState { get; set; }
        [JsonProperty("photoState")]
        public PhotoState PhotoState { get; set; }
        [Browsable(false)]
        public int Rate { get; set; }
        [Browsable(false)]
        public List<Tag> Tags { get; set; }
        [Browsable(false)]
        [JsonProperty("category")]
        public Category Category { get; set; }

        [Browsable(false)]
        public ExifMetadata ExifMetadata { get; set; }

        [Browsable(false)]
        public int LikeCount { get { return 45; } }

        [Browsable(false)]
        public BitmapFrame Image { get; set; }
        [Browsable(false)]
        public Uri Uri { get; set; }
        [Browsable(false)]
        public long UserID { get; set; }
        [Browsable(false)]
        [JsonProperty("path")]
        public String Path { get; set; }

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
