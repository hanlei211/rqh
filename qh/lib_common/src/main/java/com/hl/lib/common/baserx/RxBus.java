package com.hl.lib.common.baserx;


import java.util.HashMap;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
/**
 * 用rxjava 实现 eventBus
 */
public class RxBus {
    private static volatile RxBus instance;
    private HashMap<String, CompositeDisposable> mSubscriptionMap;
    private final Subject<Object> mSubject;

    public static RxBus getInstance() {
        if (null == instance) {
            synchronized (RxBus.class) {
                if (null == instance) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    private RxBus() {
        //转换成一个线程安全的subject对象
        mSubject = PublishSubject.create().toSerialized();
    }



    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    public void post(Object type,Object o){
        mSubject.onNext(o);
    }

    /**
     * 返回实例
     */
    public <T> Flowable<T> getObservable(Class<T> type){
        return mSubject.toFlowable(BackpressureStrategy.BUFFER).ofType(type);
    }


    /**
     * 一个默认的订阅方法
     *
     * @param <T>
     * @param type
     * @param next
     * @param error
     * @return
     */
    public <T> Disposable doSubscribe(Class<T> type, Consumer<T> next, Consumer<Throwable> error){
        return  getObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next,error);
    }

    /**
     * 是否已有观察者订阅
     *
     * @return
     */
    public boolean hasObservers() {
        return mSubject.hasObservers();
    }
    /**
     * 保存订阅后的disposable
     * @param o
     * @param disposable
     */
    public void addSubscription(Object o, Consumer<Object> disposable) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }
        String key = o.getClass().getName();
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).add((Disposable) disposable);
        } else {
            //一次性容器,可以持有多个并提供 添加和移除。
            CompositeDisposable disposables = new CompositeDisposable();
            disposables.add((Disposable) disposable);
            mSubscriptionMap.put(key, disposables);
        }
    }

    /**
     * 取消订阅
     * @param o
     */
    public void unSubscribe(Object o) {
        if (mSubscriptionMap == null) {
            return;
        }

        String key = o.getClass().getName();
        if (!mSubscriptionMap.containsKey(key)){
            return;
        }
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).dispose();
        }

        mSubscriptionMap.remove(key);
    }



}
