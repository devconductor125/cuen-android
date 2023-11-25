package com.orquitech.development.cuencaVerde.data.api.model.erosiveProcess.post;

import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ErosiveProcessData {

    @SerializedName("hash")
    @Expose
    public String hash;
    @SerializedName("form")
    @Expose
    public List<ErosiveProcessForm> forms;
}
