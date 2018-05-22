using BD_client.Data.Photos;
using BD_client.Domain;
using MahApps.Metro.Controls;
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
    public class MyPhotosPageViewModel : INotifyPropertyChanged
    {

        public event PropertyChangedEventHandler PropertyChanged = null;

        private string _page;
        public PhotoCollection Photos { get; set; }

        private IDialogCoordinator dialogCoordinator;
        private List<int> _selectedPhotos;

        public string Page
        {
            get
            {
                return _page;
            }
            set
            {
                _page = value;
                OnPropertyChanged("Page");
            }
        }

        public List<int> SelectedPhotos
        {
            get
            {
                return _selectedPhotos;
            }
            set
            {
                _selectedPhotos = value;
                OnPropertyChanged("SelectedPhotos");
            }
        }


        public MyPhotosPageViewModel(IDialogCoordinator instance)
        {
            dialogCoordinator = instance;
            var path = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + "//Img//photos";
            Photos = new PhotoCollection(path);
            MainWindow.MainVM.Photos = Photos;
        }


        virtual protected void OnPropertyChanged(string propName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propName));
        }




    }
}
