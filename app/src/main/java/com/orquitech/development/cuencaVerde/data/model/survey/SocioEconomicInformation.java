package com.orquitech.development.cuencaVerde.data.model.survey;

import android.os.Parcel;
import android.os.Parcelable;

public class SocioEconomicInformation implements Parcelable {

    private String socioeconomicLayer;
    private String numberOfFamilyGroups;
    private String housingUnits;
    private String impactedHousingUnits;
    private String impactedPeople;
    private String women;
    private String men;
    private String children;
    private String housingUnitsPopulation;
    private boolean sisben;
    private String sisbenLevel;
    private boolean familyCompensationFund;
    private String familyCompensationFundName;
    private String whyHasHasntFamilyCompensationFund;

    public String getSocioeconomicLayer() {
        return socioeconomicLayer;
    }

    public void setSocioeconomicLayer(String socioeconomicLayer) {
        this.socioeconomicLayer = socioeconomicLayer;
    }

    public String getNumberOfFamilyGroups() {
        return numberOfFamilyGroups;
    }

    public void setNumberOfFamilyGroups(String numberOfFamilyGroups) {
        this.numberOfFamilyGroups = numberOfFamilyGroups;
    }

    public String getHousingUnits() {
        return housingUnits;
    }

    public void setHousingUnits(String housingUnits) {
        this.housingUnits = housingUnits;
    }

    public String getImpactedHousingUnits() {
        return impactedHousingUnits;
    }

    public void setImpactedHousingUnits(String impactedHousingUnits) {
        this.impactedHousingUnits = impactedHousingUnits;
    }

    public String getImpactedPeople() {
        return impactedPeople;
    }

    public void setImpactedPeople(String impactedPeople) {
        this.impactedPeople = impactedPeople;
    }

    public String getWomen() {
        return women;
    }

    public void setWomen(String women) {
        this.women = women;
    }

    public String getMen() {
        return men;
    }

    public void setMen(String men) {
        this.men = men;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getHousingUnitsPopulation() {
        return housingUnitsPopulation;
    }

    public void setHousingUnitsPopulation(String housingUnitsPopulation) {
        this.housingUnitsPopulation = housingUnitsPopulation;
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

    public String getWhyHasHasntFamilyCompensationFund() {
        return whyHasHasntFamilyCompensationFund;
    }

    public void setWhyHasHasntFamilyCompensationFund(String whyHasHasntFamilyCompensationFund) {
        this.whyHasHasntFamilyCompensationFund = whyHasHasntFamilyCompensationFund;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.socioeconomicLayer);
        dest.writeString(this.numberOfFamilyGroups);
        dest.writeString(this.housingUnits);
        dest.writeString(this.impactedHousingUnits);
        dest.writeString(this.impactedPeople);
        dest.writeString(this.women);
        dest.writeString(this.men);
        dest.writeString(this.children);
        dest.writeString(this.housingUnitsPopulation);
        dest.writeByte(this.sisben ? (byte) 1 : (byte) 0);
        dest.writeString(this.sisbenLevel);
        dest.writeByte(this.familyCompensationFund ? (byte) 1 : (byte) 0);
        dest.writeString(this.familyCompensationFundName);
        dest.writeString(this.whyHasHasntFamilyCompensationFund);
    }

    public SocioEconomicInformation() {
    }

    protected SocioEconomicInformation(Parcel in) {
        this.socioeconomicLayer = in.readString();
        this.numberOfFamilyGroups = in.readString();
        this.housingUnits = in.readString();
        this.impactedHousingUnits = in.readString();
        this.impactedPeople = in.readString();
        this.women = in.readString();
        this.men = in.readString();
        this.children = in.readString();
        this.housingUnitsPopulation = in.readString();
        this.sisben = in.readByte() != 0;
        this.sisbenLevel = in.readString();
        this.familyCompensationFund = in.readByte() != 0;
        this.familyCompensationFundName = in.readString();
        this.whyHasHasntFamilyCompensationFund = in.readString();
    }

    public static final Creator<SocioEconomicInformation> CREATOR = new Creator<SocioEconomicInformation>() {
        @Override
        public SocioEconomicInformation createFromParcel(Parcel source) {
            return new SocioEconomicInformation(source);
        }

        @Override
        public SocioEconomicInformation[] newArray(int size) {
            return new SocioEconomicInformation[size];
        }
    };
}
