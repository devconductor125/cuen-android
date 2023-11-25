package com.orquitech.development.cuencaVerde.data.model.stardSheetForm.basicInformation;

import android.os.Parcel;
import android.os.Parcelable;

public class DemolitionRequirements implements Parcelable {

    private boolean yes;
    private boolean no;
    private String length;
    private String material;

    public boolean isYes() {
        return yes;
    }

    public void setYes(boolean yes) {
        this.yes = yes;
    }

    public boolean isNo() {
        return no;
    }

    public void setNo(boolean no) {
        this.no = no;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.yes ? (byte) 1 : (byte) 0);
        dest.writeByte(this.no ? (byte) 1 : (byte) 0);
        dest.writeString(this.length);
        dest.writeString(this.material);
    }

    public DemolitionRequirements() {
    }

    protected DemolitionRequirements(Parcel in) {
        this.yes = in.readByte() != 0;
        this.no = in.readByte() != 0;
        this.length = in.readString();
        this.material = in.readString();
    }

    public static final Creator<DemolitionRequirements> CREATOR = new Creator<DemolitionRequirements>() {
        @Override
        public DemolitionRequirements createFromParcel(Parcel source) {
            return new DemolitionRequirements(source);
        }

        @Override
        public DemolitionRequirements[] newArray(int size) {
            return new DemolitionRequirements[size];
        }
    };
}
