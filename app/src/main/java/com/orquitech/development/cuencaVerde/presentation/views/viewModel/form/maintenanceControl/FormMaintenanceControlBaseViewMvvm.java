package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl;

import com.orquitech.development.cuencaVerde.data.model.MaintenanceControl;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormMaintenanceControlBaseViewMvvm {

    interface View extends FormBaseViewMvvm.View {

        MaintenanceControl getMaintenanceControl();
    }
}
