package com.hl.video.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hl.lib.common.base.BaseRefreshMvpFragment;
import com.hl.lib.common.http.event.BaseRxBusEvent;
import com.hl.video.R;
import com.hl.video.ui.adapter.VideoListAdapter;
import com.hl.video.ui.bean.VideoBean;
import com.hl.video.ui.config.VideoConstant;
import com.hl.video.ui.contract.VideoListContract;
import com.hl.video.ui.model.VideoListModel;
import com.hl.video.ui.presenter.VideoListPresenter;

import java.util.List;

import io.reactivex.functions.Consumer;

public class VideoListFragment extends BaseRefreshMvpFragment<VideoListPresenter, VideoListModel> implements VideoListContract.View {
    public String newsType;
    public RecyclerView mRecylerView;
    public VideoListAdapter videoListAdapter;

    public static VideoListFragment newInstance(String newsType) {
        VideoListFragment newsListFragment = new VideoListFragment();
        Bundle args = new Bundle();
        args.putString("newsType", newsType);
        newsListFragment.setArguments(args);
        return newsListFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        newsType = getArguments().getString("newsType");
    }

    @Override
    protected void onRxBusEvent() {
        mRxManager.on(BaseRxBusEvent.class, VideoConstant.NEWS_LIST_TO_TOP, new Consumer<BaseRxBusEvent>() {
            @Override
            public void accept(BaseRxBusEvent baseRxBusEvent) throws Exception {
                Log.e("rxBus","333");
                scrollToTop();
            }
        });
    }

    @Override
    protected int onBindRreshLayout() {
        return R.id.refview_news_list;
    }


    @Override
    public String getToolbarTitle() {
        return null;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_news_list;
    }

    @Override
    public void initData() {

    }


    @Override
    public void initView(View view) {
        mRecylerView = view.findViewById(R.id.recview_news_list);
    }

    @Override
    public boolean enableLazyData() {
        return true;
    }

    @Override
    public void showVideoList(List<VideoBean> videoBeans) {
//        addBanner(videoBeans);
        videoListAdapter = new VideoListAdapter(getContext(), videoBeans);
        mRecylerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecylerView.setAdapter(videoListAdapter);
    }

    @Override
    public void scrollToTop() {
        mRecylerView.smoothScrollToPosition(0);
    }

    /**
     * 添加广告轮播
     */
//    private void addBanner(List<VideoBean> videoBeans) {
//        if(videoBeans != null){
//            NewsDetail newsDetail = new NewsDetail();
//            newsDetail.banners = DataBean.getTestData();
//            videoBeans.add(0,newsDetail);
//        }
//    }

    @Override
    public void refreshData(List data) {
//        addBanner(data);
        videoListAdapter.refresh(data);
    }

    @Override
    public void loadMoreData(List data) {
        videoListAdapter.addAll(data);
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
        mPresenter.getVideoList(newsType);
    }
    @Override
    public void onRefreshEvent() {
        if (mPresenter != null) {
            mPresenter.setVideoType(newsType);
            mPresenter.refreshData();
        }
    }

    @Override
    public void onLoadMoreEvent() {
        mPresenter.setVideoType(newsType);
        mPresenter.loadMoreData();
    }

    @Override
    public void onAutoLoadEvent() {
        if (mPresenter != null) {
            mPresenter.setVideoType(newsType);
            mPresenter.refreshData();
        }
    }

}
