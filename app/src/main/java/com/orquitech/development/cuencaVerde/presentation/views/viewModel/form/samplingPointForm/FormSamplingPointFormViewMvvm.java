package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.samplingPointForm;

import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormSamplingPointFormViewMvvm {

    interface View extends FormSamplingPointFormBaseViewMvvm.View {

    }

    interface ViewModel extends FormBaseViewMvvm.ViewModel {

        SamplingPointForm getSamplingPointForm();

        void setSamplingPointForm(SamplingPointForm samplingPointForm);
    }
}
