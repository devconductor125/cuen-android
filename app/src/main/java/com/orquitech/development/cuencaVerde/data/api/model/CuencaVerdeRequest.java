package com.orquitech.development.cuencaVerde.data.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CuencaVerdeRequest {

    @SerializedName("client_id")
    @Expose
    public String clientId;
    @SerializedName("client_secret")
    @Expose
    public String clientSecret;
    @SerializedName("grant_type")
    @Expose
    public String grantType;

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
