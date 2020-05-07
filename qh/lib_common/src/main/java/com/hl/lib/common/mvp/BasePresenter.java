package com.hl.lib.common.mvp;

import android.content.Context;



public abstract class BasePresenter<M ,V extends BaseView>{

    public M mModel;
    public V mView;
    public Context mContext;

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
    }


}
