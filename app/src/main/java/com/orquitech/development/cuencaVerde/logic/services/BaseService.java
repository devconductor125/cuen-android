package com.orquitech.development.cuencaVerde.logic.services;

import android.app.Service;

import com.orquitech.development.cuencaVerde.CuencaVerdeApp;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.DaggerServicesComponent;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.ServicesComponent;

public abstract class BaseService extends Service {

    private ServicesComponent component;

    @Override
    public void onCreate() {
        component = DaggerServicesComponent.builder()
                .appComponent(CuencaVerdeApp.getApp().getAppComponent())
                .build();
    }

    protected ServicesComponent getComponent() {
        return component;
    }
}
