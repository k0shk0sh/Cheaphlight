package com.fastaccess.cheaphlight.ui.modules.suggestions.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.ui.base.BaseFragment;
import com.fastaccess.cheaphlight.ui.modules.suggestions.model.SuggestionsMvp;
import com.fastaccess.cheaphlight.ui.widgets.FontButton;
import com.fastaccess.cheaphlight.ui.widgets.FontTextView;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.DynamicRecyclerView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kosh on 29 May 2016, 7:01 PM
 */

public class SuggestionsView extends BaseFragment implements SuggestionsMvp.View {
    @BindView(R.id.recycler) DynamicRecyclerView recycler;
    @BindView(R.id.refresh) SwipeRefreshLayout refresh;
    @BindView(R.id.layout_content) View layoutContent;
    @BindView(R.id.image_message) ImageView imageMessage;
    @BindView(R.id.text_message) FontTextView textMessage;
    @BindView(R.id.reload_button) FontButton buttonMessage;
    @BindView(R.id.empty_layout) View emptyLayout;
    @BindView(R.id.progress_layout) View progressLayout;

    @OnClick(R.id.reload_button) void onReload() {
        //Reload
    }

    @Override protected boolean isRetainRequired() {
        return true;
    }

    @Override protected int fragmentLayout() {
        return R.layout.flights_list_layout;
    }

    @Override protected void onFragmentCreated(View view, @Nullable Bundle savedInstanceState) {
        recycler.setEmptyView(emptyLayout, refresh);
    }

    public static SuggestionsView getInstance() {
        return new SuggestionsView();
    }

    @Override public void setTextViewMessage(@StringRes int resId) {
        textMessage.setText(resId);
    }

    @Override public void onHideProgress() {
        recycler.hideProgress(progressLayout);
    }

    @Override public void onShowProgress() {
        recycler.showProgress(progressLayout);
    }

    @Override public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override public void onError(@NonNull String msg) {
        showMessage(msg);
    }

    @Override public void onAppendSuggestions(List<Objects> objectsList) {

    }
}
