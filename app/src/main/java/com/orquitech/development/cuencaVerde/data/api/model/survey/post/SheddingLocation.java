
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SheddingLocation {

    @SerializedName("hydrological_source")
    @Expose
    private boolean hydrologicalSource;
    @SerializedName("sewerage_system")
    @Expose
    private boolean sewerageSystem;
    @SerializedName("soil")
    @Expose
    private boolean soil;

    public boolean isHydrologicalSource() {
        return hydrologicalSource;
    }

    public void setHydrologicalSource(boolean hydrologicalSource) {
        this.hydrologicalSource = hydrologicalSource;
    }

    public boolean isSewerageSystem() {
        return sewerageSystem;
    }

    public void setSewerageSystem(boolean sewerageSystem) {
        this.sewerageSystem = sewerageSystem;
    }

    public boolean isSoil() {
        return soil;
    }

    public void setSoil(boolean soil) {
        this.soil = soil;
    }

}
