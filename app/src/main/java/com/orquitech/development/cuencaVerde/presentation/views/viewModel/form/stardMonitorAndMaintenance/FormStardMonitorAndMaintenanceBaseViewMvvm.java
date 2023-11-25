package com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.stardMonitorAndMaintenance;

import android.location.Location;

import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance.StardMonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.form.interfaces.FormBaseViewMvvm;

import java.util.List;

public interface FormStardMonitorAndMaintenanceBaseViewMvvm {

    interface View extends FormBaseViewMvvm.View {

        StardMonitorAndMaintenance getStardMonitorAndMaintenance();

        void setStardMonitorAndMaintenanceLocation(Location location);

        void onMunicipalities(List<Municipality> municipalities);
    }
}
