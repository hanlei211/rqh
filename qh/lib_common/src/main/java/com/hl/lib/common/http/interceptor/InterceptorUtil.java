package com.hl.lib.common.http.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class InterceptorUtil {
    public static final String TAG="okhttp";
    //日志拦截器
    public static HttpLoggingInterceptor logInterceptor(){
          return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
              @Override
              public void log(String message) {
                  Log.w(TAG,"log:"+message);
              }
          }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    //头部拦截器
    public static Interceptor headerInterceptor(){
        return  new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request().newBuilder().
                        addHeader("Content-Type","application/json;charSet=UTF-8").build();
                return chain.proceed(request);
            }
        };
    }
}
