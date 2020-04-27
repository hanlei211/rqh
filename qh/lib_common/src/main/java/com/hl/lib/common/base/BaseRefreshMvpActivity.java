package com.hl.lib.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hl.lib.common.baseapp.AppManager;
import com.hl.lib.common.baserx.RxManager;
import com.hl.lib.common.http.RxLife;
import com.hl.lib.common.mvp.BaseRefreshPresenter;
import com.hl.lib.common.refresh.BaseRefreshLayout;
import com.hl.lib.common.refresh.DaisyRefreshLayout;

public abstract class BaseRefreshMvpActivity<T extends BaseRefreshPresenter, E extends BaseModel> extends BaseActivity {
    protected DaisyRefreshLayout mRefreshLayout;
    public T mPresenter;
    public E mModel;
    public Context mContext;
    public RxManager mRxManager;
    public RxLife mRxLife;
    private boolean isConfigChange = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager=new RxManager();
        mRxLife = RxLife.create();
        initPresenter();
    }

    @Override
    public void initCommonView() {
        super.initCommonView();
        initRefreshView();
    }

    public void initRefreshView() {
        mRefreshLayout = findViewById(onBindRreshLayout());
        // 下拉刷新
        mRefreshLayout.setOnRefreshListener(new BaseRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshEvent();
            }
        });
        // 上拉加载
        mRefreshLayout.setOnLoadMoreListener(new BaseRefreshLayout.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                onLoadMoreEvent();
            }
        });
        // 自动加载
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

    protected abstract int onBindRreshLayout();

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

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    @Override
    public void showNetErrView() {

    }

    @Override
    public void hideNetErrView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mRxManager != null){
            mRxManager.clear();
        }
        if (mPresenter != null)
            mPresenter.onDestroy();
        if(!isConfigChange){
            AppManager.getAppManager().finishActivity(this);
        }
    }
}
