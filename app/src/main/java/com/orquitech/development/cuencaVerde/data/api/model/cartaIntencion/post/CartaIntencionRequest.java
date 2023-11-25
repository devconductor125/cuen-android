package com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.post;

import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartaIntencionRequest {

    @SerializedName("form_letter")
    @Expose
    public CartaIntencion formLetter;
    @SerializedName("process_id")
    @Expose
    public int processId;
    @SerializedName("user_id")
    @Expose
    public int userId;
    @SerializedName("type_update")
    @Expose
    public int typeUpdate;
}
