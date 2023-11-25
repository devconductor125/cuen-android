package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.view.View;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SettingsViewModel extends BaseViewModel implements SettingsViewMvvm.ViewModel {

    private SettingsViewMvvm.View view;

    public SettingsViewModel(AppView appView) {
        super(appView);
        this.view = (SettingsViewMvvm.View) appView;
    }

    @Override
    public void onReadyForSubscriptions() {

    }

    @Override
    public void onOfflineSwitchChange(android.view.View view) {
        if (connectivityStatusManager.isConnected()) {
            this.view.getOfflineData();
            disposables.add(accionesManager.getOfflineData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(onAccionesManagerOfflineData -> proceduresManager.getOfflineData())
                    .subscribe(result -> {
                        prediosManager.getProvinces();
                        prediosManager.getPrediosManagementLayer();
                        prediosManager.getPredios();
                        proceduresManager.getProgramsFromService();
                        this.view.onOfflineDataReady();
                    }));
            prediosManager.getPrediosManagementLayer();
        }
    }

    @Override
    public void onLogOut(View view) {
        this.view.onLogOut();
    }
}
