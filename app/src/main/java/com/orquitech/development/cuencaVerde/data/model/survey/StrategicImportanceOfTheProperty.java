package com.orquitech.development.cuencaVerde.data.model.survey;

import android.os.Parcel;
import android.os.Parcelable;

public class StrategicImportanceOfTheProperty implements Parcelable {

    private boolean ecologicalConnectivity;
    private boolean waterQualityImprovement;
    private boolean supplySource;
    private boolean productiveWaterSource;
    private boolean waterRegulation;
    private boolean biodiversityConservation;
    private boolean sequesteredCarbon;
    private boolean highDegreeOfConservationForest;
    private boolean carbonBondsSaleCertification;
    private String psa;

    public boolean isEcologicalConnectivity() {
        return ecologicalConnectivity;
    }

    public void setEcologicalConnectivity(boolean ecologicalConnectivity) {
        this.ecologicalConnectivity = ecologicalConnectivity;
    }

    public boolean isWaterQualityImprovement() {
        return waterQualityImprovement;
    }

    public void setWaterQualityImprovement(boolean waterQualityImprovement) {
        this.waterQualityImprovement = waterQualityImprovement;
    }

    public boolean isSupplySource() {
        return supplySource;
    }

    public void setSupplySource(boolean supplySource) {
        this.supplySource = supplySource;
    }

    public boolean isProductiveWaterSource() {
        return productiveWaterSource;
    }

    public void setProductiveWaterSource(boolean productiveWaterSource) {
        this.productiveWaterSource = productiveWaterSource;
    }

    public boolean isWaterRegulation() {
        return waterRegulation;
    }

    public void setWaterRegulation(boolean waterRegulation) {
        this.waterRegulation = waterRegulation;
    }

    public boolean isBiodiversityConservation() {
        return biodiversityConservation;
    }

    public void setBiodiversityConservation(boolean biodiversityConservation) {
        this.biodiversityConservation = biodiversityConservation;
    }

    public boolean isSequesteredCarbon() {
        return sequesteredCarbon;
    }

    public void setSequesteredCarbon(boolean sequesteredCarbon) {
        this.sequesteredCarbon = sequesteredCarbon;
    }

    public boolean isHighDegreeOfConservationForest() {
        return highDegreeOfConservationForest;
    }

    public void setHighDegreeOfConservationForest(boolean highDegreeOfConservationForest) {
        this.highDegreeOfConservationForest = highDegreeOfConservationForest;
    }

    public boolean isCarbonBondsSaleCertification() {
        return carbonBondsSaleCertification;
    }

    public void setCarbonBondsSaleCertification(boolean carbonBondsSaleCertification) {
        this.carbonBondsSaleCertification = carbonBondsSaleCertification;
    }

    public String getPsa() {
        return psa;
    }

    public void setPsa(String psa) {
        this.psa = psa;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.ecologicalConnectivity ? (byte) 1 : (byte) 0);
        dest.writeByte(this.waterQualityImprovement ? (byte) 1 : (byte) 0);
        dest.writeByte(this.supplySource ? (byte) 1 : (byte) 0);
        dest.writeByte(this.productiveWaterSource ? (byte) 1 : (byte) 0);
        dest.writeByte(this.waterRegulation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.biodiversityConservation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.sequesteredCarbon ? (byte) 1 : (byte) 0);
        dest.writeByte(this.highDegreeOfConservationForest ? (byte) 1 : (byte) 0);
        dest.writeByte(this.carbonBondsSaleCertification ? (byte) 1 : (byte) 0);
        dest.writeString(this.psa);
    }

    public StrategicImportanceOfTheProperty() {
    }

    protected StrategicImportanceOfTheProperty(Parcel in) {
        this.ecologicalConnectivity = in.readByte() != 0;
        this.waterQualityImprovement = in.readByte() != 0;
        this.supplySource = in.readByte() != 0;
        this.productiveWaterSource = in.readByte() != 0;
        this.waterRegulation = in.readByte() != 0;
        this.biodiversityConservation = in.readByte() != 0;
        this.sequesteredCarbon = in.readByte() != 0;
        this.highDegreeOfConservationForest = in.readByte() != 0;
        this.carbonBondsSaleCertification = in.readByte() != 0;
        this.psa = in.readString();
    }

    public static final Creator<StrategicImportanceOfTheProperty> CREATOR = new Creator<StrategicImportanceOfTheProperty>() {
        @Override
        public StrategicImportanceOfTheProperty createFromParcel(Parcel source) {
            return new StrategicImportanceOfTheProperty(source);
        }

        @Override
        public StrategicImportanceOfTheProperty[] newArray(int size) {
            return new StrategicImportanceOfTheProperty[size];
        }
    };
}
