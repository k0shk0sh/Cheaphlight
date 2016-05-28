package com.fastaccess.cheaphlight.ui.widgets.recyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Kosh on 17 May 2016, 7:10 PM
 */
public abstract class BaseRecyclerAdapter<T, VH extends BaseViewHolder,
        P extends BaseViewHolder.OnItemClickListener<T>> extends RecyclerView.Adapter<VH> {

    @NonNull private List<T> data;
    @Nullable public P listener;

    public BaseRecyclerAdapter(@NonNull List<T> data) {
        this(data, null);
    }

    public BaseRecyclerAdapter(@NonNull List<T> data, @Nullable P listener) {
        this.data = data;
        this.listener = listener;
    }

    protected abstract VH viewHolder(ViewGroup parent, int viewType);

    @NonNull public List<T> getData() {
        return data;
    }

    public T getItem(int position) {
        return data.get(position);
    }

    @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewHolder(parent, viewType);
    }

    @Override public int getItemCount() {
        return data.size();
    }

    public void insertItems(List<T> items) {
        data.clear();
        notifyItemRangeRemoved(0, getItemCount());
        addItems(items);
    }

    public void addItem(T item) {
        addItem(item, getItemCount());
    }

    public void addItem(T item, int position) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public void addItems(List<T> items) {
        int prevSize = data.size();
        data.addAll(items);
        notifyItemRangeInserted(prevSize, Math.abs(data.size() - prevSize));
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(T item) {
        int position = data.indexOf(item);
        removeItem(position);
    }

    public void removeItems(List<T> items) {
        int prevSize = data.size();
        data.removeAll(items);
        notifyItemRangeRemoved(prevSize, Math.abs(data.size() - prevSize));
    }

    public void clear() {
        data.clear();
        notifyItemRangeRemoved(0, getItemCount());
    }
}
