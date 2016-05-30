package com.fastaccess.cheaphlight.ui.modules.setup.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.fastaccess.cheaphlight.App;
import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.data.model.CountriesModel;
import com.fastaccess.cheaphlight.data.model.UserModel;
import com.fastaccess.cheaphlight.helper.InputHelper;
import com.fastaccess.cheaphlight.helper.PrefConstance;
import com.fastaccess.cheaphlight.helper.PrefHelper;
import com.fastaccess.cheaphlight.provider.Analytics;
import com.fastaccess.cheaphlight.ui.base.mvp.presenter.BasePresenter;
import com.fastaccess.cheaphlight.ui.modules.setup.model.MyCountryMvp;
import com.fastaccess.cheaphlight.ui.widgets.FontAutoCompleteEditText;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.List;
import java.util.Map;

import rx.functions.Action1;

import static com.fastaccess.cheaphlight.data.model.CountriesModel.getCountries;

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

    @Override
    public void onFillCountries(@NonNull FontAutoCompleteEditText country, @NonNull final ArrayAdapter adapter, @NonNull final List<CountriesModel>
            countries) {
        App.getInstance().getSubscriber()
                .add(getCountries()
                        .doOnNext(new Action1<List<CountriesModel>>() {
                            @Override public void call(List<CountriesModel> models) {
                                countries.addAll(models);
                                adapter.notifyDataSetChanged();
                            }
                        }).subscribe());
    }

    @Override public void onSelectedCountry(List<CountriesModel> countries, @Nullable CountriesModel country) {
        boolean isEmpty = InputHelper.isEmpty(country);
        if (!isEmpty) {
            FirebaseUser user = getUser();
            if (user == null) {
                getView().onError(R.string.please_login);
                return;
            }
            if (!countries.contains(country)) {
                getView().onCountryTextError(R.string.country_selection_error);
                return;
            }
            getView().onShowProgress();
            Map<String, Object> objectMap = UserModel.getMyCountry(country);
            UserModel.getReference(getFirebaseDatabase(), user)
                    .updateChildren(objectMap, this);
            Analytics.logEvent();
        } else {
            getView().onCountryTextError(R.string.country_selection_error);
        }
    }

    @Override public void onGetMyCountry() {
        FirebaseUser user = getUser();
        if (user != null) {
            UserModel.getReference(getFirebaseDatabase(), user).child("myCountry").addValueEventListener(this);
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

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CountriesModel model = (CountriesModel) parent.getItemAtPosition(position);
        getView().onSelectedAtPosition(model);
    }

    @Override public void onDataChange(DataSnapshot dataSnapshot) {
        getView().onHideProgress();
        if (dataSnapshot == null || dataSnapshot.getValue() == null) return;
        CountriesModel model = App.gson().fromJson(dataSnapshot.getValue().toString(), CountriesModel.class);
        if (model != null) {
            PrefHelper.set(PrefConstance.MY_COUNTRY, model);
            getView().onMyCountryReceived(model);
        }
    }

    @Override public void onCancelled(DatabaseError databaseError) {
        getView().onHideProgress();
        getView().onError(databaseError.getMessage());
    }

}
