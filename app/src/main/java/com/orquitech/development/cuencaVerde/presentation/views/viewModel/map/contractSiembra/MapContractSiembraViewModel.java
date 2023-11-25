package com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.contractSiembra;

import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.ContractSiembra;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapTaskViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MapContractSiembraViewModel extends MapTaskViewModel implements MapContractSiembraViewMvvm.ViewModel {

    private final MapContractSiembraViewMvvm.View view;
    private CompositeDisposable sendMonitorMaintenanceDisposable;

    public MapContractSiembraViewModel(AppView view) {
        super(view);
        this.view = (MapContractSiembraViewMvvm.View) view;
        getComponent().inject(this);
        sendMonitorMaintenanceDisposable = new CompositeDisposable();
    }

    @Override
    public void setTask(Task task) {
        if (task != null && this.task == null) {
            this.task = task;
            prediosManager.getContractSiembra(task);
        }
    }

    @Override
    protected void subscribeToObservables() {
        super.subscribeToObservables();

        disposables.add(prediosManager.getValidContractSiembraObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(isValid -> {
                    if (isValid) {
                        sendValidContractSiembra();
                    } else {
                        this.view.showMissingMaintenanceControlFormMessage();
                    }
                })
                .subscribe());

        disposables.add(prediosManager.getContractSiembraGeoJSonSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(view::drawGeoJson)
                .subscribe());

        subscribeToSentMonitorMaintenanceObservable();
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        sendMonitorMaintenanceDisposable.clear();
    }

    @Override
    public void onGeoJson(GeoJson geoJson) {
    }

    @Override
    public void onForm(View view) {
        this.view.openContractSiembraForm();
    }

    private void sendValidContractSiembra() {
        prediosManager.sendContractSiembra(((ContractSiembra) task));
        this.view.onSendMonitorMaintenanceTriggered();
    }

    private boolean featureRequiresMaintenanceControlForm(Feature feature) {
        boolean featureRequiresMaintenanceControlForm = false;
        switch (feature.getProperties().getAccionId()) {
            case "4":
                featureRequiresMaintenanceControlForm = true;
                break;
        }
        return featureRequiresMaintenanceControlForm;
    }

    private void subscribeToSentMonitorMaintenanceObservable() {
        sendMonitorMaintenanceDisposable.add(prediosManager.getSentContractSiembraObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(contractSiembraCommentPoint -> this.view.onSendMonitorMaintenanceSuccess())
                .subscribe());
    }

    @Override
    public void onSendContractSiembraCancelled() {
        sendMonitorMaintenanceDisposable.clear();
    }

    @Override
    public int getActivityType() {
        int returnValue = 0;
        /*switch (((ContractSiembra) task).getFormType()) {
            case ContractSiembra.STARD:
                returnValue = AppViewsFactory.STARD_MONITOR_AND_MAINTENANCE_VIEW;
                break;
            case ContractSiembra.VEGETAL_MAINTENANCE:
                returnValue = AppViewsFactory.VEGETAL_MAINTENANCE_VIEW;
                break;
            case ContractSiembra.PREDIO_FOLLOW_UP:
                returnValue = AppViewsFactory.PREDIO_FOLLOW_UP_VIEW;
                break;
        }*/
        return returnValue;
    }

    @Override
    public void sendContractSiembra(View view) {
        // this.prediosManager.validateContractSiembra(((ContractSiembra) task));
    }
}
