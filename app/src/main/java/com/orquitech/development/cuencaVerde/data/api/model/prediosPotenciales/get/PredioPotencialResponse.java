package com.orquitech.development.cuencaVerde.data.api.model.prediosPotenciales.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PredioPotencialResponse extends CuencaVerdeResponse {

    @SerializedName("id")
    @Expose
    public String remoteId;
    @SerializedName("property_name")
    @Expose
    public String propertyName;
    @SerializedName("main_coordinate")
    @Expose
    public String mainCoordinate;
    @SerializedName("subtype_id")
    @Expose
    public int subtypeId;
    @SerializedName("rol_id")
    @Expose
    public int rolId;
    @SerializedName("archive_load")
    @Expose
    public int archiveLoad;
}
