package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.base;

import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public class BaseFormViewModel extends BaseViewModel implements FormBaseViewMvvm.ViewModel {

    protected Task task;
    private FormBaseViewMvvm.View view;

    public BaseFormViewModel(AppView view) {
        super(view);
        this.view = (FormBaseViewMvvm.View) view;
    }

    @Override
    public void onBackPressed(View view) {
        this.view.onBackPressed();
    }

    @Override
    public void refreshView(View view) {
        this.view.onViewModelUpdated();
    }

    @Override
    public void onNextStep(View view) {
        this.view.changeToView(this.view.getNextViewId());
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public void onSaveParcelable(View view) {
    }

    @Override
    public boolean canGoToNextScreen(int currentView) {
        return false;
    }

    @Override
    public void setTask(Task task) {
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
