package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PassengersModel implements Parcelable {
    private String kind;
    private String adultCount;
    private String childCount;
    private String infantInLapCount;
    private String infantInSeatCount;
    private String seniorCount;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getAdultCount() { return adultCount;}

    public void setAdultCount(String adultCount) { this.adultCount = adultCount;}

    public String getChildCount() { return childCount;}

    public void setChildCount(String childCount) { this.childCount = childCount;}

    public String getInfantInLapCount() { return infantInLapCount;}

    public void setInfantInLapCount(String infantInLapCount) { this.infantInLapCount = infantInLapCount;}

    public String getInfantInSeatCount() { return infantInSeatCount;}

    public void setInfantInSeatCount(String infantInSeatCount) { this.infantInSeatCount = infantInSeatCount;}

    public String getSeniorCount() { return seniorCount;}

    public void setSeniorCount(String seniorCount) { this.seniorCount = seniorCount;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.adultCount);
        dest.writeString(this.childCount);
        dest.writeString(this.infantInLapCount);
        dest.writeString(this.infantInSeatCount);
        dest.writeString(this.seniorCount);
    }

    public PassengersModel() {}

    protected PassengersModel(Parcel in) {
        this.kind = in.readString();
        this.adultCount = in.readString();
        this.childCount = in.readString();
        this.infantInLapCount = in.readString();
        this.infantInSeatCount = in.readString();
        this.seniorCount = in.readString();
    }

    public static final Parcelable.Creator<PassengersModel> CREATOR = new Parcelable.Creator<PassengersModel>() {
        @Override public PassengersModel createFromParcel(Parcel source) {return new PassengersModel(source);}

        @Override public PassengersModel[] newArray(int size) {return new PassengersModel[size];}
    };
}