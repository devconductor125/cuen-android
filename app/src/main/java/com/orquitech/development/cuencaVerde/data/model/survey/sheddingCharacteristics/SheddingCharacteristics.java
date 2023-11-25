package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class SheddingCharacteristics implements Parcelable {

    private SheddingType sheddingType;
    private SheddingPeriodicity sheddingPeriodicity;
    private SheddingSchedule sheddingSchedule;
    private boolean arTreatmentSystem;
    private SheddingLocation sheddingLocation;
    private SheddingLicence sheddingLicence;
    private WaterConcession waterConcession;
    private TreatmentSystemStatus treatmentSystemStatus;
    private SewerageSystemLastMaintenanceDate sewerageSystemLastMaintenanceDate;
    private WhoDoesTheSewerageSystemMaintenance whoDoesTheSewerageSystemMaintenance;
    private DomesticSolidWasteHarvestingHandling domesticSolidWasteHarvestingHandling;
    private AreThereAgriculturalActivitiesInTheProperty areThereAgriculturalActivitiesInTheProperty;
    private ChemicalAndOrCattleRaisingHandling chemicalAndOrCattleRaisingHandling;

    public SheddingType getSheddingType() {
        return sheddingType;
    }

    public void setSheddingType(SheddingType sheddingType) {
        this.sheddingType = sheddingType;
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

    public boolean isArTreatmentSystem() {
        return arTreatmentSystem;
    }

    public void setArTreatmentSystem(boolean arTreatmentSystem) {
        this.arTreatmentSystem = arTreatmentSystem;
    }

    public SheddingLocation getSheddingLocation() {
        return sheddingLocation;
    }

    public void setSheddingLocation(SheddingLocation sheddingLocation) {
        this.sheddingLocation = sheddingLocation;
    }

    public SheddingLicence getSheddingLicence() {
        return sheddingLicence;
    }

    public void setSheddingLicence(SheddingLicence sheddingLicence) {
        this.sheddingLicence = sheddingLicence;
    }

    public WaterConcession getWaterConcession() {
        return waterConcession;
    }

    public void setWaterConcession(WaterConcession waterConcession) {
        this.waterConcession = waterConcession;
    }

    public TreatmentSystemStatus getTreatmentSystemStatus() {
        return treatmentSystemStatus;
    }

    public void setTreatmentSystemStatus(TreatmentSystemStatus treatmentSystemStatus) {
        this.treatmentSystemStatus = treatmentSystemStatus;
    }

    public SewerageSystemLastMaintenanceDate getSewerageSystemLastMaintenanceDate() {
        return sewerageSystemLastMaintenanceDate;
    }

    public void setSewerageSystemLastMaintenanceDate(SewerageSystemLastMaintenanceDate sewerageSystemLastMaintenanceDate) {
        this.sewerageSystemLastMaintenanceDate = sewerageSystemLastMaintenanceDate;
    }

    public WhoDoesTheSewerageSystemMaintenance getWhoDoesTheSewerageSystemMaintenance() {
        return whoDoesTheSewerageSystemMaintenance;
    }

    public void setWhoDoesTheSewerageSystemMaintenance(WhoDoesTheSewerageSystemMaintenance whoDoesTheSewerageSystemMaintenance) {
        this.whoDoesTheSewerageSystemMaintenance = whoDoesTheSewerageSystemMaintenance;
    }

    public DomesticSolidWasteHarvestingHandling getDomesticSolidWasteHarvestingHandling() {
        return domesticSolidWasteHarvestingHandling;
    }

    public void setDomesticSolidWasteHarvestingHandling(DomesticSolidWasteHarvestingHandling domesticSolidWasteHarvestingHandling) {
        this.domesticSolidWasteHarvestingHandling = domesticSolidWasteHarvestingHandling;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.sheddingType, flags);
        dest.writeParcelable(this.sheddingPeriodicity, flags);
        dest.writeParcelable(this.sheddingSchedule, flags);
        dest.writeByte(this.arTreatmentSystem ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.sheddingLocation, flags);
        dest.writeParcelable(this.sheddingLicence, flags);
        dest.writeParcelable(this.waterConcession, flags);
        dest.writeParcelable(this.treatmentSystemStatus, flags);
        dest.writeParcelable(this.sewerageSystemLastMaintenanceDate, flags);
        dest.writeParcelable(this.whoDoesTheSewerageSystemMaintenance, flags);
        dest.writeParcelable(this.domesticSolidWasteHarvestingHandling, flags);
        dest.writeParcelable(this.areThereAgriculturalActivitiesInTheProperty, flags);
        dest.writeParcelable(this.chemicalAndOrCattleRaisingHandling, flags);
    }

    public SheddingCharacteristics() {
        setSheddingType(new SheddingType());
        setSheddingPeriodicity(new SheddingPeriodicity());
        setSheddingSchedule(new SheddingSchedule());
        setSheddingLocation(new SheddingLocation());
        setSheddingLicence(new SheddingLicence());
        setWaterConcession(new WaterConcession());
        setTreatmentSystemStatus(new TreatmentSystemStatus());
        setSewerageSystemLastMaintenanceDate(new SewerageSystemLastMaintenanceDate());
        setWhoDoesTheSewerageSystemMaintenance(new WhoDoesTheSewerageSystemMaintenance());
        setDomesticSolidWasteHarvestingHandling(new DomesticSolidWasteHarvestingHandling());
        setAreThereAgriculturalActivitiesInTheProperty(new AreThereAgriculturalActivitiesInTheProperty());
        setChemicalAndOrCattleRaisingHandling(new ChemicalAndOrCattleRaisingHandling());
    }

    protected SheddingCharacteristics(Parcel in) {
        this.sheddingType = in.readParcelable(SheddingType.class.getClassLoader());
        this.sheddingPeriodicity = in.readParcelable(SheddingPeriodicity.class.getClassLoader());
        this.sheddingSchedule = in.readParcelable(SheddingSchedule.class.getClassLoader());
        this.arTreatmentSystem = in.readByte() != 0;
        this.sheddingLocation = in.readParcelable(SheddingLocation.class.getClassLoader());
        this.sheddingLicence = in.readParcelable(SheddingLicence.class.getClassLoader());
        this.waterConcession = in.readParcelable(WaterConcession.class.getClassLoader());
        this.treatmentSystemStatus = in.readParcelable(TreatmentSystemStatus.class.getClassLoader());
        this.sewerageSystemLastMaintenanceDate = in.readParcelable(SewerageSystemLastMaintenanceDate.class.getClassLoader());
        this.whoDoesTheSewerageSystemMaintenance = in.readParcelable(WhoDoesTheSewerageSystemMaintenance.class.getClassLoader());
        this.domesticSolidWasteHarvestingHandling = in.readParcelable(DomesticSolidWasteHarvestingHandling.class.getClassLoader());
        this.areThereAgriculturalActivitiesInTheProperty = in.readParcelable(AreThereAgriculturalActivitiesInTheProperty.class.getClassLoader());
        this.chemicalAndOrCattleRaisingHandling = in.readParcelable(ChemicalAndOrCattleRaisingHandling.class.getClassLoader());
    }

    public static final Creator<SheddingCharacteristics> CREATOR = new Creator<SheddingCharacteristics>() {
        @Override
        public SheddingCharacteristics createFromParcel(Parcel source) {
            return new SheddingCharacteristics(source);
        }

        @Override
        public SheddingCharacteristics[] newArray(int size) {
            return new SheddingCharacteristics[size];
        }
    };
}
