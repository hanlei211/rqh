package com.hl.lib.common.http.exception;

public class RxHttpUninitializedException extends RuntimeException {

    public RxHttpUninitializedException() {
        super("RxHttp未初始化");
    }
}
