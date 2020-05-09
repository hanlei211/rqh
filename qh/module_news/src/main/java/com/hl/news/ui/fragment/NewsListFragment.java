package com.hl.news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hl.lib.common.base.BaseRefreshMvpFragment;
import com.hl.lib.common.http.event.BaseRxBusEvent;
import com.hl.news.R;
import com.hl.news.ui.adapter.NewsListAdapter;
import com.hl.news.ui.bean.DataBean;
import com.hl.news.ui.bean.NewsDetail;
import com.hl.news.ui.config.NewsConstant;
import com.hl.news.ui.contract.NewsListContract;
import com.hl.news.ui.model.NewsListModel;
import com.hl.news.ui.presenter.NewsListPresenter;

import java.util.List;

import io.reactivex.functions.Consumer;

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
    protected void onRxBusEvent() {
        mRxManager.on(BaseRxBusEvent.class, NewsConstant.NEWS_LIST_TO_TOP, new Consumer<BaseRxBusEvent>() {
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
    public void showNewsListData(List<NewsDetail> newsDetails) {
       addBanner(newsDetails);
        newsListAdapter = new NewsListAdapter(getContext(), newsDetails);
        mRecylerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecylerView.setAdapter(newsListAdapter);
    }

    @Override
    public void scrollToTop() {
        mRecylerView.smoothScrollToPosition(0);
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
        mPresenter.setVM(this, mModel);
        mPresenter.getNewsListData(newsType);
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
