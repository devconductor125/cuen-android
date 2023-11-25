
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TreatmentSystemStatus {

    @SerializedName("average")
    @Expose
    private boolean average;
    @SerializedName("bad")
    @Expose
    private boolean bad;
    @SerializedName("good")
    @Expose
    private boolean good;

    public boolean isAverage() {
        return average;
    }

    public void setAverage(boolean average) {
        this.average = average;
    }

    public boolean isBad() {
        return bad;
    }

    public void setBad(boolean bad) {
        this.bad = bad;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

}
