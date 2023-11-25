package com.orquitech.development.cuencaVerde.data.api.model.projects.get.response;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.Pivot;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProcedureResponse extends CuencaVerdeResponse {

    @SerializedName("id")
    @Expose
    public String id;
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
