package com.hl.news.api;


import com.hl.lib.common.http.Api;

public class NewsApi extends Api {

   public static Service api(){
     return  Api.api(Service.class);
   }

}
