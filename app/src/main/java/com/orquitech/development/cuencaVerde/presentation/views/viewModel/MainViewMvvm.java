package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface MainViewMvvm {

    interface View extends AppView {

        void changeToView(int viewType);

        void hidePqrsButton();
    }

    interface ViewModel {

        void onLeftNavigationButtonClicked(android.view.View view);

        void onMidNavigationButtonClicked(android.view.View view);

        void onRightNavigationButtonClicked(android.view.View view);

        void setOneSignalId(String userId);

        void evaluateRole();
    }
}
