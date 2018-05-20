using BD_client.Domain;
using MahApps.Metro.Controls.Dialogs;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace BD_client.ViewModels
{
    class ProfilePageViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged = null;

        private IDialogCoordinator dialogCoordinator;
        public ICommand EditCmd { get; set; }
        private String _page;
        private String _password;
        private String _email;
        private String _name;
        private String _surname;
        private String _role;
        private long id;
        private String oldName;
        private String oldSurname;
        private String oldPassword;

        public string Role
        {
            get
            {
                return _role;
            }
            set
            {
                _role = value;
                OnPropertyChanged("Role");
            }
        }

        public string Surname
        {
            get
            {
                return _surname;
            }
            set
            {
                _surname = value;
                OnPropertyChanged("Surname");
            }
        }
        public string Name
        {
            get
            {
                return _name;
            }
            set
            {
                _name = value;
                OnPropertyChanged("Name");
            }
        }
        public string Password
        {
            get
            {
                return _password;
            }
            set
            {
                _password = value;
                OnPropertyChanged("Password");
            }
        }

        public string Email
        {
            get
            {
                return _email;
            }
            set
            {
                _email = value;
                OnPropertyChanged("Email");
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


        public ProfilePageViewModel(IDialogCoordinator instance)
        {
            dialogCoordinator = instance;
            EditCmd = new RelayCommand(x => Edit());
            try
            {
                GetUserInfo();
                oldName = Name;
                oldSurname = Surname;
                oldPassword = Password;
            }
            catch (Exception)
            {
                ShowServerError();
            }
            
        }

        private async void ShowServerError()
        {
            await dialogCoordinator.ShowMessageAsync(this, "Error", "Server error");
        }

        private void GetUserInfo()
        {
            string url = MainWindow.MainVM.url + "api/v1/users";
            String responseContent = ApiRequest.Get(url);
            JsonTextReader reader = new JsonTextReader(new StringReader(responseContent));
            reader.SupportMultipleContent = true;

            while (true)
            {
                if (!reader.Read())
                {
                    break;
                }

                JsonSerializer serializer = new JsonSerializer();
                var usersList = serializer.Deserialize < List<User>>(reader);
                for (int i = 0; i < usersList.Count; i++)
                {
                    if (usersList[i].Email.Equals(MainWindow.MainVM.User))
                    {
                        id = usersList[i].id;
                        Name = usersList[i].FirstName;
                        Surname = usersList[i].LastName;
                        Email = usersList[i].Email;
                        Password = usersList[i].Password;
                        Role = usersList[i].Role.ToString();
                        break;
                        
                    }
                }

            }


        }

        private void EditUser()
        {
            var values = new Dictionary<string, string>
            {
                { "firstName", Name },
                { "lastName", Surname },
                { "password", Password },
            };

            string json = JsonConvert.SerializeObject(values, Formatting.Indented);

            String url = MainWindow.MainVM.url + "api/v1/users";
            ApiRequest.Put(url, json);
        }


        private async void Edit()
        {
            try
            {
                EditUser();
                await dialogCoordinator.ShowMessageAsync(this, "Success", "Profile was edited");
                MainWindow.MainVM.Page = "Pages/HomePage.xaml";
                MainWindow.MainVM.SelectedIndex = -1;
            }
            catch (Exception)
            {
                await dialogCoordinator.ShowMessageAsync(this, "Error", "Edit failed");
                Password = oldPassword;
                Name = oldName;
                Surname = oldSurname;

            }
        }

        virtual protected void OnPropertyChanged(string propName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propName));
        }
    }
}
