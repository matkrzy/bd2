using BD_client.ViewModels;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Threading;

namespace BD_client.Pages
{
    /// <summary>
    /// Interaction logic for HomePage.xaml
    /// </summary>
    public partial class HomePage : Page
    {
        DispatcherTimer Timer;
        public HomePageViewModel ViewModel { get; set; }
        public int Counter { get; set; }       
        public const int MaxNumberOfItems = 8;
        public string BasePictureName;

        private readonly TimeSpan AnimationTime;
        private readonly DoubleAnimation FadeInAnimation;
        private readonly DoubleAnimation FadeOutAnimation;
        public HomePage()
        {
            InitializeComponent();
            ViewModel = new HomePageViewModel();
            DataContext = ViewModel;
            Counter = 1;
            BasePictureName = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + "//Img//photos//photo{0}.jpg";

            AnimationTime = new TimeSpan(0, 0, 1);
            FadeInAnimation = new DoubleAnimation(1d, AnimationTime);
            FadeOutAnimation = new DoubleAnimation(0d, AnimationTime);

            TimerTick(null, null);            

            Timer = new DispatcherTimer();
            Timer.Interval = new TimeSpan(0, 0, 8);
            Timer.Tick += new EventHandler(TimerTick);
            Timer.Start();
        }
        void TimerTick(object sender, EventArgs e)
        {


            Counter++;
            if(Counter > MaxNumberOfItems)
            {
                Counter = 1;
            }
            var source = new BitmapImage();
            source.BeginInit();
            source.UriSource = new Uri(string.Format(BasePictureName, Counter));
            source.EndInit();

            FadeOutAnimation.Completed += (ob, ea) =>
            {
                SlideShow.Source = source;
                SlideShow.BeginAnimation(OpacityProperty, FadeInAnimation);
            };
            SlideShow.BeginAnimation(OpacityProperty, FadeOutAnimation);

        }

    }
}
