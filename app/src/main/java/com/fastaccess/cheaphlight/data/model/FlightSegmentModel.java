package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FlightSegmentModel implements Parcelable {
    private String kind;
    private String duration;
    private FlightNumberModel flight;
    private String id;
    private String cabin;
    private String bookingCode;
    private String bookingCodeCount;
    private String marriedSegmentGroup;
    private String subjectToGovernmentApproval;
    private String connectionDuration;

    private List<FlightLegModel> leg;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getDuration() { return duration;}

    public void setDuration(String duration) { this.duration = duration;}

    public FlightNumberModel getFlight() { return flight;}

    public void setFlight(FlightNumberModel flight) { this.flight = flight;}

    public String getId() { return id;}

    public void setId(String id) { this.id = id;}

    public String getCabin() { return cabin;}

    public void setCabin(String cabin) { this.cabin = cabin;}

    public String getBookingCode() { return bookingCode;}

    public void setBookingCode(String bookingCode) { this.bookingCode = bookingCode;}

    public String getBookingCodeCount() { return bookingCodeCount;}

    public void setBookingCodeCount(String bookingCodeCount) { this.bookingCodeCount = bookingCodeCount;}

    public String getMarriedSegmentGroup() { return marriedSegmentGroup;}

    public void setMarriedSegmentGroup(String marriedSegmentGroup) { this.marriedSegmentGroup = marriedSegmentGroup;}

    public String getSubjectToGovernmentApproval() { return subjectToGovernmentApproval;}

    public void setSubjectToGovernmentApproval(String subjectToGovernmentApproval) {
        this.subjectToGovernmentApproval =
                subjectToGovernmentApproval;
    }

    public String getConnectionDuration() { return connectionDuration;}

    public void setConnectionDuration(String connectionDuration) { this.connectionDuration = connectionDuration;}

    public List<FlightLegModel> getLeg() { return leg;}

    public void setLeg(List<FlightLegModel> leg) { this.leg = leg;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.duration);
        dest.writeParcelable(this.flight, flags);
        dest.writeString(this.id);
        dest.writeString(this.cabin);
        dest.writeString(this.bookingCode);
        dest.writeString(this.bookingCodeCount);
        dest.writeString(this.marriedSegmentGroup);
        dest.writeString(this.subjectToGovernmentApproval);
        dest.writeString(this.connectionDuration);
        dest.writeTypedList(this.leg);
    }

    public FlightSegmentModel() {}

    protected FlightSegmentModel(Parcel in) {
        this.kind = in.readString();
        this.duration = in.readString();
        this.flight = in.readParcelable(FlightNumberModel.class.getClassLoader());
        this.id = in.readString();
        this.cabin = in.readString();
        this.bookingCode = in.readString();
        this.bookingCodeCount = in.readString();
        this.marriedSegmentGroup = in.readString();
        this.subjectToGovernmentApproval = in.readString();
        this.connectionDuration = in.readString();
        this.leg = in.createTypedArrayList(FlightLegModel.CREATOR);
    }

    public static final Parcelable.Creator<FlightSegmentModel> CREATOR = new Parcelable.Creator<FlightSegmentModel>() {
        @Override public FlightSegmentModel createFromParcel(Parcel source) {return new FlightSegmentModel(source);}

        @Override public FlightSegmentModel[] newArray(int size) {return new FlightSegmentModel[size];}
    };
}