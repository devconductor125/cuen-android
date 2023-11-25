//Georgi fixed
package com.orquitech.development.cuencaVerde.dependencyInjection.module;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.data.GsonManager;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.api.cuencaVerdeApi.ApiConfig;
import com.orquitech.development.cuencaVerde.data.api.cuencaVerdeApi.ApiConfigDevelopment;
import com.orquitech.development.cuencaVerde.data.db.AppPersistenceManager;
import com.orquitech.development.cuencaVerde.data.db.DbHelper;
import com.orquitech.development.cuencaVerde.data.db.PersistenceManager;
import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.data.managers.AppApiManager;
import com.orquitech.development.cuencaVerde.data.managers.AppConnectivityManager;
import com.orquitech.development.cuencaVerde.data.managers.AppServicesManager;
import com.orquitech.development.cuencaVerde.data.managers.ConnectivityStatusManager;
import com.orquitech.development.cuencaVerde.data.managers.HttpManager;
import com.orquitech.development.cuencaVerde.data.managers.RetroFitManager;
import com.orquitech.development.cuencaVerde.data.managers.ServicesManager;
import com.orquitech.development.cuencaVerde.logic.AccionesManager;
import com.orquitech.development.cuencaVerde.logic.AppAccionesManager;
import com.orquitech.development.cuencaVerde.logic.AppLocationManager;
import com.orquitech.development.cuencaVerde.logic.AppPrediosManager;
import com.orquitech.development.cuencaVerde.logic.AppProceduresManager;
import com.orquitech.development.cuencaVerde.logic.AppUserAccountManager;
import com.orquitech.development.cuencaVerde.logic.AppUserInfoManager;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.logic.LocationManager;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.logic.ProceduresManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.logic.UserAccountManager;
import com.orquitech.development.cuencaVerde.logic.UserInfoManager;
import com.orquitech.development.cuencaVerde.logic.preferences.AppPreferencesManager;
import com.orquitech.development.cuencaVerde.logic.preferences.AppPreferencesObjectFactory;
import com.orquitech.development.cuencaVerde.logic.preferences.GenericPreferencesFactory;
import com.orquitech.development.cuencaVerde.logic.preferences.PreferencesManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Singleton
    @Provides
    public LocationManager locationManager(App app, Bus bus) {
        return new AppLocationManager(app, bus);
    }

    @Singleton
    @Provides
    public Bus bus() {
        return new RxBus();
    }

    @Singleton
    @Provides
    public PrediosManager prediosManager(App app, Bus bus, PersistenceManager persistenceManager, ApiManager apiManager, ConnectivityStatusManager connectivityStatusManager, SerializationManager serializationManager) {
        return new AppPrediosManager(app, bus, persistenceManager, apiManager, connectivityStatusManager, serializationManager);
    }

    @Singleton
    @Provides
    public DbHelper dbHelper(App app, SerializationManager serializationManager) {
        return new DbHelper(app, serializationManager);
    }

    @Singleton
    @Provides
    public PersistenceManager persistenceManager(App app, DbHelper dbHelper) {
        return new AppPersistenceManager(app, dbHelper);
    }

    @Singleton
    @Provides
    public SerializationManager serializationManager() {
        return new GsonManager<>();
    }

    @Singleton
    @Provides
    public ServicesManager servicesManager(ApiManager apiManager, ApiConfig apiConfig, HttpManager httpManager) {
        return new AppServicesManager(apiManager, apiConfig, httpManager);
    }

    @Singleton
    @Provides
    public ApiConfig apiConfig(App app) {
        return new ApiConfigDevelopment(app);
    }

    @Singleton
    @Provides
    public HttpManager httpManager(App app, ApiConfig apiConfig) {
        return new RetroFitManager(app, apiConfig);
    }

    @Singleton
    @Provides
    public ApiManager apiManager(App app, HttpManager httpManager, ApiConfig apiConfig, PreferencesManager preferencesManager, SerializationManager serializationManager, ConnectivityStatusManager connectivityStatusManager, PersistenceManager persistenceManager) {
        return new AppApiManager(app, httpManager, apiConfig, preferencesManager, serializationManager, connectivityStatusManager, persistenceManager);
    }

    @Singleton
    @Provides
    public UserAccountManager accountManager(App app, ApiManager apiManager, PreferencesManager preferencesManager, Bus bus, ConnectivityStatusManager connectivityStatusManager) {
        return new AppUserAccountManager(app, apiManager, preferencesManager, bus, connectivityStatusManager);
    }

    @Singleton
    @Provides
    public ProceduresManager projectsManager(App app, ApiManager apiManager, Bus bus, PersistenceManager persistenceManager, ConnectivityStatusManager connectivityStatusManager, SerializationManager serializationManager, PrediosManager prediosManager) {
        return new AppProceduresManager(app, apiManager, bus, persistenceManager, connectivityStatusManager, serializationManager, prediosManager);
    }

    @Singleton
    @Provides
    public GenericPreferencesFactory preferencesFactory() {
        return new AppPreferencesObjectFactory();
    }

    @Singleton
    @Provides
    public PreferencesManager preferencesManager(App app, SerializationManager serializationManager, GenericPreferencesFactory preferencesFactory) {
        return new AppPreferencesManager(app, serializationManager, preferencesFactory);
    }

    @Singleton
    @Provides
    public ConnectivityStatusManager connectivityManager() {
        return new AppConnectivityManager();
    }

    @Singleton
    @Provides
    public UserInfoManager userInfoManager(ApiManager apiManager, Bus bus) {
        return new AppUserInfoManager(apiManager, bus);
    }

    @Singleton
    @Provides
    public AccionesManager accionesManager(Bus bus, ApiManager apiManager, ConnectivityStatusManager connectivityStatusManager, PersistenceManager persistenceManager) {
        return new AppAccionesManager(bus, apiManager, connectivityStatusManager, persistenceManager);
    }
}
