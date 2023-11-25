package com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("names")
    @Expose
    public String names;
    @SerializedName("last_names")
    @Expose
    public String lastNames;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("state")
    @Expose
    public Integer state;
    @SerializedName("role_id")
    @Expose
    public Integer roleId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
}
