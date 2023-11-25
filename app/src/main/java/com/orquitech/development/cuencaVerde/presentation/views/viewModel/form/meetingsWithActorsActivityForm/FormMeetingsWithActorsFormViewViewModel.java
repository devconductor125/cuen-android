package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.meetingsWithActorsActivityForm;

import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.meetingsWithActors.MeetingsWithActorsForm;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.base.BaseFormViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FormMeetingsWithActorsFormViewViewModel extends BaseFormViewModel implements FormMeetingsWithActorsFormViewMvvm.ViewModel {

    private FormMeetingsWithActorsFormViewMvvm.View view;
    private MeetingsWithActorsForm meetingsWithActorsForm;

    private CompositeDisposable meetingsWithActorsFormDisposable;

    public FormMeetingsWithActorsFormViewViewModel(AppView view) {
        super(view);
        this.view = (FormMeetingsWithActorsFormViewMvvm.View) view;
        meetingsWithActorsFormDisposable = new CompositeDisposable();
    }

    @Override
    public void onReadyForSubscriptions() {
        subscribeToObservables();
    }

    @Override
    public void clearSubscriptions() {
        super.clearSubscriptions();
        meetingsWithActorsFormDisposable.clear();
        this.view = null;
    }

    private void subscribeToObservables() {
        disposables.add(prediosManager.getMeetingsWithActorsFormObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setMeetingsWithActorsFormFromService)
                .subscribe());

        disposables.add(prediosManager.getMunicipalitiesObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(municipalities -> view.onMunicipalities(municipalities))
                .subscribe());

        prediosManager.getLocalMunicipalities();
    }

    @Override
    public MeetingsWithActorsForm getMeetingsWithActorsForm() {
        if (meetingsWithActorsForm == null) {
            meetingsWithActorsForm = new MeetingsWithActorsForm();
        }
        return meetingsWithActorsForm;
    }

    private void setMeetingsWithActorsFormFromService(MeetingsWithActorsForm meetingsWithActorsForm) {
        this.meetingsWithActorsForm = meetingsWithActorsForm;
        view.onViewModelUpdated();
    }

    @Override
    public void onSaveParcelable(View view) {
        getMeetingsWithActorsForm().setAsCanBeSaved(true);
        if (getMeetingsWithActorsForm().isValid()) {
            this.view.onBackPressed();
        } else {
            sendMandatoryFieldsAlert();
            this.view.showErrorMessage(R.string.mandatory_fields_error);
        }
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
        prediosManager.getMeetingsWithActorsForm(task.getId());
    }

    @Override
    public void showDatePicker(View view) {
        AppDate date = null;
        switch (view.getId()) {
            case R.id.event_date_button:
                date = meetingsWithActorsForm.getEventDate();
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
            case R.id.event_date_button:
                meetingsWithActorsForm.setEventDate(date);
                break;
        }
        view.onViewModelUpdated();
    }

    @Override
    public boolean canGoToNextScreen(int currentView) {
        return true;
    }
}
