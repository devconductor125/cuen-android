package com.orquitech.development.cuencaVerde.data.model.stardSheetForm.basicInformation;

import android.os.Parcel;
import android.os.Parcelable;

public class StardEnclosurePreference implements Parcelable {

    private boolean barbWire;
    private boolean smoothWire;

    public boolean isBarbWire() {
        return barbWire;
    }

    public void setBarbWire(boolean barbWire) {
        this.barbWire = barbWire;
    }

    public boolean isSmoothWire() {
        return smoothWire;
    }

    public void setSmoothWire(boolean smoothWire) {
        this.smoothWire = smoothWire;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.barbWire ? (byte) 1 : (byte) 0);
        dest.writeByte(this.smoothWire ? (byte) 1 : (byte) 0);
    }

    public StardEnclosurePreference() {
    }

    protected StardEnclosurePreference(Parcel in) {
        this.barbWire = in.readByte() != 0;
        this.smoothWire = in.readByte() != 0;
    }

    public static final Creator<StardEnclosurePreference> CREATOR = new Creator<StardEnclosurePreference>() {
        @Override
        public StardEnclosurePreference createFromParcel(Parcel source) {
            return new StardEnclosurePreference(source);
        }

        @Override
        public StardEnclosurePreference[] newArray(int size) {
            return new StardEnclosurePreference[size];
        }
    };
}
