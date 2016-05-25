package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightTaxModel implements Parcelable {
    private String kind;
    private String id;
    private String chargeType;
    private String code;
    private String country;
    private String salePrice;
    private String name;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getId() { return id;}

    public void setId(String id) { this.id = id;}

    public String getChargeType() { return chargeType;}

    public void setChargeType(String chargeType) { this.chargeType = chargeType;}

    public String getCode() { return code;}

    public void setCode(String code) { this.code = code;}

    public String getCountry() { return country;}

    public void setCountry(String country) { this.country = country;}

    public String getSalePrice() { return salePrice;}

    public void setSalePrice(String salePrice) { this.salePrice = salePrice;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.id);
        dest.writeString(this.chargeType);
        dest.writeString(this.code);
        dest.writeString(this.country);
        dest.writeString(this.salePrice);
        dest.writeString(this.name);
    }

    public FlightTaxModel() {}

    protected FlightTaxModel(Parcel in) {
        this.kind = in.readString();
        this.id = in.readString();
        this.chargeType = in.readString();
        this.code = in.readString();
        this.country = in.readString();
        this.salePrice = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<FlightTaxModel> CREATOR = new Parcelable.Creator<FlightTaxModel>() {
        @Override public FlightTaxModel createFromParcel(Parcel source) {return new FlightTaxModel(source);}

        @Override public FlightTaxModel[] newArray(int size) {return new FlightTaxModel[size];}
    };
}