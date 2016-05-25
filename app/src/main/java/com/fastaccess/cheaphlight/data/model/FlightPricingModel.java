package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class FlightPricingModel implements Parcelable {
    private String kind;
    private String baseFareTotal;
    private String saleFareTotal;
    private String saleTaxTotal;
    private String saleTotal;
    private FlightPassengersModel passengers;
    private String fareCalculation;
    private String latestTicketingTime;
    private String ptc;
    private String refundable;
    private List<FlightFareModel> fare;
    private List<FlightSegmentPricingModel> segmentPricing;
    private List<FlightTaxModel> tax;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getBaseFareTotal() { return baseFareTotal;}

    public void setBaseFareTotal(String baseFareTotal) { this.baseFareTotal = baseFareTotal;}

    public String getSaleFareTotal() { return saleFareTotal;}

    public void setSaleFareTotal(String saleFareTotal) { this.saleFareTotal = saleFareTotal;}

    public String getSaleTaxTotal() { return saleTaxTotal;}

    public void setSaleTaxTotal(String saleTaxTotal) { this.saleTaxTotal = saleTaxTotal;}

    public String getSaleTotal() { return saleTotal;}

    public void setSaleTotal(String saleTotal) { this.saleTotal = saleTotal;}

    public FlightPassengersModel getPassengers() { return passengers;}

    public void setPassengers(FlightPassengersModel passengers) { this.passengers = passengers;}

    public String getFareCalculation() { return fareCalculation;}

    public void setFareCalculation(String fareCalculation) { this.fareCalculation = fareCalculation;}

    public String getLatestTicketingTime() { return latestTicketingTime;}

    public void setLatestTicketingTime(String latestTicketingTime) { this.latestTicketingTime = latestTicketingTime;}

    public String getPtc() { return ptc;}

    public void setPtc(String ptc) { this.ptc = ptc;}

    public String getRefundable() { return refundable;}

    public void setRefundable(String refundable) { this.refundable = refundable;}

    public List<FlightFareModel> getFare() { return fare;}

    public void setFare(List<FlightFareModel> fare) { this.fare = fare;}

    public List<FlightSegmentPricingModel> getSegmentPricing() { return segmentPricing;}

    public void setSegmentPricing(List<FlightSegmentPricingModel> segmentPricing) { this.segmentPricing = segmentPricing;}

    public List<FlightTaxModel> getTax() { return tax;}

    public void setTax(List<FlightTaxModel> tax) { this.tax = tax;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.baseFareTotal);
        dest.writeString(this.saleFareTotal);
        dest.writeString(this.saleTaxTotal);
        dest.writeString(this.saleTotal);
        dest.writeParcelable(this.passengers, flags);
        dest.writeString(this.fareCalculation);
        dest.writeString(this.latestTicketingTime);
        dest.writeString(this.ptc);
        dest.writeString(this.refundable);
        dest.writeTypedList(this.fare);
        dest.writeList(this.segmentPricing);
        dest.writeList(this.tax);
    }

    public FlightPricingModel() {}

    protected FlightPricingModel(Parcel in) {
        this.kind = in.readString();
        this.baseFareTotal = in.readString();
        this.saleFareTotal = in.readString();
        this.saleTaxTotal = in.readString();
        this.saleTotal = in.readString();
        this.passengers = in.readParcelable(FlightPassengersModel.class.getClassLoader());
        this.fareCalculation = in.readString();
        this.latestTicketingTime = in.readString();
        this.ptc = in.readString();
        this.refundable = in.readString();
        this.fare = in.createTypedArrayList(FlightFareModel.CREATOR);
        this.segmentPricing = new ArrayList<FlightSegmentPricingModel>();
        in.readList(this.segmentPricing, FlightSegmentPricingModel.class.getClassLoader());
        this.tax = new ArrayList<FlightTaxModel>();
        in.readList(this.tax, FlightTaxModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<FlightPricingModel> CREATOR = new Parcelable.Creator<FlightPricingModel>() {
        @Override public FlightPricingModel createFromParcel(Parcel source) {return new FlightPricingModel(source);}

        @Override public FlightPricingModel[] newArray(int size) {return new FlightPricingModel[size];}
    };
}