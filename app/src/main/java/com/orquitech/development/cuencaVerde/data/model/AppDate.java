package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AppDate implements Parcelable {

    private String year;
    private String month;
    private String day;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getString() {
        return day + "/" + month + "/" + year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.year);
        dest.writeString(this.month);
        dest.writeString(this.day);
    }

    public AppDate() {
    }

    protected AppDate(Parcel in) {
        this.year = in.readString();
        this.month = in.readString();
        this.day = in.readString();
    }

    public static final Creator<AppDate> CREATOR = new Creator<AppDate>() {
        @Override
        public AppDate createFromParcel(Parcel source) {
            return new AppDate(source);
        }

        @Override
        public AppDate[] newArray(int size) {
            return new AppDate[size];
        }
    };
}
