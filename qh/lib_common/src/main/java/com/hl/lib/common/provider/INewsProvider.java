package com.hl.lib.common.provider;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Description: <INewsProvider><br>
 * Author:      mxdl<br>
 * Update:     <br>
 */
public interface INewsProvider extends IProvider {
    Fragment getMainNewsFragment();
}
