package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightCarrierModel implements Parcelable {
    private String kind;
    private String code;
    private String name;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getCode() { return code;}

    public void setCode(String code) { this.code = code;}

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.code);
        dest.writeString(this.name);
    }

    public FlightCarrierModel() {}

    protected FlightCarrierModel(Parcel in) {
        this.kind = in.readString();
        this.code = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<FlightCarrierModel> CREATOR = new Parcelable.Creator<FlightCarrierModel>() {
        @Override public FlightCarrierModel createFromParcel(Parcel source) {return new FlightCarrierModel(source);}

        @Override public FlightCarrierModel[] newArray(int size) {return new FlightCarrierModel[size];}
    };
}