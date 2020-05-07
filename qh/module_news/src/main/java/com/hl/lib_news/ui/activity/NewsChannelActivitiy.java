package com.hl.lib_news.ui.activity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.hl.lib.common.base.BaseMvpActivity;
import com.hl.lib_news.R;
import com.hl.lib_news.ui.adapter.ChannelAdapter;
import com.hl.lib_news.ui.contract.NewsChannelContract;
import com.hl.lib_news.ui.model.NewsChannelModel;
import com.hl.lib_news.ui.presenter.NewsChannelPresenter;

import java.util.Arrays;
import java.util.List;


/**
 * 选择频道
 */
public class NewsChannelActivitiy  extends BaseMvpActivity<NewsChannelPresenter, NewsChannelModel>  implements NewsChannelContract.View {

    RecyclerView newsChannelMineRv;
    RecyclerView newsChannelMoreRv;

    ChannelAdapter myChannelAdapter;
    ChannelAdapter moreChannelAdapter;

    @Override
    public int onBindLayout() {
        return R.layout.act_news_channel;
    }

    @Override
    public void initView() {
        mTxtTitle.setText(getString(R.string.channel_manage));
        newsChannelMineRv = findViewById(R.id.news_channel_mine_rv);
        final List<String> myChannelNameList = Arrays.asList(getResources().getStringArray(R.array.news_channel_name));
        myChannelAdapter =  new ChannelAdapter(this,R.layout.item_news_channel);
        newsChannelMineRv.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        newsChannelMineRv.setItemAnimator(new DefaultItemAnimator());
        newsChannelMineRv.setAdapter(myChannelAdapter);
        myChannelAdapter.replaceAll(myChannelNameList);
        myChannelAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

        newsChannelMoreRv = findViewById(R.id.news_channel_more_rv);
        final List<String> moreChannelNameList = Arrays.asList(getResources().getStringArray(R.array.news_channel_name));
        moreChannelAdapter =  new ChannelAdapter(this,R.layout.item_news_channel);
        newsChannelMoreRv.setLayoutManager(new GridLayoutManager(this,4, LinearLayoutManager.VERTICAL,false));
        newsChannelMoreRv.setItemAnimator(new DefaultItemAnimator());
        newsChannelMoreRv.setAdapter(moreChannelAdapter);
        moreChannelAdapter.replaceAll(moreChannelNameList);
        moreChannelAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                moreChannelAdapter.removeAt(position);
                String channelName = moreChannelNameList.get(position);

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void showNetErrView() {

    }

    @Override
    public void hideNetErrView() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void showChannelData(List<String> channelDatas) {

    }
}
