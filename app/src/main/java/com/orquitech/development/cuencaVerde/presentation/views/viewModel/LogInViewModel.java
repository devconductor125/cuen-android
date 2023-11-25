package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.LogInCredentials;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class LogInViewModel extends BaseViewModel implements LogInViewMvvm.ViewModel {

    private LogInViewMvvm.View view;
    private CompositeDisposable logInDisposable;

    private LogInCredentials logInCredentials;

    public LogInViewModel(AppView appView) {
        super(appView);
        this.view = (LogInViewMvvm.View) appView;
        logInDisposable = new CompositeDisposable();

        this.logInCredentials = new LogInCredentials();
        this.logInCredentials.setUsername("sadmoncvmdll@gmail.com");
        this.logInCredentials.setPassword("123456");
    }

    @Override
    public void onReadyForSubscriptions() {
        subscribeToObservables();
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        logInDisposable.clear();
        this.view = null;
    }

    private void subscribeToObservables() {
        disposables.add(bus.getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> {
                    switch (bundle.getInt(RxBus.CODE)) {
                        case RxBus.SERVICE_ERROR:
                            view.onLogInError();
                            break;
                    }
                })
                .subscribe());
    }

    @Override
    public LogInCredentials getLogInCredentials() {
        return logInCredentials;
    }

    @Override
    public void setLogInCredentials(LogInCredentials logInCredentials) {
        this.logInCredentials = logInCredentials;
    }

    @Override
    public void logIn(android.view.View view) {
        if (isValidUser()) {
            logInDisposable.clear();
            logInDisposable.add(accountManager.getLogInObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(success -> {
                        if (this.view != null) {
                            this.view.onLogInSuccess();
                        }
                    })
                    .subscribe());

            accountManager.logIn(logInCredentials.getUsername(), logInCredentials.getPassword());
            this.view.onLogInTriggered();
        }
    }

    private boolean isValidUser() {
        if (TextUtils.isEmpty(logInCredentials.getUsername())) {
            view.onInvalidUsername();
            return false;
        }
        if (TextUtils.isEmpty(logInCredentials.getPassword())) {
            view.onInvalidPassword();
            return false;
        }
        return true;
    }

    @Override
    public void onLogInCancelled() {
        logInDisposable.clear();
    }
}
