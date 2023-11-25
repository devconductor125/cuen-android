package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class WaterConcession implements Parcelable {

    private boolean yes;
    private boolean no;
    private boolean inProcess;

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

    public boolean isInProcess() {
        return inProcess;
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess = inProcess;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.yes ? (byte) 1 : (byte) 0);
        dest.writeByte(this.no ? (byte) 1 : (byte) 0);
        dest.writeByte(this.inProcess ? (byte) 1 : (byte) 0);
    }

    public WaterConcession() {
    }

    protected WaterConcession(Parcel in) {
        this.yes = in.readByte() != 0;
        this.no = in.readByte() != 0;
        this.inProcess = in.readByte() != 0;
    }

    public static final Creator<WaterConcession> CREATOR = new Creator<WaterConcession>() {
        @Override
        public WaterConcession createFromParcel(Parcel source) {
            return new WaterConcession(source);
        }

        @Override
        public WaterConcession[] newArray(int size) {
            return new WaterConcession[size];
        }
    };
}
