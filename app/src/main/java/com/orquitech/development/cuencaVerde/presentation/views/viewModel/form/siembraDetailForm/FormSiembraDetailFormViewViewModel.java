package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.siembraDetailForm;

import android.text.TextUtils;
import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.siembraDetailForm.SiembraDetailForm;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.base.BaseFormViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FormSiembraDetailFormViewViewModel extends BaseFormViewModel implements FormSiembraDetailFormViewMvvm.ViewModel {

    private FormSiembraDetailFormViewMvvm.View view;
    private SiembraDetailForm siembraDetailForm;

    private CompositeDisposable siembraDetailFormDisposable;

    public FormSiembraDetailFormViewViewModel(AppView view) {
        super(view);
        this.view = (FormSiembraDetailFormViewMvvm.View) view;
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
        siembraDetailFormDisposable.add(prediosManager.getSiembraDetailFormObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setSiembraDetailFormFromService)
                .subscribe());

        prediosManager.getSiembraDetailForm(getSiembraDetailForm().getId());
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public SiembraDetailForm getSiembraDetailForm() {
        if (siembraDetailForm == null) {
            siembraDetailForm = new SiembraDetailForm();
        }
        return siembraDetailForm;
    }

    private void setSiembraDetailFormFromService(SiembraDetailForm siembraDetailForm) {
        if (!TextUtils.isEmpty(siembraDetailForm.getId())) {
            if (siembraDetailForm.getFeature() == null) {
                siembraDetailForm.setFeature(this.siembraDetailForm.getFeature());
            }
            this.siembraDetailForm = siembraDetailForm;
            view.onViewModelUpdated();
        }
    }

    @Override
    public void setSiembraDetailForm(SiembraDetailForm siembraDetailForm) {
        this.siembraDetailForm = siembraDetailForm;
    }

    @Override
    public void onSaveParcelable(View view) {
        if (siembraDetailForm.isValid()) {
            prediosManager.saveSiembraDetailForm(siembraDetailForm, getTask().getId());
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
            case R.id.sowing_date_button:
                date = siembraDetailForm.getSowingDate();
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
            case R.id.sowing_date_button:
                siembraDetailForm.setSowingDate(date);
                break;
        }
        view.onViewModelUpdated();
    }

    @Override
    public boolean canGoToNextScreen(int currentView) {
        return true;
    }
}
