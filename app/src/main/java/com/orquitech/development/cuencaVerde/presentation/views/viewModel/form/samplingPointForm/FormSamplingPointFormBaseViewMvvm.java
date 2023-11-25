package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.samplingPointForm;

import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormSamplingPointFormBaseViewMvvm {

    interface View extends FormBaseViewMvvm.View {

        SamplingPointForm getSamplingPointForm();
    }
}
