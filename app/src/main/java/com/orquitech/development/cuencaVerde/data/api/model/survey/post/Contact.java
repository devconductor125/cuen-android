
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("contact_email")
    @Expose
    private String contactEmail;
    @SerializedName("contact_id_card_number")
    @Expose
    private String contactIdCardNumber;
    @SerializedName("contact_land_line_number")
    @Expose
    private String contactLandLineNumber;
    @SerializedName("contact_mobile_number")
    @Expose
    private String contactMobileNumber;
    @SerializedName("contact_name")
    @Expose
    private String contactName;

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactIdCardNumber() {
        return contactIdCardNumber;
    }

    public void setContactIdCardNumber(String contactIdCardNumber) {
        this.contactIdCardNumber = contactIdCardNumber;
    }

    public String getContactLandLineNumber() {
        return contactLandLineNumber;
    }

    public void setContactLandLineNumber(String contactLandLineNumber) {
        this.contactLandLineNumber = contactLandLineNumber;
    }

    public String getContactMobileNumber() {
        return contactMobileNumber;
    }

    public void setContactMobileNumber(String contactMobileNumber) {
        this.contactMobileNumber = contactMobileNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

}
