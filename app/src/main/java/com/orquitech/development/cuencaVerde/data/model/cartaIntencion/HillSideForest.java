package com.orquitech.development.cuencaVerde.data.model.cartaIntencion;

import android.os.Parcel;
import android.os.Parcelable;

public class HillSideForest implements Parcelable {

    boolean checked;
    private boolean insulation;
    private boolean seedlingsInsulation;
    private boolean establishment;
    private boolean enrichment;

    public boolean isInsulation() {
        return insulation;
    }

    public void setInsulation(boolean insulation) {
        this.insulation = insulation;
    }

    public boolean isSeedlingsInsulation() {
        return seedlingsInsulation;
    }

    public void setSeedlingsInsulation(boolean seedlingsInsulation) {
        this.seedlingsInsulation = seedlingsInsulation;
    }

    public boolean isEstablishment() {
        return establishment;
    }

    public void setEstablishment(boolean establishment) {
        this.establishment = establishment;
    }

    public boolean isEnrichment() {
        return enrichment;
    }

    public void setEnrichment(boolean enrichment) {
        this.enrichment = enrichment;
    }

    public HillSideForest() {
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.insulation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.seedlingsInsulation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.establishment ? (byte) 1 : (byte) 0);
        dest.writeByte(this.enrichment ? (byte) 1 : (byte) 0);
    }

    protected HillSideForest(Parcel in) {
        this.checked = in.readByte() != 0;
        this.insulation = in.readByte() != 0;
        this.seedlingsInsulation = in.readByte() != 0;
        this.establishment = in.readByte() != 0;
        this.enrichment = in.readByte() != 0;
    }

    public static final Creator<HillSideForest> CREATOR = new Creator<HillSideForest>() {
        @Override
        public HillSideForest createFromParcel(Parcel source) {
            return new HillSideForest(source);
        }

        @Override
        public HillSideForest[] newArray(int size) {
            return new HillSideForest[size];
        }
    };
}
