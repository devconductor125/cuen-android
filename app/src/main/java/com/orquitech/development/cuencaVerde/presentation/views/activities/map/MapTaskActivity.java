package com.orquitech.development.cuencaVerde.presentation.views.activities.map;

import androidx.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.Croquis;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.databinding.ActivityTaskMapBinding;
import com.orquitech.development.cuencaVerde.logic.AppAccionesManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.logic.services.UserLocationService;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.interfaces.MeasurementFlowViewGetter;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.MapParentView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.factories.AppViewModelsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapTaskViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;

public class MapTaskActivity extends MapActivity implements MapTaskViewMvvm.View, MapParentView, ListDialogListener, ToolBarListener {

    private ActivityTaskMapBinding binding;
    protected MapTaskViewMvvm.ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.savedInstanceState = savedInstanceState;
        }
    }

    @Override
    protected boolean initView() {
        if (binding == null) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_task_map);

            viewModel = initViewModel();
            binding.setViewModel(viewModel);
            handleBundleAndRestoreState(savedInstanceState);
            viewModel.onReadyForSubscriptions();

            binding.toolBar.setListener(this);
            binding.toolBar.onLoadingData();

            // Glide.with(this).load(R.drawable.cat).asGif().into(binding.cat);
            return true;
        } else {
            return false;
        }
    }

    protected MapTaskViewMvvm.ViewModel initViewModel() {
        return (MapTaskViewMvvm.ViewModel) viewModelFactory.getViewModel(this, AppViewModelsFactory.MAP_TASK_VIEW_MODEL);
    }

    protected void handleBundleAndRestoreState(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Task task = bundle.getParcelable(RxBus.PAYLOAD);
            viewModel.setTask(task);
        }

        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean(SEND_STATUS)) {
                onSendMapTriggered();
            }
            Task task = savedInstanceState.getParcelable(TASK);
            viewModel.setTask(task);
        }

        if (bundle == null && savedInstanceState == null) {
            Bundle bundleFromService = proceduresManager.getBundleFromUserLocationService();
            Task task = bundleFromService.getParcelable(RxBus.TASK);
            Boolean pendingMapSave = bundleFromService.getBoolean(RxBus.PENDING_MAP_SAVE);
            if (task != null) {
                viewModel.setTask(task);
                if (pendingMapSave) {
                    showSavedFeatureMessage(R.string.saved_feature);
                    proceduresManager.setBundleFromUserLocationService(null);
                }
            } else {
                finish();
            }
        }

        setToolbarTitle();
    }

    @Override
    public void refreshMeasurementMap() {
        mapViewHelper.clearMeasurementLayer();
        viewModel.refreshMapGeoJson();
    }

    protected void setToolbarTitle() {
        binding.toolBar.setTitle(viewModel.getTask().getTaskTypeTitle());
    }

    @Override
    public void onToolbarRightEndIconClick() {
        binding.toolBar.onLoadingData();
        mapViewHelper.clearManagementLayer();
        viewModel.onPrediosManagementLayer();
    }

    @Override
    protected void getMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.setRetainInstance(true);
        mapFragment.getMapAsync(this::setUpMap);
    }

    @Override
    protected void setUpMap(GoogleMap map) {
        super.setUpMap(map);
        if (viewModel.getChosenAction() != null) {
            if (viewModel.getChosenAction().getAccionType().equals(AppAccionesManager.ACCIONES)) {
                mapViewHelper.drawTrackedLocationLine(proceduresManager.getTrackedLocations(), viewModel.getChosenAction());
            } else if (viewModel.getChosenAction().getAccionType().equals(AppAccionesManager.AREAS)) {
                mapViewHelper.drawTrackedLocationPolygon(proceduresManager.getTrackedLocations(), viewModel.getChosenAction());
            }
        }
    }

    @Override
    protected MapViewMvvm.ViewModel getViewModel() {
        return viewModel;
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
    protected void onResume() {
        super.onResume();
        if (viewModel != null) {
            viewModel.onReadyForSubscriptions();
        }
    }

    @Override
    protected void onPause() {
        viewModel.clearSubscriptions();
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(TASK, viewModel.getTask());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected Action getChosenAction() {
        return viewModel.getChosenAction();
    }

    @Override
    public void initDataCapture() {
        super.initDataCapture();
        if (binding != null) {
            binding.customFab.collapse();
        }
    }

    @Override
    public void initPointDataCapture() {
        super.initPointDataCapture();
        if (binding != null) {
            binding.customFab.collapse();
        }
    }

    @Override
    public void switchMeasurementButtons(boolean forceOnMeasurementLayout) {
        MeasurementFlowViewGetter measurementFlowViewGetter = getMeasurementFlowViewGetter();
        if (forceOnMeasurementLayout) {
            enableOnMeasurementButtons(measurementFlowViewGetter);
        } else {
            disableOnMeasurementButtons(measurementFlowViewGetter);
        }
    }

    @Override
    protected void switchMeasurementButtons() {
        MeasurementFlowViewGetter measurementFlowViewGetter = getMeasurementFlowViewGetter();
        if (UserLocationService.isInstanceCreated()) {
            enableOnMeasurementButtons(measurementFlowViewGetter);
        } else {
            disableOnMeasurementButtons(measurementFlowViewGetter);
        }
    }

    private void enableOnMeasurementButtons(MeasurementFlowViewGetter measurementFlowViewGetter) {
        measurementFlowViewGetter.getOffMeasurementButtons().setVisibility(View.INVISIBLE);
        measurementFlowViewGetter.getOnMeasurementButtons().setVisibility(View.VISIBLE);
        measurementFlowViewGetter.getOnMeasurementButtonsPause().setVisibility(View.VISIBLE);
        measurementFlowViewGetter.getOnMeasurementFab().setVisibility(View.VISIBLE);
        if (getViewModel().isMeasurementPaused()) {
            measurementFlowViewGetter.getOnMeasurementButtons().setVisibility(View.INVISIBLE);
            measurementFlowViewGetter.getOnMeasurementButtonsPause().setVisibility(View.VISIBLE);
        } else {
            measurementFlowViewGetter.getOnMeasurementButtons().setVisibility(View.VISIBLE);
            measurementFlowViewGetter.getOnMeasurementButtonsPause().setVisibility(View.INVISIBLE);
        }
        if (binding != null) {
            // binding.sendMapButton.setVisibility(View.INVISIBLE);
        }
    }

    protected void disableOnMeasurementButtons(MeasurementFlowViewGetter measurementFlowViewGetter) {
        measurementFlowViewGetter.getOffMeasurementButtons().setVisibility(View.VISIBLE);
        measurementFlowViewGetter.getOnMeasurementButtons().setVisibility(View.INVISIBLE);
        measurementFlowViewGetter.getOnMeasurementButtonsPause().setVisibility(View.INVISIBLE);
        measurementFlowViewGetter.getOnMeasurementFab().setVisibility(View.INVISIBLE);
        if (binding != null) {
            // binding.sendMapButton.setVisibility(View.VISIBLE);
        }
    }

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
    public void hideOnMeasurementFab() {
        binding.customFabMeasurement.collapse();
    }

    @Override
    protected void onSaveTrack() {
        disableOnMeasurementButtons(getMeasurementFlowViewGetter());
        hideHighAccuracyError();
    }

    @Override
    public void showMissingFeaturesMessage() {
        showMessage(TextUtil.getString(this, R.string.missing_features_message), R.color.colorAccent, binding.coordinator);
    }

    @Override
    public void showEmptyFeatureMessage() {
        showMessage(TextUtil.getString(this, R.string.empty_feature_message), R.color.colorAccent, binding.coordinator);
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
    public void showFormButton() {
        binding.onFormButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeFab() {
        if (binding != null) {
            binding.customFab.collapse();
            binding.customFabHydro.collapse();
        }
    }

    @Override
    public void openContractorForm() {
        binding.customFab.collapse();
        Bundle bundle = new Bundle();
        bundle.putParcelable(RxBus.PAYLOAD, viewModel.getTask());
        changeToActivity(AppViewsFactory.STARD_SHEET_FORM_VIEW, bundle);
    }

    @Override
    public void drawCroquis(List<Croquis> croquis) {
        mapViewHelper.drawCroquis(croquis);
    }

    @Override
    public void drawExecutionBaseGeoJson(GeoJson geoJson) {
        mapViewHelper.addBaseGeoJsonToMap(geoJson);
    }

    @Override
    public void drawExecutionEditedGeoJson(GeoJson geoJson) {
        mapViewHelper.addEditedGeoJsonToMap(geoJson);
    }

    @Override
    protected void onLocationResult(Location location) {
        super.onLocationResult(location);
        if (viewModel != null && location != null) {
            viewModel.setLastLocation(location);
        }
    }

    @Override
    protected void showMapView() {
        if (binding.mapContainer.getVisibility() == View.INVISIBLE) {
            binding.mapContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPrediosManagementLayer(GeoJson prediosManagementLayer) {
        super.onPrediosManagementLayer(prediosManagementLayer);
        if (binding != null) {
            binding.toolBar.onFinishedLoadingData();
        }
    }

    @Override
    public int getViewType() {
        return AppViewsFactory.MAP_TASK_VIEW;
    }

    @Override
    public void hideMeasurementUi() {
        binding.measurementId.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showHydrologicalUi() {
        binding.hydrologicalUi.setVisibility(View.VISIBLE);
    }

    @Override
    protected void sendMeasurementMap() {
        viewModel.sendMeasurementMap();
    }

    @Override
    public void showHighAccuracyError(float error) {
        binding.accuracyMessage.setVisibility(View.VISIBLE);
        binding.accuracyMessage.setText(getResources().getString(R.string.accuracy_message, String.valueOf(error)));

        // binding.catContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideHighAccuracyError() {
        binding.accuracyMessage.setVisibility(View.INVISIBLE);
        // binding.catContainer.setVisibility(View.GONE);
    }
}
