package com.hl.lib.common.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hl.lib.common.Interface.IView;
import com.hl.lib.common.R;
import com.hl.lib.common.manager.ActivityManager;
import com.hl.lib.common.util.NetUtil;
import com.hl.lib.common.view.LoadingInitView;
import com.hl.lib.common.view.NetErrorView;
import com.hl.lib.common.view.NoDataView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseActivity extends RxAppCompatActivity  implements IView {
    private ViewStub mViewStubToolbar;
    private ViewStub mViewStubContent;
    private ViewStub mViewStubInitLoading;
    private ViewStub mViewStubTransLoading;
    private ViewStub mViewStubNoData;
    private ViewStub mViewStubError;
    protected NetErrorView mNetErrorView;
    protected NoDataView mNoDataView;

    protected LoadingInitView mLoadingInitView;
    protected TextView mTxtTitle;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_root);
        initCommonView();
        ARouter.getInstance().inject(this);
        initView();
        initListener();
        initData();
        EventBus.getDefault().register(this);
        ActivityManager.getInstance().addActivity(this);
    }

    private void initCommonView() {
        mViewStubToolbar = findViewById(R.id.view_stub_toolbar);
        mViewStubContent = findViewById(R.id.view_stub_content);
        mViewStubInitLoading = findViewById(R.id.view_stub_init_loading);
        if (enableToolbar()) {
            mViewStubToolbar.setLayoutResource(onBindToolbarLayout());
            View view = mViewStubToolbar.inflate();
            initToolbar(view);
        }
        mViewStubContent.setLayoutResource(getLayoutId());
        mViewStubContent.inflate();
    }

    public abstract int getLayoutId();

    private void initToolbar(View view) {
        mToolbar = view.findViewById(R.id.toolbar_root);
        mTxtTitle = view.findViewById(R.id.toolbar_title);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }

    public boolean enableToolbar() {
        return true;
    }

    public int onBindToolbarLayout() {
        return R.layout.common_toolbar;
    }
    /**
     * 初始化view
     */
    public abstract void initView();
    /**
     * 初始化数据
     */
    public abstract void initData();

    public void initListener() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ActivityManager.getInstance().finishActivity(this);
    }

    public void showInitLoadView() {
        showInitLoadView();
    }

    public void showInitLoadView(boolean b) {
        if (mLoadingInitView == null) {
            View view = mViewStubInitLoading.inflate();
            mLoadingInitView = view.findViewById(R.id.view_init_loading);
        }
        mLoadingInitView.setVisibility(b ? View.VISIBLE : View.GONE);
        mLoadingInitView.loading(b);
    }

    private void showNetWorkErrView(boolean show) {
        if (mNetErrorView == null) {
            View view = mViewStubError.inflate();
            mNetErrorView = view.findViewById(R.id.view_net_error);
            mNetErrorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!NetUtil.checkNetToast()) {
                        return;
                    }
                    hideNetWorkErrView();
                    initData();
                }
            });
        }
        mNetErrorView.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    public void hideInitLoadView() {
        showInitLoadView(false);
    }


    public void showNoDataView() {
        showNoDataView(true);
    }

    private void showNoDataView(boolean show) {
        if (mNoDataView == null) {
            View view = mViewStubNoData.inflate();
            mNoDataView = view.findViewById(R.id.view_no_data);
        }
        mNoDataView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showNoDataView(boolean show, int resid) {
        showNoDataView(show);
        if (show) {
            mNoDataView.setNoDataView(resid);
        }
    }


    public void showNoDataView(int resid) {
        showNoDataView(true, resid);
    }

    public void hideNoDataView() {
        showNoDataView(false);
    }

    public void hideNetWorkErrView() {
        showNetWorkErrView(false);
    }


    /**
     * 关掉activity
     */
    @Override
    public void finishActivity() {

    }

    /**
     * 获取当前context
     * @return
     */
    @Override
    public Context getContext() {
        return this;
    }

}
