package com.orquitech.development.cuencaVerde.data.model.stardSheetForm.basicInformation;

import android.os.Parcel;
import android.os.Parcelable;

public class BathRoomDischarge implements Parcelable {

    private boolean yes;
    private boolean no;
    private boolean doesNotExist;

    public boolean isYes() {
        return yes;
    }

    public void setYes(boolean yes) {
        this.yes = yes;
    }

    public boolean isNo() {
        return no;
    }

    public void setNo(boolean no) {
        this.no = no;
    }

    public boolean isDoesNotExist() {
        return doesNotExist;
    }

    public void setDoesNotExist(boolean doesNotExist) {
        this.doesNotExist = doesNotExist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.yes ? (byte) 1 : (byte) 0);
        dest.writeByte(this.no ? (byte) 1 : (byte) 0);
        dest.writeByte(this.doesNotExist ? (byte) 1 : (byte) 0);
    }

    public BathRoomDischarge() {
    }

    protected BathRoomDischarge(Parcel in) {
        this.yes = in.readByte() != 0;
        this.no = in.readByte() != 0;
        this.doesNotExist = in.readByte() != 0;
    }

    public static final Creator<BathRoomDischarge> CREATOR = new Creator<BathRoomDischarge>() {
        @Override
        public BathRoomDischarge createFromParcel(Parcel source) {
            return new BathRoomDischarge(source);
        }

        @Override
        public BathRoomDischarge[] newArray(int size) {
            return new BathRoomDischarge[size];
        }
    };
}
