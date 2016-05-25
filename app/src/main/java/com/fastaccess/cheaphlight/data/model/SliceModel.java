package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SliceModel implements Parcelable {
    private String kind;
    private String origin;
    private String destination;
    private String date;
    private String maxStops;
    private String maxConnectionDuration;
    private String preferredCabin;
    /**
     * kind : qpxexpress#timeOfDayRange earliestTime : string latestTime : string
     */

    private PermittedDepartureTimeModel permittedDepartureTime;
    private String alliance;
    private List<String> permittedCarrier;
    private List<String> prohibitedCarrier;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getOrigin() { return origin;}

    public void setOrigin(String origin) { this.origin = origin;}

    public String getDestination() { return destination;}

    public void setDestination(String destination) { this.destination = destination;}

    public String getDate() { return date;}

    public void setDate(String date) { this.date = date;}

    public String getMaxStops() { return maxStops;}

    public void setMaxStops(String maxStops) { this.maxStops = maxStops;}

    public String getMaxConnectionDuration() { return maxConnectionDuration;}

    public void setMaxConnectionDuration(String maxConnectionDuration) { this.maxConnectionDuration = maxConnectionDuration;}

    public String getPreferredCabin() { return preferredCabin;}

    public void setPreferredCabin(String preferredCabin) { this.preferredCabin = preferredCabin;}

    public PermittedDepartureTimeModel getPermittedDepartureTime() { return permittedDepartureTime;}

    public void setPermittedDepartureTime(PermittedDepartureTimeModel permittedDepartureTime) {
        this.permittedDepartureTime =
                permittedDepartureTime;
    }

    public String getAlliance() { return alliance;}

    public void setAlliance(String alliance) { this.alliance = alliance;}

    public List<String> getPermittedCarrier() { return permittedCarrier;}

    public void setPermittedCarrier(List<String> permittedCarrier) { this.permittedCarrier = permittedCarrier;}

    public List<String> getProhibitedCarrier() { return prohibitedCarrier;}

    public void setProhibitedCarrier(List<String> prohibitedCarrier) { this.prohibitedCarrier = prohibitedCarrier;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.origin);
        dest.writeString(this.destination);
        dest.writeString(this.date);
        dest.writeString(this.maxStops);
        dest.writeString(this.maxConnectionDuration);
        dest.writeString(this.preferredCabin);
        dest.writeParcelable(this.permittedDepartureTime, flags);
        dest.writeString(this.alliance);
        dest.writeStringList(this.permittedCarrier);
        dest.writeStringList(this.prohibitedCarrier);
    }

    public SliceModel() {}

    protected SliceModel(Parcel in) {
        this.kind = in.readString();
        this.origin = in.readString();
        this.destination = in.readString();
        this.date = in.readString();
        this.maxStops = in.readString();
        this.maxConnectionDuration = in.readString();
        this.preferredCabin = in.readString();
        this.permittedDepartureTime = in.readParcelable(PermittedDepartureTimeModel.class.getClassLoader());
        this.alliance = in.readString();
        this.permittedCarrier = in.createStringArrayList();
        this.prohibitedCarrier = in.createStringArrayList();
    }

    public static final Parcelable.Creator<SliceModel> CREATOR = new Parcelable.Creator<SliceModel>() {
        @Override public SliceModel createFromParcel(Parcel source) {return new SliceModel(source);}

        @Override public SliceModel[] newArray(int size) {return new SliceModel[size];}
    };
}