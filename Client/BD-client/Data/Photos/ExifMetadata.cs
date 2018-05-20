using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.Globalization;
using System.Text;
using System.Windows.Media.Imaging;

namespace BD_client.Data.Photos
{
    /// <summary>
    /// Exif metadata
    /// </summary>
    public class ExifMetadata
    {
        //TODO: add more properties
        public ExifMetadata(Uri uri)
        {
            BitmapFrame frame = BitmapFrame.Create(uri);
            Width = frame.Width;
            Height = frame.Height;
            getDate(uri);

        }
        public double Width { get; set; }
        public double Height { get; set; }
        public DateTime Date { get; set; }
        private void getDate(Uri uri)
        {
            DateTime dateTime;
            try
            {
                Bitmap image = new Bitmap(uri.LocalPath);
                PropertyItem[] propItems = image.PropertyItems;
                ASCIIEncoding encodings = new ASCIIEncoding();
                String date = encodings.GetString(propItems[15].Value);
                date = date.Replace("\0", string.Empty);
                if (!DateTime.TryParseExact(date, "yyyy:MM:dd HH:mm:ss",
                    CultureInfo.CurrentCulture, DateTimeStyles.None, out dateTime))
                {
                    dateTime = DateTime.Now;
                }
            }
            catch (Exception)
            {
                dateTime = DateTime.Now;
            }
            Date = dateTime;

        }
    }
}
