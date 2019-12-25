package com.hl.lib.common.event.common;

import com.hl.lib.common.event.BaseEvent;

public class BaseActivityEvent<T> extends BaseEvent<T> {
    public BaseActivityEvent(int code) {
        super(code);
    }
}
