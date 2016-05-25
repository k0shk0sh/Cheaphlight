package com.fastaccess.cheaphlight.ui.widgets.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Kosh on 17 May 2016, 7:13 PM
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public interface OnItemClickListener<T> {
        void onItemClick(int position, View v, T item);

        void onItemLongClick(int position, View v, T item);
    }

    private BaseRecyclerAdapter adapter;

    public BaseViewHolder(View itemView, BaseRecyclerAdapter adapter) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.adapter = adapter;
    }

    @SuppressWarnings("unchecked") @Override public void onClick(View v) {
        int position = getAdapterPosition();
        if (adapter.listener != null) {
            adapter.listener.onItemClick(position, v, adapter.getItem(position));
        }
    }

    @SuppressWarnings("unchecked") @Override public boolean onLongClick(View v) {
        int position = getAdapterPosition();
        if (adapter.listener != null) {
            adapter.listener.onItemLongClick(position, v, adapter.getItem(position));
        }
        return true;
    }
}
