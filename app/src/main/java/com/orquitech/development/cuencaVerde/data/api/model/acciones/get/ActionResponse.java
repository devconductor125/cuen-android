package com.orquitech.development.cuencaVerde.data.api.model.acciones.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActionResponse extends CuencaVerdeResponse {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("color")
    @Expose
    public String color;
    @SerializedName("color_fill")
    @Expose
    public String color_fill;
}
