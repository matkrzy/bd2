using BD_client.Common;
using BD_client.Data.Photos;
using BD_client.Domain;
using BD_client.Services;
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
        public NotifyTaskCompletion<PhotoCollection> Photos { get; set; }

        private IDialogCoordinator dialogCoordinator;

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


        public MyPhotosPageViewModel(IDialogCoordinator instance)
        {
            dialogCoordinator = instance;
            var path = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + "//Img//photos";
            Photos = new NotifyTaskCompletion<PhotoCollection>(PhotoService.GetUserPhotos());
            
        }


        virtual protected void OnPropertyChanged(string propName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propName));
        }




    }
}
