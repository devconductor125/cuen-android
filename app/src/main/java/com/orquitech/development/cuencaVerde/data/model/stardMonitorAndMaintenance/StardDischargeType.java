package com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance;

import android.os.Parcel;
import android.os.Parcelable;

public class StardDischargeType implements Parcelable {

    private boolean absorptionWell;
    private boolean infiltrationField;
    private boolean hydrologicalSource;

    public boolean isAbsorptionWell() {
        return absorptionWell;
    }

    public void setAbsorptionWell(boolean absorptionWell) {
        this.absorptionWell = absorptionWell;
    }

    public boolean isInfiltrationField() {
        return infiltrationField;
    }

    public void setInfiltrationField(boolean infiltrationField) {
        this.infiltrationField = infiltrationField;
    }

    public boolean isHydrologicalSource() {
        return hydrologicalSource;
    }

    public void setHydrologicalSource(boolean hydrologicalSource) {
        this.hydrologicalSource = hydrologicalSource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.absorptionWell ? (byte) 1 : (byte) 0);
        dest.writeByte(this.infiltrationField ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hydrologicalSource ? (byte) 1 : (byte) 0);
    }

    public StardDischargeType() {
    }

    protected StardDischargeType(Parcel in) {
        this.absorptionWell = in.readByte() != 0;
        this.infiltrationField = in.readByte() != 0;
        this.hydrologicalSource = in.readByte() != 0;
    }

    public static final Creator<StardDischargeType> CREATOR = new Creator<StardDischargeType>() {
        @Override
        public StardDischargeType createFromParcel(Parcel source) {
            return new StardDischargeType(source);
        }

        @Override
        public StardDischargeType[] newArray(int size) {
            return new StardDischargeType[size];
        }
    };
}
