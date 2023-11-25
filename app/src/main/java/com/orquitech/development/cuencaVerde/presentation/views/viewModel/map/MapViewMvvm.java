package com.orquitech.development.cuencaVerde.presentation.views.viewModel.map;

import android.location.Location;

import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineStringFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.PointFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.polygon.PolygonFeature;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.MapView;
import com.orquitech.development.cuencaVerde.presentation.views.viewModel.AppViewModel;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface MapViewMvvm {

    interface View extends MapView {

        void setExistingTrackedLocations(List<List<Location>> trackedLocations);

        void drawTrackedFeature(List<List<Location>> trackedLocations, boolean isNewSegment);

        void addMultiLineFeatureToMap(MultiLineStringFeature feature);

        void addPolygonFeatureToMap(PolygonFeature feature);

        void centerViewOnNewPointFeature(PointFeature feature);

        void addMultiLineFeaturesToMap(List<MultiLineStringFeature> features);

        void addPolygonFeaturesToMap(List<PolygonFeature> features);

        void addPointFeaturesToMap(List<PointFeature> pointFeatures);

        void showSaveTrackDialog();

        void showSendMeasurementMapDialog();

        void showSavedFeatureMessage(int messageResource);

        void hideOnMeasurementFab();

        void centerViewOnFeatures(List<LatLng> coordinates);

        void onSendMapSuccess();

        void onSendMapTriggered();

        void showMissingFeaturesMessage();

        void showEmptyFeatureMessage();

        void addCommentMarker(String comment);

        void addTreeMarker(String comment);

        void onHydrologicalMonitoringPoint();

        void showCantSendMapMessage();

        void initDataCapture();

        void initPointDataCapture();

        void pauseMeasurement();

        void resumeMeasurement();

        void animateCameraToLocation(Location location);

        void showMissingFormMessage();

        void showFormButton();

        void closeFab();

        void refreshMeasurementMap();

        void showHighAccuracyError(float error);

        void hideHighAccuracyError();
    }

    interface ViewModel extends AppViewModel {

        void checkForExistingFeatures();

        void addGoodPracticeToGeoJson(Action action, LatLng lastLocation);

        void addCommentMarker(android.view.View view);

        void initDataCapture(android.view.View view);

        void initPointDataCapture(android.view.View view);

        void stopMeasurement(android.view.View view);

        void pauseMeasurement(android.view.View view);

        void resumeMeasurement(android.view.View view);

        void onSendMapCancelled();

        void setChosenAction(Action action);

        Action getChosenAction();

        String getComment();

        void setComment(String comment);

        void saveComment(android.view.View view);

        boolean isMeasurementPaused();

        void setTask(Task task);

        Task getTask();

        void onPrediosManagementLayer();

        void setLastLocation(Location location);

        void getMaterials(Action action);

        void refreshMapGeoJson();
    }
}
