package com.fastaccess.cheaphlight.ui.modules.setup.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.fastaccess.cheaphlight.App;
import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.InputHelper;
import com.fastaccess.cheaphlight.helper.PrefConstance;
import com.fastaccess.cheaphlight.helper.PrefHelper;
import com.fastaccess.cheaphlight.ui.base.mvp.presenter.BasePresenter;
import com.fastaccess.cheaphlight.ui.modules.setup.model.MyFavCountryMvp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kosh on 29 May 2016, 1:56 AM
 */

public class MyFavCountryPresenter extends BasePresenter<MyFavCountryMvp.View> implements MyFavCountryMvp.Presenter {

    protected MyFavCountryPresenter(MyFavCountryMvp.View view) {
        super(view);
    }

    public static MyFavCountryPresenter with(MyFavCountryMvp.View view) {
        return new MyFavCountryPresenter(view);
    }

    @Override public void onSelectedCountry(ArrayList<String> myFavList, List<String> countries, @Nullable Object country) {
        boolean isEmpty = InputHelper.isEmpty(country);
        if (!isEmpty) {
            if (countries.contains(country.toString())) {
                if (canAddCountry(myFavList)) {
                    getView().insertCountry(country.toString());
                } else {
                    getView().onError(R.string.favorite_maximum_error);
                }
            } else {
                getView().onError(R.string.fav_country_selection_error);
            }
        } else {
            getView().onCountryTextError(R.string.country_selection_error);
        }
    }

    @Override public void onSubmit(@NonNull List<String> selectedCountries) {
        if (canAddCountry(selectedCountries)) {
            FirebaseUser user = getApp().getFirebaseAuth().getCurrentUser();
            if (user == null || user.isAnonymous()) {
                PrefHelper.set(PrefConstance.MY_FAV_COUNTRIES, App.gson().toJson(selectedCountries));
                getView().onSuccess();
                return;
            }
            getView().onShowProgress();
            getApp().getFirebaseDatabase().getReference("users")
                    .child(user.getUid())
                    .child("my_fav_countries")
                    .setValue(selectedCountries, this);
        } else {
            getView().onError(R.string.favorite_maximum_error);
        }
    }

    @Override public boolean canAddCountry(@NonNull List<String> myFavs) {
        return myFavs.size() <= 5;
    }

    @Override public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        getView().onHideProgress();
        if (databaseError != null) {
            getView().onError(databaseError.getMessage());
        } else {
            getView().onSuccess();
        }
    }

    @Override public void onItemClick(int position, View v, Object item) {
        getView().onRemove(position);
    }

    @Override public void onItemLongClick(int position, View v, Object item) {
        getView().onRemove(position);
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object object = parent.getAdapter().getItem(position);
        getView().onAddCountry(object.toString());
    }
}
