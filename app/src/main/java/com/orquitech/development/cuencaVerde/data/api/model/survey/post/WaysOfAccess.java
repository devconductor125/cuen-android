
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WaysOfAccess {

    @SerializedName("can_be_reached_by_car")
    @Expose
    private boolean canBeReachedByCar;
    @SerializedName("primary_road")
    @Expose
    private boolean primaryRoad;
    @SerializedName("secondary_road")
    @Expose
    private boolean secondaryRoad;
    @SerializedName("third_class_road")
    @Expose
    private boolean thirdClassRoad;
    @SerializedName("unpaved_road")
    @Expose
    private boolean unpavedRoad;

    public boolean isCanBeReachedByCar() {
        return canBeReachedByCar;
    }

    public void setCanBeReachedByCar(boolean canBeReachedByCar) {
        this.canBeReachedByCar = canBeReachedByCar;
    }

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

}
