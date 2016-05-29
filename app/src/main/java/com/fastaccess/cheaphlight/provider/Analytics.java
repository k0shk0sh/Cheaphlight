package com.fastaccess.cheaphlight.provider;

import android.os.Bundle;

import com.fastaccess.cheaphlight.App;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Kosh on 29 May 2016, 1:25 AM
 */

public class Analytics {
    public static void logEvent() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, getFileName());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, getCurrentMethodName());
        App.getInstance().getFirebaseAnalytics().logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public static String getCurrentMethodName() {
        try {
            return Thread.currentThread().getStackTrace()[4].getMethodName() + "()";
        } catch (Exception ignored) {}
        return Analytics.class.getSimpleName();
    }

    public static String getFileName() {
        try {
            return Thread.currentThread().getStackTrace()[4].getFileName();
        } catch (Exception ignored) {}
        return Analytics.class.getSimpleName();
    }
}
