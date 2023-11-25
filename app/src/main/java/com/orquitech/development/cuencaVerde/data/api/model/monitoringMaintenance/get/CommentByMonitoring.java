package com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentByMonitoring {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("monitoring_id")
    @Expose
    public Integer monitoringId;
    @SerializedName("monitoring_point_id")
    @Expose
    public Object monitoringPointId;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
}
