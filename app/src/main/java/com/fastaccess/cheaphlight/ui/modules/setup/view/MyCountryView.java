package com.fastaccess.cheaphlight.ui.modules.setup.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.InputHelper;
import com.fastaccess.cheaphlight.helper.Logger;
import com.fastaccess.cheaphlight.ui.base.BaseFragment;
import com.fastaccess.cheaphlight.ui.widgets.FontAutoCompleteEditText;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kosh on 27 May 2016, 7:54 PM
 */

public class MyCountryView extends BaseFragment {
    @BindView(R.id.country) FontAutoCompleteEditText country;
    @BindView(R.id.next) ImageView next;
    private List<String> countries;

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

    @OnClick(R.id.next) public void onClick() {
        boolean isEmpty = InputHelper.isEmpty(country);
        country.setError(isEmpty ? getString(R.string.country_selection_error) : null);
        if (!isEmpty) {
            String selectedCountry = InputHelper.toString(country);
            Logger.e(countries.contains(selectedCountry));
        }
    }

    public static Fragment getInstance() {
        return new MyCountryView();
    }
}
