package com.hl.lib_news.ui.presenter;

import com.hl.lib.common.http.RxLife;
import com.hl.lib.common.http.callback.ResultCallback;
import com.hl.lib.common.http.exception.ExceptionHandle;
import com.hl.lib.common.http.listener.RequestListener;
import com.hl.lib.common.http.request.RxRequest;
import com.hl.lib.common.util.LogUtils;
import com.hl.lib_news.api.NewsApi;
import com.hl.lib_news.ui.bean.WeatherBean;
import com.hl.lib_news.ui.contract.NewMainContract;

import java.util.List;

public class NewsMainPresenter extends NewMainContract.Presenter {


    public void lodeMineChannelsRequest(RxLife mRxLife) {
        mRxLife.add(RxRequest.create(NewsApi.api().weather("天津")).listener(new RequestListener() {
            private long timeStart = 0;
            @Override
            public void onStart() {
                timeStart = System.currentTimeMillis();
                LogUtils.logd(timeStart+"");

            }

            @Override
            public void onError(ExceptionHandle exception) {
                mView.showNoDataView();
            }

            @Override
            public void onFinish() {
                long cast = System.currentTimeMillis() - timeStart;
                LogUtils.logd(cast+"");
            }
        }).request(new ResultCallback<List<WeatherBean>>() {
            @Override
            public void onSuccess(int code, List<WeatherBean> data) {
                LogUtils.logd("code:"+code);
                LogUtils.logd("data:"+data);
                mView.returnNewsChannelData(data);
            }

            @Override
            public void onFailed(int code, String msg) {
                LogUtils.logd("code:"+code);
                LogUtils.logd("data:"+msg);
            }
        }));
    }

    @Override
    public void getListNewsType() {

    }

}
