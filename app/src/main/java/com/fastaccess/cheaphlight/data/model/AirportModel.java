package com.fastaccess.cheaphlight.data.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.fastaccess.cheaphlight.App;
import com.fastaccess.cheaphlight.R;
import com.google.firebase.database.Exclude;
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

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Kosh on 30 May 2016, 8:22 PM
 */

public class AirportModel implements Parcelable {
    private String code;
    private String lat;
    private String lon;
    private String name;
    private String city;
    private String state;
    private String country;
    private String woeid;
    private String tz;
    private String phone;
    private String type;
    private String email;
    private String url;
    private String runway_length;
    private String elev;
    private String icao;
    private String direct_flights;
    private String carriers;

    @Exclude private static List<AirportModel> allAirports = new ArrayList<>();

    public String getCode() { return code;}

    public void setCode(String code) { this.code = code;}

    public String getLat() { return lat;}

    public void setLat(String lat) { this.lat = lat;}

    public String getLon() { return lon;}

    public void setLon(String lon) { this.lon = lon;}

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    public String getCity() { return city;}

    public void setCity(String city) { this.city = city;}

    public String getState() { return state;}

    public void setState(String state) { this.state = state;}

    public String getCountry() { return country;}

    public void setCountry(String country) { this.country = country;}

    public String getWoeid() { return woeid;}

    public void setWoeid(String woeid) { this.woeid = woeid;}

    public String getTz() { return tz;}

    public void setTz(String tz) { this.tz = tz;}

    public String getPhone() { return phone;}

    public void setPhone(String phone) { this.phone = phone;}

    public String getType() { return type;}

    public void setType(String type) { this.type = type;}

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email;}

    public String getUrl() { return url;}

    public void setUrl(String url) { this.url = url;}

    public String getRunway_length() { return runway_length;}

    public void setRunway_length(String runway_length) { this.runway_length = runway_length;}

    public String getElev() { return elev;}

    public void setElev(String elev) { this.elev = elev;}

    public String getIcao() { return icao;}

    public void setIcao(String icao) { this.icao = icao;}

    public String getDirect_flights() { return direct_flights;}

    public void setDirect_flights(String direct_flights) { this.direct_flights = direct_flights;}

    public String getCarriers() { return carriers;}

    public void setCarriers(String carriers) {
        this.carriers = carriers;

    }

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.lat);
        dest.writeString(this.lon);
        dest.writeString(this.name);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.country);
        dest.writeString(this.woeid);
        dest.writeString(this.tz);
        dest.writeString(this.phone);
        dest.writeString(this.type);
        dest.writeString(this.email);
        dest.writeString(this.url);
        dest.writeString(this.runway_length);
        dest.writeString(this.elev);
        dest.writeString(this.icao);
        dest.writeString(this.direct_flights);
        dest.writeString(this.carriers);
    }

    public AirportModel() {}

    protected AirportModel(Parcel in) {
        this.code = in.readString();
        this.lat = in.readString();
        this.lon = in.readString();
        this.name = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.country = in.readString();
        this.woeid = in.readString();
        this.tz = in.readString();
        this.phone = in.readString();
        this.type = in.readString();
        this.email = in.readString();
        this.url = in.readString();
        this.runway_length = in.readString();
        this.elev = in.readString();
        this.icao = in.readString();
        this.direct_flights = in.readString();
        this.carriers = in.readString();
    }

    public static final Parcelable.Creator<AirportModel> CREATOR = new Parcelable.Creator<AirportModel>() {
        @Override public AirportModel createFromParcel(Parcel source) {return new AirportModel(source);}

        @Override public AirportModel[] newArray(int size) {return new AirportModel[size];}
    };

    @NonNull private static List<AirportModel> getAllAirports() {
        if (allAirports == null || allAirports.isEmpty()) {
            Context context = App.getInstance().getApplicationContext();
            InputStream raw = context.getResources().openRawResource(R.raw.airports);
            Reader rd = new BufferedReader(new InputStreamReader(raw));
            Gson gson = App.gson();
            Type listType = new TypeToken<ArrayList<AirportModel>>() {}.getType();
            allAirports.addAll(gson.<Collection<? extends AirportModel>>fromJson(rd, listType));
        }
        return allAirports;
    }

    public static Observable<List<AirportModel>> getAirports() {
        return Observable.just(getAllAirports())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .cache();
    }
}
