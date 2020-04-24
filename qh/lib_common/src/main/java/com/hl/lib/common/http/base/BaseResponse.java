package com.hl.lib.common.http.base;

public interface BaseResponse<T> {

    int getCode();

    void setMsg(String msg);

    T getData();

    void setData(T data);

    String getMsg();
}
