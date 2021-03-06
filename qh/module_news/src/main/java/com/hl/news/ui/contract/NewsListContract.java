package com.hl.news.ui.contract;

import com.hl.lib.common.base.BaseModel;
import com.hl.lib.common.mvp.BaseRefreshPresenter;
import com.hl.lib.common.mvp.BaseRefreshView;
import com.hl.news.ui.bean.NewsDetail;

import java.util.List;

public interface NewsListContract {
    interface Model extends BaseModel {
    }

    interface View extends BaseRefreshView {
       void  showNewsListData(List<NewsDetail> newsDetails);

       void scrollToTop();
    }
    abstract static class Presenter extends BaseRefreshPresenter<Model, View> {
        public abstract void getNewsListData(String newsType);
    }
}
