﻿using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BD_client.Domain
{
    public class Category
    {
        [JsonProperty("categoryID")]
        public long Id { get; set; }
        [JsonProperty("name")]
        public String Name { get; set;}
        [JsonProperty("user")]
        public User User { get; set; }
        [JsonProperty("parentCategory")]
        public String ParentCategory { get; set; }

        public Category()
        {

        }
    }
}