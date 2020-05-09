package com.hl.video.ui.contract;

import com.hl.lib.common.base.BaseModel;
import com.hl.lib.common.mvp.BaseRefreshPresenter;
import com.hl.lib.common.mvp.BaseRefreshView;
import com.hl.video.ui.bean.VideoBean;

import java.util.List;

public interface VideoListContract {
    interface Model extends BaseModel {
    }

    interface View extends BaseRefreshView {
       void  showVideoList(List<VideoBean> videoBeans);

       void scrollToTop();
    }
    abstract static class Presenter extends BaseRefreshPresenter<Model, View> {
        public abstract void getVideoList(String videoType);
    }
}
