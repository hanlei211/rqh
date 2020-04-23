package com.hl.lib.common.mvp;

import android.content.Context;

import com.hl.lib.common.Interface.ILoadView;
import com.hl.lib.common.Interface.INetErrView;
import com.hl.lib.common.Interface.INoDataView;

/**
 * Description: <BaseView><br>
 */
public interface BaseView extends ILoadView, INoDataView,INetErrView {
    void initView();
    void initListener();
    void initData();
    void finishActivity();
    Context getContext();
}
