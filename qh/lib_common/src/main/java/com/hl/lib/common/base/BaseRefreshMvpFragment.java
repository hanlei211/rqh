package com.hl.lib.common.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hl.lib.common.baserx.RxManager;
import com.hl.lib.common.http.RxLife;
import com.hl.lib.common.mvp.BaseRefreshPresenter;
import com.hl.lib.common.refresh.BaseRefreshLayout;
import com.hl.lib.common.refresh.DaisyRefreshLayout;
import com.hl.lib.common.util.TypeConvertUtil;

import butterknife.ButterKnife;

public abstract class BaseRefreshMvpFragment<T extends BaseRefreshPresenter,E extends BaseModel> extends BaseFragment{
    protected DaisyRefreshLayout mRefreshLayout;
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

    @Override
    public void initCommonView(View view) {
        super.initCommonView(view);
        initRefreshView(view);
    }

    protected abstract int onBindRreshLayout();

    public void initRefreshView(View view) {
        mRefreshLayout = view.findViewById(onBindRreshLayout());
        mRefreshLayout.setOnRefreshListener(new BaseRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshEvent();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new BaseRefreshLayout.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                onLoadMoreEvent();
            }
        });
        mRefreshLayout.setOnAutoLoadListener(new BaseRefreshLayout.OnAutoLoadListener() {
            @Override
            public void onAutoLoad() {
                onAutoLoadEvent();
            }
        });
    }

    protected abstract void onAutoLoadEvent();

    protected abstract void onLoadMoreEvent();

    protected abstract void onRefreshEvent();


    public void enableRefresh(boolean b) {
        mRefreshLayout.setEnableRefresh(b);
    }

    public void enableLoadMore(boolean b) {
        mRefreshLayout.setEnableLoadMore(b);
    }

    public void stopRefresh() {
        mRefreshLayout.setRefreshing(false);
    }

    public void stopLoadMore() {
        mRefreshLayout.setLoadMore(false);
    }

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    public void autoLoadData() {
        if(mRefreshLayout != null){
            mRefreshLayout.autoRefresh();
        }
    }

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
