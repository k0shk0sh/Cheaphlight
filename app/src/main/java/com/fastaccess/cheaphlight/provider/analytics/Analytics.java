package com.fastaccess.cheaphlight.provider.analytics;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.fastaccess.cheaphlight.App;
import com.fastaccess.cheaphlight.helper.InputHelper;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Kosh on 29 May 2016, 1:25 AM
 */

public class Analytics {
    public static void logEvent(@IdRes int resId, @Nullable Object name, @NonNull Class<? extends View> clazz) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(resId));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, InputHelper.isEmpty(name) ? "NOT-PROVIDED" : name.toString());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, clazz.getSimpleName());
        App.getInstance().getFirebaseAnalytics().logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public static void logActionEvent(@NonNull Object object, @NonNull String name) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, object.toString());
        App.getInstance().getFirebaseAnalytics().logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public static void logActionEvent(@NonNull String name) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        App.getInstance().getFirebaseAnalytics().logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public static String getCurrentMethodName() {
        try {
            return Thread.currentThread().getStackTrace()[4].getMethodName() + "()";
        } catch (Exception ignored) {}
        return Analytics.class.getSimpleName();
    }
}
