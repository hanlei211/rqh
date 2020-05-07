package com.hl.lib.common.http.manager;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.hl.lib.common.http.RxHttp;
import com.hl.lib.common.http.interceptor.BaseUrlRedirectInterceptor;
import com.hl.lib.common.http.interceptor.CacheControlInterceptor;
import com.hl.lib.common.http.interceptor.PublicHeadersInterceptor;
import com.hl.lib.common.http.interceptor.PublicQueryParameterInterceptor;
import com.hl.lib.common.http.setting.SSLContextUtil;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    public static RetrofitManager retrofitManager;
    private Map<Class<?>, Retrofit> mRetrofitMap = null;
    private final Retrofit mRetrofit;

    public RetrofitManager() {
        this.mRetrofit = create();
    }


    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }


    /**
     * 创建实例接口
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> clazz) {
        return getInstance().getRetrofit(clazz).create(clazz);
    }

    public Retrofit getRetrofit(Class<?> clazz) {
        if (clazz == null) {
            return mRetrofit;
        }
        Retrofit retrofit = null;
        if (mRetrofitMap != null && mRetrofitMap.size() > 0) {
            Iterator<Map.Entry<Class<?>, Retrofit>> iterator = mRetrofitMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Class<?>, Retrofit> map = iterator.next();
                if (TextUtils.equals(map.getKey().getName(), clazz.getName())) {
                    retrofit = map.getValue();
                    if (retrofit != null) {
                        iterator.remove();
                    }
                    break;
                }
            }
        }
        if (retrofit != null) {
            return retrofit;
        }
        Map<Class<?>, String> baseUrlMap = RxHttp.getRequestSetting().getServiceBaseUrl();
        if (baseUrlMap == null || baseUrlMap.size() <= 0) {
            return mRetrofit;
        }

        String baseUrl = null;
        for (Map.Entry<Class<?>, String> entry : baseUrlMap.entrySet()) {
            if (TextUtils.equals(entry.getKey().getName(), clazz.getName())) {
                baseUrl = entry.getValue();
                break;
            }
        }
        if (baseUrl == null) {
            return mRetrofit;
        }
        retrofit = create(baseUrl);
        mRetrofitMap.put(clazz, retrofit);
        return retrofit;
    }

    private Retrofit create() {
        return create(RxHttp.getRequestSetting().getBaseUrl());
    }

    /**
     * 创建retrofit
     * @param baseUrl
     * @return
     */
    private Retrofit create(String baseUrl) {
       Retrofit.Builder retrofit = new Retrofit.Builder().client(createOkHttpClient())
               .baseUrl(baseUrl)
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
       Gson gson = RxHttp.getRequestSetting().getGson();
       if(gson == null){
           gson = new Gson();
       }
       retrofit.addConverterFactory(GsonConverterFactory.create(gson));
       return retrofit.build();
    }

    /**
     * 创建OkHttpClient实例
     *
     * @return OkHttpClient
     */
    @SuppressWarnings("deprecation")
    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        //添加调试打印log
        HttpLoggingInterceptor loggingInterceptor =  new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(loggingInterceptor);
        //设置缓存
        okHttpClient.cache(createCache());
        long timeout = RxHttp.getRequestSetting().getTimeout();
        long connectTimeout = RxHttp.getRequestSetting().getConnectTimeout();
        long readTimeout = RxHttp.getRequestSetting().getReadTimeout();
        long writeTimeout = RxHttp.getRequestSetting().getWriteTimeout();
        okHttpClient.connectTimeout(connectTimeout > 0 ? connectTimeout : timeout, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(readTimeout > 0 ? readTimeout : timeout, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(writeTimeout > 0 ? writeTimeout : timeout, TimeUnit.MILLISECONDS);
        // 设置应用层拦截器
        BaseUrlRedirectInterceptor.addTo(okHttpClient);
        PublicHeadersInterceptor.addTo(okHttpClient);
        PublicQueryParameterInterceptor.addTo(okHttpClient);
        CacheControlInterceptor.addTo(okHttpClient);
        SSLContext sslContext = SSLContextUtil.getDefaultSLLContext();
        if (sslContext != null) {
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            okHttpClient.sslSocketFactory(socketFactory);
        }
        okHttpClient.hostnameVerifier(SSLContextUtil.HOSTNAME_VERIFIER);
        Interceptor[] interceptors = RxHttp.getRequestSetting().getInterceptors();
        if (interceptors != null && interceptors.length > 0) {
            for (Interceptor interceptor : interceptors) {
                okHttpClient.addInterceptor(interceptor);
            }
        }
        RxHttp.getRequestSetting().setOkHttpClient(okHttpClient);
        return okHttpClient.build();
    }

    /**
     * 设置缓存
     * @return
     */
    private Cache createCache() {
        File cacheFile = new File(RxHttp.getRequestSetting().getCacheDirName());
        if(!cacheFile.exists()){
            cacheFile.mkdirs();
        }
        return new Cache(cacheFile,RxHttp.getRequestSetting().getCacheSize());
    }
}
