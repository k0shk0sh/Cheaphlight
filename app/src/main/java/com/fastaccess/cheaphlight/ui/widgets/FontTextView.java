package com.fastaccess.cheaphlight.ui.widgets;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.fastaccess.cheaphlight.helper.TypeFaceHelper;
import com.fastaccess.cheaphlight.helper.ViewHelper;


/**
 * Created by Kosh on 8/18/2015. copyrights are reserved
 */
public class FontTextView extends AppCompatTextView {

    public FontTextView(Context context) {
        super(context);
        init();
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (isInEditMode()) return;
        TypeFaceHelper.applyTypeface(this);
    }

    public void setTextColor(@ColorRes int normalColor, @ColorRes int pressedColor) {
        int nColor = getResources().getColor(normalColor);
        int pColor = getResources().getColor(pressedColor);
        setTextColor(ViewHelper.textSelector(nColor, pColor));
    }
}
