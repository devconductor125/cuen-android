package com.orquitech.development.cuencaVerde.data.model.survey;

import android.os.Parcel;
import android.os.Parcelable;

public class EconomicActivityInTheProperty implements Parcelable {

    private boolean dairyCattle;
    private boolean dualPurposeCattle;
    private boolean agriculture;
    private boolean poultryFarming;
    private boolean porcineFarming;
    private boolean commercial;
    private boolean recreational;
    private boolean residential;
    private boolean goodPracticesCertification;
    private boolean selfConsumption;
    private boolean tillageMethods;
    private boolean machining;
    private boolean handwork;
    private boolean chemical;
    private String other;
    private String propertyArea;
    private String productionArea;
    private String latitude;
    private String longitude;

    public boolean isDairyCattle() {
        return dairyCattle;
    }

    public void setDairyCattle(boolean dairyCattle) {
        this.dairyCattle = dairyCattle;
    }

    public boolean isDualPurposeCattle() {
        return dualPurposeCattle;
    }

    public void setDualPurposeCattle(boolean dualPurposeCattle) {
        this.dualPurposeCattle = dualPurposeCattle;
    }

    public boolean isAgriculture() {
        return agriculture;
    }

    public void setAgriculture(boolean agriculture) {
        this.agriculture = agriculture;
    }

    public boolean isPoultryFarming() {
        return poultryFarming;
    }

    public void setPoultryFarming(boolean poultryFarming) {
        this.poultryFarming = poultryFarming;
    }

    public boolean isPorcineFarming() {
        return porcineFarming;
    }

    public void setPorcineFarming(boolean porcineFarming) {
        this.porcineFarming = porcineFarming;
    }

    public boolean isCommercial() {
        return commercial;
    }

    public void setCommercial(boolean commercial) {
        this.commercial = commercial;
    }

    public boolean isRecreational() {
        return recreational;
    }

    public void setRecreational(boolean recreational) {
        this.recreational = recreational;
    }

    public boolean isResidential() {
        return residential;
    }

    public void setResidential(boolean residential) {
        this.residential = residential;
    }

    public boolean isGoodPracticesCertification() {
        return goodPracticesCertification;
    }

    public void setGoodPracticesCertification(boolean goodPracticesCertification) {
        this.goodPracticesCertification = goodPracticesCertification;
    }

    public boolean isSelfConsumption() {
        return selfConsumption;
    }

    public void setSelfConsumption(boolean selfConsumption) {
        this.selfConsumption = selfConsumption;
    }

    public boolean isTillageMethods() {
        return tillageMethods;
    }

    public void setTillageMethods(boolean tillageMethods) {
        this.tillageMethods = tillageMethods;
    }

    public boolean isMachining() {
        return machining;
    }

    public void setMachining(boolean machining) {
        this.machining = machining;
    }

    public boolean isHandwork() {
        return handwork;
    }

    public void setHandwork(boolean handwork) {
        this.handwork = handwork;
    }

    public boolean isChemical() {
        return chemical;
    }

    public void setChemical(boolean chemical) {
        this.chemical = chemical;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(String propertyArea) {
        this.propertyArea = propertyArea;
    }

    public String getProductionArea() {
        return productionArea;
    }

    public void setProductionArea(String productionArea) {
        this.productionArea = productionArea;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.dairyCattle ? (byte) 1 : (byte) 0);
        dest.writeByte(this.dualPurposeCattle ? (byte) 1 : (byte) 0);
        dest.writeByte(this.agriculture ? (byte) 1 : (byte) 0);
        dest.writeByte(this.poultryFarming ? (byte) 1 : (byte) 0);
        dest.writeByte(this.porcineFarming ? (byte) 1 : (byte) 0);
        dest.writeByte(this.commercial ? (byte) 1 : (byte) 0);
        dest.writeByte(this.recreational ? (byte) 1 : (byte) 0);
        dest.writeByte(this.residential ? (byte) 1 : (byte) 0);
        dest.writeByte(this.goodPracticesCertification ? (byte) 1 : (byte) 0);
        dest.writeByte(this.selfConsumption ? (byte) 1 : (byte) 0);
        dest.writeByte(this.tillageMethods ? (byte) 1 : (byte) 0);
        dest.writeByte(this.machining ? (byte) 1 : (byte) 0);
        dest.writeByte(this.handwork ? (byte) 1 : (byte) 0);
        dest.writeByte(this.chemical ? (byte) 1 : (byte) 0);
        dest.writeString(this.other);
        dest.writeString(this.propertyArea);
        dest.writeString(this.productionArea);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
    }

    public EconomicActivityInTheProperty() {
    }

    protected EconomicActivityInTheProperty(Parcel in) {
        this.dairyCattle = in.readByte() != 0;
        this.dualPurposeCattle = in.readByte() != 0;
        this.agriculture = in.readByte() != 0;
        this.poultryFarming = in.readByte() != 0;
        this.porcineFarming = in.readByte() != 0;
        this.commercial = in.readByte() != 0;
        this.recreational = in.readByte() != 0;
        this.residential = in.readByte() != 0;
        this.goodPracticesCertification = in.readByte() != 0;
        this.selfConsumption = in.readByte() != 0;
        this.tillageMethods = in.readByte() != 0;
        this.machining = in.readByte() != 0;
        this.handwork = in.readByte() != 0;
        this.chemical = in.readByte() != 0;
        this.other = in.readString();
        this.propertyArea = in.readString();
        this.productionArea = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
    }

    public static final Creator<EconomicActivityInTheProperty> CREATOR = new Creator<EconomicActivityInTheProperty>() {
        @Override
        public EconomicActivityInTheProperty createFromParcel(Parcel source) {
            return new EconomicActivityInTheProperty(source);
        }

        @Override
        public EconomicActivityInTheProperty[] newArray(int size) {
            return new EconomicActivityInTheProperty[size];
        }
    };
}
