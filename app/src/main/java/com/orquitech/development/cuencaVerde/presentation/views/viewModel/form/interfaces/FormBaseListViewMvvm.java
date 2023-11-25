package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces;

import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface FormBaseListViewMvvm {

    interface View extends AppListView {

        Task getTask();

        void onViewModelUpdated();

        void onBackPressed();

        void changeToView(int viewId);

        int getNextViewId();

        boolean canGoToNextScreen();

        void showErrorMessage(int messageResource);

        void onSendParcelableTriggered();
    }
}
