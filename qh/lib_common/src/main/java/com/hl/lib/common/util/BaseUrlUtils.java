package com.hl.lib.common.util;

/**
 * 描述：检查BaseUrl是否以"/"结尾
 *
 */
public class BaseUrlUtils {

    public static String checkBaseUrl(String url) {
        if (url.endsWith("/")) {
            return url;
        } else {
            return url + "/";
        }
    }
}
