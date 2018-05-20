using BD_client.ViewModels;
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
    /// Interaction logic for CategoriesPage.xaml
    /// </summary>
    public partial class CategoriesPage : Page
    {
        public CategoriesPage()
        {
            InitializeComponent();
            DataContext = new CategoriesPageViewModel();
        }
        private void OnPhotoDbClick(object sender, MouseButtonEventArgs e)
        {

        }

        private void OnEditPhoto(object sender, RoutedEventArgs e)
        {

        }
        private void OnDownloadPhoto(object sender, RoutedEventArgs e)
        {

        }
        private void OnRemovePhoto(object sender, RoutedEventArgs e)
        {

        }
    }
}
