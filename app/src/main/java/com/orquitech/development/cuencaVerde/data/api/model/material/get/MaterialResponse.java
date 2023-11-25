package com.orquitech.development.cuencaVerde.data.api.model.material.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaterialResponse {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("measurement")
    @Expose
    public String measurement;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("unit")
    @Expose
    public String unit;
}
