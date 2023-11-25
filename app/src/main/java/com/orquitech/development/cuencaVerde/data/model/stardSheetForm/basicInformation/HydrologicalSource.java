package com.orquitech.development.cuencaVerde.data.model.stardSheetForm.basicInformation;

import android.os.Parcel;
import android.os.Parcelable;

public class HydrologicalSource implements Parcelable {

    private String hydrologicalSourceName;
    private String hydrologicalSourceDistance;
    private boolean isThereAHydrologicalSourceCloseToTheHousehold;
    private boolean soil;
    private boolean other;
    private boolean isThereRoomToBuildAndAbsorptionWell;

    public String getHydrologicalSourceName() {
        return hydrologicalSourceName;
    }

    public void setHydrologicalSourceName(String hydrologicalSourceName) {
        this.hydrologicalSourceName = hydrologicalSourceName;
    }

    public String getHydrologicalSourceDistance() {
        return hydrologicalSourceDistance;
    }

    public void setHydrologicalSourceDistance(String hydrologicalSourceDistance) {
        this.hydrologicalSourceDistance = hydrologicalSourceDistance;
    }

    public boolean isThereAHydrologicalSourceCloseToTheHousehold() {
        return isThereAHydrologicalSourceCloseToTheHousehold;
    }

    public void setThereAHydrologicalSourceCloseToTheHousehold(boolean thereAHydrologicalSourceCloseToTheHousehold) {
        isThereAHydrologicalSourceCloseToTheHousehold = thereAHydrologicalSourceCloseToTheHousehold;
    }

    public boolean isSoil() {
        return soil;
    }

    public void setSoil(boolean soil) {
        this.soil = soil;
    }

    public boolean isOther() {
        return other;
    }

    public void setOther(boolean other) {
        this.other = other;
    }

    public boolean isThereRoomToBuildAndAbsorptionWell() {
        return isThereRoomToBuildAndAbsorptionWell;
    }

    public void setThereRoomToBuildAndAbsorptionWell(boolean thereRoomToBuildAndAbsorptionWell) {
        isThereRoomToBuildAndAbsorptionWell = thereRoomToBuildAndAbsorptionWell;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.hydrologicalSourceName);
        dest.writeString(this.hydrologicalSourceDistance);
        dest.writeByte(this.isThereAHydrologicalSourceCloseToTheHousehold ? (byte) 1 : (byte) 0);
        dest.writeByte(this.soil ? (byte) 1 : (byte) 0);
        dest.writeByte(this.other ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isThereRoomToBuildAndAbsorptionWell ? (byte) 1 : (byte) 0);
    }

    public HydrologicalSource() {
    }

    protected HydrologicalSource(Parcel in) {
        this.hydrologicalSourceName = in.readString();
        this.hydrologicalSourceDistance = in.readString();
        this.isThereAHydrologicalSourceCloseToTheHousehold = in.readByte() != 0;
        this.soil = in.readByte() != 0;
        this.other = in.readByte() != 0;
        this.isThereRoomToBuildAndAbsorptionWell = in.readByte() != 0;
    }

    public static final Creator<HydrologicalSource> CREATOR = new Creator<HydrologicalSource>() {
        @Override
        public HydrologicalSource createFromParcel(Parcel source) {
            return new HydrologicalSource(source);
        }

        @Override
        public HydrologicalSource[] newArray(int size) {
            return new HydrologicalSource[size];
        }
    };
}
