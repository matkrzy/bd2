﻿using BD_client.ViewModels;
using MahApps.Metro.Controls.Dialogs;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace BD_client.Pages
{
    /// <summary>
    /// Interaction logic for SearchPage.xaml
    /// </summary>
    public partial class SearchPage : Page
    {
        SearchPageViewModel vm = new SearchPageViewModel(DialogCoordinator.Instance);

        public SearchPage()
        {
            InitializeComponent();
            DataContext = vm;
        }

        private void OnPhotoDbClick(object sender, MouseButtonEventArgs e)
        {
        }

        private void OnEditPhoto(object sender, RoutedEventArgs e)
        {
            List<int> list = new List<int>();

            foreach (var item in this.MyPhotosListBox.SelectedItems)
            {
                list.Add(this.MyPhotosListBox.Items.IndexOf(item));// Add selected indexes to the List<int>
            }
            MainWindow.MainVM.List = list;
            MainWindow.MainVM.SelectedIndex = 1;
            MainWindow.MainVM.Page = "Pages/EditPhotoPage.xaml";
        }
        private void OnDownloadPhoto(object sender, RoutedEventArgs e)
        {
            List<int> list = new List<int>();

            foreach (var item in this.MyPhotosListBox.SelectedItems)
            {
                list.Add(this.MyPhotosListBox.Items.IndexOf(item));// Add selected indexes to the List<int>
            }
            MainWindow.MainVM.List = list;
            MainWindow.MainVM.SelectedIndex = 3;
            MainWindow.MainVM.Page = "Pages/DownloadPage.xaml";
        }
        private void OnRemovePhoto(object sender, RoutedEventArgs e)
        {
            List<int> list = new List<int>();

            foreach (var item in this.MyPhotosListBox.SelectedItems)
            {
                list.Add(this.MyPhotosListBox.Items.IndexOf(item));// Add selected indexes to the List<int>
            }
            MainWindow.MainVM.List = list;
            MainWindow.MainVM.SelectedIndex = 4;
            MainWindow.MainVM.Page = "Pages/RemovePhotoPage.xaml";
        }

        private void OnSharePhoto(object sender, RoutedEventArgs e)
        {
            List<int> list = new List<int>();

            foreach (var item in this.MyPhotosListBox.SelectedItems)
            {
                list.Add(this.MyPhotosListBox.Items.IndexOf(item));// Add selected indexes to the List<int>
            }
            MainWindow.MainVM.List = list;
            MainWindow.MainVM.SelectedIndex = 5;
            MainWindow.MainVM.Page = "Pages/SharePage.xaml";
        }
    }
}
