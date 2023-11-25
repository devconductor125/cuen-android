package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardSheetForm;

import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.AppFormView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.AppFormViewModel;

public interface StardSheetFormViewMvvm {

    interface View extends AppFormView {

        String getTaskObjectId();
    }

    interface ViewModel extends AppFormViewModel {

    }
}
