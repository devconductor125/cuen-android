package com.orquitech.development.cuencaVerde.data.managers;

import android.net.Uri;
import android.os.Bundle;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.orquitech.development.cuencaVerde.data.api.model.SendSurveyCarta.post.SendSurveyCartaResponse;
import com.orquitech.development.cuencaVerde.data.api.model.acciones.get.ActionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.cartaAndSurvey.get.CartaAndSurveyResponse;
import com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.CartaIntencionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.contractor.post.SiembraDetailsRequest;
import com.orquitech.development.cuencaVerde.data.api.model.croquis.get.CroquisResponse;
import com.orquitech.development.cuencaVerde.data.api.model.dependencies.get.DependencyResponse;
import com.orquitech.development.cuencaVerde.data.api.model.erosiveProcess.post.ErosiveProcessRequest;
import com.orquitech.development.cuencaVerde.data.api.model.logIn.LogInResponse;
import com.orquitech.development.cuencaVerde.data.api.model.material.get.MaterialActionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get.MonitoringMaintenanceResponse;
import com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.User;
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
import com.orquitech.development.cuencaVerde.data.model.ContractSiembra;
import com.orquitech.development.cuencaVerde.data.model.ContractorEvaluation;
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
import com.google.android.gms.maps.model.LatLng;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface ApiManager {

    Observable<Bundle> getUploadProgressObservable();

    void initApi(String accessToken);

    Observable<LogInResponse> logInUser(String username, String password);

    Observable<List<ProcedureResponse>> getProcedures();

    Observable<List<TaskResponse>> getTasks(String processId);

    Observable<List<OpenTaskResponse>> getOpenTasks(String processId);

    Observable<List<OpenTaskResponse>> getOpenContractorTasks(String processId);

    Observable<List<ExecutionTaskResponse>> getExecutionTasks(String processId);

    Observable<List<TaskCommentResponse>> getTaskComments(Task task);

    Observable<List<ProgramResponse>> getPrograms();

    Observable<List<CartaIntencionResponse>> getCartasIntencion(String procedureId);

    Observable<CartaIntencionResponse> getCartaIntencion(String cartaIntencionId);

    Observable<List<TaskResponse>> getDueTasks();

    Observable<SendSurveyCartaResponse> sendSurvey(String taskId, Survey survey);

    Observable<SendSurveyCartaResponse> sendCartaIntencion(String predioPotencialId, CartaIntencion cartaIntencion, List<PhotographicRegistryPhoto> files);

    Observable<CuencaVerdeResponse> sendMap(Task task, GeoJson geoJson);

    Observable<CuencaVerdeResponse> sendValidationMap(Task task, GeoJson geoJson);

    Observable<CuencaVerdeResponse> uploadFilesFromUris(String endpoint, String taskId, List<Uri> fileUris);

    Observable<PredioPotencialResponse> sendPredioPotencial(PredioPotencial predioPotencial);

    Observable<CuencaVerdeResponse> uploadPhotos(String endpoint, String taskId, List<PhotographicRegistryPhoto> files);

    Observable<CuencaVerdeResponse> uploadMinuta(String endpoint, String taskId, List<PhotographicRegistryPhoto> files);

    Observable<CuencaVerdeResponse> uploadPhotosWithHash(String endpoint, String hash, List<PhotographicRegistryPhoto> files);

    Observable<QuotaResponse> getQuota();

    Observable<SurveyResponse> getSurvey(String taskId);

    Observable<List<ActionResponse>> getAcciones(String type);

    Observable<List<MaterialActionResponse>> getAllMaterials();

    Observable<List<User>> getAllUsers();

    Observable<List<MonitoringMaintenanceResponse>> getMonitoringAndMaintenance(String procedureId);

    Observable<MonitoringMaintenanceResponse> getMonitoringAndMaintenanceById(String monitorAndMaintenanceId);

    Observable<CuencaVerdeResponse> sendMonitorAndMaintenancePoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint, List<PhotographicRegistryPhoto> monitorAndMaintenancePhotos);

    Observable<CuencaVerdeResponse> endMonitorAndMaintenance(MonitorAndMaintenance monitorAndMaintenance);

    Observable<ResponseBody> getImageFile(String fileName);

    void sendPQRS(PQRS pqrs);

    Observable<List<DependencyResponse>> getDependencies();

    Observable<List<PQRSTypeResponse>> getPqrsTypes();

    Observable<CuencaVerdeResponse> sendStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance);

    Observable<CuencaVerdeResponse> sendStardSheetForm(StardSheetForm stardSheetForm, String taskId);

    Observable<CuencaVerdeResponse> sendContractorForm(ContractorForm contractorForm, String contractorSiembraId);

    Observable<CuencaVerdeResponse> sendSiembraDetailForm(SiembraDetailForm contractorForm, String hash);

    Observable<CuencaVerdeResponse> sendSiembraDetailForms(SiembraDetailsRequest siembraDetailsRequest);

    Observable<CuencaVerdeResponse> sendMaintenanceControl(MaintenanceControl maintenanceControl, String monitorAndMaintenanceId);

    Observable<StardMonitorAndMaintenance> getStardMonitorAndMaintenance(StardMonitorAndMaintenanceFormResponse stardMonitorAndMaintenanceFormResponse);

    Observable<StardMonitorAndMaintenanceFormResponse> getStardMonitorAndMaintenanceForm(String monitorAndMaintenanceId);

    Observable<CuencaVerdeResponse> sendVegetalMaintenance(VegetalMaintenance vegetalMaintenance);

    Observable<CuencaVerdeResponse> getVegetalMaintenance(String monitorAndMaintenanceId);

    Observable<CuencaVerdeResponse> sendPredioFollowUp(PredioFollowUp predioFollowUp);

    Observable<CuencaVerdeResponse> getPredioFollowUp(String monitorAndMaintenanceId);

    Observable<CuencaVerdeResponse> sendContractorEvaluation(ContractorEvaluation contractorEvaluation);

    Observable<CuencaVerdeResponse> getContractorEvaluation(String monitorAndMaintenanceId);

    Observable<GeoJson> getGeoJson(String taskId, String endpoint);

    void sendOneSignalId(String userId);

    Observable<List<ProvinceResponse>> getProvinces();

    Observable<ResponseBody> getPrediosManagementLayer();

    Observable<List<CroquisResponse>> getPredioCroquis(String taskId);

    Observable<List<MunicipalityResponse>> getMunicipalities(int provinceId);

    Observable<List<PredioPotencialResponse>> getPrediosPotenciales();

    Observable<CartaAndSurveyResponse> getCartasAndSurveys(String predioPotencialId);

    Observable<CuencaVerdeResponse> sendContractSiembra(ContractSiembra contractSiembra);

    Observable<GeoJson> getContractSiembraGeoJson(String contractSiembraId);

    Observable<CuencaVerdeResponse> endExecutionTask(String taskId);

    Observable<CuencaVerdeResponse> endContractorTask(String taskId);

    Observable<CuencaVerdeResponse> endCommunicationsTask(String taskId);

    Observable<CuencaVerdeResponse> endPsaTask(String taskId);

    Observable<CuencaVerdeResponse> endSpecialTask(String taskId);

    Observable<CuencaVerdeResponse> endMeasurementTask(String taskId);

    Observable<CuencaVerdeResponse> endTaskNoMap(String taskId);

    Observable<CuencaVerdeResponse> sendMeetingsWithActorsForm(MeetingsWithActorsForm meetingsWithActorsForm, String taskId);

    Observable<List<RoleResponse>> getRole();

    Observable<List<CroquisResponse>> getPredioCroquisFromCoordinates(LatLng coordinates);

    Observable<CuencaVerdeResponse> sendRecursoHidricoForms(RecursoHidricoRequest recursoHidricoRequest);

    Observable<CuencaVerdeResponse> sendErosiveProcessForms(ErosiveProcessRequest erosiveProcessRequest);

    Observable<CuencaVerdeResponse> sendOpenTaskComment(OpenTaskCommentRequest openTaskCommentRequest);
}
