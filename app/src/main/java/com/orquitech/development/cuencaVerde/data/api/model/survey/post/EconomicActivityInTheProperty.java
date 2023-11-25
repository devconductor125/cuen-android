
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EconomicActivityInTheProperty {

    @SerializedName("agriculture")
    @Expose
    private boolean agriculture;
    @SerializedName("chemical")
    @Expose
    private boolean chemical;
    @SerializedName("commercial")
    @Expose
    private boolean commercial;
    @SerializedName("dairy_cattle")
    @Expose
    private boolean dairyCattle;
    @SerializedName("dual_purpose_cattle")
    @Expose
    private boolean dualPurposeCattle;
    @SerializedName("good_practices_certification")
    @Expose
    private boolean goodPracticesCertification;
    @SerializedName("handwork")
    @Expose
    private boolean handwork;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("machining")
    @Expose
    private boolean machining;
    @SerializedName("other")
    @Expose
    private String other;
    @SerializedName("porcine_farming")
    @Expose
    private boolean porcineFarming;
    @SerializedName("poultry_farming")
    @Expose
    private boolean poultryFarming;
    @SerializedName("production_area")
    @Expose
    private String productionArea;
    @SerializedName("property_area")
    @Expose
    private String propertyArea;
    @SerializedName("recreational")
    @Expose
    private boolean recreational;
    @SerializedName("residential")
    @Expose
    private boolean residential;
    @SerializedName("self_consumption")
    @Expose
    private boolean selfConsumption;
    @SerializedName("tillage_methods")
    @Expose
    private boolean tillageMethods;

    public boolean isAgriculture() {
        return agriculture;
    }

    public void setAgriculture(boolean agriculture) {
        this.agriculture = agriculture;
    }

    public boolean isChemical() {
        return chemical;
    }

    public void setChemical(boolean chemical) {
        this.chemical = chemical;
    }

    public boolean isCommercial() {
        return commercial;
    }

    public void setCommercial(boolean commercial) {
        this.commercial = commercial;
    }

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

    public boolean isGoodPracticesCertification() {
        return goodPracticesCertification;
    }

    public void setGoodPracticesCertification(boolean goodPracticesCertification) {
        this.goodPracticesCertification = goodPracticesCertification;
    }

    public boolean isHandwork() {
        return handwork;
    }

    public void setHandwork(boolean handwork) {
        this.handwork = handwork;
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

    public boolean isMachining() {
        return machining;
    }

    public void setMachining(boolean machining) {
        this.machining = machining;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public boolean isPorcineFarming() {
        return porcineFarming;
    }

    public void setPorcineFarming(boolean porcineFarming) {
        this.porcineFarming = porcineFarming;
    }

    public boolean isPoultryFarming() {
        return poultryFarming;
    }

    public void setPoultryFarming(boolean poultryFarming) {
        this.poultryFarming = poultryFarming;
    }

    public String getProductionArea() {
        return productionArea;
    }

    public void setProductionArea(String productionArea) {
        this.productionArea = productionArea;
    }

    public String getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(String propertyArea) {
        this.propertyArea = propertyArea;
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

}
