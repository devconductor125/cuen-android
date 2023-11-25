package com.orquitech.development.cuencaVerde.data.api.model.province.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProvinceResponse extends CuencaVerdeResponse {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
}
