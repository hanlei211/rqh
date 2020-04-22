package com.hl.lib.common.mvp;

import com.hl.lib.common.Interface.IPresenter;
import com.hl.lib.common.Interface.IView;
import com.hl.lib.common.baserx.RxManager;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;


public abstract class BasePresenter<M extends BaseModel, V extends IView> implements IPresenter<V> {

    protected M mModel;
    protected WeakReference<V> mView;

    private RxManager rxManager = new RxManager();

    @Override
    public void attachView(V view) {
        mView = new WeakReference<>(view);
        if (mModel == null) {
            this.mModel = createModule();
        }

    }

    public void injectLifecycle(LifecycleProvider lifecycle) {
        if (mModel != null) {
            mModel.injectLifecycle(lifecycle);
        }
    }

    @Override
    public boolean isViewAttach() {
        return mView != null && mView.get() != null;
    }

    /**
     * 由外部创建 module
     *
     * @return module
     */
    protected abstract M createModule();

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }

        if (mModel != null) {
            mModel = null;
        }
        if( rxManager != null){
            rxManager.clear();
        }

    }

    public V getView() {
        return mView.get();
    }

    protected void showLoading() {
        if (isViewAttach()) {
            getView().showInitLoadView();
        }
    }

    protected void dismissLoading() {
        if (isViewAttach()) {
            getView().hideInitLoadView();
        }
    }

    protected void onFail(Throwable th, String code, String msg) {
        if (isViewAttach()) {
            getView().showNetErrView();
        }
    }

    protected void onNoData() {
        if (isViewAttach()) {
            getView().showNoDataView();
        }
    }

}
