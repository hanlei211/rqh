package com.hl.lib.common.http.listener;

import com.hl.lib.common.http.exception.ExceptionHandle;

public interface RequestListener {

    void onStart();

    void onError(ExceptionHandle exception);

    void onFinish();
}
