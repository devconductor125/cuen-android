
package com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot {

    @SerializedName("task_id")
    @Expose
    public Integer taskId;
    @SerializedName("process_id")
    @Expose
    public Integer processId;

}
