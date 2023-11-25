package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardSheetForm;

import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.stardSheetForm.StardSheetForm;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.base.BaseFormViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FormStardSheetFormViewViewModel extends BaseFormViewModel implements FormStardSheetFormViewMvvm.ViewModel {

    private FormStardSheetFormViewMvvm.View view;
    private StardSheetForm stardSheetForm;
    private Task task;

    private CompositeDisposable monitorAndMaintenanceDisposable;

    public FormStardSheetFormViewViewModel(AppView view) {
        super(view);
        this.view = (FormStardSheetFormViewMvvm.View) view;
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
        monitorAndMaintenanceDisposable.add(prediosManager.getStardSheetFormObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setStardSheetForm)
                .subscribe());

        disposables.add(prediosManager.getMunicipalitiesObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(municipalities -> view.onMunicipalities(municipalities))
                .subscribe());

        prediosManager.getLocalMunicipalities();
    }

    @Override
    public StardSheetForm getStardSheetForm() {
        if (stardSheetForm == null) {
            stardSheetForm = new StardSheetForm();
        }
        return stardSheetForm;
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
        prediosManager.getStardSheetForm(task);
    }

    private void setStardSheetForm(StardSheetForm stardSheetForm) {
        this.stardSheetForm = stardSheetForm;
        this.view.setHydrologicalSourceCloseToTheHousehold(this.stardSheetForm.getBasicInformation().getHydrologicalSource().isThereAHydrologicalSourceCloseToTheHousehold());
        view.onViewModelUpdated();
    }

    @Override
    public void onBackPressed(View view) {
        this.view.onBackPressed();
    }

    @Override
    public void switchHydrologicalSourceCloseToTheHousehold(View view, boolean isChecked) {
        this.view.setHydrologicalSourceCloseToTheHousehold(isChecked);
    }

    @Override
    public void onNextStep(View view) {
        if (stardSheetForm.isPart1Valid()) {
            this.view.changeToView(this.view.getNextViewId());
        } else {
            sendMandatoryFieldsAlert();
            this.view.showErrorMessage(R.string.mandatory_fields_error);
        }
    }

    @Override
    public void onSaveParcelable(View view) {
        if (!stardSheetForm.isValid()) {
            this.view.showErrorMessage(R.string.no_info_error);
            return;
        }
        prediosManager.saveStardSheetForm(stardSheetForm, task.getId());
        this.view.onSendParcelableTriggered();
    }

    @Override
    public boolean canGoToNextScreen(int currentView) {
        boolean canGoToNextScreen = false;
        switch (currentView) {
            case 1:
                canGoToNextScreen = stardSheetForm.isPart1Valid();
                break;
            case 2:
                canGoToNextScreen = stardSheetForm.isPart2Valid();
                break;
        }
        if (!canGoToNextScreen) {
            sendMandatoryFieldsAlert();
            this.view.showErrorMessage(R.string.mandatory_fields_error);
        }
        return canGoToNextScreen;
    }
}
