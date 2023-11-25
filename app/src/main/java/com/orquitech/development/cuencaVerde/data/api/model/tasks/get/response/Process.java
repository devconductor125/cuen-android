package com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Process {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("pivot")
    @Expose
    public Pivot pivot;
}
