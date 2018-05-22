using BD_client.Domain;
using MahApps.Metro.Controls.Dialogs;
using Newtonsoft.Json;
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
            Categories.Add(new Domain.Category() {Name = "Birthday" });
            Categories.Add(new Domain.Category() { Name = "Easter" });
            CancelCmd = new RelayCommand(x => Cancel());
            EditCmd = new RelayCommand(x => Edit());
            if (MainWindow.MainVM.List != null )
                getSelectedPhtotos();
            
        }

        private void getSelectedPhtotos()
        {

            for(int i =0; i<MainWindow.MainVM.List.Count;i++)
            {
                int index = MainWindow.MainVM.List[i];
                Photo newPhoto = MainWindow.MainVM.Photos[index];
                Photos.Add(newPhoto);
            }
            MainWindow.MainVM.List.Clear();
            MainWindow.MainVM.List = null;
        }

        private void Cancel()
        {
            MainWindow.MainVM.Page = "Pages/MyPhotosPage.xaml";
            MainWindow.MainVM.SelectedIndex = -1;
        }

        private void EditPhoto()
        {
            string url = MainWindow.MainVM.url + "api/v1/photos/" + Photos[SelectedIndex].Index;
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
            url = MainWindow.MainVM.url + "api/v1/tags";
            bool repeat = false;
            for (int i = 0; i < jsonTags.Length; i++)
            {
                for (int j = 0; j < Photos[SelectedIndex].Tags.Count; j++)
                {
                    if (jsonTags[i].Equals(Photos[SelectedIndex].Tags[j].Name))
                    {
                        repeat = true;
                        break;
                    }
                 }
                if (!repeat)
                {
                    var valuesTags = new Dictionary<string, string>
                {
                   { "name", jsonTags[i] },
                   { "photo", Photos[SelectedIndex].Index.ToString() }
                };
                    try
                    {
                        json = JsonConvert.SerializeObject(valuesTags, Formatting.Indented);
                        ApiRequest.Post(url, json);
                    }
                    catch (Exception)
                    {
                        throw new Exception();
                    }
                }
                repeat = false;
            }


            //Put category
            if (Categories[SelectedCategory].Name.Equals(Photos[SelectedIndex].Category.Name))
                repeat = true;

            if (!repeat)
            {
                var valuesCategory = new Dictionary<string, string>
            {
               { "idphoto", Photos[SelectedIndex].Index.ToString() },
               { "idcategory",Categories[SelectedCategory].Id.ToString() }
            };

                url = MainWindow.MainVM.url + "not in server yet";
                try
                {
                    json = JsonConvert.SerializeObject(valuesCategory, Formatting.Indented);
                    ApiRequest.Put(url, json);
                }
                catch (Exception)
                {
                    throw new Exception();
                }
            }
            repeat = false;
        }

        private async void Edit()
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
                _selectedIndex = value;
                OnPropertyChanged("SelectedIndex");
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


        virtual protected void OnPropertyChanged(string propName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propName));
        }
    }
}