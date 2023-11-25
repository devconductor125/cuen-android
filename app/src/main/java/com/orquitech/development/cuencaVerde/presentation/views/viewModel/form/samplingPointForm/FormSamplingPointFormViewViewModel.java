package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.samplingPointForm;

import android.text.TextUtils;
import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.base.BaseFormViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FormSamplingPointFormViewViewModel extends BaseFormViewModel implements FormSamplingPointFormViewMvvm.ViewModel {

    private FormSamplingPointFormViewMvvm.View view;
    private SamplingPointForm samplingPointForm;

    private CompositeDisposable siembraDetailFormDisposable;

    public FormSamplingPointFormViewViewModel(AppView view) {
        super(view);
        this.view = (FormSamplingPointFormViewMvvm.View) view;
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
        siembraDetailFormDisposable.add(prediosManager.getSamplingPointFormSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setSamplingPointFormFromService)
                .subscribe());

        prediosManager.getSamplingPointForm(getSamplingPointForm().getId());
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public SamplingPointForm getSamplingPointForm() {
        if (samplingPointForm == null) {
            samplingPointForm = new SamplingPointForm();
        }
        return samplingPointForm;
    }

    private void setSamplingPointFormFromService(SamplingPointForm samplingPointForm) {
        if (!TextUtils.isEmpty(samplingPointForm.getId())) {
            if (samplingPointForm.getFeature() == null) {
                samplingPointForm.setFeature(this.samplingPointForm.getFeature());
            }
            this.samplingPointForm = samplingPointForm;
            view.onViewModelUpdated();
        }
    }

    @Override
    public void setSamplingPointForm(SamplingPointForm samplingPointForm) {
        this.samplingPointForm = samplingPointForm;
    }

    @Override
    public void onSaveParcelable(View view) {
        if (samplingPointForm.isValid() && !TextUtils.isEmpty(samplingPointForm.getFeature().getProperties().getHash())) {
            prediosManager.saveSamplingPointForm(samplingPointForm, getTask().getId());
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
}
