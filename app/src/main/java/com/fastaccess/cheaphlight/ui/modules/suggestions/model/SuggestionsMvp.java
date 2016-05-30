package com.fastaccess.cheaphlight.ui.modules.suggestions.model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;
import java.util.Objects;

/**
 * Created by Kosh on 29 May 2016, 7:51 PM
 */

public interface SuggestionsMvp {

    interface View {
        void setTextViewMessage(@StringRes int resId);

        void onHideProgress();

        void onShowProgress();

        void onError(@StringRes int resId);

        void onError(@NonNull String msg);

        void onAppendSuggestions(List<Objects> objectsList);

    }

    interface Presenter extends SwipeRefreshLayout.OnRefreshListener {

        void login(@NonNull Activity activity);

        void onReload();

    }
}
