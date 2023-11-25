
package com.orquitech.development.cuencaVerde.data.api.model.survey.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurveyResponse extends CuencaVerdeResponse {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("lat")
    @Expose
    public double lat;
    @SerializedName("lng")
    @Expose
    public double lng;
}
