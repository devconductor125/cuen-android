package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.survey;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.base.BaseFormViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FormSurveyViewViewModel extends BaseFormViewModel implements FormSurveyViewMvvm.ViewModel {

    private FormSurveyViewMvvm.View view;
    private PredioPotencial predioPotencial;
    private Survey survey;

    private CompositeDisposable surveyDisposable;

    public FormSurveyViewViewModel(AppView view) {
        super(view);
        this.view = (FormSurveyViewMvvm.View) view;
        surveyDisposable = new CompositeDisposable();
    }

    @Override
    public void setPredioPotencial(PredioPotencial predioPotencial) {
        this.predioPotencial = predioPotencial;
        projectsManager.getSurveyByPredioPotencialId(predioPotencial.getId());
    }

    @Override
    public void onReadyForSubscriptions() {
        subscribeToObservables();
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        surveyDisposable.clear();
        this.view = null;
    }

    private void subscribeToObservables() {
        surveyDisposable.add(projectsManager.getSavedSurveyObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setSurvey)
                .subscribe());

        disposables.add(prediosManager.getMunicipalitiesObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(municipalities -> view.onMunicipalities(municipalities))
                .subscribe());

        prediosManager.getLocalMunicipalities();
    }

    @Override
    public Survey getSurvey() {
        return survey;
    }

    @Override
    public String getPredioPotencialId() {
        return this.view.getPredioPotencialId(predioPotencial.getRemoteId());
    }

    private void setSurvey(Survey survey) {
        if (survey.getPredioPotencialId() < 0) {
            survey.setAsNew();
        }
        survey.setPropertyName(predioPotencial.getName());
        this.survey = survey;
        this.survey.setPredioPotencialId(Integer.parseInt(this.predioPotencial.getId() != null ? this.predioPotencial.getId() : "0"));
        if (survey.isNew()) {
            view.initLocationRequest();
        }
        view.onViewModelUpdated();
    }

    @Override
    public void showDatePicker(View view) {
        AppDate date = survey.getPropertyVisitDate();
        this.view.showDatePicker(date);
    }

    @Override
    public void setDate(int year, int month, int day) {
        AppDate date = new AppDate();
        date.setYear(String.valueOf(year));
        date.setMonth(String.valueOf(month));
        date.setDay(String.valueOf(day));
        survey.setPropertyVisitDate(date);
        view.onViewModelUpdated();
    }

    @Override
    public void showCorrelationDialog(View view) {
        this.view.showCorrelationDialog();
    }

    @Override
    public void setPropertyCorrelation(String propertyCorrelation) {
        this.survey.setPropertyCorrelation(propertyCorrelation);
        view.onViewModelUpdated();
    }

    @Override
    public void onBackPressed(android.view.View view) {
        this.view.onBackPressed();
    }

    @Override
    public void onNextStep(android.view.View view) {
        this.view.changeToView(this.view.getNextViewId());
    }

    @Override
    public void onSaveParcelable(View view) {
        String value = ((Spinner)view.getRootView().findViewById(R.id.email_spinner)).getSelectedItem().toString();;
        survey.setReceiverEmail(value);

        Log.d("this is survey", survey.getMunicipality());

        if (!survey.hasValidName()) {
            this.view.showErrorMessage(R.string.predio_potencial_missing_name);
            return;
        }
        if (!survey.hasValidCoordinates()) {
            this.view.showErrorMessage(R.string.predio_potencial_missing_location);
            return;
        }
        if (connectivityStatusManager.isConnected()) {
            if (predioPotencial.getRemoteId() > 0) {
                prediosManager.sendSurvey(predioPotencial, survey);
                this.view.onSendParcelableTriggered();
            } else {
                this.view.showErrorMessage(R.string.missing_remote_id);
            }
        } else {
            this.view.showErrorMessage(R.string.cant_send_task_message);
        }
    }

    @Override
    public int customTitleResource() {
        return this.survey.isNew() ? R.string.new_form : R.string.form;
    }

    @Override
    public boolean canGoToNextScreen(int currentView) {
        boolean canGoToNextScreen;
        switch (currentView) {
            case 1:
                canGoToNextScreen = survey.isPart1Valid();
                break;
            case 2:
                canGoToNextScreen = survey.isPart2Valid();
                break;
            default:
                canGoToNextScreen = true;
        }
        if (!canGoToNextScreen) {
            sendMandatoryFieldsAlert();
            this.view.showErrorMessage(R.string.mandatory_fields_error);
        }
        return canGoToNextScreen;
    }
}
