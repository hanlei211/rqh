package com.hl.main;

import com.hl.lib.common.base.BaseActivity;


/**
 * 闪屏页面
 */
public class SplashActivity extends BaseActivity {

    @Override
    public int onBindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        startActivity(MainActivity.class);
        finish();
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
}
