package com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonitoringMaintenanceRequest {

    @SerializedName("point_id")
    @Expose
    public String pointId;
    @SerializedName("monitor_and_maintenance_id")
    @Expose
    public String monitorAndMaintenanceId;
    @SerializedName("comment")
    @Expose
    public String comment;
    @SerializedName("lat")
    @Expose
    public Double lat;
    @SerializedName("lng")
    @Expose
    public Double lng;
}
