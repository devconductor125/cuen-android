package com.orquitech.development.cuencaVerde.data.api.model.contractor.post;

import com.orquitech.development.cuencaVerde.data.model.siembraDetailForm.SiembraDetailForm;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("hash")
    @Expose
    public String hash;
    @SerializedName("form")
    @Expose
    public List<SiembraDetailForm> forms;
}
