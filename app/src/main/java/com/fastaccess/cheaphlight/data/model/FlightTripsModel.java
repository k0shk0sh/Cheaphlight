package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FlightTripsModel implements Parcelable {
    private String kind;
    private String requestId;
    private FlightDataModel data;
    private List<FlightTripOptionModel> tripOption;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getRequestId() { return requestId;}

    public void setRequestId(String requestId) { this.requestId = requestId;}

    public FlightDataModel getData() { return data;}

    public void setData(FlightDataModel data) { this.data = data;}

    public List<FlightTripOptionModel> getTripOption() { return tripOption;}

    public void setTripOption(List<FlightTripOptionModel> tripOption) { this.tripOption = tripOption;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.requestId);
        dest.writeParcelable(this.data, flags);
        dest.writeTypedList(this.tripOption);
    }

    public FlightTripsModel() {}

    protected FlightTripsModel(Parcel in) {
        this.kind = in.readString();
        this.requestId = in.readString();
        this.data = in.readParcelable(FlightDataModel.class.getClassLoader());
        this.tripOption = in.createTypedArrayList(FlightTripOptionModel.CREATOR);
    }

    public static final Parcelable.Creator<FlightTripsModel> CREATOR = new Parcelable.Creator<FlightTripsModel>() {
        @Override public FlightTripsModel createFromParcel(Parcel source) {return new FlightTripsModel(source);}

        @Override public FlightTripsModel[] newArray(int size) {return new FlightTripsModel[size];}
    };
}