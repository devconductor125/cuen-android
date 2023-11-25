package com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon;

import android.os.Parcel;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineString;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MultiPolygonFeature extends Feature {

    @SerializedName("geometry")
    @Expose
    public MultiPolygon geometry;

    public MultiPolygonFeature() {
        type = "Feature";
    }

    public MultiPolygon getGeometry() {
        return (MultiPolygon) geometry;
    }

    public void setGeometry(MultiPolygon geometry) {
        this.geometry = geometry;
    }

    public List<List<LatLng>> getGeometryList() {
        return ((MultiPolygon) geometry).getCoordinates();
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

    protected MultiPolygonFeature(Parcel in) {
        super(in);
        this.geometry = in.readParcelable(MultiLineString.class.getClassLoader());
    }

    public static final Creator<MultiPolygonFeature> CREATOR = new Creator<MultiPolygonFeature>() {
        @Override
        public MultiPolygonFeature createFromParcel(Parcel source) {
            return new MultiPolygonFeature(source);
        }

        @Override
        public MultiPolygonFeature[] newArray(int size) {
            return new MultiPolygonFeature[size];
        }
    };

    public void clearSpikes() {
        getGeometry().clearSpikes();
    }
}
