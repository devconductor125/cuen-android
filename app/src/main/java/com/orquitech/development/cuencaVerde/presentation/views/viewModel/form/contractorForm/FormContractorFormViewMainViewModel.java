package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.contractorForm;

import com.orquitech.development.cuencaVerde.data.model.contractorForm.ContractorForm;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;

public class FormContractorFormViewMainViewModel extends BaseViewModel implements ContractorFormViewMvvm.ViewModel {

    private final ContractorFormViewMvvm.View view;

    public FormContractorFormViewMainViewModel(AppView appView) {
        super(appView);
        this.view = (ContractorFormViewMvvm.View) appView;
    }

    @Override
    public void onBarButtonClick(int position) {

    }

    @Override
    public void saveContractorForm(ContractorForm contractorForm, String taskId) {
        prediosManager.saveContractorForm(contractorForm, taskId);
    }

    @Override
    public void onReadyForSubscriptions() {
    }
}
