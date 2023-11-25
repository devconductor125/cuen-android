package com.orquitech.development.cuencaVerde.data.model.survey;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.BaseItem;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.data.model.survey.basicNeeds.BasicNeeds;
import com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics.EnvironmentalDamage;
import com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics.SheddingCharacteristics;

public class Survey extends BaseItem implements Parcelable {

    private Contact contact;
    private AppDate propertyVisitDate;
    private String propertyName;
    private String propertyCorrelation;
    private int propertyCorrelationId;
    private String propertyCorrelationOther;
    private boolean propertyColantaPartner;
    private boolean propertyMilkMerchant;
    private PropertyType propertyType;
    private String propertyRetailName;
    private String propertySector;
    private String propertyReservoir;
    private boolean propertyReservoirRioGrande;
    private boolean propertyReservoirLaFe;
    private PropertyLegalStatus propertyLegalStatus;
    private String address;
    private String nit;
    private String municipality;
    private String zone;
    private boolean ruralZone;
    private boolean urbanZone;
    private String township;
    private String lane;
    private String microBasin;
    private String hydrologicalSource;
    private NaturalEcosystemsInTheProperty naturalEcosystemsInTheProperty;
    private StrategicImportanceOfTheProperty strategicImportanceOfTheProperty;
    private SocioEconomicInformation socioEconomicInformation;
    private FamilyInformation familyInformation;
    private WaysOfAccess waysOfAccess;
    private EconomicActivityInTheProperty economicActivityInTheProperty;
    private BasicNeeds basicNeeds;
    private SheddingCharacteristics sheddingCharacteristics;
    private EnvironmentalDamage environmentalDamage;
    private boolean isNew;
    private int predioPotencialId = -999;
    private boolean hasBeenSent;
    private String receiverEmail;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public AppDate getPropertyVisitDate() {
        return propertyVisitDate;
    }

