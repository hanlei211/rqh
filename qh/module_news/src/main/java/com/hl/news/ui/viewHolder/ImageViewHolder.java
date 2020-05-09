package com.hl.news.ui.viewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imageView = (ImageView) itemView;
    }
}
