package com.hl.lib_news.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hl.lib.common.base.BaseMvpFragment;
import com.hl.lib_news.R;
import com.hl.lib_news.ui.bean.WeatherBean;
import com.hl.lib_news.ui.contract.NewMainContract;
import com.hl.lib_news.ui.model.NewsMainModel;
import com.hl.lib_news.ui.presenter.NewsMainPresenter;

import java.util.List;

public class MainNewFragment extends BaseMvpFragment<NewsMainPresenter, NewsMainModel> implements NewMainContract.View{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    public static MainNewFragment newInstance() {
        return new MainNewFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initPresenter() {

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
        mPresenter.setVM(this, mModel);
        mPresenter.lodeMineChannelsRequest(mRxlife);
    }

    @Override
    public void initView(View view) {
      mViewPager = view.findViewById(R.id.pager_tour);
      mTabLayout = view.findViewById(R.id.layout_tour);
    }

    @Override
    public void returnNewsChannelData(List<WeatherBean> model) {

    }

    @Override
    public void showNetErrView() {

    }

    @Override
    public void hideNetErrView() {

    }
}
