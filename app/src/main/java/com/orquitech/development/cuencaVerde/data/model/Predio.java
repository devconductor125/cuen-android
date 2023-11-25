package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineStringFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class Predio extends BaseItem implements Parcelable {

    private GeoJson geoJson;
    private String name;

    public Predio() {
        geoJson = new GeoJson();
        id = "-1";
    }

    public void setId(long id) {
        this.id = String.valueOf(id);
    }

    public GeoJson getGeoJson() {
        return geoJson;
    }

    public void setGeoJson(GeoJson geoJson) {
        this.geoJson = geoJson;
    }

    public void addFeatureFromCoordinates(String actionName, List<LatLng> coordinates) {
        //geoJson.addMultiLineString(actionName, coordinates, GeoJson.MULTI_LINE_STRING);
    }

    public MultiLineStringFeature getLastAddedFeature() {
        return geoJson.getMultiLineStringFeatures().get(geoJson.getMultiLineStringFeatures().size() - 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.geoJson, flags);
        dest.writeString(this.name);
    }

    protected Predio(Parcel in) {
        this.id = in.readString();
        this.geoJson = in.readParcelable(GeoJson.class.getClassLoader());
        this.name = in.readString();
    }

    public static final Creator<Predio> CREATOR = new Creator<Predio>() {
        @Override
        public Predio createFromParcel(Parcel source) {
            return new Predio(source);
        }

        @Override
        public Predio[] newArray(int size) {
            return new Predio[size];
        }
    };
}
