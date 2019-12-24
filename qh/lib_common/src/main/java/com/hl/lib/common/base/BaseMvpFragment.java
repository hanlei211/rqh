package com.hl.lib.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hl.lib.common.Interface.IView;
import com.hl.lib.common.mvp.BaseModel;
import com.hl.lib.common.mvp.BasePresenter;

import javax.inject.Inject;

public abstract class BaseMvpFragment<M extends BaseModel,V extends IView,P extends BasePresenter<M,V>> extends  BaseFragment{
    @Inject
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    public void initPresenter() {
        mPresenter = createPresenter();
        if(mPresenter != null){
            mPresenter.attachView((V) mActivity);
            mPresenter.injectLifecycle(mActivity);
        }
    }


    /**
     * 创建Presenter
     *
     * @return Presenter
     */
    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        if(mPresenter != null){
            mPresenter.detachView();
            mPresenter= null;
        }
        super.onDestroy();
    }
}
