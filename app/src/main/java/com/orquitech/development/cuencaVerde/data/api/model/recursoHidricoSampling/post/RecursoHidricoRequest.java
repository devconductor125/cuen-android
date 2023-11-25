package com.orquitech.development.cuencaVerde.data.api.model.recursoHidricoSampling.post;

import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecursoHidricoRequest {

    @SerializedName("task_id")
    @Expose
    public String taskId;
    @SerializedName("data")
    @Expose
    public List<RecursoHidricoSamplingData> data;
    @SerializedName("geojson")
    @Expose
    public GeoJson geojson;
}
