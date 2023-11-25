package com.orquitech.development.cuencaVerde.data.api.model.cartaAndSurvey.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartaAndSurveyResponse extends CuencaVerdeResponse {

    @SerializedName("info_general")
    @Expose
    public String infoGeneral;
    @SerializedName("title_letter")
    @Expose
    public String titleLetter;
    @SerializedName("form_letter")
    @Expose
    public String formLetter;
    @SerializedName("potential_id")
    @Expose
    public Integer potentialId;
}
