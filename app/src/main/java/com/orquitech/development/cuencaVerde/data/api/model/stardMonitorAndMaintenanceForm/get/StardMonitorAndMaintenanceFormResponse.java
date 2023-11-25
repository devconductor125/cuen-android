package com.orquitech.development.cuencaVerde.data.api.model.stardMonitorAndMaintenanceForm.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StardMonitorAndMaintenanceFormResponse extends CuencaVerdeResponse {

    @SerializedName("id")
    @Expose
    public String id = "-1";
    @SerializedName("property_name")
    @Expose
    public String propertyName;
    @SerializedName("municipality")
    @Expose
    public String municipality;
    @SerializedName("lane")
    @Expose
    public String lane;
    @SerializedName("contact")
    @Expose
    public Contact contact;
    @SerializedName("coordinate")
    @Expose
    public Coordinate coordinate;
}
