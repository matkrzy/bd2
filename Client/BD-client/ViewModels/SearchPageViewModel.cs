using MahApps.Metro.Controls.Dialogs;
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
using BD_client.Services;

namespace BD_client.ViewModels
{
    class SearchPageViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged = null;
        private IDialogCoordinator dialogCoordinator;
        private string _page;
        public ObservableCollection<Domain.Category> Categories { get; set; }
        public ObservableCollection<SearchFilter> SearchFilters { get; set; }
        public ObservableCollection<Photo> PhotosResult { get; set; }
        public List<Photo> Photos { get; set; }
        public ICommand SearchCmd { get; set; }
        public ICommand CancelCmd { get; set; }
        public ICommand CategoryCmd { get; set; }
        public ICommand DescriptionCmd { get; set; }
        public ICommand TagsCmd { get; set; }
        public ICommand ExifCmd { get; set; }
        public ICommand RemovePhotoCmd { get; set; }
        public ICommand RemoveFilterCmd { get; set; }
        public List<Photo> photosToDisplay { get; set; }
        private int _categorySelectedIndex;
        private int _dataGridPhotoSelectedIndex;
        private int _dataGridSelectedIndex;
        private String _descriptionPhrase;
        private String _exifPhrase;
        private String _tagsPhrase;

        public SearchPageViewModel(IDialogCoordinator instance)
        {
            dialogCoordinator = instance;
            Categories = new ObservableCollection<Domain.Category>();
            Photos = MainWindow.MainVM.Photos;
            PhotosResult = new ObservableCollection<Photo>();
            SearchFilters = new ObservableCollection<SearchFilter>();
            SearchCmd = new RelayCommand(x => ShowResults());
            CancelCmd = new RelayCommand(x => Cancel());
            CategoryCmd = new RelayCommand(x => AddCategoryFilter());
            DescriptionCmd = new RelayCommand(x => AddDescriptionFilter());
            TagsCmd = new RelayCommand(x => AddTagsFilter());
            ExifCmd = new RelayCommand(x => AddExifFilter());
            RemoveFilterCmd = new RelayCommand(x => RemoveFilter());
            RemovePhotoCmd = new RelayCommand(x => RemovePhoto());
            GetCategories();
            CategorySelectedIndex = 0;
        }

        private void RemovePhoto()
        {
            Photos.RemoveAt(DataGridPhotoSelectedIndex);
        }

