package com.hl.lib_news.provider;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hl.lib.common.provider.INewsProvider;
import com.hl.lib_news.ui.fragment.MainNewFragment;

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
