package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.os.Bundle;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.CuencaVerdeApp;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.managers.ConnectivityStatusManager;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.DaggerViewModelsComponent;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.ViewModelsComponent;
import com.orquitech.development.cuencaVerde.logic.AccionesManager;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.logic.LocationManager;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.logic.ProceduresManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.logic.UserAccountManager;
import com.orquitech.development.cuencaVerde.logic.preferences.PreferencesManager;
import com.orquitech.development.cuencaVerde.presentation.factories.ListAdapterFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel {

    private final AppView view;
    protected CompositeDisposable disposables;
    private ViewModelsComponent component;

    @Inject
    public ProceduresManager projectsManager;

    @Inject
    public PrediosManager prediosManager;

    @Inject
    public Bus bus;

    @Inject
    public ListAdapterFactory listAdapterFactory;

    @Inject
    public ProceduresManager proceduresManager;

    @Inject
    public App app;

    @Inject
    public AccionesManager accionesManager;

    @Inject
    public PreferencesManager preferencesManager;

    @Inject
    public ConnectivityStatusManager connectivityStatusManager;

    @Inject
    LocationManager locationManager;

    @Inject
    public UserAccountManager accountManager;

    @Inject
    public SerializationManager serializationManager;

    public BaseViewModel(AppView view) {
        this.view = view;

        component = DaggerViewModelsComponent.builder()
                .appComponent(CuencaVerdeApp.getApp().getAppComponent())
                .build();

        component.inject(this);

        disposables = new CompositeDisposable();
    }

    protected ViewModelsComponent getComponent() {
        return component;
    }

    public void clearSubscriptions() {
        disposables.clear();
    }

    protected void sendMandatoryFieldsAlert() {
        Bundle bundle = new Bundle();
        bundle.putInt(RxBus.CODE, RxBus.TEXT_FIELD_VALIDATION);
        bus.publish(bundle);
    }
}
