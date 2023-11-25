package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class WhoDoesTheSewerageSystemMaintenance implements Parcelable {

    private boolean owner;
    private boolean contractor;
    private boolean other;
    private String landLineNumber;
    private String mobileNumber;

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isContractor() {
        return contractor;
    }

    public void setContractor(boolean contractor) {
        this.contractor = contractor;
    }

    public boolean isOther() {
        return other;
    }

    public void setOther(boolean other) {
        this.other = other;
    }

    public String getLandLineNumber() {
        return landLineNumber;
    }

    public void setLandLineNumber(String landLineNumber) {
        this.landLineNumber = landLineNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.owner ? (byte) 1 : (byte) 0);
        dest.writeByte(this.contractor ? (byte) 1 : (byte) 0);
        dest.writeByte(this.other ? (byte) 1 : (byte) 0);
        dest.writeString(this.landLineNumber);
        dest.writeString(this.mobileNumber);
    }

    public WhoDoesTheSewerageSystemMaintenance() {
    }

    protected WhoDoesTheSewerageSystemMaintenance(Parcel in) {
        this.owner = in.readByte() != 0;
        this.contractor = in.readByte() != 0;
        this.other = in.readByte() != 0;
        this.landLineNumber = in.readString();
        this.mobileNumber = in.readString();
    }

    public static final Creator<WhoDoesTheSewerageSystemMaintenance> CREATOR = new Creator<WhoDoesTheSewerageSystemMaintenance>() {
        @Override
        public WhoDoesTheSewerageSystemMaintenance createFromParcel(Parcel source) {
            return new WhoDoesTheSewerageSystemMaintenance(source);
        }

        @Override
        public WhoDoesTheSewerageSystemMaintenance[] newArray(int size) {
            return new WhoDoesTheSewerageSystemMaintenance[size];
        }
    };
}
