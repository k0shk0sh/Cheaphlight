package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class FlightDataModel implements Parcelable {
    private String kind;
    /**
     * kind : qpxexpress#airportData code : string city : string name : string
     */

    private List<FlightAirportModel> airport;
    /**
     * kind : qpxexpress#cityData code : string country : string name : string
     */

    private List<FlightCityModel> city;
    /**
     * kind : qpxexpress#aircraftData code : string name : string
     */

    private List<FlightAircraftModel> aircraft;
    /**
     * kind : qpxexpress#taxData id : string name : string
     */

    private List<FlightTaxModel> tax;
    /**
     * kind : qpxexpress#carrierData code : string name : string
     */

    private List<FlightCarrierModel> carrier;

    public String getKind() { return kind;}

    public void setKind(String kind) { this.kind = kind;}

    public List<FlightAirportModel> getAirport() { return airport;}

    public void setAirport(List<FlightAirportModel> airport) { this.airport = airport;}

    public List<FlightCityModel> getCity() { return city;}

    public void setCity(List<FlightCityModel> city) { this.city = city;}

    public List<FlightAircraftModel> getAircraft() { return aircraft;}

    public void setAircraft(List<FlightAircraftModel> aircraft) { this.aircraft = aircraft;}

    public List<FlightTaxModel> getTax() { return tax;}

    public void setTax(List<FlightTaxModel> tax) { this.tax = tax;}

    public List<FlightCarrierModel> getCarrier() { return carrier;}

    public void setCarrier(List<FlightCarrierModel> carrier) { this.carrier = carrier;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeTypedList(this.airport);
        dest.writeTypedList(this.city);
        dest.writeList(this.aircraft);
        dest.writeList(this.tax);
        dest.writeTypedList(this.carrier);
    }

    public FlightDataModel() {}

    protected FlightDataModel(Parcel in) {
        this.kind = in.readString();
        this.airport = in.createTypedArrayList(FlightAirportModel.CREATOR);
        this.city = in.createTypedArrayList(FlightCityModel.CREATOR);
        this.aircraft = new ArrayList<FlightAircraftModel>();
        in.readList(this.aircraft, FlightAircraftModel.class.getClassLoader());
        this.tax = new ArrayList<FlightTaxModel>();
        in.readList(this.tax, FlightTaxModel.class.getClassLoader());
        this.carrier = in.createTypedArrayList(FlightCarrierModel.CREATOR);
    }

    public static final Parcelable.Creator<FlightDataModel> CREATOR = new Parcelable.Creator<FlightDataModel>() {
        @Override public FlightDataModel createFromParcel(Parcel source) {return new FlightDataModel(source);}

        @Override public FlightDataModel[] newArray(int size) {return new FlightDataModel[size];}
    };
}