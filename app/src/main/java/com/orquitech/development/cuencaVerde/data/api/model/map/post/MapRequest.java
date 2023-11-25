package com.orquitech.development.cuencaVerde.data.api.model.map.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapRequest {

    @SerializedName("geojson")
    @Expose
    public String geojson;
    @SerializedName("task_id")
    @Expose
    public String taskId;
    @SerializedName("state")
    @Expose
    public int state;
}
