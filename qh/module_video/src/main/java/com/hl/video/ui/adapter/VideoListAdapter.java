package com.hl.video.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.hl.video.R;
import com.hl.video.ui.bean.VideoBean;

import java.util.List;

public class VideoListAdapter extends MultiItemRecycleViewAdapter<VideoBean> {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_PHOTO_ITEM = 1;
    public static final int TYPE_BANNER = 3;
    List<VideoBean> VideoBeans;
    Context mContext;

    public VideoListAdapter(Context context, final List<VideoBean> datas) {
        super(context, datas, new MultiItemTypeSupport<VideoBean>() {
            @Override
            public int getLayoutId(int itemType) {
                if (itemType == TYPE_PHOTO_ITEM) {
                    return R.layout.item_news_photo;
                } else if (itemType == TYPE_BANNER) {
                    return R.layout.item_banner;
                } else {
                    return R.layout.item_news;
                }
            }

            @Override
            public int getItemViewType(int position, VideoBean VideoBean) {
                 if (!TextUtils.isEmpty(VideoBean.url)) {
                    return TYPE_PHOTO_ITEM;
                }
                return TYPE_ITEM;
            }
        });
        this.VideoBeans = datas;
        this.mContext = context;
    }

    public VideoListAdapter(Context context, MultiItemTypeSupport<VideoBean> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    @Override
    public void convert(ViewHolderHelper helper, VideoBean VideoBean) {
        if (helper.getLayoutId() == R.layout.item_news) {
            setItemValues(helper, VideoBean, getPosition(helper));
        } else if (helper.getLayoutId() == R.layout.item_news_photo) {
            setPhotoItemValues(helper, VideoBean, getPosition(helper));
        }
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
        String imgSrc = VideoBean.url;

        holder.setText(R.id.news_summary_title_tv, title);
        holder.setText(R.id.news_summary_ptime_tv, rate);
        holder.setImageUrl(R.id.news_summary_photo, imgSrc);
        holder.setOnClickListener(R.id.ll_root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                VideoBeanActivity.startAction(mContext,holder.getView(R.id.news_summary_photo_iv),VideoBean.getPostid(),VideoBean.getImgsrc());
            }
        });
    }


    /**
     * 图文样式
     *
     * @param holder
     * @param position
     */
    private void setPhotoItemValues(ViewHolderHelper holder, final VideoBean VideoBean, int position) {
        String title = VideoBean.title;
        String rate = VideoBean.rate;
        String imgSrc = VideoBean.url;


        holder.setText(R.id.news_summary_title_tv, title);
        holder.setText(R.id.news_summary_ptime_tv, rate);
        holder.setImageUrl(R.id.news_summary_photo, imgSrc);
        holder.setOnClickListener(R.id.ll_root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NewsPhotoDetailActivity.startAction(mContext,getPhotoDetail(VideoBean));
            }
        });
    }

}
