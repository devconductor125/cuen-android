package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.vegetalMaintenance;

import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance.VegetalMaintenance;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FormVegetalMaintenanceViewViewModel extends BaseViewModel implements FormVegetalMaintenanceViewMvvm.ViewModel {

    private FormVegetalMaintenanceViewMvvm.View view;
    private MonitorAndMaintenance monitorAndMaintenance;
    private VegetalMaintenance vegetalMaintenance;

    private CompositeDisposable monitorAndMaintenanceDisposable;

    public FormVegetalMaintenanceViewViewModel(AppView view) {
        super(view);
        this.view = (FormVegetalMaintenanceViewMvvm.View) view;
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
        disposables.add(prediosManager.getMunicipalitiesObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(municipalities -> view.onMunicipalities(municipalities))
                .subscribe());

        prediosManager.getLocalMunicipalities();
    }

    @Override
    public void setTask(Task task) {
        this.monitorAndMaintenance = (MonitorAndMaintenance) task;
        getVegetalMaintenanceFromManager();
        prediosManager.getVegetalMaintenance(monitorAndMaintenance.getId());
    }

    @Override
    public VegetalMaintenance getVegetalMaintenance() {
        return vegetalMaintenance;
    }

    @Override
    public void setVegetalMaintenance(VegetalMaintenance vegetalMaintenance) {
        vegetalMaintenance.setMonitorAndMaintenanceId(monitorAndMaintenance.getId());
        this.vegetalMaintenance = vegetalMaintenance;
        view.onViewModelUpdated();
    }

    @Override
    public void showExecutionStartDatePicker(View view) {
        AppDate date = vegetalMaintenance.getExecutionStartDate();
        this.view.showExecutionStartDatePicker(date);
    }

    @Override
    public void setExecutionStartDate(int year, int month, int day) {
        AppDate date = new AppDate();
        date.setYear(String.valueOf(year));
        date.setMonth(String.valueOf(month));
        date.setDay(String.valueOf(day));
        vegetalMaintenance.setExecutionStartDate(date);
        view.onViewModelUpdated();
    }

    @Override
    public void showExecutionEndDatePicker(View view) {
        AppDate date = vegetalMaintenance.getExecutionEndDate();
        this.view.showExecutionEndDatePicker(date);
    }

    @Override
    public void setExecutionEndDate(int year, int month, int day) {
        AppDate date = new AppDate();
        date.setYear(String.valueOf(year));
        date.setMonth(String.valueOf(month));
        date.setDay(String.valueOf(day));
        vegetalMaintenance.setExecutionEndDate(date);
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
    public void onSendParcelable(View view) {
        if (!vegetalMaintenance.hasValidName()) {
            this.view.showErrorMessage(R.string.missing_name);
            return;
        }
        if (!vegetalMaintenance.hasValidPredioName()) {
            this.view.showErrorMessage(R.string.missing_predio_name);
            return;
        }
        prediosManager.sendVegetalMaintenance(vegetalMaintenance);
        this.view.onSendParcelableTriggered();
    }

    @Override
    public void onSendParcelableCancelled() {
        monitorAndMaintenanceDisposable.clear();
    }

    public void getVegetalMaintenanceFromManager() {
        monitorAndMaintenanceDisposable.add(prediosManager.getVegetalMaintenanceObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setVegetalMaintenance)
                .subscribe());
        prediosManager.getVegetalMaintenanceByMonitorAndMaintenanceId(monitorAndMaintenance.getId());
    }
}
