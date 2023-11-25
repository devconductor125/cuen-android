package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl;

import com.orquitech.development.cuencaVerde.data.model.MaintenanceControl;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

public interface FormMaintenanceControlViewMvvm {

    interface View extends FormMaintenanceControlBaseViewMvvm.View {

    }

    interface ViewModel extends FormBaseViewMvvm.ViewModel {

        MaintenanceControl getMaintenanceControl();

        void setMaintenanceControl(MaintenanceControl maintenanceControl);
    }
}
