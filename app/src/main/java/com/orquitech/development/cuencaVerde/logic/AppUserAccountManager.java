package com.orquitech.development.cuencaVerde.logic;

import android.annotation.SuppressLint;
import android.util.Log;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.data.managers.ConnectivityStatusManager;
import com.orquitech.development.cuencaVerde.data.model.Auth;
import com.orquitech.development.cuencaVerde.data.model.Role;
import com.orquitech.development.cuencaVerde.data.utils.MapperUtils;
import com.orquitech.development.cuencaVerde.data.utils.ServicesUtils;
import com.orquitech.development.cuencaVerde.logic.preferences.AppPreferencesObjectFactory;
import com.orquitech.development.cuencaVerde.logic.preferences.PreferencesManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;

public class AppUserAccountManager extends BaseManager implements UserAccountManager {

    private Auth auth;
    private final App app;
    private final PublishSubject<Boolean> logInSubject = PublishSubject.create();
    private final ApiManager apiManager;
    private final PreferencesManager preferencesManager;
    private final ConnectivityStatusManager connectivityStatusManager;

    public AppUserAccountManager(App app, ApiManager apiManager, PreferencesManager preferencesManager, Bus bus, ConnectivityStatusManager connectivityStatusManager) {
        super(bus);
        this.app = app;
        this.apiManager = apiManager;
        this.preferencesManager = preferencesManager;
        this.connectivityStatusManager = connectivityStatusManager;
        getAuth();
    }

    @Override
    public Observable<Boolean> getLogInObservable() {
        return logInSubject;
    }

    @SuppressLint("CheckResult")
    @Override
    public void logIn(String username, String password) {
        apiManager.logInUser(username, password)
                .doOnError(error -> Log.d(getClass().getSimpleName(), "Error on login " + error.getMessage()))
                .onErrorReturn(ServicesUtils::getLogInResponseException)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                })
                .subscribe(response -> {
                    if (response.getCode() > 0) {
                        publishServiceError(response);
                    } else {
                        Log.d(getClass().getSimpleName(), "Success login!");
                        Auth user = MapperUtils.logInResponseToAuth(response);
                        preferencesManager.set(AppPreferencesObjectFactory.AUTH, user);
                        apiManager.initApi(response.accessToken);
                        app.getObjectsInBackground(connectivityStatusManager.isConnected());

                        apiManager.getRole()
                                .doOnError(error -> Log.d(getClass().getSimpleName(), "Error on getting role " + error.getMessage()))
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnComplete(() -> {
                                })
                                .subscribe(roles -> {
                                    if (roles.size() > 0) {
                                        Role role = new Role();
                                        role.setRoleValue(roles.get(0).role);
                                        preferencesManager.set(AppPreferencesObjectFactory.ROLE, role);
                                        logInSubject.onNext(true);
                                    }
                                });
                    }
                });
    }

    private Auth getAuth() {
        if (auth == null) {
            auth = (Auth) preferencesManager.get(AppPreferencesObjectFactory.AUTH, Auth.class);
        }
        return auth;
    }

    @Override
    public boolean hasAccessToken() {
        Auth auth = getAuth();
        return auth != null && auth.hasAccessToken();
    }

    @Override
    public void clearAccessToken() {
        auth = null;
    }
}
