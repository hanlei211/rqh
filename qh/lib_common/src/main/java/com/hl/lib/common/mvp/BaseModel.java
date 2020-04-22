package com.hl.lib.common.mvp;

import android.content.Context;

import com.hl.lib.common.baserx.RxManager;
import com.trello.rxlifecycle2.LifecycleProvider;

public class BaseModel {

    private Context context;
    private LifecycleProvider lifecycle;
    public RxManager mRxManage = new RxManager();

    public BaseModel(Context context) {
        this.context = context;
    }

    public LifecycleProvider getLifecycle(){
        return lifecycle;
    }

    public void injectLifecycle(LifecycleProvider lifecycle){
        this.lifecycle = lifecycle;
    }


    public android.content.Context getContext() {
        return context;
    }
    public void destory(){
        mRxManage.clear();
    }
}
