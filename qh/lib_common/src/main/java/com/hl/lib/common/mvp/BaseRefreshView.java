package com.hl.lib.common.mvp;

import java.util.List;

/**
 * Description: <BaseRefreshView><br>
 */
public interface BaseRefreshView<T> extends BaseRefreshContract.View {
    //ˢ������
    void refreshData(List<T> data);
    //���ظ���
    void loadMoreData(List<T> data);

}
