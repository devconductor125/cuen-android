package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface TasksViewMvvm {

    interface View extends AppListView {

        void refreshTasks();

        void onBackClick();
    }

    interface ViewModel extends BaseListViewViewModel {

        void setProject(Procedure project);

        String getProjectName();

        void getTasks(boolean refresh);

        void getTasks();

        void onBackClick(android.view.View view);

        boolean hasProject();
    }
}
