package com.hl.video.api;

import com.example.lib_api.bean.ResponseBean;
import com.example.lib_api.config.ApiConfig;
import com.hl.video.ui.bean.VideoBean;
import com.hl.video.ui.bean.VideoListBean;
import com.hl.video.ui.bean.VideoTitleBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {


    /**
     * https://movie.douban.com/j/search_tags?type=tv&tag=   --电视剧类型
     * https://movie.douban.com/j/search_subjects?type=tv&tag= -- 电视剧类型列表
     *
     *
     *
     */
    /**
     *   获取电视标题
     */
    @GET(ApiConfig.BASE_URL_HTTPS+"/j/search_tags?")
    Observable<Response<VideoTitleBean>> getVideoTitles(@Query("type") String type);
    /**
     *  获取电视列表
     */
    @GET(ApiConfig.BASE_URL_HTTPS+"/j/search_subjects?")
    Observable<Response<VideoListBean>> getTvList(@Query("type") String type, @Query("tag")String tag);


}
