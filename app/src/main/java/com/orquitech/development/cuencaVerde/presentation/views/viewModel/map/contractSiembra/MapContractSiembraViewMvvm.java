package com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.contractSiembra;

import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapTaskViewMvvm;

public interface MapContractSiembraViewMvvm {

    interface View extends MapTaskViewMvvm.View {

        void showMissingInfoMessage(int messageResource, int colorSecondary);

        void showInvalidContractSiembraMessage();

        void showCantSendMonitorMaintenanceMessage();

        void onSendMonitorMaintenanceSuccess();

        void onSendMonitorMaintenanceTriggered();

        void showMissingMaintenanceControlFormMessage();

        void openContractSiembraForm();

        void drawGeoJson(GeoJson geoJson);
    }

    interface ViewModel extends MapTaskViewMvvm.ViewModel {

        void sendContractSiembra(android.view.View view);

        void onSendContractSiembraCancelled();

        int getActivityType();
    }
}
