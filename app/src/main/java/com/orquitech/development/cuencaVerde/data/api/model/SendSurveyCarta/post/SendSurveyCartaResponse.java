package com.orquitech.development.cuencaVerde.data.api.model.SendSurveyCarta.post;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendSurveyCartaResponse extends CuencaVerdeResponse {

    @SerializedName("response")
    @Expose
    public Response response;
}
