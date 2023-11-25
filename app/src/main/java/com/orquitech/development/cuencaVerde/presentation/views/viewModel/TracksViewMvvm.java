package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface TracksViewMvvm {

    interface View extends AppView {

    }

    interface ViewModel extends AppViewModel {

        void getPredios();
    }
}
