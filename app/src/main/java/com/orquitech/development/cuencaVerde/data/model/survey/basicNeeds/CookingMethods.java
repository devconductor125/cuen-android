package com.orquitech.development.cuencaVerde.data.model.survey.basicNeeds;

import android.os.Parcel;
import android.os.Parcelable;

public class CookingMethods implements Parcelable {

    private boolean naturalGas;
    private boolean gasBottle;
    private boolean electricity;
    private boolean biodigester;
    private boolean firewood;
    private String other;

    public boolean isNaturalGas() {
        return naturalGas;
    }

    public void setNaturalGas(boolean naturalGas) {
        this.naturalGas = naturalGas;
    }

    public boolean isGasBottle() {
        return gasBottle;
    }

    public void setGasBottle(boolean gasBottle) {
        this.gasBottle = gasBottle;
    }

    public boolean isElectricity() {
        return electricity;
    }

    public void setElectricity(boolean electricity) {
        this.electricity = electricity;
    }

    public boolean isBiodigester() {
        return biodigester;
    }

    public void setBiodigester(boolean biodigester) {
        this.biodigester = biodigester;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.naturalGas ? (byte) 1 : (byte) 0);
        dest.writeByte(this.gasBottle ? (byte) 1 : (byte) 0);
        dest.writeByte(this.electricity ? (byte) 1 : (byte) 0);
        dest.writeByte(this.biodigester ? (byte) 1 : (byte) 0);
        dest.writeByte(this.firewood ? (byte) 1 : (byte) 0);
        dest.writeString(this.other);
    }

    public CookingMethods() {
    }

    protected CookingMethods(Parcel in) {
        this.naturalGas = in.readByte() != 0;
        this.gasBottle = in.readByte() != 0;
        this.electricity = in.readByte() != 0;
        this.biodigester = in.readByte() != 0;
        this.firewood = in.readByte() != 0;
        this.other = in.readString();
    }

    public static final Creator<CookingMethods> CREATOR = new Creator<CookingMethods>() {
        @Override
        public CookingMethods createFromParcel(Parcel source) {
            return new CookingMethods(source);
        }

        @Override
        public CookingMethods[] newArray(int size) {
            return new CookingMethods[size];
        }
    };
}