        private void GetCategories()
        {
            string url = MainWindow.MainVM.BaseUrl + "api/v1/categories";
            String responseContent = ApiRequest.Get(url);
            JsonTextReader reader = new JsonTextReader(new StringReader(responseContent));
            reader.SupportMultipleContent = true;
            List<Domain.Category> categoriesList = null;
            while (true)
            {
                if (!reader.Read())
                {
                    break;
                }

                JsonSerializer serializer = new JsonSerializer();
                categoriesList = serializer.Deserialize<List<Domain.Category>>(reader);

            }
            bool repeated = false;
            foreach (var category in categoriesList)
            {
                foreach (var displayCategory in Categories)
                {
                    if (displayCategory.Name.ToLower().Equals(category.Name.ToLower()))
                    {
                        repeated = true;
                        break;
                    }
                }
                if(!repeated)
                    Categories.Add(category);

                repeated = false;
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

        public int DataGridPhotoSelectedIndex
        {
            get
            {
                return _dataGridPhotoSelectedIndex;
            }
            set
            {
                _dataGridPhotoSelectedIndex = value;
                OnPropertyChanged("DataGridPhotoSelectedIndex");
            }
        }

        private async void GetPhotosByCategories(List<int> SelectedCategoriesIds)
        {
            photosToDisplay = await PhotoService.GetUsersPhotosByCategoriesIds(false, SelectedCategoriesIds.ToArray());
        }

        private List<int> SearchCategories(List<int> SelectedCategoriesIds)
        {
            List<int> photoIndex = new List<int>();
            GetPhotosByCategories(SelectedCategoriesIds);
            for (int i = 0; i < Photos.Count; i++)
            {
                for (int j = 0; j < photosToDisplay.Count; j++)
                {
                    if (Photos[i].Id == photosToDisplay[j].Id)
                    {
                        photoIndex.Add(i);
                        break;
                    }
                }
            }
            return photoIndex;
        }

        private List<int> SearchTags(string searchPhrase)
        {
            List<int> photoIndex = new List<int>();
            for(int i = 0; i < Photos.Count; i++)
            {
                for (int j = 0; j < Photos[i].Tags.Count; j++)
                {
                    if (Photos[i].Tags[j].Name.ToLower().Contains(searchPhrase.ToLower()))
                    {
                        photoIndex.Add(i);
                    }
                }
            }
            return photoIndex;
        }

        private List<int> SearchDescription(string searchPhrase)
        {
            List<int> photoIndex = new List<int>();
            for(int i = 0; i<Photos.Count;i++)
            {
                if (Photos[i].Description.ToLower().Contains(searchPhrase.ToLower()))
                    photoIndex.Add(i);
            }
            return photoIndex;
        }

        //private List<int> SearchExif()
        //{

        //}
        private List<int> GetCategoriesFilters()
        {
            List<int> searchCategories = new List<int>();
            foreach (var searchFilter in SearchFilters)
            {
                if (searchFilter.Type == "Category")
                {
                    foreach (var category in Categories)
                    {
                        if (category.Name == searchFilter.Filter)
                        {
                            searchCategories.Add(unchecked((int)category.Id));
                        }
                    }
                }
            }
            return searchCategories;
        }

        private List<string> GetExifFilters()
        {
            List<string> searchExif = new List<string>();
            foreach (var searchFilter in SearchFilters)
            {
                if (searchFilter.Type == "Exif")
                {
                    searchExif.Add(searchFilter.Filter);
                }
            }
            return searchExif;
        }

        private List<string> GetDescriptionFilters()
        {
            List<string> searchDescription = new List<string>();
            foreach (var searchFilter in SearchFilters)
            {
                if (searchFilter.Type == "Description")
                {
                    searchDescription.Add(searchFilter.Filter);
                }
            }
            return searchDescription;
        }

        private List<string> GetTagFilters()
        {
            List<string> searchTag = new List<string>();
            foreach (var searchFilter in SearchFilters)
            {
                if (searchFilter.Type == "Tag")
                {
                    searchTag.Add(searchFilter.Filter);
                }
            }

            return searchTag;
        }


        private List<int> GetAllPhotoIndexCategories()
        {
            List<int> searchCategories = GetCategoriesFilters();
            List<int> photoIndexCategories = SearchCategories(searchCategories);
            return photoIndexCategories;
        }

        private List<int> GetAllPhotoIndexTags()
        {
            List<int> resultPhotoIndex = new List<int>();
            List<string> searchTag = GetTagFilters();
            foreach(var tag in searchTag)
            {
                List<int> photoIndex = SearchTags(tag);
                bool repeated = false;
                foreach (var index in photoIndex)
                {
                    foreach(var resultIndex in resultPhotoIndex)
                    {
                        if(resultIndex == index)
                        {
                            repeated = true;
                            break;
                        }
                    }

                    if (!repeated)
                        resultPhotoIndex.Add(index);
                    repeated = false;
                }
            }

            return resultPhotoIndex;
        }

        private List<int> GetAllPhotoIndexDescription()
        {
            List<int> resultPhotoIndex = new List<int>();
            List<string> searchDescription = GetDescriptionFilters();
            foreach (var description in searchDescription)
            {
                List<int> photoIndex = SearchDescription(description);
                bool repeated = false;
                foreach (var index in photoIndex)
                {
                    foreach (var resultIndex in resultPhotoIndex)
                    {
                        if (resultIndex == index)
                        {
                            repeated = true;
                            break;
                        }
                    }

                    if (!repeated)
                        resultPhotoIndex.Add(index);
                    repeated = false;
                }
            }

            return resultPhotoIndex;
        }


        private List<int> GetAllPhotoIndexExif()
        {
            List<int> resultPhotoIndex = new List<int>();
            List<string> searchExif = GetExifFilters();
            return resultPhotoIndex;
        }



        private async void ShowResults()
        {
            List<int> photoIndexes = commonPart();

            await dialogCoordinator.ShowMessageAsync(this, "Success", "Results");
        }

        private List<int> MakeTmpResult(List<int> results)
        {
            List<int> tmpResult = new List<int>();
            foreach( int result in results)
            {
                tmpResult.Add(result);
            }
            return tmpResult;
        }

        private List<int> commonPart()
        {
            List<int> allPhotoIndexCategories = GetAllPhotoIndexCategories();
            List<int> allPhotoIndexTags = GetAllPhotoIndexTags();
            List<int> allPhotoIndexDescription = GetAllPhotoIndexDescription();
            //List<int> allPhotoIndexExif = GetAllPhotoIndexExif();
            List<int> result = new List<int>();
            List<int> tmpResult;


            foreach (var categoryIndex in allPhotoIndexCategories)
            {
                if (allPhotoIndexTags.Contains(categoryIndex))
                    result.Add(categoryIndex);
            }

            tmpResult = MakeTmpResult(result);
            result.Clear();

            foreach( var tmp in tmpResult)
            {
                if (allPhotoIndexDescription.Contains(tmp))
                    result.Add(tmp);
            }

            //tmpResult = MakeTmpResult(result);
            //result.Clear();
            //foreach (var tmp in tmpResult)
            //{
            //    foreach (var exifIndex in allPhotoIndexExif)
            //    {
            //        if (tmp == exifIndex)
            //            result.Add(tmp);
            //    }
            //}

            return result;
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
