package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.data.model.BaseDialogListItem;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface ActionItemViewMvvm {

    interface View extends AppView {

        void onClick(BaseDialogListItem item);
    }

    interface ViewModel extends AppListItemViewModel {

        String getTitle();

        String getBulletColor();
    }
}
