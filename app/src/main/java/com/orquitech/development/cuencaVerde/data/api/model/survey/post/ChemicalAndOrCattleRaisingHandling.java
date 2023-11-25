
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChemicalAndOrCattleRaisingHandling {

    @SerializedName("burning")
    @Expose
    private boolean burning;
    @SerializedName("burying")
    @Expose
    private boolean burying;
    @SerializedName("delivery_to_a_collecting_entity_without_separation")
    @Expose
    private boolean deliveryToACollectingEntityWithoutSeparation;
    @SerializedName("other_describe")
    @Expose
    private String otherDescribe;
    @SerializedName("separation_and_delivery_to_a_collecting_entity")
    @Expose
    private boolean separationAndDeliveryToACollectingEntity;

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

    public boolean isSeparationAndDeliveryToACollectingEntity() {
        return separationAndDeliveryToACollectingEntity;
    }

    public void setSeparationAndDeliveryToACollectingEntity(boolean separationAndDeliveryToACollectingEntity) {
        this.separationAndDeliveryToACollectingEntity = separationAndDeliveryToACollectingEntity;
    }

}
