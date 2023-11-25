package com.orquitech.development.cuencaVerde.data.model.cartaIntencion;

import android.os.Parcel;
import android.os.Parcelable;

public class DeforestationPrevention implements Parcelable {

    boolean checked;
    private boolean efficientStove;
    private boolean orchardFirewood;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isEfficientStove() {
        return efficientStove;
    }

    public void setEfficientStove(boolean efficientStove) {
        this.efficientStove = efficientStove;
    }

    public boolean isOrchardFirewood() {
        return orchardFirewood;
    }

    public void setOrchardFirewood(boolean orchardFirewood) {
        this.orchardFirewood = orchardFirewood;
    }

    public boolean isInsulation() {
        return efficientStove;
    }

    public void setInsulation(boolean insulation) {
        this.efficientStove = insulation;
    }

    public boolean isSeedlingsInsulation() {
        return orchardFirewood;
    }

    public void setSeedlingsInsulation(boolean seedlingsInsulation) {
        this.orchardFirewood = seedlingsInsulation;
    }

    public DeforestationPrevention() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.efficientStove ? (byte) 1 : (byte) 0);
        dest.writeByte(this.orchardFirewood ? (byte) 1 : (byte) 0);
    }

    protected DeforestationPrevention(Parcel in) {
        this.checked = in.readByte() != 0;
        this.efficientStove = in.readByte() != 0;
        this.orchardFirewood = in.readByte() != 0;
    }

    public static final Creator<DeforestationPrevention> CREATOR = new Creator<DeforestationPrevention>() {
        @Override
        public DeforestationPrevention createFromParcel(Parcel source) {
            return new DeforestationPrevention(source);
        }

        @Override
        public DeforestationPrevention[] newArray(int size) {
            return new DeforestationPrevention[size];
        }
    };
}
