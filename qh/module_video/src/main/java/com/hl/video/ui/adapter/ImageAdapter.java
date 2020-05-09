package com.hl.video.ui.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.hl.banner.adapter.BaseBannerAdapter;
import com.hl.video.ui.bean.DataBean;
import com.hl.video.ui.viewHolder.ImageViewHolder;

import java.util.List;

public class ImageAdapter  extends BaseBannerAdapter<DataBean, ImageViewHolder> {

    public ImageAdapter(List<DataBean> datas) {
        super(datas);
    }

    @Override
    public ImageViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(params);
        return new ImageViewHolder(imageView);
    }

    @Override
    public void onBindView(ImageViewHolder holder, DataBean data, int position, int size) {
          holder.imageView.setImageResource(data.imageRes);
    }
}
