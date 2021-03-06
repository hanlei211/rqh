package com.hl.news.ui.presenter;

import com.hl.lib.common.baseapp.AppConfig;
import com.hl.lib.common.http.callback.ResultCallback;
import com.hl.lib.common.http.exception.ExceptionHandle;
import com.hl.lib.common.http.listener.RequestListener;
import com.hl.lib.common.http.request.RxRequest;
import com.hl.lib.common.util.LogUtils;
import com.hl.lib.common.util.NetUtil;
import com.hl.news.api.NewsApi;
import com.hl.news.ui.contract.NewsListContract;
import com.hl.news.ui.model.NewsListResult;

public class NewsListPresenter extends NewsListContract.Presenter {

    public String newsType;

    public void setNewsType(String newsType){
        this.newsType = newsType;
    }
    @Override
    public void getNewsListData(String newsType) {
        if (!NetUtil.checkNetToast()) {
            mView.showNetErrView();
            return;
        }
        RxRequest.create(NewsApi.api().getNewsListData(AppConfig.APP_KEY,newsType)).listener(new RequestListener() {
            private long timeStart = 0;
            @Override
            public void onStart() {
                timeStart = System.currentTimeMillis();
            }

            @Override
            public void onError(ExceptionHandle exception) {
                LogUtils.logd("exception"+exception.getMsg());
                mView.stopRefresh();
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
                if (data.list != null && data.list.size() > 0) {
                    mView.showNewsListData(data.list);
                } else {
                    mView.showNoDataView();
                }
                mView.stopRefresh();
            }

            @Override
            public void onFailed(int code, String msg) {
                mView.stopRefresh();
            }
        });
    }

    public void refreshData(){
        if (!NetUtil.checkNetToast()) {
            mView.showNetErrView();
            return;
        }
        RxRequest.create(NewsApi.api().getNewsListData(AppConfig.APP_KEY,newsType)).listener(new RequestListener() {
            private long timeStart = 0;
            @Override
            public void onStart() {
                timeStart = System.currentTimeMillis();
            }

            @Override
            public void onError(ExceptionHandle exception) {
                LogUtils.logd("exception"+exception.getMsg());
                mView.stopRefresh();
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
                if (data.list != null && data.list.size() > 0) {
                    mView.refreshData(data.list);
                } else {
                    mView.showNoDataView();
                }
                mView.stopRefresh();
            }

            @Override
            public void onFailed(int code, String msg) {
                mView.stopRefresh();
            }
        });
    }

    public void loadMoreData() {
        if (!NetUtil.checkNetToast()) {
            mView.showNetErrView();
            return;
        }
        RxRequest.create(NewsApi.api().getNewsListData(AppConfig.APP_KEY,newsType)).listener(new RequestListener() {
            private long timeStart = 0;
            @Override
            public void onStart() {
                timeStart = System.currentTimeMillis();
            }

            @Override
            public void onError(ExceptionHandle exception) {
                LogUtils.logd("exception"+exception.getMsg());
                mView.stopLoadMore();
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
                if (data.list != null && data.list.size() > 0) {
                    mView.loadMoreData(data.list);
                } else {
                    mView.showNoDataView();
                }
                mView.stopLoadMore();
            }

            @Override
            public void onFailed(int code, String msg) {
                mView.stopLoadMore();
            }
        });
    }
}
