package com.orquitech.development.cuencaVerde.data.model.survey;

import android.os.Parcel;
import android.os.Parcelable;

public class WaysOfAccess implements Parcelable {

    private boolean primaryRoad;
    private boolean secondaryRoad;
    private boolean thirdClassRoad;
    private boolean unpavedRoad;
    private boolean canBeReachedByCar;

    public boolean isPrimaryRoad() {
        return primaryRoad;
    }

    public void setPrimaryRoad(boolean primaryRoad) {
        this.primaryRoad = primaryRoad;
    }

    public boolean isSecondaryRoad() {
        return secondaryRoad;
    }

    public void setSecondaryRoad(boolean secondaryRoad) {
        this.secondaryRoad = secondaryRoad;
    }

    public boolean isThirdClassRoad() {
        return thirdClassRoad;
    }

    public void setThirdClassRoad(boolean thirdClassRoad) {
        this.thirdClassRoad = thirdClassRoad;
    }

    public boolean isUnpavedRoad() {
        return unpavedRoad;
    }

    public void setUnpavedRoad(boolean unpavedRoad) {
        this.unpavedRoad = unpavedRoad;
    }

    public boolean isCanBeReachedByCar() {
        return canBeReachedByCar;
    }

    public void setCanBeReachedByCar(boolean canBeReachedByCar) {
        this.canBeReachedByCar = canBeReachedByCar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.primaryRoad ? (byte) 1 : (byte) 0);
        dest.writeByte(this.secondaryRoad ? (byte) 1 : (byte) 0);
        dest.writeByte(this.thirdClassRoad ? (byte) 1 : (byte) 0);
        dest.writeByte(this.unpavedRoad ? (byte) 1 : (byte) 0);
        dest.writeByte(this.canBeReachedByCar ? (byte) 1 : (byte) 0);
    }

    public WaysOfAccess() {
    }

    protected WaysOfAccess(Parcel in) {
        this.primaryRoad = in.readByte() != 0;
        this.secondaryRoad = in.readByte() != 0;
        this.thirdClassRoad = in.readByte() != 0;
        this.unpavedRoad = in.readByte() != 0;
        this.canBeReachedByCar = in.readByte() != 0;
    }

    public static final Creator<WaysOfAccess> CREATOR = new Creator<WaysOfAccess>() {
        @Override
        public WaysOfAccess createFromParcel(Parcel source) {
            return new WaysOfAccess(source);
        }

        @Override
        public WaysOfAccess[] newArray(int size) {
            return new WaysOfAccess[size];
        }
    };
}
