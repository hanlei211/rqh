package com.hl.lib_news.ui.presenter;

import com.hl.lib.common.baseapp.AppConfig;
import com.hl.lib.common.http.callback.ResultCallback;
import com.hl.lib.common.http.exception.ExceptionHandle;
import com.hl.lib.common.http.listener.RequestListener;
import com.hl.lib.common.http.request.RxRequest;
import com.hl.lib.common.util.LogUtils;
import com.hl.lib_news.R;
import com.hl.lib_news.api.NewsApi;
import com.hl.lib_news.ui.contract.NewMainContract;

import java.util.Arrays;
import java.util.List;

public class NewsMainPresenter extends NewMainContract.Presenter {

    @Override
    public void getListNewsType() {
        RxRequest.create(NewsApi.api().getNewsChannelType(AppConfig.APP_KEY)).listener(new RequestListener() {
            private long timeStart = 0;
            @Override
            public void onStart() {
                timeStart = System.currentTimeMillis();
            }

            @Override
            public void onError(ExceptionHandle exception) {
                LogUtils.logd("onError(" + exception.getMsg() + ")");
//                mView.showNoDataView();
                mView.hideInitLoadView();
                List<String> myChannelNameList = Arrays.asList(mContext.getResources().getStringArray(R.array.news_channel_name));
                mView.showChannelType(myChannelNameList);
                mView.initTabLayout();
            }

            @Override
            public void onFinish() {
                long cast = System.currentTimeMillis() - timeStart;
            }
        }).request(new ResultCallback<List<String>>() {
            @Override
            public void onSuccess(int code, List<String> data) {
                mView.showChannelType(data);
                mView.initTabLayout();
            }

            @Override
            public void onFailed(int code, String msg) {
                mView.hideInitLoadView();
            }
        });
    }
}
