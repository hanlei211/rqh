package com.hl.video.api;

import com.hl.lib.common.http.Api;

public class VideoApi extends Api {

   public static Service api(){
     return  Api.api(Service.class);
   }

}
