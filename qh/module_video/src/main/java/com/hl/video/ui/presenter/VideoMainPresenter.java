package com.hl.video.ui.presenter;

import com.example.lib_api.bean.ResponseBean;
import com.google.gson.Gson;
import com.hl.lib.common.http.callback.ResultCallback;
import com.hl.lib.common.http.exception.ExceptionHandle;
import com.hl.lib.common.http.listener.RequestListener;
import com.hl.lib.common.http.request.RxRequest;
import com.hl.lib.common.http.request.RxRequest1;
import com.hl.lib.common.util.LogUtils;
import com.hl.video.R;
import com.hl.video.api.VideoApi;
import com.hl.video.ui.bean.VideoTitleBean;
import com.hl.video.ui.config.VideoConstant;
import com.hl.video.ui.contract.VideoMainContract;

import java.util.Arrays;
import java.util.List;


public class VideoMainPresenter extends VideoMainContract.Presenter {

    @Override
    public void getVideoTitle() {
        RxRequest1.create(VideoApi.api().getVideoTitles(VideoConstant.VIDEO_TYPE_TV)).listener(new RequestListener() {
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
                mView.showVideoTitle(myChannelNameList);
                mView.initTabLayout();
            }

            @Override
            public void onFinish() {
                long cast = System.currentTimeMillis() - timeStart;
            }
        }).request(new ResultCallback<VideoTitleBean>() {

            @Override
            public void onSuccess(int code, VideoTitleBean data) {
                LogUtils.logd(data.toString());
                if (data != null) {
                    mView.showVideoTitle(data.tags);
                    mView.initTabLayout();
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                mView.hideInitLoadView();
            }
        });
    }
}
