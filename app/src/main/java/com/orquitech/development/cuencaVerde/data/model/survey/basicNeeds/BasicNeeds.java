package com.orquitech.development.cuencaVerde.data.model.survey.basicNeeds;

import android.os.Parcel;
import android.os.Parcelable;

public class BasicNeeds implements Parcelable {

    private boolean sewerageSystem;
    private boolean aqueductNetwork;
    private boolean electricalNetwork;
    private HydrologicalSource hydrologicalSource;
    private ElectricitySource electricitySource;
    private CookingMethods cookingMethods;
    private BasicSanitationMethods basicSanitationMethods;

    public boolean isSewerageSystem() {
        return sewerageSystem;
    }

    public void setSewerageSystem(boolean sewerageSystem) {
        this.sewerageSystem = sewerageSystem;
    }

    public boolean isAqueductNetwork() {
        return aqueductNetwork;
    }

    public void setAqueductNetwork(boolean aqueductNetwork) {
        this.aqueductNetwork = aqueductNetwork;
    }

    public boolean isElectricalNetwork() {
        return electricalNetwork;
    }

    public void setElectricalNetwork(boolean electricalNetwork) {
        this.electricalNetwork = electricalNetwork;
    }

    public HydrologicalSource getHydrologicalSource() {
        return hydrologicalSource;
    }

    public void setHydrologicalSource(HydrologicalSource hydrologicalSource) {
        this.hydrologicalSource = hydrologicalSource;
    }

    public ElectricitySource getElectricitySource() {
        return electricitySource;
    }

    public void setElectricitySource(ElectricitySource electricitySource) {
        this.electricitySource = electricitySource;
    }

    public CookingMethods getCookingMethods() {
        return cookingMethods;
    }

    public void setCookingMethods(CookingMethods cookingMethods) {
        this.cookingMethods = cookingMethods;
    }

    public BasicSanitationMethods getBasicSanitationMethods() {
        return basicSanitationMethods;
    }

    public void setBasicSanitationMethods(BasicSanitationMethods basicSanitationMethods) {
        this.basicSanitationMethods = basicSanitationMethods;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.sewerageSystem ? (byte) 1 : (byte) 0);
        dest.writeByte(this.aqueductNetwork ? (byte) 1 : (byte) 0);
        dest.writeByte(this.electricalNetwork ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.hydrologicalSource, flags);
        dest.writeParcelable(this.electricitySource, flags);
        dest.writeParcelable(this.cookingMethods, flags);
        dest.writeParcelable(this.basicSanitationMethods, flags);
    }

    public BasicNeeds() {
        setHydrologicalSource(new HydrologicalSource());
        setElectricitySource(new ElectricitySource());
        setCookingMethods(new CookingMethods());
        setBasicSanitationMethods(new BasicSanitationMethods());
    }

    protected BasicNeeds(Parcel in) {
        this.sewerageSystem = in.readByte() != 0;
        this.aqueductNetwork = in.readByte() != 0;
        this.electricalNetwork = in.readByte() != 0;
        this.hydrologicalSource = in.readParcelable(HydrologicalSource.class.getClassLoader());
        this.electricitySource = in.readParcelable(ElectricitySource.class.getClassLoader());
        this.cookingMethods = in.readParcelable(CookingMethods.class.getClassLoader());
        this.basicSanitationMethods = in.readParcelable(BasicSanitationMethods.class.getClassLoader());
    }

    public static final Creator<BasicNeeds> CREATOR = new Creator<BasicNeeds>() {
        @Override
        public BasicNeeds createFromParcel(Parcel source) {
            return new BasicNeeds(source);
        }

        @Override
        public BasicNeeds[] newArray(int size) {
            return new BasicNeeds[size];
        }
    };
}
