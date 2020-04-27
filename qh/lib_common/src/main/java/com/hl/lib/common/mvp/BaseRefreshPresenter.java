package com.hl.lib.common.mvp;

import android.content.Context;

import com.hl.lib.common.base.BaseRefreshMvpFragment;
import com.hl.lib.common.baserx.RxManager;


public abstract class BaseRefreshPresenter<M ,V>{

    public M mModel;
    public V mView;
    public Context mContext;

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
    }

    public void  onDestroy(){
    }

}
