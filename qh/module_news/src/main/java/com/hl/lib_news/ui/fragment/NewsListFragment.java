package com.hl.lib_news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.hl.lib.common.base.BaseRefreshMvpFragment;
import com.hl.lib_news.R;
import com.hl.lib_news.ui.bean.NewsDetail;
import com.hl.lib_news.ui.contract.NewsListContract;
import com.hl.lib_news.ui.model.NewsListModel;
import com.hl.lib_news.ui.presenter.NewsListPresenter;

import java.util.List;

public class NewsListFragment  extends BaseRefreshMvpFragment<NewsListPresenter, NewsListModel> implements NewsListContract.View{
    public String  newsType;

    public static NewsListFragment newInstance(String newsType) {
        NewsListFragment newsListFragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putString("newsType",newsType);
        newsListFragment.setArguments(args);
        return new NewsListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        newsType = getArguments().getString("newsType");
    }

    @Override
    protected int onBindRreshLayout() {
        return R.layout.fragment_news_list;
    }


    @Override
    public String getToolbarTitle() {
        return null;
    }

    @Override
    public int onBindLayout() {
        return 0;
    }

    @Override
    public void initData() {
        mPresenter.setVM(this, (NewsListContract.Model) mModel);
        mPresenter.getNewsListData(mRxLife,newsType);
    }

    @Override
    public void initView(View view) {

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

    }

    @Override
    public void refreshData(List data) {

    }

    @Override
    public void loadMoreData(List data) {

    }

    @Override
    public void onRefreshEvent() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onLoadMoreEvent() {

    }

    @Override
    public void onAutoLoadEvent() {

    }

    @Override
    public void autoLoadData() {

    }
}
