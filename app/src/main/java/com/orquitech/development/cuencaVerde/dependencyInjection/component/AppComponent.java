//Georgi fixed
package com.orquitech.development.cuencaVerde.dependencyInjection.component;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.data.db.DbHelper;
import com.orquitech.development.cuencaVerde.data.db.PersistenceManager;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.api.cuencaVerdeApi.ApiConfig;
import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.data.managers.ConnectivityStatusManager;
import com.orquitech.development.cuencaVerde.data.managers.HttpManager;
import com.orquitech.development.cuencaVerde.data.managers.ServicesManager;
import com.orquitech.development.cuencaVerde.dependencyInjection.module.AppModule;
import com.orquitech.development.cuencaVerde.dependencyInjection.module.DataModule;
import com.orquitech.development.cuencaVerde.dependencyInjection.module.ViewsModule;
import com.orquitech.development.cuencaVerde.logic.AccionesManager;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.logic.LocationManager;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.logic.ProceduresManager;
import com.orquitech.development.cuencaVerde.logic.UserAccountManager;
import com.orquitech.development.cuencaVerde.logic.UserInfoManager;
import com.orquitech.development.cuencaVerde.logic.preferences.GenericPreferencesFactory;
import com.orquitech.development.cuencaVerde.logic.preferences.PreferencesManager;
import com.orquitech.development.cuencaVerde.presentation.factories.ListAdapterFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.ViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.ViewModelsFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class, ViewsModule.class})
public interface AppComponent {

    App app();

    ViewsFactory viewFactory();

    ViewModelsFactory viewModeFactory();

    ListAdapterFactory listAdapterFactory();

    Bus bus();

    LocationManager locationManager();

    PrediosManager prediosManager();

    DbHelper dbHelper();

    PersistenceManager persistenceManager();

    SerializationManager serializationManager();

    ServicesManager servicesManager();

    ApiConfig apiConfig();

    HttpManager httpManager();

    ApiManager apiManager();

    UserAccountManager userAccountManager();

    ProceduresManager proceduresManager();

    PreferencesManager preferencesManager();

    GenericPreferencesFactory preferencesFactory();

    ConnectivityStatusManager connectivityManager();

    UserInfoManager userInfoManager();

    AccionesManager accionesManager();
}
