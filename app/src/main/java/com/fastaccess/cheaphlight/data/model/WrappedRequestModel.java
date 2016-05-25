package com.fastaccess.cheaphlight.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kosh on 25 May 2016, 10:18 PM
 */

public class WrappedRequestModel implements Parcelable {

    private RequestModel request;

    public RequestModel getRequest() { return request;}

    public void setRequest(RequestModel request) { this.request = request;}

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel dest, int flags) {dest.writeParcelable(this.request, flags);}

    public WrappedRequestModel() {}

    protected WrappedRequestModel(Parcel in) {this.request = in.readParcelable(RequestModel.class.getClassLoader());}

    public static final Parcelable.Creator<WrappedRequestModel> CREATOR = new Parcelable.Creator<WrappedRequestModel>() {
        @Override public WrappedRequestModel createFromParcel(Parcel source) {return new WrappedRequestModel(source);}

        @Override public WrappedRequestModel[] newArray(int size) {return new WrappedRequestModel[size];}
    };
}
