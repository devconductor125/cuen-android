package com.orquitech.development.cuencaVerde.data.api.model.croquis.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon.MultiPolygon;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CroquisResponse extends CuencaVerdeResponse {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("predio_id")
    @Expose
    public String predioId;
    @SerializedName("multipolygon")
    @Expose
    public MultiPolygon multipolygon;
}
