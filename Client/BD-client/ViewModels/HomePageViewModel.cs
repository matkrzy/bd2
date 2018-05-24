using BD_client.Domain;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace BD_client.ViewModels
{
    public class HomePageViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged = null;
        public ICommand AddCmd { get; set; }
        public ICommand SearchCmd { get; set; }
        //TODO: do celów testowych, potem usunąć
        public ICommand TestCmd { get; set; }

        public HomePageViewModel()
        {
            AddCmd = new RelayCommand(x => Add());
            SearchCmd = new RelayCommand(x => Search());
            TestCmd = new RelayCommand(x => TestClick());

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
        //TODO: do celów testowych, potem usunąć
        private async void TestClick()
        {
            try
            {
                var res = await ApiRequest.GetAsync("api/v1/photos");
            }
            catch (Exception ex)
            {

            }


        }


    }


}
