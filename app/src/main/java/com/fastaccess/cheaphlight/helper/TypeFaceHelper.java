package com.fastaccess.cheaphlight.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Kosh on 17/12/15 10:25 PM
 */
public class TypeFaceHelper {
    private static Typeface sTypeface;

    public static void generateTypeface(Context context) {
        sTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/app_font.ttf");
    }

    public static void applyTypeface(TextView textView) {
        if (sTypeface != null) {
            textView.setTypeface(sTypeface);
        }
    }
}
