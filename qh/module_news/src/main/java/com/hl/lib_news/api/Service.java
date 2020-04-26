package com.hl.lib_news.api;

import com.example.lib_api.config.ApiConfig;
import com.hl.lib.common.http.Api;
import com.hl.lib_news.ui.bean.WeatherBean;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Service {

    /**
     *
     */
    @Headers({Api.Header.BASE_URL_REDIRECT + ":" + ApiConfig.BASE_URL_OTHER_NAME})
    @GET("weatherApi?")
    Observable<Response<WeatherBean>> weather(@Query("city") String city);
}
