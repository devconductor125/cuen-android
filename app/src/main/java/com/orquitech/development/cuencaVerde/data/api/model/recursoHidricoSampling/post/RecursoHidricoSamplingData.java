package com.orquitech.development.cuencaVerde.data.api.model.recursoHidricoSampling.post;

import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecursoHidricoSamplingData {

    @SerializedName("hash")
    @Expose
    public String hash;
    @SerializedName("form")
    @Expose
    public List<SamplingPointForm> forms;
}
