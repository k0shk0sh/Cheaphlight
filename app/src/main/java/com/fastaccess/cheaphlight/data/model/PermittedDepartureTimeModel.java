package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PermittedDepartureTimeModel implements Parcelable {
    private String kind;
    private String earliestTime;
    private String latestTime;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getEarliestTime() { return earliestTime;}

    public void setEarliestTime(String earliestTime) { this.earliestTime = earliestTime;}

    public String getLatestTime() { return latestTime;}

    public void setLatestTime(String latestTime) { this.latestTime = latestTime;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.earliestTime);
        dest.writeString(this.latestTime);
    }

    public PermittedDepartureTimeModel() {}

    protected PermittedDepartureTimeModel(Parcel in) {
        this.kind = in.readString();
        this.earliestTime = in.readString();
        this.latestTime = in.readString();
    }

    public static final Parcelable.Creator<PermittedDepartureTimeModel> CREATOR = new Parcelable.Creator<PermittedDepartureTimeModel>() {
        @Override public PermittedDepartureTimeModel createFromParcel(Parcel source) {return new PermittedDepartureTimeModel(source);}

        @Override public PermittedDepartureTimeModel[] newArray(int size) {return new PermittedDepartureTimeModel[size];}
    };
}