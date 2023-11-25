
package com.orquitech.development.cuencaVerde.data.api.model.tasks.post;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExecutionTaskRequest extends CuencaVerdeResponse {

    @SerializedName("task_id")
    @Expose
    public String taskId;
    @SerializedName("mapjson")
    @Expose
    public GeoJson mapjson;
}
