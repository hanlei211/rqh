package com.hl.news.ui.adapter;

import android.content.Context;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.hl.news.R;

import java.util.List;

public class ChannelAdapter  extends CommonRecycleViewAdapter<String> {

    public ChannelAdapter(Context context, int layoutId, List<String> mDatass) {
        super(context, layoutId, mDatass);
    }

    public ChannelAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, String name) {
        helper.setText(R.id.news_channel_tv,name);
    }
}
