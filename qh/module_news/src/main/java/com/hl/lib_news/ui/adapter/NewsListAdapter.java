package com.hl.lib_news.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.hl.banner.indicator.Indicator;
import com.hl.banner.indicator.RectangleIndicator;
import com.hl.banner.indicator.RoundLinesIndicator;
import com.hl.banner.util.BannerUtils;
import com.hl.banner.view.Banner;
import com.hl.lib_news.R;
import com.hl.lib_news.ui.bean.DataBean;
import com.hl.lib_news.ui.bean.NewsDetail;

import org.w3c.dom.Text;

import java.util.List;

public class NewsListAdapter extends MultiItemRecycleViewAdapter<NewsDetail> {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_PHOTO_ITEM = 1;
    public static final int TYPE_BANNER = 3;
    List<NewsDetail> newsDetails;
    Context mContext;

    public NewsListAdapter(Context context, final List<NewsDetail> datas) {
        super(context, datas, new MultiItemTypeSupport<NewsDetail>() {
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
            public int getItemViewType(int position, NewsDetail newsDetail) {
                if (newsDetail.banners != null) {
                    return TYPE_BANNER;
                } else if (!TextUtils.isEmpty(newsDetail.src)) {
                    return TYPE_PHOTO_ITEM;
                }
                return TYPE_ITEM;
            }
        });
        this.newsDetails = datas;
        this.mContext = context;
    }

    public NewsListAdapter(Context context, MultiItemTypeSupport<NewsDetail> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    @Override
    public void convert(ViewHolderHelper helper, NewsDetail newsDetail) {
        if (helper.getLayoutId() == R.layout.item_news) {
            setItemValues(helper, newsDetail, getPosition(helper));
        } else if (helper.getLayoutId() == R.layout.item_news_photo) {
            setPhotoItemValues(helper, newsDetail, getPosition(helper));
        } else if (helper.getLayoutId() == R.layout.item_banner) {
            setImageValues(helper, newsDetail, getPosition(helper));
        }
    }


    /**
     * 首页轮播图
     *
     * @param holder
     * @param newsDetail
     * @param position
     */
    private void setImageValues(final ViewHolderHelper holder, final NewsDetail newsDetail, final int position) {
        List<DataBean> banners = newsDetail.banners;
        Banner banner = holder.getView(R.id.banner);
        Indicator indicator = holder.getView(R.id.indicator);
        banner.setAdapter(new ImageAdapter(newsDetail.banners));
        banner.setBannerRound(BannerUtils.dp2px(5));
        banner.setIndicator(new RectangleIndicator(mContext));
        banner.setIndicatorSelectedWidth((int) BannerUtils.dp2px(15));

    }

    /**
     * 普通样式
     *
     * @param holder
     * @param newsDetail
     * @param position
     */
    private void setItemValues(final ViewHolderHelper holder, final NewsDetail newsDetail, final int position) {
        String title = newsDetail.title;
        if (title == null) {
            title = "头条";
        }
        String ptime = newsDetail.time;
        String imgSrc = newsDetail.pic;

        holder.setText(R.id.news_summary_title_tv, title);
        holder.setText(R.id.news_summary_ptime_tv, ptime);
        holder.setImageUrl(R.id.news_summary_photo, imgSrc);
        holder.setOnClickListener(R.id.ll_root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NewsDetailActivity.startAction(mContext,holder.getView(R.id.news_summary_photo_iv),newsDetail.getPostid(),newsDetail.getImgsrc());
            }
        });
    }


    /**
     * 图文样式
     *
     * @param holder
     * @param position
     */
    private void setPhotoItemValues(ViewHolderHelper holder, final NewsDetail newsDetail, int position) {
        String title = newsDetail.title;
        String ptime = newsDetail.time;
        String imgSrc = newsDetail.pic;

        holder.setText(R.id.news_summary_title_tv, title);
        holder.setText(R.id.news_summary_ptime_tv, ptime);
        holder.setImageUrl(R.id.news_summary_photo, imgSrc);
        holder.setOnClickListener(R.id.ll_root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NewsPhotoDetailActivity.startAction(mContext,getPhotoDetail(newsDetail));
            }
        });
    }

}
