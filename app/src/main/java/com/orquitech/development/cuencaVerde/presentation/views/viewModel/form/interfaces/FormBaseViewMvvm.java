package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces;

import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AppViewModel;

public interface FormBaseViewMvvm {

    interface View extends AppView {

        Task getTask();

        void onViewModelUpdated();

        void onBackPressed();

        void changeToView(int viewId);

        int getNextViewId();

        boolean canGoToNextScreen();

        void showErrorMessage(int messageResource);

        void onSendParcelableTriggered();
    }

    interface ViewModel extends AppViewModel {

        void onNextStep(android.view.View view);

        void onBackPressed(android.view.View view);

        void onSaveParcelable(android.view.View view);

        void refreshView(android.view.View view);

        boolean canGoToNextScreen(int currentView);

        Task getTask();

        void setTask(Task task);
    }
}
