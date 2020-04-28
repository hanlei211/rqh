package com.hl.lib_news.ui.contract;

import com.hl.lib.common.base.BaseModel;
import com.hl.lib.common.http.RxLife;
import com.hl.lib.common.mvp.BasePresenter;
import com.hl.lib.common.mvp.BaseView;

import java.util.List;

public interface NewMainContract {
    interface Model extends BaseModel {
    }

    interface View extends BaseView {
       void  showChannelType(List<String> chanelType);

        void initTabLayout();
    }
    abstract static class Presenter extends BasePresenter<Model, View> {
        public abstract void getListNewsType();
    }
}
