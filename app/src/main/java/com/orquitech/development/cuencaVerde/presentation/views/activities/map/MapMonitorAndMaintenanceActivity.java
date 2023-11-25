package com.orquitech.development.cuencaVerde.presentation.views.activities.map;

import android.app.Activity;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.model.geoJson.PointFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineStringFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon.MultiPolygonFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.polygon.PolygonFeature;
import com.orquitech.development.cuencaVerde.data.utils.LocationUtils;
import com.orquitech.development.cuencaVerde.databinding.ActivityMonitorAndMaintenanceBinding;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.interfaces.MeasurementFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.DialogListenerAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.MapMonitorMaintenanceCommentFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.MapParentView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapMonitorAndMaintenanceViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListener;

import java.util.List;

public class MapMonitorAndMaintenanceActivity extends MapTaskActivity implements MapMonitorAndMaintenanceViewMvvm.View, MapParentView, ListDialogListener, ToolBarListener {

    private ActivityMonitorAndMaintenanceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean initView() {
        if (binding == null) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_monitor_and_maintenance);
            viewModel = (MapMonitorAndMaintenanceViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.MONITOR_AND_MAINTENANCE_VIEW_MODEL);
            binding.setViewModel((MapMonitorAndMaintenanceViewMvvm.ViewModel) viewModel);
            handleBundleAndRestoreState(savedInstanceState);
            viewModel.onReadyForSubscriptions();

            binding.toolBar.setListener(this);
            binding.toolBar.onLoadingData();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setToolbarTitle() {
        binding.toolBar.setTitle(viewModel.getTask().getTaskTypeTitle());
    }

    @Override
    protected void onCameraMoveStarted() {
        if (binding.customFab.isExpanded()) {
            binding.customFab.collapse();
        }
        if (binding.customFabMeasurement.isExpanded()) {
            binding.customFabMeasurement.collapse();
        }
    }

    @Override
    public void addCommentMarker(String comment) {
        showAddMonitoringPointDialog(null);
    }

    @Override
    public void initDataCapture() {
        super.initDataCapture();
        binding.customFab.collapse();
    }

    @Override
    public void initPointDataCapture() {
        super.initPointDataCapture();
        binding.customFab.collapse();
    }

    @Override
    protected MeasurementFlowViewGetter getMeasurementFlowViewGetter() {
        return new MeasurementFlowViewGetter() {

            @Override
            public View getOffMeasurementButtons() {
                return binding.offMeasurementButtons;
            }

            @Override
            public View getOnMeasurementButtons() {
                return binding.onMeasurementButtons;
            }

            @Override
            public View getOnMeasurementButtonsPause() {
                return binding.onMeasurementButtonsPause;
            }

            @Override
            public View getOnMeasurementFab() {
                return binding.onMeasurementFab;
            }
        };
    }

    @Override
    protected void onMeasurementCantBePaused() {
        showMessage(TextUtil.getString(this, R.string.cant_pause), R.color.colorAccent, binding.coordinator);
    }

    @Override
    public void showSavedFeatureMessage(int messageResource) {
        showMessage(TextUtil.getString(this, messageResource), R.color.colorSecondary, binding.coordinator);
    }

    @Override
    protected void onSaveTrack() {
        disableOnMeasurementButtons(getMeasurementFlowViewGetter());
    }

    @Override
    public void showMissingFeaturesMessage() {
        showMessage(TextUtil.getString(this, R.string.missing_features_message), R.color.colorAccent, binding.coordinator);
    }

    @Override
    public void showCantSendMapMessage() {
        showMessage(TextUtil.getString(this, R.string.cant_send_task_message), R.color.colorAccent, binding.coordinator);
    }

    @Override
    public void showMissingFormMessage() {
        showMessage(TextUtil.getString(this, R.string.requires_stard_sheet_form), R.color.colorAccent, binding.coordinator);
    }

