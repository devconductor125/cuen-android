package com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance;

import android.os.Parcel;
import android.os.Parcelable;

public class MaintenanceType implements Parcelable {

    private boolean preventive;
    private boolean corrective;
    private boolean urgent;

    public boolean isPreventive() {
        return preventive;
    }

    public void setPreventive(boolean preventive) {
        this.preventive = preventive;
    }

    public boolean isCorrective() {
        return corrective;
    }

    public void setCorrective(boolean corrective) {
        this.corrective = corrective;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.preventive ? (byte) 1 : (byte) 0);
        dest.writeByte(this.corrective ? (byte) 1 : (byte) 0);
        dest.writeByte(this.urgent ? (byte) 1 : (byte) 0);
    }

    public MaintenanceType() {
    }

    protected MaintenanceType(Parcel in) {
        this.preventive = in.readByte() != 0;
        this.corrective = in.readByte() != 0;
        this.urgent = in.readByte() != 0;
    }

    public static final Creator<MaintenanceType> CREATOR = new Creator<MaintenanceType>() {
        @Override
        public MaintenanceType createFromParcel(Parcel source) {
            return new MaintenanceType(source);
        }

        @Override
        public MaintenanceType[] newArray(int size) {
            return new MaintenanceType[size];
        }
    };
}
