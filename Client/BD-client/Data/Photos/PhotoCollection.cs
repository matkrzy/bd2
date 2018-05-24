using System;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using System.Windows;

namespace BD_client.Domain
{
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
                foreach(var fileInfo in Directory.GetFiles("*.jpg"))
                {
                    Add(new Photo(fileInfo.FullName));
                }
            }
            catch(Exception e)
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
