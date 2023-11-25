package com.orquitech.development.cuencaVerde.data.managers;

import com.orquitech.development.cuencaVerde.data.api.cuencaVerdeApi.ApiConfig;

public class AppServicesManager implements ServicesManager {

    private ApiManager apiManager;
    private ApiConfig apiConfig;
    private final HttpManager httpManager;

    public AppServicesManager(ApiManager apiManager, ApiConfig apiConfig, HttpManager httpManager) {
        this.apiManager = apiManager;
        this.apiConfig = apiConfig;
        this.httpManager = httpManager;
    }
}
