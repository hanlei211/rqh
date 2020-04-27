package com.hl.lib_news.api;

import com.hl.lib.common.http.Api;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Service {

    /**
     *
     */
    @Headers({Api.Header.CACHE_ALIVE_SECOND})
    @GET("news/channel?")
    Observable<Response<String>> getNewsChannelType(@Query("appkey") String appkey);
}
