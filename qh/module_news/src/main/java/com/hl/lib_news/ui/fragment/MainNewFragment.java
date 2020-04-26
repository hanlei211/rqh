package com.hl.lib_news.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hl.lib.common.base.BaseMvpFragment;
import com.hl.lib_news.R;
import com.hl.lib_news.ui.bean.WeatherBean;
import com.hl.lib_news.ui.contract.NewMainContract;
import com.hl.lib_news.ui.model.NewsMainModel;
import com.hl.lib_news.ui.presenter.NewsMainPresenter;

import java.util.List;

public class MainNewFragment extends BaseMvpFragment<NewsMainPresenter, NewsMainModel> implements NewMainContract.View{

    public static MainNewFragment newInstance() {
        return new MainNewFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void initData() {
        mPresenter.setVM(mModel, this);
        mPresenter.lodeMineChannelsRequest(mRxlife);
    }

    @Override
    public void initView(View view) {

    }


    @Override
    public void initPresenter() {

    }

    @Override
    public void finishActivity() {

    }

    @Override
    public void showInitLoadView() {

    }

    @Override
    public void hideInitLoadView() {

    }

    @Override
    public void showNetErrView() {

    }

    @Override
    public void hideNetErrView() {

    }

    @Override
    public void showNoDataView() {

    }

    @Override
    public void hideNoDataView() {

    }

    @Override
    public void returnNewsChannelData(List<WeatherBean> model) {

    }
}