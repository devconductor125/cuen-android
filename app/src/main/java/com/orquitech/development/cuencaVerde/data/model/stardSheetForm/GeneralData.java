package com.orquitech.development.cuencaVerde.data.model.stardSheetForm;

import android.os.Parcel;
import android.os.Parcelable;

public class GeneralData implements Parcelable {

    private String municipality;
    private String propertyName;
    private String lane;
    private String catastralNumber;
    private String familyHead;
    private String mobileNumber;
    private String landLineNumber;
    private String personWhoAttendedTheVisit;
    private String numberOfHouseholdsThatWillConnectToTheSystem;
    private String populationToBeServed;

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getCatastralNumber() {
        return catastralNumber;
    }

    public void setCatastralNumber(String catastralNumber) {
        this.catastralNumber = catastralNumber;
    }

    public String getFamilyHead() {
        return familyHead;
    }

    public void setFamilyHead(String familyHead) {
        this.familyHead = familyHead;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLandLineNumber() {
        return landLineNumber;
    }

    public void setLandLineNumber(String landLineNumber) {
        this.landLineNumber = landLineNumber;
    }

    public String getPersonWhoAttendedTheVisit() {
        return personWhoAttendedTheVisit;
    }

    public void setPersonWhoAttendedTheVisit(String personWhoAttendedTheVisit) {
        this.personWhoAttendedTheVisit = personWhoAttendedTheVisit;
    }

    public String getNumberOfHouseholdsThatWillConnectToTheSystem() {
        return numberOfHouseholdsThatWillConnectToTheSystem;
    }

    public void setNumberOfHouseholdsThatWillConnectToTheSystem(String numberOfHouseholdsThatWillConnectToTheSystem) {
        this.numberOfHouseholdsThatWillConnectToTheSystem = numberOfHouseholdsThatWillConnectToTheSystem;
    }

    public String getPopulationToBeServed() {
        return populationToBeServed;
    }

    public void setPopulationToBeServed(String populationToBeServed) {
        this.populationToBeServed = populationToBeServed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.municipality);
        dest.writeString(this.propertyName);
        dest.writeString(this.lane);
        dest.writeString(this.catastralNumber);
        dest.writeString(this.familyHead);
        dest.writeString(this.mobileNumber);
        dest.writeString(this.landLineNumber);
        dest.writeString(this.personWhoAttendedTheVisit);
        dest.writeString(this.numberOfHouseholdsThatWillConnectToTheSystem);
        dest.writeString(this.populationToBeServed);
    }

    public GeneralData() {
    }

    protected GeneralData(Parcel in) {
        this.municipality = in.readString();
        this.propertyName = in.readString();
        this.lane = in.readString();
        this.catastralNumber = in.readString();
        this.familyHead = in.readString();
        this.mobileNumber = in.readString();
        this.landLineNumber = in.readString();
        this.personWhoAttendedTheVisit = in.readString();
        this.numberOfHouseholdsThatWillConnectToTheSystem = in.readString();
        this.populationToBeServed = in.readString();
    }

    public static final Creator<GeneralData> CREATOR = new Creator<GeneralData>() {
        @Override
        public GeneralData createFromParcel(Parcel source) {
            return new GeneralData(source);
        }

        @Override
        public GeneralData[] newArray(int size) {
            return new GeneralData[size];
        }
    };
}
