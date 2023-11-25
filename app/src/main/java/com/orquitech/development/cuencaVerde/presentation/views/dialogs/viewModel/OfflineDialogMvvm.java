package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface OfflineDialogMvvm {

    interface View extends AppView {

        void onGotOfflineData();
    }

    interface ViewModel {

        boolean isGettingOfflineData();

        void getOfflineData();
    }
}
