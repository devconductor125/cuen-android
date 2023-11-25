package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class EnvironmentalDamage implements Parcelable {

    private boolean erosion;
    private String erosionQuantity;
    private boolean wetlandDesiccation;
    private String wetlandDesiccationQuantity;
    private boolean nativesLogging;
    private String nativesLoggingQuantity;
    private boolean burning;
    private String burningQuantity;
    private String others;
    private String comments;

    public boolean isErosion() {
        return erosion;
    }

    public void setErosion(boolean erosion) {
        this.erosion = erosion;
    }

    public String getErosionQuantity() {
        return erosionQuantity;
    }

    public void setErosionQuantity(String erosionQuantity) {
        this.erosionQuantity = erosionQuantity;
    }

    public boolean isWetlandDesiccation() {
        return wetlandDesiccation;
    }

    public void setWetlandDesiccation(boolean wetlandDesiccation) {
        this.wetlandDesiccation = wetlandDesiccation;
    }

    public String getWetlandDesiccationQuantity() {
        return wetlandDesiccationQuantity;
    }

    public void setWetlandDesiccationQuantity(String wetlandDesiccationQuantity) {
        this.wetlandDesiccationQuantity = wetlandDesiccationQuantity;
    }

    public boolean isNativesLogging() {
        return nativesLogging;
    }

    public void setNativesLogging(boolean nativesLogging) {
        this.nativesLogging = nativesLogging;
    }

    public String getNativesLoggingQuantity() {
        return nativesLoggingQuantity;
    }

    public void setNativesLoggingQuantity(String nativesLoggingQuantity) {
        this.nativesLoggingQuantity = nativesLoggingQuantity;
    }

    public boolean isBurning() {
        return burning;
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public String getBurningQuantity() {
        return burningQuantity;
    }

    public void setBurningQuantity(String burningQuantity) {
        this.burningQuantity = burningQuantity;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.erosion ? (byte) 1 : (byte) 0);
        dest.writeString(this.erosionQuantity);
        dest.writeByte(this.wetlandDesiccation ? (byte) 1 : (byte) 0);
        dest.writeString(this.wetlandDesiccationQuantity);
        dest.writeByte(this.nativesLogging ? (byte) 1 : (byte) 0);
        dest.writeString(this.nativesLoggingQuantity);
        dest.writeByte(this.burning ? (byte) 1 : (byte) 0);
        dest.writeString(this.burningQuantity);
        dest.writeString(this.others);
        dest.writeString(this.comments);
    }

    public EnvironmentalDamage() {
    }

    protected EnvironmentalDamage(Parcel in) {
        this.erosion = in.readByte() != 0;
        this.erosionQuantity = in.readString();
        this.wetlandDesiccation = in.readByte() != 0;
        this.wetlandDesiccationQuantity = in.readString();
        this.nativesLogging = in.readByte() != 0;
        this.nativesLoggingQuantity = in.readString();
        this.burning = in.readByte() != 0;
        this.burningQuantity = in.readString();
        this.others = in.readString();
        this.comments = in.readString();
    }

    public static final Creator<EnvironmentalDamage> CREATOR = new Creator<EnvironmentalDamage>() {
        @Override
        public EnvironmentalDamage createFromParcel(Parcel source) {
            return new EnvironmentalDamage(source);
        }

        @Override
        public EnvironmentalDamage[] newArray(int size) {
            return new EnvironmentalDamage[size];
        }
    };
}
