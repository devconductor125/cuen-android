package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface AddPhotoItemViewMvvm {

    interface View extends AppView {

        void onClick();
    }

    interface ViewModel extends AppListItemViewModel {

        void onClick(android.view.View view);
    }
}
