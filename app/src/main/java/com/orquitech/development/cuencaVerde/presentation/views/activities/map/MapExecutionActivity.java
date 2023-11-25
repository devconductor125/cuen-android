package com.orquitech.development.cuencaVerde.presentation.views.activities.map;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.utils.LocationUtils;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.DialogListenerAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.FeatureDetailsWithCommentFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.MapMonitorMaintenanceCommentFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapExecutionTaskViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapTaskViewMvvm;

import java.util.List;

public class MapExecutionActivity extends MapTaskActivity implements MapExecutionTaskViewMvvm.View {

    @Override
    public int getViewType() {
        return AppViewsFactory.MAP_EXECUTION_VIEW;
    }

    @Override
    protected MapTaskViewMvvm.ViewModel initViewModel() {
        return (MapTaskViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.MAP_EXECUTION_TASK_VIEW_MODEL);
    }

    @Override
    public void showFeatureDialog(Feature feature) {
        if (feature != null) {
            if (feature.isFromManagementLayer()) {
                showManagementLayerPropertyInfo(feature);
            } else {
                if (feature.getProperties() != null && !TextUtils.isEmpty(feature.getProperties().getHash())) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(RxBus.PAYLOAD, feature);
                    bundle.putParcelable(RxBus.TASK, viewModel.getTask());
                    FeatureDetailsWithCommentFragment fragment = (FeatureDetailsWithCommentFragment) dialogManager.getDialogFragment(DialogsFactory.FEATURE_DETAIL_WITH_COMMENT, bundle);
                    fragment.show(getSupportFragmentManager(), MAP_DIALOG);
                }
            }
        }
    }

    @Override
    public void addCommentMarker(String comment) {
        showAddMonitoringPointDialog(null);
    }

    @Override
    public void showAddMonitoringPointDialog(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
        if (mapViewHelper.rawLastLocation != null) {
            if (monitorAndMaintenanceCommentPoint == null) {
                monitorAndMaintenanceCommentPoint = new MonitorAndMaintenanceCommentPoint();
                monitorAndMaintenanceCommentPoint.setIsNew();
                monitorAndMaintenanceCommentPoint.setMonitorAndMaintenanceId(viewModel.getTask().getId());
                monitorAndMaintenanceCommentPoint.setLocation(LocationUtils.latLngToLocation(mapViewHelper.rawLastLocation));
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable(RxBus.PAYLOAD, monitorAndMaintenanceCommentPoint);
            MapMonitorMaintenanceCommentFragment fragment = (MapMonitorMaintenanceCommentFragment) dialogManager.getDialogFragment(DialogsFactory.MAP_MONITOR_MAINTENANCE_COMMENT_MARKER, bundle);
            fragment.setListener(new DialogListenerAdapter() {
                @Override
                public void onMidButtonClick(Parcelable data) {
                    if (data instanceof MonitorAndMaintenanceCommentPoint) {
                        mapViewHelper.addMonitorAndMaintenanceCommentPointMarker((MonitorAndMaintenanceCommentPoint) data);
                        ((MapExecutionTaskViewMvvm.ViewModel) viewModel).saveMonitorAndMaintenancePoint((MonitorAndMaintenanceCommentPoint) data);
                    }
                }
            });
            fragment.show(getSupportFragmentManager(), MAP_DIALOG);
        }
    }

    @Override
    public void addMonitorAndMaintenanceCommentPointMarker(List<MonitorAndMaintenanceCommentPoint> monitorAndMaintenanceCommentPoints) {
        for (MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint : monitorAndMaintenanceCommentPoints) {
            mapViewHelper.addMonitorAndMaintenanceCommentPointMarker(monitorAndMaintenanceCommentPoint);
        }
    }

    @Override
    public void onMarkerClick(Object tag) {
        if (tag instanceof MonitorAndMaintenanceCommentPoint) {
            showAddMonitoringPointDialog((MonitorAndMaintenanceCommentPoint) tag);
        }
    }
}
