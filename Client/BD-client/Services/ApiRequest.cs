using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.IO;
using System.IO.IsolatedStorage;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace BD_client.Domain
{
    public class ApiRequest
    {
        public static String JWT = null;

        public static void Post(String url, String value)
        {
            byte[] data = Encoding.ASCII.GetBytes(value);
            HttpWebRequest request = (HttpWebRequest)HttpWebRequest.Create(url);
            var cookieContainer = new CookieContainer();
            request.CookieContainer = cookieContainer;
            if (JWT != null)
            {
                Cookie cookie = new Cookie();
                cookie.Name = "JWT";
                cookie.Value = JWT;
                cookie.Domain = request.RequestUri.Host;
                request.CookieContainer.Add(cookie);
            }
            request.Method = "POST";
            request.ContentType = "application/json";
            request.ContentLength = data.Length;
            using (Stream stream = request.GetRequestStream())
            {
                stream.Write(data, 0, data.Length);
            }

            var response = (HttpWebResponse)request.GetResponse();
            if (response.StatusCode != HttpStatusCode.OK)
                throw new Exception();
            if (JWT == null)
            {
                var allCookies = new CookieCollection();
                var domainTableField = cookieContainer.GetType().GetRuntimeFields().FirstOrDefault(x => x.Name == "m_domainTable");
                var domains = (IDictionary)domainTableField.GetValue(cookieContainer);

                foreach (var val in domains.Values)
                {
                    var type = val.GetType().GetRuntimeFields().First(x => x.Name == "m_list");
                    var values = (IDictionary)type.GetValue(val);
                    foreach (CookieCollection cookies in values.Values)
                    {
                        allCookies.Add(cookies);
                    }
                }
                CookieCollection responseCookies = allCookies;

                if (responseCookies.Count > 0)
                {
                    JWT = responseCookies[0].Value;
                    String name = responseCookies[0].Name;
                }
            }
        }

        public static void PostImage(String url, Stream value)
        {
            HttpContent fileStreamContent = new StreamContent(value);
            var baseAddress = new Uri(url);
            var cookieContainer = new CookieContainer();
            using (var handler = new HttpClientHandler() { CookieContainer = cookieContainer })
            using (var formData = new MultipartFormDataContent())
            using (var client = new HttpClient(handler) { BaseAddress = baseAddress })
            {
                cookieContainer.Add(baseAddress, new Cookie("JWT", JWT));
                formData.Add(fileStreamContent,"file");
                var response = client.PostAsync(url, formData).Result; //404 BAD REQUEST
                if (!response.IsSuccessStatusCode)
                    throw new Exception();
                
            }
        }


        public static void Put(String url, String value)
        {

            var bytes = Encoding.ASCII.GetBytes(value);
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            var cookieContainer = new CookieContainer();
            request.CookieContainer = cookieContainer;
            if (JWT != null)
            {
                Cookie cookie = new Cookie();
                cookie.Name = "JWT";
                cookie.Value = JWT;
                cookie.Domain = request.RequestUri.Host;
                request.CookieContainer.Add(cookie);
            }
            request.Method = "PUT";
            request.ContentType = "application/json";
            using (var requestStream = request.GetRequestStream())
            {
                requestStream.Write(bytes, 0, bytes.Length);
            }

            var response = (HttpWebResponse)request.GetResponse();
            if (response.StatusCode != HttpStatusCode.OK)
                throw new Exception();
        }

        public static void PutInParams(String url)
        {

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            var cookieContainer = new CookieContainer();
            request.CookieContainer = cookieContainer;
            if (JWT != null)
            {
                Cookie cookie = new Cookie();
                cookie.Name = "JWT";
                cookie.Value = JWT;
                cookie.Domain = request.RequestUri.Host;
                request.CookieContainer.Add(cookie);
            }
            request.Method = "PUT";

            var response = (HttpWebResponse)request.GetResponse();
            if (response.StatusCode != HttpStatusCode.OK)
                throw new Exception();
        }



        public static String Get(String url)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            var cookieContainer = new CookieContainer();
            request.CookieContainer = cookieContainer;
            if (JWT != null)
            {
                Cookie cookie = new Cookie();
                cookie.Name = "JWT";
                cookie.Value = JWT;
                cookie.Domain = request.RequestUri.Host;
                request.CookieContainer.Add(cookie);
            }
            request.AutomaticDecompression = DecompressionMethods.GZip | DecompressionMethods.Deflate;
            string responseContent = null;

            using (WebResponse response = request.GetResponse())
            {
                using (Stream stream = response.GetResponseStream())
                {
                    using (StreamReader sr99 = new StreamReader(stream))
                    {
                        responseContent = sr99.ReadToEnd();
                    }
                }
            }
            return responseContent;
        }
    }

}
