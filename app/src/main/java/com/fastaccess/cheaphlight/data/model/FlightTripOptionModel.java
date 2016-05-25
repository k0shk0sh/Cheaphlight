package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FlightTripOptionModel implements Parcelable {
    private String kind;
    private String saleTotal;
    private String id;
    private List<FlightSliceModel> slice;

    private List<FlightPricingModel> pricing;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getSaleTotal() { return saleTotal;}

    public void setSaleTotal(String saleTotal) { this.saleTotal = saleTotal;}

    public String getId() { return id;}

    public void setId(String id) { this.id = id;}

    public List<FlightSliceModel> getSlice() { return slice;}

    public void setSlice(List<FlightSliceModel> slice) { this.slice = slice;}

    public List<FlightPricingModel> getPricing() { return pricing;}

    public void setPricing(List<FlightPricingModel> pricing) { this.pricing = pricing;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.saleTotal);
        dest.writeString(this.id);
        dest.writeTypedList(this.slice);
        dest.writeTypedList(this.pricing);
    }

    public FlightTripOptionModel() {}

    protected FlightTripOptionModel(Parcel in) {
        this.kind = in.readString();
        this.saleTotal = in.readString();
        this.id = in.readString();
        this.slice = in.createTypedArrayList(FlightSliceModel.CREATOR);
        this.pricing = in.createTypedArrayList(FlightPricingModel.CREATOR);
    }

    public static final Parcelable.Creator<FlightTripOptionModel> CREATOR = new Parcelable.Creator<FlightTripOptionModel>() {
        @Override public FlightTripOptionModel createFromParcel(Parcel source) {return new FlightTripOptionModel(source);}

        @Override public FlightTripOptionModel[] newArray(int size) {return new FlightTripOptionModel[size];}
    };
}