package com.orquitech.development.cuencaVerde.logic;

import com.orquitech.development.cuencaVerde.data.model.Quota;

import io.reactivex.Observable;

public interface UserInfoManager {

    Observable<Quota> getQuotaObservable();

    void getQuota();
}
