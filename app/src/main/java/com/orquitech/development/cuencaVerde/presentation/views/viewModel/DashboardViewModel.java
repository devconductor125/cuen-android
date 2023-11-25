package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.logic.ProceduresManager;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import javax.inject.Inject;

public class DashboardViewModel extends BaseSingleListViewModel implements DashboardViewMvvm.ViewModel {

    @Inject
    App app;

    @Inject
    Bus bus;

    @Inject
    ProceduresManager projectsManager;

    @Inject
    PrediosManager prediosManager;

    public DashboardViewModel(AppView view) {
        super((AppListView) view);
        getComponent().inject(this);
    }

    @Override
    public void onReadyForSubscriptions() {
        super.onReadyForSubscriptions();
        subscribeToDashboardItemsListObservable(projectsManager.getDashboardObservable(), AppViewsFactory.PROJECTS_LIST_ITEM_VIEW);
        getDashboard();
    }

    @Override
    public void getDashboard(boolean refresh) {
        projectsManager.clearProjects();
        getDashboard();
    }

    @Override
    public void getDashboard() {
        projectsManager.getDashboard();
    }
}
