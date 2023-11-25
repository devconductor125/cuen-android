package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.contractorForm;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.contractorForm.ContractorForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormContractorFormViewMvvm {

    interface View extends FormContractorFormBaseViewMvvm.View {

        void showDatePicker(AppDate date, int viewId);
    }

    interface ViewModel extends FormBaseViewMvvm.ViewModel {

        ContractorForm getContractorForm();

        void showDatePicker(android.view.View view);

        void setDate(int year, int month, int day, int viewId);
    }
}
