package com.orquitech.development.cuencaVerde.data.model.survey;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyLegalStatus implements Parcelable {

    private boolean tenantStatus;
    private boolean possession;
    private boolean succession;
    private int value;
    private String comments;

    public boolean isTenantStatus() {
        return tenantStatus;
    }

    public void setTenantStatus(boolean tenantStatus) {
        this.tenantStatus = tenantStatus;
        value = 1;
    }

    public boolean isPossession() {
        return possession;
    }

    public void setPossession(boolean possession) {
        this.possession = possession;
        value = 2;
    }

    public boolean isSuccession() {
        return succession;
    }

    public void setSuccession(boolean succession) {
        this.succession = succession;
        value = 3;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        dest.writeByte(this.tenantStatus ? (byte) 1 : (byte) 0);
        dest.writeByte(this.possession ? (byte) 1 : (byte) 0);
        dest.writeByte(this.succession ? (byte) 1 : (byte) 0);
        dest.writeString(this.comments);
    }

    public PropertyLegalStatus() {
    }

    protected PropertyLegalStatus(Parcel in) {
        this.tenantStatus = in.readByte() != 0;
        this.possession = in.readByte() != 0;
        this.succession = in.readByte() != 0;
        this.comments = in.readString();
    }

    public static final Creator<PropertyLegalStatus> CREATOR = new Creator<PropertyLegalStatus>() {
        @Override
        public PropertyLegalStatus createFromParcel(Parcel source) {
            return new PropertyLegalStatus(source);
        }

        @Override
        public PropertyLegalStatus[] newArray(int size) {
            return new PropertyLegalStatus[size];
        }
    };
}
