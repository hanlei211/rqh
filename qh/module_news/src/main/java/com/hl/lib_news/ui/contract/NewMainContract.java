package com.hl.lib_news.ui.contract;

import android.provider.ContactsContract;

import com.hl.lib.common.base.BaseModel;
import com.hl.lib.common.mvp.BasePresenter;
import com.hl.lib.common.mvp.BaseView;
import com.hl.lib_news.ui.bean.WeatherBean;

import java.util.List;

public interface NewMainContract {
    interface Model extends BaseModel {
    }

    interface View extends BaseView {
       void  returnNewsChannelData(List<WeatherBean> weatherBeans);
    }
    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getListNewsType();
    }
}
