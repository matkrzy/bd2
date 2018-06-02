using BD_client.Data.Photos;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using System.Windows;

namespace BD_client.Domain
{
    //TODO: PhotoCollectionv2
    public class PhotoCollectionv2 : ObservableCollection<Photo>
    {
        public List<Photo> Photos { get; set; }
        public DirectoryInfo DirectoryInfo { get; set; }
        public PhotoCollectionv2(string path)
        {
            DirectoryInfo = new DirectoryInfo(path);
            Photos = new List<Photo>();
        }                
        public void Update()
        {
            ClearItems();
            foreach (var fileInfo in DirectoryInfo.GetFiles())
            {
                int photoId;
                if (int.TryParse(Path.GetFileNameWithoutExtension(fileInfo.FullName), out photoId))
                {
                    var photo = Photos.FirstOrDefault(x => x.Id == photoId);
                    if(photo != null)
                    {
                        Add(new Photo
                        {
                            Id = photoId,
                            Path = fileInfo.FullName,
                            Name = photo.Name
                        });
                    }
                }
            }
        }
        public void Update(IEnumerable<int> exceptIds)
        {
            ClearItems();
            foreach(var fileInfo in DirectoryInfo.GetFiles())
            {
                int photoId;
                if (int.TryParse(Path.GetFileNameWithoutExtension(fileInfo.FullName), out photoId) && !exceptIds.Contains(photoId))
                {
                    Add(new Photo(fileInfo.FullName, photoId));
                }
            }

        }
    }

    [Obsolete]
    public class PhotoCollection : ObservableCollection<Photo>
    {
        private DirectoryInfo Directory;
        public PhotoCollection()
        {
            var path = System.IO.Directory.GetCurrentDirectory() + @"\..\..\tmp";
            Directory = new DirectoryInfo(path);
            Update();
        }
        public PhotoCollection(string path)
        {
            Directory = new DirectoryInfo(path);
            Update();
        }
        private void Update()
        {
            ClearItems();
            try
            {
                //TODO:
                foreach (var fileInfo in Directory.GetFiles("*.jpg"))
                {
                    Add(new Photo(fileInfo.FullName, 0));
                }
            }
            catch (Exception e)
            {
                MessageBox.Show($"An Error Occured: {e.Message}");
            }
        }
    }

    [Obsolete]
    public class TemporaryPhotoCollection : ObservableCollection<string>
    {
        private DirectoryInfo Directory;
        public TemporaryPhotoCollection(string path)
        {
            Directory = new DirectoryInfo(path);
            Update();
        }
        private void Update()
        {
            ClearItems();
            try
            {
                foreach (var fileInfo in Directory.GetFiles("*.jpg"))
                {
                    Add(fileInfo.FullName);
                }
            }
            catch (Exception e)
            {
                MessageBox.Show($"An Error Occured: {e.Message}");
            }
        }

    }
}
