package com.orquitech.development.cuencaVerde.data.model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.utils.FormUtils;

public class PQRS implements Parcelable {

    private String idNumber;
    private String contactName;
    private boolean agreementCorporation;
    private boolean subscribeAgreement;
    private String description;
    private boolean createPredioPotencial;
    private Location location;
    private String propertyName;
    private Dependency dependency;
    private PQRSType pqrsType;
    private String email;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public PQRS() {
        setDependency(new Dependency());
        setPqrsType(new PQRSType());
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAgreementCorporation() {
        return agreementCorporation;
    }

    public void setAgreementCorporation(boolean agreementCorporation) {
        this.agreementCorporation = agreementCorporation;
    }

    public boolean isSubscribeAgreement() {
        return subscribeAgreement;
    }

    public void setSubscribeAgreement(boolean subscribeAgreement) {
        this.subscribeAgreement = subscribeAgreement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCreatePredioPotencial() {
        return createPredioPotencial;
    }

    public void setCreatePredioPotencial(boolean createPredioPotencial) {
        this.createPredioPotencial = createPredioPotencial;
    }

    public String getLatitude() {
        if (location == null) {
            location = new Location("");
            location.setLatitude(0);
            location.setLongitude(0);
        }
        return String.valueOf(location.getLatitude());
    }

    public void setLatitude(String latitude) {
        this.location.setLatitude(Double.parseDouble(latitude));
    }

    public String getLongitude() {
        if (location == null) {
            location = new Location("");
            location.setLatitude(0);
            location.setLongitude(0);
        }
        return String.valueOf(location.getLongitude());
    }

    public void setLongitude(String longitude) {
        this.location.setLongitude(Double.parseDouble(longitude));
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPropertyName() {
        return propertyName != null ? propertyName : "";
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    public PQRSType getPqrsType() {
        return pqrsType;
    }

    public void setPqrsType(PQRSType pqrsType) {
        this.pqrsType = pqrsType;
    }

    public boolean hasValidCoordinates() {
        return location.getLatitude() != 0 && location.getLongitude() != 0;
    }

    public boolean hasValidName() {
        return !TextUtils.isEmpty(getContactName());
    }

    public boolean hasValidEmailAddress() {
        return FormUtils.isEmailValid(email);
    }

    public boolean hasValidPredioName() {
        return !TextUtils.isEmpty(getPropertyName());
    }

    public boolean hasValidIdNumber() {
        return !TextUtils.isEmpty(getIdNumber());
    }

    public boolean hasDependency() {
        return getDependency() != null && !TextUtils.isEmpty(getDependency().getTitle());
    }

    public boolean hasPqrsType() {
        return getPqrsType() != null && !TextUtils.isEmpty(getPqrsType().getTitle());
    }

    public boolean hasDescription() {
        return !TextUtils.isEmpty(getDescription());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idNumber);
        dest.writeString(this.contactName);
        dest.writeByte(this.agreementCorporation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.subscribeAgreement ? (byte) 1 : (byte) 0);
        dest.writeString(this.description);
        dest.writeByte(this.createPredioPotencial ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.propertyName);
        dest.writeParcelable(this.dependency, flags);
        dest.writeParcelable(this.pqrsType, flags);
        dest.writeString(this.email);
    }

    protected PQRS(Parcel in) {
        this.idNumber = in.readString();
        this.contactName = in.readString();
        this.agreementCorporation = in.readByte() != 0;
        this.subscribeAgreement = in.readByte() != 0;
        this.description = in.readString();
        this.createPredioPotencial = in.readByte() != 0;
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.propertyName = in.readString();
        this.dependency = in.readParcelable(Dependency.class.getClassLoader());
        this.pqrsType = in.readParcelable(PQRSType.class.getClassLoader());
        this.email = in.readString();
    }

    public static final Creator<PQRS> CREATOR = new Creator<PQRS>() {
        @Override
        public PQRS createFromParcel(Parcel source) {
            return new PQRS(source);
        }

        @Override
        public PQRS[] newArray(int size) {
            return new PQRS[size];
        }
    };
}
