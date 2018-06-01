using BD_client.Data.Utils;
using BD_client.Domain;
using MahApps.Metro.Controls.Dialogs;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace BD_client.ViewModels
{
    class EditPhotoPageViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged = null;
        public ObservableCollection<Category> Categories { get; set; }
        public ICommand CancelCmd { get; set; }
        public ICommand EditCmd { get; set; }
        private IDialogCoordinator dialogCoordinator;
        public ObservableCollection<Photo> Photos { get; set; }
        private int _selectedIndex;
        private string _page;
        private string _description;
        private string _tags;
        private int _selectedCategory;

        public EditPhotoPageViewModel(IDialogCoordinator instance)
        {
            dialogCoordinator = instance;
            SelectedCategory = 0;
            Photos = new ObservableCollection<Photo>();
            Categories = new ObservableCollection<Category>();
            CancelCmd = new RelayCommand(x => Cancel());
            EditCmd = new RelayCommand(x => Edit());
            if (MainWindow.MainVM.List != null)
                GetSelectedPhtotos();

        }

        private void GetSelectedPhtotos()
        {

            for (int i = 0; i < MainWindow.MainVM.List.Count; i++)
            {
                int index = MainWindow.MainVM.List[i];
                Photo newPhoto = MainWindow.MainVM.Photos[index];
                Photos.Add(newPhoto);
            }
            MainWindow.MainVM.List.Clear();
            MainWindow.MainVM.List = null;

            ShowPhotosDetails();
        }

        private void ShowPhotosDetails()
        {
            Tags = "";
            if (Photos[SelectedIndex].Tags != null)
            {
                for (int i = 0; i < Photos[SelectedIndex].Tags.Count; i++)
                {
                    Tags += Photos[SelectedIndex].Tags[i].Name;
                    if(i+1 != Photos[SelectedIndex].Tags.Count)
                        Tags += " ";
                }
            }

            Description = "";
            if (Photos[SelectedIndex].Description != null)
            {
                Description = Photos[SelectedIndex].Description;
            }
        }

        private void Cancel()
        {
            MainWindow.MainVM.Page = "Pages/MyPhotosPage.xaml";
            MainWindow.MainVM.SelectedIndex = -1;
        }

        private void EditPhoto()    
        {
            string url = MainWindow.MainVM.BaseUrl + "api/v1/photos/" + Photos[SelectedIndex].Id;
            string[] jsonTags = Tags.Split(' ');
            string json;

            //Put photos
            var valuesPhoto = new Dictionary<string, string>
            {
                { "description", Description }
            };

            try
            {
                json = JsonConvert.SerializeObject(valuesPhoto, Formatting.Indented);
                ApiRequest.Put(url, json);
            }
            catch (Exception)
            {
                throw new Exception();
            }

            //Post tags
            url = MainWindow.MainVM.BaseUrl + "api/v1/tags";
            bool repeat = false;
            List<TagDTO> tagList = new List<TagDTO>();
            for (int i = 0; i < jsonTags.Length; i++)
            {
                if (Photos[SelectedIndex].Tags.Count != 0)
                {
                    for (int j = 0; j < Photos[SelectedIndex].Tags.Count; j++)
                    {
                        if (jsonTags[i].Equals(Photos[SelectedIndex].Tags[j].Name))
                        {
                            repeat = true;
                            break;
                        }
                    }
                }
                if (!repeat)
                {
                    TagDTO newTag = new TagDTO();
                    newTag.name = jsonTags[i];
                    newTag.photo = Photos[SelectedIndex].Id;
                    tagList.Add(newTag);
                }
    
                repeat = false;
            }
            try
            {
                if (tagList.Count != 0)
                {
                    json = JsonConvert.SerializeObject(tagList, Formatting.Indented);
                    ApiRequest.Post(url, json);
                }
            }
            catch (Exception)
            {
                throw new Exception();
            }
        }

        public async void Edit()
        {

            try
            {
                EditPhoto();
                await dialogCoordinator.ShowMessageAsync(this, "Success", "Photo was edited");
                MainWindow.MainVM.Page = "Pages/MyPhotosPage.xaml";
                MainWindow.MainVM.SelectedIndex = -1;
            }
            catch (Exception)
            {
                await dialogCoordinator.ShowMessageAsync(this, "Error", "Editing failed");
            }

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

        public string Description
        {
            get
            {
                return _description;
            }
            set
            {
                _description = value;
                OnPropertyChanged("Description");
            }
        }

        public string Tags
        {
            get
            {
                return _tags;
            }
            set
            {
                _tags = value;
                OnPropertyChanged("Tags");
            }
        }


        public int SelectedIndex
        {
            get
            {
                return _selectedIndex;
            }
            set
            {
                if (SetField(ref _selectedIndex, value, "SelectedIndex"))
                    ShowPhotosDetails();
            }
        }

        public int SelectedCategory
        {
            get
            {
                return _selectedCategory;
            }
            set
            {
                _selectedCategory = value;
                OnPropertyChanged("SelectedCategory");
            }
        }

        protected bool SetField<T>(ref T field, T value, string propertyName)
        {
            if (EqualityComparer<T>.Default.Equals(field, value)) return false;
            field = value;
            OnPropertyChanged(propertyName);
            return true;
        }

        virtual protected void OnPropertyChanged(string propName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propName));
        }
    }
}