package com.hl.lib.common.http;

import android.content.Context;
import androidx.annotation.NonNull;

import com.hl.lib.common.http.exception.NullRequestSettingException;
import com.hl.lib.common.http.exception.RxHttpUninitializedException;
import com.hl.lib.common.http.reponse.BaseReponse;
import com.hl.lib.common.http.request.RxRequest;
import com.hl.lib.common.http.setting.RequestSetting;

import io.reactivex.Observable;


public class RxHttp {

    private static RxHttp instance = null;
    private final Context mAppContext;
    private RequestSetting mRequestSetting = null;

    public static RxHttp  getInstance(){
        if(instance == null) {
           throw  new RxHttpUninitializedException();
        }
        return instance;
    }

    public static void init(@NonNull Context context){
        instance = new RxHttp(context.getApplicationContext());
    }

    public RxHttp(Context context){
        mAppContext = context;
    }

    @NonNull
    public static Context getAppContext(){
        return getInstance().mAppContext;
    }

    public  static void initRequest(RequestSetting setting){
        getInstance().mRequestSetting = setting;
    }

    public static RequestSetting  getRequestSetting(){
        RequestSetting setting = getInstance().mRequestSetting;
        if(setting == null){
           throw new NullRequestSettingException();
        }
        return setting;
    }

    public static <T,R extends BaseReponse<T>> RxRequest<T,R> request(@NonNull Observable<R> observable){
         return RxRequest.create(observable);
    }
}
