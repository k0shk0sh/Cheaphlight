package com.fastaccess.cheaphlight.ui.modules.setup.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.widget.AdapterView;

import com.fastaccess.cheaphlight.data.model.CountriesModel;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.BaseViewHolder;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kosh on 29 May 2016, 1:51 AM
 */

public interface MyFavCountryMvp {

    interface View {

        void onCountryTextError(@StringRes int resId);

        void onAddCountry(@Nullable CountriesModel country);

        void insertCountry(@NonNull CountriesModel country);

        void onRemove(int position);

        void onShowProgress();

        void onHideProgress();

        void onSuccess();

        void onError(@NonNull String msg);

        void onError(@StringRes int resId);
    }

    interface Presenter extends DatabaseReference.CompletionListener,
            BaseViewHolder.OnItemClickListener<CountriesModel>, AdapterView.OnItemClickListener {
        void onSelectedCountry(ArrayList<CountriesModel> myFavList, List<CountriesModel> countries, @Nullable CountriesModel country);

        void onSubmit(@NonNull List<CountriesModel> selectedCountries);

        boolean canAddCountry(@NonNull List<CountriesModel> myFavs);
    }
}
