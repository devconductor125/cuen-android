package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardMonitorAndMaintenance;

import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance.StardMonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.base.BaseFormViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FormStardMonitorAndMaintenanceViewViewModel extends BaseFormViewModel implements FormStardMonitorAndMaintenanceViewMvvm.ViewModel {

    private FormStardMonitorAndMaintenanceViewMvvm.View view;
    private StardMonitorAndMaintenance stardMonitorAndMaintenance;

    private CompositeDisposable monitorAndMaintenanceDisposable;

    public FormStardMonitorAndMaintenanceViewViewModel(AppView view) {
        super(view);
        this.view = (FormStardMonitorAndMaintenanceViewMvvm.View) view;
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
        monitorAndMaintenanceDisposable.add(prediosManager.getStardMonitorAndMaintenanceObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setStardMonitorAndMaintenance)
                .subscribe());

        disposables.add(prediosManager.getMunicipalitiesObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(municipalities -> view.onMunicipalities(municipalities))
                .subscribe());

        prediosManager.getLocalMunicipalities();
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
        prediosManager.getStardMonitorAndMaintenanceByMonitorAndMaintenanceId(task.getId());
    }

    @Override
    public StardMonitorAndMaintenance getStardMonitorAndMaintenance() {
        return stardMonitorAndMaintenance;
    }

    @Override
    public void setStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance) {
        if (this.stardMonitorAndMaintenance == null) {
            stardMonitorAndMaintenance.setMonitorAndMaintenanceId(task.getId());
            this.stardMonitorAndMaintenance = stardMonitorAndMaintenance;
        }
        view.onViewModelUpdated();
    }

    @Override
    public void showDatePicker(View view) {
        AppDate date = stardMonitorAndMaintenance.getRequestDate();
        this.view.showDatePicker(date);
    }

    @Override
    public void setDate(int year, int month, int day) {
        AppDate date = new AppDate();
        date.setYear(String.valueOf(year));
        date.setMonth(String.valueOf(month));
        date.setDay(String.valueOf(day));
        stardMonitorAndMaintenance.setRequestDate(date);
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
        if (!stardMonitorAndMaintenance.hasValidName()) {
            this.view.showErrorMessage(R.string.missing_name);
            return;
        }
        prediosManager.sendStardMonitorAndMaintenance(stardMonitorAndMaintenance);
        this.view.onSendParcelableTriggered();
    }
}
