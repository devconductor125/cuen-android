package com.orquitech.development.cuencaVerde.data.api.model.SendSurveyCarta.post;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response extends CuencaVerdeResponse {

    @SerializedName("poll")
    @Expose
    public int poll;
    @SerializedName("letter")
    @Expose
    public int letter;
}
