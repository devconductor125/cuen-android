package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.erosiveProcessForm;

import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormErosiveProcessFormBaseViewMvvm {

    interface View extends FormBaseViewMvvm.View {

        ErosiveProcessForm getErosiveProcessForm();
    }
}
