package com.hl.lib_news.ui.presenter;

import com.hl.lib.common.baseapp.AppConfig;
import com.hl.lib.common.http.RxLife;
import com.hl.lib.common.http.callback.ResultCallback;
import com.hl.lib.common.http.exception.ExceptionHandle;
import com.hl.lib.common.http.listener.RequestListener;
import com.hl.lib.common.http.request.RxRequest;
import com.hl.lib.common.util.LogUtils;
import com.hl.lib_news.api.NewsApi;
import com.hl.lib_news.ui.bean.NewsDetail;
import com.hl.lib_news.ui.contract.NewsListContract;
import com.hl.lib_news.ui.model.NewsListResult;

import java.util.List;

public class NewsListPresenter extends NewsListContract.Presenter {

    @Override
    public void getNewsListData(String newsType) {
        RxRequest.create(NewsApi.api().getNewsListData(AppConfig.APP_KEY)).listener(new RequestListener() {
            private long timeStart = 0;
            @Override
            public void onStart() {
                timeStart = System.currentTimeMillis();
            }

            @Override
            public void onError(ExceptionHandle exception) {
                LogUtils.logd("exception"+exception.getMsg());
                mView.showNoDataView();
            }

            @Override
            public void onFinish() {
                long cast = System.currentTimeMillis() - timeStart;
                LogUtils.logd(cast+"");
            }
        }).request(new ResultCallback<NewsListResult>() {
            @Override
            public void onSuccess(int code, NewsListResult data) {
                mView.showNewsListData(data.list);
            }

            @Override
            public void onFailed(int code, String msg) {
            }
        });
    }

    public void refreshData(){

    }
}
