package com.orquitech.development.cuencaVerde.data.db;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.data.ManagerCallback;
import com.orquitech.development.cuencaVerde.data.adapters.ManagerCallbackAdapter;
import com.orquitech.development.cuencaVerde.data.model.ContractSiembra;
import com.orquitech.development.cuencaVerde.data.model.ContractorEvaluation;
import com.orquitech.development.cuencaVerde.data.model.Croquis;
import com.orquitech.development.cuencaVerde.data.model.GeometryComment;
import com.orquitech.development.cuencaVerde.data.model.MaintenanceControl;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenancePhoto;
import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.PQRS;
import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.data.model.Predio;
import com.orquitech.development.cuencaVerde.data.model.PredioFollowUp;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.data.model.Province;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.data.model.contractorForm.ContractorForm;
import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.model.meetingsWithActors.MeetingsWithActorsForm;
import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.orquitech.development.cuencaVerde.data.model.siembraDetailForm.SiembraDetailForm;
import com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance.StardMonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.stardSheetForm.StardSheetForm;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance.VegetalMaintenance;
import com.orquitech.development.cuencaVerde.data.utils.FilesUtils;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class AppPersistenceManager implements PersistenceManager {

    private final DbHelper dbHelper;
    private final App app;
    private PublishSubject<Boolean> onClearedDatabaseSubject = PublishSubject.create();

    @Inject
    public AppPersistenceManager(App app, DbHelper dbHelper) {
        this.app = app;
        this.dbHelper = dbHelper;
    }

    @Override
    public Observable<Boolean> getOnClearedDatabaseObservable() {
        return onClearedDatabaseSubject;
    }

    @SuppressLint("CheckResult")
    @Override
    public void savePredio(Predio predio, ManagerCallback callback) {
        dbHelper.savePredio(predio)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved predio " + predio.getId()))
                .subscribe(callback::onPredio);
    }

    @SuppressLint("CheckResult")
    public void savePredios(List<Predio> predios) {
        dbHelper.savePredios(predios)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved " + predios.size() + " predios"))
                .subscribe(savedPredio -> {

                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPredios(int offset, int limit, ManagerCallback callback) {
        dbHelper.getPredios(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got predios from DB"))
                .subscribe(callback::onPredios);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPrediosPotenciales(int offset, int limit, ManagerCallback callback) {
        dbHelper.getPrediosPotenciales(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got predios potenciales from DB"))
                .subscribe(callback::onPrediosPotenciales);
    }

    @Override
    public void saveProcedure(Procedure project) {
        saveProcedure(project, null);
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveProcedure(Procedure project, ManagerCallback callback) {
        dbHelper.saveProcedure(project)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved project " + project.getId()))
                .subscribe(projectResult -> {
                    if (callback != null) {
                        callback.onProcedure(projectResult);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveTask(Task task, ManagerCallback callback) {
        dbHelper.saveTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved projects in DB: " + task.getId()))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onTask(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveProjects(List<Item> projects, ManagerCallback callback) {
        dbHelper.saveProjects(projects)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved projects in DB: " + projects.size()))
                .subscribe(projectsResult -> {
                    if (callback != null) {
                        callback.onProcedures(projectsResult);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getProcedures(int offset, int limit, ManagerCallback callback) {
        dbHelper.getProjects(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got projects from DB"))
                .subscribe(callback::onProcedures);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getProcedureById(String projectId, ManagerCallback callback) {
        dbHelper.getProjectById(projectId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got project from DB"))
                .subscribe(callback::onProcedure);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getTasks(String procedureId, ManagerCallback callback) {
        dbHelper.getTasks(procedureId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got tasks from DB"))
                .subscribe(callback::onTasks);
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveSurvey(Survey survey) {
        saveSurvey(survey, null);
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveSurvey(Survey survey, ManagerCallback callback) {
        if (survey != null) {
            dbHelper.saveSurvey(survey)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> Log.d(getClass().getName(), "Saved survey in DB"))
                    .subscribe(surveyResult -> {
                        if (callback != null) {
                            callback.onSurvey(surveyResult);
                        }
                    });
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void getSurveyByPredioPotencialId(String predioPotencialId, ManagerCallback callback) {
        dbHelper.getSurveyByPredioPotencialId(predioPotencialId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got survey from DB " + predioPotencialId))
                .subscribe(callback::onSurvey);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getDueTasks(ManagerCallback callback) {
        dbHelper.getDueTasks(0, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got dashboard tasks from DB"))
                .subscribe(callback::onTasks);
    }

    @SuppressLint("CheckResult")
    @Override
    public void savePredioPotencial(PredioPotencial predioPotencial, ManagerCallback callback) {
        dbHelper.savePredioPotencial(predioPotencial)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved predio potencial in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onSaved();
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deletePredioByRemoteId(long remoteId) {
        dbHelper.deletePredioByRemoteIdSubscription(remoteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted predio potencial from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void savePrediosPotenciales(List<PredioPotencial> predios, ManagerCallback callback) {
        dbHelper.savePrediosPotenciales(predios)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved predio potencial in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onSaved();
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deletePrediosPotenciales() {
        dbHelper.deletePrediosPotenciales()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted predios potenciales from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteSurvey(Survey survey) {
        dbHelper.deleteSurvey(survey.getPredioPotencialId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted survey from DB from fragment_task_detail"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveAcciones(List<Item> acciones) {
        dbHelper.saveAcciones(acciones)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved accionesTitle in DB: " + acciones.size()))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAcciones(String type, int offset, int limit, ManagerCallback callback) {
        dbHelper.getAcciones(type, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got accionesTitle from DB"))
                .subscribe(callback::onAcciones);
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveMaterials(List<Item> materials) {
        dbHelper.saveMaterials(materials)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved materials in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMaterials(int offset, int limit, ManagerCallback callback) {
        dbHelper.getMaterials(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got materials from DB"))
                .subscribe(callback::onMaterials);
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveMonitorAndMaintenances(List<Item> monitorAndMaintenances) {
        dbHelper.saveMonitorAndMaintenances(monitorAndMaintenances)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved monitorAndMaintenances in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveTasks(List<Item> tasks) {
        dbHelper.saveTasks(tasks)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved tasks in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMonitoringAndMaintenances(String procedureId, ManagerCallback callback) {
        dbHelper.getMonitorAndMaintenanceList(procedureId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got MonitorAndMaintenanceList from DB"))
                .subscribe(callback::onMonitorAndMaintenanceList);
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveMonitorAndMaintenancePhoto(MonitorAndMaintenancePhoto monitorAndMaintenancePhoto) {
        dbHelper.saveMonitorAndMaintenancePhoto(monitorAndMaintenancePhoto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved monitorAndMaintenancePhoto in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void savePhotographicRegistryPhoto(PhotographicRegistryPhoto photographicRegistryPhoto, ManagerCallback callback) {
        dbHelper.savePhotographicRegistry(photographicRegistryPhoto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved photographicRegistryPhoto in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onPhotographicRegistryPhoto(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void updatePhotographicRegistry(PhotographicRegistryPhoto photographicRegistryPhoto) {
        dbHelper.updatePhotographicRegistry(photographicRegistryPhoto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Updated photographicRegistryPhoto in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint, ManagerCallback callback) {
        dbHelper.saveMonitorAndMaintenanceCommentPoint(monitorAndMaintenanceCommentPoint)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved monitorAndMaintenanceCommentPoint in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onMonitorAndMaintenanceCommentPoint(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMonitorAndMaintenancePoints(String monitorAndMaintenanceId, ManagerCallbackAdapter callback) {
        if (!TextUtils.isEmpty(monitorAndMaintenanceId)) {
            dbHelper.getMonitorAndMaintenanceCommentPoints(monitorAndMaintenanceId, 0, 1000)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> Log.d(getClass().getName(), "Got getMonitorAndMaintenancePoints from DB"))
                    .subscribe(callback::onMonitorAndMaintenanceCommentPoints);
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteMonitorAndMaintenanceCommentPoint(String monitorAndMaintenanceIdCommentPointId) {
        dbHelper.deleteMonitorAndMaintenanceCommentPoint(monitorAndMaintenanceIdCommentPointId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted monitorAndMaintenanceIdCommentPointId " + monitorAndMaintenanceIdCommentPointId + " and its photos"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deletePhotographicRegistry(List<String> photographicRegistryIds) {
        dbHelper.deletePhotographicRegistry(photographicRegistryIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted " + photographicRegistryIds.size() + " photographicRegistries"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deletePhotographicRegistryByTaskId(String taskId) {
        dbHelper.getPhotographicRegistryPhotos(taskId, 0, 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted task " + taskId + " photographicRegistries"))
                .subscribe(photographicRegistryPhotos -> {

                    List<String> photographicRegistryIds = new ArrayList<>();
                    for (PhotographicRegistryPhoto photo : photographicRegistryPhotos) {
                        FilesUtils.deleteFile(app.getApplicationContext(), photo.getPhotoName());
                        photographicRegistryIds.add(photo.getId());
                    }
                    deletePhotographicRegistry(photographicRegistryIds);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMonitorAndMaintenancePhotos(String monitorAndMaintenanceCommentPointId, ManagerCallbackAdapter callback) {
        if (TextUtils.isEmpty(monitorAndMaintenanceCommentPointId)) {
            monitorAndMaintenanceCommentPointId = "-1";
        }
        dbHelper.getMonitorAndMaintenancePhotos(monitorAndMaintenanceCommentPointId, 0, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got monitorAndMaintenancePhotos"))
                .subscribe(callback::onMonitorAndMaintenancePhotos);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPhotographicRegistryPhotos(String taskId, ManagerCallback callback) {
        if (TextUtils.isEmpty(taskId)) {
            taskId = "-1";
        }
        dbHelper.getPhotographicRegistryPhotos(taskId, 0, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got taskPhotographicRegistryPhotos"))
                .subscribe(callback::onPhotographicRegistryPhotos);
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteMonitorAndMaintenanceCommentPointByMonitorAndMaintenanceId(String monitorAndMaintenanceId) {
        dbHelper.deleteMonitorAndMaintenanceCommentPointByMonitorAndMaintenanceId(monitorAndMaintenanceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted deleteMonitorAndMaintenanceCommentPointByMonitorAndMaintenanceId " + monitorAndMaintenanceId + " and its photos"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPendingMonitorAndMaintenances(int offset, int limit, ManagerCallback callback) {
        dbHelper.getPendingMonitorAndMaintenances()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got gotPendingMonitorAndMaintenances"))
                .subscribe(callback::onMonitorAndMaintenanceList);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getGeoJson(String taskId, ManagerCallback callback) {
        dbHelper.getGeoJson(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got gotGeoJson"))
                .subscribe(callback::onGeoJson);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getExecutionTaskBaseGeoJson(String taskId, ManagerCallback callback) {
        dbHelper.getExecutionBaseGeoJson(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got base gotGeoJson"))
                .subscribe(callback::onGeoJson);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getExecutionTaskEditedGeoJson(String taskId, ManagerCallback callback) {
        dbHelper.getExecutionEditedGeoJson(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got edited gotGeoJson"))
                .subscribe(callback::onGeoJson);
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteGeoJson(String taskId) {
        /*dbHelper.deleteGeoJson(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted geoJson from DB"))
                .subscribe(result -> {
                });*/
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteExecutionTaskBaseGeoJson(String taskId) {
        dbHelper.deleteExecutionBaseGeoJson(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted base geoJson from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteExecutionTaskEditedGeoJson(String taskId) {
        dbHelper.deleteExecutionEditedGeoJson(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted edited geoJson from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMonitorAndMaintenanceById(String monitorAndMaintenanceId, ManagerCallback callback) {
        dbHelper.getMonitorAndMaintenanceById(monitorAndMaintenanceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got MonitorAndMaintenance from DB"))
                .subscribe(callback::onMonitorAndMaintenance);
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveMonitorAndMaintenance(MonitorAndMaintenance monitorAndMaintenance) {
        dbHelper.saveMonitorAndMaintenance(monitorAndMaintenance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved vegetalMaintenance in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteMonitorAndMaintenanceById(String monitorAndMaintenanceId, ManagerCallback callback) {
        dbHelper.deleteMonitorAndMaintenanceById(monitorAndMaintenanceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted monitorAndMaintenanceById from DB"))
                .subscribe(result -> callback.onMonitorAndMaintenanceDeleted());
    }

    @SuppressLint("CheckResult")
    @Override
    public void savePQRS(PQRS pqrs) {
        dbHelper.savePQRS(pqrs)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved PQRS in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllPQRS(ManagerCallback callback) {
        dbHelper.getAllPQRS()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got all PQRS from DB"))
                .subscribe(callback::onPQRSList);
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteAllPQRS() {
        dbHelper.deleteAllPQRS()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted all PQRS from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveDependencies(List<Item> dependencies) {
        dbHelper.saveDependencies(dependencies)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved dependencies in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getDependencies(ManagerCallback callback) {
        dbHelper.getDependencies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got all dependencies from DB"))
                .subscribe(callback::onDependencies);
    }

    @SuppressLint("CheckResult")
    @Override
    public void savePqrsTypes(List<Item> pqrsTypes) {
        dbHelper.savePQRSTypes(pqrsTypes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved pqrsTypes in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPqrsTypes(String dependencyId, ManagerCallback callback) {
        dbHelper.getPQRSTypes(dependencyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got all pqrsTypes from DB"))
                .subscribe(callback::onPqrsTypes);
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance, ManagerCallback callback) {
        if (stardMonitorAndMaintenance == null) {
            return;
        }
        dbHelper.saveStardMonitorAndMaintenance(stardMonitorAndMaintenance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved stardMonitorAndMaintenance in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onStardMonitorAndMaintenance(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveContractSiembra(ContractSiembra contractSiembra, ManagerCallback callback) {
        if (contractSiembra == null) {
            return;
        }
        dbHelper.saveContractSiembra(contractSiembra)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved contractSiembra in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onContractSiembra(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getStardMonitorAndMaintenance(String monitorAndMaintenanceId, ManagerCallbackAdapter callback) {
        dbHelper.getStardMonitorAndMaintenance(monitorAndMaintenanceId, 0, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got stardMonitorAndMaintenance"))
                .subscribe(callback::onStardMonitorAndMaintenance);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getStardMonitorAndMaintenances(int offset, int limit, ManagerCallback callback) {
        dbHelper.getPendingStardMonitorAndMaintenances()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got pendingStardMonitorAndMaintenances"))
                .subscribe(callback::onStardMonitorAndMaintenances);
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteStardMonitorAndMaintenance(String monitorAndMaintenanceId) {
        dbHelper.deleteStardMonitorAndMaintenance(monitorAndMaintenanceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted pendingStardMonitorAndMaintenances from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveVegetalMaintenance(VegetalMaintenance vegetalMaintenance, ManagerCallback callback) {
        dbHelper.saveVegetalMaintenance(vegetalMaintenance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved vegetalMaintenance in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onVegetalMaintenance(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getVegetalMaintenanceByMonitorAndMaintenanceId(String monitorAndMaintenanceId, ManagerCallbackAdapter callback) {
        dbHelper.getVegetalMaintenance(monitorAndMaintenanceId, 0, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got vegetalMaintenance"))
                .subscribe(callback::onVegetalMaintenance);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPendingVegetalMaintenances(int offset, int limit, ManagerCallback callback) {
        dbHelper.getPendingVegetalMaintenances()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got pendingVegetalMaintenances"))
                .subscribe(callback::onVegetalMaintenances);
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteVegetalMaintenance(String monitorAndMaintenanceId) {
        dbHelper.deleteVegetalMaintenance(monitorAndMaintenanceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted pendingVegetalMaintenances from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void savePredioFollowUp(PredioFollowUp predioFollowUp, ManagerCallback callback) {
        dbHelper.savePredioFollowUp(predioFollowUp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved predioFollowUp in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onPredioFollowUp(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPredioFollowUpByMonitorAndMaintenanceId(String monitorAndMaintenanceId, ManagerCallbackAdapter callback) {
        dbHelper.getPredioFollowUp(monitorAndMaintenanceId, 0, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got predioFollowUp"))
                .subscribe(callback::onPredioFollowUp);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPendingPredioFollowUps(int offset, int limit, ManagerCallback callback) {
        dbHelper.getPendingPredioFollowUps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got getPredioFollowUpMaintenances"))
                .subscribe(callback::onPredioFollowUps);
    }

    @SuppressLint("CheckResult")
    @Override
    public void deletePredioFollowUp(String monitorAndMaintenanceId) {
        dbHelper.deletePredioFollowUp(monitorAndMaintenanceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted predioFollowUp from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteCartaIntencion(String cartaIntencionId) {
        dbHelper.deleteCartaIntencion(cartaIntencionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted stardSheetForm from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveContractorEvaluation(ContractorEvaluation contractorEvaluation, ManagerCallback callback) {
        dbHelper.saveContractorEvaluation(contractorEvaluation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved contractorEvaluation in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onContractorEvaluation(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getContractorEvaluationByMonitorAndMaintenanceId(String monitorAndMaintenanceId, ManagerCallbackAdapter callback) {
        dbHelper.getContractorEvaluation(monitorAndMaintenanceId, 0, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got contractorEvaluation"))
                .subscribe(callback::onContractorEvaluation);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPendingContractorEvaluations(int offset, int limit, ManagerCallback callback) {
        dbHelper.getPendingContractorEvaluations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got getContractorEvaluations"))
                .subscribe(callback::onContractorEvaluations);
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteContractorEvaluation(String monitorAndMaintenanceId) {
        dbHelper.deleteContractorEvaluation(monitorAndMaintenanceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted contractorEvaluation from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveCartaIntencion(CartaIntencion cartaIntencion, ManagerCallback callback) {
        dbHelper.saveCartaIntencion(cartaIntencion)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved unique stardSheetForm in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onCartaIntencion(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCartaIntencion(String cartaIntencionId, ManagerCallback callback) {
        dbHelper.getCartaIntencionByPredioPotencialId(cartaIntencionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got stardSheetForm from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onCartaIntencion(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveGeoJson(GeoJson geoJson, String taskId, ManagerCallback callback) {
        dbHelper.saveGeoJson(geoJson, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved geoJson in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onGeoJson(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveExecutionTaskBaseGeoJson(GeoJson geoJson, String taskId, ManagerCallback callback) {
        dbHelper.saveExecutionTaskBaseGeoJson(geoJson, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved base geoJson in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onGeoJson(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveExecutionTaskEditedGeoJson(GeoJson geoJson, String taskId, ManagerCallback callback) {
        dbHelper.saveExecutionTaskEditedGeoJson(geoJson, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved edited geoJson in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onGeoJson(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getStardSheet(Task task, ManagerCallback callback) {
        dbHelper.getStardSheet(task.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got stardSheetForm from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onForm(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getContractorForm(String contractSiembraId, ManagerCallback callback) {
        dbHelper.getContractorForm(contractSiembraId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got contractorForm from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onContractorForm(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getSiembraDetailForm(String siembraDetailFormId, ManagerCallback callback) {
        if (!TextUtils.isEmpty(siembraDetailFormId)) {
            dbHelper.getSiembraDetailForm(siembraDetailFormId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> Log.d(getClass().getName(), "Got siembraDetailForm from DB"))
                    .subscribe(result -> {
                        if (callback != null) {
                            callback.onSiembraDetailForm(result);
                        }
                    });
        } else {
            callback.onSiembraDetailForm(new SiembraDetailForm());
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveStardSheet(StardSheetForm stardSheetForm, String taskId, ManagerCallback callback) {
        dbHelper.saveStardSheet(stardSheetForm, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved stardSheet in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onForm(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveContractorForm(ContractorForm contractorForm, String contractSiembraId, ManagerCallback callback) {
        dbHelper.saveContractorForm(contractorForm, contractSiembraId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved contractorForm in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onContractorForm(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveSiembraDetailForm(SiembraDetailForm siembraDetailForm, String hash, String taskId, ManagerCallback callback) {
        dbHelper.saveSiembraDetailForm(siembraDetailForm, hash, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved siembraDetailForm in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onSiembraDetailForm(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteStardSheet(String taskId) {
        dbHelper.deleteStardSheet(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted stardSheet from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteContractorForm(String taskId) {
        dbHelper.deleteContractorForm(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted contractorForm from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteSiembraDetailForms(Set<String> hashes) {
        dbHelper.deleteSiembraDetailForms(hashes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted siembraDetailForms from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getContractSiembra(String contractSurveyId, ManagerCallback callback) {
        dbHelper.getContractSiembra(contractSurveyId, 0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got contractSurvey from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onContractSiembra(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteContractSiembra(String contractSiembraId) {
        dbHelper.deleteContractSiembra(contractSiembraId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted contractSiembra from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMaintenanceControl(MonitorAndMaintenance monitorAndMaintenance, ManagerCallback callback) {
        dbHelper.getMaintenanceControl(monitorAndMaintenance.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got maintenanceControl from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onMaintenanceControl(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveMaintenanceControl(MaintenanceControl maintenanceControl, String monitorAndMaintenanceId, ManagerCallback callback) {
        dbHelper.saveMaintenanceControl(maintenanceControl, monitorAndMaintenanceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved maintenanceControl in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onMaintenanceControl(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteMaintenanceControl(String monitorAndMaintenanceId) {
        dbHelper.deleteMaintenanceControl(monitorAndMaintenanceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted maintenanceControl from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void clearTables() {
        dbHelper.clearTables()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Cleared al tables from DB"))
                .subscribe(result -> onClearedDatabaseSubject.onNext(true), error -> Log.e(getClass().getName(), "ClearTables error: " + error.getMessage()));
    }

    @SuppressLint("CheckResult")
    @Override
    public void getTaskComments(String taskId, ManagerCallback callback) {
        dbHelper.getTaskComments(taskId, 0, 200)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got taskComments from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onTaskComments(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveTaskComments(List<Item> taskComments, String taskId) {
        dbHelper.saveTaskComments(taskComments, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved taskComments in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveProvinces(List<Province> provinces) {
        dbHelper.saveProvinces(provinces)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved provinces in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMunicipalities(ManagerCallback callback) {
        dbHelper.getMunicipalities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got provinces from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onMunicipalities(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveMunicipalities(List<Municipality> municipalities) {
        dbHelper.saveMunicipalities(municipalities)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved municipalities in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCroquis(String predioId, ManagerCallback callback) {
        if (!TextUtils.isEmpty(predioId)) {
            dbHelper.getCroquis(predioId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> Log.d(getClass().getName(), "Got croquis from DB -> predioId: " + predioId))
                    .subscribe(result -> {
                        if (callback != null) {
                            callback.onCroquisList(result);
                        }
                    });
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveCroquisList(List<Croquis> croquisList, String predioId) {
        dbHelper.saveCroquisList(croquisList, predioId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved croquis list in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPrediosManagementLayer(ManagerCallback callback) {
        dbHelper.getPrediosManagementLayer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got predios management layer from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onPrediosManagementGeoJson(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void savePrediosManagementLayer(GeoJson json) {
        dbHelper.savePrediosManagementLayer(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved predios management layer in DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getSiembraDetails(String hash, ManagerCallback callback) {
        dbHelper.getSiembraDetails(hash, 0, 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got siembraDetail from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onSiembraDetailList(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getSiembraDetailsByTaskId(String taskId, ManagerCallback callback) {
        dbHelper.getSiembraDetailsByTaskId(taskId, 0, 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got siembraDetail by task from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onSiembraDetailList(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMeetingsWithActorsForm(String taskId, ManagerCallback callback) {
        dbHelper.getMeetingsWithActorsForm(taskId, 0, 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got meetingsWithActorsForm from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onMeetingsWithActorsForm(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveMeetingsWithActorsForm(MeetingsWithActorsForm meetingsWithActorsForm, String taskId, ManagerCallback callback) {
        dbHelper.saveMeetingsWithActorsForm(meetingsWithActorsForm, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got siembraDetail by task from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onMeetingsWithActorsForm(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteMeetingsWithActorsForm(String taskId) {
        dbHelper.deleteMeetingsWithActorsForm(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Deleted meetingsWithActorsForm from DB"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveGeometryComment(String taskId, GeometryComment geometryComment) {
        dbHelper.saveGeometryComment(taskId, geometryComment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved GeometryComment for task: " + taskId))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void savePrograms(List<Item> programs) {
        dbHelper.savePrograms(programs)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> Log.e(getClass().getSimpleName(), "savePrograms -> " + error.getMessage()))
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved programs"))
                .subscribe(result -> {
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPrograms(ManagerCallback callback) {
        dbHelper.getPrograms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got programs"))
                .subscribe(programs -> {
                    if (callback != null) {
                        callback.onPrograms(programs);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getGeometryComment(String taskId, String hash, ManagerCallback callback) {
        dbHelper.getGeometryComment(taskId, hash)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got GeometryComment for task: " + taskId))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onGeometryComment(result);
                    }
                });
    }

    /***
     *
     * Sampling point form CRUD
     *
     */

    @SuppressLint("CheckResult")
    @Override
    public void saveSamplingPointForm(SamplingPointForm samplingPointForm, String hash, String taskId, ManagerCallback callback) {
        dbHelper.saveSamplingPointForm(samplingPointForm, hash, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved samplingPointForm in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onSamplingPointForm(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getSamplingPointsByTaskId(String taskId, ManagerCallback callback) {
        dbHelper.getSamplingPointsByTaskId(taskId, 0, 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got samplingPoints by task from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onSamplingPoints(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getSamplingPoints(String hash, ManagerCallback callback) {
        dbHelper.getSamplingPoints(hash, 0, 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got samplingPoints from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onSamplingPoints(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getSamplingPointForm(String samplingPointFormId, ManagerCallback callback) {
        if (!TextUtils.isEmpty(samplingPointFormId)) {
            dbHelper.getSamplingPointForm(samplingPointFormId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> Log.d(getClass().getName(), "Got samplingPointForm from DB"))
                    .subscribe(result -> {
                        if (callback != null) {
                            callback.onSamplingPointForm(result);
                        }
                    });
        } else {
            callback.onSamplingPointForm(new SamplingPointForm());
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteSamplingPointForms(Set<String> hashes) {
        if (hashes.size() > 0) {
            dbHelper.deleteSamplingPointForms(hashes)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> Log.d(getClass().getName(), "Deleted samplingPointForm from DB"))
                    .subscribe();
        }
    }

    /***
     *
     * Erosive Process form CRUD
     *
     */

    @SuppressLint("CheckResult")
    @Override
    public void saveErosiveProcessForm(ErosiveProcessForm erosiveProcessForm, String hash, String taskId, ManagerCallback callback) {
        dbHelper.saveErosiveProcessForm(erosiveProcessForm, hash, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Saved erosiveProcessForm in DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onErosiveProcessForm(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getErosiveProcessFormsByTaskId(String taskId, ManagerCallback callback) {
        dbHelper.getErosiveProcessFormsByTaskId(taskId, 0, 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got erosiveProcessForms by task from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onErosiveProcessForms(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getErosiveProcessForms(String hash, ManagerCallback callback) {
        dbHelper.getErosiveProcessForms(hash, 0, 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getName(), "Got erosiveProcessForms from DB"))
                .subscribe(result -> {
                    if (callback != null) {
                        callback.onErosiveProcessForms(result);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getErosiveProcessForm(String erosiveProcessFormId, ManagerCallback callback) {
        if (!TextUtils.isEmpty(erosiveProcessFormId)) {
            dbHelper.getErosiveProcessForm(erosiveProcessFormId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> Log.d(getClass().getName(), "Got erosiveProcessForm from DB"))
                    .subscribe(result -> {
                        if (callback != null) {
                            callback.onErosiveProcessForm(result);
                        }
                    });
        } else {
            callback.onErosiveProcessForm(new ErosiveProcessForm());
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteErosiveProcessForms(Set<String> hashes) {
        if (hashes.size() > 0) {
            dbHelper.deleteErosiveProcessForms(hashes)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> Log.d(getClass().getName(), "Deleted erosiveProcessForms from DB"))
                    .subscribe();
        }
    }
}
