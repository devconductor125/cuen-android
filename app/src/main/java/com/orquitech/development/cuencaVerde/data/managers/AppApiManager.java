package com.orquitech.development.cuencaVerde.data.managers;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.api.UploadListener;
import com.orquitech.development.cuencaVerde.data.api.cuencaVerdeApi.ApiConfig;
import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeRequest;
import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.orquitech.development.cuencaVerde.data.api.model.SendSurveyCarta.post.SendSurveyCartaResponse;
import com.orquitech.development.cuencaVerde.data.api.model.acciones.get.ActionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.cartaAndSurvey.get.CartaAndSurveyResponse;
import com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.CartaIntencionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.User;
import com.orquitech.development.cuencaVerde.data.api.model.contractor.post.SiembraDetailsRequest;
import com.orquitech.development.cuencaVerde.data.api.model.croquis.get.CroquisResponse;
import com.orquitech.development.cuencaVerde.data.api.model.dependencies.get.DependencyResponse;
import com.orquitech.development.cuencaVerde.data.api.model.erosiveProcess.post.ErosiveProcessRequest;
import com.orquitech.development.cuencaVerde.data.api.model.logIn.LogInRequest;
import com.orquitech.development.cuencaVerde.data.api.model.logIn.LogInResponse;
import com.orquitech.development.cuencaVerde.data.api.model.map.post.MapRequest;
import com.orquitech.development.cuencaVerde.data.api.model.material.get.MaterialActionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.meetingsWithActors.post.MeetingsWithActorsFormRequest;
import com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get.MonitoringMaintenanceResponse;
import com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.post.MonitoringMaintenanceRequest;
import com.orquitech.development.cuencaVerde.data.api.model.municipalities.get.MunicipalityResponse;
import com.orquitech.development.cuencaVerde.data.api.model.pqrsTypes.get.PQRSTypeResponse;
import com.orquitech.development.cuencaVerde.data.api.model.prediosPotenciales.get.PredioPotencialResponse;
import com.orquitech.development.cuencaVerde.data.api.model.programs.get.ProgramResponse;
import com.orquitech.development.cuencaVerde.data.api.model.projects.get.response.ProcedureResponse;
import com.orquitech.development.cuencaVerde.data.api.model.province.get.ProvinceResponse;
import com.orquitech.development.cuencaVerde.data.api.model.quota.get.QuotaResponse;
import com.orquitech.development.cuencaVerde.data.api.model.recursoHidricoSampling.post.RecursoHidricoRequest;
import com.orquitech.development.cuencaVerde.data.api.model.role.RoleResponse;
import com.orquitech.development.cuencaVerde.data.api.model.stardMonitorAndMaintenanceForm.get.StardMonitorAndMaintenanceFormResponse;
import com.orquitech.development.cuencaVerde.data.api.model.survey.get.SurveyResponse;
import com.orquitech.development.cuencaVerde.data.api.model.taskComments.get.TaskCommentResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.ExecutionTaskResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.OpenTaskResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.TaskResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.post.OpenTaskCommentRequest;
import com.orquitech.development.cuencaVerde.data.api.services.CroquisApi;
import com.orquitech.development.cuencaVerde.data.api.services.CuencaVerdeApi;
import com.orquitech.development.cuencaVerde.data.db.PersistenceManager;
import com.orquitech.development.cuencaVerde.data.model.Auth;
import com.orquitech.development.cuencaVerde.data.model.ContractSiembra;
import com.orquitech.development.cuencaVerde.data.model.ContractorEvaluation;
import com.orquitech.development.cuencaVerde.data.model.ExecutionTask;
import com.orquitech.development.cuencaVerde.data.model.MaintenanceControl;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.data.model.PQRS;
import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.data.model.PredioFollowUp;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.data.model.contractorForm.ContractorForm;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.model.meetingsWithActors.MeetingsWithActorsForm;
import com.orquitech.development.cuencaVerde.data.model.siembraDetailForm.SiembraDetailForm;
import com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance.StardMonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.stardSheetForm.StardSheetForm;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance.VegetalMaintenance;
import com.orquitech.development.cuencaVerde.data.utils.FilesUtils;
import com.orquitech.development.cuencaVerde.data.utils.MapperUtils;
import com.orquitech.development.cuencaVerde.data.utils.ServicesUtils;
import com.orquitech.development.cuencaVerde.logic.preferences.AppPreferencesObjectFactory;
import com.orquitech.development.cuencaVerde.logic.preferences.PreferencesManager;
import com.orquitech.development.cuencaVerde.logic.utils.FileUtils;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class AppApiManager implements ApiManager {

    public static final String BYTES_WRITTEN = "bytesWritten";
    public static final String CONTENT_LENGTH = "contentLength";
    private final App app;
    private final HttpManager httpManager;
    private final ApiConfig apiConfig;
    private final SerializationManager serializationManager;
    private final ConnectivityStatusManager connectivityStatusManager;
    private final PersistenceManager persistenceManager;

    private CroquisApi croquisApi;
    private CuencaVerdeApi cuencaVerdeApi;
    private CuencaVerdeApi cuencaVerdeImagesApi;
    private CuencaVerdeApi cuencaVerdeOauth;

    private CuencaVerdeApi cuencaVerdeMockApi;
    private CuencaVerdeApi cuencaVerdeFilesUploadApi;
    private UploadListener uploadListener;
    private PublishSubject<Bundle> uploadProgressSubject = PublishSubject.create();

    public AppApiManager(App app, HttpManager httpManager, ApiConfig apiConfig, PreferencesManager preferencesManager, SerializationManager serializationManager, ConnectivityStatusManager connectivityStatusManager, PersistenceManager persistenceManager) {
        this.app = app;
        this.httpManager = httpManager;
        this.apiConfig = apiConfig;
        this.serializationManager = serializationManager;
        this.connectivityStatusManager = connectivityStatusManager;
        this.persistenceManager = persistenceManager;
        initUploadListener();
        Auth user = (Auth) preferencesManager.get(AppPreferencesObjectFactory.AUTH, Auth.class);
        String accessToken = user != null ? user.getAccessToken() : "";
        initApi(accessToken);
    }

    private void initUploadListener() {
        this.uploadListener = (bytesWritten, contentLength) -> {
            Bundle bundle = new Bundle();
            bundle.putLong(BYTES_WRITTEN, bytesWritten);
            bundle.putLong(CONTENT_LENGTH, contentLength);
            uploadProgressSubject.onNext(bundle);
        };
    }

    @Override
    public Observable<Bundle> getUploadProgressObservable() {
        return uploadProgressSubject;
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<PredioPotencialResponse> sendPredioPotencial(PredioPotencial predioPotencial) {
        Map<String, String> fieldsMap = new HashMap<>();
        fieldsMap.put("name", predioPotencial.getName());
        fieldsMap.put("lat", String.valueOf(predioPotencial.getLatitude()));
        fieldsMap.put("lng", String.valueOf(predioPotencial.getLongitude()));
        return cuencaVerdeApi.sendPredioPotencial(getString(R.string.predio_potencial_endpoint), fieldsMap)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(ServicesUtils::getPredioPotencialResponseException)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void initApi(String accessToken) {
        this.croquisApi = httpManager.getCroquisApiRetrofit().create(CroquisApi.class);
        this.cuencaVerdeApi = httpManager.getCuencaVerdeRetrofit(accessToken).create(CuencaVerdeApi.class);
        this.cuencaVerdeImagesApi = httpManager.getCuencaVerdeImagesRetrofit(accessToken).create(CuencaVerdeApi.class);
        this.cuencaVerdeOauth = httpManager.getOauthRetrofit(accessToken).create(CuencaVerdeApi.class);
        this.cuencaVerdeMockApi = httpManager.getCuencaVerdeMockRetrofit(accessToken).create(CuencaVerdeApi.class);
        this.cuencaVerdeFilesUploadApi = httpManager.getCuencaVerdeFilesUploadRetrofit(accessToken, uploadListener).create(CuencaVerdeApi.class);
    }

    private String getString(int resource) {
        return TextUtil.getString(app.getApplicationContext(), resource);
    }

    private CuencaVerdeRequest setGeneralFormData(CuencaVerdeRequest request) {
        request.setClientId(apiConfig.getClientId());
        request.setClientSecret(apiConfig.getClientSecret());
        request.setGrantType(apiConfig.getGrantType());
        return request;
    }

    @Override
    public Observable<LogInResponse> logInUser(String username, String password) {
        LogInRequest logInRequest = new LogInRequest(username, password);
        logInRequest = (LogInRequest) setGeneralFormData(logInRequest);
        return cuencaVerdeOauth.logIn(getString(R.string.auth_endpoint), logInRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<List<ProcedureResponse>> getProcedures() {
        return cuencaVerdeApi.getProcedures(getString(R.string.procedures_endpoint))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<List<TaskResponse>> getTasks(String processId) {
        if (TextUtils.isEmpty(processId)) {
            return cuencaVerdeApi.getAllTasks(getString(R.string.get_all_tasks))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.io());
        } else {
            return cuencaVerdeApi.getTasks(getString(R.string.get_tasks_endpoint), processId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.io());
        }
    }

    @Override
    public Observable<List<OpenTaskResponse>> getOpenTasks(String processId) {
        if (TextUtils.isEmpty(processId)) {
            return cuencaVerdeApi.getAllOpenTasks(getString(R.string.get_all_open_tasks))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.io());
        } else {
            return cuencaVerdeApi.getOpenTasks(getString(R.string.get_open_tasks_endpoint), processId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.io());
        }
    }

    @Override
    public Observable<List<OpenTaskResponse>> getOpenContractorTasks(String processId) {
        if (TextUtils.isEmpty(processId)) {
            return cuencaVerdeApi.getAllOpenContractorTasks(getString(R.string.get_all_open_contractor_tasks))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.io());
        } else {
            return cuencaVerdeApi.getOpenContractorTasks(getString(R.string.get_open_tasks_contractor_endpoint), processId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.io());
        }
    }

    @Override
    public Observable<List<ExecutionTaskResponse>> getExecutionTasks(String processId) {
        if (TextUtils.isEmpty(processId)) {
            return cuencaVerdeApi.getAllExecutionTasks(getString(R.string.get_all_execution_tasks))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.io());
        } else {
            return cuencaVerdeApi.getExecutionTasks(getString(R.string.get_execution_tasks_endpoint), processId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.io());
        }
    }

    @Override
    public Observable<List<TaskCommentResponse>> getTaskComments(Task task) {
        return cuencaVerdeApi.getTaskComments(getString(R.string.get_task_comments_endpoint), task.getId(), task.getTaskType())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<List<ProgramResponse>> getPrograms() {
        return cuencaVerdeApi.getPrograms(getString(R.string.programs_endpoint))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<List<CartaIntencionResponse>> getCartasIntencion(String procedureId) {
        if (TextUtils.isEmpty(procedureId)) {
            return cuencaVerdeApi.getCartasIntencion(getString(R.string.carta_intencion_endpoint))
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io());
        } else {
            return cuencaVerdeApi.getCartasIntencionByProcedureId(getString(R.string.carta_intencion_endpoint), procedureId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io());
        }
    }

    @Override
    public Observable<CartaIntencionResponse> getCartaIntencion(String cartaIntencionId) {
        return cuencaVerdeApi.getCartaIntencion(getString(R.string.carta_intencion_endpoint), cartaIntencionId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<List<TaskResponse>> getDueTasks() {
        return cuencaVerdeMockApi.getDueTasks(getString(R.string.due_tasks_endpoint))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<SendSurveyCartaResponse> sendSurvey(String predioPotencialId, Survey survey) {
        String json = serializationManager.toJson(survey);
        Map<String, String> fieldsMap = new HashMap<>();
        fieldsMap.put("info_general", json);
        fieldsMap.put("potential_id", predioPotencialId);
        return cuencaVerdeApi.sendSurvey(getString(R.string.send_survey_carta_endpoint), fieldsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<SendSurveyCartaResponse> sendCartaIntencion(String predioPotencialId, CartaIntencion cartaIntencion, List<PhotographicRegistryPhoto> registryPhotos) {
        String json = serializationManager.toJson(cartaIntencion);
        Map<String, String> fieldsMap = new HashMap<>();
        fieldsMap.put("form_letter", json);
        fieldsMap.put("potential_id", predioPotencialId);

        final String MEDIA_TYPE = "files[]";
        List<MultipartBody.Part> parts = new ArrayList<>();
        Map<String, String> comments = new HashMap<>();

        ArrayList<File> files = new ArrayList<>();
        for (PhotographicRegistryPhoto photo : registryPhotos) {
            files.add(FilesUtils.getOutputMediaFile(app.getApplicationContext(), photo.getPhotoName()));
            comments.put(photo.getPhotoName(), photo.getComment());
        }

        for (File file : files) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(MEDIA_TYPE, file.getName(), requestFile);
            parts.add(part);
        }

        RequestBody letterBody = RequestBody.create(okhttp3.MultipartBody.FORM, json);
        RequestBody idBody = RequestBody.create(okhttp3.MultipartBody.FORM, predioPotencialId);
        RequestBody typeBody = RequestBody.create(okhttp3.MultipartBody.FORM, predioPotencialId);
        RequestBody commentsBody = RequestBody.create(okhttp3.MultipartBody.FORM, serializationManager.toJson(comments));
        return cuencaVerdeApi.sendCartaIntencion(getString(R.string.send_survey_carta_endpoint), letterBody, idBody, typeBody, commentsBody, parts)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendMap(Task task, GeoJson geoJson) {
        String geoJsonString = serializationManager.toJson(geoJson);
        MapRequest mapRequest = new MapRequest();
        mapRequest.geojson = geoJsonString;
        mapRequest.taskId = task.getCleanedId();
        String endpoint;
        if (task.getTaskType().equals(BaseFragment.PSA)) {
            endpoint = getString(R.string.geo_json_open_task_endpoint);
        } else if (task instanceof ExecutionTask) {
            endpoint = getString(R.string.send_execution_map_endpoint); // Tareas de equipo seguimiento (también conocidas como ejecución)
        } else {
            endpoint = getString(R.string.send_map_endpoint); // Tareas de gestión predial
        }
        return cuencaVerdeApi.sendMap(endpoint, mapRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendValidationMap(Task task, GeoJson geoJson) {
        String geoJsonString = serializationManager.toJson(geoJson);
        MapRequest mapRequest = new MapRequest();
        mapRequest.geojson = geoJsonString;
        mapRequest.taskId = task.getCleanedId();
        String endpoint = getString(R.string.send_validation_map);
        return cuencaVerdeApi.sendMap(endpoint, mapRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> uploadFilesFromUris(String endpoint, String taskId, List<Uri> fileUris) {
        final String MEDIA_TYPE = "files[]";
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (Uri uri : fileUris) {
            File file = getFile(uri);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(MEDIA_TYPE, file.getName(), requestFile);
            parts.add(part);
        }
        RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, taskId);
        return cuencaVerdeFilesUploadApi.uploadFiles(endpoint, description, parts)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> uploadPhotos(String endpoint, String taskId, List<PhotographicRegistryPhoto> registryPhotos) {
        final String MEDIA_TYPE = "files[]";
        List<MultipartBody.Part> parts = new ArrayList<>();
        Map<String, String> comments = new HashMap<>();

        ArrayList<File> files = new ArrayList<>();
        for (PhotographicRegistryPhoto photo : registryPhotos) {
            files.add(FilesUtils.getOutputMediaFile(app.getApplicationContext(), photo.getPhotoName()));
            comments.put(photo.getPhotoName(), photo.getComment());
        }

        for (File file : files) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(MEDIA_TYPE, file.getName(), requestFile);
            parts.add(part);
        }

        RequestBody taskIdBody = RequestBody.create(okhttp3.MultipartBody.FORM, taskId);
        RequestBody typeBody = RequestBody.create(okhttp3.MultipartBody.FORM, taskId);
        RequestBody commentsBody = RequestBody.create(okhttp3.MultipartBody.FORM, serializationManager.toJson(comments));
        return cuencaVerdeFilesUploadApi.uploadFiles(endpoint, taskIdBody, typeBody, commentsBody, parts)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> uploadMinuta(String endpoint, String taskId, List<PhotographicRegistryPhoto> minutaFiles) {
        final String MEDIA_TYPE = "files[]";
        List<MultipartBody.Part> parts = new ArrayList<>();

        ArrayList<File> files = new ArrayList<>();
        for (PhotographicRegistryPhoto photo : minutaFiles) {
            files.add(FilesUtils.getOutputMediaFile(app.getApplicationContext(), photo.getPhotoName()));
        }

        for (File file : files) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(MEDIA_TYPE, file.getName(), requestFile);
            parts.add(part);
        }

        RequestBody taskIdBody = RequestBody.create(okhttp3.MultipartBody.FORM, taskId);
        RequestBody subTypeIdBody = RequestBody.create(okhttp3.MultipartBody.FORM, "32");
        return cuencaVerdeFilesUploadApi.uploadMinuta(endpoint, taskIdBody, subTypeIdBody, parts)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> uploadPhotosWithHash(String endpoint, String hash, List<PhotographicRegistryPhoto> registryPhotos) {
        final String MEDIA_TYPE = "files[]";
        List<MultipartBody.Part> parts = new ArrayList<>();
        Map<String, String> comments = new HashMap<>();

        ArrayList<File> files = new ArrayList<>();
        for (PhotographicRegistryPhoto photo : registryPhotos) {
            files.add(FilesUtils.getOutputMediaFile(app.getApplicationContext(), photo.getPhotoName()));
            comments.put(photo.getPhotoName(), photo.getComment());
        }

        for (File file : files) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(MEDIA_TYPE, file.getName(), requestFile);
            parts.add(part);
        }

        RequestBody hashBody = RequestBody.create(okhttp3.MultipartBody.FORM, hash);
        RequestBody typeBody = RequestBody.create(okhttp3.MultipartBody.FORM, "0");
        RequestBody commentsBody = RequestBody.create(okhttp3.MultipartBody.FORM, serializationManager.toJson(comments));
        return cuencaVerdeFilesUploadApi.uploadFilesWithHash(endpoint, hashBody, typeBody, commentsBody, parts)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    private File getFile(Uri uri) {
        String path = FileUtils.getPath(app.getApplicationContext(), app.getContentResolverObject(), uri);
        return new File(path);
    }

    @Override
    public Observable<QuotaResponse> getQuota() {
        return cuencaVerdeApi.getQuota(getString(R.string.quota_endpoint))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<SurveyResponse> getSurvey(String taskId) {
        return cuencaVerdeApi.getSurvey(getString(R.string.survey_endpoint), taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<List<ActionResponse>> getAcciones(String type) {
        return cuencaVerdeApi.getAcciones(getString(R.string.acciones_endpoint), type)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<List<MaterialActionResponse>> getAllMaterials() {
        return cuencaVerdeApi.getAllMaterials(getString(R.string.materials_endpoint))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return cuencaVerdeApi.getAllUsers(getString(R.string.users_endpoint))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<List<MonitoringMaintenanceResponse>> getMonitoringAndMaintenance(String procedureId) {
        if (TextUtils.isEmpty(procedureId)) {
            return cuencaVerdeApi.getMonitoringAndMaintenance(getString(R.string.monitoring_maintenance_endpoint))
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io());
        } else {
            return cuencaVerdeApi.getMonitoringAndMaintenanceByProcedureId(getString(R.string.monitoring_maintenance_endpoint), procedureId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io());
        }
    }

    @Override
    public Observable<MonitoringMaintenanceResponse> getMonitoringAndMaintenanceById(String monitorAndMaintenanceId) {
        return cuencaVerdeApi.getMonitoringAndMaintenanceById(getString(R.string.monitoring_maintenance_endpoint), monitorAndMaintenanceId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendMonitorAndMaintenancePoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint, List<PhotographicRegistryPhoto> monitorAndMaintenancePhotos) {
        final String MEDIA_TYPE = "files[]";
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (PhotographicRegistryPhoto monitorAndMaintenancePhoto : monitorAndMaintenancePhotos) {
            File file = FilesUtils.getOutputMediaFile(app.getApplicationContext(), monitorAndMaintenancePhoto.getPhotoName());
            if (file != null) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData(MEDIA_TYPE, file.getName(), requestFile);
                parts.add(part);
            }
        }
        MonitoringMaintenanceRequest monitoringMaintenanceRequest = new MonitoringMaintenanceRequest();
        monitoringMaintenanceRequest.pointId = monitorAndMaintenanceCommentPoint.isFromService() ? monitorAndMaintenanceCommentPoint.getId() : null;
        monitoringMaintenanceRequest.monitorAndMaintenanceId = monitorAndMaintenanceCommentPoint.getMonitorAndMaintenanceId();
        monitoringMaintenanceRequest.comment = monitorAndMaintenanceCommentPoint.getComment();
        monitoringMaintenanceRequest.lat = monitorAndMaintenanceCommentPoint.getLocation().getLatitude();
        monitoringMaintenanceRequest.lng = monitorAndMaintenanceCommentPoint.getLocation().getLongitude();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), serializationManager.toJson(monitoringMaintenanceRequest));
        return cuencaVerdeFilesUploadApi.sendMonitorMaintenance(getString(R.string.monitoring_maintenance_files_endpoint), requestBody, parts)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> endMonitorAndMaintenance(MonitorAndMaintenance monitorAndMaintenance) {
        return cuencaVerdeApi.endMonitoring(getString(R.string.end_monitoring), monitorAndMaintenance.getCleanedId())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(error -> new CuencaVerdeResponse());
    }

    @Override
    public Observable<ResponseBody> getImageFile(String fileName) {
        return cuencaVerdeImagesApi.getImageFile(getString(R.string.images_url), fileName)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendPQRS(PQRS pqrs) {
        Map<String, Serializable> fieldsMap = new HashMap<>();
        fieldsMap.put("card_id", pqrs.getIdNumber());
        fieldsMap.put("contact_name", pqrs.getContactName());
        fieldsMap.put("agreement_corporation", pqrs.isAgreementCorporation() ? 1 : 0);
        fieldsMap.put("subcribe_agreement", pqrs.isSubscribeAgreement() ? 1 : 0);
        fieldsMap.put("property_name", pqrs.getPropertyName());
        fieldsMap.put("description", pqrs.getDescription());
        fieldsMap.put("email", pqrs.getEmail());
        fieldsMap.put("lat", pqrs.getLatitude());
        fieldsMap.put("lng", pqrs.getLongitude());
        fieldsMap.put("role_id", Integer.valueOf(pqrs.getDependency().getId()));
        fieldsMap.put("type_pqrs", Integer.valueOf(pqrs.getPqrsType().getId()));
        cuencaVerdeApi.sendPQRS(getString(R.string.pqrs_endpoint), fieldsMap)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(ServicesUtils::getSurveyResponseException)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getSimpleName(), "Success sending PQRS!"))
                .subscribe(response -> {
                });
    }

    @Override
    public Observable<List<DependencyResponse>> getDependencies() {
        return cuencaVerdeApi.getDependencies(getString(R.string.roles_endpoint))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<List<PQRSTypeResponse>> getPqrsTypes() {
        return cuencaVerdeApi.getPqrsTypes(getString(R.string.pqrs_types_endpoint))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance) {
        return cuencaVerdeApi.sendFillableForm(getString(R.string.form_endpoint), stardMonitorAndMaintenance.getMonitorAndMaintenanceId(), 1, stardMonitorAndMaintenance)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendStardSheetForm(StardSheetForm stardSheetForm, String taskId) {
        return cuencaVerdeApi.sendFillableForm(getString(R.string.form_endpoint), taskId, 9, stardSheetForm)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendContractorForm(ContractorForm contractorForm, String taskId) {
        return cuencaVerdeApi.sendContractorForm(getString(R.string.send_contractor_format_endpoint), taskId, contractorForm)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendSiembraDetailForm(SiembraDetailForm siembraDetailForm, String hash) {
        return cuencaVerdeApi.sendFillableForm(getString(R.string.form_endpoint), hash, 11, siembraDetailForm)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendSiembraDetailForms(SiembraDetailsRequest siembraDetailsRequest) {
        return cuencaVerdeApi.sendSiembraDetailForms(getString(R.string.send_contractor_siembras_format_endpoint), siembraDetailsRequest, siembraDetailsRequest.taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendRecursoHidricoForms(RecursoHidricoRequest recursoHidricoRequest) {
        return cuencaVerdeApi.sendRecursoHidricoForms(getString(R.string.open_task_with_forms_endpoint), recursoHidricoRequest, recursoHidricoRequest.taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendErosiveProcessForms(ErosiveProcessRequest erosiveProcessRequest) {
        return cuencaVerdeApi.sendErosiveProcessForms(getString(R.string.open_task_with_forms_endpoint), erosiveProcessRequest, erosiveProcessRequest.taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendMaintenanceControl(MaintenanceControl maintenanceControl, String monitorAndMaintenanceId) {
        return cuencaVerdeApi.sendFillableForm(getString(R.string.form_endpoint), monitorAndMaintenanceId, 3, maintenanceControl)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<StardMonitorAndMaintenance> getStardMonitorAndMaintenance(StardMonitorAndMaintenanceFormResponse stardMonitorAndMaintenanceFormResponse) {
        return cuencaVerdeApi.getFillableForm(getString(R.string.form_endpoint), stardMonitorAndMaintenanceFormResponse.id, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(ServicesUtils::getStardMonitoringMaintenanceFormResponseException)
                .doOnError(onError -> Log.e(getClass().getSimpleName(), onError.getMessage()))
                .flatMap(response -> {
                    StardMonitorAndMaintenance stardMonitorAndMaintenance = new StardMonitorAndMaintenance();
                    if (response.data != null) {
                        stardMonitorAndMaintenance = (StardMonitorAndMaintenance) serializationManager.fromJson(response.data, StardMonitorAndMaintenance.class);
                    }
                    stardMonitorAndMaintenance.setMonitorAndMaintenanceId(stardMonitorAndMaintenanceFormResponse.id);
                    stardMonitorAndMaintenance.setPropertyName(stardMonitorAndMaintenanceFormResponse.propertyName);
                    stardMonitorAndMaintenance.setMunicipality(stardMonitorAndMaintenanceFormResponse.municipality);
                    stardMonitorAndMaintenance.setLane(stardMonitorAndMaintenanceFormResponse.lane);
                    stardMonitorAndMaintenance.setContact(MapperUtils.contactResponseToContact(stardMonitorAndMaintenanceFormResponse.contact));
                    stardMonitorAndMaintenance.setLocation(MapperUtils.coordinateResponseToLocation(stardMonitorAndMaintenanceFormResponse.coordinate));
                    return Observable.just(stardMonitorAndMaintenance);
                });
    }

    @Override
    public Observable<StardMonitorAndMaintenanceFormResponse> getStardMonitorAndMaintenanceForm(String monitorAndMaintenanceId) {
        return cuencaVerdeApi.getStardMonitorAndMaintenanceForm(getString(R.string.stard_monitor_and_maintenance_form_endpoint), monitorAndMaintenanceId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(ServicesUtils::getStardMonitoringMaintenanceFormResponseException);
    }

    @Override
    public Observable<CuencaVerdeResponse> sendVegetalMaintenance(VegetalMaintenance vegetalMaintenance) {
        return cuencaVerdeApi.sendFillableForm(getString(R.string.form_endpoint), vegetalMaintenance.getMonitorAndMaintenanceId(), 3, vegetalMaintenance)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> getVegetalMaintenance(String monitorAndMaintenanceId) {
        return cuencaVerdeApi.getFillableForm(getString(R.string.form_endpoint), monitorAndMaintenanceId, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendPredioFollowUp(PredioFollowUp predioFollowUp) {
        return cuencaVerdeApi.sendFillableForm(getString(R.string.form_endpoint), predioFollowUp.getMonitorAndMaintenanceId(), 3, predioFollowUp)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> getPredioFollowUp(String monitorAndMaintenanceId) {
        return cuencaVerdeApi.getFillableForm(getString(R.string.form_endpoint), monitorAndMaintenanceId, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendContractorEvaluation(ContractorEvaluation contractorEvaluation) {
        return cuencaVerdeApi.sendFillableForm(getString(R.string.form_endpoint), contractorEvaluation.getMonitorAndMaintenanceId(), 3, contractorEvaluation)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendMeetingsWithActorsForm(MeetingsWithActorsForm meetingsWithActorsForm, String taskId) {
        MeetingsWithActorsFormRequest meetingsWithActorsFormRequest = new MeetingsWithActorsFormRequest();
        meetingsWithActorsFormRequest.taskId = taskId;
        meetingsWithActorsFormRequest.municipality = meetingsWithActorsForm.getMunicipality();
        meetingsWithActorsFormRequest.basin = meetingsWithActorsForm.getBasin();
        meetingsWithActorsFormRequest.sidewalk = meetingsWithActorsForm.getLane();
        meetingsWithActorsFormRequest.objectiveGroup = meetingsWithActorsForm.getObjectiveGroup();
        if (meetingsWithActorsForm.getEventDate() != null) {
            meetingsWithActorsFormRequest.date = meetingsWithActorsForm.getEventDate().getString();
        }
        meetingsWithActorsFormRequest.associatedName = meetingsWithActorsForm.getContributor();
        meetingsWithActorsFormRequest.associatedId = null;
        meetingsWithActorsFormRequest.numberAttendees = meetingsWithActorsForm.getAssistants();
        meetingsWithActorsFormRequest.numberTrees = meetingsWithActorsForm.getTrees();
        meetingsWithActorsFormRequest.experenceType = meetingsWithActorsForm.getExperienceType();
        meetingsWithActorsFormRequest.experenceConsolidated = meetingsWithActorsForm.getExperienceConsolidated();

        meetingsWithActorsFormRequest.experience = meetingsWithActorsForm.isExperience();
        meetingsWithActorsFormRequest.workshop = meetingsWithActorsForm.isWorkshop();
        meetingsWithActorsFormRequest.sowing = meetingsWithActorsForm.isSowing();
        meetingsWithActorsFormRequest.training = meetingsWithActorsForm.isTraining();

        meetingsWithActorsFormRequest.eventName = meetingsWithActorsForm.getEventDescription();
        meetingsWithActorsFormRequest.asistentList = meetingsWithActorsForm.isAssistanceList() ? "Sí" : "No";
        meetingsWithActorsFormRequest.registryPhotographic = null;
        meetingsWithActorsFormRequest.type = 1;

        return cuencaVerdeApi.sendMeetingsWithActorsForm(getString(R.string.send_meeting_with_actors_format_endpoint), meetingsWithActorsFormRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> getContractorEvaluation(String monitorAndMaintenanceId) {
        return cuencaVerdeApi.getFillableForm(getString(R.string.form_endpoint), monitorAndMaintenanceId, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<GeoJson> getGeoJson(String taskId, String endpoint) {
        return cuencaVerdeApi.getGeoJson(endpoint, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public void sendOneSignalId(String userId) {
        Map<String, Serializable> fieldsMap = new HashMap<>();
        fieldsMap.put("player_id", userId);
        Log.d(getClass().getSimpleName(), "OneSignalId: " + userId);
        cuencaVerdeApi.sendOneSignalId(getString(R.string.one_signal_endpoint), fieldsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(error -> new CuencaVerdeResponse())
                .doOnError(throwable -> Log.e(getClass().getSimpleName(), throwable.getMessage()))
                .doOnComplete(() -> {
                })
                .subscribe();
    }

    @Override
    public Observable<List<ProvinceResponse>> getProvinces() {
        return cuencaVerdeApi.getProvinces(getString(R.string.provinces_endpoint))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<ResponseBody> getPrediosManagementLayer() {
        return cuencaVerdeApi.getPrediosManagementLayer(getString(R.string.predios_management_layer), "data.json")
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread());
    }

    @Override
    public Observable<List<CroquisResponse>> getPredioCroquis(String predioId) {
        return cuencaVerdeApi.getPredioCroquis(getString(R.string.croquis_endpoint), String.valueOf(predioId))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<List<MunicipalityResponse>> getMunicipalities(int provinceId) {
        return cuencaVerdeApi.getMunicipalities(getString(R.string.municipalities_endpoint), String.valueOf(provinceId))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<List<PredioPotencialResponse>> getPrediosPotenciales() {
        return cuencaVerdeApi.getPrediosPotenciales(getString(R.string.predios_potenciales_endpoint), 1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CartaAndSurveyResponse> getCartasAndSurveys(String predioPotencialId) {
        return cuencaVerdeApi.getCartasAndSurveys(getString(R.string.survey_carta_endpoint), predioPotencialId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<CuencaVerdeResponse> sendContractSiembra(ContractSiembra contractSiembra) {
        return cuencaVerdeApi.sendContractSiembra(getString(R.string.contract_siembra_endpoint), contractSiembra.getId(), contractSiembra)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<GeoJson> getContractSiembraGeoJson(String contractSiembraId) {
        return cuencaVerdeApi.getContractSiembra(getString(R.string.contract_siembra_endpoint), contractSiembraId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(ServicesUtils::getContractSiembraResponseException)
                .doOnError(throwable -> Log.e(getClass().getSimpleName(), throwable.getMessage()));
    }

    @Override
    public Observable<CuencaVerdeResponse> endExecutionTask(String taskId) {
        Map<String, String> fieldsMap = new HashMap<>();
        fieldsMap.put("task_id", taskId);
        return cuencaVerdeApi.endExecutionTask(getString(R.string.end_execution_task), fieldsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(error -> new CuencaVerdeResponse());
    }

    @Override
    public Observable<CuencaVerdeResponse> endContractorTask(String taskId) {
        Map<String, String> fieldsMap = new HashMap<>();
        fieldsMap.put("task_id", taskId);
        return cuencaVerdeApi.endContractorTask(getString(R.string.end_contractor_task), fieldsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(error -> new CuencaVerdeResponse());
    }

    @Override
    public Observable<CuencaVerdeResponse> endCommunicationsTask(String taskId) {
        Map<String, String> fieldsMap = new HashMap<>();
        fieldsMap.put("task_id", taskId);
        return cuencaVerdeApi.endContractorTask(getString(R.string.end_communications_task), fieldsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(error -> new CuencaVerdeResponse());
    }

    @Override
    public Observable<CuencaVerdeResponse> endPsaTask(String taskId) {
        Map<String, String> fieldsMap = new HashMap<>();
        fieldsMap.put("task_id", taskId);
        return cuencaVerdeApi.endContractorTask(getString(R.string.end_psa_task), fieldsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(error -> new CuencaVerdeResponse());
    }

    @Override
    public Observable<CuencaVerdeResponse> endSpecialTask(String taskId) {
        Map<String, String> fieldsMap = new HashMap<>();
        fieldsMap.put("task_id", taskId);
        return cuencaVerdeApi.endContractorTask(getString(R.string.end_communications_task), fieldsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(error -> new CuencaVerdeResponse());
    }

    @Override
    public Observable<CuencaVerdeResponse> endMeasurementTask(String taskId) {
        Map<String, String> fieldsMap = new HashMap<>();
        fieldsMap.put("task_id", taskId);
        return cuencaVerdeApi.endMeasurementTask(getString(R.string.end_measurement_task), fieldsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(error -> new CuencaVerdeResponse());
    }

    @Override
    public Observable<CuencaVerdeResponse> endTaskNoMap(String taskId) {
        Map<String, String> fieldsMap = new HashMap<>();
        fieldsMap.put("task_id", taskId);
        return cuencaVerdeApi.endMeasurementTask(getString(R.string.end_task_no_map), fieldsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(error -> new CuencaVerdeResponse());
    }

    @Override
    public Observable<List<RoleResponse>> getRole() {
        return cuencaVerdeApi.getRole(getString(R.string.user_role_endpoint))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(ServicesUtils::getRoleResponseException)
                .doOnError(throwable -> Log.e(getClass().getSimpleName(), throwable.getMessage()));
    }

    @Override
    public Observable<List<CroquisResponse>> getPredioCroquisFromCoordinates(LatLng coordinates) {
        return croquisApi.getCroquis(getString(R.string.croquis_from_coordinates_endpoint), coordinates.latitude, coordinates.longitude, 250)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(ServicesUtils::getCroquisResponseException)
                .doOnError(throwable -> Log.e(getClass().getSimpleName(), throwable.getMessage()));
    }

    @Override
    public Observable<CuencaVerdeResponse> sendOpenTaskComment(OpenTaskCommentRequest openTaskCommentRequest) {
        return cuencaVerdeApi.sendOpenTaskComment(getString(R.string.open_task_comment), openTaskCommentRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorReturn(error -> new CuencaVerdeResponse());
    }
}
