package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface SettingsViewMvvm {

    interface View extends AppView {

        void onLogOut();

        void getOfflineData();

        void onOfflineDataReady();
    }

    interface ViewModel extends AppViewModel {

        void onOfflineSwitchChange(android.view.View view);

        void onLogOut(android.view.View view);
    }
}
