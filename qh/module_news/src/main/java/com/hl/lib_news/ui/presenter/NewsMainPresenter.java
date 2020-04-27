package com.hl.lib_news.ui.presenter;

import com.hl.lib.common.http.RxLife;
import com.hl.lib.common.http.exception.ExceptionHandle;
import com.hl.lib.common.http.request.RxRequest;
import com.hl.lib_news.api.NewsApi;
import com.hl.lib_news.ui.bean.WeatherBean;
import com.hl.lib_news.ui.contract.NewMainContract;

import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;

public class NewsMainPresenter extends NewMainContract.Presenter {


    public void lodeMineChannelsRequest(RxLife mRxLife) {
        mRxLife.add(RxRequest.create(NewsApi.api().weather("天津")).listener(new RxRequest.RequestListener() {
            private long timeStart = 0;
            @Override
            public void onStart() {
                timeStart = System.currentTimeMillis();
                HttpLoggingInterceptor.Logger.DEFAULT.log("timeStart");

            }

            @Override
            public void onError(ExceptionHandle exception) {
                mView.showNoDataView();
            }

            @Override
            public void onFinish() {
                long cast = System.currentTimeMillis() - timeStart;

            }
        }).request(new RxRequest.ResultCallback<List<WeatherBean>>() {
            @Override
            public void onSuccess(int code, List<WeatherBean> data) {
                HttpLoggingInterceptor.Logger.DEFAULT.log(code+"");
                HttpLoggingInterceptor.Logger.DEFAULT.log(data+"");
                mView.returnNewsChannelData(data);
            }

            @Override
            public void onFailed(int code, String msg) {
                mView.showNetErrView();
            }
        }));
    }

    @Override
    public void getListNewsType() {

    }

}
