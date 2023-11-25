package com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance;

import android.os.Parcel;
import android.os.Parcelable;

public class StardType implements Parcelable {

    private boolean masonry;
    private boolean fiberglass;
    private boolean plastic;

    public boolean isMasonry() {
        return masonry;
    }

    public void setMasonry(boolean masonry) {
        this.masonry = masonry;
    }

    public boolean isFiberglass() {
        return fiberglass;
    }

    public void setFiberglass(boolean fiberglass) {
        this.fiberglass = fiberglass;
    }

    public boolean isPlastic() {
        return plastic;
    }

    public void setPlastic(boolean plastic) {
        this.plastic = plastic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.masonry ? (byte) 1 : (byte) 0);
        dest.writeByte(this.fiberglass ? (byte) 1 : (byte) 0);
        dest.writeByte(this.plastic ? (byte) 1 : (byte) 0);
    }

    public StardType() {
    }

    protected StardType(Parcel in) {
        this.masonry = in.readByte() != 0;
        this.fiberglass = in.readByte() != 0;
        this.plastic = in.readByte() != 0;
    }

    public static final Creator<StardType> CREATOR = new Creator<StardType>() {
        @Override
        public StardType createFromParcel(Parcel source) {
            return new StardType(source);
        }

        @Override
        public StardType[] newArray(int size) {
            return new StardType[size];
        }
    };
}
