package com.fastaccess.cheaphlight.ui.modules.setup.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.fastaccess.cheaphlight.App;
import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.data.model.CountriesModel;
import com.fastaccess.cheaphlight.data.model.UserModel;
import com.fastaccess.cheaphlight.helper.FirebaseHelper;
import com.fastaccess.cheaphlight.helper.InputHelper;
import com.fastaccess.cheaphlight.helper.Logger;
import com.fastaccess.cheaphlight.helper.PrefConstance;
import com.fastaccess.cheaphlight.helper.PrefHelper;
import com.fastaccess.cheaphlight.provider.Analytics;
import com.fastaccess.cheaphlight.ui.base.mvp.presenter.BasePresenter;
import com.fastaccess.cheaphlight.ui.modules.setup.model.MyFavCountryMvp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override public void onSelectedCountry(ArrayList<CountriesModel> myFavList, List<CountriesModel> countries, @Nullable CountriesModel country) {
        boolean isEmpty = InputHelper.isEmpty(country);
        if (!isEmpty) {
            if (countries.contains(country) && !myFavList.contains(country)) {
                if (canAddCountry(myFavList)) {
                    getView().insertCountry(country);
                } else {
                    getView().onError(R.string.favorite_maximum_error);
                }
            } else {
                getView().onError(R.string.fav_country_selection_error);
            }
        } else {
            getView().onCountryTextError(R.string.fav_country_selection_error);
        }
    }

    @Override public void onSubmit(@NonNull List<CountriesModel> selectedCountries) {
        Analytics.logEvent();
        if (selectedCountries.isEmpty()) {
            getView().onError(R.string.fav_country_selection_error);
            return;
        }
        if (canAddCountry(selectedCountries)) {
            FirebaseUser user = getFirebaseAuth().getCurrentUser();
            if (user == null || user.isAnonymous()) {
                PrefHelper.set(PrefConstance.MY_FAV_COUNTRIES, App.gson().toJson(selectedCountries));
                getView().onSuccess();
                return;
            }
            getView().onShowProgress();
            Map<String, Object> objectMap = UserModel.getFavMyCountries(selectedCountries);
            UserModel.getReference(getFirebaseDatabase(), user)
                    .updateChildren(objectMap, this);
        } else {
            getView().onError(R.string.favorite_maximum_error);
        }
    }

    @Override public boolean canAddCountry(@NonNull List<CountriesModel> myFavs) {
        return myFavs.size() <= 5;
    }

    @Override public void onGetMyFavCountries() {
        FirebaseUser user = getUser();
        if (user != null) {
            UserModel.getReference(getFirebaseDatabase(), user).child("myFavCountries").addValueEventListener(this);
            getView().onShowProgress();
        }
    }

    @Override public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        getView().onHideProgress();
        if (databaseError != null) {
            getView().onError(databaseError.getMessage());
        } else {
            getView().onSuccess();
        }
    }

    @Override public void onItemClick(int position, View v, CountriesModel item) {
        getView().onRemove(position);
    }

    @Override public void onItemLongClick(int position, View v, CountriesModel item) {
        getView().onRemove(position);
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CountriesModel object = (CountriesModel) parent.getAdapter().getItem(position);
        getView().onAddCountry(object);
    }

    @Override public void onDataChange(DataSnapshot dataSnapshot) {
        getView().onHideProgress();
        if (dataSnapshot == null || dataSnapshot.getValue() == null) return;
        Logger.e(dataSnapshot.getValue());
        List<CountriesModel> models = FirebaseHelper.getList(dataSnapshot.getValue().toString(), CountriesModel[].class);
        if (!models.isEmpty()) getView().onReceivedMyFavCountries(models);
    }

    @Override public void onCancelled(DatabaseError databaseError) {
        getView().onHideProgress();
        getView().onError(databaseError.getMessage());
    }
}
