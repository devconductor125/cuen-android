package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class MaintenanceControl implements Parcelable {

    protected String id;
    private boolean pendingToBeSent;
    private boolean isValidObject;
    private String maintenancesSpeciesAmount;
    private String maintenancesSpecies;
    private String overseedingSpeciesAmount;
    private String overseedingSpecies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPendingToBeSent() {
        return pendingToBeSent;
    }

    public void setAsPendingToBeSent() {
        this.pendingToBeSent = true;
    }

    public boolean isValid() {
        return !TextUtils.isEmpty(maintenancesSpeciesAmount) ||
                !TextUtils.isEmpty(maintenancesSpecies) ||
                !TextUtils.isEmpty(overseedingSpeciesAmount) ||
                !TextUtils.isEmpty(overseedingSpecies);
    }

    public String getMaintenancesSpeciesAmount() {
        return maintenancesSpeciesAmount;
    }

    public void setMaintenancesSpeciesAmount(String maintenancesSpeciesAmount) {
        this.maintenancesSpeciesAmount = maintenancesSpeciesAmount;
    }

    public String getMaintenancesSpecies() {
        return maintenancesSpecies;
    }

    public void setMaintenancesSpecies(String maintenancesSpecies) {
        this.maintenancesSpecies = maintenancesSpecies;
    }

    public String getOverseedingSpeciesAmount() {
        return overseedingSpeciesAmount;
    }

    public void setOverseedingSpeciesAmount(String overseedingSpeciesAmount) {
        this.overseedingSpeciesAmount = overseedingSpeciesAmount;
    }

    public String getOverseedingSpecies() {
        return overseedingSpecies;
    }

    public void setOverseedingSpecies(String overseedingSpecies) {
        this.overseedingSpecies = overseedingSpecies;
    }

    public boolean isValidObject() {
        return isValidObject;
    }

    public void initNullFields() {
        this.isValidObject = true;
    }

    public MaintenanceControl() {
        initNullFields();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeByte(this.pendingToBeSent ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isValidObject ? (byte) 1 : (byte) 0);
        dest.writeString(this.maintenancesSpeciesAmount);
        dest.writeString(this.maintenancesSpecies);
        dest.writeString(this.overseedingSpeciesAmount);
        dest.writeString(this.overseedingSpecies);
    }

    protected MaintenanceControl(Parcel in) {
        this.id = in.readString();
        this.pendingToBeSent = in.readByte() != 0;
        this.isValidObject = in.readByte() != 0;
        this.maintenancesSpeciesAmount = in.readString();
        this.maintenancesSpecies = in.readString();
        this.overseedingSpeciesAmount = in.readString();
        this.overseedingSpecies = in.readString();
    }

    public static final Creator<MaintenanceControl> CREATOR = new Creator<MaintenanceControl>() {
        @Override
        public MaintenanceControl createFromParcel(Parcel source) {
            return new MaintenanceControl(source);
        }

        @Override
        public MaintenanceControl[] newArray(int size) {
            return new MaintenanceControl[size];
        }
    };
}
