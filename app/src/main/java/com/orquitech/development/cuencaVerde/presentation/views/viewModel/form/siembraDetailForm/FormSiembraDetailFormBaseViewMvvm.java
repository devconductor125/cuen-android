package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.siembraDetailForm;

import com.orquitech.development.cuencaVerde.data.model.siembraDetailForm.SiembraDetailForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormSiembraDetailFormBaseViewMvvm {

    interface View extends FormBaseViewMvvm.View {

        SiembraDetailForm getSiembraDetailForm();
    }
}
