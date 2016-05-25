package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RequestModel implements Parcelable {
    private PassengersModel passengers;
    private String maxPrice;
    private String saleCountry;
    private String refundable;
    private String solutions;
    private List<SliceModel> slice;

    public PassengersModel getPassengers() { return passengers;}

    public void setPassengers(PassengersModel passengers) { this.passengers = passengers;}

    public String getMaxPrice() { return maxPrice;}

    public void setMaxPrice(String maxPrice) { this.maxPrice = maxPrice;}

    public String getSaleCountry() { return saleCountry;}

    public void setSaleCountry(String saleCountry) { this.saleCountry = saleCountry;}

    public String getRefundable() { return refundable;}

    public void setRefundable(String refundable) { this.refundable = refundable;}

    public String getSolutions() { return solutions;}

    public void setSolutions(String solutions) { this.solutions = solutions;}

    public List<SliceModel> getSlice() { return slice;}

    public void setSlice(List<SliceModel> slice) { this.slice = slice;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.passengers, flags);
        dest.writeString(this.maxPrice);
        dest.writeString(this.saleCountry);
        dest.writeString(this.refundable);
        dest.writeString(this.solutions);
        dest.writeTypedList(this.slice);
    }

    public RequestModel() {}

    protected RequestModel(Parcel in) {
        this.passengers = in.readParcelable(PassengersModel.class.getClassLoader());
        this.maxPrice = in.readString();
        this.saleCountry = in.readString();
        this.refundable = in.readString();
        this.solutions = in.readString();
        this.slice = in.createTypedArrayList(SliceModel.CREATOR);
    }

    public static final Parcelable.Creator<RequestModel> CREATOR = new Parcelable.Creator<RequestModel>() {
        @Override public RequestModel createFromParcel(Parcel source) {return new RequestModel(source);}

        @Override public RequestModel[] newArray(int size) {return new RequestModel[size];}
    };
}
