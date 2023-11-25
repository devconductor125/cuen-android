package com.orquitech.development.cuencaVerde.data.api.cuencaVerdeApi;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;

public class ApiConfigDevelopment implements ApiConfig {

    private App app;

    public final static int UNAUTHORIZED = 401;
    public final static int CONNECT_EXCEPTION = 400;
    public final static int BAD_RESPONSE = 2000;

    public ApiConfigDevelopment(App app) {
        this.app = app;
    }

    @Override
    public String getCroquisApiUrl() {
        return app.getApplicationContext().getString(R.string.croquis_api_url);
    }

    @Override
    public String getCuencaVerdeDbApiUrl() {
        return app.getApplicationContext().getString(R.string.cuenca_verde_api_url);
    }

    @Override
    public String getCuencaVerdeImagesUrl() {
        return app.getApplicationContext().getString(R.string.cuenca_verde_images_url);
    }

    @Override
    public String getClientId() {
        return app.getApplicationContext().getString(R.string.cuenca_verde_api_client_id);
    }

    @Override
    public String getClientSecret() {
        return app.getApplicationContext().getString(R.string.cuenca_verde_api_client_secret);
    }

    @Override
    public String getGrantType() {
        return app.getApplicationContext().getString(R.string.cuenca_verde_api_client_grant_type);
    }
}
