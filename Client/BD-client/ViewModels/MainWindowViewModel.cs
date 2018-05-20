﻿using BD_client.Domain;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace BD_client.ViewModels
{
    public class MainWindowViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged = null;

        public ICommand ProfileCmd { get; set; }
        public ICommand LogoutCmd { get; set; }
        public ICommand MyPhotosCmd { get; set; }
        public ICommand HomeCmd { get; set; }
        public ICommand PublicPhotosCmd { get; }
        public ICommand CategoriesCmd { get; }

        private bool _enabled;

        private int _selectedIndex;
        private String _user;
        public String url = "http://localhost:9000/";

        public int SelectedIndex
        {
            get
            {
                return _selectedIndex;
            }
            set
            {
                _selectedIndex = value;
                OnPropertyChanged("SelectedIndex");
            }
        }

        public String User
        {
            get
            {
                return _user;
            }
            set
            {
                _user = value;
                OnPropertyChanged("User");
            }
        }

        public bool Enabled
        {
            get
            {
                return _enabled;
            }
            set
            {
                _enabled = value;
                OnPropertyChanged("Enabled");
            }
        }

        private string _page;

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

        internal LogInPageViewModel DataContext { get; private set; }

        public MainWindowViewModel()
        {
            MyPhotosCmd = new RelayCommand(x => ShowMyPhotos());
            HomeCmd = new RelayCommand(x => ShowHome());
            ProfileCmd = new RelayCommand(x => Profile());
            LogoutCmd = new RelayCommand(x => Logout());
            PublicPhotosCmd = new RelayCommand(x => ShowPublicPhotos());
            CategoriesCmd = new RelayCommand(x => ShowCategories());
            Page = "Pages/LogInPage.xaml";
            Enabled = false;
            SelectedIndex = -1;
            User = "";
        }

        virtual protected void OnPropertyChanged(string propName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propName));
        }

        private void ShowCategories()
        {
            MainWindow.MainVM.Page = "Pages/CategoriesPage.xaml";
            MainWindow.MainVM.SelectedIndex = -1;
        }
        private void ShowMyPhotos()
        {
            MainWindow.MainVM.Page = "Pages/MyPhotosPage.xaml";
            MainWindow.MainVM.SelectedIndex = -1;
        }

        private void ShowHome()
        {
            MainWindow.MainVM.Page = "Pages/HomePage.xaml";
            MainWindow.MainVM.SelectedIndex = -1;
        }
        private void ShowPublicPhotos()
        {
            MainWindow.MainVM.Page = "Pages/PublicPhotos.xaml";
            MainWindow.MainVM.SelectedIndex = -1;
        }

        private void Logout()
        {
            ApiRequest.JWT = null;
            MainWindow.MainVM.Page = "Pages/LogInPage.xaml";
            MainWindow.MainVM.SelectedIndex = -1;
            MainWindow.MainVM.Enabled = false;
            MainWindow.MainVM.User = "";
        }

        private void Profile()
        {
            MainWindow.MainVM.Page = "Pages/ProfilePage.xaml";
            MainWindow.MainVM.SelectedIndex = -1;
        }
    }
}