package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class SheddingLocation implements Parcelable {

    private boolean hydrologicalSource;
    private boolean soil;
    private boolean municipalAqueduct;
    private boolean spring;

    public boolean isHydrologicalSource() {
        return hydrologicalSource;
    }

    public void setHydrologicalSource(boolean hydrologicalSource) {
        this.hydrologicalSource = hydrologicalSource;
    }

    public boolean isSoil() {
        return soil;
    }

    public void setSoil(boolean soil) {
        this.soil = soil;
    }

    public boolean isMunicipalAqueduct() {
        return municipalAqueduct;
    }

    public void setMunicipalAqueduct(boolean municipalAqueduct) {
        this.municipalAqueduct = municipalAqueduct;
    }

    public boolean isSpring() {
        return spring;
    }

    public void setSpring(boolean spring) {
        this.spring = spring;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.hydrologicalSource ? (byte) 1 : (byte) 0);
        dest.writeByte(this.soil ? (byte) 1 : (byte) 0);
        dest.writeByte(this.municipalAqueduct ? (byte) 1 : (byte) 0);
        dest.writeByte(this.spring ? (byte) 1 : (byte) 0);
    }

    public SheddingLocation() {
    }

    protected SheddingLocation(Parcel in) {
        this.hydrologicalSource = in.readByte() != 0;
        this.soil = in.readByte() != 0;
        this.municipalAqueduct = in.readByte() != 0;
        this.spring = in.readByte() != 0;
    }

    public static final Creator<SheddingLocation> CREATOR = new Creator<SheddingLocation>() {
        @Override
        public SheddingLocation createFromParcel(Parcel source) {
            return new SheddingLocation(source);
        }

        @Override
        public SheddingLocation[] newArray(int size) {
            return new SheddingLocation[size];
        }
    };
}
