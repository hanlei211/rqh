package com.hl.lib.common.Interface;


public interface IPresenter<T extends IView> {

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
