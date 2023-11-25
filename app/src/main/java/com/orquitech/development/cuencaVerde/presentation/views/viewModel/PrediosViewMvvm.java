package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface PrediosViewMvvm {

    interface View extends AppListView {

        void refreshTasks();

        void onBackClick();
    }

    interface ViewModel extends BaseListViewViewModel {

        void getPredios();

        void onBackClick(android.view.View view);
    }
}
