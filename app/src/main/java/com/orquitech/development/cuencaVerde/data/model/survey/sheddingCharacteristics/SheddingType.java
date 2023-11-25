package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class SheddingType implements Parcelable {

    private boolean gathered;
    private boolean distributed;

    public boolean isGathered() {
        return gathered;
    }

    public void setGathered(boolean gathered) {
        this.gathered = gathered;
    }

    public boolean isDistributed() {
        return distributed;
    }

    public void setDistributed(boolean distributed) {
        this.distributed = distributed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.gathered ? (byte) 1 : (byte) 0);
        dest.writeByte(this.distributed ? (byte) 1 : (byte) 0);
    }

    public SheddingType() {
    }

    protected SheddingType(Parcel in) {
        this.gathered = in.readByte() != 0;
        this.distributed = in.readByte() != 0;
    }

    public static final Creator<SheddingType> CREATOR = new Creator<SheddingType>() {
        @Override
        public SheddingType createFromParcel(Parcel source) {
            return new SheddingType(source);
        }

        @Override
        public SheddingType[] newArray(int size) {
            return new SheddingType[size];
        }
    };
}
