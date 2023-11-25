package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.contractorForm;

import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.contractorForm.ContractorForm;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.base.BaseFormViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FormContractorFormViewViewModel extends BaseFormViewModel implements FormContractorFormViewMvvm.ViewModel {

    private FormContractorFormViewMvvm.View view;
    private ContractorForm contractorForm;
    private Task task;

    private CompositeDisposable contractorFormDisposable;

    public FormContractorFormViewViewModel(AppView view) {
        super(view);
        this.view = (FormContractorFormViewMvvm.View) view;
        contractorFormDisposable = new CompositeDisposable();
    }

    @Override
    public void onReadyForSubscriptions() {
        subscribeToObservables();
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        contractorFormDisposable.clear();
        this.view = null;
    }

    private void subscribeToObservables() {
        contractorFormDisposable.add(prediosManager.getContractorFormObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setContractorForm)
                .subscribe());
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
        prediosManager.getContractorForm(task);
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public ContractorForm getContractorForm() {
        if (contractorForm == null) {
            contractorForm = new ContractorForm();
        }
        return contractorForm;
    }

    private void setContractorForm(ContractorForm contractorForm) {
        contractorForm.setContractorSiembraId(task.getId());
        this.contractorForm = contractorForm;
        view.onViewModelUpdated();
    }

    @Override
    public void onBackPressed(View view) {
        this.view.onBackPressed();
    }

    @Override
    public void onSaveParcelable(View view) {
        if (contractorForm.isValid()) {
            this.view.onBackPressed();
        } else {
            sendMandatoryFieldsAlert();
            this.view.showErrorMessage(R.string.mandatory_fields_error);
        }
    }

    @Override
    public void showDatePicker(View view) {
        AppDate date = null;
        switch (view.getId()) {
            case R.id.start_date_button:
                date = contractorForm.getStartDate();
                break;
            case R.id.report_date_button:
                date = contractorForm.getReportDate();
                break;
            case R.id.completion_date_button:
                date = contractorForm.getCompletionDate();
                break;
            case R.id.sowing_date_button:
                date = contractorForm.getSowingDate();
                break;
            case R.id.maintenance_date_button:
                date = contractorForm.getMaintenanceDate();
                break;
        }
        this.view.showDatePicker(date, view.getId());
    }

    @Override
    public void setDate(int year, int month, int day, int viewId) {
        AppDate date = new AppDate();
        date.setYear(String.valueOf(year));
        date.setMonth(String.valueOf(month));
        date.setDay(String.valueOf(day));

        switch (viewId) {
            case R.id.start_date_button:
                contractorForm.setStartDate(date);
                break;
            case R.id.report_date_button:
                contractorForm.setReportDate(date);
                break;
            case R.id.completion_date_button:
                contractorForm.setCompletionDate(date);
                break;
            case R.id.sowing_date_button:
                contractorForm.setSowingDate(date);
                break;
            case R.id.maintenance_date_button:
                contractorForm.setMaintenanceDate(date);
                break;
        }
        view.onViewModelUpdated();
    }

    @Override
    public boolean canGoToNextScreen(int currentView) {
        return true;
    }
}
