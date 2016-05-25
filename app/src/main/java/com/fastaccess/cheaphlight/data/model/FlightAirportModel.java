package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightAirportModel implements Parcelable {
    private String kind;
    private String code;
    private String city;
    private String name;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getCode() { return code;}

    public void setCode(String code) { this.code = code;}

    public String getCity() { return city;}

    public void setCity(String city) { this.city = city;}

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.code);
        dest.writeString(this.city);
        dest.writeString(this.name);
    }

    public FlightAirportModel() {}

    protected FlightAirportModel(Parcel in) {
        this.kind = in.readString();
        this.code = in.readString();
        this.city = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<FlightAirportModel> CREATOR = new Parcelable.Creator<FlightAirportModel>() {
        @Override public FlightAirportModel createFromParcel(Parcel source) {return new FlightAirportModel(source);}

        @Override public FlightAirportModel[] newArray(int size) {return new FlightAirportModel[size];}
    };
}