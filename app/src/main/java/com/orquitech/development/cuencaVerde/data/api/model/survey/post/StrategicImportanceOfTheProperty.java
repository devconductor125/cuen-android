
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StrategicImportanceOfTheProperty {

    @SerializedName("biodiversity_conservation")
    @Expose
    private boolean biodiversityConservation;
    @SerializedName("carbon_bonds_sale_certification")
    @Expose
    private boolean carbonBondsSaleCertification;
    @SerializedName("ecological_connectivity")
    @Expose
    private boolean ecologicalConnectivity;
    @SerializedName("high_degree_of_conservation_forest")
    @Expose
    private boolean highDegreeOfConservationForest;
    @SerializedName("productive_water_source")
    @Expose
    private boolean productiveWaterSource;
    @SerializedName("psa")
    @Expose
    private String psa;
    @SerializedName("sequestered_carbon")
    @Expose
    private boolean sequesteredCarbon;
    @SerializedName("supply_source")
    @Expose
    private boolean supplySource;
    @SerializedName("water_quality_improvement")
    @Expose
    private boolean waterQualityImprovement;
    @SerializedName("water_regulation")
    @Expose
    private boolean waterRegulation;

    public boolean isBiodiversityConservation() {
        return biodiversityConservation;
    }

    public void setBiodiversityConservation(boolean biodiversityConservation) {
        this.biodiversityConservation = biodiversityConservation;
    }

    public boolean isCarbonBondsSaleCertification() {
        return carbonBondsSaleCertification;
    }

    public void setCarbonBondsSaleCertification(boolean carbonBondsSaleCertification) {
        this.carbonBondsSaleCertification = carbonBondsSaleCertification;
    }

    public boolean isEcologicalConnectivity() {
        return ecologicalConnectivity;
    }

    public void setEcologicalConnectivity(boolean ecologicalConnectivity) {
        this.ecologicalConnectivity = ecologicalConnectivity;
    }

    public boolean isHighDegreeOfConservationForest() {
        return highDegreeOfConservationForest;
    }

    public void setHighDegreeOfConservationForest(boolean highDegreeOfConservationForest) {
        this.highDegreeOfConservationForest = highDegreeOfConservationForest;
    }

    public boolean isProductiveWaterSource() {
        return productiveWaterSource;
    }

    public void setProductiveWaterSource(boolean productiveWaterSource) {
        this.productiveWaterSource = productiveWaterSource;
    }

    public String getPsa() {
        return psa;
    }

    public void setPsa(String psa) {
        this.psa = psa;
    }

    public boolean isSequesteredCarbon() {
        return sequesteredCarbon;
    }

    public void setSequesteredCarbon(boolean sequesteredCarbon) {
        this.sequesteredCarbon = sequesteredCarbon;
    }

    public boolean isSupplySource() {
        return supplySource;
    }

    public void setSupplySource(boolean supplySource) {
        this.supplySource = supplySource;
    }

    public boolean isWaterQualityImprovement() {
        return waterQualityImprovement;
    }

    public void setWaterQualityImprovement(boolean waterQualityImprovement) {
        this.waterQualityImprovement = waterQualityImprovement;
    }

    public boolean isWaterRegulation() {
        return waterRegulation;
    }

    public void setWaterRegulation(boolean waterRegulation) {
        this.waterRegulation = waterRegulation;
    }

}
