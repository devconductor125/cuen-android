
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DomesticSolidWasteHarvestingHandling {

    @SerializedName("burning")
    @Expose
    private boolean burning;
    @SerializedName("harnessing")
    @Expose
    private boolean harnessing;
    @SerializedName("none")
    @Expose
    private boolean none;
    @SerializedName("recycling")
    @Expose
    private boolean recycling;
    @SerializedName("separation")
    @Expose
    private boolean separation;

    public boolean isBurning() {
        return burning;
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public boolean isHarnessing() {
        return harnessing;
    }

    public void setHarnessing(boolean harnessing) {
        this.harnessing = harnessing;
    }

    public boolean isNone() {
        return none;
    }

    public void setNone(boolean none) {
        this.none = none;
    }

    public boolean isRecycling() {
        return recycling;
    }

    public void setRecycling(boolean recycling) {
        this.recycling = recycling;
    }

    public boolean isSeparation() {
        return separation;
    }

    public void setSeparation(boolean separation) {
        this.separation = separation;
    }

}
