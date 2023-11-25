
package com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskUser {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("task_id")
    @Expose
    public Integer taskId;

}
