package com.fastaccess.cheaphlight.ui.modules.setup.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.AnimHelper;
import com.fastaccess.cheaphlight.helper.InputHelper;
import com.fastaccess.cheaphlight.provider.analytics.Analytics;
import com.fastaccess.cheaphlight.ui.base.BaseFragment;
import com.fastaccess.cheaphlight.ui.modules.setup.model.MyCountryMvp;
import com.fastaccess.cheaphlight.ui.modules.setup.model.SetupPagerMvp;
import com.fastaccess.cheaphlight.ui.modules.setup.presenter.MyCountryPresenter;
import com.fastaccess.cheaphlight.ui.widgets.FontAutoCompleteEditText;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kosh on 27 May 2016, 7:54 PM
 */

public class MyCountryView extends BaseFragment implements MyCountryMvp.View {
    @BindView(R.id.country) FontAutoCompleteEditText country;
    @BindView(R.id.progress) View progress;
    @BindView(R.id.next) ImageView next;
    private List<String> countries;
    private SetupPagerMvp.View view;
    private MyCountryPresenter presenter;

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof SetupPagerMvp.View)) {
            throw new IllegalArgumentException("Activity must implement SetupPagerMvp.View");
        }
        view = (SetupPagerMvp.View) context;
    }

    @Override public void onDetach() {
        super.onDetach();
        view = null;
    }

    @Override protected int fragmentLayout() {
        return R.layout.my_country_page;
    }

    @Override protected boolean isRetainRequired() {
        return false;
    }

    @Override protected void onFragmentCreated(View view, @Nullable Bundle savedInstanceState) {
        countries = Arrays.asList(getResources().getStringArray(R.array.countries_list_human));
        country.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, countries));
    }

    @OnClick(R.id.next) public void onClick(View view) {
        Analytics.logEvent(view.getId(), view.getTag(), view.getClass());
        getPresenter().onSelectedCountry(countries, InputHelper.toString(country));
    }

    @Override public void onCountryTextError(@StringRes int resId) {
        country.setError(resId != 0 ? getString(resId) : null);
    }

    @Override public void onShowProgress() {
        AnimHelper.animateVisibityWithTranslate(false, next);
        AnimHelper.animateVisibityWithTranslate(true, progress);
    }

    @Override public void onHideProgress() {
        AnimHelper.animateVisibityWithTranslate(false, progress);
        AnimHelper.animateVisibityWithTranslate(true, next);
    }

    @Override public void onSuccess() {
        view.onNext();
    }

    @Override public void onError(@NonNull String msg) {
        showMessage(msg);
    }

    @Override public void onError(@StringRes int resId) {
        showMessage(resId);
    }

    public static Fragment getInstance() {
        return new MyCountryView();
    }

    public MyCountryPresenter getPresenter() {
        if (presenter == null) {
            presenter = MyCountryPresenter.with(this);
        }
        return presenter;
    }
}
