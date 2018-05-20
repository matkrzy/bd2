using BD_client.ViewModels;
using MahApps.Metro.Controls.Dialogs;
using System.IO;
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

        public PublicPhotos()
        {
            InitializeComponent();
            var viewModel = new PublicPhotosPageViewModel(DialogCoordinator.Instance);
            DataContext = viewModel;
        }

    }
}
