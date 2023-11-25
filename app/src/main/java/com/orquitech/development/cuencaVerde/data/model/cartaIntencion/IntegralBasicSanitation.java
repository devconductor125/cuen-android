package com.orquitech.development.cuencaVerde.data.model.cartaIntencion;

import android.os.Parcel;
import android.os.Parcelable;

public class IntegralBasicSanitation implements Parcelable {

    boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private boolean stardMaintenance;
    private boolean stardInstallation;

    public boolean isStardMaintenance() {
        return stardMaintenance;
    }

    public void setStardMaintenance(boolean stardMaintenance) {
        this.stardMaintenance = stardMaintenance;
    }

    public boolean isStardInstallation() {
        return stardInstallation;
    }

    public void setStardInstallation(boolean stardInstallation) {
        this.stardInstallation = stardInstallation;
    }

    public IntegralBasicSanitation() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.stardMaintenance ? (byte) 1 : (byte) 0);
        dest.writeByte(this.stardInstallation ? (byte) 1 : (byte) 0);
    }

    protected IntegralBasicSanitation(Parcel in) {
        this.checked = in.readByte() != 0;
        this.stardMaintenance = in.readByte() != 0;
        this.stardInstallation = in.readByte() != 0;
    }

    public static final Creator<IntegralBasicSanitation> CREATOR = new Creator<IntegralBasicSanitation>() {
        @Override
        public IntegralBasicSanitation createFromParcel(Parcel source) {
            return new IntegralBasicSanitation(source);
        }

        @Override
        public IntegralBasicSanitation[] newArray(int size) {
            return new IntegralBasicSanitation[size];
        }
    };
}
