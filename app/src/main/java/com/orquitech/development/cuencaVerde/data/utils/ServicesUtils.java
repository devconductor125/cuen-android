package com.orquitech.development.cuencaVerde.data.utils;

import android.util.Log;

import com.orquitech.development.cuencaVerde.data.api.cuencaVerdeApi.ApiConfigDevelopment;
import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.orquitech.development.cuencaVerde.data.api.model.SendSurveyCarta.post.SendSurveyCartaResponse;
import com.orquitech.development.cuencaVerde.data.api.model.acciones.get.ActionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.cartaAndSurvey.get.CartaAndSurveyResponse;
import com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.CartaIntencionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.croquis.get.CroquisResponse;
import com.orquitech.development.cuencaVerde.data.api.model.dependencies.get.DependencyResponse;
import com.orquitech.development.cuencaVerde.data.api.model.logIn.LogInResponse;
import com.orquitech.development.cuencaVerde.data.api.model.material.get.MaterialActionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get.MonitoringMaintenanceResponse;
import com.orquitech.development.cuencaVerde.data.api.model.municipalities.get.MunicipalityResponse;
import com.orquitech.development.cuencaVerde.data.api.model.pqrsTypes.get.PQRSTypeResponse;
import com.orquitech.development.cuencaVerde.data.api.model.prediosManagement.get.PrediosManagementResponse;
import com.orquitech.development.cuencaVerde.data.api.model.prediosPotenciales.get.PredioPotencialResponse;
import com.orquitech.development.cuencaVerde.data.api.model.programs.get.ProgramResponse;
import com.orquitech.development.cuencaVerde.data.api.model.projects.get.response.ProcedureResponse;
import com.orquitech.development.cuencaVerde.data.api.model.province.get.ProvinceResponse;
import com.orquitech.development.cuencaVerde.data.api.model.quota.get.QuotaResponse;
import com.orquitech.development.cuencaVerde.data.api.model.role.RoleResponse;
import com.orquitech.development.cuencaVerde.data.api.model.stardMonitorAndMaintenanceForm.get.StardMonitorAndMaintenanceFormResponse;
import com.orquitech.development.cuencaVerde.data.api.model.survey.get.SurveyResponse;
import com.orquitech.development.cuencaVerde.data.api.model.taskComments.get.TaskCommentResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.ExecutionTaskResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.OpenTaskResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.TaskResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.post.ExecutionTaskRequest;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.google.gson.stream.MalformedJsonException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.HttpException;

public class ServicesUtils {

    private static void handleResponse(Throwable throwable, CuencaVerdeResponse response) {
        if (throwable instanceof ConnectException ||
                throwable instanceof SocketTimeoutException ||
                throwable instanceof MalformedJsonException) {
            response.setCode(ApiConfigDevelopment.CONNECT_EXCEPTION);
        } else if (throwable instanceof HttpException) {
            if (((HttpException) throwable).code() == ApiConfigDevelopment.UNAUTHORIZED) {
                response.setCode(ApiConfigDevelopment.UNAUTHORIZED);
            } else {
                response.setCode(ApiConfigDevelopment.CONNECT_EXCEPTION);
            }
        } else {
            response.setCode(ApiConfigDevelopment.BAD_RESPONSE);
        }
    }

