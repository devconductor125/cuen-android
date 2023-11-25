package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.erosiveProcessForm;

import android.text.TextUtils;
import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.base.BaseFormViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FormErosiveProcessFormViewViewModel extends BaseFormViewModel implements FormErosiveProcessFormViewMvvm.ViewModel {

    private FormErosiveProcessFormViewMvvm.View view;
    private ErosiveProcessForm erosiveProcessForm;

    private CompositeDisposable siembraDetailFormDisposable;

    public FormErosiveProcessFormViewViewModel(AppView view) {
        super(view);
        this.view = (FormErosiveProcessFormViewMvvm.View) view;
        siembraDetailFormDisposable = new CompositeDisposable();
    }

    @Override
    public void onReadyForSubscriptions() {
        subscribeToObservables();
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        siembraDetailFormDisposable.clear();
        this.view = null;
    }

    private void subscribeToObservables() {
        siembraDetailFormDisposable.add(prediosManager.getErosiveProcessFormSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setErosiveProcessFormFromService)
                .subscribe());

        prediosManager.getErosivePointForm(getErosiveProcessForm().getId());
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public ErosiveProcessForm getErosiveProcessForm() {
        if (erosiveProcessForm == null) {
            erosiveProcessForm = new ErosiveProcessForm();
        }
        return erosiveProcessForm;
    }

    private void setErosiveProcessFormFromService(ErosiveProcessForm erosiveProcessForm) {
        if (!TextUtils.isEmpty(erosiveProcessForm.getId())) {
            if (erosiveProcessForm.getFeature() == null) {
                erosiveProcessForm.setFeature(this.erosiveProcessForm.getFeature());
            }
            this.erosiveProcessForm = erosiveProcessForm;
            view.onViewModelUpdated();
        }
    }

    @Override
    public void setErosiveProcessForm(ErosiveProcessForm erosiveProcessForm) {
        this.erosiveProcessForm = erosiveProcessForm;
    }

    @Override
    public void onSaveParcelable(View view) {
        if (erosiveProcessForm.isValid() && !TextUtils.isEmpty(erosiveProcessForm.getFeature().getProperties().getHash())) {
            prediosManager.saveErosiveProcessForm(erosiveProcessForm, getTask().getId());
            this.view.onBackPressed();
        } else {
            sendMandatoryFieldsAlert();
            this.view.showErrorMessage(R.string.mandatory_fields_error);
        }
    }

    @Override
    public boolean canGoToNextScreen(int currentView) {
        return true;
    }

    @Override
    public void showDatePicker(View view) {
        AppDate date = erosiveProcessForm.getIdentificationDate();
        this.view.showDatePicker(date);
    }

    @Override
    public void setDate(int year, int month, int day) {
        AppDate date = new AppDate();
        date.setYear(String.valueOf(year));
        date.setMonth(String.valueOf(month));
        date.setDay(String.valueOf(day));
        erosiveProcessForm.setIdentificationDate(date);
        view.onViewModelUpdated();
    }
}
