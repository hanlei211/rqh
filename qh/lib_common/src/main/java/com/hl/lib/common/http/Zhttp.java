package com.hl.lib.common.http;

import android.app.Application;
import android.content.Context;

import com.hl.lib.common.http.interceptor.InterceptorUtil;
import com.hl.lib.common.http.service.ApiService;
import com.hl.lib.common.util.Utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求入口
 */
public class Zhttp {
    public static final int DEFAULT_MILLISECONDS = 60000;             //默认的超时时间
    private static final int DEFAULT_RETRY_COUNT = 3;                 //默认重试次数
    private static final int DEFAULT_RETRY_INCREASEDELAY = 0;         //默认重试叠加时间
    private static final int DEFAULT_RETRY_DELAY = 500;               //默认重试延时
    public static final int DEFAULT_CACHE_NEVER_EXPIRE = -1;          //缓存过期时间，默认永久缓存
    private String mBaseUrl;                                          //全局BaseUrl
    private int mRetryCount = DEFAULT_RETRY_COUNT;                    //重试次数默认3次
    private int mRetryDelay = DEFAULT_RETRY_DELAY;                    //延迟xxms重试
    private int mRetryIncreaseDelay = DEFAULT_RETRY_INCREASEDELAY;    //叠加延迟

    private static Application sContext;
    private OkHttpClient okHttpClient;                 //okhttp请求的客户端
    private Retrofit retrofit;                               //Retrofit请求Builder

    private volatile static Zhttp singleton = null;

    private Zhttp() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .addInterceptor(InterceptorUtil.logInterceptor())
                .addNetworkInterceptor(InterceptorUtil.headerInterceptor())
                .build();


         retrofit=new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create()).             //设置gson转换器,将返回的json数据转为实体
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).       //设置CallAdapter
                baseUrl(ApiService.HOST).
                client(okHttpClient)                                                  //设置客户端okhttp相关参数
                .build();

    }

    public static Zhttp getInstance() {
        testInitialize();
        if (singleton == null) {
            synchronized (Zhttp.class) {
                if (singleton == null) {
                    singleton = new Zhttp();
                }
            }
        }
        return singleton;
    }

    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     */
    public static void init(Application app) {
        sContext = app;
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        testInitialize();
        return sContext;
    }

    private static void testInitialize() {
        if (sContext == null)
            throw new ExceptionInInitializerError("请先在全局Application中调用 Zhttp.init() 初始化！");
    }

    /**
     * 全局设置baseurl
     */
    public Zhttp setBaseUrl(String baseUrl) {
        mBaseUrl = Utils.checkNotNull(baseUrl, "baseUrl == null");
        return this;
    }

    /**
     * 获取全局baseurl
     */
    public static String getBaseUrl() {
        return getInstance().mBaseUrl;
    }

    /**
     * 取消订阅
     */
    public static void cancelSubscription(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public int getRetryCount() {
        return DEFAULT_RETRY_COUNT;
    }

    public int getRetryDelay() {
        return DEFAULT_RETRY_DELAY;
    }

    public int getRetryIncreaseDelay() {
        return DEFAULT_RETRY_INCREASEDELAY;
    }
}
