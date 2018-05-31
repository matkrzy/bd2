using BD_client.Services;
using BD_client.ViewModels;
using BD_client.ViewModels.Category;
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
            Categories.SelectedItemChanged += async (s, e) => GetCategoryChildren(s, e);
            var viewModel = new CategoriesPageViewModel();
            DataContext = viewModel;
        }

        /// <summary>
        /// (Handler dla eventu click na kategorii)
        /// Pobiera podkategorie danej kategorii i dołącza je do drzewa
        /// </summary>
        private async Task GetCategoryChildren(object sender, RoutedPropertyChangedEventArgs<object> e)
        {
            var selectedNode = e.NewValue as CategoryViewModel;
            var children = await CategoryService.GetCategoryChildren(selectedNode.Id);
            var childrenViewModels = children.Select(x => new CategoryViewModel(x));
            //
            selectedNode.Children.Clear();
            selectedNode.Children.AddRange(childrenViewModels);
        }

        //private Display

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
