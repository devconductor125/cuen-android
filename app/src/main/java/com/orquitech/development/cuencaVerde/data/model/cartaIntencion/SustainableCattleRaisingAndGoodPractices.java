package com.orquitech.development.cuencaVerde.data.model.cartaIntencion;

import android.os.Parcel;
import android.os.Parcelable;

public class SustainableCattleRaisingAndGoodPractices implements Parcelable {

    boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private boolean cattlePass;
    private boolean saverSprue;
    private boolean waterTank;
    private boolean sparseTrees;
    private boolean electricFence;

    public boolean isCattlePass() {
        return cattlePass;
    }

    public void setCattlePass(boolean cattlePass) {
        this.cattlePass = cattlePass;
    }

    public boolean isSaverSprue() {
        return saverSprue;
    }

    public void setSaverSprue(boolean saverSprue) {
        this.saverSprue = saverSprue;
    }

    public boolean isWaterTank() {
        return waterTank;
    }

    public void setWaterTank(boolean waterTank) {
        this.waterTank = waterTank;
    }

    public boolean isSparseTrees() {
        return sparseTrees;
    }

    public void setSparseTrees(boolean sparseTrees) {
        this.sparseTrees = sparseTrees;
    }

    public boolean isElectricFence() {
        return electricFence;
    }

    public void setElectricFence(boolean electricFence) {
        this.electricFence = electricFence;
    }

    public SustainableCattleRaisingAndGoodPractices() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.cattlePass ? (byte) 1 : (byte) 0);
        dest.writeByte(this.saverSprue ? (byte) 1 : (byte) 0);
        dest.writeByte(this.waterTank ? (byte) 1 : (byte) 0);
        dest.writeByte(this.sparseTrees ? (byte) 1 : (byte) 0);
        dest.writeByte(this.electricFence ? (byte) 1 : (byte) 0);
    }

    protected SustainableCattleRaisingAndGoodPractices(Parcel in) {
        this.checked = in.readByte() != 0;
        this.cattlePass = in.readByte() != 0;
        this.saverSprue = in.readByte() != 0;
        this.waterTank = in.readByte() != 0;
        this.sparseTrees = in.readByte() != 0;
        this.electricFence = in.readByte() != 0;
    }

    public static final Creator<SustainableCattleRaisingAndGoodPractices> CREATOR = new Creator<SustainableCattleRaisingAndGoodPractices>() {
        @Override
        public SustainableCattleRaisingAndGoodPractices createFromParcel(Parcel source) {
            return new SustainableCattleRaisingAndGoodPractices(source);
        }

        @Override
        public SustainableCattleRaisingAndGoodPractices[] newArray(int size) {
            return new SustainableCattleRaisingAndGoodPractices[size];
        }
    };
}
