package com.orquitech.development.cuencaVerde.data.model.stardSheetForm.basicInformation;

import android.os.Parcel;
import android.os.Parcelable;

public class SystemType implements Parcelable {

    private boolean masonry;
    private boolean prefabricated;

    public boolean isMasonry() {
        return masonry;
    }

    public void setMasonry(boolean masonry) {
        this.masonry = masonry;
    }

    public boolean isPrefabricated() {
        return prefabricated;
    }

    public void setPrefabricated(boolean prefabricated) {
        this.prefabricated = prefabricated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.masonry ? (byte) 1 : (byte) 0);
        dest.writeByte(this.prefabricated ? (byte) 1 : (byte) 0);
    }

    public SystemType() {
    }

    protected SystemType(Parcel in) {
        this.masonry = in.readByte() != 0;
        this.prefabricated = in.readByte() != 0;
    }

    public static final Creator<SystemType> CREATOR = new Creator<SystemType>() {
        @Override
        public SystemType createFromParcel(Parcel source) {
            return new SystemType(source);
        }

        @Override
        public SystemType[] newArray(int size) {
            return new SystemType[size];
        }
    };
}
