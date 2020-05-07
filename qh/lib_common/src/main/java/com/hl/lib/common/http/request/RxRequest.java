package com.hl.lib.common.http.request;


import androidx.annotation.NonNull;

import com.hl.lib.common.http.RxHttp;
import com.hl.lib.common.http.RxLife;
import com.hl.lib.common.http.callback.ResultCallback;
import com.hl.lib.common.http.exception.ApiException;
import com.hl.lib.common.http.exception.ExceptionHandle;
import com.hl.lib.common.http.listener.RequestListener;
import com.hl.lib.common.http.reponse.BaseReponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class RxRequest<T, R extends BaseReponse<T>> {

    private final Observable<R> mObserVable;
    private ResultCallback<T> mCallBack = null;
    private RequestListener mListener = null;
    private RxLife mRxLife = null;


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
    public void request(@NonNull ResultCallback callback) {
        mCallBack = callback;
        mObserVable.subscribe(new Observer<R>() {
            @Override
            public void onSubscribe(Disposable d) {
                if (mRxLife != null) {
                    mRxLife.add(d);
                }
            }

            @Override
            public void onNext(R r) {
                if (!isSuccess(r.getCode())) {
                    mCallBack.onFailed(r.getCode(), r.getMsg());
                }
                mCallBack.onSuccess(r.getCode(), r.getData());
            }

            @Override
            public void onError(Throwable e) {
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
            }

            @Override
            public void onComplete() {
                if (mListener != null) {
                    mListener.onFinish();
                }
            }
        });
    }


    /**
     * 判断返回状态码是否正确
     *
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


