using BD_client.Data.Photos;
using BD_client.Domain;
using MahApps.Metro.Controls.Dialogs;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace BD_client.ViewModels
{
    public class PublicPhotosPageViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged = null;
        public PhotoCollection HotPhotos { get; set; }
        public PhotoCollection TrendingPhotos { get; set; }
        public PhotoCollection FreshPhotos { get; set; }
        public ICommand LikeCmd { get; set; }
        private IDialogCoordinator dialogCoordinator;

        public PublicPhotosPageViewModel(IDialogCoordinator instance)
        {
            dialogCoordinator = instance;
            var basePath = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + "//Img//public";
            HotPhotos = new PhotoCollection(basePath + "//hot");
            TrendingPhotos = new PhotoCollection(basePath + "//trending");
            FreshPhotos = new PhotoCollection(basePath + "//fresh");
            LikeCmd = new RelayCommand(x => Like());
        }

        private async void Like()
        {
            await dialogCoordinator.ShowMessageAsync(this, "New like", "You ♥ this photo");
        }

        virtual protected void OnPropertyChanged(string propName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propName));
        }
    }
}
