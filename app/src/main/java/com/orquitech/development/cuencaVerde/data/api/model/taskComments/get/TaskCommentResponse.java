
package com.orquitech.development.cuencaVerde.data.api.model.taskComments.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskCommentResponse extends CuencaVerdeResponse {

    @SerializedName("comment_id")
    @Expose
    public int id;
    @SerializedName("user_name")
    @Expose
    public String username;
    @SerializedName("description")
    @Expose
    public String description;
}
