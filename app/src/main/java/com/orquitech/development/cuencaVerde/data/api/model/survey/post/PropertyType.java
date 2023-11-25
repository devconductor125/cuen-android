
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyType {

    @SerializedName("commercial")
    @Expose
    private boolean commercial;
    @SerializedName("other")
    @Expose
    private Object other;
    @SerializedName("property_other")
    @Expose
    private boolean propertyOther;
    @SerializedName("residential")
    @Expose
    private boolean residential;
    @SerializedName("value")
    @Expose
    private int value;

    public boolean isCommercial() {
        return commercial;
    }

    public void setCommercial(boolean commercial) {
        this.commercial = commercial;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    public boolean isPropertyOther() {
        return propertyOther;
    }

    public void setPropertyOther(boolean propertyOther) {
        this.propertyOther = propertyOther;
    }

    public boolean isResidential() {
        return residential;
    }

    public void setResidential(boolean residential) {
        this.residential = residential;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
