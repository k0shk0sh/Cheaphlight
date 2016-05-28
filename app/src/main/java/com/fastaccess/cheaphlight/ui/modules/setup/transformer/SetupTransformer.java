package com.fastaccess.cheaphlight.ui.modules.setup.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.Logger;

/**
 * Created by Kosh on 29 May 2016, 1:02 AM
 */

public class SetupTransformer implements ViewPager.PageTransformer {

    @Override public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        View card = view.findViewById(R.id.card);
        Logger.e(position);
        if (position >= -1) {
            if (position <= 0) {
                setTranslationX(view, -position);
                setTranslationX(card, pageWidth * position);
                setAlpha(card, 1 + position);
            } else if (position <= 1) {
                setTranslationX(view, position);
                setTranslationX(card, pageWidth * position);
                setAlpha(card, 1 - position);
            }
        }
    }

    private void setAlpha(View view, float value) {
        view.animate().alpha(value);
    }

    private void setTranslationX(View view, float value) {
        view.animate().translationX(value);
    }


}
