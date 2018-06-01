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
            var filesToDisplay = new List<FileInfo>();
            var photoIds = Photos.Select(x => x.Id);

            foreach (var fileInfo in DirectoryInfo.GetFiles())
            {
                var photoId = int.Parse(Path.GetFileNameWithoutExtension(fileInfo.FullName));
                if (photoIds.Contains(photoId))
                {
                    Add(new Photo(fileInfo.FullName));
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
                foreach (var fileInfo in Directory.GetFiles("*.jpg"))
                {
                    Add(new Photo(fileInfo.FullName));
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
