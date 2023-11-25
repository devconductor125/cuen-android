package com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get;

import java.util.List;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonitoringMaintenanceResponse extends CuencaVerdeResponse {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("hash_map")
    @Expose
    public String hashMap;
    @SerializedName("start")
    @Expose
    public String start;
    @SerializedName("end")
    @Expose
    public String end;
    @SerializedName("date_start")
    @Expose
    public String dateStart;
    @SerializedName("date_deadline")
    @Expose
    public String dateDeadline;
    @SerializedName("type_monitoring")
    @Expose
    public String typeMonitoring;
    @SerializedName("comment_by_monitoring")
    @Expose
    public List<CommentByMonitoring> commentByMonitoring = null;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("geojson_feature")
    @Expose
    public String geojsonFeature;
    @SerializedName("points")
    @Expose
    public List<Point> points = null;
    @SerializedName("budget")
    @Expose
    public Budget budget;
    @SerializedName("potential_property_id")
    @Expose
    public String potentialId;
}
