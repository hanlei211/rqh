package com.hl.lib.common.http;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.hl.lib.common.util.Utils;

import java.net.Proxy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 网络请求入口
 * 
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
    private OkHttpClient.Builder okHttpClientBuilder;                 //okhttp请求的客户端
    private Retrofit.Builder retrofit;                               //Retrofit请求Builder
//    private RxCache.Builder rxCacheBuilder;                           //RxCache请求的Builder

    private volatile static Zhttp singleton = null;

    private Zhttp(){
    okHttpClientBuilder = new OkHttpClient.Builder();
    okHttpClientBuilder.connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS);
    okHttpClientBuilder.readTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS);
    okHttpClientBuilder.writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        retrofit = new Retrofit.Builder();
        retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public static Zhttp getInstance(){
        testInitialize();
        if(singleton == null){
            synchronized (Zhttp.class){
                if(singleton == null){
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

    public static OkHttpClient getOkHttpClient() {
        return getInstance().okHttpClientBuilder.build();
    }
    public static Retrofit getRetrofit() {
        return getInstance().retrofit.build();
    }

    /**
     * 调试模式,默认打开所有的异常调试
     */
    public Zhttp debug(String tag) {
        debug(tag, true);
        return this;
    }

    /**
     * 调试模式,第二个参数表示所有catch住的log是否需要打印<br>
     * 一般来说,这些异常是由于不标准的数据格式,或者特殊需要主动产生的,
     * 并不是框架错误,如果不想每次打印,这里可以关闭异常显示
     */
    public Zhttp debug(String tag, boolean isPrintException) {
        String tempTag = TextUtils.isEmpty(tag)?"RxHttp_":tag;
        if(isPrintException){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(loggingInterceptor);
        }
//        HttpLog.customTagPrefix = tempTag;
//        HttpLog.allowE = isPrintException;
//        HttpLog.allowD = isPrintException;
//        HttpLog.allowI = isPrintException;
//        HttpLog.allowV = isPrintException;
        return this;
    }

    /**
     * 全局读取超时时间
     */
    public Zhttp setReadTimeOut(long readTimeOut) {
        okHttpClientBuilder.readTimeout(readTimeOut, TimeUnit.MILLISECONDS);
        return this;
    }

    /**
     * 全局写入超时时间
     */
    public Zhttp setWriteTimeOut(long writeTimeout) {
        okHttpClientBuilder.writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);
        return this;
    }

    /**
     * 全局连接超时时间
     */
    public Zhttp setConnectTimeout(long connectTimeout) {
        okHttpClientBuilder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
        return this;
    }

    /**
     * 超时重试次数
     */
    public Zhttp setRetryCount(int retryCount) {
        if (retryCount < 0) throw new IllegalArgumentException("retryCount must > 0");
        mRetryCount = retryCount;
        return this;
    }

    /**
     * 超时重试次数
     */
    public static int getRetryCount() {
        return getInstance().mRetryCount;
    }

    /**
     * 超时重试延迟时间
     */
    public Zhttp setRetryDelay(int retryDelay) {
        if (retryDelay < 0) throw new IllegalArgumentException("retryDelay must > 0");
        mRetryDelay = retryDelay;
        return this;
    }

    /**
     * 超时重试延迟时间
     */
    public static int getRetryDelay() {
        return getInstance().mRetryDelay;
    }

    /**
     * 超时重试延迟叠加时间
     */
    public Zhttp setRetryIncreaseDelay(int retryIncreaseDelay) {
        if (retryIncreaseDelay < 0)
            throw new IllegalArgumentException("retryIncreaseDelay must > 0");
        mRetryIncreaseDelay = retryIncreaseDelay;
        return this;
    }

    /**
     * 超时重试延迟叠加时间
     */
    public static int getRetryIncreaseDelay() {
        return getInstance().mRetryIncreaseDelay;
    }


    /**
     * 添加全局拦截器
     */
    public Zhttp addInterceptor(Interceptor interceptor) {
        okHttpClientBuilder.addInterceptor(Utils.checkNotNull(interceptor, "interceptor == null"));
        return this;
    }

    /**
     * 添加全局网络拦截器
     */
    public Zhttp addNetworkInterceptor(Interceptor interceptor) {
        okHttpClientBuilder.addNetworkInterceptor(Utils.checkNotNull(interceptor, "interceptor == null"));
        return this;
    }

    /**
     * 全局设置代理
     */
    public Zhttp setOkproxy(Proxy proxy) {
        okHttpClientBuilder.proxy(Utils.checkNotNull(proxy, "proxy == null"));
        return this;
    }

    /**
     * 全局设置请求的连接池
     */
    public Zhttp setOkconnectionPool(ConnectionPool connectionPool) {
        okHttpClientBuilder.connectionPool(Utils.checkNotNull(connectionPool, "connectionPool == null"));
        return this;
    }

    /**
     * 全局为Retrofit设置自定义的OkHttpClient
     */
    public Zhttp setOkclient(OkHttpClient client) {
        retrofit.client(Utils.checkNotNull(client, "client == null"));
        return this;
    }

    /**
     * 全局设置Converter.Factory,默认GsonConverterFactory.create()
     */
    public Zhttp addConverterFactory(Converter.Factory factory) {
        retrofit.addConverterFactory(Utils.checkNotNull(factory, "factory == null"));
        return this;
    }

    /**
     * 全局设置CallAdapter.Factory,默认RxJavaCallAdapterFactory.create()
     */
    public Zhttp addCallAdapterFactory(CallAdapter.Factory factory) {
        retrofit.addCallAdapterFactory(Utils.checkNotNull(factory, "factory == null"));
        return this;
    }

    /**
     * 全局设置Retrofit callbackExecutor
     */
    public Zhttp setCallbackExecutor(Executor executor) {
        retrofit.callbackExecutor(Utils.checkNotNull(executor, "executor == null"));
        return this;
    }

    /**
     * 全局设置Retrofit对象Factory
     */
    public Zhttp setCallFactory(okhttp3.Call.Factory factory) {
        retrofit.callFactory(Utils.checkNotNull(factory, "factory == null"));
        return this;
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

}
