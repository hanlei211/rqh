package com.hl.lib.common.base;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<E, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context mContext;
    protected List<E> mList;
    protected OnItemClickListener mItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    public BaseAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layoutId = getLayoutId();
        View view = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false);
        return onCreateHolder(view);
    }


    //增加布局文件
    protected abstract int getLayoutId();

    //创建一个holder
    protected abstract VH onCreateHolder(View view);

    //绑定数据
    protected abstract void onBindData(VH viewHolder, E e, int position);

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        final E e = mList.get(position);
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(e, position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return mOnItemLongClickListener.onItemLongClick(e, position);
                }
            });
        }
        onBindData(holder, e, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<E> list) {
        if (list != null && list.size() > 0) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void refresh(List<E> list) {
        mList.clear();
        if (list != null && list.size() > 0) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void remove(int positon) {
        mList.remove(positon);
        notifyDataSetChanged();
    }

    public void remove(E e) {
        mList.remove(e);
        notifyDataSetChanged();
    }

    public void add(E e) {
        mList.add(e);
        notifyDataSetChanged();
    }

    public void addLast(E e) {
        add(e);
    }

    public void addFirst(E e) {
        mList.add(0, e);
        notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
    public List<E> getDataList() {
        return mList;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener<E> {
        void onItemClick(E e, int position);
    }
    public interface OnItemLongClickListener<E> {
        boolean onItemLongClick(E e, int postion);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public List<E> getListData(){
        return mList;
    }
}
