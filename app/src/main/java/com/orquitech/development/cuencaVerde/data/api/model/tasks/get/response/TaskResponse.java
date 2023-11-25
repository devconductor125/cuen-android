
package com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response;

import java.util.List;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskResponse extends CuencaVerdeResponse {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("date_start")
    @Expose
    public String dateStart;
    @SerializedName("date_end")
    @Expose
    public String dateEnd;
    @SerializedName("option_date")
    @Expose
    public Integer optionDate;
    @SerializedName("state")
    @Expose
    public Integer state;
    @SerializedName("task_type_id")
    @Expose
    public String taskTypeId;
    @SerializedName("task_sub_type_name")
    @Expose
    public String taskSubTypeName;
    @SerializedName("task_status_id")
    @Expose
    public Integer taskStatusId;
    @SerializedName("task_sub_type_id")
    @Expose
    public Integer taskSubTypeId;
    @SerializedName("property_id")
    @Expose
    public Integer propertyId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("task_status_name")
    @Expose
    public String taskStatusName;
    @SerializedName("sub_type")
    @Expose
    public SubType subType;
    @SerializedName("role_id")
    @Expose
    public Integer roleId;
    @SerializedName("role_name")
    @Expose
    public String roleName;
    @SerializedName("process_id")
    @Expose
    public String processId;
    @SerializedName("potential_property_id")
    @Expose
    public String potentialId;
    @SerializedName("process")
    @Expose
    public List<Process> process = null;
    @SerializedName("task_user")
    @Expose
    public List<TaskUser> taskUser = null;
}
