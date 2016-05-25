package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightLegModel implements Parcelable {
    private String kind;
    private String id;
    private String aircraft;
    private String arrivalTime;
    private String departureTime;
    private String origin;
    private String destination;
    private String originTerminal;
    private String destinationTerminal;
    private String duration;
    private String operatingDisclosure;
    private String onTimePerformance;
    private String mileage;
    private String meal;
    private String secure;
    private String connectionDuration;
    private String changePlane;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getId() { return id;}

    public void setId(String id) { this.id = id;}

    public String getAircraft() { return aircraft;}

    public void setAircraft(String aircraft) { this.aircraft = aircraft;}

    public String getArrivalTime() { return arrivalTime;}

    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime;}

    public String getDepartureTime() { return departureTime;}

    public void setDepartureTime(String departureTime) { this.departureTime = departureTime;}

    public String getOrigin() { return origin;}

    public void setOrigin(String origin) { this.origin = origin;}

    public String getDestination() { return destination;}

    public void setDestination(String destination) { this.destination = destination;}

    public String getOriginTerminal() { return originTerminal;}

    public void setOriginTerminal(String originTerminal) { this.originTerminal = originTerminal;}

    public String getDestinationTerminal() { return destinationTerminal;}

    public void setDestinationTerminal(String destinationTerminal) { this.destinationTerminal = destinationTerminal;}

    public String getDuration() { return duration;}

    public void setDuration(String duration) { this.duration = duration;}

    public String getOperatingDisclosure() { return operatingDisclosure;}

    public void setOperatingDisclosure(String operatingDisclosure) { this.operatingDisclosure = operatingDisclosure;}

    public String getOnTimePerformance() { return onTimePerformance;}

    public void setOnTimePerformance(String onTimePerformance) { this.onTimePerformance = onTimePerformance;}

    public String getMileage() { return mileage;}

    public void setMileage(String mileage) { this.mileage = mileage;}

    public String getMeal() { return meal;}

    public void setMeal(String meal) { this.meal = meal;}

    public String getSecure() { return secure;}

    public void setSecure(String secure) { this.secure = secure;}

    public String getConnectionDuration() { return connectionDuration;}

    public void setConnectionDuration(String connectionDuration) { this.connectionDuration = connectionDuration;}

    public String getChangePlane() { return changePlane;}

    public void setChangePlane(String changePlane) { this.changePlane = changePlane;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.id);
        dest.writeString(this.aircraft);
        dest.writeString(this.arrivalTime);
        dest.writeString(this.departureTime);
        dest.writeString(this.origin);
        dest.writeString(this.destination);
        dest.writeString(this.originTerminal);
        dest.writeString(this.destinationTerminal);
        dest.writeString(this.duration);
        dest.writeString(this.operatingDisclosure);
        dest.writeString(this.onTimePerformance);
        dest.writeString(this.mileage);
        dest.writeString(this.meal);
        dest.writeString(this.secure);
        dest.writeString(this.connectionDuration);
        dest.writeString(this.changePlane);
    }

    public FlightLegModel() {}

    protected FlightLegModel(Parcel in) {
        this.kind = in.readString();
        this.id = in.readString();
        this.aircraft = in.readString();
        this.arrivalTime = in.readString();
        this.departureTime = in.readString();
        this.origin = in.readString();
        this.destination = in.readString();
        this.originTerminal = in.readString();
        this.destinationTerminal = in.readString();
        this.duration = in.readString();
        this.operatingDisclosure = in.readString();
        this.onTimePerformance = in.readString();
        this.mileage = in.readString();
        this.meal = in.readString();
        this.secure = in.readString();
        this.connectionDuration = in.readString();
        this.changePlane = in.readString();
    }

    public static final Parcelable.Creator<FlightLegModel> CREATOR = new Parcelable.Creator<FlightLegModel>() {
        @Override public FlightLegModel createFromParcel(Parcel source) {return new FlightLegModel(source);}

        @Override public FlightLegModel[] newArray(int size) {return new FlightLegModel[size];}
    };
}