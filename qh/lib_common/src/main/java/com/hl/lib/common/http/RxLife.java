package com.hl.lib.common.http;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 生命周期
 */
public class RxLife {
    private CompositeDisposable compositeDisposable = null;

    private RxLife(){
        compositeDisposable = new CompositeDisposable();
    }

    public static RxLife create(){
        return new RxLife();
    }

    public void add(Disposable disposable){
        if(compositeDisposable == null || compositeDisposable.isDisposed()){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void destroy(Disposable disposable){
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            return;
        }
        compositeDisposable.dispose();
    }



}
