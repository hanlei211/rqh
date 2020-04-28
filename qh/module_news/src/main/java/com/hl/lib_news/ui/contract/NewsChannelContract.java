package com.hl.lib_news.ui.contract;

import com.hl.lib.common.base.BaseModel;
import com.hl.lib.common.mvp.BasePresenter;
import com.hl.lib.common.mvp.BaseView;

import java.util.List;

public interface NewsChannelContract {

      interface  Model extends BaseModel{

      }

    interface View extends BaseView {
        void showChannelData(List<String> channelDatas);
    }
    abstract static class Presenter extends BasePresenter<Model, View> {

    }
}
