package com.hl.video.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.hl.video.ui.fragment.VideoListFragment;

import java.util.List;

public class NewsMainFragmentAdapter extends FragmentStateAdapter {
    public List<String> titles;

    public NewsMainFragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<String> titles) {
        super(fragmentActivity);
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return VideoListFragment.newInstance(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.size();
    }
}
