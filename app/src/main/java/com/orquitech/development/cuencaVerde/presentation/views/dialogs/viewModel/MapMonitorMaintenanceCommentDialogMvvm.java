package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import android.content.Context;

import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.ListAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;

public interface MapMonitorMaintenanceCommentDialogMvvm {

    interface View extends AppView {

        void saveMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint);

        void onListAdapterReady(ListAdapter adapter);

        Context getContext();

        void onPhotographyRegistry(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint);
    }

    interface ViewModel {

        String getComment();

        void setComment(String comment);

        void saveMonitorAndMaintenanceComment(android.view.View view);

        void setMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint);

        void deleteMonitorAndMaintenanceCommentPoint();

        void clearSubscriptions();

        void onPhotographyRegistry(android.view.View view);
    }
}
