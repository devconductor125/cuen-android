package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface TasksMainViewMvvm {

    interface View extends AppView {

        void changeToView(int viewId, boolean shouldReplace);
    }

    interface ViewModel {

    }
}
