
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NaturalEcosystemsInTheProperty {

    @SerializedName("contaminated")
    @Expose
    private String contaminated;
    @SerializedName("erosion")
    @Expose
    private String erosion;
    @SerializedName("mountainside_forest")
    @Expose
    private boolean mountainsideForest;
    @SerializedName("protected_source")
    @Expose
    private String protectedSource;
    @SerializedName("riverbank_area")
    @Expose
    private boolean riverbankArea;
    @SerializedName("riverbank_forest")
    @Expose
    private boolean riverbankForest;
    @SerializedName("spring")
    @Expose
    private boolean spring;
    @SerializedName("un_protected")
    @Expose
    private String unProtected;

    public String getContaminated() {
        return contaminated;
    }

    public void setContaminated(String contaminated) {
        this.contaminated = contaminated;
    }

    public String getErosion() {
        return erosion;
    }

    public void setErosion(String erosion) {
        this.erosion = erosion;
    }

    public boolean isMountainsideForest() {
        return mountainsideForest;
    }

    public void setMountainsideForest(boolean mountainsideForest) {
        this.mountainsideForest = mountainsideForest;
    }

    public String getProtectedSource() {
        return protectedSource;
    }

    public void setProtectedSource(String protectedSource) {
        this.protectedSource = protectedSource;
    }

    public boolean isRiverbankArea() {
        return riverbankArea;
    }

    public void setRiverbankArea(boolean riverbankArea) {
        this.riverbankArea = riverbankArea;
    }

    public boolean isRiverbankForest() {
        return riverbankForest;
    }

    public void setRiverbankForest(boolean riverbankForest) {
        this.riverbankForest = riverbankForest;
    }

    public boolean isSpring() {
        return spring;
    }

    public void setSpring(boolean spring) {
        this.spring = spring;
    }

    public String getUnProtected() {
        return unProtected;
    }

    public void setUnProtected(String unProtected) {
        this.unProtected = unProtected;
    }

}
