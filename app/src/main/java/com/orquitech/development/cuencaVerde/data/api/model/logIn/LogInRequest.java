package com.orquitech.development.cuencaVerde.data.api.model.logIn;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogInRequest extends CuencaVerdeRequest {

    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("password")
    @Expose
    public String password;

    public LogInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