    @Override
    public void openContractorForm() {
        binding.customFab.collapse();
        Bundle bundle = new Bundle();
        bundle.putParcelable(RxBus.PAYLOAD, viewModel.getTask());
        changeToActivity(AppViewsFactory.STARD_SHEET_FORM_VIEW, bundle);
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
                        ((MapMonitorAndMaintenanceViewMvvm.ViewModel) viewModel).saveMonitorAndMaintenancePoint((MonitorAndMaintenanceCommentPoint) data);
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
    public void onToolbarLeftIconClick() {
        finish();
    }

    @Override
    public void onToolbarMidIconClick() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RxBus.PAYLOAD, viewModel.getTask());
        changeToActivity(((MapMonitorAndMaintenanceViewMvvm.ViewModel) viewModel).getActivityType(), bundle);
    }

    @Override
    public void onToolbarRightEndIconClick() {
        binding.toolBar.onLoadingData();
        mapViewHelper.clearManagementLayer();
        viewModel.onPrediosManagementLayer();
    }

    @Override
    public void onMarkerClick(Object tag) {
        if (tag instanceof MonitorAndMaintenanceCommentPoint) {
            showAddMonitoringPointDialog((MonitorAndMaintenanceCommentPoint) tag);
        }
    }

    @Override
    public void showMissingPointsMessage() {
        showMessage(TextUtil.getString(this, R.string.missing_points_message), R.color.colorAccent, binding.container);
    }

    @Override
    public void showCantSendMonitorMaintenanceMessage() {
        showMessage(TextUtil.getString(this, R.string.cant_send_monitor_maintenance_message), R.color.colorAccent, binding.container);
    }

    @Override
    public void onSendMonitorMaintenanceSuccess() {
        dialogManager.dismissDialog();
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onSendMonitorMaintenanceTriggered() {
        showProgressDialog(new DialogListenerAdapter() {
            @Override
            public void onDismiss() {
            }
        });
    }

    @Override
    public void onGotMonitorAndMaintenance(MonitorAndMaintenance monitorAndMaintenance) {
        Feature feature = (Feature) serializationManager.fromJson(monitorAndMaintenance.getJsonFeature(), Feature.class);
        if (feature != null) {
            switch (feature.getProperties().getFeatureType()) {
                case GeoJson.MULTI_LINE_STRING:
                    mapViewHelper.addMultiLineFeatureToMap((MultiLineStringFeature) monitorAndMaintenance.getFeature());
                    break;
                case GeoJson.POINT:
                    mapViewHelper.addPointFeatureToMap((PointFeature) monitorAndMaintenance.getFeature());
                    break;
                case GeoJson.POLYGON:
                    mapViewHelper.addPolygonFeatureToMap((PolygonFeature) monitorAndMaintenance.getFeature());
                    break;
                case GeoJson.MULTI_POLYGON:
                    mapViewHelper.addMultiPolygonFeatureToMap((MultiPolygonFeature) monitorAndMaintenance.getFeature());
                    break;
            }
        }
    }

    @Override
    public void showFormButton() {
        binding.onFormButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeFab() {
        if (binding != null) {
            binding.customFab.collapse();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AppViewsFactory.STARD_MONITOR_AND_MAINTENANCE_VIEW:
            case AppViewsFactory.VEGETAL_MAINTENANCE_VIEW:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        showMessage(TextUtil.getString(this, R.string.send_monitor_maintenance_success), R.color.colorSecondary);
                        break;
                }
                break;
        }
    }

    @Override
    public void showMissingInfoMessage(int messageResource, int colorSecondary) {
        this.showMessage(getString(messageResource), colorSecondary, R.id.coordinator);
    }

    @Override
    public void showMissingMaintenanceControlFormMessage() {
        this.showMessage(getString(R.string.requires_maintenance_control_form), R.color.colorAccent, R.id.coordinator);
    }

    @Override
    public void openMaintenanceControlForm() {
        binding.customFab.collapse();
        Bundle bundle = new Bundle();
        bundle.putParcelable(RxBus.PAYLOAD, viewModel.getTask());
        changeToActivity(AppViewsFactory.MAINTENANCE_CONTROL_FORM_VIEW, bundle);
    }

    @Override
    public void onPrediosManagementLayer(GeoJson prediosManagementLayer) {
        super.onPrediosManagementLayer(prediosManagementLayer);
        binding.toolBar.onFinishedLoadingData();
    }

    @Override
    protected void showMapView() {
        if (binding.mapContainer.getVisibility() == View.INVISIBLE) {
            binding.mapContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideOnMeasurementFab() {
        binding.customFabMeasurement.collapse();
    }
}
