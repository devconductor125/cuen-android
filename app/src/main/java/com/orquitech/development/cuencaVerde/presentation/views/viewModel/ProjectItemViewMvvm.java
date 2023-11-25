package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface ProjectItemViewMvvm {

    interface View extends AppView {

        void onClick(Procedure project);
    }

    interface ViewModel extends AppListItemViewModel {

        String getTitle();

        String getPendingTasks();

        String getDoneTasks();
    }
}
