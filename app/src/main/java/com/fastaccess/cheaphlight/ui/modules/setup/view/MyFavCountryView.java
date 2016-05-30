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
import com.fastaccess.cheaphlight.data.model.CountriesModel;
import com.fastaccess.cheaphlight.helper.AnimHelper;
import com.fastaccess.cheaphlight.ui.base.BaseFragment;
import com.fastaccess.cheaphlight.ui.modules.setup.adapter.MyCountriesAdapter;
import com.fastaccess.cheaphlight.ui.modules.setup.model.MyFavCountryMvp;
import com.fastaccess.cheaphlight.ui.modules.setup.model.SetupPagerMvp;
import com.fastaccess.cheaphlight.ui.modules.setup.presenter.MyFavCountryPresenter;
import com.fastaccess.cheaphlight.ui.widgets.FontAutoCompleteEditText;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.ResizeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;

/**
 * Created by Kosh on 27 May 2016, 7:54 PM
 */

public class MyFavCountryView extends BaseFragment implements MyFavCountryMvp.View {
    @BindView(R.id.done) ImageView done;
    @BindView(R.id.recycler) ResizeableRecyclerView recycler;
    @BindView(R.id.country) FontAutoCompleteEditText country;
    @BindView(R.id.progress) View progress;
    @State ArrayList<CountriesModel> myFavList = new ArrayList<>();
    private MyCountriesAdapter adapter;
    private List<CountriesModel> countries = new ArrayList<>();
    private SetupPagerMvp.View view;
    private MyFavCountryPresenter presenter;

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof SetupPagerMvp.View)) {
            throw new IllegalArgumentException("Activity must implement SetupPagerMvp.View");
        }
        view = (SetupPagerMvp.View) context;
    }

    @Override public void onDetach() {
        super.onDetach();
        getPresenter().onDestroy();
        view = null;
    }

    @Override protected int fragmentLayout() {
        return R.layout.my_fav_country_page;
    }

    @Override protected boolean isRetainRequired() {
        return false;
    }

    @Override protected void onFragmentCreated(final View view, @Nullable Bundle savedInstanceState) {
        adapter = new MyCountriesAdapter(myFavList, getPresenter());
        recycler.setAdapter(adapter);
        ArrayAdapter<CountriesModel> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout
                .simple_dropdown_item_1line, countries);
        country.setAdapter(arrayAdapter);
        getPresenter().onFillCountries(country, arrayAdapter, countries);
        country.setOnItemClickListener(getPresenter());
    }

    @OnClick(R.id.done) public void onClick() {
        getPresenter().onSubmit(myFavList);
    }

    @Override public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getPresenter().onGetMyFavCountries();
        }
    }

    @Override public void onCountryTextError(@StringRes int resId) {
        country.setError(resId != 0 ? getString(resId) : null);
    }

    @Override public void onAddCountry(@Nullable CountriesModel country) {
        getPresenter().onSelectedCountry(myFavList, countries, country);
    }

    @Override public void insertCountry(@NonNull CountriesModel country) {
        adapter.addItem(country);
        this.country.setText("");
    }

    @Override public void onReceivedMyFavCountries(@NonNull List<CountriesModel> models) {
        adapter.insertItems(models);
    }

    @Override public void onRemove(int position) {
        adapter.removeItem(position);
    }

    @Override public void onShowProgress() {
        AnimHelper.animateVisibility(false, done);
        AnimHelper.animateVisibility(true, progress);
    }

    @Override public void onHideProgress() {
        AnimHelper.animateVisibility(false, progress);
        AnimHelper.animateVisibility(true, done);
    }

    @Override public void onSuccess() {
        view.onFinishActivity();
    }

    @Override public void onError(@NonNull String msg) {
        showMessage(msg);
    }

    @Override public void onError(@StringRes int resId) {
        showMessage(resId);
    }

    public static Fragment getInstance() {
        return new MyFavCountryView();
    }

    public MyFavCountryPresenter getPresenter() {
        if (presenter == null) presenter = MyFavCountryPresenter.with(this);
        return presenter;
    }
}