    public void setPropertyVisitDate(AppDate propertyVisitDate) {
        this.propertyVisitDate = propertyVisitDate;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyCorrelation() {
        return propertyCorrelation;
    }

    public void setPropertyCorrelation(String propertyCorrelation) {
        this.propertyCorrelation = propertyCorrelation;
        switch (propertyCorrelation) {
            case "Propietario":
                this.propertyCorrelationId = 1;
                break;
            case "Poseedor":
                this.propertyCorrelationId = 2;
                break;
            case "Representante legal":
                this.propertyCorrelationId = 3;
                break;
            case "Arrendatario":
                this.propertyCorrelationId = 4;
                break;
            case "Tenedor":
                this.propertyCorrelationId = 5;
                break;
            case "Otro":
                this.propertyCorrelationId = 6;
                break;
        }
    }

    public boolean isPropertyColantaPartner() {
        return propertyColantaPartner;
    }

    public void setPropertyColantaPartner(boolean propertyColantaPartner) {
        this.propertyColantaPartner = propertyColantaPartner;
    }

    public boolean isPropertyMilkMerchant() {
        return propertyMilkMerchant;
    }

    public void setPropertyMilkMerchant(boolean propertyMilkMerchant) {
        this.propertyMilkMerchant = propertyMilkMerchant;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyRetailName() {
        return propertyRetailName;
    }

    public void setPropertyRetailName(String propertyRetailName) {
        this.propertyRetailName = propertyRetailName;
    }

    public String getPropertySector() {
        return propertySector;
    }

    public void setPropertySector(String propertySector) {
        this.propertySector = propertySector;
    }

    public String getPropertyReservoir() {
        return propertyReservoir;
    }

    public void setPropertyReservoir(String propertyReservoir) {
        this.propertyReservoir = propertyReservoir;
    }

    public boolean isPropertyReservoirRioGrande() {
        return propertyReservoirRioGrande;
    }

    public void setPropertyReservoirRioGrande(boolean propertyReservoirRioGrande) {
        this.propertyReservoirRioGrande = propertyReservoirRioGrande;
    }

    public boolean isPropertyReservoirLaFe() {
        return propertyReservoirLaFe;
    }

    public void setPropertyReservoirLaFe(boolean propertyReservoirLaFe) {
        this.propertyReservoirLaFe = propertyReservoirLaFe;
    }

    public PropertyLegalStatus getPropertyLegalStatus() {
        return propertyLegalStatus;
    }

    public void setPropertyLegalStatus(PropertyLegalStatus propertyLegalStatus) {
        this.propertyLegalStatus = propertyLegalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public boolean isRuralZone() {
        return ruralZone;
    }

    public void setRuralZone(boolean ruralZone) {
        this.ruralZone = ruralZone;
    }

    public boolean isUrbanZone() {
        return urbanZone;
    }

    public void setUrbanZone(boolean urbanZone) {
        this.urbanZone = urbanZone;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getMicroBasin() {
        return microBasin;
    }

    public void setMicroBasin(String microBasin) {
        this.microBasin = microBasin;
    }

    public String getHydrologicalSource() {
        return hydrologicalSource;
    }

    public void setHydrologicalSource(String hydrologicalSource) {
        this.hydrologicalSource = hydrologicalSource;
    }

    public NaturalEcosystemsInTheProperty getNaturalEcosystemsInTheProperty() {
        return naturalEcosystemsInTheProperty;
    }

    public void setNaturalEcosystemsInTheProperty(NaturalEcosystemsInTheProperty naturalEcosystemsInTheProperty) {
        this.naturalEcosystemsInTheProperty = naturalEcosystemsInTheProperty;
    }

    public StrategicImportanceOfTheProperty getStrategicImportanceOfTheProperty() {
        return strategicImportanceOfTheProperty;
    }

    public void setStrategicImportanceOfTheProperty(StrategicImportanceOfTheProperty strategicImportanceOfTheProperty) {
        this.strategicImportanceOfTheProperty = strategicImportanceOfTheProperty;
    }

    public SocioEconomicInformation getSocioEconomicInformation() {
        return socioEconomicInformation;
    }

    public void setSocioEconomicInformation(SocioEconomicInformation socioEconomicInformation) {
        this.socioEconomicInformation = socioEconomicInformation;
    }

    public FamilyInformation getFamilyInformation() {
        return familyInformation;
    }

    public void setFamilyInformation(FamilyInformation familyInformation) {
        this.familyInformation = familyInformation;
    }

    public WaysOfAccess getWaysOfAccess() {
        return waysOfAccess;
    }

    public void setWaysOfAccess(WaysOfAccess waysOfAccess) {
        this.waysOfAccess = waysOfAccess;
    }

    public EconomicActivityInTheProperty getEconomicActivityInTheProperty() {
        return economicActivityInTheProperty;
    }

    public void setEconomicActivityInTheProperty(EconomicActivityInTheProperty economicActivityInTheProperty) {
        this.economicActivityInTheProperty = economicActivityInTheProperty;
    }

    public BasicNeeds getBasicNeeds() {
        return basicNeeds;
    }

    public void setBasicNeeds(BasicNeeds basicNeeds) {
        this.basicNeeds = basicNeeds;
    }

    public SheddingCharacteristics getSheddingCharacteristics() {
        return sheddingCharacteristics;
    }

    public void setSheddingCharacteristics(SheddingCharacteristics sheddingCharacteristics) {
        this.sheddingCharacteristics = sheddingCharacteristics;
    }

    public EnvironmentalDamage getEnvironmentalDamage() {
        return environmentalDamage;
    }

    public void setEnvironmentalDamage(EnvironmentalDamage environmentalDamage) {
        this.environmentalDamage = environmentalDamage;
    }

    public int getPropertyCorrelationId() {
        return propertyCorrelationId;
    }

    public void setPropertyCorrelationId(int propertyCorrelationId) {
        this.propertyCorrelationId = propertyCorrelationId;
    }

    public boolean hasOtherPropertyCorrelation() {
        return propertyCorrelationId == 6;
    }

    public String getPropertyCorrelationOther() {
        return propertyCorrelationOther;
    }

    public void setPropertyCorrelationOther(String propertyCorrelationOther) {
        this.propertyCorrelationOther = propertyCorrelationOther;
    }

    public void setLocation(Location location) {
        this.economicActivityInTheProperty.setLatitude(String.valueOf(location.getLatitude()));
        this.economicActivityInTheProperty.setLongitude(String.valueOf(location.getLongitude()));
    }

    public boolean hasValidCoordinates() {
        return !TextUtils.isEmpty(economicActivityInTheProperty.getLatitude()) && !TextUtils.isEmpty(economicActivityInTheProperty.getLongitude()) && Double.valueOf(economicActivityInTheProperty.getLatitude()) != 0 && Double.valueOf(economicActivityInTheProperty.getLongitude()) != 0;
    }

    public boolean hasValidName() {
        return !TextUtils.isEmpty(getPropertyName());
    }

    public boolean isNew() {
        return this.isNew;
    }

    public void setAsNew() {
        this.isNew = true;
    }

    public void setReceiverEmail(String email) {
        this.receiverEmail = email;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setForPredioPotencial(PredioPotencial predioPotencial) {
        this.propertyName = predioPotencial.getName();
        setLocation(predioPotencial.getLocation());
    }

    public int getPredioPotencialId() {
        return predioPotencialId;
    }

    public void setPredioPotencialId(int predioPotencialId) {
        this.predioPotencialId = predioPotencialId;
    }

    public boolean isHasBeenSent() {
        return hasBeenSent;
    }

    public void setHasBeenSent() {
        this.hasBeenSent = true;
    }

    public boolean isPart1Valid() {
        return contact.isValid();
    }

    public boolean isPart2Valid() {
        return true;
    }

    public boolean isValid() {
        return isPart1Valid() && isPart2Valid();
    }

    public Survey() {
        setContact(new Contact());
        setPropertyType(new PropertyType());
        setPropertyLegalStatus(new PropertyLegalStatus());
        setNaturalEcosystemsInTheProperty(new NaturalEcosystemsInTheProperty());
        setStrategicImportanceOfTheProperty(new StrategicImportanceOfTheProperty());
        setSocioEconomicInformation(new SocioEconomicInformation());
        setFamilyInformation(new FamilyInformation());
        setWaysOfAccess(new WaysOfAccess());
        setEconomicActivityInTheProperty(new EconomicActivityInTheProperty());
        setBasicNeeds(new BasicNeeds());
        setSheddingCharacteristics(new SheddingCharacteristics());
        setEnvironmentalDamage(new EnvironmentalDamage());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.contact, flags);
        dest.writeParcelable(this.propertyVisitDate, flags);
        dest.writeString(this.propertyName);
        dest.writeString(this.propertyCorrelation);
        dest.writeInt(this.propertyCorrelationId);
        dest.writeString(this.propertyCorrelationOther);
        dest.writeByte(this.propertyColantaPartner ? (byte) 1 : (byte) 0);
        dest.writeByte(this.propertyMilkMerchant ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.propertyType, flags);
        dest.writeString(this.propertyRetailName);
        dest.writeString(this.propertySector);
        dest.writeString(this.propertyReservoir);
        dest.writeByte(this.propertyReservoirRioGrande ? (byte) 1 : (byte) 0);
        dest.writeByte(this.propertyReservoirLaFe ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.propertyLegalStatus, flags);
        dest.writeString(this.address);
        dest.writeString(this.nit);
        dest.writeString(this.municipality);
        dest.writeString(this.zone);
        dest.writeByte(this.ruralZone ? (byte) 1 : (byte) 0);
        dest.writeByte(this.urbanZone ? (byte) 1 : (byte) 0);
        dest.writeString(this.township);
        dest.writeString(this.lane);
        dest.writeString(this.microBasin);
        dest.writeString(this.hydrologicalSource);
        dest.writeParcelable(this.naturalEcosystemsInTheProperty, flags);
        dest.writeParcelable(this.strategicImportanceOfTheProperty, flags);
        dest.writeParcelable(this.socioEconomicInformation, flags);
        dest.writeParcelable(this.familyInformation, flags);
        dest.writeParcelable(this.waysOfAccess, flags);
        dest.writeParcelable(this.economicActivityInTheProperty, flags);
        dest.writeParcelable(this.basicNeeds, flags);
        dest.writeParcelable(this.sheddingCharacteristics, flags);
        dest.writeParcelable(this.environmentalDamage, flags);
        dest.writeByte(this.isNew ? (byte) 1 : (byte) 0);
        dest.writeInt(this.predioPotencialId);
        dest.writeByte(this.hasBeenSent ? (byte) 1 : (byte) 0);
    }

    protected Survey(Parcel in) {
        this.id = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
        this.propertyVisitDate = in.readParcelable(AppDate.class.getClassLoader());
        this.propertyName = in.readString();
        this.propertyCorrelation = in.readString();
        this.propertyCorrelationId = in.readInt();
        this.propertyCorrelationOther = in.readString();
        this.propertyColantaPartner = in.readByte() != 0;
        this.propertyMilkMerchant = in.readByte() != 0;
        this.propertyType = in.readParcelable(PropertyType.class.getClassLoader());
        this.propertyRetailName = in.readString();
        this.propertySector = in.readString();
        this.propertyReservoir = in.readString();
        this.propertyReservoirRioGrande = in.readByte() != 0;
        this.propertyReservoirLaFe = in.readByte() != 0;
        this.propertyLegalStatus = in.readParcelable(PropertyLegalStatus.class.getClassLoader());
        this.address = in.readString();
        this.nit = in.readString();
        this.municipality = in.readString();
        this.zone = in.readString();
        this.ruralZone = in.readByte() != 0;
        this.urbanZone = in.readByte() != 0;
        this.township = in.readString();
        this.lane = in.readString();
        this.microBasin = in.readString();
        this.hydrologicalSource = in.readString();
        this.naturalEcosystemsInTheProperty = in.readParcelable(NaturalEcosystemsInTheProperty.class.getClassLoader());
        this.strategicImportanceOfTheProperty = in.readParcelable(StrategicImportanceOfTheProperty.class.getClassLoader());
        this.socioEconomicInformation = in.readParcelable(SocioEconomicInformation.class.getClassLoader());
        this.familyInformation = in.readParcelable(FamilyInformation.class.getClassLoader());
        this.waysOfAccess = in.readParcelable(WaysOfAccess.class.getClassLoader());
        this.economicActivityInTheProperty = in.readParcelable(EconomicActivityInTheProperty.class.getClassLoader());
        this.basicNeeds = in.readParcelable(BasicNeeds.class.getClassLoader());
        this.sheddingCharacteristics = in.readParcelable(SheddingCharacteristics.class.getClassLoader());
        this.environmentalDamage = in.readParcelable(EnvironmentalDamage.class.getClassLoader());
        this.isNew = in.readByte() != 0;
        this.predioPotencialId = in.readInt();
        this.hasBeenSent = in.readByte() != 0;
    }

    public static final Creator<Survey> CREATOR = new Creator<Survey>() {
        @Override
        public Survey createFromParcel(Parcel source) {
            return new Survey(source);
        }

        @Override
        public Survey[] newArray(int size) {
            return new Survey[size];
        }
    };
}
