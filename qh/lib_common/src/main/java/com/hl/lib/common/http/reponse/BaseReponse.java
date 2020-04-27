package com.hl.lib.common.http.reponse;

public interface BaseReponse<E> {
    int getCode();

    void setCode(int code);

    E getData();

    void setData(E data);

    String getMsg();

    void setMsg(String msg);
}
