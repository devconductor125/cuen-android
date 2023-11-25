
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SheddingSchedule {

    @SerializedName("hours24")
    @Expose
    private boolean hours24;
    @SerializedName("hours24_to12")
    @Expose
    private boolean hours24To12;
    @SerializedName("hours_less_than12")
    @Expose
    private boolean hoursLessThan12;

    public boolean isHours24() {
        return hours24;
    }

    public void setHours24(boolean hours24) {
        this.hours24 = hours24;
    }

    public boolean isHours24To12() {
        return hours24To12;
    }

    public void setHours24To12(boolean hours24To12) {
        this.hours24To12 = hours24To12;
    }

    public boolean isHoursLessThan12() {
        return hoursLessThan12;
    }

    public void setHoursLessThan12(boolean hoursLessThan12) {
        this.hoursLessThan12 = hoursLessThan12;
    }

}
