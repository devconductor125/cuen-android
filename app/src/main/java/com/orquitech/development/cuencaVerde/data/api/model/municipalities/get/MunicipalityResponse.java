package com.orquitech.development.cuencaVerde.data.api.model.municipalities.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MunicipalityResponse extends CuencaVerdeResponse {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("departament_id")
    @Expose
    public String municipalityId;
}
