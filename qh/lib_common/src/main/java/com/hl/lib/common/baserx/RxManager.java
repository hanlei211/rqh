package com.hl.lib.common.baserx;



import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 用于管理单个presenter的RxBus的事件和Rxjava相关代码的生命周期处理
 */
public class RxManager {
    public RxBus mRxBus = RxBus.getInstance();
    private Map<String, Observable<?>> mObservables = new HashMap<>();
    /**
     * 管理订阅者
     */
    private CompositeDisposable mCompositeSubscription = new CompositeDisposable();


    /**
     * RxBus注入监听
     * @param type
     * @param eventName
     * @param consumer
     */
    public <T>void on(Class<T> type, String eventName, Consumer<T> consumer) {
        Disposable disposable = mRxBus.doSubscribe(type, consumer, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
        mRxBus.addSubscription(eventName,disposable);
        mCompositeSubscription.add(disposable);
    }

    /**
     * 单纯的Observables 和 Subscribers管理
     * @param m
     */
    public void add(Disposable m) {
        /*订阅管理*/
        mCompositeSubscription.add(m);
    }
    /**
     * 单个presenter生命周期结束，取消订阅和所有rxbus观察
     */
    public void clear() {
        mCompositeSubscription.dispose();// 取消所有订阅
        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet()) {
            mRxBus.unSubscribe(entry.getKey());// 移除rxbus观察
        }
    }
    //发送rxbus
    public void post(Object tag, Object content) {
        mRxBus.post(tag, content);
    }
}
