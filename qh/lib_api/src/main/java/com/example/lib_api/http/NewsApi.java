package com.example.lib_api.http;

import android.app.Service;

import com.hl.lib.common.http.Api;

public class NewsApi  extends Api {

   public static Service  api(){
     return Api.api(Service.class);
   }

}
