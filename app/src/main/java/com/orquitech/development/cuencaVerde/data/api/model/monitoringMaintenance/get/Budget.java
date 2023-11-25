package com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Budget {

    @SerializedName("action_name")
    @Expose
    public String actionName;
    @SerializedName("action_type")
    @Expose
    public String actionType;
    @SerializedName("material_name")
    @Expose
    public String materialName;
    @SerializedName("material_type")
    @Expose
    public String materialType;
}
