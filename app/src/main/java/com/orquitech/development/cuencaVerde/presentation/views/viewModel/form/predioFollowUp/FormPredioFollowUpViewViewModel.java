package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.predioFollowUp;

import com.orquitech.development.cuencaVerde.data.model.PredioFollowUp;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.base.BaseFormViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FormPredioFollowUpViewViewModel extends BaseFormViewModel implements FormPredioFollowUpViewMvvm.ViewModel {

    private FormPredioFollowUpViewMvvm.View view;
    private PredioFollowUp predioFollowUpForm;

    private CompositeDisposable monitorAndMaintenanceDisposable;

    public FormPredioFollowUpViewViewModel(AppView view) {
        super(view);
        this.view = (FormPredioFollowUpViewMvvm.View) view;
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
        monitorAndMaintenanceDisposable.add(prediosManager.getPredioFollowUpObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setPredioFollowUpFormFromService)
                .subscribe());

        disposables.add(prediosManager.getMunicipalitiesObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(municipalities -> view.onMunicipalities(municipalities))
                .subscribe());

        prediosManager.getLocalMunicipalities();
    }

    @Override
    public PredioFollowUp getPredioFollowUpForm() {
        if (predioFollowUpForm == null) {
            predioFollowUpForm = new PredioFollowUp();
        }
        return predioFollowUpForm;
    }

    private void setPredioFollowUpFormFromService(PredioFollowUp predioFollowUp) {
        this.predioFollowUpForm = predioFollowUp;
        view.onViewModelUpdated();
    }

    @Override
    public void setTask(Task task) {
        this.task = task;
        prediosManager.getPredioFollowUp(task.getId());
    }
}
