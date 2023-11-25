
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnvironmentalDamage {

    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("natives_logging")
    @Expose
    private boolean nativesLogging;
    @SerializedName("others")
    @Expose
    private String others;
    @SerializedName("wetland_desiccation")
    @Expose
    private boolean wetlandDesiccation;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isNativesLogging() {
        return nativesLogging;
    }

    public void setNativesLogging(boolean nativesLogging) {
        this.nativesLogging = nativesLogging;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public boolean isWetlandDesiccation() {
        return wetlandDesiccation;
    }

    public void setWetlandDesiccation(boolean wetlandDesiccation) {
        this.wetlandDesiccation = wetlandDesiccation;
    }

}
