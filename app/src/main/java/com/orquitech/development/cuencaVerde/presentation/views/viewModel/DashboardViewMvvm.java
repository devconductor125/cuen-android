package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface DashboardViewMvvm {

    interface View extends AppListView {

        void onProjectClicked(Procedure project);
    }

    interface ViewModel extends BaseListViewViewModel {

        void getDashboard(boolean refresh);

        void getDashboard();
    }
}
