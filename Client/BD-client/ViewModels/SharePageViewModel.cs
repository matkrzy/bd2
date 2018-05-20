using BD_client.Domain;
using MahApps.Metro.Controls.Dialogs;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace BD_client.ViewModels
{
    class SharePageViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged = null;

        public ObservableCollection<Photo> Photos { get; set; }
        public ObservableCollection<User> Users { get; set; }

        public ICommand CancelCmd { get; set; }
        public ICommand RemovePhotoCmd { get; set; }
        public ICommand ShareCmd { get; set; }
        private IDialogCoordinator dialogCoordinator;

        public int DataGridSelectedIndex
        {
            get
            {
                return _dataGridSelectedIndex;
            }
            set
            {
                _dataGridSelectedIndex = value;
                OnPropertyChanged("DataGridSelectedIndex");
            }
        }

        private string _page;
        private string _username;
        private int _dataGridSelectedIndex;


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

        public string Username
        {
            get
            {
                return _username;
            }
            set
            {
                _username = value;
                OnPropertyChanged("Username");
            }
        }



        public SharePageViewModel(IDialogCoordinator instance)
        {
            dialogCoordinator = instance;
            Photos = new ObservableCollection<Photo>();
            ShareCmd = new RelayCommand(x => Share());
            CancelCmd = new RelayCommand(x => Cancel());
            RemovePhotoCmd = new RelayCommand(x => RemovePhoto());
            Photos.Add(new Domain.Photo() { Index = 1, Path = "C:\\Photo1.jpg" });
            Photos.Add(new Domain.Photo() { Index = 2, Path = "C:\\Photo2.jpg" });
            Users = new ObservableCollection<User>();
            Users.Add(new Domain.User() { FirstName = "Marta" });
            Users.Add(new Domain.User() { FirstName = "Gazder" });
            Users.Add(new Domain.User() { FirstName = "Gorczi" });
            Users.Add(new Domain.User() { FirstName = "Michał" });
        }
        private void RemovePhoto()
        {
            Photos.RemoveAt(DataGridSelectedIndex);
        }
        private async void Share()
        {
            //share
            await dialogCoordinator.ShowMessageAsync(this, "Success", Photos.Count + " of " + Photos.Count + " photos was shared to user " + Username);
            Photos.Clear();
        }

        private void Cancel()
        {
            MainWindow.MainVM.Page = "Pages/MyPhotosPage.xaml";
            MainWindow.MainVM.SelectedIndex = -1;
        }

        virtual protected void OnPropertyChanged(string propName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propName));
        }

    }
}
