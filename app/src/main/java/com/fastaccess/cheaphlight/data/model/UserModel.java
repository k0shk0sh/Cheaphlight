package com.fastaccess.cheaphlight.data.model;

import java.util.List;

/**
 * Created by Kosh on 29 May 2016, 5:26 AM
 */

public class UserModel {

    private String displayName;
    private String email;
    private String uid;
    private CountriesModel my_country;
    private List<CountriesModel> my_fav_countries;

    public String getDisplayName() { return displayName;}

    public void setDisplayName(String displayName) { this.displayName = displayName;}

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email;}

    public String getUid() { return uid;}

    public void setUid(String uid) { this.uid = uid;}

    public List<CountriesModel> getMy_fav_countries() {
        return my_fav_countries;
    }

    public void setMy_fav_countries(List<CountriesModel> my_fav_countries) {
        this.my_fav_countries = my_fav_countries;
    }

    public CountriesModel getMy_country() {
        return my_country;
    }

    public void setMy_country(CountriesModel my_country) {
        this.my_country = my_country;
    }
}
