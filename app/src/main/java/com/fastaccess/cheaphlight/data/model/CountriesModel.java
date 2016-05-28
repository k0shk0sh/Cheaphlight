package com.fastaccess.cheaphlight.data.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.fastaccess.cheaphlight.App;
import com.fastaccess.cheaphlight.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kosh on 29 May 2016, 3:26 AM
 */

public class CountriesModel implements Parcelable {
    private String countryCode;
    private String countryName;
    private String currencyCode;
    private String population;
    private String capital;
    private String continentName;
    private String continent;
    private static List<CountriesModel> allCountries;

    public String getCountryCode() { return countryCode;}

    public void setCountryCode(String countryCode) { this.countryCode = countryCode;}

    public String getCountryName() { return countryName;}

    public void setCountryName(String countryName) { this.countryName = countryName;}

    public String getCurrencyCode() { return currencyCode;}

    public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode;}

    public String getPopulation() { return population;}

    public void setPopulation(String population) { this.population = population;}

    public String getCapital() { return capital;}

    public void setCapital(String capital) { this.capital = capital;}

    public String getContinentName() { return continentName;}

    public void setContinentName(String continentName) { this.continentName = continentName;}

    public String getContinent() { return continent;}

    public void setContinent(String continent) { this.continent = continent;}

    @Override public String toString() {
        return countryName;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountriesModel that = (CountriesModel) o;
        return countryName != null ? countryName.equals(that.countryName) : that.countryName == null;

    }

    @Override public int hashCode() {
        return countryName != null ? countryName.hashCode() : 0;
    }

    /**
     * @return all parsed countries, reason for not passing context, is that the list will only be loaded once and then retained its state.
     */
    public static List<CountriesModel> getAllCountries() {
        if (allCountries == null || allCountries.isEmpty()) {
            Context context = App.getInstance().getApplicationContext();
            InputStream raw = context.getResources().openRawResource(R.raw.countries);
            Reader rd = new BufferedReader(new InputStreamReader(raw));
            Gson gson = App.gson();
            Type listType = new TypeToken<ArrayList<CountriesModel>>() {}.getType();
            allCountries = new ArrayList<>();
            allCountries.addAll((Collection<? extends CountriesModel>) gson.fromJson(rd, listType));
        }
        return allCountries;
    }

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.countryCode);
        dest.writeString(this.countryName);
        dest.writeString(this.currencyCode);
        dest.writeString(this.population);
        dest.writeString(this.capital);
        dest.writeString(this.continentName);
        dest.writeString(this.continent);
    }

    public CountriesModel() {}

    protected CountriesModel(Parcel in) {
        this.countryCode = in.readString();
        this.countryName = in.readString();
        this.currencyCode = in.readString();
        this.population = in.readString();
        this.capital = in.readString();
        this.continentName = in.readString();
        this.continent = in.readString();
    }

    public static final Parcelable.Creator<CountriesModel> CREATOR = new Parcelable.Creator<CountriesModel>() {
        @Override public CountriesModel createFromParcel(Parcel source) {return new CountriesModel(source);}

        @Override public CountriesModel[] newArray(int size) {return new CountriesModel[size];}
    };
}
