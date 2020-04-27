package com.hl.lib.common.http.request;


import android.support.annotation.NonNull;

import com.hl.lib.common.http.RxHttp;
import com.hl.lib.common.http.RxLife;
import com.hl.lib.common.http.callback.ResultCallback;
import com.hl.lib.common.http.exception.ApiException;
import com.hl.lib.common.http.exception.ExceptionHandle;
import com.hl.lib.common.http.listener.RequestListener;
import com.hl.lib.common.http.reponse.BaseReponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class RxRequest<T, R extends BaseReponse<T>> {

    private Observable<R> mObserVable;
    private ResultCallback<T> mCallBack = null;
    private RequestListener mListener = null;
    private RxLife mRxLife;


    public RxRequest(Observable<R> obserVable) {
        mObserVable = obserVable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T, R extends BaseReponse<T>> RxRequest<T, R> create(@NonNull Observable observable) {
        return new RxRequest<>(observable);
    }

    /**
     * 添加请求生命周期的监听
     */
    public RxRequest<T, R> listener(RequestListener listener) {
        this.mListener = listener;
        return this;
    }

    /**
     * 发生请求并回调
     *
     * @return
     */
    public Disposable request(@NonNull ResultCallback callback) {
        mCallBack = callback;
        Disposable disposable = mObserVable.subscribe(new Consumer<BaseReponse<T>>() {
            @Override
            public void accept(BaseReponse<T> response) throws Exception {
                if (!isSuccess(response.getCode())) {
                    throw new ApiException(response.getCode(), response.getMsg());
                }
                mCallBack.onSuccess(response.getCode(), response.getData());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable e) throws Exception {
                if (e instanceof ApiException) {
                    ApiException exception = (ApiException) e;
                    mCallBack.onFailed(exception.getCode(), (exception.getMsg()));
                } else {
                    if (mListener != null) {
                        ExceptionHandle handle = RxHttp.getRequestSetting().getExceptionHandle();
                        if (handle == null) {
                            handle = new ExceptionHandle();
                        }
                        handle.handle(e);
                        mListener.onError(handle);
                    }
                }
                if (mListener != null) {
                    mListener.onFinish();
                }
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                if (mListener != null) {
                    mListener.onFinish();
                }
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                if (mListener != null) {
                    mListener.onStart();
                }
            }
        });
        if (mRxLife != null) {
            mRxLife.add(disposable);
        }
        return disposable;
    }


    /**
     * 判断返回状态码是否正确
     * @param code
     * @return
     */
    public boolean isSuccess(int code) {
        if (code == RxHttp.getRequestSetting().getSuccessCode()) {
            return true;
        }
        int[] codes = RxHttp.getRequestSetting().getMultiSuccessCode();
        if (codes == null && codes.length <= 0) {
            return false;
        }
        for (int i : codes) {
            if (code == i) {
                return true;
            }
        }
        return false;
    }
}


