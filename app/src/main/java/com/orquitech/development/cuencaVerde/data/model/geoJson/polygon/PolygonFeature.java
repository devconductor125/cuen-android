package com.orquitech.development.cuencaVerde.data.model.geoJson.polygon;

import android.os.Parcel;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineString;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PolygonFeature extends Feature {

    @SerializedName("geometry")
    @Expose
    public Polygon geometry;

    public PolygonFeature() {
        type = "Feature";
    }

    public Polygon getGeometry() {
        return (Polygon) geometry;
    }

    public void setGeometry(Polygon geometry) {
        this.geometry = geometry;
    }

    public List<List<LatLng>> getGeometryList() {
        return ((Polygon) geometry).getCoordinates();
    }

    public String getActionName() {
        return properties.getAccionTitle() + " con " + properties.getMaterialTitle();
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

    protected PolygonFeature(Parcel in) {
        super(in);
        this.geometry = in.readParcelable(MultiLineString.class.getClassLoader());
    }

    public static final Creator<PolygonFeature> CREATOR = new Creator<PolygonFeature>() {
        @Override
        public PolygonFeature createFromParcel(Parcel source) {
            return new PolygonFeature(source);
        }

        @Override
        public PolygonFeature[] newArray(int size) {
            return new PolygonFeature[size];
        }
    };

    public void clearSpikes() {
        getGeometry().clearSpikes();
    }
}
