package com.orquitech.development.cuencaVerde.data.model.contractorForm;

import android.os.Parcel;
import android.os.Parcelable;

public class WorkForce implements Parcelable {

    private boolean professional;
    private boolean technical;
    private boolean technological;
    private boolean unqualified;

    public boolean isProfessional() {
        return professional;
    }

    public void setProfessional(boolean professional) {
        this.professional = professional;
    }

    public boolean isTechnical() {
        return technical;
    }

    public void setTechnical(boolean technical) {
        this.technical = technical;
    }

    public boolean isTechnological() {
        return technological;
    }

    public void setTechnological(boolean technological) {
        this.technological = technological;
    }

    public boolean isUnqualified() {
        return unqualified;
    }

    public void setUnqualified(boolean unqualified) {
        this.unqualified = unqualified;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.professional ? (byte) 1 : (byte) 0);
        dest.writeByte(this.technical ? (byte) 1 : (byte) 0);
        dest.writeByte(this.technological ? (byte) 1 : (byte) 0);
        dest.writeByte(this.unqualified ? (byte) 1 : (byte) 0);
    }

    public WorkForce() {
    }

    protected WorkForce(Parcel in) {
        this.professional = in.readByte() != 0;
        this.technical = in.readByte() != 0;
        this.technological = in.readByte() != 0;
        this.unqualified = in.readByte() != 0;
    }

    public static final Creator<WorkForce> CREATOR = new Creator<WorkForce>() {
        @Override
        public WorkForce createFromParcel(Parcel source) {
            return new WorkForce(source);
        }

        @Override
        public WorkForce[] newArray(int size) {
            return new WorkForce[size];
        }
    };
}
