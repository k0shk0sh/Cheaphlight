package com.fastaccess.cheaphlight.ui.widgets.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Kosh on 9/24/2015. copyrights are reserved
 * <p>
 * recyclerview which will hide/show itself base on adapter
 */
public class DynamicRecyclerView extends RecyclerView {

    private View emptyView;

    private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            showEmptyView();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            showEmptyView();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            showEmptyView();
        }
    };

    public DynamicRecyclerView(Context context) {
        super(context);
    }

    public DynamicRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode()) return;
    }

    public void showEmptyView() {
        Adapter<?> adapter = getAdapter();
        if (adapter != null && emptyView != null) {
            if (adapter.getItemCount() == 0) {
                emptyView.setVisibility(VISIBLE);
                this.setVisibility(GONE);
            } else {
                emptyView.setVisibility(GONE);
                this.setVisibility(VISIBLE);
            }
        }

    }

    @Override public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
            observer.onChanged();
        }
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        showEmptyView();
    }

    public void hideProgress(View view) {
        if (!view.isShown()) return;
//        ViewHelper.animateVisibility(false, view);
    }

    public void showProgress(View view) {
        if (view.isShown()) return;
//        ViewHelper.animateVisibility(true, view);
    }
}
