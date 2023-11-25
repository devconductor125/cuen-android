package com.orquitech.development.cuencaVerde.data.api.model.tasks.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenTaskCommentRequest {

    @SerializedName("task_id")
    @Expose
    public int taskId;
    @SerializedName("type")
    @Expose
    public int type;
    @SerializedName("description")
    @Expose
    public String description;
}
