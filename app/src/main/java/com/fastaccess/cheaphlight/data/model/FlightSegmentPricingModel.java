package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FlightSegmentPricingModel implements Parcelable {
    private String kind;
    private String fareId;
    private String segmentId;
    /**
     * kind : qpxexpress#freeBaggageAllowance bagDescriptor : [{"kind":"qpxexpress#bagDescriptor","commercialName":"string",
     * "count":"integer","description":["string"],"subcode":"string"}] kilos : integer kilosPerPiece : integer pieces : integer pounds : integer
     */

    private List<FlightFreeBaggageOptionModel> freeBaggageOption;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getFareId() { return fareId;}

    public void setFareId(String fareId) { this.fareId = fareId;}

    public String getSegmentId() { return segmentId;}

    public void setSegmentId(String segmentId) { this.segmentId = segmentId;}

    public List<FlightFreeBaggageOptionModel> getFreeBaggageOption() { return freeBaggageOption;}

    public void setFreeBaggageOption(List<FlightFreeBaggageOptionModel> freeBaggageOption) { this.freeBaggageOption = freeBaggageOption;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.fareId);
        dest.writeString(this.segmentId);
        dest.writeTypedList(this.freeBaggageOption);
    }

    public FlightSegmentPricingModel() {}

    protected FlightSegmentPricingModel(Parcel in) {
        this.kind = in.readString();
        this.fareId = in.readString();
        this.segmentId = in.readString();
        this.freeBaggageOption = in.createTypedArrayList(FlightFreeBaggageOptionModel.CREATOR);
    }

    public static final Parcelable.Creator<FlightSegmentPricingModel> CREATOR = new Parcelable.Creator<FlightSegmentPricingModel>() {
        @Override public FlightSegmentPricingModel createFromParcel(Parcel source) {return new FlightSegmentPricingModel(source);}

        @Override public FlightSegmentPricingModel[] newArray(int size) {return new FlightSegmentPricingModel[size];}
    };
}