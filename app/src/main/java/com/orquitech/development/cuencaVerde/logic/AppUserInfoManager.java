package com.orquitech.development.cuencaVerde.logic;

import android.annotation.SuppressLint;
import android.util.Log;

import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.data.model.Quota;
import com.orquitech.development.cuencaVerde.data.utils.MapperUtils;
import com.orquitech.development.cuencaVerde.data.utils.ServicesUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;

public class AppUserInfoManager extends BaseManager implements UserInfoManager {

    private final PublishSubject<Quota> quotaInSubject = PublishSubject.create();
    private final ApiManager apiManager;

    public AppUserInfoManager(ApiManager apiManager, Bus bus) {
        super(bus);
        this.apiManager = apiManager;
    }

    @Override
    public Observable<Quota> getQuotaObservable() {
        return quotaInSubject;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getQuota() {
        apiManager.getQuota()
                .doOnError(error -> Log.d(getClass().getSimpleName(), "Error on get quota " + error.getMessage()))
                .onErrorReturn(ServicesUtils::getQuotaResponseException)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                })
                .subscribe(response -> {
                    if (response.getCode() > 0) {
                        publishServiceError(response);
                    } else {
                        Log.d(getClass().getSimpleName(), "Success get quota!");
                        Quota quota = MapperUtils.quotaResponseToQuota(response);
                        quotaInSubject.onNext(quota);
                    }
                });
    }
}
