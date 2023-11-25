package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface PredioPotencialTasksViewMvvm {

    interface View extends AppListView {

        void refreshTasks();

        void onBackClick();

        void changeToView(int viewId);

        void showMessage(int messageResourceId, int color);

        void onViewModelUpdated();
    }

    interface ViewModel extends BaseListViewViewModel {

        void setPredio(PredioPotencial predio);

        String getPredioName();

        void onBackClick(android.view.View view);

        void onSurvey(android.view.View view);

        void onCartaIntencion(android.view.View view);

        void onSendPredio(android.view.View view);

        void onSendDocuments(android.view.View view);

        boolean hasProject();

        boolean hasRemoteId();

        PredioPotencial getPredioPotencial();
    }
}
