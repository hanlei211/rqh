package com.hl.video.ui.adapter;

import android.content.Context;
import android.view.View;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.hl.video.R;
import com.hl.video.ui.bean.VideoBean;

import java.util.List;

public class VideoListAdapter extends CommonRecycleViewAdapter<VideoBean> {

    public VideoListAdapter(Context context, int layoutId, final List<VideoBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolderHelper helper, VideoBean VideoBean) {
        setItemValues(helper, VideoBean, getPosition(helper));
    }

    /**
     * 普通样式
     *
     * @param holder
     * @param VideoBean
     * @param position
     */
    private void setItemValues(final ViewHolderHelper holder, final VideoBean VideoBean, final int position) {
        String title = VideoBean.title;
        String rate = VideoBean.rate;
        String imgSrc = VideoBean.cover;

        holder.setText(R.id.tv_title, title);
        holder.setText(R.id.tv_rate, rate);
        holder.setImageUrl(R.id.iv_url, imgSrc);
        holder.setOnClickListener(R.id.ll_root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                VideoBeanActivity.startAction(mContext,holder.getView(R.id.news_summary_photo_iv),VideoBean.getPostid(),VideoBean.getImgsrc());
            }
        });
    }


}
