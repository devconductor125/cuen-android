
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurveyRequest {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("basic_needs")
    @Expose
    private BasicNeeds basicNeeds;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("economic_activity_in_the_property")
    @Expose
    private EconomicActivityInTheProperty economicActivityInTheProperty;
    @SerializedName("environmental_damage")
    @Expose
    private EnvironmentalDamage environmentalDamage;
    @SerializedName("hydrological_source")
    @Expose
    private String hydrologicalSource;
    @SerializedName("lane")
    @Expose
    private String lane;
    @SerializedName("micro_basin")
    @Expose
    private String microBasin;
    @SerializedName("municipality")
    @Expose
    private String municipality;
    @SerializedName("natural_ecosystems_in_the_property")
    @Expose
    private NaturalEcosystemsInTheProperty naturalEcosystemsInTheProperty;
    @SerializedName("nit")
    @Expose
    private String nit;
    @SerializedName("property_colanta_partner")
    @Expose
    private boolean propertyColantaPartner;
    @SerializedName("property_correlation")
    @Expose
    private String propertyCorrelation;
    @SerializedName("property_correlation_id")
    @Expose
    private int propertyCorrelationId;
    @SerializedName("property_legal_status")
    @Expose
    private PropertyLegalStatus propertyLegalStatus;
    @SerializedName("property_milk_merchant")
    @Expose
    private boolean propertyMilkMerchant;
    @SerializedName("property_name")
    @Expose
    private String propertyName;
    @SerializedName("property_reservoir")
    @Expose
    private String propertyReservoir;
    @SerializedName("property_retail_name")
    @Expose
    private String propertyRetailName;
    @SerializedName("property_sector")
    @Expose
    private String propertySector;
    @SerializedName("property_type")
    @Expose
    private PropertyType propertyType;
    @SerializedName("property_visit_date")
    @Expose
    private PropertyVisitDate propertyVisitDate;
    @SerializedName("shedding_characteristics")
    @Expose
    private SheddingCharacteristics sheddingCharacteristics;
    @SerializedName("socio_economic_information")
    @Expose
    private SocioEconomicInformation socioEconomicInformation;
    @SerializedName("strategic_importance_of_the_property")
    @Expose
    private StrategicImportanceOfTheProperty strategicImportanceOfTheProperty;
    @SerializedName("task_id")
    @Expose
    private String taskId;
    @SerializedName("township")
    @Expose
    private String township;
    @SerializedName("ways_of_access")
    @Expose
    private WaysOfAccess waysOfAccess;
    @SerializedName("zone")
    @Expose
    private String zone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BasicNeeds getBasicNeeds() {
        return basicNeeds;
    }

    public void setBasicNeeds(BasicNeeds basicNeeds) {
        this.basicNeeds = basicNeeds;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public EconomicActivityInTheProperty getEconomicActivityInTheProperty() {
        return economicActivityInTheProperty;
    }

    public void setEconomicActivityInTheProperty(EconomicActivityInTheProperty economicActivityInTheProperty) {
        this.economicActivityInTheProperty = economicActivityInTheProperty;
    }

    public EnvironmentalDamage getEnvironmentalDamage() {
        return environmentalDamage;
    }

    public void setEnvironmentalDamage(EnvironmentalDamage environmentalDamage) {
        this.environmentalDamage = environmentalDamage;
    }

    public String getHydrologicalSource() {
        return hydrologicalSource;
    }

    public void setHydrologicalSource(String hydrologicalSource) {
        this.hydrologicalSource = hydrologicalSource;
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

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public NaturalEcosystemsInTheProperty getNaturalEcosystemsInTheProperty() {
        return naturalEcosystemsInTheProperty;
    }

    public void setNaturalEcosystemsInTheProperty(NaturalEcosystemsInTheProperty naturalEcosystemsInTheProperty) {
        this.naturalEcosystemsInTheProperty = naturalEcosystemsInTheProperty;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public boolean isPropertyColantaPartner() {
        return propertyColantaPartner;
    }

    public void setPropertyColantaPartner(boolean propertyColantaPartner) {
        this.propertyColantaPartner = propertyColantaPartner;
    }

    public String getPropertyCorrelation() {
        return propertyCorrelation;
    }

    public void setPropertyCorrelation(String propertyCorrelation) {
        this.propertyCorrelation = propertyCorrelation;
    }

    public int getPropertyCorrelationId() {
        return propertyCorrelationId;
    }

    public void setPropertyCorrelationId(int propertyCorrelationId) {
        this.propertyCorrelationId = propertyCorrelationId;
    }

    public PropertyLegalStatus getPropertyLegalStatus() {
        return propertyLegalStatus;
    }

    public void setPropertyLegalStatus(PropertyLegalStatus propertyLegalStatus) {
        this.propertyLegalStatus = propertyLegalStatus;
    }

    public boolean isPropertyMilkMerchant() {
        return propertyMilkMerchant;
    }

    public void setPropertyMilkMerchant(boolean propertyMilkMerchant) {
        this.propertyMilkMerchant = propertyMilkMerchant;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyReservoir() {
        return propertyReservoir;
    }

    public void setPropertyReservoir(String propertyReservoir) {
        this.propertyReservoir = propertyReservoir;
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

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public PropertyVisitDate getPropertyVisitDate() {
        return propertyVisitDate;
    }

    public void setPropertyVisitDate(PropertyVisitDate propertyVisitDate) {
        this.propertyVisitDate = propertyVisitDate;
    }

    public SheddingCharacteristics getSheddingCharacteristics() {
        return sheddingCharacteristics;
    }

    public void setSheddingCharacteristics(SheddingCharacteristics sheddingCharacteristics) {
        this.sheddingCharacteristics = sheddingCharacteristics;
    }

    public SocioEconomicInformation getSocioEconomicInformation() {
        return socioEconomicInformation;
    }

    public void setSocioEconomicInformation(SocioEconomicInformation socioEconomicInformation) {
        this.socioEconomicInformation = socioEconomicInformation;
    }

    public StrategicImportanceOfTheProperty getStrategicImportanceOfTheProperty() {
        return strategicImportanceOfTheProperty;
    }

    public void setStrategicImportanceOfTheProperty(StrategicImportanceOfTheProperty strategicImportanceOfTheProperty) {
        this.strategicImportanceOfTheProperty = strategicImportanceOfTheProperty;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public WaysOfAccess getWaysOfAccess() {
        return waysOfAccess;
    }

    public void setWaysOfAccess(WaysOfAccess waysOfAccess) {
        this.waysOfAccess = waysOfAccess;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
