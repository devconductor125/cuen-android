
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SheddingCharacteristics {

    @SerializedName("ar_treatment_system")
    @Expose
    private boolean arTreatmentSystem;
    @SerializedName("are_there_agricultural_activities_in_the_property")
    @Expose
    private AreThereAgriculturalActivitiesInTheProperty areThereAgriculturalActivitiesInTheProperty;
    @SerializedName("chemical_and_or_cattle_raising_handling")
    @Expose
    private ChemicalAndOrCattleRaisingHandling chemicalAndOrCattleRaisingHandling;
    @SerializedName("domestic_solid_waste_harvesting_handling")
    @Expose
    private DomesticSolidWasteHarvestingHandling domesticSolidWasteHarvestingHandling;
    @SerializedName("sewerage_system_last_maintenance_date")
    @Expose
    private SewerageSystemLastMaintenanceDate sewerageSystemLastMaintenanceDate;
    @SerializedName("shedding_licence")
    @Expose
    private SheddingLicence sheddingLicence;
    @SerializedName("shedding_location")
    @Expose
    private SheddingLocation sheddingLocation;
    @SerializedName("shedding_periodicity")
    @Expose
    private SheddingPeriodicity sheddingPeriodicity;
    @SerializedName("shedding_schedule")
    @Expose
    private SheddingSchedule sheddingSchedule;
    @SerializedName("shedding_type")
    @Expose
    private SheddingType sheddingType;
    @SerializedName("treatment_system_status")
    @Expose
    private TreatmentSystemStatus treatmentSystemStatus;
    @SerializedName("water_concession")
    @Expose
    private WaterConcession waterConcession;
    @SerializedName("who_does_the_sewerage_system_maintenance")
    @Expose
    private WhoDoesTheSewerageSystemMaintenance whoDoesTheSewerageSystemMaintenance;

    public boolean isArTreatmentSystem() {
        return arTreatmentSystem;
    }

    public void setArTreatmentSystem(boolean arTreatmentSystem) {
        this.arTreatmentSystem = arTreatmentSystem;
    }

    public AreThereAgriculturalActivitiesInTheProperty getAreThereAgriculturalActivitiesInTheProperty() {
        return areThereAgriculturalActivitiesInTheProperty;
    }

    public void setAreThereAgriculturalActivitiesInTheProperty(AreThereAgriculturalActivitiesInTheProperty areThereAgriculturalActivitiesInTheProperty) {
        this.areThereAgriculturalActivitiesInTheProperty = areThereAgriculturalActivitiesInTheProperty;
    }

    public ChemicalAndOrCattleRaisingHandling getChemicalAndOrCattleRaisingHandling() {
        return chemicalAndOrCattleRaisingHandling;
    }

    public void setChemicalAndOrCattleRaisingHandling(ChemicalAndOrCattleRaisingHandling chemicalAndOrCattleRaisingHandling) {
        this.chemicalAndOrCattleRaisingHandling = chemicalAndOrCattleRaisingHandling;
    }

    public DomesticSolidWasteHarvestingHandling getDomesticSolidWasteHarvestingHandling() {
        return domesticSolidWasteHarvestingHandling;
    }

    public void setDomesticSolidWasteHarvestingHandling(DomesticSolidWasteHarvestingHandling domesticSolidWasteHarvestingHandling) {
        this.domesticSolidWasteHarvestingHandling = domesticSolidWasteHarvestingHandling;
    }

    public SewerageSystemLastMaintenanceDate getSewerageSystemLastMaintenanceDate() {
        return sewerageSystemLastMaintenanceDate;
    }

    public void setSewerageSystemLastMaintenanceDate(SewerageSystemLastMaintenanceDate sewerageSystemLastMaintenanceDate) {
        this.sewerageSystemLastMaintenanceDate = sewerageSystemLastMaintenanceDate;
    }

    public SheddingLicence getSheddingLicence() {
        return sheddingLicence;
    }

    public void setSheddingLicence(SheddingLicence sheddingLicence) {
        this.sheddingLicence = sheddingLicence;
    }

    public SheddingLocation getSheddingLocation() {
        return sheddingLocation;
    }

    public void setSheddingLocation(SheddingLocation sheddingLocation) {
        this.sheddingLocation = sheddingLocation;
    }

    public SheddingPeriodicity getSheddingPeriodicity() {
        return sheddingPeriodicity;
    }

    public void setSheddingPeriodicity(SheddingPeriodicity sheddingPeriodicity) {
        this.sheddingPeriodicity = sheddingPeriodicity;
    }

    public SheddingSchedule getSheddingSchedule() {
        return sheddingSchedule;
    }

    public void setSheddingSchedule(SheddingSchedule sheddingSchedule) {
        this.sheddingSchedule = sheddingSchedule;
    }

    public SheddingType getSheddingType() {
        return sheddingType;
    }

    public void setSheddingType(SheddingType sheddingType) {
        this.sheddingType = sheddingType;
    }

    public TreatmentSystemStatus getTreatmentSystemStatus() {
        return treatmentSystemStatus;
    }

    public void setTreatmentSystemStatus(TreatmentSystemStatus treatmentSystemStatus) {
        this.treatmentSystemStatus = treatmentSystemStatus;
    }

    public WaterConcession getWaterConcession() {
        return waterConcession;
    }

    public void setWaterConcession(WaterConcession waterConcession) {
        this.waterConcession = waterConcession;
    }

    public WhoDoesTheSewerageSystemMaintenance getWhoDoesTheSewerageSystemMaintenance() {
        return whoDoesTheSewerageSystemMaintenance;
    }

    public void setWhoDoesTheSewerageSystemMaintenance(WhoDoesTheSewerageSystemMaintenance whoDoesTheSewerageSystemMaintenance) {
        this.whoDoesTheSewerageSystemMaintenance = whoDoesTheSewerageSystemMaintenance;
    }

}
