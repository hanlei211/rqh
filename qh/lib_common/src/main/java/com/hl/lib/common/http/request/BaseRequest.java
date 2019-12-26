package com.hl.lib.common.http.request;

import android.content.Context;
import android.text.TextUtils;

import com.hl.lib.common.http.Zhttp;
import com.hl.lib.common.util.Utils;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;

public class BaseRequest<T extends BaseRequest> {
    protected String baseUrl;                                              //BaseUrl
    protected String url;                                                  //请求url
    protected long readTimeOut;                                            //读超时
    protected long writeTimeOut;                                           //写超时
    protected long connectTimeout;                                         //链接超时
    protected int retryCount;                                              //重试次数默认3次
    protected int retryDelay;                                              //延迟xxms重试
    protected int retryIncreaseDelay;                                      //叠加延迟
    protected boolean isSyncRequest;                                       //是否是同步请求
    protected Context context;
    protected HttpUrl httpUrl;
    protected Proxy proxy;
    protected List<Converter.Factory> converterFactories = new ArrayList<>();
    protected List<CallAdapter.Factory> adapterFactories = new ArrayList<>();
    protected final List<Interceptor> interceptors = new ArrayList<>();
    protected final List<Interceptor> networkInterceptors = new ArrayList<>();

    public BaseRequest(String url){
        this.url = url;
        context = Zhttp.getContext();
        Zhttp config = Zhttp.getInstance();
        this.baseUrl = config.getBaseUrl();
        if (!TextUtils.isEmpty(this.baseUrl)) {
            httpUrl = HttpUrl.parse(baseUrl);
        }
        if (baseUrl == null && url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
            httpUrl = HttpUrl.parse(url);
            baseUrl = httpUrl.url().getProtocol() + "://" + httpUrl.url().getHost() + "/";
        }
        retryCount = config.getRetryCount();                              //超时重试次数
        retryDelay = config.getRetryDelay();                              //超时重试延时
        retryIncreaseDelay = config.getRetryIncreaseDelay();              //超时重试叠加延时
    }

    public T readTimeOut(long readTimeOut) {
        this.readTimeOut = readTimeOut;
        return (T) this;
    }

    public T writeTimeOut(long writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
        return (T) this;
    }

    public T connectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
        return (T) this;
    }

    public T baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        if (!TextUtils.isEmpty(this.baseUrl))
            httpUrl = HttpUrl.parse(baseUrl);
        return (T) this;
    }

    public T retryCount(int retryCount) {
        if (retryCount < 0) throw new IllegalArgumentException("retryCount must > 0");
        this.retryCount = retryCount;
        return (T) this;
    }

    public T retryDelay(int retryDelay) {
        if (retryDelay < 0) throw new IllegalArgumentException("retryDelay must > 0");
        this.retryDelay = retryDelay;
        return (T) this;
    }

    public T retryIncreaseDelay(int retryIncreaseDelay) {
        if (retryIncreaseDelay < 0)
            throw new IllegalArgumentException("retryIncreaseDelay must > 0");
        this.retryIncreaseDelay = retryIncreaseDelay;
        return (T) this;
    }

    public T addInterceptor(Interceptor interceptor) {
        interceptors.add(Utils.checkNotNull(interceptor, "interceptor == null"));
        return (T) this;
    }

    public T addNetworkInterceptor(Interceptor interceptor) {
        networkInterceptors.add(Utils.checkNotNull(interceptor, "interceptor == null"));
        return (T) this;
    }

    /**
     * 设置Converter.Factory,默认GsonConverterFactory.create()
     */
    public T addConverterFactory(Converter.Factory factory) {
        converterFactories.add(factory);
        return (T) this;
    }

    /**
     * 设置CallAdapter.Factory,默认RxJavaCallAdapterFactory.create()
     */
    public T addCallAdapterFactory(CallAdapter.Factory factory) {
        adapterFactories.add(factory);
        return (T) this;
    }

    /**
     * 设置代理
     */
    public T okproxy(Proxy proxy) {
        this.proxy = proxy;
        return (T) this;
    }
}
