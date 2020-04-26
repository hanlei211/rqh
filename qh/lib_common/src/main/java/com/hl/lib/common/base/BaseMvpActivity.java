package com.hl.lib.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hl.lib.common.baseapp.AppManager;
import com.hl.lib.common.baserx.RxManager;
import com.hl.lib.common.mvp.BaseModel;
import com.hl.lib.common.mvp.BasePresenter;

public abstract  class BaseMvpActivity<T extends BasePresenter, E extends BaseModel> extends BaseActivity {

    public T mPresenter;
    public E mModel;
    public Context mContext;
    public RxManager mRxManager;
    private boolean isConfigChange=false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        if(!isConfigChange){
            AppManager.getAppManager().finishActivity(this);
        }
    }

}
