using BD_client.Common;
using BD_client.Data.Photos;
using BD_client.Domain;
using BD_client.Services;
using BD_client.ViewModels.Category;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace BD_client.ViewModels
{
    public class CategoriesPageViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;
        public PhotoCollection Photos { get; set; }
        public NotifyTaskCompletion<List<CategoryViewModel>> RootCategories { get; set; }

        public CategoriesPageViewModel()
        {
            var path = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + "//Img//photos";
            Photos = new PhotoCollection(path);
            RootCategories = new NotifyTaskCompletion<List<CategoryViewModel>>(GetUsersRootCategoryViewModels());

        }

        private async Task<List<CategoryViewModel>> GetUsersRootCategoryViewModels()
        {
            try
            {
                var categories = await CategoryService.GetUsersRootCategories();
                return categories.Select(x => new CategoryViewModel(x)).ToList();

            }
            catch (Exception e)
            {
                throw;
            }            
        }

    }
}
