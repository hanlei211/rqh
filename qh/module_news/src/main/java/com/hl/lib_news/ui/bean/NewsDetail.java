package com.hl.lib_news.ui.bean;

import com.hl.lib.common.http.base.BaseBean;

import java.util.List;

public class NewsDetail  extends BaseBean {

    public String title;
    public String time;
    public String src;
    public String category;
    public String pic;
    public String content;
    public String url;
    public String webUrl;
    public List<DataBean> banners;
}
