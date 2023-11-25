package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class DomesticSolidWasteHarvestingHandling implements Parcelable {

    private boolean separation;
    private boolean burning;
    private boolean recycling;
    private boolean harnessing;
    private boolean none;

    public boolean isSeparation() {
        return separation;
    }

    public void setSeparation(boolean separation) {
        this.separation = separation;
    }

    public boolean isBurning() {
        return burning;
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public boolean isRecycling() {
        return recycling;
    }

    public void setRecycling(boolean recycling) {
        this.recycling = recycling;
    }

    public boolean isHarnessing() {
        return harnessing;
    }

    public void setHarnessing(boolean harnessing) {
        this.harnessing = harnessing;
    }

    public boolean isNone() {
        return none;
    }

    public void setNone(boolean none) {
        this.none = none;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.separation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.burning ? (byte) 1 : (byte) 0);
        dest.writeByte(this.recycling ? (byte) 1 : (byte) 0);
        dest.writeByte(this.harnessing ? (byte) 1 : (byte) 0);
        dest.writeByte(this.none ? (byte) 1 : (byte) 0);
    }

    public DomesticSolidWasteHarvestingHandling() {
    }

    protected DomesticSolidWasteHarvestingHandling(Parcel in) {
        this.separation = in.readByte() != 0;
        this.burning = in.readByte() != 0;
        this.recycling = in.readByte() != 0;
        this.harnessing = in.readByte() != 0;
        this.none = in.readByte() != 0;
    }

    public static final Creator<DomesticSolidWasteHarvestingHandling> CREATOR = new Creator<DomesticSolidWasteHarvestingHandling>() {
        @Override
        public DomesticSolidWasteHarvestingHandling createFromParcel(Parcel source) {
            return new DomesticSolidWasteHarvestingHandling(source);
        }

        @Override
        public DomesticSolidWasteHarvestingHandling[] newArray(int size) {
            return new DomesticSolidWasteHarvestingHandling[size];
        }
    };
}
