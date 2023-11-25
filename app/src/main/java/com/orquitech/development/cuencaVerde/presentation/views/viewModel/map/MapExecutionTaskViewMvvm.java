package com.orquitech.development.cuencaVerde.presentation.views.viewModel.map;

import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;

import java.util.List;

public interface MapExecutionTaskViewMvvm {

    interface View extends MapTaskViewMvvm.View {

        void showAddMonitoringPointDialog(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint);

        void addMonitorAndMaintenanceCommentPointMarker(List<MonitorAndMaintenanceCommentPoint> resultList);
    }

    interface ViewModel extends MapTaskViewMvvm.ViewModel {

        void saveMonitorAndMaintenancePoint(MonitorAndMaintenanceCommentPoint data);
    }
}
