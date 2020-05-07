package com.hl.lib.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hl.lib.common.R;


/**
 * Description : 图片加载工具类 使用glide框架封装
 */
@GlideModule
public class ImageLoaderUtils{

    @SuppressLint("CheckResult")
    public  static RequestOptions options() {
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_empty_picture)
                .placeholder(R.drawable.ic_image_loading)
                .skipMemoryCache(true)
                .centerCrop();
        return options;
    }

    @SuppressLint("CheckResult")
    public  static RequestOptions options(int placeholder, int error) {
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(error)
                .placeholder(placeholder)
                .skipMemoryCache(false)
                .centerCrop();
        return options;
    }

    /**
     * 圆形图片
     * @param context
     * @param error
     * @return
     */
    @SuppressLint("CheckResult")
    public  static RequestOptions options(Context context, int error) {
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(error)
                .placeholder(R.drawable.ic_image_loading)
                .skipMemoryCache(false)
                .centerCrop()
                .transform(new GlideRoundTransformUtil(context));
        return options;
    }

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).apply(options(placeholder,error)).into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).apply(options()).into(imageView);
    }


    public static void displaySmallPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).apply(options())
                .thumbnail(0.5f)
                .into(imageView);
    }
    public static void displayBigPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }

    }

    public static void displayRound(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).apply(options(context,R.drawable.toux2)).into(imageView);
    }
    public static void displayRound(Context context, ImageView imageView, int resId) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(resId).apply(options(context,R.drawable.toux2)).into(imageView);
    }

}
