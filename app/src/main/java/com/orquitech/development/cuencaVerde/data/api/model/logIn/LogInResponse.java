package com.orquitech.development.cuencaVerde.data.api.model.logIn;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogInResponse extends CuencaVerdeResponse {

    @SerializedName("token_type")
    @Expose
    public String tokenType;
    @SerializedName("expires_in")
    @Expose
    public String expiresIn;
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;
}
