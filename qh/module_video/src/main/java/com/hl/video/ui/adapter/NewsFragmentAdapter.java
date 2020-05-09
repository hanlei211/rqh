package com.hl.video.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hl.video.ui.fragment.VideoListFragment;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class NewsFragmentAdapter  extends FragmentPagerAdapter {

    public FragmentManager mFragmentManager;
    List<VideoListFragment> pages;
    private List<String> titles ;
    Context mContent;
    public NewsFragmentAdapter(Context mContext, FragmentManager fm,List<String> titles,List<VideoListFragment> newsListFragments) {
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

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
