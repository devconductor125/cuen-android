package com.orquitech.development.cuencaVerde.data.model.geoJson;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointFeature extends Feature {

    @SerializedName("geometry")
    @Expose
    public Point geometry;

    public PointFeature() {
        type = "Feature";
    }

    public Point getGeometry() {
        return (Point) geometry;
    }

    public void setGeometry(Point geometry) {
        this.geometry = geometry;
    }

    public String getActionName() {
        return properties.getAccionTitle() + " " + properties.getMaterialTitle();
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

    protected PointFeature(Parcel in) {
        super(in);
        this.geometry = in.readParcelable(Point.class.getClassLoader());
    }

    public static final Creator<PointFeature> CREATOR = new Creator<PointFeature>() {
        @Override
        public PointFeature createFromParcel(Parcel source) {
            return new PointFeature(source);
        }

        @Override
        public PointFeature[] newArray(int size) {
            return new PointFeature[size];
        }
    };
}
