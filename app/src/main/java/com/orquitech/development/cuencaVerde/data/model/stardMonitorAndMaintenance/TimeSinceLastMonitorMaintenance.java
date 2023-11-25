package com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeSinceLastMonitorMaintenance implements Parcelable {

    private boolean months6To12;
    private boolean years1To2;
    private boolean years2To4;

    public boolean isMonths6To12() {
        return months6To12;
    }

    public void setMonths6To12(boolean months6To12) {
        this.months6To12 = months6To12;
    }

    public boolean isYears1To2() {
        return years1To2;
    }

    public void setYears1To2(boolean years1To2) {
        this.years1To2 = years1To2;
    }

    public boolean isYears2To4() {
        return years2To4;
    }

    public void setYears2To4(boolean years2To4) {
        this.years2To4 = years2To4;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.months6To12 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.years1To2 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.years2To4 ? (byte) 1 : (byte) 0);
    }

    public TimeSinceLastMonitorMaintenance() {
    }

    protected TimeSinceLastMonitorMaintenance(Parcel in) {
        this.months6To12 = in.readByte() != 0;
        this.years1To2 = in.readByte() != 0;
        this.years2To4 = in.readByte() != 0;
    }

    public static final Creator<TimeSinceLastMonitorMaintenance> CREATOR = new Creator<TimeSinceLastMonitorMaintenance>() {
        @Override
        public TimeSinceLastMonitorMaintenance createFromParcel(Parcel source) {
            return new TimeSinceLastMonitorMaintenance(source);
        }

        @Override
        public TimeSinceLastMonitorMaintenance[] newArray(int size) {
            return new TimeSinceLastMonitorMaintenance[size];
        }
    };
}
