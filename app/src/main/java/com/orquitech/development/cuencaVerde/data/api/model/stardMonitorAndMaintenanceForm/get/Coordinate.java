package com.orquitech.development.cuencaVerde.data.api.model.stardMonitorAndMaintenanceForm.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinate {

    @SerializedName("lat")
    @Expose
    public double lat;
    @SerializedName("lon")
    @Expose
    public double lon;
}
