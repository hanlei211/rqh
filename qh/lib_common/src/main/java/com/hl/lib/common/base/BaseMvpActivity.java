package com.hl.lib.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hl.lib.common.mvp.BaseModel;
import com.hl.lib.common.mvp.BasePresenter;

public abstract  class BaseMvpActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {

    public T mPresenter;
    public E mModel;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }






}
