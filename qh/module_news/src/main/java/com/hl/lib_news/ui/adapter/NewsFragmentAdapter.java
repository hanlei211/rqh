package com.hl.lib_news.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hl.lib_news.ui.fragment.NewsListFragment;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class NewsFragmentAdapter  extends FragmentPagerAdapter {

    public FragmentManager mFragmentManager;
    List<NewsListFragment> pages;
    private List<String> titles ;
    Context mContent;
    public NewsFragmentAdapter(Context mContext, FragmentManager fm,List<String> titles,List<NewsListFragment> newsListFragments) {
        super(fm);
        this.mContent = mContext;
        this.titles = titles;
        this.pages = newsListFragments;
        this.mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int i) {
        if (pages != null) {
            return pages.get(i);
        }
        return null;
    }

    @Override
    public int getCount() {
        return pages!=null?pages.size():0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
