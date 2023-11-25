package com.orquitech.development.cuencaVerde.data.api.services;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.orquitech.development.cuencaVerde.data.api.model.SendSurveyCarta.post.SendSurveyCartaResponse;
import com.orquitech.development.cuencaVerde.data.api.model.acciones.get.ActionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.cartaAndSurvey.get.CartaAndSurveyResponse;
import com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.CartaIntencionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.User;
import com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.post.CartaIntencionRequest;
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
import com.orquitech.development.cuencaVerde.data.api.model.tasks.post.ExecutionTaskRequest;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.post.OpenTaskCommentRequest;
import com.orquitech.development.cuencaVerde.data.model.ContractSiembra;
import com.orquitech.development.cuencaVerde.data.model.ContractorEvaluation;
import com.orquitech.development.cuencaVerde.data.model.MaintenanceControl;
import com.orquitech.development.cuencaVerde.data.model.PredioFollowUp;
import com.orquitech.development.cuencaVerde.data.model.contractorForm.ContractorForm;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.model.siembraDetailForm.SiembraDetailForm;
import com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance.StardMonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.stardSheetForm.StardSheetForm;
import com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance.VegetalMaintenance;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface CuencaVerdeApi {

    @POST("{url}")
    Observable<LogInResponse> logIn(@Path(value = "url", encoded = true) String url, @Body LogInRequest logInRequest);

    @POST("{url}")
    Observable<CuencaVerdeResponse> sendExecutionMap(@Path(value = "url", encoded = true) String url, @Body ExecutionTaskRequest executionTaskRequest);

    @GET("{url}/{monitoringId}")
    Observable<CuencaVerdeResponse> endMonitoring(@Path(value = "url", encoded = true) String url, @Path("monitoringId") String monitoringId);

    @FormUrlEncoded
    @POST("{url}")
    Observable<CuencaVerdeResponse> endExecutionTask(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, String> fieldsMap);

    @FormUrlEncoded
    @POST("{url}")
    Observable<CuencaVerdeResponse> endContractorTask(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, String> fieldsMap);

    @FormUrlEncoded
    @POST("{url}")
    Observable<CuencaVerdeResponse> endMeasurementTask(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, String> fieldsMap);

    @Multipart
    @POST("{url}")
    Observable<CuencaVerdeResponse> uploadFiles(@Path(value = "url", encoded = true) String url, @Part("task_id") RequestBody description, @Part List<MultipartBody.Part> files);

    @Multipart
    @POST("{url}")
    Observable<CuencaVerdeResponse> uploadMinuta(@Path(value = "url", encoded = true) String url, @Part("task_id") RequestBody taskId, @Part("subType_id") RequestBody subTypeId, @Part List<MultipartBody.Part> files);

    @Multipart
    @POST("{url}")
    Observable<CuencaVerdeResponse> uploadFiles(@Path(value = "url", encoded = true) String url, @Part("task_id") RequestBody taskIdBody, @Part("type") RequestBody typeBody, @Part("comments") RequestBody comments, @Part List<MultipartBody.Part> files);

    @Multipart
    @POST("{url}")
    Observable<CuencaVerdeResponse> uploadFilesWithHash(@Path(value = "url", encoded = true) String url, @Part("hash") RequestBody hash, @Part("type") RequestBody type, @Part("comments") RequestBody comments, @Part List<MultipartBody.Part> files);

    @Multipart
    @POST("{url}")
    Observable<CuencaVerdeResponse> sendMonitorMaintenance(@Path(value = "url", encoded = true) String url, @Part("monitor_maintenance") RequestBody description, @Part List<MultipartBody.Part> files);

    @GET("{url}")
    Observable<List<ProcedureResponse>> getProcedures(@Path(value = "url", encoded = true) String url);

    @GET("{url}")
    Observable<List<RoleResponse>> getRole(@Path(value = "url", encoded = true) String url);

    @GET("{url}")
    Observable<List<CartaIntencionResponse>> getCartasIntencion(@Path(value = "url", encoded = true) String url);

    @GET("{url}/{procedureId}")
    Observable<List<CartaIntencionResponse>> getCartasIntencionByProcedureId(@Path(value = "url", encoded = true) String url, @Path("procedureId") String procedureId);

    @GET("{url}/{id}")
    Observable<CartaIntencionResponse> getCartaIntencion(@Path(value = "url", encoded = true) String url, @Path("id") String procedureId);

    @GET("{url}")
    Observable<List<TaskResponse>> getAllTasks(@Path(value = "url", encoded = true) String url);

    @GET("{url}")
    Observable<List<ProgramResponse>> getPrograms(@Path(value = "url", encoded = true) String url);

    @GET("{url}/{id}")
    Observable<List<TaskResponse>> getTasks(@Path(value = "url", encoded = true) String url, @Path("id") String procedureId);

    @GET("{url}/{taskId}/{type}")
    Observable<List<TaskCommentResponse>> getTaskComments(@Path(value = "url", encoded = true) String url, @Path("taskId") String taskId, @Path("type") String type);

    @GET("{url}")
    Observable<List<TaskResponse>> getDueTasks(@Path(value = "url", encoded = true) String url);

    @FormUrlEncoded
    @POST("{url}")
    Observable<SendSurveyCartaResponse> sendSurvey(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, String> fieldsMap);

    @POST("{url}")
    Observable<CuencaVerdeResponse> sendMap(@Path(value = "url", encoded = true) String url, @Body MapRequest mapRequest);

    @FormUrlEncoded
    @POST("{url}")
    Observable<PredioPotencialResponse> sendPredioPotencial(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, String> fieldsMap);

    @GET("{url}")
    Observable<QuotaResponse> getQuota(@Path(value = "url", encoded = true) String url);

    @GET("{url}/{id}")
    Observable<SurveyResponse> getSurvey(@Path(value = "url", encoded = true) String url, @Path("id") String taskId);

    @GET("{url}/{type}")
    Observable<List<ActionResponse>> getAcciones(@Path(value = "url", encoded = true) String url, @Path("type") String type);

    @GET("{url}")
    Observable<List<MaterialActionResponse>> getAllMaterials(@Path(value = "url", encoded = true) String url);

    @GET("{url}")
    Observable<List<User>> getAllUsers(@Path(value = "url", encoded = true) String url);

    @GET("{url}")
    Observable<List<MonitoringMaintenanceResponse>> getMonitoringAndMaintenance(@Path(value = "url", encoded = true) String url);

    @GET("{url}/{procedureId}")
    Observable<List<MonitoringMaintenanceResponse>> getMonitoringAndMaintenanceByProcedureId(@Path(value = "url", encoded = true) String url, @Path("procedureId") String procedureId);

    @GET("{url}/{id}")
    Observable<MonitoringMaintenanceResponse> getMonitoringAndMaintenanceById(@Path(value = "url", encoded = true) String url, @Path("id") String monitorAndMaintenanceId);

    @POST("{url}/{taskId}")
    Observable<CuencaVerdeResponse> sendContractorForm(@Path(value = "url", encoded = true) String url, @Path("taskId") String taskId, @Body ContractorForm contractorForm);

    @POST("{url}/{monitorAndMaintenanceId}/{formId}")
    Observable<CuencaVerdeResponse> sendFillableForm(@Path(value = "url", encoded = true) String url, @Path("monitorAndMaintenanceId") String monitorAndMaintenanceId, @Path("formId") int formId, @Body StardMonitorAndMaintenance stardMonitorAndMaintenance);

    @POST("{url}/{monitorAndMaintenanceId}/{formId}")
    Observable<CuencaVerdeResponse> sendFillableForm(@Path(value = "url", encoded = true) String url, @Path("monitorAndMaintenanceId") String monitorAndMaintenanceId, @Path("formId") int formId, @Body VegetalMaintenance vegetalMaintenance);

    @POST("{url}/{taskId}/{formId}")
    Observable<CuencaVerdeResponse> sendFillableForm(@Path(value = "url", encoded = true) String url, @Path("taskId") String taskId, @Path("formId") int formId, @Body StardSheetForm stardSheetForm);

    @POST("{url}/{taskId}/{formId}")
    Observable<CuencaVerdeResponse> sendFillableForm(@Path(value = "url", encoded = true) String url, @Path("taskId") String taskId, @Path("formId") int formId, @Body SiembraDetailForm siembraDetailForm);

    @POST("{url}/{contractSiembraId}")
    Observable<CuencaVerdeResponse> sendContractSiembra(@Path(value = "url", encoded = true) String url, @Path("contractSiembraId") String contractSiembraId, @Body ContractSiembra contractSiembra);

    @GET("{url}/{contractSiembraId}")
    Observable<GeoJson> getContractSiembra(@Path(value = "url", encoded = true) String url, @Path("contractSiembraId") String contractSiembraId);

    @POST("{url}/{monitorAndMaintenanceId}/{formId}")
    Observable<CuencaVerdeResponse> sendFillableForm(@Path(value = "url", encoded = true) String url, @Path("monitorAndMaintenanceId") String taskId, @Path("formId") int monitorAndMaintenanceId, @Body MaintenanceControl maintenanceControl);

    @POST("{url}/{monitorAndMaintenanceId}/{formId}")
    Observable<CuencaVerdeResponse> sendFillableForm(@Path(value = "url", encoded = true) String url, @Path("monitorAndMaintenanceId") String monitorAndMaintenanceId, @Path("formId") int formId, @Body PredioFollowUp predioFollowUp);

    @POST("{url}/{monitorAndMaintenanceId}/{formId}")
    Observable<CuencaVerdeResponse> sendFillableForm(@Path(value = "url", encoded = true) String url, @Path("monitorAndMaintenanceId") String monitorAndMaintenanceId, @Path("formId") int formId, @Body ContractorEvaluation contractorEvaluation);

    @GET("{url}/{monitorAndMaintenanceId}/{formId}")
    Observable<CuencaVerdeResponse> getFillableForm(@Path(value = "url", encoded = true) String url, @Path("monitorAndMaintenanceId") String monitorAndMaintenanceId, @Path("formId") int formId);

    @GET("{url}/{monitorAndMaintenanceId}")
    Observable<StardMonitorAndMaintenanceFormResponse> getStardMonitorAndMaintenanceForm(@Path(value = "url", encoded = true) String url, @Path("monitorAndMaintenanceId") String monitorAndMaintenanceId);

    @GET("{url}/{taskId}")
    Observable<GeoJson> getGeoJson(@Path(value = "url", encoded = true) String url, @Path("taskId") String taskId);

    @GET("{url}/{fileName}")
    @Streaming
    Observable<ResponseBody> getImageFile(@Path(value = "url", encoded = true) String url, @Path("fileName") String fileName);

    @FormUrlEncoded
    @POST("{url}")
    Observable<CuencaVerdeResponse> sendPQRS(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, Serializable> fieldsMap);

    @GET("{url}")
    Observable<List<DependencyResponse>> getDependencies(@Path(value = "url", encoded = true) String url);

    @GET("{url}")
    Observable<List<PQRSTypeResponse>> getPqrsTypes(@Path(value = "url", encoded = true) String url);

    @FormUrlEncoded
    @POST("{url}")
    Observable<CuencaVerdeResponse> sendOneSignalId(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, Serializable> fieldsMap);

    @Multipart
    @POST("{url}")
    Observable<SendSurveyCartaResponse> sendCartaIntencion(@Path(value = "url", encoded = true) String url, @Part("form_letter") RequestBody letterBody, @Part("potential_id") RequestBody idBody, @Part("type") RequestBody typeBody, @Part("comments") RequestBody comments, @Part List<MultipartBody.Part> files);

    @GET("{url}")
    Observable<List<ProvinceResponse>> getProvinces(@Path(value = "url", encoded = true) String url);

    @GET("{url}/{provinceId}")
    Observable<List<MunicipalityResponse>> getMunicipalities(@Path(value = "url", encoded = true) String url, @Path("provinceId") String provinceId);

    @GET("{url}/{type}")
    Observable<List<PredioPotencialResponse>> getPrediosPotenciales(@Path(value = "url", encoded = true) String url, @Path("type") int type);

    @GET("{url}/{fileName}")
    @Streaming
    Observable<ResponseBody> getPrediosManagementLayer(@Path(value = "url", encoded = true) String url, @Path("fileName") String fileName);

    @GET("{url}/{predioPotencialId}")
    Observable<CartaAndSurveyResponse> getCartasAndSurveys(@Path(value = "url", encoded = true) String url, @Path("predioPotencialId") String predioPotencialId);

    @GET("{url}/{taskId}")
    Observable<List<CroquisResponse>> getPredioCroquis(@Path(value = "url", encoded = true) String url, @Path("taskId") String taskId);

    @GET("{url}")
    Observable<List<OpenTaskResponse>> getAllOpenTasks(@Path(value = "url", encoded = true) String url);

    @GET("{url}/{id}")
    Observable<List<OpenTaskResponse>> getOpenTasks(@Path(value = "url", encoded = true) String url, @Path("id") String procedureId);

    @GET("{url}")
    Observable<List<OpenTaskResponse>> getAllOpenContractorTasks(@Path(value = "url", encoded = true) String url);

    @GET("{url}/{id}")
    Observable<List<OpenTaskResponse>> getOpenContractorTasks(@Path(value = "url", encoded = true) String url, @Path("id") String procedureId);

    @GET("{url}")
    Observable<List<ExecutionTaskResponse>> getAllExecutionTasks(@Path(value = "url", encoded = true) String url);

    @GET("{url}/{id}")
    Observable<List<ExecutionTaskResponse>> getExecutionTasks(@Path(value = "url", encoded = true) String url, @Path("id") String procedureId);

    @POST("{url}/{task_id}")
    Observable<CuencaVerdeResponse> sendSiembraDetailForms(@Path(value = "url", encoded = true) String url, @Body SiembraDetailsRequest siembraDetailsRequest, @Path("task_id") String taskId);

    @POST("{url}/{task_id}")
    Observable<CuencaVerdeResponse> sendRecursoHidricoForms(@Path(value = "url", encoded = true) String url, @Body RecursoHidricoRequest recursoHidricoRequest, @Path("task_id") String taskId);

    @POST("{url}/{task_id}")
    Observable<CuencaVerdeResponse> sendErosiveProcessForms(@Path(value = "url", encoded = true) String url, @Body ErosiveProcessRequest erosiveProcessRequest, @Path("task_id") String taskId);

    @POST("{url}")
    Observable<CuencaVerdeResponse> sendMeetingsWithActorsForm(@Path(value = "url", encoded = true) String url, @Body MeetingsWithActorsFormRequest meetingsWithActorsFormRequest);

    @POST("{url}")
    Observable<CuencaVerdeResponse> sendOpenTaskComment(@Path(value = "url", encoded = true) String url, @Body OpenTaskCommentRequest openTaskCommentRequest);
}
