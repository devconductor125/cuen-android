package com.orquitech.development.cuencaVerde.data.api.model.quota.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuotaResponse extends CuencaVerdeResponse {

    @SerializedName("quote")
    @Expose
    public int quota;
    @SerializedName("total")
    @Expose
    public int predios;
    @SerializedName("percentage")
    @Expose
    public double percentage;
}
