package com.hl.lib.common.http.callback;


import retrofit2.Callback;

public interface ResultCallback<E> {
    void onSuccess(int code, E data);

    void onFailed(int code, String msg);
}
