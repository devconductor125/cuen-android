package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.erosiveProcessForm;

import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.AppFormView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.AppFormViewModel;

public interface ErosiveProcessFormViewMvvm {

    interface View extends AppFormView {

        String getHash();

        String getTaskObjectId();
    }

    interface ViewModel extends AppFormViewModel {

    }
}
