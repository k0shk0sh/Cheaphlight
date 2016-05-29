package com.fastaccess.cheaphlight.ui.widgets.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.fastaccess.cheaphlight.helper.AnimHelper;
import com.fastaccess.cheaphlight.helper.Logger;

/**
 * Created by Kosh on 9/24/2015. copyrights are reserved
 * <p>
 * recyclerview which will show/show itself base on adapter
 */
public class DynamicRecyclerView extends RecyclerView {

    private View emptyView;
    private View parentView;

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
                show(false);
            } else {
                show(true);
            }
        } else {
            show(false);
        }

    }

    @Override public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
            observer.onChanged();
        }
    }

    private void show(boolean show) {
        if (parentView == null) {
            AnimHelper.animateVisibityWithTranslate(show, this);
        } else {
            AnimHelper.animateVisibityWithTranslate(show, parentView);
        }
        AnimHelper.animateVisibityWithTranslate(!show, emptyView);
        Logger.e(show);
    }

    public void setEmptyView(View emptyView, @Nullable View parentView) {
        this.emptyView = emptyView;
        this.parentView = parentView;
        showEmptyView();
    }

    public void hideProgress(View view) {
        if (!view.isShown()) return;
        AnimHelper.animateVisibityWithTranslate(false, view);
    }

    public void showProgress(View view) {
        if (view.isShown()) return;
        AnimHelper.animateVisibityWithTranslate(true, view);
    }
}
