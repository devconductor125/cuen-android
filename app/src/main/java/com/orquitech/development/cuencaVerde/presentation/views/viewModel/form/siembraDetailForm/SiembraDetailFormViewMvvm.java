package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.siembraDetailForm;

import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.AppFormView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.AppFormViewModel;

public interface SiembraDetailFormViewMvvm {

    interface View extends AppFormView {

        String getHash();

        String getTaskObjectId();
    }

    interface ViewModel extends AppFormViewModel {

    }
}
