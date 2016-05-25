package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FlightSliceModel implements Parcelable {
    private String kind;
    private String duration;
    /**
     * kind : qpxexpress#segmentInfo duration : integer flight : {"carrier":"string","number":"string"} id : string cabin : string bookingCode :
     * string bookingCodeCount : integer marriedSegmentGroup : string subjectToGovernmentApproval : boolean leg :
     * [{"kind":"qpxexpress#legInfo","id":"string","aircraft":"string","arrivalTime":"string","departureTime":"string",
     * "origin":"string","destination":"string","originTerminal":"string","destinationTerminal":"string","duration":"integer",
     * "operatingDisclosure":"string","onTimePerformance":"integer","mileage":"integer","meal":"string","secure":"boolean",
     * "connectionDuration":"integer","changePlane":"boolean"}] connectionDuration : integer
     */

    private List<FlightSegmentModel> segment;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getDuration() { return duration;}

    public void setDuration(String duration) { this.duration = duration;}

    public List<FlightSegmentModel> getSegment() { return segment;}

    public void setSegment(List<FlightSegmentModel> segment) { this.segment = segment;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.duration);
        dest.writeTypedList(this.segment);
    }

    public FlightSliceModel() {}

    protected FlightSliceModel(Parcel in) {
        this.kind = in.readString();
        this.duration = in.readString();
        this.segment = in.createTypedArrayList(FlightSegmentModel.CREATOR);
    }

    public static final Parcelable.Creator<FlightSliceModel> CREATOR = new Parcelable.Creator<FlightSliceModel>() {
        @Override public FlightSliceModel createFromParcel(Parcel source) {return new FlightSliceModel(source);}

        @Override public FlightSliceModel[] newArray(int size) {return new FlightSliceModel[size];}
    };
}