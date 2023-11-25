package com.orquitech.development.cuencaVerde.data.api.model.prediosManagement.get;

import android.os.Parcel;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrediosManagementResponse extends CuencaVerdeResponse implements Parcelable {

    @SerializedName("json")
    @Expose
    public GeoJson json;
    @SerializedName("date")
    @Expose
    public String date;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.json, flags);
        dest.writeString(this.date);
    }

    public PrediosManagementResponse() {
    }

    protected PrediosManagementResponse(Parcel in) {
        this.json = in.readParcelable(GeoJson.class.getClassLoader());
        this.date = in.readString();
    }

    public static final Creator<PrediosManagementResponse> CREATOR = new Creator<PrediosManagementResponse>() {
        @Override
        public PrediosManagementResponse createFromParcel(Parcel source) {
            return new PrediosManagementResponse(source);
        }

        @Override
        public PrediosManagementResponse[] newArray(int size) {
            return new PrediosManagementResponse[size];
        }
    };
}
