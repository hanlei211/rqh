package com.hl.news.provider;

import android.content.Context;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hl.lib.common.provider.INewsProvider;
import com.hl.news.ui.fragment.MainNewFragment;

@Route(path = "/new/main",name = "新闻")
public class NewsProvider implements INewsProvider {
    @Override
    public Fragment getMainNewsFragment() {
        return MainNewFragment.newInstance();
    }

    @Override
    public void init(Context context) {

    }
}
