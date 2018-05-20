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
    public class HomePageViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged = null;
        public ICommand AddCmd { get; set; }
        public ICommand SearchCmd { get; set; }

        public HomePageViewModel()
        {
            AddCmd = new RelayCommand(x => Add());
            SearchCmd = new RelayCommand(x => Search());
        }

        private void Add()
        {
            MainWindow.MainVM.Page = "Pages/AddPhotosPage.xaml";
            MainWindow.MainVM.SelectedIndex = 0;
        }

        private void Search()
        {
            MainWindow.MainVM.Page = "Pages/SearchPage.xaml";
            MainWindow.MainVM.SelectedIndex = 2;
        }
    }


}
