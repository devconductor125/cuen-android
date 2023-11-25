package com.orquitech.development.cuencaVerde.data.model.survey.basicNeeds;

import android.os.Parcel;
import android.os.Parcelable;

public class BasicSanitationMethods implements Parcelable {

    private String stardHousehold;
    private String noStardHousehold;
    private boolean stardCorrectiveMaintenance;
    private String stardCorrectiveMaintenanceQuantity;
    private boolean stardPreventiveMaintenance;
    private String stardPreventiveMaintenanceQuantity;

    public String getStardHousehold() {
        return stardHousehold;
    }

    public void setStardHousehold(String stardHousehold) {
        this.stardHousehold = stardHousehold;
    }

    public String getNoStardHousehold() {
        return noStardHousehold;
    }

    public void setNoStardHousehold(String noStardHousehold) {
        this.noStardHousehold = noStardHousehold;
    }

    public boolean isStardCorrectiveMaintenance() {
        return stardCorrectiveMaintenance;
    }

    public void setStardCorrectiveMaintenance(boolean stardCorrectiveMaintenance) {
        this.stardCorrectiveMaintenance = stardCorrectiveMaintenance;
    }

    public String getStardCorrectiveMaintenanceQuantity() {
        return stardCorrectiveMaintenanceQuantity;
    }

    public void setStardCorrectiveMaintenanceQuantity(String stardCorrectiveMaintenanceQuantity) {
        this.stardCorrectiveMaintenanceQuantity = stardCorrectiveMaintenanceQuantity;
    }

    public boolean isStardPreventiveMaintenance() {
        return stardPreventiveMaintenance;
    }

    public void setStardPreventiveMaintenance(boolean stardPreventiveMaintenance) {
        this.stardPreventiveMaintenance = stardPreventiveMaintenance;
    }

    public String getStardPreventiveMaintenanceQuantity() {
        return stardPreventiveMaintenanceQuantity;
    }

    public void setStardPreventiveMaintenanceQuantity(String stardPreventiveMaintenanceQuantity) {
        this.stardPreventiveMaintenanceQuantity = stardPreventiveMaintenanceQuantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.stardHousehold);
        dest.writeString(this.noStardHousehold);
        dest.writeByte(this.stardCorrectiveMaintenance ? (byte) 1 : (byte) 0);
        dest.writeString(this.stardCorrectiveMaintenanceQuantity);
        dest.writeByte(this.stardPreventiveMaintenance ? (byte) 1 : (byte) 0);
        dest.writeString(this.stardPreventiveMaintenanceQuantity);
    }

    public BasicSanitationMethods() {
    }

    protected BasicSanitationMethods(Parcel in) {
        this.stardHousehold = in.readString();
        this.noStardHousehold = in.readString();
        this.stardCorrectiveMaintenance = in.readByte() != 0;
        this.stardCorrectiveMaintenanceQuantity = in.readString();
        this.stardPreventiveMaintenance = in.readByte() != 0;
        this.stardPreventiveMaintenanceQuantity = in.readString();
    }

    public static final Creator<BasicSanitationMethods> CREATOR = new Creator<BasicSanitationMethods>() {
        @Override
        public BasicSanitationMethods createFromParcel(Parcel source) {
            return new BasicSanitationMethods(source);
        }

        @Override
        public BasicSanitationMethods[] newArray(int size) {
            return new BasicSanitationMethods[size];
        }
    };
}
