using BD_client.Data.Photos;
using BD_client.Domain;
using System.ComponentModel;
using System.IO;

namespace BD_client.ViewModels
{
    public class CategoriesPageViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;
        public PhotoCollection Photos { get; set; }

        public CategoriesPageViewModel()
        {
            var path = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + "//Img//photos";
            Photos = new PhotoCollection(path);
        }

    }
}
