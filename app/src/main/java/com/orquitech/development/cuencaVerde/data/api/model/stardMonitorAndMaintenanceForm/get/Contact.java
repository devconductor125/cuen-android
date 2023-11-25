package com.orquitech.development.cuencaVerde.data.api.model.stardMonitorAndMaintenanceForm.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("contact_email")
    @Expose
    public String contactEmail;
    @SerializedName("contact_id_card_number")
    @Expose
    public String contactIdCardNumber;
    @SerializedName("contact_land_line_number")
    @Expose
    public String contactLandLineNumber;
    @SerializedName("contact_mobile_number")
    @Expose
    public String contactMobileNumber;
    @SerializedName("contact_name")
    @Expose
    public String contactName;
}
