package com.orquitech.development.cuencaVerde.data.model.survey.basicNeeds;

import android.os.Parcel;
import android.os.Parcelable;

public class ElectricitySource implements Parcelable {

    private boolean network;
    private boolean station;
    private boolean solarEnergy;
    private boolean candles;
    private String other;

    public boolean isNetwork() {
        return network;
    }

    public void setNetwork(boolean network) {
        this.network = network;
    }

    public boolean isStation() {
        return station;
    }

    public void setStation(boolean station) {
        this.station = station;
    }

    public boolean isSolarEnergy() {
        return solarEnergy;
    }

    public void setSolarEnergy(boolean solarEnergy) {
        this.solarEnergy = solarEnergy;
    }

    public boolean isCandles() {
        return candles;
    }

    public void setCandles(boolean candles) {
        this.candles = candles;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.network ? (byte) 1 : (byte) 0);
        dest.writeByte(this.station ? (byte) 1 : (byte) 0);
        dest.writeByte(this.solarEnergy ? (byte) 1 : (byte) 0);
        dest.writeByte(this.candles ? (byte) 1 : (byte) 0);
        dest.writeString(this.other);
    }

    public ElectricitySource() {
    }

    protected ElectricitySource(Parcel in) {
        this.network = in.readByte() != 0;
        this.station = in.readByte() != 0;
        this.solarEnergy = in.readByte() != 0;
        this.candles = in.readByte() != 0;
        this.other = in.readString();
    }

    public static final Creator<ElectricitySource> CREATOR = new Creator<ElectricitySource>() {
        @Override
        public ElectricitySource createFromParcel(Parcel source) {
            return new ElectricitySource(source);
        }

        @Override
        public ElectricitySource[] newArray(int size) {
            return new ElectricitySource[size];
        }
    };
}
