package com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance;

import android.os.Parcel;
import android.os.Parcelable;

public class StardTankCapacity implements Parcelable {

    private boolean lt1000;
    private boolean lt1500;
    private boolean lt2000;
    private boolean lt2500;
    private boolean lt3000;
    private boolean lt3500;
    private boolean lt4000;
    private boolean lt4500;
    private boolean lt5000;

    public boolean isLt1000() {
        return lt1000;
    }

    public void setLt1000(boolean lt1000) {
        this.lt1000 = lt1000;
    }

    public boolean isLt1500() {
        return lt1500;
    }

    public void setLt1500(boolean lt1500) {
        this.lt1500 = lt1500;
    }

    public boolean isLt2000() {
        return lt2000;
    }

    public void setLt2000(boolean lt2000) {
        this.lt2000 = lt2000;
    }

    public boolean isLt2500() {
        return lt2500;
    }

    public void setLt2500(boolean lt2500) {
        this.lt2500 = lt2500;
    }

    public boolean isLt3000() {
        return lt3000;
    }

    public void setLt3000(boolean lt3000) {
        this.lt3000 = lt3000;
    }

    public boolean isLt3500() {
        return lt3500;
    }

    public void setLt3500(boolean lt3500) {
        this.lt3500 = lt3500;
    }

    public boolean isLt4000() {
        return lt4000;
    }

    public void setLt4000(boolean lt4000) {
        this.lt4000 = lt4000;
    }

    public boolean isLt4500() {
        return lt4500;
    }

    public void setLt4500(boolean lt4500) {
        this.lt4500 = lt4500;
    }

    public boolean isLt5000() {
        return lt5000;
    }

    public void setLt5000(boolean lt5000) {
        this.lt5000 = lt5000;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.lt1000 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.lt1500 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.lt2000 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.lt2500 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.lt3000 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.lt3500 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.lt4000 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.lt4500 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.lt5000 ? (byte) 1 : (byte) 0);
    }

    public StardTankCapacity() {
    }

    protected StardTankCapacity(Parcel in) {
        this.lt1000 = in.readByte() != 0;
        this.lt1500 = in.readByte() != 0;
        this.lt2000 = in.readByte() != 0;
        this.lt2500 = in.readByte() != 0;
        this.lt3000 = in.readByte() != 0;
        this.lt3500 = in.readByte() != 0;
        this.lt4000 = in.readByte() != 0;
        this.lt4500 = in.readByte() != 0;
        this.lt5000 = in.readByte() != 0;
    }

    public static final Creator<StardTankCapacity> CREATOR = new Creator<StardTankCapacity>() {
        @Override
        public StardTankCapacity createFromParcel(Parcel source) {
            return new StardTankCapacity(source);
        }

        @Override
        public StardTankCapacity[] newArray(int size) {
            return new StardTankCapacity[size];
        }
    };
}
