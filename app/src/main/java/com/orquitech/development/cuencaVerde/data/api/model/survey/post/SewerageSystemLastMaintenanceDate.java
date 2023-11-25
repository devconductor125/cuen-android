
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SewerageSystemLastMaintenanceDate {

    @SerializedName("cant_remember")
    @Expose
    private boolean cantRemember;
    @SerializedName("never")
    @Expose
    private boolean never;
    @SerializedName("years12")
    @Expose
    private boolean years12;
    @SerializedName("years23")
    @Expose
    private boolean years23;
    @SerializedName("years34")
    @Expose
    private boolean years34;

    public boolean isCantRemember() {
        return cantRemember;
    }

    public void setCantRemember(boolean cantRemember) {
        this.cantRemember = cantRemember;
    }

    public boolean isNever() {
        return never;
    }

    public void setNever(boolean never) {
        this.never = never;
    }

    public boolean isYears12() {
        return years12;
    }

    public void setYears12(boolean years12) {
        this.years12 = years12;
    }

    public boolean isYears23() {
        return years23;
    }

    public void setYears23(boolean years23) {
        this.years23 = years23;
    }

    public boolean isYears34() {
        return years34;
    }

    public void setYears34(boolean years34) {
        this.years34 = years34;
    }

}
