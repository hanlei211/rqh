package com.hl.qh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_api.config.ApiConfig;
import com.hl.lib.common.BaseApplication;
import com.hl.lib.common.http.RxHttp;
import com.hl.lib.common.http.setting.DefaultRequestSetting;
import com.hl.lib.common.util.LogUtils;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Description: <MyApplication><br>
 * Update:     <br>
 */
public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //NewsDBManager.getInstance(this).initNewsDB();
//        RetrofitManager.init(this);
        //路由初始化
        initARouter();
        //初始化日志框架
        initLogger();
        //rxhttp 初始化
        RxHttp.init(this);
        RxHttp.initRequest(new DefaultRequestSetting() {
            @NonNull
            @Override
            public String getBaseUrl() {
                return ApiConfig.BASE_URL;
            }

            @Override
            public Map<String, String> getRedirectBaseUrl() {
                Map<String, String> urls = new HashMap<>(2);
                urls.put(ApiConfig.BASE_URL_HTTPS_NAME, ApiConfig.BASE_URL_HTTPS);
                return urls;
            }

            @Override
            public int getSuccessCode() {
                return ApiConfig.SUCCESS_CODE;
            }


            @Override
            public Map<String, String> getStaticPublicQueryParameter() {
                Map<String, String> parameters = new HashMap<>(2);
                parameters.put("system", "android");
                parameters.put("version_code", "1");
                return parameters;
            }

            @Override
            public void setOkHttpClient(OkHttpClient.Builder builder) {
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }
        });
    }
    /**
     * 初始化日志
     */
    private void initLogger() {
        LogUtils.logInit(BuildConfig.DEBUG);

    }
    /**
     * 初始化ARouter
     */
    private void initARouter() {
        if (BuildConfig.DEBUG) { //如果在debug模式下
            // 打印日志,默认关闭
            ARouter.openLog();
            // 开启调试模式，默认关闭(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
    }
}
