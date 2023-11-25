package com.orquitech.development.cuencaVerde.data.model.stardSheetForm.basicInformation;

import android.os.Parcel;
import android.os.Parcelable;

public class BasicInformation implements Parcelable {

    private String distanceBetweenStardAndHousehold;
    private SystemType systemType;
    private HydrologicalSource hydrologicalSource;
    private DischargeLocation dischargeLocation;
    private BathRoomDischarge bathRoomDischarge;
    private DemolitionRequirements demolitionRequirements;
    private KitchenWaters kitchenWaters;
    private TerrainCondition terrainCondition;
    private StardEnclosurePreference stardEnclosurePreference;

    public String getDistanceBetweenStardAndHousehold() {
        return distanceBetweenStardAndHousehold;
    }

    public void setDistanceBetweenStardAndHousehold(String distanceBetweenStardAndHousehold) {
        this.distanceBetweenStardAndHousehold = distanceBetweenStardAndHousehold;
    }

    public SystemType getSystemType() {
        return systemType;
    }

    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
    }

    public HydrologicalSource getHydrologicalSource() {
        return hydrologicalSource;
    }

    public void setHydrologicalSource(HydrologicalSource hydrologicalSource) {
        this.hydrologicalSource = hydrologicalSource;
    }

    public DischargeLocation getDischargeLocation() {
        return dischargeLocation;
    }

    public void setDischargeLocation(DischargeLocation dischargeLocation) {
        this.dischargeLocation = dischargeLocation;
    }

    public BathRoomDischarge getBathRoomDischarge() {
        return bathRoomDischarge;
    }

    public void setBathRoomDischarge(BathRoomDischarge bathRoomDischarge) {
        this.bathRoomDischarge = bathRoomDischarge;
    }

    public DemolitionRequirements getDemolitionRequirements() {
        return demolitionRequirements;
    }

    public void setDemolitionRequirements(DemolitionRequirements demolitionRequirements) {
        this.demolitionRequirements = demolitionRequirements;
    }

    public KitchenWaters getKitchenWaters() {
        return kitchenWaters;
    }

    public void setKitchenWaters(KitchenWaters kitchenWaters) {
        this.kitchenWaters = kitchenWaters;
    }

    public TerrainCondition getTerrainCondition() {
        return terrainCondition;
    }

    public void setTerrainCondition(TerrainCondition terrainCondition) {
        this.terrainCondition = terrainCondition;
    }

    public StardEnclosurePreference getStardEnclosurePreference() {
        return stardEnclosurePreference;
    }

    public void setStardEnclosurePreference(StardEnclosurePreference stardEnclosurePreference) {
        this.stardEnclosurePreference = stardEnclosurePreference;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.distanceBetweenStardAndHousehold);
        dest.writeParcelable(this.systemType, flags);
        dest.writeParcelable(this.hydrologicalSource, flags);
        dest.writeParcelable(this.dischargeLocation, flags);
        dest.writeParcelable(this.bathRoomDischarge, flags);
        dest.writeParcelable(this.demolitionRequirements, flags);
        dest.writeParcelable(this.kitchenWaters, flags);
        dest.writeParcelable(this.terrainCondition, flags);
        dest.writeParcelable(this.stardEnclosurePreference, flags);
    }

    public BasicInformation() {
        if (this.systemType == null) {
            this.systemType = new SystemType();
        }
        if (this.hydrologicalSource == null) {
            this.hydrologicalSource = new HydrologicalSource();
        }
        if (this.dischargeLocation == null) {
            this.dischargeLocation = new DischargeLocation();
        }
        if (this.bathRoomDischarge == null) {
            this.bathRoomDischarge = new BathRoomDischarge();
        }
        if (this.demolitionRequirements == null) {
            this.demolitionRequirements = new DemolitionRequirements();
        }
        if (this.kitchenWaters == null) {
            this.kitchenWaters = new KitchenWaters();
        }
        if (this.terrainCondition == null) {
            this.terrainCondition = new TerrainCondition();
        }
        if (this.stardEnclosurePreference == null) {
            this.stardEnclosurePreference = new StardEnclosurePreference();
        }
    }

    protected BasicInformation(Parcel in) {
        this.distanceBetweenStardAndHousehold = in.readString();
        this.systemType = in.readParcelable(SystemType.class.getClassLoader());
        this.hydrologicalSource = in.readParcelable(HydrologicalSource.class.getClassLoader());
        this.dischargeLocation = in.readParcelable(DischargeLocation.class.getClassLoader());
        this.bathRoomDischarge = in.readParcelable(BathRoomDischarge.class.getClassLoader());
        this.demolitionRequirements = in.readParcelable(DemolitionRequirements.class.getClassLoader());
        this.kitchenWaters = in.readParcelable(KitchenWaters.class.getClassLoader());
        this.terrainCondition = in.readParcelable(TerrainCondition.class.getClassLoader());
        this.stardEnclosurePreference = in.readParcelable(StardEnclosurePreference.class.getClassLoader());
    }

    public static final Creator<BasicInformation> CREATOR = new Creator<BasicInformation>() {
        @Override
        public BasicInformation createFromParcel(Parcel source) {
            return new BasicInformation(source);
        }

        @Override
        public BasicInformation[] newArray(int size) {
            return new BasicInformation[size];
        }
    };
}
