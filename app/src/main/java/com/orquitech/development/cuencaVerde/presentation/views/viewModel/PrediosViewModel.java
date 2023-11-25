package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import android.view.View;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

import javax.inject.Inject;

public class PrediosViewModel extends BaseSingleListViewModel implements PrediosViewMvvm.ViewModel {

    @Inject
    App app;

    @Inject
    PrediosManager prediosManager;

    @Inject
    ApiManager apiManager;

    public PrediosViewModel(AppView view) {
        super((AppListView) view);
        getComponent().inject(this);
    }

    @Override
    public void onReadyForSubscriptions() {
        super.onReadyForSubscriptions();
        subscribeToObservables();
        getPredios();
    }

    private void subscribeToObservables() {
        subscribeToItemsListObservable(prediosManager.getPrediosObservable(), AppViewsFactory.PREDIOS_LIST_ITEM_VIEW);
    }

    @Override
    public void onBackClick(View view) {
        ((PrediosViewMvvm.View) this.view).onBackClick();
    }

    @Override
    public void getPredios() {
        prediosManager.getPredios();
    }
}
