package com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry {

    @SerializedName("coordinates")
    @Expose
    public List<Double> coordinates = null;
    @SerializedName("type")
    @Expose
    public String type;
}
