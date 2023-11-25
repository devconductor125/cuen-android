package com.orquitech.development.cuencaVerde.data.model.survey.basicNeeds;

import android.os.Parcel;
import android.os.Parcelable;

public class HydrologicalSource implements Parcelable {

    private boolean municipalAqueduct;
    private boolean spring;
    private boolean rainWater;
    private String other;

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

    public boolean isRainWater() {
        return rainWater;
    }

    public void setRainWater(boolean rainWater) {
        this.rainWater = rainWater;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.municipalAqueduct ? (byte) 1 : (byte) 0);
        dest.writeByte(this.spring ? (byte) 1 : (byte) 0);
        dest.writeByte(this.rainWater ? (byte) 1 : (byte) 0);
        dest.writeString(this.other);
    }

    public HydrologicalSource() {
    }

    protected HydrologicalSource(Parcel in) {
        this.municipalAqueduct = in.readByte() != 0;
        this.spring = in.readByte() != 0;
        this.rainWater = in.readByte() != 0;
        this.other = in.readString();
    }

    public static final Creator<HydrologicalSource> CREATOR = new Creator<HydrologicalSource>() {
        @Override
        public HydrologicalSource createFromParcel(Parcel source) {
            return new HydrologicalSource(source);
        }

        @Override
        public HydrologicalSource[] newArray(int size) {
            return new HydrologicalSource[size];
        }
    };
}
