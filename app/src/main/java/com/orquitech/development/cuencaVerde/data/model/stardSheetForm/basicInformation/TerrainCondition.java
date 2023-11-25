package com.orquitech.development.cuencaVerde.data.model.stardSheetForm.basicInformation;

import android.os.Parcel;
import android.os.Parcelable;

public class TerrainCondition implements Parcelable {

    private boolean highSlope;
    private boolean areThereErosiveProcesses;

    public boolean isHighSlope() {
        return highSlope;
    }

    public void setHighSlope(boolean highSlope) {
        this.highSlope = highSlope;
    }

    public boolean isAreThereErosiveProcesses() {
        return areThereErosiveProcesses;
    }

    public void setAreThereErosiveProcesses(boolean areThereErosiveProcesses) {
        this.areThereErosiveProcesses = areThereErosiveProcesses;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.highSlope ? (byte) 1 : (byte) 0);
        dest.writeByte(this.areThereErosiveProcesses ? (byte) 1 : (byte) 0);
    }

    public TerrainCondition() {
    }

    protected TerrainCondition(Parcel in) {
        this.highSlope = in.readByte() != 0;
        this.areThereErosiveProcesses = in.readByte() != 0;
    }

    public static final Creator<TerrainCondition> CREATOR = new Creator<TerrainCondition>() {
        @Override
        public TerrainCondition createFromParcel(Parcel source) {
            return new TerrainCondition(source);
        }

        @Override
        public TerrainCondition[] newArray(int size) {
            return new TerrainCondition[size];
        }
    };
}
