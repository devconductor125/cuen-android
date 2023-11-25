package com.orquitech.development.cuencaVerde.logic;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.ManagerCallback;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.adapters.ManagerCallbackAdapter;
import com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get.Image;
import com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get.MonitoringMaintenanceResponse;
import com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get.Point;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.ExecutionTaskResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.OpenTaskResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.TaskResponse;
import com.orquitech.development.cuencaVerde.data.db.PersistenceManager;
import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.data.managers.ConnectivityStatusManager;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.AddPhoto;
import com.orquitech.development.cuencaVerde.data.model.Croquis;
import com.orquitech.development.cuencaVerde.data.model.ErosiveProcess;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenancePhoto;
import com.orquitech.development.cuencaVerde.data.model.MonitoreoRecursoHidricoProcess;
import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.data.model.Program;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.TaskComment;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.model.stardSheetForm.StardSheetForm;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.data.utils.FilesUtils;
import com.orquitech.development.cuencaVerde.data.utils.MapperUtils;
import com.orquitech.development.cuencaVerde.data.utils.SHAUtils;
import com.orquitech.development.cuencaVerde.data.utils.ServicesUtils;
import com.orquitech.development.cuencaVerde.logic.services.UserLocationService;
import com.orquitech.development.cuencaVerde.logic.utils.DateUtils;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.widgets.ParcelableLocationArrayList;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class AppProceduresManager extends BaseManager implements ProceduresManager {

    private final App app;
    private final ApiManager apiManager;
    private final PersistenceManager persistenceManager;
    private final ConnectivityStatusManager connectivityStatusManager;
    private final SerializationManager serializationManager;
    private final PrediosManager prediosManager;

    private final PublishSubject<List<Item>> dashboardSubject = PublishSubject.create();
    private final PublishSubject<List<Item>> tasksSubject = PublishSubject.create();
    private final PublishSubject<List<Item>> taskCommentsSubject = PublishSubject.create();
    private final PublishSubject<List<Item>> programsSubject = PublishSubject.create();
    private final PublishSubject<Procedure> savedPredioSubject = PublishSubject.create();
    private final PublishSubject<Survey> savedSurveySubject = PublishSubject.create();
    private final PublishSubject<GeoJson> savedGeoJsonSubject = PublishSubject.create();
    private final PublishSubject<GeoJson> savedGeoJsonOnMeasurementSubject = PublishSubject.create();
    private final PublishSubject<List<Item>> monitorAndMaintenancePhotosSubject = PublishSubject.create();
    private final PublishSubject<List<Item>> taskPhotographicRegistriesSubject = PublishSubject.create();
    private final PublishSubject<PhotographicRegistryPhoto> photographicRegistriesSubject = PublishSubject.create();
    private final PublishSubject<List<PhotographicRegistryPhoto>> taskHasMinutaPhotosSubject = PublishSubject.create();
    private final PublishSubject<Boolean> sentMapSubject = PublishSubject.create();
    private final PublishSubject<MonitorAndMaintenance> sentMonitorAndMaintenanceSubject = PublishSubject.create();
    private final PublishSubject<MonitorAndMaintenance> monitorAndMaintenanceSubject = PublishSubject.create();
    private final PublishSubject<MonitorAndMaintenanceCommentPoint> monitorAndMaintenanceCommentPointSubject = PublishSubject.create();
    private final PublishSubject<List<MonitorAndMaintenanceCommentPoint>> monitorAndMaintenanceCommentPointsSubject = PublishSubject.create();
    private final PublishSubject<Boolean> sentPhotographicRegistrySubject = PublishSubject.create();

    private ArrayMap<String, Procedure> procedures;
    private ArrayMap<String, Task> openTasksMap = new ArrayMap<>();
    private ArrayMap<String, Task> openContractorTasksMap = new ArrayMap<>();
    private ArrayMap<String, Task> executionTasksMap = new ArrayMap<>();
    private ArrayMap<String, MonitorAndMaintenance> monitoringAndMaintenanceMap = new ArrayMap<>();
    private ArrayMap<String, CartaIntencion> cartasIntencionMap = new ArrayMap<>();
    private Action chosenAction;
    private boolean sendingMonitorAndMaintenance;
    private List<ParcelableLocationArrayList> trackedLocations;
    private Bundle bundleFromUserLocationService;

    public AppProceduresManager(App app, ApiManager apiManager, Bus bus, PersistenceManager persistenceManager, ConnectivityStatusManager connectivityStatusManager, SerializationManager serializationManager, PrediosManager prediosManager) {
        super(bus);
        this.app = app;
        this.apiManager = apiManager;
        this.persistenceManager = persistenceManager;
        this.connectivityStatusManager = connectivityStatusManager;
        this.serializationManager = serializationManager;
        this.prediosManager = prediosManager;
        trackedLocations = new ArrayList<>();
    }

    @Override
    public List<List<Location>> getTrackedLocations() {
        List<List<Location>> result = new ArrayList<>();
        result.addAll(trackedLocations);
        return result;
    }

    @Override
    public void setTrackedLocations(List<ParcelableLocationArrayList> trackedLocations) {
        this.trackedLocations = trackedLocations;
    }

    @Override
    public void addNewTrackedLocation(Location location) {
        if (trackedLocations.size() == 0) {
            trackedLocations.add(new ParcelableLocationArrayList());
        }
        trackedLocations.get(trackedLocations.size() - 1).add(location);
    }

    @Override
    public void splitCurrentTrack() {
        if (trackedLocations.size() > 0 && trackedLocations.get(trackedLocations.size() - 1).size() > 1) {
            trackedLocations.add(new ParcelableLocationArrayList());
        }
    }

    @Override
    public Location getLastAddedTrackedLocation() {
        Location resultLocation = null;
        if (trackedLocations != null && trackedLocations.size() > 0) {
            ParcelableLocationArrayList tempList = trackedLocations.get(trackedLocations.size() - 1);
            if (tempList != null && tempList.size() > 0) {
                resultLocation = tempList.get(tempList.size() - 1);
            }
        }
        return resultLocation;
    }

    @Override
    public Observable<List<Item>> getDashboardObservable() {
        return dashboardSubject;
    }

    @Override
    public Observable<Procedure> getSavedProjectObservable() {
        return savedPredioSubject;
    }

    @Override
    public Observable<GeoJson> getSavedGeoJsonObservable() {
        return savedGeoJsonSubject;
    }

    @Override
    public Observable<GeoJson> getSavedGeoJsonOnMeasurementObservable() {
        return savedGeoJsonOnMeasurementSubject;
    }

    @Override
    public Observable<Survey> getSavedSurveyObservable() {
        return savedSurveySubject;
    }

    @Override
    public Observable<Boolean> getSentMapSubjectObservable() {
        return sentMapSubject;
    }

    @Override
    public Observable<MonitorAndMaintenance> getSentMonitorAndMaintenanceCommentSubjectObservable() {
        return sentMonitorAndMaintenanceSubject;
    }

    @Override
    public Observable<MonitorAndMaintenance> getMonitorAndMaintenanceSubject() {
        return monitorAndMaintenanceSubject;
    }

    @Override
    public Observable<List<Item>> getMonitorAndMaintenancePhotosSubject() {
        return monitorAndMaintenancePhotosSubject;
    }

    @Override
    public Observable<List<Item>> getTaskPhotographicRegistriesSubject() {
        return taskPhotographicRegistriesSubject;
    }

    @Override
    public Observable<PhotographicRegistryPhoto> getPhotographicRegistrySubject() {
        return photographicRegistriesSubject;
    }

    @Override
    public Observable<List<PhotographicRegistryPhoto>> getTaskHasMinutaPhotosSubject() {
        return taskHasMinutaPhotosSubject;
    }

    @Override
    public Observable<MonitorAndMaintenanceCommentPoint> getMonitorAndMaintenanceCommentPointSubject() {
        return monitorAndMaintenanceCommentPointSubject;
    }

    @Override
    public Observable<List<MonitorAndMaintenanceCommentPoint>> getMonitorAndMaintenanceCommentPointsSubject() {
        return monitorAndMaintenanceCommentPointsSubject;
    }

    @Override
    public Observable<Boolean> getSentPhotographicRegistrySubjectObservable() {
        return sentPhotographicRegistrySubject;
    }

    @Override
    public void getDashboard() {
        getProcedures(new ManagerCallbackAdapter() {
            @Override
            public void onProcedures(List<Item> procedures) {
                getDueTasks(new ManagerCallbackAdapter() {
                    @Override
                    public void onTasks(List<Item> tasks) {
                        List<Item> dashboardItems = new ArrayList<>();
                        dashboardItems.addAll(tasks);
                        dashboardItems.addAll(procedures);
                        dashboardSubject.onNext(dashboardItems);
                    }
                });
            }
        }).subscribe();
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getProcedures(ManagerCallback callback) {
        return Observable.create(subscriber -> {
            if (hasProjects()) {
                List<Item> items = getProjectsList();
                if (callback != null) {
                    callback.onProcedures(items);
                }
                subscriber.onNext(true);
            } else {
                if (connectivityStatusManager.isConnected()) {
                    apiManager.getProcedures()
                            .onErrorReturn(ServicesUtils::getProjectResponseExceptionList)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(projectsResponse -> {
                                if (projectsResponse.size() == 1 && projectsResponse.get(0).getCode() > 0) {
                                    publishServiceError(projectsResponse.get(0));
                                } else {
                                    Log.d(getClass().getSimpleName(), "Success getDashboard!");
                                    procedures = MapperUtils.projectsResponseToProjectsList(projectsResponse);
                                    List<Item> items = getProjectsList();
                                    if (callback != null) {
                                        Collections.reverse(items);
                                        callback.onProcedures(items);
                                    }
                                    persistenceManager.saveProjects(items, null);
                                }
                                subscriber.onNext(true);
                            });
                } else {
                    persistenceManager.getProcedures(0, 100, new ManagerCallbackAdapter() {
                        @Override
                        public void onProcedures(List<Item> procedures) {
                            for (Item procedure : procedures) {
                                ((Procedure) procedure).setType(AppViewsFactory.PROJECTS_LIST_ITEM_VIEW);
                            }
                            List<Item> items = new ArrayList<>(procedures);
                            addProjects(procedures);
                            if (callback != null) {
                                callback.onProcedures(items);
                            }
                        }
                    });
                }
            }
        });
    }

    private void addProjects(List<Item> projectsList) {
        if (procedures == null) {
            procedures = new ArrayMap<>();
        }
        for (Item project : projectsList) {
            procedures.put(((Procedure) project).getId(), (Procedure) project);
        }
    }

    private boolean hasProjects() {
        return procedures != null && procedures.size() > 0;
    }

    @Override
    public void clearMonitorAndMaintenance() {
        monitoringAndMaintenanceMap = new ArrayMap<>();
    }

    @Override
    public void getDueTasks(ManagerCallbackAdapter callback) {
        if (connectivityStatusManager.isConnected()) {
            List<Item> tasks = new ArrayList<>();
            if (callback != null) {
                callback.onTasks(tasks);
            }
            /* apiManager.getDueTasks()
                    .onErrorReturn(ServicesUtils::getTaskResponseExceptionList)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(tasksResponse -> {
                        if (tasksResponse.size() == 1 && tasksResponse.get(0).getCode() > 0) {
                            publishServiceError(tasksResponse.get(0));
                            callback.onTasks(new ArrayList<>());
                        } else {
                            Log.d(getClass().getSimpleName(), "Success getDueTasks tasks!");
                            List<Item> tasks = MapperUtils.tasksResponseToTasksList(tasksResponse);
                            if (callback != null) {
                                callback.onTasks(tasks);
                            }
                        }
                    });*/
        } else {
            /*persistenceManager.getDueTasks(new ManagerCallbackAdapter() { TODO Query this shit
                @Override
                public void onTasks(List<Item> tasks) {
                    List<Item> items = new ArrayList<>(tasks);
                    if (callback != null) {
                        callback.onTasks(items);
                    }
                }
            });*/
            callback.onTasks(new ArrayList<>());
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getProjectTasks(String procedureId) {
        return Observable.create(subscriber -> {
            monitoringAndMaintenanceMap = new ArrayMap<>();
            openTasksMap = new ArrayMap<>();
            openContractorTasksMap = new ArrayMap<>();
            executionTasksMap = new ArrayMap<>();
            if (connectivityStatusManager.isConnected()) {
                apiManager.getMonitoringAndMaintenance(procedureId)
                        .onErrorReturn(ServicesUtils::getMonitoringMaintenanceResponseExceptionList)
                        .flatMap(monitoringAndMaintenanceResponseList -> handleMonitorAndMaintenanceResponseList(procedureId, monitoringAndMaintenanceResponseList))
                        .onErrorReturn(ServicesUtils::getOpenTaskResponseExceptionList)
                        .flatMap(openTasksResponseList -> handleOpenTasksResponse(procedureId, openTasksResponseList))
                        .onErrorReturn(ServicesUtils::getOpenTaskResponseExceptionList)
                        .flatMap(openTasksResponseList -> handleOpenTasksContractorResponse(procedureId, openTasksResponseList))
                        .onErrorReturn(ServicesUtils::getExecutionTaskResponseExceptionList)
                        .flatMap(executionTasksResponseList -> handleExecutionTasksResponse(procedureId, executionTasksResponseList))
                        .onErrorReturn(ServicesUtils::getTaskResponseExceptionList)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(tasks -> handleTasksResponse(tasks, subscriber));
            } else {
                persistenceManager.getMonitoringAndMaintenances(procedureId, new ManagerCallbackAdapter() {
                    @Override
                    public void onMonitorAndMaintenanceList(List<MonitorAndMaintenance> monitorAndMaintenanceList) {
                        persistenceManager.getTasks(procedureId, new ManagerCallbackAdapter() {
                            @Override
                            public void onTasks(List<Item> tasks) {
                                tasks.addAll(monitorAndMaintenanceList);
                                Collections.reverse(tasks);
                                tasksSubject.onNext(tasks);
                            }
                        });
                    }
                });
            }
        });
    }

    private Observable<List<OpenTaskResponse>> handleMonitorAndMaintenanceResponseList(String procedureId, List<MonitoringMaintenanceResponse> monitoringAndMaintenanceResponseList) {
        if (monitoringAndMaintenanceResponseList.size() > 0 && monitoringAndMaintenanceResponseList.get(0).id != null) {
            if (TextUtils.isEmpty(procedureId)) {
                monitoringAndMaintenanceMap = new ArrayMap<>();
                List<Item> monitorAndMaintenanceList = MapperUtils.monitoringAndMaintenanceResponseToList(monitoringAndMaintenanceResponseList, serializationManager);
                for (Item item : monitorAndMaintenanceList) {
                    getMonitorAndMaintenanceById(((MonitorAndMaintenance) item).getId());
                    prediosManager.getStardMonitorAndMaintenance(((MonitorAndMaintenance) item).getId());
                }
                persistenceManager.saveMonitorAndMaintenances(monitorAndMaintenanceList);
            } else {
                List<Item> items = MapperUtils.monitoringAndMaintenanceResponseToList(monitoringAndMaintenanceResponseList, serializationManager);
                for (Item item : items) {
                    monitoringAndMaintenanceMap.put(((MonitorAndMaintenance) item).getId(), (MonitorAndMaintenance) item);
                    prediosManager.getStardMonitorAndMaintenance(((MonitorAndMaintenance) item).getId());
                }
            }
        }
        return apiManager.getOpenTasks(procedureId);
    }

    private Observable<List<OpenTaskResponse>> handleOpenTasksResponse(String procedureId, List<OpenTaskResponse> tasksResponse) {
        if (tasksResponse.size() == 1 && tasksResponse.get(0).getCode() > 0 && tasksResponse.get(0).getCode() != 400) {
            publishServiceError(tasksResponse.get(0));
        } else {
            Log.d(getClass().getSimpleName(), "Success getTasks contractor tasks! " + tasksResponse.size());
            List<Item> tasks = MapperUtils.openTasksResponseToTasksList(tasksResponse);
            List<Item> items = new ArrayList<>(tasks);

            persistenceManager.saveTasks(tasks);
            getOpenTasksAdditionalInfoFromService(tasks);

            for (Item item : items) {
                openTasksMap.put(((Task) item).getId(), (Task) item);
            }
        }
        return apiManager.getOpenContractorTasks(procedureId);
    }

    private Observable<List<ExecutionTaskResponse>> handleOpenTasksContractorResponse(String procedureId, List<OpenTaskResponse> tasksResponse) {
        if (tasksResponse.size() == 1 && tasksResponse.get(0).getCode() > 0 && tasksResponse.get(0).getCode() != 400) {
            publishServiceError(tasksResponse.get(0));
        } else {
            Log.d(getClass().getSimpleName(), "Success getTasks tasks! " + tasksResponse.size());
            List<Item> tasks = MapperUtils.openContractorTasksResponseToTasksList(tasksResponse);
            List<Item> items = new ArrayList<>(tasks);

            persistenceManager.saveTasks(tasks);
            getContractorTasksAdditionalInfoFromService(tasks);

            for (Item item : items) {
                openContractorTasksMap.put(((Task) item).getId(), (Task) item);
            }
        }
        return apiManager.getExecutionTasks(procedureId);
    }

    private Observable<List<TaskResponse>> handleExecutionTasksResponse(String procedureId, List<ExecutionTaskResponse> tasksResponse) {
        if (tasksResponse.size() == 1 && tasksResponse.get(0).getCode() > 0 && tasksResponse.get(0).getCode() != 400) {
            publishServiceError(tasksResponse.get(0));
        } else {
            Log.d(getClass().getSimpleName(), "Success get execution tasks! " + tasksResponse.size());
            List<Item> tasks = MapperUtils.executionTasksResponseToTasksList(tasksResponse);
            List<Item> items = new ArrayList<>(tasks);

            persistenceManager.saveTasks(tasks);
            getExecutionTasksAdditionalInfoFromService(tasks);

            for (Item item : items) {
                executionTasksMap.put(((Task) item).getId(), (Task) item);
            }
        }
        return apiManager.getTasks(procedureId);
    }

    private void handleTasksResponse(List<TaskResponse> tasksResponse, ObservableEmitter<Boolean> subscriber) {
        if (tasksResponse.size() == 1 && tasksResponse.get(0).getCode() > 0 && tasksResponse.get(0).getCode() != 400) {
            publishServiceError(tasksResponse.get(0));
            subscriber.onNext(true);
        } else {
            Log.d(getClass().getSimpleName(), "Success getTasks tasks! " + tasksResponse.size());
            List<Item> tasks = MapperUtils.tasksResponseToTasksList(tasksResponse);
            List<Item> items = new ArrayList<>(tasks);

            persistenceManager.saveTasks(tasks);
            getTasksAdditionalInfoFromService(tasks);

            List<Item> monitoringAndMaintenanceList = new ArrayList<>(monitoringAndMaintenanceMap.values());
            List<Item> openTasksList = new ArrayList<>(openTasksMap.values());
            List<Item> openContractorTasksList = new ArrayList<>(openContractorTasksMap.values());
            List<Item> executionTasksList = new ArrayList<>(executionTasksMap.values());

            Collections.reverse(items);
            Collections.reverse(monitoringAndMaintenanceList);
            Collections.reverse(openTasksList);
            Collections.reverse(openContractorTasksList);
            Collections.reverse(executionTasksList);

            items.addAll(monitoringAndMaintenanceList);
            items.addAll(openTasksList);
            items.addAll(openContractorTasksList);
            items.addAll(executionTasksList);
            tasksSubject.onNext(items);
            subscriber.onNext(true);
        }
    }

    @SuppressLint("CheckResult")
    private void getTasksAdditionalInfoFromService(List<Item> tasks) {
        for (Item task : tasks) {
            if (!TextUtils.isEmpty(((Task) task).getPotentialId())) {
                apiManager.getPredioCroquis(((Task) task).getPotentialId())
                        .onErrorReturn(ServicesUtils::getCroquisResponseException)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(croquisResponse -> {
                            if (croquisResponse.size() == 1 && croquisResponse.get(0).getCode() > 0) {
                                publishServiceError(croquisResponse.get(0));
                            } else {
                                Log.d(getClass().getSimpleName(), "Success get croquis for task: " + ((Task) task).getId());
                                List<Croquis> croquisList = MapperUtils.croquisResponseToCroquisList(croquisResponse);
                                persistenceManager.saveCroquisList(croquisList, ((Task) task).getPotentialId());
                            }
                        });
            }

            getTaskComments(((Task) task));
            prediosManager.getGeoJson((Task) task);

            if (task instanceof ErosiveProcess || task instanceof MonitoreoRecursoHidricoProcess) {
                LatLng location;
                if (task instanceof ErosiveProcess) {
                    location = ((ErosiveProcess) task).getLocation();
                } else {
                    location = ((MonitoreoRecursoHidricoProcess) task).getLocation();
                }
                apiManager.getPredioCroquisFromCoordinates(location)
                        .onErrorReturn(ServicesUtils::getCroquisResponseException)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(croquisResponse -> {
                            if (croquisResponse.size() == 1 && croquisResponse.get(0).getCode() > 0) {
                                publishServiceError(croquisResponse.get(0));
                            } else {
                                Log.d(getClass().getSimpleName(), "Success get croquis for coordinates: " + ((Task) task).getId());
                                List<Croquis> croquisList = MapperUtils.croquisResponseToCroquisList(croquisResponse);
                                persistenceManager.saveCroquisList(croquisList, ((Task) task).getId());
                            }
                        });
            }
        }
    }

    @SuppressLint("CheckResult")
    private void getOpenTasksAdditionalInfoFromService(List<Item> tasks) {
        for (Item task : tasks) {
            getTaskComments(((Task) task));
        }
    }

    @SuppressLint("CheckResult")
    private void getContractorTasksAdditionalInfoFromService(List<Item> tasks) {
        for (Item task : tasks) {
            prediosManager.getContractSiembra((Task) task);
            getTaskComments(((Task) task));
        }
    }

    @SuppressLint("CheckResult")
    private void getExecutionTasksAdditionalInfoFromService(List<Item> tasks) {
        for (Item task : tasks) {
            apiManager.getGeoJson(((Task) task).getCleanedId(), app.getApplicationContext().getString(R.string.execution_base_geo_json_endpoint))
                    .onErrorReturn(ServicesUtils::getGeoJsonResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getCode() > 0 && response.getCode() != 400) {
                            publishServiceError(response);
                        } else {
                            if (response.getType() != null) {
                                GeoJson geoJsonResponse = MapperUtils.setGeoJsonFeatures(serializationManager, response);
                                persistenceManager.saveExecutionTaskBaseGeoJson(geoJsonResponse, ((Task) task).getId(), null);
                            }
                        }
                    });
            apiManager.getGeoJson(((Task) task).getCleanedId(), app.getApplicationContext().getString(R.string.send_execution_map_endpoint))
                    .onErrorReturn(ServicesUtils::getGeoJsonResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getCode() > 0 && response.getCode() != 400) {
                            publishServiceError(response);
                        } else {
                            if (response.getType() != null) {
                                GeoJson geoJsonResponse = MapperUtils.setGeoJsonFeatures(serializationManager, response);
                                persistenceManager.saveExecutionTaskEditedGeoJson(geoJsonResponse, ((Task) task).getId(), null);
                            }
                        }
                    });
            getTaskComments(((Task) task));
        }
    }

    private List<Item> getProjectsList() {
        if (procedures != null) {
            return new ArrayList<>(procedures.values());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void addFeatureToGeoJson(String taskId, Action action) {
        addFeatureToGeoJson(taskId, action, null);
    }

    @Override
    public void addFeatureToGeoJson(String taskId, Action action, LatLng location) {
        persistenceManager.getGeoJson(taskId, new ManagerCallbackAdapter() {
            @Override
            public void onGeoJson(GeoJson geoJson) {
                if (action.getAccionType().equals(AppAccionesManager.AREAS) || action.getAccionType().equals(AppAccionesManager.ACCIONES)) {
                    if (getTrackedLocations().size() <= 0) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(RxBus.CODE, RxBus.EMPTY_MAP_FEATURE);
                        bus.publish(bundle);
                        return;
                    }
                }
                switch (action.getAccionType()) {
                    case AppAccionesManager.AREAS:
                        geoJson.addPolygonFeature(action, getTrackedLocations(), SHAUtils.getSha256(serializationManager.toJson(getTrackedLocations())));
                        break;
                    case AppAccionesManager.ACCIONES:
                        geoJson.addMultiLineString(action, getTrackedLocations(), SHAUtils.getSha256(serializationManager.toJson(getTrackedLocations())));
                        break;
                    case AppAccionesManager.PUNTOS:
                        geoJson.addPoint(action, location, SHAUtils.getSha256(serializationManager.toJson(location)));
                        break;
                }
                persistenceManager.saveGeoJson(geoJson, taskId, new ManagerCallbackAdapter() {
                    @Override
                    public void onGeoJson(GeoJson geoJson) {
                        if (UserLocationService.isInstanceCreated()) {
                            savedGeoJsonOnMeasurementSubject.onNext(geoJson);
                        } else {
                            savedGeoJsonSubject.onNext(geoJson);
                        }
                        if (!action.getAccionType().equals(AppAccionesManager.PUNTOS)) {
                            setTrackedLocations(new ArrayList<>());
                        }
                    }
                });
            }
        });
    }

    @Override
    public void clearAfterPredioSaved() {
        savedPredioSubject.onNext(new Procedure());
    }

    @Override
    public void clearProjects() {
        procedures = new ArrayMap<>();
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendMap(Task task) {
        persistenceManager.getGeoJson(task.getId(), new ManagerCallbackAdapter() {
            @Override
            public void onGeoJson(GeoJson geoJson) {
                geoJson.mergeFeatures();
                if (geoJson.hasValidFeatures()) {
                    apiManager.sendMap(task, geoJson)
                            .onErrorReturn(ServicesUtils::getResponseException)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(geoJsonResponse -> {
                                if (geoJsonResponse.getCode() > 0 && geoJsonResponse.getCode() != 200) {
                                    publishServiceError(geoJsonResponse);
                                } else {
                                    // persistenceManager.deleteGeoJson(task.getId());
                                    persistenceManager.getStardSheet(task, new ManagerCallbackAdapter() {
                                        @Override
                                        public void onForm(StardSheetForm stardSheet) {
                                            prediosManager.sendStardSheetForm(stardSheet, task.getId());
                                        }
                                    });
                                    sentMapSubject.onNext(true);
                                }
                            });
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendValidationMap(Task task) {
        persistenceManager.getGeoJson(task.getId(), new ManagerCallbackAdapter() {
            @Override
            public void onGeoJson(GeoJson geoJson) {
                geoJson.mergeFeatures();
                if (geoJson.hasValidFeatures()) {
                    apiManager.sendValidationMap(task, geoJson)
                            .onErrorReturn(ServicesUtils::getResponseException)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(geoJsonResponse -> {
                                if (geoJsonResponse.getCode() > 0 && geoJsonResponse.getCode() != 200) {
                                    publishServiceError(geoJsonResponse);
                                } else {
                                    // persistenceManager.deleteGeoJson(task.getId());
                                    persistenceManager.getStardSheet(task, new ManagerCallbackAdapter() {
                                        @Override
                                        public void onForm(StardSheetForm stardSheet) {
                                            prediosManager.sendStardSheetForm(stardSheet, task.getId());
                                        }
                                    });
                                    sentMapSubject.onNext(true);
                                }
                            });
                }
            }
        });
    }

    @Override
    public void saveSurvey(Survey survey) {
        persistenceManager.saveSurvey(survey, null);
    }

    @Override
    public void getSurveyByPredioPotencialId(String predioPotencialId) {
        persistenceManager.getSurveyByPredioPotencialId(predioPotencialId, new ManagerCallbackAdapter() {
            @Override
            public void onSurvey(Survey survey) {
                if (survey.getPropertyVisitDate() == null) {
                    survey.setPropertyVisitDate(DateUtils.getCurrentDate());
                }
                savedSurveySubject.onNext(survey);
            }
        });
    }

    @Override
    public void getSurvey(String taskId) {
    }

    @Override
    public Observable<List<Item>> getTaskObservable() {
        return tasksSubject;
    }

    @Override
    public Observable<List<Item>> getTaskCommentsObservable() {
        return taskCommentsSubject;
    }

    @Override
    public Observable<List<Item>> getProgramsObservable() {
        return programsSubject;
    }

    @SuppressLint("CheckResult")
    @Override
    public void uploadFiles(String taskId, ArrayList<Uri> fileUris) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.uploadFilesFromUris(app.getApplicationContext().getString(R.string.send_files_endpoint), taskId, fileUris)
                    .onErrorReturn(ServicesUtils::getResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(uploadResponse -> {
                        if (uploadResponse.getCode() > 0) {
                            publishServiceError(uploadResponse);
                        } else {
                            Log.d(getClass().getSimpleName(), "Success uploadFilesFromUris tasks!");
                        }
                    });
        }
    }

    @Override
    public void setChosenAction(Action chosenAction) {
        this.chosenAction = chosenAction;
    }

    @Override
    public Action getChosenAction() {
        return this.chosenAction;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMonitorAndMaintenanceByIdForView(String monitorAndMaintenanceId) {
        persistenceManager.getMonitorAndMaintenanceById(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
            @Override
            public void onMonitorAndMaintenance(MonitorAndMaintenance monitorAndMaintenance) {
                monitoringAndMaintenanceMap.put(monitorAndMaintenance.getId(), monitorAndMaintenance);
                monitorAndMaintenanceSubject.onNext(monitorAndMaintenance);
                persistenceManager.getMonitorAndMaintenancePoints(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
                    @Override
                    public void onMonitorAndMaintenanceCommentPoints(List<MonitorAndMaintenanceCommentPoint> monitorAndMaintenanceCommentPoints) {
                        monitorAndMaintenanceCommentPointsSubject.onNext(monitorAndMaintenanceCommentPoints);
                    }
                });
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMonitorAndMaintenanceById(String monitorAndMaintenanceId) {
        if (monitoringAndMaintenanceMap.size() > 0) {
            monitoringAndMaintenanceMap.remove(monitorAndMaintenanceId);
        }
        if (connectivityStatusManager.isConnected()) {
            apiManager.getMonitoringAndMaintenanceById(monitorAndMaintenanceId)
                    .onErrorReturn(ServicesUtils::getMonitoringMaintenanceResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleMonitorAndMaintenanceResponse);
        } else {
            persistenceManager.getMonitorAndMaintenanceById(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
                @Override
                public void onMonitorAndMaintenance(MonitorAndMaintenance monitorAndMaintenance) {
                    monitoringAndMaintenanceMap.put(monitorAndMaintenance.getId(), monitorAndMaintenance);
                    monitorAndMaintenanceSubject.onNext(monitorAndMaintenance);
                    persistenceManager.getMonitorAndMaintenancePoints(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
                        @Override
                        public void onMonitorAndMaintenanceCommentPoints(List<MonitorAndMaintenanceCommentPoint> monitorAndMaintenanceCommentPoints) {
                            monitorAndMaintenanceCommentPointsSubject.onNext(monitorAndMaintenanceCommentPoints);
                        }
                    });
                }
            });
        }
    }

    @SuppressLint("CheckResult")
    private void handleMonitorAndMaintenanceResponse(MonitoringMaintenanceResponse monitorAndMaintenanceResponse) {
        if (monitorAndMaintenanceResponse.getCode() > 0) {
            publishServiceError(monitorAndMaintenanceResponse);
        } else {
            Log.d(getClass().getSimpleName(), "Success get vegetalMaintenance!");
            MonitorAndMaintenance monitorAndMaintenance = MapperUtils.monitorAndMaintenanceResponseToMonitorAndMaintenance(monitorAndMaintenanceResponse, serializationManager);
            persistenceManager.saveMonitorAndMaintenance(monitorAndMaintenance);
            monitoringAndMaintenanceMap.put(monitorAndMaintenance.getId(), monitorAndMaintenance);
            monitorAndMaintenanceSubject.onNext(monitorAndMaintenance);

            if (monitorAndMaintenanceResponse.points != null && monitorAndMaintenanceResponse.points.size() > 0) {
                for (Point point : monitorAndMaintenanceResponse.points) {
                    for (MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint : monitorAndMaintenance.getPoints()) {
                        monitorAndMaintenanceCommentPoint.setFromService();
                        persistenceManager.saveMonitorAndMaintenanceCommentPoint(monitorAndMaintenanceCommentPoint, new ManagerCallbackAdapter() {
                            @Override
                            public void onMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
                                monitorAndMaintenanceCommentPointSubject.onNext(monitorAndMaintenanceCommentPoint);
                            }
                        });
                    }
                    if (point.images != null) {
                        for (Image image : point.images) {
                            apiManager.getImageFile(image.name)
                                    .onErrorReturn(error -> getErrorResponseBody())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(responseBody -> handleMonitorAndMaintenanceImageFileResponse(responseBody, image, point));
                        }
                    }
                }
            }
        }
    }

    private void handleMonitorAndMaintenanceImageFileResponse(ResponseBody responseBody, Image image, Point point) {
        try {
            if (responseBody != null && responseBody.byteStream() != null) {
                String fileName = image.name.substring(image.name.indexOf("_") + 1);

                MonitorAndMaintenancePhoto monitorAndMaintenancePhoto = new MonitorAndMaintenancePhoto();
                monitorAndMaintenancePhoto.setPhotoName(fileName);
                monitorAndMaintenancePhoto.setMonitorAndMaintenanceCommentPointId(String.valueOf(point.id));
                Bitmap bitmap = FilesUtils.saveImageFileFromStream(app.getApplicationContext(), responseBody.byteStream(), fileName);
                monitorAndMaintenancePhoto.setBitmap(bitmap);
                monitorAndMaintenancePhoto.setType(AppViewsFactory.MONITOR_MAINTENANCE_PHOTO_ITEM_VIEW);

                persistenceManager.saveMonitorAndMaintenancePhoto(monitorAndMaintenancePhoto);
            }
        } catch (NullPointerException ex) {
            Log.d(getClass().getSimpleName(), ex.getMessage());
        }
    }

    private void handlePhotographicRegistryImageFileResponse(ResponseBody responseBody, Image image, Task task) {
        try {
            if (responseBody != null && responseBody.byteStream() != null) {
                String fileName = image.name.substring(image.name.indexOf("_") + 1);

                PhotographicRegistryPhoto photographicRegistryPhoto = new PhotographicRegistryPhoto();
                photographicRegistryPhoto.setPhotoName(fileName);
                photographicRegistryPhoto.setTaskId(String.valueOf(task.getId()));
                // Bitmap bitmap = FilesUtils.saveImageFileFromStream(app.getApplicationContext(), responseBody.byteStream(), fileName);
                // photographicRegistryPhoto.setUri(bitmap);
                photographicRegistryPhoto.setType(AppViewsFactory.PHOTOGRAPHIC_REGISTRY_PHOTO_ITEM_VIEW);

                persistenceManager.savePhotographicRegistryPhoto(photographicRegistryPhoto, null);
            }
        } catch (NullPointerException ex) {
            Log.d(getClass().getSimpleName(), ex.getMessage());
        }
    }

    private ResponseBody getErrorResponseBody() {
        return new ResponseBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public long contentLength() {
                return 0;
            }

            @Override
            public BufferedSource source() {
                return null;
            }
        };
    }

    @Override
    public void getMonitorAndMaintenancePhotos(String monitorAndMaintenanceCommentPointId) {
        persistenceManager.getMonitorAndMaintenancePhotos(monitorAndMaintenanceCommentPointId, new ManagerCallbackAdapter() {
            @Override
            public void onMonitorAndMaintenancePhotos(List<MonitorAndMaintenancePhoto> monitorAndMaintenancePhotos) {
                List<Item> items = new ArrayList<>();
                AddPhoto addPhoto = new AddPhoto();
                addPhoto.setType(AppViewsFactory.ADD_PHOTO_ITEM_VIEW);
                items.add(addPhoto);
                items.addAll(monitorAndMaintenancePhotos);
                monitorAndMaintenancePhotosSubject.onNext(items);
            }
        });
    }

    @Override
    public void saveMonitorAndMaintenancePhoto(MonitorAndMaintenancePhoto monitorAndMaintenancePhoto) {
        persistenceManager.saveMonitorAndMaintenancePhoto(monitorAndMaintenancePhoto);
    }

    @Override
    public void getPhotographicRegistries(String taskId) {
        persistenceManager.getPhotographicRegistryPhotos(taskId, new ManagerCallbackAdapter() {
            @Override
            public void onPhotographicRegistryPhotos(List<PhotographicRegistryPhoto> photographicRegistryPhotos) {
                List<Item> items = new ArrayList<>(photographicRegistryPhotos);
                taskPhotographicRegistriesSubject.onNext(items);
            }
        });
    }

    @Override
    public void taskHasMinutaPhotos(String taskId) {
        persistenceManager.getPhotographicRegistryPhotos(taskId, new ManagerCallbackAdapter() {
            @Override
            public void onPhotographicRegistryPhotos(List<PhotographicRegistryPhoto> photographicRegistryPhotos) {
                taskHasMinutaPhotosSubject.onNext(photographicRegistryPhotos);
            }
        });
    }

    @Override
    public void getPhotographicRegistryById(String registryId) {
        persistenceManager.getPhotographicRegistryPhotos(registryId, new ManagerCallbackAdapter() {
            @Override
            public void onPhotographicRegistryPhotos(List<PhotographicRegistryPhoto> photographicRegistryPhotos) {
                if (photographicRegistryPhotos.size() > 0) {
                    photographicRegistriesSubject.onNext(photographicRegistryPhotos.get(0));
                }
            }
        });
    }

    @Override
    public void savePhotographicRegistry(PhotographicRegistryPhoto photographicRegistryPhoto, ManagerCallback callback) {
        persistenceManager.savePhotographicRegistryPhoto(photographicRegistryPhoto, callback);
    }

    @Override
    public void updatePhotographicRegistry(PhotographicRegistryPhoto photographicRegistryPhoto) {
        persistenceManager.updatePhotographicRegistry(photographicRegistryPhoto);
    }

    @Override
    public void deletePhotographicRegistry(List<PhotographicRegistryPhoto> photos) {
        List<String> photographicRegistryIds = new ArrayList<>();
        for (PhotographicRegistryPhoto photo : photos) {
            photographicRegistryIds.add(photo.getId());
        }
        persistenceManager.deletePhotographicRegistry(photographicRegistryIds);
    }

    @Override
    public void saveMonitorAndMaintenancePoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenancePoint) {
        persistenceManager.saveMonitorAndMaintenanceCommentPoint(monitorAndMaintenancePoint, null);
    }

    @Override
    public void saveMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
        persistenceManager.saveMonitorAndMaintenanceCommentPoint(monitorAndMaintenanceCommentPoint, new ManagerCallbackAdapter() {
            @Override
            public void onMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
                monitorAndMaintenanceCommentPointSubject.onNext(monitorAndMaintenanceCommentPoint);
            }
        });
    }

    @Override
    public void getMonitorAndMaintenancePoints(String monitorAndMaintenanceId) {
        persistenceManager.getMonitorAndMaintenancePoints(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
            @Override
            public void onMonitorAndMaintenanceCommentPoints(List<MonitorAndMaintenanceCommentPoint> monitorAndMaintenanceCommentPoints) {
                monitorAndMaintenanceCommentPointsSubject.onNext(monitorAndMaintenanceCommentPoints);
            }
        });
    }

    @Override
    public void deleteMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
        persistenceManager.deleteMonitorAndMaintenanceCommentPoint(monitorAndMaintenanceCommentPoint.getId());
    }

    @Override
    public void sendMonitorAndMaintenanceData(MonitorAndMaintenance monitorAndMaintenance, String monitorAndMaintenanceId) {
        if (connectivityStatusManager.isConnected()) {
            persistenceManager.getMonitorAndMaintenancePoints(monitorAndMaintenance.getId(), new ManagerCallbackAdapter() {
                @Override
                public void onMonitorAndMaintenanceCommentPoints(List<MonitorAndMaintenanceCommentPoint> monitorAndMaintenanceCommentPoints) {
                    for (MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint : monitorAndMaintenanceCommentPoints) {
                        persistenceManager.getPhotographicRegistryPhotos("monitoreo_" + monitorAndMaintenanceCommentPoint.getId(), new ManagerCallbackAdapter() {
                            @SuppressLint("CheckResult")
                            @Override
                            public void onPhotographicRegistryPhotos(List<PhotographicRegistryPhoto> photographicRegistryPhotos) {
                                apiManager.sendMonitorAndMaintenancePoint(monitorAndMaintenanceCommentPoint, photographicRegistryPhotos)
                                        .onErrorReturn(ServicesUtils::getResponseException)
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(uploadResponse -> {
                                            if (uploadResponse.getCode() > 0) {
                                                publishServiceError(uploadResponse);
                                            } else {
                                                Log.d(getClass().getSimpleName(), "Success sendMonitorAndMaintenanceData!");
                                                // deleteSentMonitorMaintenance(monitorAndMaintenance.getId());
                                            }
                                        });
                            }
                        });
                    }
                }
            });
            prediosManager.sendMaintenanceControlForm(monitorAndMaintenance, monitorAndMaintenanceId);
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void endMonitorAndMaintenance(MonitorAndMaintenance monitorAndMaintenance) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.endMonitorAndMaintenance(monitorAndMaintenance)
                    .onErrorReturn(ServicesUtils::getResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(uploadResponse -> {
                    });
        }
    }

    @Override
    public void deleteSentMonitorMaintenance(String monitorAndMaintenanceId) {
        monitoringAndMaintenanceMap.remove(monitorAndMaintenanceId);
        persistenceManager.deleteMonitorAndMaintenanceById(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
            @Override
            public void onMonitorAndMaintenanceDeleted() {
                persistenceManager.deleteMonitorAndMaintenanceCommentPointByMonitorAndMaintenanceId(monitorAndMaintenanceId);
            }
        });
    }

    @Override
    public Observable<Boolean> getOfflineData() {
        return getProcedures(null)
                .flatMap(onProcedures -> getProjectTasks(null))
                .map(onTasks -> true);
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<Boolean> getTaskComments(Task task) { // TODO get comments by task type, execution
        return Observable.create(subscriber -> {
            if (connectivityStatusManager.isConnected()) {
                apiManager.getTaskComments(task)
                        .onErrorReturn(ServicesUtils::getTaskCommentsResponseExceptionList)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(taskCommentsResponse -> {
                            List<Item> taskComments = MapperUtils.taskCommentsResponseToTaskCommentsList(taskCommentsResponse);
                            List<Item> items = new ArrayList<>(taskComments);

                            persistenceManager.saveTaskComments(taskComments, task.getId());
                            taskCommentsSubject.onNext(items);
                        });
            } else {
                persistenceManager.getTaskComments(task.getId(), new ManagerCallbackAdapter() {
                    @Override
                    public void onTaskComments(List<TaskComment> taskComments) {
                        List<Item> items = new ArrayList<>(taskComments);
                        taskCommentsSubject.onNext(items);
                    }
                });
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void endMeasurementTask(Task task) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.endMeasurementTask(task.getId())
                    .onErrorReturn(ServicesUtils::getResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {});
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void endTaskNoMap(Task task) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.endTaskNoMap(task.getId())
                    .onErrorReturn(ServicesUtils::getResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                    });
        }
    }

    @Override
    public void sendPhotographicRegistry(Task task) {
        if (connectivityStatusManager.isConnected()) {
            persistenceManager.getPhotographicRegistryPhotos(task.getId(), new ManagerCallbackAdapter() {
                @SuppressLint("CheckResult")
                @Override
                public void onPhotographicRegistryPhotos(List<PhotographicRegistryPhoto> photographicRegistryPhotos) {
                    if (photographicRegistryPhotos.size() > 0) {
                        String taskId = String.valueOf(task.getCleanedId());
                        apiManager.uploadPhotos(app.getApplicationContext().getString(R.string.send_communications_images_endpoint), taskId, photographicRegistryPhotos)
                                .onErrorReturn(ServicesUtils::getResponseException)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(uploadResponse -> {
                                    if (uploadResponse.getCode() > 0 && uploadResponse.getCode() != 200) {
                                        publishServiceError(uploadResponse);
                                    } else {
                                        sentPhotographicRegistrySubject.onNext(true);
                                    }
                                });
                    } else {
                        sentPhotographicRegistrySubject.onNext(true);
                    }
                }
            });
        }
    }

    @Override
    public void sendMinuta(Task task) {
        if (connectivityStatusManager.isConnected()) {
            persistenceManager.getPhotographicRegistryPhotos(task.getId(), new ManagerCallbackAdapter() {
                @SuppressLint("CheckResult")
                @Override
                public void onPhotographicRegistryPhotos(List<PhotographicRegistryPhoto> photographicRegistryPhotos) {
                    if (photographicRegistryPhotos.size() > 0) {
                        String taskId = String.valueOf(task.getCleanedId());
                        apiManager.uploadMinuta(app.getApplicationContext().getString(R.string.send_minuta_endpoint), taskId, photographicRegistryPhotos)
                                .onErrorReturn(ServicesUtils::getResponseException)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(uploadResponse -> {
                                    if (uploadResponse.getCode() > 0 && uploadResponse.getCode() != 200) {
                                        publishServiceError(uploadResponse);
                                    } else {
                                        sentPhotographicRegistrySubject.onNext(true);
                                    }
                                });
                    } else {
                        sentPhotographicRegistrySubject.onNext(true);
                    }
                }
            });
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void getProgramsFromService() {
        if (connectivityStatusManager.isConnected()) {
            apiManager.getPrograms()
                    .onErrorReturn(ServicesUtils::getProgramsResponseExceptionList)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(programsResponse -> {
                        List<Item> programs = MapperUtils.programsResponseToProgramsList(programsResponse);
                        persistenceManager.savePrograms(programs);
                    });
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPrograms() {
        persistenceManager.getPrograms(new ManagerCallbackAdapter() {
            @Override
            public void onPrograms(List<Program> programs) {
                List<Item> items = new ArrayList<>(programs);
                programsSubject.onNext(items);
            }
        });
    }

    @Override
    public void setBundleFromUserLocationService(Bundle bundle) {
        bundleFromUserLocationService = bundle;
    }

    @Override
    public Bundle getBundleFromUserLocationService() {
        return bundleFromUserLocationService;
    }
}
