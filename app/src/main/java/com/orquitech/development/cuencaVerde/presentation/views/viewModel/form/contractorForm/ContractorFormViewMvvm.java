package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.contractorForm;

import com.orquitech.development.cuencaVerde.data.model.contractorForm.ContractorForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.AppFormView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AppViewModel;

public interface ContractorFormViewMvvm {

    interface View extends AppFormView {

    }

    interface ViewModel extends AppViewModel {

        void onBarButtonClick(int position);

        void saveContractorForm(ContractorForm contractorForm, String taskId);
    }
}
