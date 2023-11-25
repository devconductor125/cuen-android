package com.orquitech.development.cuencaVerde.data.model.geoJson;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenericFeature extends Feature {

    @SerializedName("geometry")
    @Expose
    public Geometry geometry;

    public GenericFeature() {
        type = "Feature";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.geometry, flags);
    }

    protected GenericFeature(Parcel in) {
        super(in);
        this.geometry = in.readParcelable(Geometry.class.getClassLoader());
    }

    public static final Creator<GenericFeature> CREATOR = new Creator<GenericFeature>() {
        @Override
        public GenericFeature createFromParcel(Parcel source) {
            return new GenericFeature(source);
        }

        @Override
        public GenericFeature[] newArray(int size) {
            return new GenericFeature[size];
        }
    };
}
