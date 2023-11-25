package com.orquitech.development.cuencaVerde.presentation.views.viewModel.map;

import android.location.Location;
import android.os.Parcelable;
import android.view.View;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.ErosiveProcess;
import com.orquitech.development.cuencaVerde.data.model.Material;
import com.orquitech.development.cuencaVerde.data.model.MonitoreoRecursoHidricoProcess;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.model.geoJson.PointFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineStringFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.polygon.PolygonFeature;
import com.orquitech.development.cuencaVerde.logic.AppAccionesManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.logic.services.UserLocationService;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.BaseViewModel;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MapTaskViewModel extends BaseViewModel implements MapTaskViewMvvm.ViewModel {

    protected final MapTaskViewMvvm.View view;
    protected Task task;
    protected GeoJson geoJson;
    private String comment = "";
    private Location lastLocation;
    private Action choosenAction;

    public MapTaskViewModel(AppView appView) {
        super(appView);
        this.view = (MapTaskViewMvvm.View) appView;
    }

    @Override
    public void onReadyForSubscriptions() {
        subscribeToObservables();
    }

    @Override
    public void setTask(Task task) {
        if (task != null && this.task == null) {
            this.task = task;
            if (UserLocationService.isInstanceCreated()) {
                view.setExistingTrackedLocations(projectsManager.getTrackedLocations());
            } else {
                this.proceduresManager.setTrackedLocations(new ArrayList<>());
            }
            if (task.getTaskType().equals(BaseFragment.EXECUTION)) {
                prediosManager.getExecutionTaskBaseGeoJson(this.task);
                prediosManager.getExecutionTaskEditedGeoJson(this.task);
            }
            prediosManager.getGeoJson(this.task);
            getCroquis();

            if (task.getTaskType().equals(BaseFragment.EXECUTION) && task.getTaskOpenSubTypeId() == 13) {
                this.view.hideMeasurementUi();
            }
            if (task.getTaskType().equals(BaseFragment.HYDROLOGICAL_PROCESS) || task.getTaskType().equals(BaseFragment.EROSIVE_PROCESS)) {
                this.view.hideMeasurementUi();
                this.view.showHydrologicalUi();
            }
        }
    }

    private void getCroquis() {
        if (task instanceof ErosiveProcess || task instanceof MonitoreoRecursoHidricoProcess) {
            prediosManager.getCroquis(this.task.getId());
        } else {
            prediosManager.getCroquis(this.task.getPotentialId());
        }
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public void checkForExistingFeatures() {
        if (task != null) {
            if (geoJson != null) {

                List<MultiLineStringFeature> multiLineStringFeatures = geoJson.getMultiLineStringFeatures();
                view.addMultiLineFeaturesToMap(multiLineStringFeatures);

                List<PolygonFeature> polygonFeatures = geoJson.getPolygonFeatures();
                view.addPolygonFeaturesToMap(polygonFeatures);

                List<PointFeature> pointFeatures = geoJson.getPointFeatures();
                view.addPointFeaturesToMap(pointFeatures);

                List<LatLng> coordinates = new ArrayList<>();

                for (PolygonFeature feature : polygonFeatures) {
                    List<List<LatLng>> coordinatesList = feature.getGeometryList();
                    for (List<LatLng> coordinate : coordinatesList) {
                        coordinates.addAll(coordinate);
                    }
                }

                for (MultiLineStringFeature feature : multiLineStringFeatures) {
                    List<List<LatLng>> coordinatesList = feature.getGeometryList();
                    for (List<LatLng> coordinate : coordinatesList) {
                        coordinates.addAll(coordinate);
                    }
                }

                for (PointFeature pointFeature : pointFeatures) {
                    List<Double> coordinate = new ArrayList<>();
                    coordinate.add(pointFeature.getGeometry().coordinates.get(1));
                    coordinate.add(pointFeature.getGeometry().coordinates.get(0));
                    LatLng latLng = new LatLng(coordinate.get(0), coordinate.get(1));
                    coordinates.add(latLng);
                }

                if (!UserLocationService.isInstanceCreated()) {
                    view.centerViewOnFeatures(coordinates);
                }
            }
        }
    }

    @Override
    public void addCommentMarker(View view) {
        this.view.addCommentMarker(this.comment);
        this.view.closeFab();
    }

    @Override
    public void onNewTree(View view) {
        this.view.addTreeMarker(this.comment);
        this.view.closeFab();
    }

    @Override
    public void onNewHydrologicalMonitoringPoint(View view) {
        this.view.onHydrologicalMonitoringPoint();
        this.view.closeFab();
    }

    @Override
    public void initDataCapture(View view) {
        this.view.initDataCapture();
    }

    @Override
    public void initPointDataCapture(View view) {
        this.view.initPointDataCapture();
    }

    @Override
    public void sendMeasurementMap() {
        if (geoJson.hasStard()) {
            prediosManager.validateStardSheetForm(task);
        } else {
            sendValidMap();
        }
    }

    @Override
    public void onSendMapCancelled() {
    }

    @Override
    public void addGoodPracticeToGeoJson(Action action, LatLng location) {
        proceduresManager.addFeatureToGeoJson(task.getId(), action, location);
    }

    protected void subscribeToObservables() {
        disposables.add(prediosManager.getPrediosManagementLayerSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(geoJson -> {
                    if (this.view != null && geoJson.features != null) {
                        this.view.onPrediosManagementLayer(geoJson);
                    }
                })
                .subscribe());

        disposables.add(prediosManager.getValidStardSheetFormObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(hasValidStardSheet -> {
                    if (hasValidStardSheet) {
                        sendValidMap();
                    } else {
                        this.view.showMissingFormMessage();
                    }
                })
                .subscribe());

        disposables.add(prediosManager.getGeoJsonObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::onGeoJson)
                .subscribe());

        disposables.add(prediosManager.getCroquisObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this.view::drawCroquis)
                .subscribe());

        if (task.getTaskType().equals(BaseFragment.EXECUTION)) {
            disposables.add(prediosManager.getExecutionBaseGeoJsonObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(this.view::drawExecutionBaseGeoJson)
                    .subscribe());

            disposables.add(prediosManager.getExecutionBaseGeoJsonObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(this.view::drawExecutionEditedGeoJson)
                    .subscribe());
        }

        disposables.add(bus.getBehaviorObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> {
                    switch (bundle.getInt(RxBus.CODE)) {
                        case RxBus.EMPTY_MAP_FEATURE:
                            this.view.showEmptyFeatureMessage();
                            break;
                        case RxBus.LOCATION_CHANGED:
                            task = bundle.getParcelable(RxBus.PAYLOAD);
                            if (task != null) {
                                List<List<Location>> trackedLocations = proceduresManager.getTrackedLocations();
                                if (trackedLocations != null && trackedLocations.size() > 0) {
                                    boolean isNewSegment = trackedLocations.get(trackedLocations.size() - 1).size() == 1;
                                    view.drawTrackedFeature(trackedLocations, isNewSegment);
                                }
                            }
                            break;
                        case RxBus.LOCATION_CHANGED_NO_NEW_POINT:
                            task = bundle.getParcelable(RxBus.PAYLOAD);
                            if (task != null) {
                                List<List<Location>> trackedLocations = proceduresManager.getTrackedLocations();
                                if (trackedLocations != null && trackedLocations.size() > 0) {
                                    List<Location> lastTrack = new ArrayList<>(trackedLocations.get(trackedLocations.size() - 1));
                                    if (lastTrack.size() > 0) {
                                        view.animateCameraToLocation(lastTrack.get(lastTrack.size() - 1));
                                    }
                                }
                            }
                            break;
                        case RxBus.LOCATION_HIGH_ACCURACY_ERROR:
                            float error = bundle.getFloat(RxBus.PAYLOAD);
                            this.view.showHighAccuracyError(error);
                            break;
                        case RxBus.LOCATION_LOW_ACCURACY_ERROR:
                            this.view.hideHighAccuracyError();
                            break;
                    }
                })
                .subscribe());

        disposables.add(proceduresManager.getSavedGeoJsonObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(geoJson -> {
                    Parcelable lastAddedFeature = geoJson.getLastAddedFeature();
                    if (lastAddedFeature instanceof MultiLineStringFeature) {
                        view.addMultiLineFeatureToMap((MultiLineStringFeature) lastAddedFeature);
                    } else if (lastAddedFeature instanceof PolygonFeature) {
                        view.addPolygonFeatureToMap((PolygonFeature) lastAddedFeature);
                    } else if (lastAddedFeature instanceof PointFeature) {
                        view.centerViewOnNewPointFeature((PointFeature) lastAddedFeature);
                        if (Integer.valueOf(((PointFeature) lastAddedFeature).getProperties().getAccionId()) == 24) {
                            view.showFormButton();
                        }
                    }
                    if (!((Feature) lastAddedFeature).getProperties().isTree()) {
                        view.switchMeasurementButtons(false);
                    }
                    view.showSavedFeatureMessage(R.string.saved_feature);
                    view.refreshMeasurementMap();
                })
                .subscribe());

        disposables.add(proceduresManager.getSavedGeoJsonOnMeasurementObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(geoJson -> {
                    Parcelable lastAddedFeature = geoJson.getLastAddedFeature();
                    if (lastAddedFeature instanceof PointFeature) {
                        view.centerViewOnNewPointFeature((PointFeature) lastAddedFeature);
                        if (Integer.valueOf(((PointFeature) lastAddedFeature).getProperties().getAccionId()) == 24) {
                            view.showFormButton();
                        }
                    }
                    view.showSavedFeatureMessage(R.string.saved_feature);
                    view.hideOnMeasurementFab();
                })
                .subscribe());

        disposables.add(accionesManager.getMaterialsSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(materials -> {
                    if (choosenAction != null && materials != null && materials.size() > 0) {
                        this.view.onMaterialSelected(choosenAction, ((Material) materials.get(0)));
                    }
                })
                .subscribe());

        disposables.add(proceduresManager.getSentMapSubjectObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(result -> this.view.onSendMapSuccess())
                .subscribe());
    }

    @Override
    public void onGeoJson(GeoJson geoJson) {
        // geoJson.clearSpikes();
        this.geoJson = geoJson;
        checkForExistingFeatures();
        if (geoJson.hasStard()) {
            view.showFormButton();
        }
    }

    private void sendValidMap() {
        proceduresManager.sendMap(task);
        this.view.onSendMapTriggered();
    }

    @Override
    public void setChosenAction(Action action) {
        proceduresManager.setChosenAction(action);
    }

    @Override
    public Action getChosenAction() {
        return proceduresManager.getChosenAction();
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void saveComment(View view) {
        this.view.addCommentMarker(comment);
    }

    @Override
    public void stopMeasurement(View view) {
        this.view.showSaveTrackDialog();
    }

    @Override
    public void pauseMeasurement(View view) {
        String chosenActionType = prediosManager.getChosenActionType();
        if (chosenActionType != null && !chosenActionType.equals(AppAccionesManager.PUNTOS)) {
            prediosManager.setIsMeasurementPaused(true);
        }
        this.view.pauseMeasurement();
    }

    @Override
    public void resumeMeasurement(View view) {
        prediosManager.setIsMeasurementPaused(false);
        this.view.resumeMeasurement();
    }

    @Override
    public boolean isMeasurementPaused() {
        return prediosManager.isMeasurementPaused();
    }

    @Override
    public void onForm(View view) {
        this.view.openContractorForm();
    }

    @Override
    public boolean canSendMap() {
        return task.canSendMap();
    }

    @Override
    public void onPrediosManagementLayer() {
        prediosManager.clearPrediosManagementLayer();
        prediosManager.getPrediosManagementLayerFromFile(this.lastLocation);
    }

    @Override
    public void setLastLocation(Location location) {
        if (this.lastLocation == null) {
            prediosManager.getPrediosManagementLayerFromFile(location);
        }
        if (location != null) {
            this.lastLocation = location;
        }
    }

    @Override
    public void getMaterials(Action action) {
        this.choosenAction = action;
        accionesManager.getMaterials(action.getId());
    }

    @Override
    public void refreshMapGeoJson() {
        prediosManager.getGeoJson(this.task);
    }
}
