package com.orquitech.development.cuencaVerde.data.model.survey;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyType implements Parcelable {

    private boolean residential;
    private boolean commercial;
    private boolean propertyOther;
    private int value;
    private String other;

    public boolean isResidential() {
        return residential;
    }

    public void setResidential(boolean residential) {
        this.residential = residential;
        value = 1;
    }

    public boolean isCommercial() {
        return commercial;
    }

    public void setCommercial(boolean commercial) {
        this.commercial = commercial;
        value = 2;
    }

    public boolean isPropertyOther() {
        return propertyOther;
    }

    public void setPropertyOther(boolean propertyOther) {
        this.propertyOther = propertyOther;
        value = 3;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.residential ? (byte) 1 : (byte) 0);
        dest.writeByte(this.commercial ? (byte) 1 : (byte) 0);
        dest.writeByte(this.propertyOther ? (byte) 1 : (byte) 0);
        dest.writeString(this.other);
    }

    public PropertyType() {
    }

    protected PropertyType(Parcel in) {
        this.residential = in.readByte() != 0;
        this.commercial = in.readByte() != 0;
        this.propertyOther = in.readByte() != 0;
        this.other = in.readString();
    }

    public static final Parcelable.Creator<PropertyType> CREATOR = new Parcelable.Creator<PropertyType>() {
        @Override
        public PropertyType createFromParcel(Parcel source) {
            return new PropertyType(source);
        }

        @Override
        public PropertyType[] newArray(int size) {
            return new PropertyType[size];
        }
    };
}
