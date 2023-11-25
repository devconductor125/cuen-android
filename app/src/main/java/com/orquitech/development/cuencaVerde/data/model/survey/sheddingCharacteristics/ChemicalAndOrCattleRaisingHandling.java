package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class ChemicalAndOrCattleRaisingHandling implements Parcelable {

    private boolean separationAndDeliveryToACollectingEntity;
    private boolean burning;
    private boolean burying;
    private boolean deliveryToACollectingEntityWithoutSeparation;
    private String otherDescribe;

    public boolean isSeparationAndDeliveryToACollectingEntity() {
        return separationAndDeliveryToACollectingEntity;
    }

    public void setSeparationAndDeliveryToACollectingEntity(boolean separationAndDeliveryToACollectingEntity) {
        this.separationAndDeliveryToACollectingEntity = separationAndDeliveryToACollectingEntity;
    }

    public boolean isBurning() {
        return burning;
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public boolean isBurying() {
        return burying;
    }

    public void setBurying(boolean burying) {
        this.burying = burying;
    }

    public boolean isDeliveryToACollectingEntityWithoutSeparation() {
        return deliveryToACollectingEntityWithoutSeparation;
    }

    public void setDeliveryToACollectingEntityWithoutSeparation(boolean deliveryToACollectingEntityWithoutSeparation) {
        this.deliveryToACollectingEntityWithoutSeparation = deliveryToACollectingEntityWithoutSeparation;
    }

    public String getOtherDescribe() {
        return otherDescribe;
    }

    public void setOtherDescribe(String otherDescribe) {
        this.otherDescribe = otherDescribe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.separationAndDeliveryToACollectingEntity ? (byte) 1 : (byte) 0);
        dest.writeByte(this.burning ? (byte) 1 : (byte) 0);
        dest.writeByte(this.burying ? (byte) 1 : (byte) 0);
        dest.writeByte(this.deliveryToACollectingEntityWithoutSeparation ? (byte) 1 : (byte) 0);
        dest.writeString(this.otherDescribe);
    }

    public ChemicalAndOrCattleRaisingHandling() {
    }

    protected ChemicalAndOrCattleRaisingHandling(Parcel in) {
        this.separationAndDeliveryToACollectingEntity = in.readByte() != 0;
        this.burning = in.readByte() != 0;
        this.burying = in.readByte() != 0;
        this.deliveryToACollectingEntityWithoutSeparation = in.readByte() != 0;
        this.otherDescribe = in.readString();
    }

    public static final Creator<ChemicalAndOrCattleRaisingHandling> CREATOR = new Creator<ChemicalAndOrCattleRaisingHandling>() {
        @Override
        public ChemicalAndOrCattleRaisingHandling createFromParcel(Parcel source) {
            return new ChemicalAndOrCattleRaisingHandling(source);
        }

        @Override
        public ChemicalAndOrCattleRaisingHandling[] newArray(int size) {
            return new ChemicalAndOrCattleRaisingHandling[size];
        }
    };
}
