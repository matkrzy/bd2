﻿using MahApps.Metro.Controls.Dialogs;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using MahApps.Metro.Controls;
using System.Runtime.Remoting.Contexts;
using System.Collections.ObjectModel;
using BD_client.Domain;
using Newtonsoft.Json;
using System.IO;

namespace BD_client.ViewModels
{
    class SearchPageViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged = null;
        private IDialogCoordinator dialogCoordinator;
        private string _page;
        public ObservableCollection<Category> Categories { get; set; }
        public ObservableCollection<SearchFilter> SearchFilters { get; set; }
        public ICommand SearchCmd { get; set; }
        public ICommand CancelCmd { get; set; }
        public ICommand CategoryCmd { get; set; }
        public ICommand DescriptionCmd { get; set; }
        public ICommand TagsCmd { get; set; }
        public ICommand ExifCmd { get; set; }
        public ICommand RemoveFilterCmd { get; set; }
        private int _categorySelectedIndex;
        private int _dataGridSelectedIndex;
        private String _descriptionPhrase;
        private String _exifPhrase;
        private String _tagsPhrase;

        public SearchPageViewModel(IDialogCoordinator instance)
        {
            dialogCoordinator = instance;
            Categories = new ObservableCollection<Category>();
            SearchFilters = new ObservableCollection<SearchFilter>();
            SearchCmd = new RelayCommand(x => ShowResults());
            CancelCmd = new RelayCommand(x => Cancel());
            CategoryCmd = new RelayCommand(x => AddCategoryFilter());
            DescriptionCmd = new RelayCommand(x => AddDescriptionFilter());
            TagsCmd = new RelayCommand(x => AddTagsFilter());
            ExifCmd = new RelayCommand(x => AddExifFilter());
            RemoveFilterCmd = new RelayCommand(x => RemoveFilter());
            GetCategories();
            CategorySelectedIndex = 0;
        }

        private void GetCategories()
        {
            string url = MainWindow.MainVM.BaseUrl + "api/v1/categories";
            String responseContent = ApiRequest.Get(url);
            JsonTextReader reader = new JsonTextReader(new StringReader(responseContent));
            reader.SupportMultipleContent = true;
            List<Category> categoriesList = null;
            while (true)
            {
                if (!reader.Read())
                {
                    break;
                }

                JsonSerializer serializer = new JsonSerializer();
                categoriesList = serializer.Deserialize<List<Category>>(reader);

            }

            foreach (Category category in categoriesList)
            {
                Categories.Add(category);
            }


        }

        private void AddExifFilter()
        {
            SearchFilters.Add(new Domain.SearchFilter() { Type = "Exif", Filter = ExifPhrase });
        }

        private void RemoveFilter()
        {
            SearchFilters.RemoveAt(DataGridSelectedIndex);
        }

        private void AddTagsFilter()
        {
            SearchFilters.Add(new Domain.SearchFilter() { Type = "Tag", Filter = TagsPhrase });
        }

        private void AddDescriptionFilter()
        {
            SearchFilters.Add(new Domain.SearchFilter() { Type = "Description", Filter = DescriptionPhrase });
        }

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

        public string DescriptionPhrase
        {
            get
            {
                return _descriptionPhrase;
            }
            set
            {
                _descriptionPhrase = value;
                OnPropertyChanged("DescriptionPhrase");
            }
        }

        public string TagsPhrase
        {
            get
            {
                return _tagsPhrase;
            }
            set
            {
                _tagsPhrase = value;
                OnPropertyChanged("TagsPhrase");
            }
        }

        public string ExifPhrase
        {
            get
            {
                return _exifPhrase;
            }
            set
            {
                _exifPhrase = value;
                OnPropertyChanged("ExifPhrase");
            }
        }

        public int CategorySelectedIndex
        {
            get
            {
                return _categorySelectedIndex;
            }
            set
            {
                _categorySelectedIndex = value;
                OnPropertyChanged("CategorySelectedIndex");
            }
        }

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


        private async void ShowResults()
        {
            await dialogCoordinator.ShowMessageAsync(this, "Success", "Results");
        }

        private void Cancel()
        {
            MainWindow.MainVM.Page = "Pages/MyPhotosPage.xaml";
            MainWindow.MainVM.SelectedIndex = -1;
        }

        private void AddCategoryFilter()
        {
            SearchFilters.Add(new Domain.SearchFilter() { Type = "Category", Filter = Categories[CategorySelectedIndex].Name });
        }

        virtual protected void OnPropertyChanged(string propName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propName));
        }
    }
}
