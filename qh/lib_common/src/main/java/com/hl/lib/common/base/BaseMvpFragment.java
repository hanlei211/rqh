package com.hl.lib.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hl.lib.common.baserx.RxManager;
import com.hl.lib.common.http.RxLife;
import com.hl.lib.common.mvp.BasePresenter;
import com.hl.lib.common.util.TypeConvertUtil;

import butterknife.ButterKnife;

public abstract class BaseMvpFragment<T extends BasePresenter,E extends BaseModel> extends BaseFragment{

    public T mPresenter;
    public E mModel;
    public RxManager mRxManager;
    public RxLife mRxLife;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRxManager=new RxManager();
        mRxLife = RxLife.create();
        mPresenter = TypeConvertUtil.getT(this,0);
        mModel = TypeConvertUtil.getT(this,1);
        if(mPresenter !=null){
            mPresenter.mContext =this.getActivity();
        }
        initPresenter();
        return mView;
    }

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if(mRxManager != null){
            mRxManager.clear();
        }
        if(mRxLife != null){
            mRxLife.destroy();
        }
        if(mPresenter != null){
            mPresenter= null;
        }
    }
}
