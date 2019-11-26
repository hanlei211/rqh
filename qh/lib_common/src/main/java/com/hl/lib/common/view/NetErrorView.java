package com.hl.lib.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.hl.lib.common.R;

public class NetErrorView extends RelativeLayout {


    public NetErrorView(Context context) {
        super(context);
        init();
    }

    public NetErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NetErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.button_network_err,this, true);
        this.setClickable(true);
        this.setFocusable(true);
    }
}
