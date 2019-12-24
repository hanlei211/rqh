package com.hl.lib.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hl.lib.common.mvp.BasePresenter;

public abstract  class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    public void initPresenter() {
        mPresenter = createPresenter();
        if(mPresenter != null){
            mPresenter.attachView(this);
            mPresenter.injectLifecycle(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null ){
            mPresenter.detachView();
            mPresenter= null;
        }
    }

    /**
     * 创建Presenter
     *
     * @return Presenter
     */
    protected abstract P createPresenter();



}
