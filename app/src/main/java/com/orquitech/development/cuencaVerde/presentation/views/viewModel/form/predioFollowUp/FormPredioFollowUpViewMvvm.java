package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.predioFollowUp;

import com.orquitech.development.cuencaVerde.data.model.PredioFollowUp;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormPredioFollowUpViewMvvm {

    interface View extends FormPredioFollowUpBaseViewMvvm.View {

    }

    interface ViewModel extends FormBaseViewMvvm.ViewModel {

        PredioFollowUp getPredioFollowUpForm();
    }
}
