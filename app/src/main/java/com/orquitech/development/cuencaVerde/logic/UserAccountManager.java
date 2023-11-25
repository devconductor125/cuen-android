package com.orquitech.development.cuencaVerde.logic;

import io.reactivex.Observable;

public interface UserAccountManager {

    Observable<Boolean> getLogInObservable();

    void logIn(String username, String password);

    boolean hasAccessToken();

    void clearAccessToken();
}
