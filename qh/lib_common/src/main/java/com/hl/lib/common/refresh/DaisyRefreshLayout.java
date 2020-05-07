package com.hl.lib.common.refresh;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

/**
 * Description: <小菊花样式的刷新控件><br>
 */
public class DaisyRefreshLayout extends BaseRefreshLayout{
    private DaisyHeaderView mDaisyHeaderView;
    private DaisyFooterView mDaisyFooterView;

    public DaisyRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDaisyHeaderView = new DaisyHeaderView(context);
        mDaisyFooterView = new DaisyFooterView(context);
        setHeaderView(mDaisyHeaderView);
        setFooterView(mDaisyFooterView);
        setOnPullRefreshListener(new OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                mDaisyHeaderView.onRefresh();
                if (mOnRefreshListener != null) {
                    mOnRefreshListener.onRefresh();
                }
            }

            @Override
            public void onPullDistance(int distance) {

            }

            @Override
            public void onPullEnable(boolean enable) {
                mDaisyHeaderView.onPullEnable(enable);
            }
        });
        setOnPushLoadMoreListener(new OnPushLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mDaisyFooterView.onLoadMore();
                if (mOnLoadMoreListener != null) {
                    mOnLoadMoreListener.onLoadMore();
                }
            }

            @Override
            public void onPushDistance(int distance) {

            }

            @Override
            public void onPushEnable(boolean enable) {
                mDaisyFooterView.onPushEnable(enable);
            }
        });
    }

    @Override
    public void showRefresh() {
        if (mDaisyHeaderView != null) {
            mDaisyHeaderView.onRefresh();
        }
    }
    @Override
    public void setRefreshing(boolean refreshing){
        mDaisyHeaderView.setRefreshing(refreshing);
        super.setRefreshing(refreshing);
    }
    public void setLoadMore(boolean loadMore) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mDaisyFooterView.setLoadMore(loadMore);
        }
        super.setLoadMore(loadMore);
    }
}
