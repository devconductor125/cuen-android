package com.orquitech.development.cuencaVerde.data.api.model.erosiveProcess.post;

import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ErosiveProcessRequest {

    @SerializedName("task_id")
    @Expose
    public String taskId;
    @SerializedName("data")
    @Expose
    public List<ErosiveProcessData> data;
    @SerializedName("geojson")
    @Expose
    public GeoJson geojson;
}
