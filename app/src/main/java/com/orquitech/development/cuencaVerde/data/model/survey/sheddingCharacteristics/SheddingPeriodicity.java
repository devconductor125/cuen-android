package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class SheddingPeriodicity implements Parcelable {

    private boolean continuous;
    private boolean intermittent;

    public boolean isContinuous() {
        return continuous;
    }

    public void setContinuous(boolean continuous) {
        this.continuous = continuous;
    }

    public boolean isIntermittent() {
        return intermittent;
    }

    public void setIntermittent(boolean intermittent) {
        this.intermittent = intermittent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.continuous ? (byte) 1 : (byte) 0);
        dest.writeByte(this.intermittent ? (byte) 1 : (byte) 0);
    }

    public SheddingPeriodicity() {
    }

    protected SheddingPeriodicity(Parcel in) {
        this.continuous = in.readByte() != 0;
        this.intermittent = in.readByte() != 0;
    }

    public static final Creator<SheddingPeriodicity> CREATOR = new Creator<SheddingPeriodicity>() {
        @Override
        public SheddingPeriodicity createFromParcel(Parcel source) {
            return new SheddingPeriodicity(source);
        }

        @Override
        public SheddingPeriodicity[] newArray(int size) {
            return new SheddingPeriodicity[size];
        }
    };
}
