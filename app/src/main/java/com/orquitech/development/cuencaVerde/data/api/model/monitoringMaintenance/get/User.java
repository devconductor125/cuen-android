package com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("names")
    @Expose
    public Object names;
    @SerializedName("last_names")
    @Expose
    public Object lastNames;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
}
