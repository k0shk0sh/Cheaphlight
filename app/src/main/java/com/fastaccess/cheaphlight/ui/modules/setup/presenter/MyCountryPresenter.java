package com.fastaccess.cheaphlight.ui.modules.setup.presenter;

import android.support.annotation.Nullable;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.InputHelper;
import com.fastaccess.cheaphlight.helper.PrefConstance;
import com.fastaccess.cheaphlight.helper.PrefHelper;
import com.fastaccess.cheaphlight.ui.base.mvp.presenter.BasePresenter;
import com.fastaccess.cheaphlight.ui.modules.setup.model.MyCountryMvp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by Kosh on 29 May 2016, 1:56 AM
 */

public class MyCountryPresenter extends BasePresenter<MyCountryMvp.View> implements MyCountryMvp.Presenter {

    protected MyCountryPresenter(MyCountryMvp.View view) {
        super(view);
    }

    public static MyCountryPresenter with(MyCountryMvp.View view) {
        return new MyCountryPresenter(view);
    }

    @Override public void onSelectedCountry(List<String> countries, @Nullable Object country) {
        boolean isEmpty = InputHelper.isEmpty(country);
        if (!isEmpty) {
            FirebaseUser user = getApp().getFirebaseAuth().getCurrentUser();
            String selectedCountry = country.toString();
            if (!countries.contains(selectedCountry)) {
                getView().onCountryTextError(R.string.country_selection_error);
                return;
            }
            if (user == null || user.isAnonymous()) {
                PrefHelper.set(PrefConstance.MY_COUNTRY, selectedCountry);
                getView().onSuccess();
                return;
            }
            getView().onShowProgress();
            getApp().getFirebaseDatabase().getReference("users")
                    .child(user.getUid())
                    .child("my_country").setValue(selectedCountry, this);
        } else {
            getView().onCountryTextError(R.string.country_selection_error);
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
}
