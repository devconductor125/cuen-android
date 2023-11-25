package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardSheetForm;

import com.orquitech.development.cuencaVerde.data.model.stardSheetForm.StardSheetForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormStardSheetFormViewMvvm {

    interface View extends FormStardSheetFormBaseViewMvvm.View {

        void setHydrologicalSourceCloseToTheHousehold(boolean isChecked);
    }

    interface ViewModel extends FormBaseViewMvvm.ViewModel {

        StardSheetForm getStardSheetForm();

        void switchHydrologicalSourceCloseToTheHousehold(android.view.View view, boolean isChecked);
    }
}
