package com.orquitech.development.cuencaVerde.data.api.model.contractor.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SiembraDetailsRequest {

    @SerializedName("task_id")
    @Expose
    public String taskId;
    @SerializedName("data")
    @Expose
    public List<Data> data;
}
