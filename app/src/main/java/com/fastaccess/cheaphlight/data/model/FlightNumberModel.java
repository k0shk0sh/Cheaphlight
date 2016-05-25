package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightNumberModel implements Parcelable {
    private String carrier;
    private String number;

    public String getCarrier() { return carrier;}

    public void setCarrier(String carrier) { this.carrier = carrier;}

    public String getNumber() { return number;}

    public void setNumber(String number) { this.number = number;}

    public FlightNumberModel() {}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.carrier);
        dest.writeString(this.number);
    }

    protected FlightNumberModel(Parcel in) {
        this.carrier = in.readString();
        this.number = in.readString();
    }

    public static final Creator<FlightNumberModel> CREATOR = new Creator<FlightNumberModel>() {
        @Override public FlightNumberModel createFromParcel(Parcel source) {return new FlightNumberModel(source);}

        @Override public FlightNumberModel[] newArray(int size) {return new FlightNumberModel[size];}
    };
}