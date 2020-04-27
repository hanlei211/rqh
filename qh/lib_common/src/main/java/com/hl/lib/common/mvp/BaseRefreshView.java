package com.hl.lib.common.mvp;

import java.util.List;

/**
 * Description: <BaseRefreshView><br>
 */
public interface BaseRefreshView<T> extends BaseRefreshContract.View {
    //刷新数据
    void refreshData(List<T> data);
    //加载更多
    void loadMoreData(List<T> data);

}
