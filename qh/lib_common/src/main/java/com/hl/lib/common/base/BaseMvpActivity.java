package com.hl.lib.common.base;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.hl.lib.common.baseapp.AppManager;
import com.hl.lib.common.baserx.RxManager;
import com.hl.lib.common.http.RxLife;
import com.hl.lib.common.mvp.BasePresenter;

@SuppressWarnings("FieldCanBeLocal")
public abstract  class BaseMvpActivity<T extends BasePresenter, E extends BaseModel> extends BaseActivity {

    public T mPresenter;
    public E mModel;
    public Context mContext;
    private boolean isConfigChange=false;
    public RxManager mRxManager;
    public RxLife mRxLife;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager=new RxManager();
        mRxLife = RxLife.create();
        initPresenter();
        onRxBusEvent();
    }

    protected abstract void onRxBusEvent();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRxManager != null)
            mRxManager.clear();
        if(mRxLife != null){
            mRxLife.destroy();
        }
        if(!isConfigChange){
            AppManager.getAppManager().finishActivity(this);
        }
    }

}
