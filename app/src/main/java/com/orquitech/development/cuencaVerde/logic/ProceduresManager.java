package com.orquitech.development.cuencaVerde.logic;

import android.annotation.SuppressLint;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import com.orquitech.development.cuencaVerde.data.ManagerCallback;
import com.orquitech.development.cuencaVerde.data.adapters.ManagerCallbackAdapter;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenancePhoto;
import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ParcelableLocationArrayList;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public interface ProceduresManager {

    List<List<Location>> getTrackedLocations();

    void setTrackedLocations(List<ParcelableLocationArrayList> trackedLocations);

    void addNewTrackedLocation(Location location);

    void splitCurrentTrack();

    Location getLastAddedTrackedLocation();

    Observable<List<Item>> getDashboardObservable();

    Observable<Procedure> getSavedProjectObservable();

    Observable<GeoJson> getSavedGeoJsonObservable();

    Observable<GeoJson> getSavedGeoJsonOnMeasurementObservable();

    Observable<Survey> getSavedSurveyObservable();

    Observable<Boolean> getSentMapSubjectObservable();

    Observable<MonitorAndMaintenance> getSentMonitorAndMaintenanceCommentSubjectObservable();

    Observable<PhotographicRegistryPhoto> getPhotographicRegistrySubject();

    Observable<List<PhotographicRegistryPhoto>> getTaskHasMinutaPhotosSubject();

    Observable<MonitorAndMaintenanceCommentPoint> getMonitorAndMaintenanceCommentPointSubject();

    Observable<List<MonitorAndMaintenanceCommentPoint>> getMonitorAndMaintenanceCommentPointsSubject();

    Observable<List<Item>> getTaskObservable();

    Observable<MonitorAndMaintenance> getMonitorAndMaintenanceSubject();

    Observable<List<Item>> getMonitorAndMaintenancePhotosSubject();

    Observable<List<Item>> getTaskPhotographicRegistriesSubject();

    Observable<Boolean> getSentPhotographicRegistrySubjectObservable();

    void getDashboard();

    Observable<Boolean> getProcedures(ManagerCallback callback);

    void clearMonitorAndMaintenance();

    void getDueTasks(ManagerCallbackAdapter persistenceManagerCallbackAdapter);

    void saveSurvey(Survey survey);

    Observable<Boolean> getProjectTasks(String projectId);

    void addFeatureToGeoJson(String taskId, Action actionName);

    void addFeatureToGeoJson(String taskId, Action actionName, LatLng location);

    void clearAfterPredioSaved();

    void clearProjects();

    void getSurveyByPredioPotencialId(String taskId);

    void sendMap(Task task);

    void sendValidationMap(Task task);

    Observable<List<Item>> getTaskCommentsObservable();

    Observable<List<Item>> getProgramsObservable();

    void uploadFiles(String taskId, ArrayList<Uri> uriList);

    void getSurvey(String taskId);

    void setChosenAction(Action chosenAction);

    Action getChosenAction();

    @SuppressLint("CheckResult")
    void getMonitorAndMaintenanceByIdForView(String monitorAndMaintenanceId);

    void getMonitorAndMaintenanceById(String monitorAndMaintenanceId);

    void getMonitorAndMaintenancePhotos(String monitorAndMaintenanceCommentPointId);

    void saveMonitorAndMaintenancePhoto(MonitorAndMaintenancePhoto monitorAndMaintenancePhoto);

    void getPhotographicRegistries(String taskId);

    void taskHasMinutaPhotos(String taskId);

    void getPhotographicRegistryById(String registryId);

    void savePhotographicRegistry(PhotographicRegistryPhoto photographicRegistryPhoto, ManagerCallback callback);

    void updatePhotographicRegistry(PhotographicRegistryPhoto photographicRegistryPhoto);

    void deletePhotographicRegistry(List<PhotographicRegistryPhoto> photos);

    void saveMonitorAndMaintenancePoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenance);

    void saveMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint);

    void getMonitorAndMaintenancePoints(String monitorAndMaintenanceId);

    void deleteMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint);

    void sendMonitorAndMaintenanceData(MonitorAndMaintenance monitorAndMaintenance, String monitorAndMaintenanceId);

    void endMonitorAndMaintenance(MonitorAndMaintenance monitorAndMaintenance);

    void deleteSentMonitorMaintenance(String monitorAndMaintenanceId);

    Observable<Boolean> getOfflineData();

    Observable<Boolean> getTaskComments(Task task);

    void endMeasurementTask(Task task);

    void endTaskNoMap(Task task);

    void sendPhotographicRegistry(Task task);

    void sendMinuta(Task task);

    void getProgramsFromService();

    void getPrograms();

    void setBundleFromUserLocationService(Bundle bundle);

    Bundle getBundleFromUserLocationService();
}
