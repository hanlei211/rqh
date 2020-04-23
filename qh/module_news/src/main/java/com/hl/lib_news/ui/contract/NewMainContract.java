package com.hl.lib_news.ui.contract;

import com.hl.lib.common.base.BaseModel;
import com.hl.lib.common.mvp.BasePresenter;
import com.hl.lib.common.mvp.BaseView;
import com.hl.lib_news.ui.bean.NewsChannelTable;

import java.util.List;
import java.util.Observable;

public interface NewMainContract {
    interface Model extends BaseModel {
        Observable< List<NewsChannelTable> > lodeMineNewsChannels();
    }

    interface View extends BaseView {
         void initTabLayout();
         void showNewsListTable(List<NewsChannelTable> list);

    }
    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getListNewsType();
    }
}