    public static LogInResponse getLogInResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        LogInResponse response = new LogInResponse();
        handleResponse(throwable, response);
        return response;
    }

    public static List<RoleResponse> getRoleResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        RoleResponse response = new RoleResponse();
        ArrayList<RoleResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        handleResponse(throwable, response);
        return responsesList;
    }

    public static GeoJson getContractSiembraResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        GeoJson response = new GeoJson();
        handleResponse(throwable, response);
        return response;
    }

    public static ExecutionTaskRequest postExecutionTaskResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        ExecutionTaskRequest response = new ExecutionTaskRequest();
        handleResponse(throwable, response);
        return response;
    }

    public static QuotaResponse getQuotaResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        QuotaResponse response = new QuotaResponse();
        handleResponse(throwable, response);
        return response;
    }

    public static SurveyResponse getSurveyResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        SurveyResponse response = new SurveyResponse();
        handleResponse(throwable, response);
        return response;
    }

    public static PredioPotencialResponse getPredioPotencialResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        PredioPotencialResponse response = new PredioPotencialResponse();
        handleResponse(throwable, response);
        return response;
    }

    public static GeoJson getGeoJsonResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        GeoJson response = new GeoJson();
        handleResponse(throwable, response);
        return response;
    }

    public static CartaIntencionResponse getCartaIntencionResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        CartaIntencionResponse response = new CartaIntencionResponse();
        handleResponse(throwable, response);
        return response;
    }

    public static CartaAndSurveyResponse getCartasAndSurveysResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        CartaAndSurveyResponse response = new CartaAndSurveyResponse();
        handleResponse(throwable, response);
        return response;
    }

    public static CuencaVerdeResponse getResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        CuencaVerdeResponse response = new CuencaVerdeResponse();
        handleResponse(throwable, response);
        return response;
    }

    public static SendSurveyCartaResponse getSurveyResponseExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        SendSurveyCartaResponse response = new SendSurveyCartaResponse();
        handleResponse(throwable, response);
        return response;
    }

    public static List<TaskResponse> getTaskResponseExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        TaskResponse response = new TaskResponse();
        handleResponse(throwable, response);
        ArrayList<TaskResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static List<OpenTaskResponse> getOpenTaskResponseExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        OpenTaskResponse response = new OpenTaskResponse();
        handleResponse(throwable, response);
        ArrayList<OpenTaskResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static List<ExecutionTaskResponse> getExecutionTaskResponseExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        ExecutionTaskResponse response = new ExecutionTaskResponse();
        handleResponse(throwable, response);
        ArrayList<ExecutionTaskResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static List<CartaIntencionResponse> getCartaIntencionResponseExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        CartaIntencionResponse response = new CartaIntencionResponse();
        handleResponse(throwable, response);
        ArrayList<CartaIntencionResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static List<MonitoringMaintenanceResponse> getMonitoringMaintenanceResponseExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        MonitoringMaintenanceResponse response = new MonitoringMaintenanceResponse();
        handleResponse(throwable, response);
        ArrayList<MonitoringMaintenanceResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static List<TaskCommentResponse> getTaskCommentsResponseExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        TaskCommentResponse response = new TaskCommentResponse();
        handleResponse(throwable, response);
        ArrayList<TaskCommentResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static List<ProgramResponse> getProgramsResponseExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        ProgramResponse response = new ProgramResponse();
        handleResponse(throwable, response);
        ArrayList<ProgramResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static StardMonitorAndMaintenanceFormResponse getStardMonitoringMaintenanceFormResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        StardMonitorAndMaintenanceFormResponse response = new StardMonitorAndMaintenanceFormResponse();
        handleResponse(throwable, response);
        return response;
    }

    public static List<CroquisResponse> getCroquisResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        CroquisResponse response = new CroquisResponse();
        handleResponse(throwable, response);
        ArrayList<CroquisResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static MonitoringMaintenanceResponse getMonitoringMaintenanceResponseException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        MonitoringMaintenanceResponse response = new MonitoringMaintenanceResponse();
        handleResponse(throwable, response);
        return response;
    }

    public static List<ActionResponse> getAccionesResponseExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        ActionResponse response = new ActionResponse();
        handleResponse(throwable, response);
        ArrayList<ActionResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static List<MaterialActionResponse> getMaterialsResponseExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        MaterialActionResponse response = new MaterialActionResponse();
        handleResponse(throwable, response);
        ArrayList<MaterialActionResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static List<ProcedureResponse> getProjectResponseExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        ProcedureResponse response = new ProcedureResponse();
        handleResponse(throwable, response);
        ArrayList<ProcedureResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static List<DependencyResponse> getDependenciesExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        DependencyResponse response = new DependencyResponse();
        handleResponse(throwable, response);
        ArrayList<DependencyResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static List<PredioPotencialResponse> getPrediosPotencialesExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        PredioPotencialResponse response = new PredioPotencialResponse();
        handleResponse(throwable, response);
        ArrayList<PredioPotencialResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static List<ProvinceResponse> getProvincesExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        ProvinceResponse response = new ProvinceResponse();
        handleResponse(throwable, response);
        ArrayList<ProvinceResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static PrediosManagementResponse getPrediosManagementLayerException(Throwable throwable) {
        Log.d("getResponseException", throwable.getMessage() + " " + throwable.getClass().getName());
        PrediosManagementResponse response = new PrediosManagementResponse();
        handleResponse(throwable, response);
        return response;
    }

    public static List<MunicipalityResponse> geMunicipalitiesExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        MunicipalityResponse response = new MunicipalityResponse();
        handleResponse(throwable, response);
        ArrayList<MunicipalityResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }

    public static List<PQRSTypeResponse> getPqrsTypesExceptionList(Throwable throwable) {
        Log.d("getResponseExceptionLis", throwable.getMessage() + " " + throwable.getClass().getName());
        PQRSTypeResponse response = new PQRSTypeResponse();
        handleResponse(throwable, response);
        ArrayList<PQRSTypeResponse> responsesList = new ArrayList<>();
        responsesList.add(response);
        return responsesList;
    }
}
