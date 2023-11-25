package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class TreatmentSystemStatus implements Parcelable {

    private boolean good;
    private boolean bad;
    private boolean average;

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public boolean isBad() {
        return bad;
    }

    public void setBad(boolean bad) {
        this.bad = bad;
    }

    public boolean isAverage() {
        return average;
    }

    public void setAverage(boolean average) {
        this.average = average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.good ? (byte) 1 : (byte) 0);
        dest.writeByte(this.bad ? (byte) 1 : (byte) 0);
        dest.writeByte(this.average ? (byte) 1 : (byte) 0);
    }

    public TreatmentSystemStatus() {
    }

    protected TreatmentSystemStatus(Parcel in) {
        this.good = in.readByte() != 0;
        this.bad = in.readByte() != 0;
        this.average = in.readByte() != 0;
    }

    public static final Creator<TreatmentSystemStatus> CREATOR = new Creator<TreatmentSystemStatus>() {
        @Override
        public TreatmentSystemStatus createFromParcel(Parcel source) {
            return new TreatmentSystemStatus(source);
        }

        @Override
        public TreatmentSystemStatus[] newArray(int size) {
            return new TreatmentSystemStatus[size];
        }
    };
}
