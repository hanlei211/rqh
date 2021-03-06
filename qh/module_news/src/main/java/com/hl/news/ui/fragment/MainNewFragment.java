package com.hl.news.ui.fragment;


import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.ImageView;

import com.hl.lib.common.base.BaseMvpFragment;
import com.hl.lib.common.baserx.RxBus;
import com.hl.lib.common.http.event.BaseRxBusEvent;
import com.hl.news.R;
import com.hl.news.ui.adapter.NewsFragmentAdapter;
import com.hl.news.ui.contract.NewMainContract;
import com.hl.news.ui.model.NewsMainModel;
import com.hl.news.ui.presenter.NewsMainPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainNewFragment extends BaseMvpFragment<NewsMainPresenter, NewsMainModel> implements NewMainContract.View{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView imageView;
    private List<String> titles = new ArrayList<>();
    private List<NewsListFragment> newsListFragments = new ArrayList<>();
    private NewsFragmentAdapter adapter;

    public static MainNewFragment newInstance() {
        return new MainNewFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onRxBusEvent() {

    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
        mPresenter.getListNewsType();
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
    }

    @Override
    public void initView(View view) {
      mViewPager = view.findViewById(R.id.pager_tour);
      mTabLayout = view.findViewById(R.id.layout_tour);
      imageView = view.findViewById(R.id.add_channel_iv);
      imageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
//              startActivity(NewsChannelActivitiy.class);
//                mRxManager.post(NewsConstant.NEWS_LIST_TO_TOP,"");
              RxBus rxBus = RxBus.getInstance();
               //发送事件
              rxBus.post(new BaseRxBusEvent());
          }
      });
    }

    @Override
    public void showChannelType(List<String> newsTypes) {
        newsListFragments.clear();
        titles.clear();
        if(newsTypes != null){
            for(String newType : newsTypes){
                titles.add(newType);
                newsListFragments.add(NewsListFragment.newInstance(newType));
            }
        }
    }
    

    @Override
    public void initTabLayout() {
        adapter  =  new NewsFragmentAdapter(getContext(),getChildFragmentManager(),titles,newsListFragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                newsListFragments.get(tab.getPosition()).autoLoadData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void showNetErrView() {

    }

    @Override
    public void hideNetErrView() {

    }
}
