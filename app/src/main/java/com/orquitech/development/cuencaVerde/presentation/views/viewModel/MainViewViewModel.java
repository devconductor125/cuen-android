package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.view.View;

import com.orquitech.development.cuencaVerde.data.model.Role;
import com.orquitech.development.cuencaVerde.logic.preferences.AppPreferencesObjectFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public class MainViewViewModel extends BaseViewModel implements MainViewMvvm.ViewModel {

    private final MainViewMvvm.View view;

    public MainViewViewModel(AppView appView) {
        super(appView);
        this.view = (MainViewMvvm.View) appView;
    }

    @Override
    public void onLeftNavigationButtonClicked(View view) {
        this.view.changeToView(AppViewsFactory.DASHBOARD_VIEW);
    }

    @Override
    public void onMidNavigationButtonClicked(View view) {
        this.view.changeToView(AppViewsFactory.TASKS_VIEW);
    }

    @Override
    public void onRightNavigationButtonClicked(View view) {
        this.view.changeToView(AppViewsFactory.PQRS_VIEW);
    }

    @Override
    public void setOneSignalId(String userId) {
        preferencesManager.set(AppPreferencesObjectFactory.ONE_SIGNAL_ID, userId);
    }

    @Override
    public void evaluateRole() {
        Role role = (Role) preferencesManager.get(AppPreferencesObjectFactory.ROLE, Role.class);
        if (role.cantSendPqrs()) {
            view.hidePqrsButton();
        }
    }
}
