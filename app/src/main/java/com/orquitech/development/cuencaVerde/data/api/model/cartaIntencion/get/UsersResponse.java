package com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse extends CuencaVerdeResponse {

    @SerializedName("users")
    @Expose
    public List<User> users = null;
}
