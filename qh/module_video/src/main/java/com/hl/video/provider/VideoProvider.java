package com.hl.video.provider;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hl.lib.common.provider.IVideoProvider;
import com.hl.video.ui.fragment.MainVideoFragment;

@Route(path = "/video/main",name = "视频")
public class VideoProvider implements IVideoProvider {

    @Override
    public void init(Context context) {

    }

    @Override
    public Fragment getMainVideoFragment() {
        return MainVideoFragment.newInstance();
    }
}
