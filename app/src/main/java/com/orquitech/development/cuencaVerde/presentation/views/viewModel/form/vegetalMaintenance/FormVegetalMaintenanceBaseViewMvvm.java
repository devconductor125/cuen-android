package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.vegetalMaintenance;

import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance.VegetalMaintenance;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

import java.util.List;

public interface FormVegetalMaintenanceBaseViewMvvm {

    interface View extends FormBaseViewMvvm.View {

        VegetalMaintenance getVegetalMaintenance();

        void onMunicipalities(List<Municipality> municipalities);
    }
}
