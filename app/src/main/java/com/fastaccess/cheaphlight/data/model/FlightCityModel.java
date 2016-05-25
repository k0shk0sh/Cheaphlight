package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightCityModel implements Parcelable {
    private String kind;
    private String code;
    private String country;
    private String name;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getCode() { return code;}

    public void setCode(String code) { this.code = code;}

    public String getCountry() { return country;}

    public void setCountry(String country) { this.country = country;}

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.code);
        dest.writeString(this.country);
        dest.writeString(this.name);
    }

    public FlightCityModel() {}

    protected FlightCityModel(Parcel in) {
        this.kind = in.readString();
        this.code = in.readString();
        this.country = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<FlightCityModel> CREATOR = new Parcelable.Creator<FlightCityModel>() {
        @Override public FlightCityModel createFromParcel(Parcel source) {return new FlightCityModel(source);}

        @Override public FlightCityModel[] newArray(int size) {return new FlightCityModel[size];}
    };
}