package com.hl.lib_news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hl.lib.common.base.BaseRefreshMvpFragment;
import com.hl.lib_news.R;
import com.hl.lib_news.ui.adapter.NewsListAdapter;
import com.hl.lib_news.ui.bean.DataBean;
import com.hl.lib_news.ui.bean.NewsDetail;
import com.hl.lib_news.ui.contract.NewsListContract;
import com.hl.lib_news.ui.model.NewsListModel;
import com.hl.lib_news.ui.presenter.NewsListPresenter;

import java.util.List;

public class NewsListFragment extends BaseRefreshMvpFragment<NewsListPresenter, NewsListModel> implements NewsListContract.View {
    public String newsType;
    public RecyclerView mRecylerView;
    public NewsListAdapter newsListAdapter;

    public static NewsListFragment newInstance(String newsType) {
        NewsListFragment newsListFragment = new NewsListFragment();
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
        mPresenter.setVM(this, mModel);
        mPresenter.getNewsListData(newsType);
    }

    @Override
    public void initView(View view) {
        mRecylerView = view.findViewById(R.id.recview_news_list);
        mRecylerView.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public boolean enableLazyData() {
        return true;
    }

    @Override
    public void showNetErrView() {

    }

    @Override
    public void hideNetErrView() {

    }

    @Override
    public void showNewsListData(List<NewsDetail> newsDetails) {
       addBanner(newsDetails);
        newsListAdapter = new NewsListAdapter(getContext(), newsDetails);
        mRecylerView.setAdapter(newsListAdapter);
    }

    /**
     * 添加广告轮播
     * @param newsDetails
     */
    private void addBanner(List<NewsDetail> newsDetails) {
        if(newsDetails != null){
            NewsDetail newsDetail = new NewsDetail();
            newsDetail.banners = DataBean.getTestData();
            newsDetails.add(0,newsDetail);
        }
    }

    @Override
    public void refreshData(List data) {
        addBanner(data);
        newsListAdapter.refresh(data);
    }

    @Override
    public void loadMoreData(List data) {
        newsListAdapter.addAll(data);
    }


    @Override
    public void initPresenter() {

    }

    @Override
    public void onRefreshEvent() {
        if (mPresenter != null) {
            mPresenter.setNewsType(newsType);
            mPresenter.refreshData();
        }
    }

    @Override
    public void onLoadMoreEvent() {
        mPresenter.setNewsType(newsType);
        mPresenter.loadMoreData();
    }

    @Override
    public void onAutoLoadEvent() {
        if (mPresenter != null) {
            mPresenter.setNewsType(newsType);
            mPresenter.refreshData();
        }
    }

}
