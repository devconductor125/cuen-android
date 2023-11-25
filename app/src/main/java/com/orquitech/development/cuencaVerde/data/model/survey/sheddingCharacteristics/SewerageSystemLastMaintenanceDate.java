package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class SewerageSystemLastMaintenanceDate implements Parcelable {

    private boolean years12;
    private boolean years23;
    private boolean years34;
    private boolean never;
    private boolean cantRemember;

    public boolean isYears12() {
        return years12;
    }

    public void setYears12(boolean years12) {
        this.years12 = years12;
    }

    public boolean isYears23() {
        return years23;
    }

    public void setYears23(boolean years23) {
        this.years23 = years23;
    }

    public boolean isYears34() {
        return years34;
    }

    public void setYears34(boolean years34) {
        this.years34 = years34;
    }

    public boolean isNever() {
        return never;
    }

    public void setNever(boolean never) {
        this.never = never;
    }

    public boolean isCantRemember() {
        return cantRemember;
    }

    public void setCantRemember(boolean cantRemember) {
        this.cantRemember = cantRemember;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.years12 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.years23 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.years34 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.never ? (byte) 1 : (byte) 0);
        dest.writeByte(this.cantRemember ? (byte) 1 : (byte) 0);
    }

    public SewerageSystemLastMaintenanceDate() {
    }

    protected SewerageSystemLastMaintenanceDate(Parcel in) {
        this.years12 = in.readByte() != 0;
        this.years23 = in.readByte() != 0;
        this.years34 = in.readByte() != 0;
        this.never = in.readByte() != 0;
        this.cantRemember = in.readByte() != 0;
    }

    public static final Creator<SewerageSystemLastMaintenanceDate> CREATOR = new Creator<SewerageSystemLastMaintenanceDate>() {
        @Override
        public SewerageSystemLastMaintenanceDate createFromParcel(Parcel source) {
            return new SewerageSystemLastMaintenanceDate(source);
        }

        @Override
        public SewerageSystemLastMaintenanceDate[] newArray(int size) {
            return new SewerageSystemLastMaintenanceDate[size];
        }
    };
}
