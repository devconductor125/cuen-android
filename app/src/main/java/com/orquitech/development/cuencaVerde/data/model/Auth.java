package com.orquitech.development.cuencaVerde.data.model;

import android.text.TextUtils;

public class Auth {

    private String token;
    private String refreshToken;

    public void setToken(String token) {
        this.token = token;
    }

    public void setRefreshToken(String accessToken) {
        this.refreshToken = accessToken;
    }

    public boolean hasAccessToken() {
        return !TextUtils.isEmpty(token);
    }

    public String getAccessToken() {
        if (token == null) {
            token = "";
        }
        return token;
    }
}
