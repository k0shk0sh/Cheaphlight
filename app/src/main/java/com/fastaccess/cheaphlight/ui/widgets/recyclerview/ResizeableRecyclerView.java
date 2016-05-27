package com.fastaccess.cheaphlight.ui.widgets.recyclerview;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Kosh on 27/03/16 2:22 AM.
 */
public class ResizeableRecyclerView extends DynamicRecyclerView {
    private int iconSize;

    public ResizeableRecyclerView(Context context) {
        super(context);
    }

    public ResizeableRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResizeableRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
//        int width = MeasureSpec.getSize(widthSpec);
//        if (iconSize == 0) {
//            iconSize = ViewHelper.toDp(getContext(), 600);
//            Logger.e(iconSize);
//        }
//        if (width != 0) {
//            int spanCount = Math.max(1, getMeasuredWidth() / iconSize);
//            if (spanCount > 0) {
//                Logger.e(spanCount);
//                if (getLayoutManager() instanceof GridLayoutManager) {
//                    ((GridLayoutManager) getLayoutManager()).setSpanCount(spanCount);
//                    getLayoutManager().requestLayout();
//                }
//            }
//        }
    }

    public int getIconSize() {
        return iconSize;
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
        requestLayout();
    }
}
