package com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories;

import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AppListItemViewModel;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public interface ViewModelsFactory {

    BaseViewModel getViewModel(AppView view, int type);

    AppListItemViewModel getItemViewModel(AppView view, int type, Item item);
}
