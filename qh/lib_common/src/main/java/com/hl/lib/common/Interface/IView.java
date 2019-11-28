package com.hl.lib.common.Interface;

import android.content.Context;

public interface IView extends ILoadView,INetErrView,INoDataView {
    void  finishActivity();
    Context getContext();
}
