package com.orquitech.development.cuencaVerde.presentation.views.activities.map;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.BaseItem;
import com.orquitech.development.cuencaVerde.data.model.Material;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.FeatureProperties;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Point;
import com.orquitech.development.cuencaVerde.data.model.geoJson.PointFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineStringFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.polygon.PolygonFeature;
import com.orquitech.development.cuencaVerde.data.utils.LocationUtils;
import com.orquitech.development.cuencaVerde.data.utils.SHAUtils;
import com.orquitech.development.cuencaVerde.logic.AppAccionesManager;
import com.orquitech.development.cuencaVerde.logic.PrediosManager;
import com.orquitech.development.cuencaVerde.logic.ProceduresManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.logic.services.UserLocationService;
import com.orquitech.development.cuencaVerde.presentation.views.MapViewHelper;
import com.orquitech.development.cuencaVerde.presentation.views.activities.BaseLocationActivity;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.DialogListenerAdapter;
import com.orquitech.development.cuencaVerde.presentation.views.adapters.interfaces.DialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ConfirmationDialogFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.FeatureDetailsFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.FeatureDetailsWithCommentFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ItemsListFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.ListDialogListener;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.MapMarkerCommentFragment;
import com.orquitech.development.cuencaVerde.presentation.views.dialogs.factories.DialogsFactory;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.MapParentView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.map.MapViewMvvm;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ToolBarListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public abstract class MapActivity extends BaseLocationActivity implements DialogListener, MapViewMvvm.View, MapParentView, ListDialogListener, ToolBarListener {

    protected static final String SEND_STATUS = "sendStatus";
    protected static final String TASK = "object";

    private boolean isSendingMap;
    protected MapViewHelper mapViewHelper;
    protected Bundle savedInstanceState;

    @Inject
    ProceduresManager proceduresManager;

    @Inject
    public PrediosManager prediosManager;

    @Inject
    SerializationManager serializationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        mapViewHelper = new MapViewHelper(this);

        if (savedInstanceState != null) {
            this.savedInstanceState = savedInstanceState;
            setUpView();
        }
    }

    @Override
    public void onEnterAnimationComplete() {
        setUpView();
    }

    private void setUpView() {
        boolean canInit = initView();
        if (canInit) {
            getMapFragment();
            switchMeasurementButtons();
        }
    }

    protected abstract boolean initView();

    protected abstract void handleBundleAndRestoreState(Bundle savedInstanceState);

    protected abstract void getMapFragment();

    protected void setUpMap(GoogleMap map) {
        mapViewHelper.setUpMap(map);
        if (getViewModel().getChosenAction() != null) {
            if (getViewModel().getChosenAction().getAccionType().equals(AppAccionesManager.ACCIONES)) {
                mapViewHelper.drawTrackedLocationLine(proceduresManager.getTrackedLocations(), getViewModel().getChosenAction());
            } else if (getViewModel().getChosenAction().getAccionType().equals(AppAccionesManager.AREAS)) {
                mapViewHelper.drawTrackedLocationPolygon(proceduresManager.getTrackedLocations(), getViewModel().getChosenAction());
            }
        }
        map.setOnCameraMoveStartedListener(reason -> {
            if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
                onCameraMoveStarted();
            }
        });

        LatLng lastKnownLocation = locationManager.getLastKnownLocation();
        if (lastKnownLocation.latitude != 0 && lastKnownLocation.longitude != 0) {
            mapViewHelper.moveCameraToLocation(lastKnownLocation);
            showMapView();
        }
    }

    protected void showMapView() {
    }

    protected abstract MapViewMvvm.ViewModel getViewModel();

    protected abstract void onCameraMoveStarted();

    @Override
    public void centerViewOnFeatures(List<LatLng> coordinates) {
        mapViewHelper.centerViewOnFeatures(coordinates);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Parcelable parcelable = getIntent().getParcelableExtra(RxBus.PAYLOAD);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapViewHelper.onResume();
        subscribeToObservables();
    }

    @Override
    protected void onPause() {
        mapViewHelper.onPause();
        dialogManager.dismissDialog();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapViewHelper.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(SEND_STATUS, isSendingMap);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onLocationResult(Location location) {
        mapViewHelper.onLocationResult(location);
    }

    @Override
    public void getLocation() {
        requestLocationPermission();
    }

    @Override
    public void setExistingTrackedLocations(List<List<Location>> trackedLocations) {
        if (trackedLocations != null) {
            mapViewHelper.setExistingTrackedLocations(trackedLocations, getChosenAction());
        }
    }

    protected abstract Action getChosenAction();

    @Override
    public void drawTrackedFeature(List<List<Location>> trackedLocations, boolean isNewSegment) {
        mapViewHelper.drawTrackedFeature(trackedLocations, getViewModel().getChosenAction(), isNewSegment);
        if (trackedLocations != null && trackedLocations.size() > 0) {
            List<Location> tempsLocations = (trackedLocations.get(trackedLocations.size() - 1));
            if (tempsLocations.size() > 0) {
                mapViewHelper.animateCameraToLocation(LocationUtils.locationToLatLng(tempsLocations.get(tempsLocations.size() - 1)));
            }
        }
    }

    @Override
    public void addMultiLineFeatureToMap(MultiLineStringFeature feature) {
        mapViewHelper.drawLine(feature);
        List<LatLng> coordinates = new ArrayList<>();
        for (List<LatLng> coordinate : feature.getGeometryList()) {
            coordinates.addAll(coordinate);
        }
        centerViewOnFeatures(coordinates);
        mapViewHelper.removeCircles();
    }

    @Override
    public void addPolygonFeatureToMap(PolygonFeature feature) {
        mapViewHelper.drawPolygonFromPolygonFeature(feature);
        List<LatLng> coordinates = new ArrayList<>();
        for (List<LatLng> coordinate : feature.getGeometryList()) {
            coordinates.addAll(coordinate);
        }
        centerViewOnFeatures(coordinates);
        mapViewHelper.removeCircles();
    }

    @Override
    public void centerViewOnNewPointFeature(PointFeature feature) {
        List<LatLng> coordinates = new ArrayList<>();
        LatLng coordinate = new LatLng(feature.getGeometry().coordinates.get(1), feature.getGeometry().coordinates.get(0));
        coordinates.add(coordinate);
        centerViewOnFeatures(coordinates);
    }

    @Override
    public void addMultiLineFeaturesToMap(List<MultiLineStringFeature> features) {
        for (MultiLineStringFeature feature : features) {
            mapViewHelper.drawLine(feature);
        }
        mapViewHelper.removeCircles();
    }

    @Override
    public void addPolygonFeaturesToMap(List<PolygonFeature> features) {
        for (PolygonFeature feature : features) {
            mapViewHelper.drawPolygonFromPolygonFeature(feature);
        }
        mapViewHelper.removeCircles();
    }

    @Override
    public void addPointFeaturesToMap(List<PointFeature> pointFeatures) {
        for (PointFeature pointFeature : pointFeatures) {
            List<Double> coordinates = new ArrayList<>();
            coordinates.add(pointFeature.getGeometry().coordinates.get(1));
            coordinates.add(pointFeature.getGeometry().coordinates.get(0));
            LatLng latLng = new LatLng(coordinates.get(0), coordinates.get(1));

            if (pointFeature.getProperties().isTree()) {
                mapViewHelper.drawTreeCircle(latLng, pointFeature);
            } else if (pointFeature.getProperties().isSamplingPoint()) {
                mapViewHelper.drawSamplingPointCircle(latLng, pointFeature);
            } else {
                mapViewHelper.drawCircle(latLng, pointFeature);
            }
        }
    }

    @Override
    public void onSendMapSuccess() {
        isSendingMap = false;
        dialogManager.dismissDialog();
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onSendMapTriggered() {
        isSendingMap = true;
    }

    @Override
    public void addCommentMarker(String comment) {
        MapMarkerCommentFragment fragment = (MapMarkerCommentFragment) dialogManager.getDialogFragment(DialogsFactory.MAP_COMMENT_MARKER, new Bundle());
        fragment.setListener(new DialogListenerAdapter() {
            @Override
            public void onMidButtonClick(Serializable data) {
                if (data instanceof String && !TextUtils.isEmpty(((String) data))) {
                    PointFeature pointFeature = new PointFeature();
                    FeatureProperties properties = new FeatureProperties();

                    properties.setAccionTitle((String) data);
                    properties.setColor(getString(R.string.white_color));
                    pointFeature.setProperties(properties);

                    addGoodPracticesPoint(pointFeature, getString(R.string.feature_comment_title, (String) data), false);
                }
            }
        });
        fragment.show(getSupportFragmentManager(), MAP_DIALOG);
    }

    @Override
    public void addTreeMarker(String comment) {
        PointFeature pointFeature = new PointFeature();
        FeatureProperties properties = new FeatureProperties();
        properties.setAsTree();
        properties.setAccionHash(SHAUtils.getSha256(serializationManager.toJson(mapViewHelper.rawLastLocation)));

        properties.setAccionTitle(FeatureProperties.TREE);
        pointFeature.setProperties(properties);

        addGoodPracticesPoint(pointFeature, comment, true);
    }

    @Override
    public void onHydrologicalMonitoringPoint() {
        if (mapViewHelper.rawLastLocation != null) {
            PointFeature pointFeature = new PointFeature();
            FeatureProperties properties = new FeatureProperties();
            properties.setSamplingPoint(true);
            properties.setAccionHash(SHAUtils.getSha256(serializationManager.toJson(mapViewHelper.rawLastLocation)));

            properties.setAccionTitle(FeatureProperties.TREE);
            pointFeature.setProperties(properties);

            Point point = new Point();
            List<Double> coordinates = new ArrayList<>();
            coordinates.add(mapViewHelper.rawLastLocation.longitude);
            coordinates.add(mapViewHelper.rawLastLocation.latitude);
            point.setCoordinates(coordinates);
            pointFeature.setGeometry(point);

            mapViewHelper.drawSamplingPointCircle(mapViewHelper.rawLastLocation, pointFeature);
            Action action = new Action();
            action.setTitle(getString(R.string.sampling_point));
            action.setColor(getString(R.string.white_color));
            action.setAccionType(AppAccionesManager.PUNTOS);
            action.setSamplingPoint(true);
            getViewModel().addGoodPracticeToGeoJson(action, mapViewHelper.rawLastLocation);
        }
    }

    private void addGoodPracticesPoint(PointFeature pointFeature, String title, boolean isTree) {
        Point point = new Point();
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(mapViewHelper.rawLastLocation.longitude);
        coordinates.add(mapViewHelper.rawLastLocation.latitude);
        point.setCoordinates(coordinates);
        pointFeature.setGeometry(point);

        if (isTree) {
            mapViewHelper.drawTreeCircle(mapViewHelper.rawLastLocation, pointFeature);
        } else {
            mapViewHelper.drawCircle(mapViewHelper.rawLastLocation, pointFeature);
        }

        Action action = new Action();
        action.setTitle(title);
        action.setColor(getString(R.string.white_color));
        if (TextUtils.isEmpty(action.getAccionType())) {
            action.setAccionType(AppAccionesManager.PUNTOS);
        }
        if (isTree) {
            action.setTree(true);
        }
        getViewModel().addGoodPracticeToGeoJson(action, mapViewHelper.rawLastLocation);
    }

    @Override
    public void initDataCapture() {
        if (locationManager.hasPermission()) {
            if (!UserLocationService.isInstanceCreated()) {
                showActionTypeSelectorDialog();
            }
        } else {
            requestLocationPermission();
        }
    }

    @Override
    public void initPointDataCapture() {
        if (locationManager.hasPermission()) {
            if (UserLocationService.isInstanceCreated()) {
                showPointActionTypeSelectorDialog();
            }
        } else {
            requestLocationPermission();
        }
    }

    @Override
    public void pauseMeasurement() {
        String chosenActionType = prediosManager.getChosenActionType();
        if (chosenActionType != null && !chosenActionType.equals(AppAccionesManager.PUNTOS)) {
            mapViewHelper.switchCircleColor();
            Bundle bundle = new Bundle();
            bundle.putInt(RxBus.CODE, RxBus.PAUSE_TRACK);
            if (chosenActionType.equals(AppAccionesManager.ACCIONES)) {
                proceduresManager.splitCurrentTrack();
            }
            bus.publish(bundle);
            switchMeasurementButtons();
        } else {
            onMeasurementCantBePaused();
        }
    }

    protected abstract void onMeasurementCantBePaused();

    @Override
    public void resumeMeasurement() {
        mapViewHelper.switchCircleColor();
        mapViewHelper.animateCameraToLocation(mapViewHelper.rawLastLocation);
        Bundle bundle = new Bundle();
        bundle.putInt(RxBus.CODE, RxBus.RESUME_TRACK);
        bus.publish(bundle);
        switchMeasurementButtons();
    }

    @Override
    public void animateCameraToLocation(Location location) {
        mapViewHelper.animateCameraToLocation(LocationUtils.locationToLatLng(location));
    }

    @Override
    public void showSaveTrackDialog() {
        if (mapViewHelper.hasTrackedPoints()) {
            ConfirmationDialogFragment fragment = (ConfirmationDialogFragment) dialogManager.getDialogFragment(DialogsFactory.CONFIRMATION, new Bundle());
            fragment.setTitle(getString(R.string.save_track_question));
            Bundle bundle = new Bundle();
            bundle.putString(RxBus.PAYLOAD, SAVE_MAP_DIALOG);
            fragment.setBundle(bundle);
            fragment.show(getSupportFragmentManager(), MAP_DIALOG);
        } else {
            showEmptyFeatureMessage();
        }
    }

    @Override
    public void showSendMeasurementMapDialog() {
        ConfirmationDialogFragment fragment = (ConfirmationDialogFragment) dialogManager.getDialogFragment(DialogsFactory.CONFIRMATION, new Bundle());
        fragment.setTitle(getString(R.string.send_map_question));
        Bundle bundle = new Bundle();
        bundle.putString(RxBus.PAYLOAD, SEND_MAP_DIALOG);
        fragment.setBundle(bundle);
        fragment.show(getSupportFragmentManager(), MAP_DIALOG);
    }

    @Override
    public void showSavedFeatureMessage(int messageResource) {

    }

    @Override
    public void hideOnMeasurementFab() {

    }

    @Override
    public void showMissingFeaturesMessage() {

    }

    @Override
    public void showEmptyFeatureMessage() {

    }

    @Override
    public void showCantSendMapMessage() {

    }

    @Override
    public void showMissingFormMessage() {

    }

    @Override
    public void showFormButton() {

    }

    @Override
    public void closeFab() {

    }

    private void saveTrack() {
        Intent stopIntent = new Intent(UserLocationService.STOP_TRACKING);
        sendBroadcast(stopIntent);
        mapViewHelper.clearTrackedLocations();
        prediosManager.setChosenActionType(null);
        onSaveTrack();
    }

    protected abstract void onSaveTrack();

    public void showActionTypeSelectorDialog() {
        ItemsListFragment itemsListFragment = (ItemsListFragment) dialogManager.getDialogFragment(DialogsFactory.ACTION_TYPE, new Bundle());
        itemsListFragment.setItemsType(DialogsFactory.ACTION_TYPE);
        itemsListFragment.show(getSupportFragmentManager(), MAP_DIALOG);
    }

    public void showPointActionTypeSelectorDialog() {
        ItemsListFragment itemsListFragment = (ItemsListFragment) dialogManager.getDialogFragment(DialogsFactory.POINT_ACTION_TYPE, new Bundle());
        itemsListFragment.setItemsType(DialogsFactory.POINT_ACTION_TYPE);
        itemsListFragment.show(getSupportFragmentManager(), MAP_DIALOG);
    }

    private void showAreaSelectorDialog() {
        ItemsListFragment itemsListFragment = (ItemsListFragment) dialogManager.getDialogFragment(DialogsFactory.MAP_AREAS, new Bundle());
        itemsListFragment.setItemsType(DialogsFactory.MAP_AREAS);
        itemsListFragment.show(getSupportFragmentManager(), MAP_DIALOG);
    }

    private void showActionSelectorDialog() {
        ItemsListFragment itemsListFragment = (ItemsListFragment) dialogManager.getDialogFragment(DialogsFactory.MAP_ACTIONS, new Bundle());
        itemsListFragment.setItemsType(DialogsFactory.MAP_ACTIONS);
        itemsListFragment.show(getSupportFragmentManager(), MAP_DIALOG);
    }

    public void showActionPointSelectorDialog() {
        ItemsListFragment itemsListFragment = (ItemsListFragment) dialogManager.getDialogFragment(DialogsFactory.MAP_ACTIONS_POINTS, new Bundle());
        itemsListFragment.setItemsType(DialogsFactory.MAP_ACTIONS_POINTS);
        itemsListFragment.show(getSupportFragmentManager(), MAP_DIALOG);
    }

    @Override
    public void onItemClick(Bundle bundle) {
        int itemsType = bundle.getInt(ItemsListFragment.ITEMS_TYPE);
        BaseItem item = bundle.getParcelable(RxBus.PAYLOAD);
        if (item instanceof Action) {
            Action action = (Action) item;
            switch (itemsType) {
                case DialogsFactory.ACTION_TYPE:
                case DialogsFactory.POINT_ACTION_TYPE:
                    switch (action.getAccionType()) {
                        case AppAccionesManager.AREAS:
                            prediosManager.setChosenActionType(AppAccionesManager.AREAS);
                            showAreaSelectorDialog();
                            break;
                        case AppAccionesManager.ACCIONES:
                            prediosManager.setChosenActionType(AppAccionesManager.ACCIONES);
                            showActionSelectorDialog();
                            break;
                        case AppAccionesManager.PUNTOS:
                            if (!action.isOnMeasurement()) {
                                prediosManager.setChosenActionType(AppAccionesManager.PUNTOS);
                            }
                            showActionPointSelectorDialog();
                            break;
                    }
                    break;
                case DialogsFactory.MAP_AREAS:
                    getViewModel().getMaterials(action);
                    break;
                case DialogsFactory.MAP_ACTIONS:
                    getViewModel().getMaterials(action);
                    break;
                case DialogsFactory.MAP_ACTIONS_POINTS:
                    getViewModel().getMaterials(action);
                    break;
            }
        } else if (item instanceof Material) {
            Material material = (Material) item;
            switch (itemsType) {
                case DialogsFactory.MAP_MATERIALS:
                    Action action = bundle.getParcelable(ItemsListFragment.PARENT_PAYLOAD);
                    onMaterialSelected(action, material);
                    break;
            }
        }
    }

    public void onMaterialSelected(Action action, Material material) {
        if (action != null) {
            if (action.getAccionType().equals(AppAccionesManager.PUNTOS)) {
                PointFeature pointFeature = new PointFeature();
                FeatureProperties featureProperties = new FeatureProperties();

                featureProperties.setAccionId(action.getId());
                featureProperties.setAccionTitle(action.getTitle());
                featureProperties.setMaterialId(material.getId());
                featureProperties.setMaterialTitle(material.getTitle());
                featureProperties.setColor(action.getColor());

                pointFeature.setProperties(featureProperties);

                action.setMaterial(material);

                mapViewHelper.drawCircle(mapViewHelper.rawLastLocation, pointFeature);
                getViewModel().addGoodPracticeToGeoJson(action, mapViewHelper.rawLastLocation);
            } else {
                getViewModel().setChosenAction(action);
                action.setMaterial(material);
                startTrackRecording(action);
                switchMeasurementButtons(true);
            }
        }
    }

    protected abstract void switchMeasurementButtons(boolean forceOnMeasurementLayout);

    protected abstract void switchMeasurementButtons();

    protected void startTrackRecording(Action action) {
        mapViewHelper.clearTrackedLocations();
        Bundle serviceBundle = new Bundle();
        serviceBundle.putParcelable(UserLocationService.ACTION, action);
        serviceBundle.putParcelable(RxBus.PAYLOAD, getViewModel().getTask());
        locationManager.startLocationTracking(serviceBundle);
    }

    @Override
    public void onToolbarLeftIconClick() {
        finish();
    }

    @Override
    public void onToolbarMidIconClick() {
    }

    @Override
    public void onToolbarRightEndIconClick() {
    }

    @Override
    public boolean canAnimateMapCamera() {
        return true;
    }

    @Override
    public boolean canMoveMapCamera() {
        return true;
    }

    @Override
    public void onMarkerClick(Object tag) {
    }

    @Override
    public void showFeatureDialog(Feature feature) {
        if (feature != null) {
            if (feature.isFromManagementLayer()) {
                showManagementLayerPropertyInfo(feature);
            } else if (feature.isTree()) {
                if (feature.getProperties() != null && !TextUtils.isEmpty(feature.getProperties().getHash())) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(RxBus.PAYLOAD, feature);
                    bundle.putParcelable(RxBus.TASK, getViewModel().getTask());
                    FeatureDetailsWithCommentFragment fragment = (FeatureDetailsWithCommentFragment) dialogManager.getDialogFragment(DialogsFactory.FEATURE_DETAIL_WITH_COMMENT, bundle);
                    fragment.show(getSupportFragmentManager(), MAP_DIALOG);
                }
            } else {
                if (feature.getProperties() != null && !TextUtils.isEmpty(feature.getProperties().getHash())) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(RxBus.PAYLOAD, feature);
                    FeatureDetailsFragment fragment = (FeatureDetailsFragment) dialogManager.getDialogFragment(DialogsFactory.FEATURE_DETAIL, bundle);
                    fragment.show(getSupportFragmentManager(), MAP_DIALOG);
                }
            }
        }
    }

    @Override
    public void onButtonOne() {

    }

    @Override
    public void onButtonTwo(Bundle bundle) {
        if (bundle != null) {
            switch (Objects.requireNonNull(bundle.getString(RxBus.PAYLOAD))) {
                case SAVE_MAP_DIALOG:
                    saveTrack();
                    break;
                case SEND_MAP_DIALOG:
                    sendMeasurementMap();
                    break;
            }
        }
    }

    abstract protected void sendMeasurementMap();

    @Override
    public void onDismiss() {

    }

    @Override
    public void onMidButtonClick(Parcelable data) {

    }

    @Override
    public void onMidButtonClick(Serializable data) {

    }

    @Override
    public void onPrediosManagementLayer(GeoJson prediosManagementLayer) {
        mapViewHelper.addPrediosManagementLayer(prediosManagementLayer, serializationManager);
    }
}
