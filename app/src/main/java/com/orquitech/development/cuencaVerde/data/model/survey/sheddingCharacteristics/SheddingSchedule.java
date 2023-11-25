package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class SheddingSchedule implements Parcelable {

    private boolean hours24;
    private boolean hours24To12;
    private boolean hoursLessThan12;

    public boolean isHours24() {
        return hours24;
    }

    public void setHours24(boolean hours24) {
        this.hours24 = hours24;
    }

    public boolean isHours24To12() {
        return hours24To12;
    }

    public void setHours24To12(boolean hours24To12) {
        this.hours24To12 = hours24To12;
    }

    public boolean isHoursLessThan12() {
        return hoursLessThan12;
    }

    public void setHoursLessThan12(boolean hoursLessThan12) {
        this.hoursLessThan12 = hoursLessThan12;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.hours24 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hours24To12 ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hoursLessThan12 ? (byte) 1 : (byte) 0);
    }

    public SheddingSchedule() {
    }

    protected SheddingSchedule(Parcel in) {
        this.hours24 = in.readByte() != 0;
        this.hours24To12 = in.readByte() != 0;
        this.hoursLessThan12 = in.readByte() != 0;
    }

    public static final Creator<SheddingSchedule> CREATOR = new Creator<SheddingSchedule>() {
        @Override
        public SheddingSchedule createFromParcel(Parcel source) {
            return new SheddingSchedule(source);
        }

        @Override
        public SheddingSchedule[] newArray(int size) {
            return new SheddingSchedule[size];
        }
    };
}
