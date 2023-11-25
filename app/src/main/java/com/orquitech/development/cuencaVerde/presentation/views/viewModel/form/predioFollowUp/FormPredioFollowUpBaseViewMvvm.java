package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.predioFollowUp;

import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.PredioFollowUp;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

import java.util.List;

public interface FormPredioFollowUpBaseViewMvvm {

    interface View extends FormBaseViewMvvm.View {

        PredioFollowUp getPredioFollowUp();

        void onMunicipalities(List<Municipality> municipalities);
    }
}
