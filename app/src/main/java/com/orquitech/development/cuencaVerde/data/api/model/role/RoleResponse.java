package com.orquitech.development.cuencaVerde.data.api.model.role;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoleResponse extends CuencaVerdeResponse {

    @SerializedName("entity")
    @Expose
    public long entity;
    @SerializedName("role")
    @Expose
    public long role;
    @SerializedName("permission")
    @Expose
    public long permission;
}
