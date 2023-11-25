package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl;

import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.MaintenanceControl;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.base.BaseFormViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FormMaintenanceControlViewViewModel extends BaseFormViewModel implements FormMaintenanceControlViewMvvm.ViewModel {

    private FormMaintenanceControlViewMvvm.View view;
    private MaintenanceControl maintenanceControl;

    private CompositeDisposable monitorAndMaintenanceDisposable;

    public FormMaintenanceControlViewViewModel(AppView view) {
        super(view);
        this.view = (FormMaintenanceControlViewMvvm.View) view;
        monitorAndMaintenanceDisposable = new CompositeDisposable();
    }

    @Override
    public void onReadyForSubscriptions() {
        subscribeToObservables();
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        monitorAndMaintenanceDisposable.clear();
        this.view = null;
    }

    private void subscribeToObservables() {
        monitorAndMaintenanceDisposable.add(prediosManager.getMaintenanceControlFormObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setMaintenanceControl)
                .subscribe());
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
        prediosManager.getMaintenanceControl((MonitorAndMaintenance) task);
    }

    @Override
    public MaintenanceControl getMaintenanceControl() {
        return maintenanceControl;
    }

    @Override
    public void setMaintenanceControl(MaintenanceControl maintenanceControl) {
        this.maintenanceControl = maintenanceControl;
        view.onViewModelUpdated();
    }

    @Override
    public void onBackPressed(View view) {
        this.view.onBackPressed();
    }

    @Override
    public void onNextStep(View view) {
        this.view.changeToView(this.view.getNextViewId());
    }

    @Override
    public void onSaveParcelable(View view) {
        if (!maintenanceControl.isValid()) {
            this.view.showErrorMessage(R.string.no_info_error);
            return;
        }
        prediosManager.saveMaintenanceControl(maintenanceControl, task.getId());
        this.view.onSendParcelableTriggered();
    }
}
