package com.hl.lib.common.http;

import android.annotation.SuppressLint;
import android.app.Service;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.hl.lib.common.http.manager.RetrofitManager;

public class Api {


    /**
     * 创建一个接口实例
     *
     * @param clazz Retrofit的ServiceInterface，建议定义为子类的内部接口
     * @param <T>   ServiceInterface的名字
     * @return 接口实例
     */
    protected static <T> Service api(Class<?> clazz) {
        return RetrofitManager.getService(clazz);
    }

    public interface  Header{

        /**
         *添加以这个为名的Header可以让这个Request使用另一个BaseUrl
         */
        String BASE_URL_REDIRECT ="RxHttp-BaseUrl-Redirect";

        /**
         * 添加以这个为名的Header可以让这个Request支持缓存（有网联网获取，无网读取缓存）
         * 如//@Headers({Header.CACHE_ALIVE_SECOND + ":" + 10})
         */
        String CACHE_ALIVE_SECOND = "RxHttp-Cache-Alive-Second";

    }
}
