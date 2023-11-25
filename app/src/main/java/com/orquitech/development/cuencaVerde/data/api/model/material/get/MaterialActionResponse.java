package com.orquitech.development.cuencaVerde.data.api.model.material.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MaterialActionResponse extends CuencaVerdeResponse {

    @SerializedName("action")
    @Expose
    public int action;
    @SerializedName("materials")
    @Expose
    public List<MaterialResponse> materials = null;
}
