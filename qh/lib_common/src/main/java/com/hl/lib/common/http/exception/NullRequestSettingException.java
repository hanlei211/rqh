package com.hl.lib.common.http.exception;

/**
 */
public class NullRequestSettingException extends RuntimeException {
    public NullRequestSettingException() {
        super("RequestSetting未设置");
    }
}
