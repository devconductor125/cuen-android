package com.orquitech.development.cuencaVerde.data.model.survey;

import android.os.Parcel;
import android.os.Parcelable;

public class FamilyInformation implements Parcelable {

    private boolean manHousehold;
    private boolean womanHousehold;
    private boolean victimizedPopulation;
    private boolean isRegistered;
    private boolean disabilityInFamilyMember;
    private String whichDisability;
    private boolean cognitiveDisability;
    private boolean motorDisability;
    private boolean visualDisability;
    private boolean auditiveDisability;
    private String otherDisability;

    public boolean isManHousehold() {
        return manHousehold;
    }

    public void setManHousehold(boolean manHousehold) {
        this.manHousehold = manHousehold;
    }

    public boolean isWomanHousehold() {
        return womanHousehold;
    }

    public void setWomanHousehold(boolean womanHousehold) {
        this.womanHousehold = womanHousehold;
    }

    public boolean isVictimizedPopulation() {
        return victimizedPopulation;
    }

    public void setVictimizedPopulation(boolean victimizedPopulation) {
        this.victimizedPopulation = victimizedPopulation;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public boolean isDisabilityInFamilyMember() {
        return disabilityInFamilyMember;
    }

    public void setDisabilityInFamilyMember(boolean disabilityInFamilyMember) {
        this.disabilityInFamilyMember = disabilityInFamilyMember;
    }

    public String getWhichDisability() {
        return whichDisability;
    }

    public void setWhichDisability(String whichDisability) {
        this.whichDisability = whichDisability;
    }

    public boolean isCognitiveDisability() {
        return cognitiveDisability;
    }

    public void setCognitiveDisability(boolean cognitiveDisability) {
        this.cognitiveDisability = cognitiveDisability;
    }

    public boolean isMotorDisability() {
        return motorDisability;
    }

    public void setMotorDisability(boolean motorDisability) {
        this.motorDisability = motorDisability;
    }

    public boolean isVisualDisability() {
        return visualDisability;
    }

    public void setVisualDisability(boolean visualDisability) {
        this.visualDisability = visualDisability;
    }

    public boolean isAuditiveDisability() {
        return auditiveDisability;
    }

    public void setAuditiveDisability(boolean auditiveDisability) {
        this.auditiveDisability = auditiveDisability;
    }

    public String getOtherDisability() {
        return otherDisability;
    }

    public void setOtherDisability(String otherDisability) {
        this.otherDisability = otherDisability;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.manHousehold ? (byte) 1 : (byte) 0);
        dest.writeByte(this.womanHousehold ? (byte) 1 : (byte) 0);
        dest.writeByte(this.victimizedPopulation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isRegistered ? (byte) 1 : (byte) 0);
        dest.writeByte(this.disabilityInFamilyMember ? (byte) 1 : (byte) 0);
        dest.writeString(this.whichDisability);
        dest.writeByte(this.cognitiveDisability ? (byte) 1 : (byte) 0);
        dest.writeByte(this.motorDisability ? (byte) 1 : (byte) 0);
        dest.writeByte(this.visualDisability ? (byte) 1 : (byte) 0);
        dest.writeByte(this.auditiveDisability ? (byte) 1 : (byte) 0);
        dest.writeString(this.otherDisability);
    }

    public FamilyInformation() {
    }

    protected FamilyInformation(Parcel in) {
        this.manHousehold = in.readByte() != 0;
        this.womanHousehold = in.readByte() != 0;
        this.victimizedPopulation = in.readByte() != 0;
        this.isRegistered = in.readByte() != 0;
        this.disabilityInFamilyMember = in.readByte() != 0;
        this.whichDisability = in.readString();
        this.cognitiveDisability = in.readByte() != 0;
        this.motorDisability = in.readByte() != 0;
        this.visualDisability = in.readByte() != 0;
        this.auditiveDisability = in.readByte() != 0;
        this.otherDisability = in.readString();
    }

    public static final Creator<FamilyInformation> CREATOR = new Creator<FamilyInformation>() {
        @Override
        public FamilyInformation createFromParcel(Parcel source) {
            return new FamilyInformation(source);
        }

        @Override
        public FamilyInformation[] newArray(int size) {
            return new FamilyInformation[size];
        }
    };
}
