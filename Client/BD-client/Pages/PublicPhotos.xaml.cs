using BD_client.Common;
using BD_client.Domain;
using BD_client.Domain.Enums;
using BD_client.Services;
using BD_client.ViewModels;
using MahApps.Metro.Controls.Dialogs;
using System.Configuration;
using System.IO;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace BD_client.Pages
{
    /// <summary>
    /// Interaction logic for PublicPhotos.xaml
    /// </summary>
    public partial class PublicPhotos : Page
    {
        private int CurrentPage;
        private PublicPhotoType CurrentTab;
        private readonly int PhotosPerPage;
        public int PhotosCount { get; set; }
        public PublicPhotosPageViewModel ViewModel;


        public PublicPhotos()
        {
            InitializeComponent();

            TabControl.SelectionChanged += async (s, e) => OnTabSelectionChanged(s, e);

            CurrentPage = 0;
            CurrentTab = PublicPhotoType.Hot;
            PhotosPerPage = int.Parse(ConfigurationManager.AppSettings["PhotosPerPage"]);
            ViewModel = new PublicPhotosPageViewModel(DialogCoordinator.Instance);
            ViewModel.Photos = new NotifyTaskCompletion<PhotoCollection>(GetPhotos());
            DataContext = ViewModel;
        }

        private async Task<PhotoCollection> GetPhotos()
        {
            var destination = Directory.GetCurrentDirectory() + @"\..\..\tmp\public";
            var photos = await PhotoService.GetPublicPhotos(CurrentTab, CurrentPage, PhotosPerPage);
            //TODO: różne typy zdjęć, nie tylko jpg
            foreach (var photo in photos)
            {
                var completePath = $@"{destination}\{photo.Id}.jpg";
                if (!File.Exists(completePath))
                {
                    // jeżeli zdjęcie nie jest jeszcze pobrane
                    if (!(await ImageService.DownloadImageToLocation(completePath, photo.Id)))
                    {
                        //TODO: wyświetlić komunikat informujący o błędzie
                    }
                }
            }
            return new PhotoCollection(destination, photos);
        }

        //TODO: photos synchronized odtąd
        private async Task OnTabSelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            CurrentTab = (PublicPhotoType)TabControl.SelectedIndex;
            //ViewModel.Photos = new NotifyTaskCompletion<PhotoCollection>(GetAllUserPhotos());
            var photos = await PhotoService.GetAllUserPhotos();
            ViewModel.PhotosSynchronized.Photos.Clear();
            if (photos != null)
            {
                ViewModel.PhotosSynchronized.Photos.AddRange(photos);
            }
            ViewModel.PhotosSynchronized.Update();
        }

        //TODO:
        //tymczasowo
        private async Task<PhotoCollection> GetAllUserPhotos()
        {
            var destination = Directory.GetCurrentDirectory() + @"\..\..\tmp\public";
            var photos = await PhotoService.GetAllUserPhotos();
            //TODO: różne typy zdjęć, nie tylko jpg
            foreach (var photo in photos)
            {
                var completePath = $@"{destination}\{photo.Id}.jpg";
                if (!File.Exists(completePath))
                {
                    // jeżeli zdjęcie nie jest jeszcze pobrane
                    if (!(await ImageService.DownloadImageToLocation(completePath, photo.Id)))
                    {
                        //TODO: wyświetlić komunikat informujący o błędzie
                    }
                }
            }
            return new PhotoCollection(destination, photos);
        }
    }
}
