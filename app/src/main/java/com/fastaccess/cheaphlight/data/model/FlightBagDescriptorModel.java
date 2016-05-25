package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FlightBagDescriptorModel implements Parcelable {
    private String kind;
    private String commercialName;
    private String count;
    private String subcode;
    private List<String> description;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getCommercialName() { return commercialName;}

    public void setCommercialName(String commercialName) { this.commercialName = commercialName;}

    public String getCount() { return count;}

    public void setCount(String count) { this.count = count;}

    public String getSubcode() { return subcode;}

    public void setSubcode(String subcode) { this.subcode = subcode;}

    public List<String> getDescription() { return description;}

    public void setDescription(List<String> description) { this.description = description;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.commercialName);
        dest.writeString(this.count);
        dest.writeString(this.subcode);
        dest.writeStringList(this.description);
    }

    public FlightBagDescriptorModel() {}

    protected FlightBagDescriptorModel(Parcel in) {
        this.kind = in.readString();
        this.commercialName = in.readString();
        this.count = in.readString();
        this.subcode = in.readString();
        this.description = in.createStringArrayList();
    }

    public static final Parcelable.Creator<FlightBagDescriptorModel> CREATOR = new Parcelable.Creator<FlightBagDescriptorModel>() {
        @Override public FlightBagDescriptorModel createFromParcel(Parcel source) {return new FlightBagDescriptorModel(source);}

        @Override public FlightBagDescriptorModel[] newArray(int size) {return new FlightBagDescriptorModel[size];}
    };
}