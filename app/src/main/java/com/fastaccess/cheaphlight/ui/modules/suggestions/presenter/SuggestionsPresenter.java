package com.fastaccess.cheaphlight.ui.modules.suggestions.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.fastaccess.cheaphlight.App;
import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.AppHelper;
import com.fastaccess.cheaphlight.ui.base.mvp.presenter.BasePresenter;
import com.fastaccess.cheaphlight.ui.modules.security.view.LoginView;
import com.fastaccess.cheaphlight.ui.modules.suggestions.model.SuggestionsMvp;

/**
 * Created by Kosh on 29 May 2016, 7:55 PM
 */

public class SuggestionsPresenter extends BasePresenter<SuggestionsMvp.View> implements SuggestionsMvp.Presenter {

    protected SuggestionsPresenter(SuggestionsMvp.View view) {
        super(view);
    }

    public static SuggestionsPresenter with(SuggestionsMvp.View view) {
        return new SuggestionsPresenter(view);
    }

    @Override public void login(@NonNull Activity activity) {
        activity.startActivity(new Intent(activity, LoginView.class));
        activity.finish();
    }

    @Override public void onReload() {
        if (getUser() == null) {
            getView().setTextViewMessage(R.string.login_for_suggestions);
            return;
        }
        if (!AppHelper.isOnline(App.getInstance())) {
            getView().setTextViewMessage(R.string.network_error);
            return;
        }
        getView().onShowProgress();
    }

    @Override public void onRefresh() {
        onReload();
    }
}
