package com.orquitech.development.cuencaVerde.presentation.views.viewModel;

import com.orquitech.development.cuencaVerde.data.model.Program;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface CheckboxItemViewMvvm {

    interface View extends AppView {

    }

    interface ViewModel extends AppListItemViewModel {

        Program getProgram();

        void setProgram(Program program);
    }
}
