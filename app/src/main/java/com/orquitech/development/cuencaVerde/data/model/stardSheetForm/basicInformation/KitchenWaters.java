package com.orquitech.development.cuencaVerde.data.model.stardSheetForm.basicInformation;

import android.os.Parcel;
import android.os.Parcelable;

public class KitchenWaters implements Parcelable {

    private boolean isTheKitchenStreamIndependentFormTheSanitaryUnit;
    private boolean doesTheKitchenStreamHaveAGreaseTrap;

    public boolean isTheKitchenStreamIndependentFormTheSanitaryUnit() {
        return isTheKitchenStreamIndependentFormTheSanitaryUnit;
    }

    public void setTheKitchenStreamIndependentFormTheSanitaryUnit(boolean theKitchenStreamIndependentFormTheSanitaryUnit) {
        isTheKitchenStreamIndependentFormTheSanitaryUnit = theKitchenStreamIndependentFormTheSanitaryUnit;
    }

    public boolean isDoesTheKitchenStreamHaveAGreaseTrap() {
        return doesTheKitchenStreamHaveAGreaseTrap;
    }

    public void setDoesTheKitchenStreamHaveAGreaseTrap(boolean doesTheKitchenStreamHaveAGreaseTrap) {
        this.doesTheKitchenStreamHaveAGreaseTrap = doesTheKitchenStreamHaveAGreaseTrap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isTheKitchenStreamIndependentFormTheSanitaryUnit ? (byte) 1 : (byte) 0);
        dest.writeByte(this.doesTheKitchenStreamHaveAGreaseTrap ? (byte) 1 : (byte) 0);
    }

    public KitchenWaters() {
    }

    protected KitchenWaters(Parcel in) {
        this.isTheKitchenStreamIndependentFormTheSanitaryUnit = in.readByte() != 0;
        this.doesTheKitchenStreamHaveAGreaseTrap = in.readByte() != 0;
    }

    public static final Creator<KitchenWaters> CREATOR = new Creator<KitchenWaters>() {
        @Override
        public KitchenWaters createFromParcel(Parcel source) {
            return new KitchenWaters(source);
        }

        @Override
        public KitchenWaters[] newArray(int size) {
            return new KitchenWaters[size];
        }
    };
}
