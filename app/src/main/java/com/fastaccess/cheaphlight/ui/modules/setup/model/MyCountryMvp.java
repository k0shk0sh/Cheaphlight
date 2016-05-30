package com.fastaccess.cheaphlight.ui.modules.setup.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.fastaccess.cheaphlight.data.model.CountriesModel;
import com.fastaccess.cheaphlight.ui.widgets.FontAutoCompleteEditText;

import java.util.List;

/**
 * Created by Kosh on 29 May 2016, 1:51 AM
 */

public interface MyCountryMvp {

    interface View {

        void onCountryTextError(@StringRes int resId);

        void onSelectedAtPosition(@NonNull CountriesModel selectionState);

        void onMyCountryReceived(@NonNull CountriesModel model);

        void onShowProgress();

        void onHideProgress();

        void onSuccess();

        void onError(@NonNull String msg);

        void onError(@StringRes int resId);
    }

    interface Presenter extends AdapterView.OnItemClickListener {

        void onSelectedCountry(List<CountriesModel> countries, @Nullable CountriesModel country);

        void onFillCountries(@NonNull FontAutoCompleteEditText country, @NonNull final ArrayAdapter adapter, @NonNull final List<CountriesModel>
                countries);

        void onGetMyCountry();
    }
}
