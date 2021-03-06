package com.hl.news.api;

import com.example.lib_api.bean.ResponseBean;
import com.hl.news.ui.model.NewsListResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    /**
     *  获取频道标题列表
     */
    @GET("news/channel?")
    Observable<ResponseBean<List<String>>> getNewsChannelType(@Query("appkey") String appkey);
    /**
     * 获取新闻列表
     */
    @GET("news/get?")
    Observable<ResponseBean<NewsListResult>> getNewsListData(@Query("appkey") String appkey,@Query("channel")String channel);
}
