package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FlightFareModel implements Parcelable {
    private String kind;
    private String id;
    private String carrier;
    private String origin;
    private String destination;
    private String basisCode;
    @SerializedName("private") private String privateX;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getId() { return id;}

    public void setId(String id) { this.id = id;}

    public String getCarrier() { return carrier;}

    public void setCarrier(String carrier) { this.carrier = carrier;}

    public String getOrigin() { return origin;}

    public void setOrigin(String origin) { this.origin = origin;}

    public String getDestination() { return destination;}

    public void setDestination(String destination) { this.destination = destination;}

    public String getBasisCode() { return basisCode;}

    public void setBasisCode(String basisCode) { this.basisCode = basisCode;}

    public String getPrivateX() { return privateX;}

    public void setPrivateX(String privateX) { this.privateX = privateX;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.id);
        dest.writeString(this.carrier);
        dest.writeString(this.origin);
        dest.writeString(this.destination);
        dest.writeString(this.basisCode);
        dest.writeString(this.privateX);
    }

    public FlightFareModel() {}

    protected FlightFareModel(Parcel in) {
        this.kind = in.readString();
        this.id = in.readString();
        this.carrier = in.readString();
        this.origin = in.readString();
        this.destination = in.readString();
        this.basisCode = in.readString();
        this.privateX = in.readString();
    }

    public static final Parcelable.Creator<FlightFareModel> CREATOR = new Parcelable.Creator<FlightFareModel>() {
        @Override public FlightFareModel createFromParcel(Parcel source) {return new FlightFareModel(source);}

        @Override public FlightFareModel[] newArray(int size) {return new FlightFareModel[size];}
    };
}