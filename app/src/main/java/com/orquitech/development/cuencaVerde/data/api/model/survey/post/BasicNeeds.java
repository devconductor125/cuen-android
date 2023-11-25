
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicNeeds {

    @SerializedName("aqueduct_network")
    @Expose
    private boolean aqueductNetwork;
    @SerializedName("electrical_network")
    @Expose
    private boolean electricalNetwork;
    @SerializedName("sewerage_system")
    @Expose
    private boolean sewerageSystem;

    public boolean isAqueductNetwork() {
        return aqueductNetwork;
    }

    public void setAqueductNetwork(boolean aqueductNetwork) {
        this.aqueductNetwork = aqueductNetwork;
    }

    public boolean isElectricalNetwork() {
        return electricalNetwork;
    }

    public void setElectricalNetwork(boolean electricalNetwork) {
        this.electricalNetwork = electricalNetwork;
    }

    public boolean isSewerageSystem() {
        return sewerageSystem;
    }

    public void setSewerageSystem(boolean sewerageSystem) {
        this.sewerageSystem = sewerageSystem;
    }
}
