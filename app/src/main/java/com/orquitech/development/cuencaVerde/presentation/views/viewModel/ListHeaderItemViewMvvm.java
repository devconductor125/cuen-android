package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface ListHeaderItemViewMvvm {

    interface View extends AppView {

    }

    interface ViewModel extends AppListItemViewModel {

        String getText();
    }
}
