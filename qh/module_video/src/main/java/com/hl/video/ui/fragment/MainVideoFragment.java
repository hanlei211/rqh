package com.hl.video.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hl.lib.common.base.BaseMvpFragment;
import com.hl.lib.common.baserx.RxBus;
import com.hl.lib.common.http.event.BaseRxBusEvent;
import com.hl.video.R;
import com.hl.video.ui.adapter.NewsFragmentAdapter;
import com.hl.video.ui.contract.VideoMainContract;
import com.hl.video.ui.model.VideoMainModel;
import com.hl.video.ui.presenter.VideoMainPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainVideoFragment extends BaseMvpFragment<VideoMainPresenter, VideoMainModel> implements VideoMainContract.View{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> titles = new ArrayList<>();
    private List<VideoListFragment> videoListFragments = new ArrayList<>();
    private NewsFragmentAdapter adapter;

    public static MainVideoFragment newInstance() {
        return new MainVideoFragment();
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
        mPresenter.getVideoTitle();
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
    }


    @Override
    public void showVideoTitle(List<String> videoType) {
        videoListFragments.clear();
        titles.clear();
        if(videoType != null){
            for(String newType : videoType){
                titles.add(newType);
                videoListFragments.add(VideoListFragment.newInstance(newType));
            }
        }
    }

    @Override
    public void initTabLayout() {
        adapter  =  new NewsFragmentAdapter(getContext(),getChildFragmentManager(),titles,videoListFragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                videoListFragments.get(tab.getPosition()).autoLoadData();
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
