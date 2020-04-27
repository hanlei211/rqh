package com.hl.lib.common.refresh.contract;

/**
 * Description: <下拉刷新的协议><br>
 * Update:     <br>
 */
public interface PullContract {
    /**
     * 手指上滑下滑的回调
     * @param enable
     */
    void onPullEnable(boolean enable);

    /**
     * 手指松开的回调
     */
    void onRefresh();

  }
