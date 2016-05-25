package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FlightFreeBaggageOptionModel implements Parcelable {
    private String kind;
    private String kilos;
    private String kilosPerPiece;
    private String pieces;
    private String pounds;
    /**
     * kind : qpxexpress#bagDescriptor commercialName : string count : integer description : ["string"] subcode : string
     */

    private List<FlightBagDescriptorModel> bagDescriptor;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public String getKilos() { return kilos;}

    public void setKilos(String kilos) { this.kilos = kilos;}

    public String getKilosPerPiece() { return kilosPerPiece;}

    public void setKilosPerPiece(String kilosPerPiece) { this.kilosPerPiece = kilosPerPiece;}

    public String getPieces() { return pieces;}

    public void setPieces(String pieces) { this.pieces = pieces;}

    public String getPounds() { return pounds;}

    public void setPounds(String pounds) { this.pounds = pounds;}

    public List<FlightBagDescriptorModel> getBagDescriptor() { return bagDescriptor;}

    public void setBagDescriptor(List<FlightBagDescriptorModel> bagDescriptor) { this.bagDescriptor = bagDescriptor;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.kilos);
        dest.writeString(this.kilosPerPiece);
        dest.writeString(this.pieces);
        dest.writeString(this.pounds);
        dest.writeTypedList(this.bagDescriptor);
    }

    public FlightFreeBaggageOptionModel() {}

    protected FlightFreeBaggageOptionModel(Parcel in) {
        this.kind = in.readString();
        this.kilos = in.readString();
        this.kilosPerPiece = in.readString();
        this.pieces = in.readString();
        this.pounds = in.readString();
        this.bagDescriptor = in.createTypedArrayList(FlightBagDescriptorModel.CREATOR);
    }

    public static final Parcelable.Creator<FlightFreeBaggageOptionModel> CREATOR = new Parcelable.Creator<FlightFreeBaggageOptionModel>() {
        @Override public FlightFreeBaggageOptionModel createFromParcel(Parcel source) {return new FlightFreeBaggageOptionModel(source);}

        @Override public FlightFreeBaggageOptionModel[] newArray(int size) {return new FlightFreeBaggageOptionModel[size];}
    };
}