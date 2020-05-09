package com.hl.video.ui.presenter;
import com.hl.lib.common.http.callback.ResultCallback;
import com.hl.lib.common.http.exception.ExceptionHandle;
import com.hl.lib.common.http.listener.RequestListener;
import com.hl.lib.common.http.request.RxRequest;
import com.hl.lib.common.util.LogUtils;
import com.hl.lib.common.util.NetUtil;
import com.hl.video.api.VideoApi;
import com.hl.video.ui.bean.VideoBean;
import com.hl.video.ui.config.VideoConstant;
import com.hl.video.ui.contract.VideoListContract;

public class VideoListPresenter extends VideoListContract.Presenter {

    public String videoType;

    public void setVideoType(String videoType){
        this.videoType = videoType;
    }

    public void refreshData(){
        if (!NetUtil.checkNetToast()) {
            mView.showNetErrView();
            return;
        }
        RxRequest.create(VideoApi.api().getTvList(VideoConstant.VIDEO_TYPE_TV,videoType)).listener(new RequestListener() {
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
        }).request(new ResultCallback<VideoBean>() {
            @Override
            public void onSuccess(int code, VideoBean data) {
//                if (data.list != null && data.list.size() > 0) {
//                    mView.refreshData(data.list);
//                } else {
//                    mView.showNoDataView();
//                }
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
        RxRequest.create(VideoApi.api().getTvList(VideoConstant.VIDEO_TYPE_TV,videoType)).listener(new RequestListener() {
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
        }).request(new ResultCallback<VideoBean>() {
            @Override
            public void onSuccess(int code, VideoBean data) {
//                if (data.list != null && data.list.size() > 0) {
//                    mView.loadMoreData(data.list);
//                } else {
//                    mView.showNoDataView();
//                }
                mView.stopLoadMore();
            }

            @Override
            public void onFailed(int code, String msg) {
                mView.stopLoadMore();
            }
        });
    }

    @Override
    public void getVideoList(String videoType) {
        if (!NetUtil.checkNetToast()) {
            mView.showNetErrView();
            return;
        }
        RxRequest.create(VideoApi.api().getTvList(VideoConstant.VIDEO_TYPE_TV,videoType)).listener(new RequestListener() {
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
        }).request(new ResultCallback<VideoBean>() {
            @Override
            public void onSuccess(int code, VideoBean data) {
//                if (data.list != null && data.list.size() > 0) {
//                    mView.showNewsListData(data.list);
//                } else {
//                    mView.showNoDataView();
//                }
                mView.stopRefresh();
            }

            @Override
            public void onFailed(int code, String msg) {
                mView.stopRefresh();
            }
        });
    }
}
