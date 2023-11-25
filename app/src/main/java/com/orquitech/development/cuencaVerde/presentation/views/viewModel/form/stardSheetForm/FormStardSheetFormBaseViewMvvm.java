package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardSheetForm;

import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.stardSheetForm.StardSheetForm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

import java.util.List;

public interface FormStardSheetFormBaseViewMvvm {

    interface View extends FormBaseViewMvvm.View {

        StardSheetForm getStardSheetForm();

        void onMunicipalities(List<Municipality> municipalities);
    }
}
