package com.orquitech.development.cuencaVerde.data.model.cartaIntencion;

import android.os.Parcel;
import android.os.Parcelable;

public class SubType implements Parcelable {

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public SubType() {
    }

    protected SubType(Parcel in) {
        this.name = in.readString();
    }

    public static final Creator<SubType> CREATOR = new Creator<SubType>() {
        @Override
        public SubType createFromParcel(Parcel source) {
            return new SubType(source);
        }

        @Override
        public SubType[] newArray(int size) {
            return new SubType[size];
        }
    };
}
