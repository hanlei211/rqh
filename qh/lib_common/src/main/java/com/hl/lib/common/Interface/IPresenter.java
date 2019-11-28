package com.hl.lib.common.Interface;


public interface IPresenter<T extends IView> {

    /**
     * 绑定view
     * @param view
     */
  void attachView(T view);

    /**
     * 解绑view
     */
  void detachView();
    /**
     * 判断view是否已经销毁
     * @return true 未销毁
     */
    boolean isViewAttach();
}
