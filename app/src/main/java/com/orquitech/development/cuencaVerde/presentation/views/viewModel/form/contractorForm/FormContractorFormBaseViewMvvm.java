package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.contractorForm;

import com.orquitech.development.cuencaVerde.data.model.contractorForm.ContractorForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormContractorFormBaseViewMvvm {

    interface View extends FormBaseViewMvvm.View {

        ContractorForm getContractorForm();
    }
}
