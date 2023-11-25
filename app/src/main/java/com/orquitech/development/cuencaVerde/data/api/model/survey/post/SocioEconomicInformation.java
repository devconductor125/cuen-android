
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocioEconomicInformation {

    @SerializedName("family_compensation_fund")
    @Expose
    private boolean familyCompensationFund;
    @SerializedName("family_compensation_fund_name")
    @Expose
    private String familyCompensationFundName;
    @SerializedName("housing_units")
    @Expose
    private String housingUnits;
    @SerializedName("housing_units_population")
    @Expose
    private String housingUnitsPopulation;
    @SerializedName("number_of_family_groups")
    @Expose
    private String numberOfFamilyGroups;
    @SerializedName("sisben")
    @Expose
    private boolean sisben;
    @SerializedName("sisben_level")
    @Expose
    private String sisbenLevel;
    @SerializedName("socioeconomic_layer")
    @Expose
    private String socioeconomicLayer;
    @SerializedName("why_has_hasnt_family_compensation_fund")
    @Expose
    private String whyHasHasntFamilyCompensationFund;

    public boolean isFamilyCompensationFund() {
        return familyCompensationFund;
    }

    public void setFamilyCompensationFund(boolean familyCompensationFund) {
        this.familyCompensationFund = familyCompensationFund;
    }

    public String getFamilyCompensationFundName() {
        return familyCompensationFundName;
    }

    public void setFamilyCompensationFundName(String familyCompensationFundName) {
        this.familyCompensationFundName = familyCompensationFundName;
    }

    public String getHousingUnits() {
        return housingUnits;
    }

    public void setHousingUnits(String housingUnits) {
        this.housingUnits = housingUnits;
    }

    public String getHousingUnitsPopulation() {
        return housingUnitsPopulation;
    }

    public void setHousingUnitsPopulation(String housingUnitsPopulation) {
        this.housingUnitsPopulation = housingUnitsPopulation;
    }

    public String getNumberOfFamilyGroups() {
        return numberOfFamilyGroups;
    }

    public void setNumberOfFamilyGroups(String numberOfFamilyGroups) {
        this.numberOfFamilyGroups = numberOfFamilyGroups;
    }

    public boolean isSisben() {
        return sisben;
    }

    public void setSisben(boolean sisben) {
        this.sisben = sisben;
    }

    public String getSisbenLevel() {
        return sisbenLevel;
    }

    public void setSisbenLevel(String sisbenLevel) {
        this.sisbenLevel = sisbenLevel;
    }

    public String getSocioeconomicLayer() {
        return socioeconomicLayer;
    }

    public void setSocioeconomicLayer(String socioeconomicLayer) {
        this.socioeconomicLayer = socioeconomicLayer;
    }

    public String getWhyHasHasntFamilyCompensationFund() {
        return whyHasHasntFamilyCompensationFund;
    }

    public void setWhyHasHasntFamilyCompensationFund(String whyHasHasntFamilyCompensationFund) {
        this.whyHasHasntFamilyCompensationFund = whyHasHasntFamilyCompensationFund;
    }

}
