package com.hl.lib_news.ui.model;

import com.hl.lib_news.ui.bean.NewsDetail;

import java.io.Serializable;
import java.util.List;

public class NewsListResult implements Serializable {

    public  String channel;
    public  int num;
    public List<NewsDetail> list;
}
