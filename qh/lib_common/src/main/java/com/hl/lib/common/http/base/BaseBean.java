package com.hl.lib.common.http.base;

import com.google.gson.Gson;
import com.hl.lib.common.util.JsonFormatUtils;

import java.io.Serializable;

public class BaseBean implements Serializable {
    public String toJson() {
        return new Gson().toJson(this);
    }

    public String toFormatJson(){
        return JsonFormatUtils.format(toJson());
    }

}
