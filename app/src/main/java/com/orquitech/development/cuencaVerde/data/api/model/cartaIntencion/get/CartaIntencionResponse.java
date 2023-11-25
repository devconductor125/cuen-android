package com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartaIntencionResponse extends CuencaVerdeResponse {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("form_letter")
    @Expose
    public String formLetter;
    @SerializedName("process_id")
    @Expose
    public Integer processId;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("task_type_id")
    @Expose
    public Integer taskTypeId;
    @SerializedName("task_type_name")
    @Expose
    public String taskTypeName;
    @SerializedName("process")
    @Expose
    public Process process;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("sub_type")
    @Expose
    public SubType subType;
}
