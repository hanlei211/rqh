package com.hl.video.ui.contract;

import com.hl.lib.common.base.BaseModel;
import com.hl.lib.common.mvp.BasePresenter;
import com.hl.lib.common.mvp.BaseView;

import java.util.List;

public interface VideoMainContract {
    interface Model extends BaseModel {
    }

    interface View extends BaseView {
       void  showVideoTitle(List<String> videoType);

        void initTabLayout();
    }
    abstract static class Presenter extends BasePresenter<Model, View> {
        public abstract void getVideoTitle();
    }
}
