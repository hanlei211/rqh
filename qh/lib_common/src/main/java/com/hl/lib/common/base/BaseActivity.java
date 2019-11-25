package com.hl.lib.common.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hl.lib.common.manager.ActivityManager;
import com.hl.lib.common.R;
import com.hl.lib.common.view.BaseView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public abstract class BaseActivity extends RxAppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        setContentView(R.layout.activity_root);
//        initCommonView();
//        ARouter.getInstance().inject(this);
//        initView();
//        initListener();
//        initData();
//        EventBus.getDefault().register(this);
        ActivityManager.getInstance().addActivity(this);
    }
}
