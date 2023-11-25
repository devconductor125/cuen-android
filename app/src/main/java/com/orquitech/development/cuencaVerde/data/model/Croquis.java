package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon.MultiPolygon;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class Croquis extends BaseItem implements Parcelable {

    private MultiPolygon multiPolygon;
    private String remoteId;

    public MultiPolygon getMultiPolygon() {
        return multiPolygon;
    }

    public void setMultiPolygon(MultiPolygon multiPolygon) {
        this.multiPolygon = multiPolygon;
    }

    public List<List<LatLng>> getGeometryList() {
        return multiPolygon.getCoordinates();
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    public String getPredioName() {
        return null;
    }

    public Croquis() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.multiPolygon, flags);
        dest.writeString(this.remoteId);
    }

    protected Croquis(Parcel in) {
        this.id = in.readString();
        this.multiPolygon = in.readParcelable(MultiPolygon.class.getClassLoader());
        this.remoteId = in.readString();
    }

    public static final Creator<Croquis> CREATOR = new Creator<Croquis>() {
        @Override
        public Croquis createFromParcel(Parcel source) {
            return new Croquis(source);
        }

        @Override
        public Croquis[] newArray(int size) {
            return new Croquis[size];
        }
    };
}
