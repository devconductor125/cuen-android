package com.orquitech.development.cuencaVerde.data.api.model.meetingsWithActors.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeetingsWithActorsFormRequest {

    @SerializedName("task_id")
    @Expose
    public String taskId;
    @SerializedName("municipality")
    @Expose
    public String municipality;
    @SerializedName("basin")
    @Expose
    public String basin;
    @SerializedName("sidewalk")
    @Expose
    public String sidewalk;
    @SerializedName("objective_group")
    @Expose
    public String objectiveGroup;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("associated_name")
    @Expose
    public String associatedName;
    @SerializedName("associated_id")
    @Expose
    public Integer associatedId;
    @SerializedName("number_attendees")
    @Expose
    public String numberAttendees;
    @SerializedName("number_trees")
    @Expose
    public String numberTrees;
    @SerializedName("experence_type")
    @Expose
    public String experenceType;
    @SerializedName("experence_consolidated")
    @Expose
    public String experenceConsolidated;
    @SerializedName("event_name")
    @Expose
    public String eventName;
    @SerializedName("asistent_list")
    @Expose
    public String asistentList;
    @SerializedName("registry_photographic")
    @Expose
    public String registryPhotographic;
    @SerializedName("type")
    @Expose
    public Integer type;
    @SerializedName("experience")
    @Expose
    public boolean experience;
    @SerializedName("workshop")
    @Expose
    public boolean workshop;
    @SerializedName("sowing")
    @Expose
    public boolean sowing;
    @SerializedName("training")
    @Expose
    public boolean training;
}
