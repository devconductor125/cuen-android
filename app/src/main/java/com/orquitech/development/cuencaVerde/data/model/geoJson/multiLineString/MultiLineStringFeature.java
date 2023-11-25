package com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString;

import android.os.Parcel;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MultiLineStringFeature extends Feature {

    @SerializedName("geometry")
    @Expose
    public MultiLineString geometry;

    public MultiLineStringFeature() {
        type = "Feature";
    }

    public MultiLineString getGeometry() {
        return (MultiLineString) geometry;
    }

    public void setGeometry(MultiLineString geometry) {
        this.geometry = geometry;
    }

    public List<List<LatLng>> getGeometryList() {
        return ((MultiLineString) geometry).getCoordinates();
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

    protected MultiLineStringFeature(Parcel in) {
        super(in);
        this.geometry = in.readParcelable(MultiLineString.class.getClassLoader());
    }

    public static final Creator<MultiLineStringFeature> CREATOR = new Creator<MultiLineStringFeature>() {
        @Override
        public MultiLineStringFeature createFromParcel(Parcel source) {
            return new MultiLineStringFeature(source);
        }

        @Override
        public MultiLineStringFeature[] newArray(int size) {
            return new MultiLineStringFeature[size];
        }
    };

    public void clearSpikes() {
        getGeometry().clearSpikes();
    }
}
