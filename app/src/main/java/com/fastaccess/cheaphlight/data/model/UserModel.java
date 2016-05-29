package com.fastaccess.cheaphlight.data.model;

import android.support.annotation.NonNull;

import com.fastaccess.cheaphlight.App;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kosh on 29 May 2016, 3:43 PM
 */

public class UserModel {

    private CountriesModel myCountry;
    private List<CountriesModel> myFavCountries;
    private String uid;
    private long lastLoginTime;

    public CountriesModel getMyCountry() {
        return myCountry;
    }

    public void setMyCountry(CountriesModel myCountry) {
        this.myCountry = myCountry;
    }

    public List<CountriesModel> getMyFavCountries() {
        return myFavCountries;
    }

    public void setMyFavCountries(List<CountriesModel> myFavCountries) {
        this.myFavCountries = myFavCountries;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public static Map<String, Object> updateLastLogin() {
        Map<String, Object> map = new HashMap<>();
        map.put("/lastLoginTime", new Date().getTime());
        return map;
    }

    public static Map<String, Object> getMyCountry(@NonNull CountriesModel model) {
        Map<String, Object> map = new HashMap<>();
        map.put("/myCountry/", App.gson().toJson(model));
        return map;
    }

    public static Map<String, Object> getFavMyCountries(@NonNull List<CountriesModel> models) {
        Map<String, Object> map = new HashMap<>();
        map.put("/myFavCountries/", App.gson().toJson(models));
        return map;
    }

    public static DatabaseReference getReference(@NonNull FirebaseDatabase database, @NonNull FirebaseUser user) {
        return database.getReference("user-data").child(user.getUid());
    }

    @Override public String toString() {
        return "UserModel{" +
                "myCountry=" + myCountry +
                ", myFavCountries=" + myFavCountries +
                ", uid='" + uid + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
