package com.hl.lib_news.ui.model;

import com.hl.lib_news.ui.bean.NewsChannelTable;
import com.hl.lib_news.ui.contract.NewMainContract;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class NewsMainModel implements NewMainContract.Model {
    @Override
    public Observable<List<NewsChannelTable>> lodeMineNewsChannels() {
        return Observable.create(new ObservableOnSubscribe<List<NewsChannelTable>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NewsChannelTable>> emitter) throws Exception {

            }
        }).compose(RxSchedulers.);
    }
}
