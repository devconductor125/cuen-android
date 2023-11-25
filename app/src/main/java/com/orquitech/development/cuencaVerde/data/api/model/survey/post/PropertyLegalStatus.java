
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyLegalStatus {

    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("possession")
    @Expose
    private boolean possession;
    @SerializedName("succession")
    @Expose
    private boolean succession;
    @SerializedName("tenant_status")
    @Expose
    private boolean tenantStatus;
    @SerializedName("value")
    @Expose
    private int value;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isPossession() {
        return possession;
    }

    public void setPossession(boolean possession) {
        this.possession = possession;
    }

    public boolean isSuccession() {
        return succession;
    }

    public void setSuccession(boolean succession) {
        this.succession = succession;
    }

    public boolean isTenantStatus() {
        return tenantStatus;
    }

    public void setTenantStatus(boolean tenantStatus) {
        this.tenantStatus = tenantStatus;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
