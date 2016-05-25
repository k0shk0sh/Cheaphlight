package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kosh on 25 May 2016, 10:27 PM
 */

public class FlightsResponseModel implements Parcelable {

    private String kind;
    private FlightTripsModel trips;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public FlightTripsModel getTrips() { return trips;}

    public void setTrips(FlightTripsModel trips) { this.trips = trips;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeParcelable(this.trips, flags);
    }

    public FlightsResponseModel() {}

    protected FlightsResponseModel(Parcel in) {
        this.kind = in.readString();
        this.trips = in.readParcelable(FlightTripsModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<FlightsResponseModel> CREATOR = new Parcelable.Creator<FlightsResponseModel>() {
        @Override public FlightsResponseModel createFromParcel(Parcel source) {return new FlightsResponseModel(source);}

        @Override public FlightsResponseModel[] newArray(int size) {return new FlightsResponseModel[size];}
    };
}
