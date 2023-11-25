package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.maintenanceControl;

import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.AppFormView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.AppFormViewModel;

public interface MaintenanceControlViewMvvm {

    interface View extends AppFormView {

        String getMonitorAndMaintenanceId();
    }

    interface ViewModel extends AppFormViewModel {

    }
}
