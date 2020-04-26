package com.hl.lib.common.mvp;

import android.content.Context;

import com.hl.lib.common.baserx.RxManager;



public abstract class BasePresenter<M ,V>{

    public M mModel;
    public V mView;
    private RxManager rxManager = new RxManager();
    public Context mContext;

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
    }

    public void  onDestroy(){
        if(rxManager != null){
            rxManager.clear();
        }
    }

}
