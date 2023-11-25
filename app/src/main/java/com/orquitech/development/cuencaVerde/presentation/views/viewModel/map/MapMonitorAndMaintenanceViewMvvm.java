package com.orquitech.development.cuencaVerde.presentation.views.viewModel.map;

import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;

import java.util.List;

public interface MapMonitorAndMaintenanceViewMvvm {

    interface View extends MapTaskViewMvvm.View {

        void showMissingInfoMessage(int messageResource, int colorSecondary);

        void showAddMonitoringPointDialog(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint);

        void addMonitorAndMaintenanceCommentPointMarker(List<MonitorAndMaintenanceCommentPoint> monitorAndMaintenanceCommentPoints);

        void showMissingPointsMessage();

        void showCantSendMonitorMaintenanceMessage();

        void onSendMonitorMaintenanceSuccess();

        void onSendMonitorMaintenanceTriggered();

        void onGotMonitorAndMaintenance(MonitorAndMaintenance monitorAndMaintenance);

        void showMissingMaintenanceControlFormMessage();

        void openMaintenanceControlForm();
    }

    interface ViewModel extends MapTaskViewMvvm.ViewModel {

        void addMonitoringPoint(android.view.View view);

        void saveMonitorAndMaintenancePoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint);

        void onSendMonitorAndMaintenanceCancelled();

        int getActivityType();
    }
}
