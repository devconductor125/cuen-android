package com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance;

import android.os.Parcel;
import android.os.Parcelable;

public class TravelTimeFromMunicipality implements Parcelable {

    private boolean minutes5To30;
    private boolean minutes30To60;
    private boolean hours2To3;

    public boolean isMinutes5To30() {
        return minutes5To30;
    }

    public void setMinutes5To30(boolean minutes5To30) {
        this.minutes5To30 = minutes5To30;
    }

    public boolean isMinutes30To60() {
        return minutes30To60;
    }

    public void setMinutes30To60(boolean minutes30To60) {
        this.minutes30To60 = minutes30To60;
    }

    public boolean isHours2To3() {
        return hours2To3;
    }

    public void setHours2To3(boolean hours2To3) {
        this.hours2To3 = hours2To3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.minutes5To30 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.minutes30To60 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hours2To3 ? (byte) 1 : (byte) 0);
    }

    public TravelTimeFromMunicipality() {
    }

    protected TravelTimeFromMunicipality(Parcel in) {
        this.minutes5To30 = in.readByte() != 0;
        this.minutes30To60 = in.readByte() != 0;
        this.hours2To3 = in.readByte() != 0;
    }

    public static final Creator<TravelTimeFromMunicipality> CREATOR = new Creator<TravelTimeFromMunicipality>() {
        @Override
        public TravelTimeFromMunicipality createFromParcel(Parcel source) {
            return new TravelTimeFromMunicipality(source);
        }

        @Override
        public TravelTimeFromMunicipality[] newArray(int size) {
            return new TravelTimeFromMunicipality[size];
        }
    };
}
