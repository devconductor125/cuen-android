package com.orquitech.development.cuencaVerde.data.utils;

import android.location.Location;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.orquitech.development.cuencaVerde.data.api.model.acciones.get.ActionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.CartaIntencionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.croquis.get.CroquisResponse;
import com.orquitech.development.cuencaVerde.data.api.model.dependencies.get.DependencyResponse;
import com.orquitech.development.cuencaVerde.data.api.model.logIn.LogInResponse;
import com.orquitech.development.cuencaVerde.data.api.model.material.get.MaterialActionResponse;
import com.orquitech.development.cuencaVerde.data.api.model.material.get.MaterialResponse;
import com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get.MonitoringMaintenanceResponse;
import com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get.Point;
import com.orquitech.development.cuencaVerde.data.api.model.municipalities.get.MunicipalityResponse;
import com.orquitech.development.cuencaVerde.data.api.model.pqrsTypes.get.PQRSTypeResponse;
import com.orquitech.development.cuencaVerde.data.api.model.prediosPotenciales.get.PredioPotencialResponse;
import com.orquitech.development.cuencaVerde.data.api.model.programs.get.ProgramResponse;
import com.orquitech.development.cuencaVerde.data.api.model.projects.get.response.ProcedureResponse;
import com.orquitech.development.cuencaVerde.data.api.model.province.get.ProvinceResponse;
import com.orquitech.development.cuencaVerde.data.api.model.quota.get.QuotaResponse;
import com.orquitech.development.cuencaVerde.data.api.model.stardMonitorAndMaintenanceForm.get.Contact;
import com.orquitech.development.cuencaVerde.data.api.model.stardMonitorAndMaintenanceForm.get.Coordinate;
import com.orquitech.development.cuencaVerde.data.api.model.survey.get.SurveyResponse;
import com.orquitech.development.cuencaVerde.data.api.model.taskComments.get.TaskCommentResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.ExecutionTaskResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.OpenTaskResponse;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.get.response.TaskResponse;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.Auth;
import com.orquitech.development.cuencaVerde.data.model.ContractSiembra;
import com.orquitech.development.cuencaVerde.data.model.Croquis;
import com.orquitech.development.cuencaVerde.data.model.Dependency;
import com.orquitech.development.cuencaVerde.data.model.ErosiveProcess;
import com.orquitech.development.cuencaVerde.data.model.ExecutionTask;
import com.orquitech.development.cuencaVerde.data.model.Material;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.data.model.MonitoreoRecursoHidricoProcess;
import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.PQRSType;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.data.model.Program;
import com.orquitech.development.cuencaVerde.data.model.Province;
import com.orquitech.development.cuencaVerde.data.model.Quota;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.TaskComment;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.Process;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.SubType;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.User;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.model.geoJson.PointFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineStringFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon.MultiPolygonFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.polygon.PolygonFeature;
import com.orquitech.development.cuencaVerde.data.model.survey.EconomicActivityInTheProperty;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.google.android.gms.maps.model.LatLng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapperUtils {

    public static Quota quotaResponseToQuota(CuencaVerdeResponse quotaResponse) {
        Quota quota = new Quota();
        quota.setQuota(((QuotaResponse) quotaResponse).quota);
        quota.setPredios(((QuotaResponse) quotaResponse).predios);
        quota.setPercentage(((QuotaResponse) quotaResponse).percentage);
        return quota;
    }

    public static Auth logInResponseToAuth(CuencaVerdeResponse logInResponse) {
        Auth user = new Auth();
        user.setToken(((LogInResponse) logInResponse).accessToken);
        user.setRefreshToken(((LogInResponse) logInResponse).refreshToken);
        return user;
    }

    public static ArrayMap<String, Procedure> projectsResponseToProjectsList(List<ProcedureResponse> projectsResponse) {
        ArrayMap<String, Procedure> projects = new ArrayMap<>();
        for (CuencaVerdeResponse projectResponse : projectsResponse) {
            if (!TextUtils.isEmpty(((ProcedureResponse) projectResponse).name)) {
                Procedure project = new Procedure();
                project.setId(((ProcedureResponse) projectResponse).id);
                project.setName(((ProcedureResponse) projectResponse).name);
                project.setType(AppViewsFactory.PROJECTS_LIST_ITEM_VIEW);
                projects.put(project.getId(), project);
            }
        }
        return projects;
    }

    public static List<Croquis> croquisResponseToCroquisList(List<CroquisResponse> croquisResponses) {
        List<Croquis> croquisList = new ArrayList<>();
        for (CroquisResponse croquisResponse : croquisResponses) {
            Croquis croquis = new Croquis();
            croquis.setRemoteId(croquisResponse.id);
            croquis.setMultiPolygon(croquisResponse.multipolygon);
            croquisList.add(croquis);
        }
        return croquisList;
    }

    public static List<Item> tasksResponseToTasksList(List<TaskResponse> tasksResponse) {
        List<Item> tasks = new ArrayList<>();
        for (TaskResponse taskResponse : tasksResponse) {
            if (!TextUtils.isEmpty(taskResponse.taskTypeId)) {
                Task task = new Task();
                task.setId(String.valueOf(taskResponse.id));
                task.setTitle(task.getId() + " " + taskResponse.title);
                task.setTaskType(String.valueOf(taskResponse.taskTypeId));
                task.setProcedureId(taskResponse.processId);
                task.setPotentialId(taskResponse.potentialId);
                if (taskResponse.subType != null) {
                    task.setTaskTypeTitle(taskResponse.subType.name);
                    task.setTaskSubType(taskResponse.subType.id);
                } else {
                    task.setTaskTypeTitle(taskResponse.taskSubTypeName);
                }
                task.setDescription(taskResponse.description);
                if (taskResponse.process != null && taskResponse.process.size() > 0) {
                    task.setProcedureId(String.valueOf(taskResponse.process.get(0).id));
                    task.setProcedureName(taskResponse.process.get(0).name);
                }
                task.setStatus(String.valueOf(taskResponse.taskStatusId));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                try {
                    Date dateStart = simpleDateFormat.parse(taskResponse.dateStart);
                    Date dateEnd = simpleDateFormat.parse(taskResponse.dateEnd);
                    simpleDateFormat.applyPattern("dd/MM/yyyy");
                    task.setFromDate(simpleDateFormat.format(dateStart));
                    task.setToDate(simpleDateFormat.format(dateEnd));

                    Date date = new Date();
                    task.setDueDateStatus(getDueDateStatus(date, dateEnd));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                task.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);
                tasks.add(task);
            }
        }

        // tasks.add(getMockMonitoreoRecursoHidricoTask());
        // tasks.add(getMockExecutionTask());
        // tasks.add(getMockCommunicationsOpenTask());
        // tasks.add(getMockContractSiembrasTask());

        return tasks;
    }

    private static int getDueDateStatus(Date date, Date dateToCompare) {
        return date.compareTo(dateToCompare) <= 0 ? Task.ON_TIME : Task.DELAYED;
    }

    public static List<Item> openTasksResponseToTasksList(List<OpenTaskResponse> tasksResponse) {
        List<Item> tasks = new ArrayList<>();
        try {
            for (OpenTaskResponse taskResponse : tasksResponse) {
                if (taskResponse.id > 0) {
                    Task task = getOpenTaskObject(taskResponse.taskOpenSubTypeId);
                    task.setId(String.valueOf(taskResponse.id) + "_open_" + taskResponse.taskStatusId);
                    task.setTitle(taskResponse.title);
                    task.setTaskType(String.valueOf(taskResponse.taskTypeId));
                    task.setProcedureId(taskResponse.processId);
                    task.setOpen(taskResponse.open);
                    task.setPotentialId(taskResponse.potentialId);
                    task.setTaskOpenSubTypeId(taskResponse.taskOpenSubTypeId);
                    task.setTaskSubType(taskResponse.taskOpenSubTypeId);
                    switch (taskResponse.taskOpenSubTypeId) {
                        case 6:
                        case 7:
                        case 8:
                            task.setTaskType(BaseFragment.COMMUNICATIONS);
                            break;
                        case 2:
                            task.setTaskType(BaseFragment.PSA);
                            break;
                        case 21:
                            task.setTaskType(BaseFragment.HYDROLOGICAL_PROCESS);
                            break;
                        case 26:
                            task.setTaskType(BaseFragment.EROSIVE_PROCESS);
                            break;
                    }
                    if (taskResponse.subType != null) {
                        task.setTaskTypeTitle(taskResponse.subType.name);
                    } else {
                        task.setTaskTypeTitle(taskResponse.taskSubTypeName);
                    }
                    task.setDescription(taskResponse.description);
                    if (taskResponse.process != null) {
                        task.setProcedureId(String.valueOf(taskResponse.process.id));
                        task.setProcedureName(taskResponse.process.name);
                    }
                    task.setStatus(String.valueOf(taskResponse.taskStatusId));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    try {
                        Date dateStart = simpleDateFormat.parse(taskResponse.dateStart);
                        Date dateEnd = simpleDateFormat.parse(taskResponse.dateEnd);
                        simpleDateFormat.applyPattern("dd/MM/yyyy");
                        task.setFromDate(simpleDateFormat.format(dateStart));
                        task.setToDate(simpleDateFormat.format(dateEnd));

                        Date date = new Date();
                        task.setDueDateStatus(getDueDateStatus(date, dateEnd));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    task.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);
                    tasks.add(task);
                }
            }
        } catch (Exception ex) {
            Log.e(MapperUtils.class.getSimpleName(), ex.getMessage());
        }
        return tasks;
    }

    private static Task getOpenTaskObject(Integer taskOpenSubTypeId) {
        switch (taskOpenSubTypeId) {
            case 21:
                return new MonitoreoRecursoHidricoProcess();
            case 26:
                return new ErosiveProcess();
            default:
                return new Task();
        }
    }

    public static List<Item> openContractorTasksResponseToTasksList(List<OpenTaskResponse> tasksResponse) {
        List<Item> tasks = new ArrayList<>();
        try {
            for (OpenTaskResponse taskResponse : tasksResponse) {
                if (taskResponse.id > 0) {
                    ContractSiembra task = new ContractSiembra();
                    task.setId(String.valueOf(taskResponse.id) + "_open_" + taskResponse.taskStatusId);
                    task.setTitle(taskResponse.title);
                    task.setTaskType(String.valueOf(taskResponse.taskTypeId));
                    task.setProcedureId(taskResponse.processId);
                    task.setOpen(taskResponse.open);
                    task.setPotentialId(taskResponse.potentialId);
                    task.setTaskOpenSubTypeId(taskResponse.taskOpenSubTypeId);
                    switch (taskResponse.taskOpenSubTypeId) {
                        case 18:
                            task.setTaskType(BaseFragment.CONTRACTOR);
                            break;
                    }
                    if (taskResponse.subType != null) {
                        task.setTaskTypeTitle(taskResponse.subType.name);
                    } else {
                        task.setTaskTypeTitle(taskResponse.taskSubTypeName);
                    }
                    task.setDescription(taskResponse.description);
                    if (taskResponse.process != null) {
                        task.setProcedureId(String.valueOf(taskResponse.process.id));
                        task.setProcedureName(taskResponse.process.name);
                    }
                    task.setStatus(String.valueOf(taskResponse.taskStatusId));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    try {
                        Date dateStart = simpleDateFormat.parse(taskResponse.dateStart);
                        Date dateEnd = simpleDateFormat.parse(taskResponse.dateEnd);
                        simpleDateFormat.applyPattern("dd/MM/yyyy");
                        task.setFromDate(simpleDateFormat.format(dateStart));
                        task.setToDate(simpleDateFormat.format(dateEnd));

                        Date date = new Date();
                        task.setDueDateStatus(getDueDateStatus(date, dateEnd));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    task.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);
                    tasks.add(task);
                }
            }
        } catch (Exception ex) {
            Log.e(MapperUtils.class.getSimpleName(), ex.getMessage());
        }
        return tasks;
    }

    public static List<Item> executionTasksResponseToTasksList(List<ExecutionTaskResponse> tasksResponse) {
        List<Item> tasks = new ArrayList<>();
        for (ExecutionTaskResponse taskResponse : tasksResponse) {
            if (taskResponse.id > 0) {
                ExecutionTask task = new ExecutionTask();
                task.setId(String.valueOf(taskResponse.id) + "_execution_" + taskResponse.taskStatusId);
                task.setTitle(taskResponse.taskSubTypeName);
                task.setTaskType(BaseFragment.EXECUTION);
                task.setProcedureId(taskResponse.processId);
                task.setOpen(taskResponse.open);
                task.setPotentialId(taskResponse.potentialId);
                task.setTaskOpenSubTypeId(taskResponse.taskOpenSubTypeId);
                task.setTaskTypeTitle(taskResponse.title);
                task.setDescription(taskResponse.description);
                if (taskResponse.process != null) {
                    task.setProcedureId(String.valueOf(taskResponse.process.id));
                    task.setProcedureName(taskResponse.process.name);
                }
                task.setStatus(String.valueOf(taskResponse.taskStatusId));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                try {
                    Date dateStart = simpleDateFormat.parse(taskResponse.dateStart);
                    Date dateEnd = simpleDateFormat.parse(taskResponse.dateEnd);
                    simpleDateFormat.applyPattern("dd/MM/yyyy");
                    task.setFromDate(simpleDateFormat.format(dateStart));
                    task.setToDate(simpleDateFormat.format(dateEnd));

                    Date date = new Date();
                    task.setDueDateStatus(getDueDateStatus(date, dateEnd));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                task.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);
                tasks.add(task);
            }
        }
        return tasks;
    }

    private static Item getMockErosiveProcessesTask() {
        ErosiveProcess task = new ErosiveProcess();
        task.setId("34_erosiveprocess_4");
        task.setDescription("Proceso erosivo");
        task.setProcedureName("Procedimiento de prueba para procesos erosivos");
        task.setDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit malesuada sociosqu ornare curae praesent, nam at justo eros platea posuere suspendisse nibh diam erat lectus, imperdiet tellus ullamcorper vehicula quis turpis ");
        task.setTaskType(BaseFragment.EROSIVE_PROCESS);
        task.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);
        task.setLatLng(new LatLng(5.991119, -75.360904));
        return task;
    }

    private static Item getMockMonitoreoRecursoHidricoTask() {
        MonitoreoRecursoHidricoProcess task = new MonitoreoRecursoHidricoProcess();
        task.setId("3875_procesorecursohidrico_5");
        task.setTaskTypeTitle("monitoreo recurso hídrico");
        task.setProcedureName("Procedimiento de monitoreo recurso hídrico");
        task.setDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit malesuada sociosqu ornare curae praesent, nam at justo eros platea posuere suspendisse nibh diam erat lectus, imperdiet tellus ullamcorper vehicula quis turpis ");
        task.setTaskType(BaseFragment.HYDROLOGICAL_PROCESS);
        task.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);
        task.setLatLng(new LatLng(5.991119, -75.360904));
        return task;
    }

    private static Item getMockContractSiembrasTask() {
        ContractSiembra task = new ContractSiembra();
        task.setId("456_formato_contratista_24444");
        task.setTaskTypeTitle("contratista");
        task.setProcedureName("Procedimiento de prueba para contratista");
        task.setDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit malesuada sociosqu ornare curae praesent, nam at justo eros platea posuere suspendisse nibh diam erat lectus, imperdiet tellus ullamcorper vehicula quis turpis ");
        task.setTaskType(BaseFragment.CONTRACTOR);
        task.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);
        return task;
    }

    private static Item getMockExecutionTask() {
        ExecutionTask task = new ExecutionTask();
        task.setId("678_ejecucion_453234");
        task.setTaskTypeTitle("ejecución");
        task.setProcedureName("Procedimiento de prueba para ejecución");
        task.setDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit malesuada sociosqu ornare curae praesent, nam at justo eros platea posuere suspendisse nibh diam erat lectus, imperdiet tellus ullamcorper vehicula quis turpis ");
        task.setTaskType(BaseFragment.EXECUTION);
        task.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);
        return task;
    }

    private static Item getMockCommunicationsOpenTask() {
        Task task = new Task();
        task.setId("456_comunicaciones_453234");
        task.setTaskTypeTitle("comunicaciones");
        task.setProcedureName("Procedimiento de prueba para comunicaciones");
        task.setDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit malesuada sociosqu ornare curae praesent, nam at justo eros platea posuere suspendisse nibh diam erat lectus, imperdiet tellus ullamcorper vehicula quis turpis ");
        task.setTaskType(BaseFragment.COMMUNICATIONS);
        task.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);
        task.setTaskOpenSubTypeId(6);
        return task;
    }

    public static List<Item> taskCommentsResponseToTaskCommentsList(List<TaskCommentResponse> taskCommentResponses) {
        List<Item> taskComments = new ArrayList<>();
        for (TaskCommentResponse taskResponse : taskCommentResponses) {
            TaskComment taskComment = new TaskComment();
            taskComment.setId(String.valueOf(taskResponse.id));
            taskComment.setContent(taskResponse.description);
            taskComment.setAuthor(String.valueOf(taskResponse.username));
            taskComment.setType(AppViewsFactory.TASK_COMMENT_ITEM_VIEW);
            if (!TextUtils.isEmpty(taskComment.getId()) && !TextUtils.isEmpty(taskComment.getContent())) {
                taskComments.add(taskComment);
            }
        }
        return taskComments;
    }

    public static List<Item> programsResponseToProgramsList(List<ProgramResponse> programResponseList) {
        List<Item> programsList = new ArrayList<>();
        for (ProgramResponse programResponse : programResponseList) {
            Program program = new Program();
            program.setId(String.valueOf(programResponse.id));
            program.setName(programResponse.name);
            program.setType(AppViewsFactory.CHECKBOX_ITEM_VIEW);
            programsList.add(program);
        }
        return programsList;
    }

    public static List<Item> cartasIntencionToList(List<CartaIntencionResponse> cartaIntencionResponses) {
        List<Item> cartasIntencionResponseList = new ArrayList<>();
        for (CartaIntencionResponse cartaIntencionResponse : cartaIntencionResponses) {
            cartasIntencionResponseList.add(cartaIntencionResponseToCartaIntencion(cartaIntencionResponse));
        }
        return cartasIntencionResponseList;
    }

    public static CartaIntencion cartaIntencionResponseToCartaIntencion(CartaIntencionResponse cartaIntencionResponse) {
        CartaIntencion cartaIntencion = new CartaIntencion();

        cartaIntencion.setId(String.valueOf(cartaIntencionResponse.id));
        if (cartaIntencionResponse.process != null) {
            cartaIntencion.setTitle(cartaIntencionResponse.process.name);
        }
        cartaIntencion.setFormLetter(cartaIntencionResponse.formLetter);
        cartaIntencion.setUserId(cartaIntencionResponse.userId);
        cartaIntencion.setCreatedAt(cartaIntencionResponse.createdAt);
        cartaIntencion.setUpdatedAt(cartaIntencionResponse.updatedAt);
        cartaIntencion.setTaskTypeId(cartaIntencionResponse.taskTypeId);
        if (cartaIntencionResponse.subType != null) {
            cartaIntencion.setTaskTypeName(cartaIntencionResponse.subType.name);
        }
        if (cartaIntencionResponse.process != null) {
            cartaIntencion.setProcess(processResponseToProcess(cartaIntencionResponse.process));
        }
        if (cartaIntencionResponse.subType != null) {
            cartaIntencion.setSubType(subTypeResponseToProcess(cartaIntencionResponse.subType));
        }
        if (cartaIntencionResponse.user != null) {
            cartaIntencion.setUser(userResponseToUser(cartaIntencionResponse.user));
        }
        cartaIntencion.initNullFields();
        cartaIntencion.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        /*try { // TODO Get start and end date
            Date dateStart = simpleDateFormat.parse(cartaIntencionResponse.dateStart);
            Date dateEnd = simpleDateFormat.parse(cartaIntencionResponse.dateDeadline);
            simpleDateFormat.applyPattern("dd/MM/yyyy");
            cartaIntencion.setFromDate(simpleDateFormat.format(dateStart));
            cartaIntencion.setToDate(simpleDateFormat.format(dateEnd));

            Date date = new Date();
            task.setDueDateStatus(getDueDateStatus(date, dateEnd));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        cartaIntencion.setDueDateStatus(Task.ON_TIME);
        return cartaIntencion;
    }

    private static Process processResponseToProcess(com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.Process responseProcess) {
        Process process = new Process();

        process.setId(String.valueOf(responseProcess.id));
        process.setName(responseProcess.name);
        process.setDescription(responseProcess.description);
        process.setCreatedAt(responseProcess.createdAt);
        process.setUpdatedAt(responseProcess.updatedAt);

        return process;
    }

    private static User userResponseToUser(com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.User responseUser) {
        User user = new User();

        user.setId(String.valueOf(responseUser.id));
        user.setNames(responseUser.names);
        user.setLastNames(responseUser.lastNames);
        user.setName(responseUser.name);
        user.setEmail(responseUser.createdAt);
        user.setState(Integer.parseInt(responseUser.updatedAt));
        user.setRoleId(responseUser.roleId);
        user.setCreatedAt(responseUser.createdAt);
        user.setUpdatedAt(responseUser.updatedAt);

        return user;
    }

    private static SubType subTypeResponseToProcess(com.orquitech.development.cuencaVerde.data.api.model.cartaIntencion.get.SubType responseSubType) {
        SubType subType = new SubType();
        subType.setName(responseSubType.name);
        return subType;
    }

    public static List<Item> monitoringAndMaintenanceResponseToList(List<MonitoringMaintenanceResponse> monitoringMaintenanceResponses, SerializationManager serializationManager) {
        List<Item> monitoringAndMaintenanceResponseList = new ArrayList<>();
        for (MonitoringMaintenanceResponse monitoringMaintenanceResponse : monitoringMaintenanceResponses) {
            monitoringAndMaintenanceResponseList.add(monitorAndMaintenanceResponseToMonitorAndMaintenance(monitoringMaintenanceResponse, serializationManager));
        }
        return monitoringAndMaintenanceResponseList;
    }

    public static MonitorAndMaintenance monitorAndMaintenanceResponseToMonitorAndMaintenance(MonitoringMaintenanceResponse monitorAndMaintenanceResponse, SerializationManager serializationManager) {
        MonitorAndMaintenance monitorAndMaintenance = new MonitorAndMaintenance();
        monitorAndMaintenance.setId(monitorAndMaintenanceResponse.id);
        monitorAndMaintenance.setTitle(monitorAndMaintenanceResponse.title);
        monitorAndMaintenance.setTaskType(monitorAndMaintenanceResponse.typeMonitoring);
        monitorAndMaintenance.setTaskTypeTitle(monitorAndMaintenanceResponse.typeMonitoring);
        monitorAndMaintenance.setJsonFeature(monitorAndMaintenanceResponse.geojsonFeature);
        monitorAndMaintenance.setPotentialId(monitorAndMaintenanceResponse.potentialId);
        monitorAndMaintenance.setTaskType(BaseFragment.MONITOREO);
        Feature feature = (Feature) serializationManager.fromJson(monitorAndMaintenanceResponse.geojsonFeature, Feature.class);
        if (feature != null && feature.getProperties().getFeatureType() != null) {
            switch (feature.getProperties().getFeatureType()) {
                case GeoJson.MULTI_LINE_STRING:
                    feature = (MultiLineStringFeature) serializationManager.fromJson(monitorAndMaintenanceResponse.geojsonFeature, MultiLineStringFeature.class);
                    break;
                case GeoJson.POINT:
                    feature = (PointFeature) serializationManager.fromJson(monitorAndMaintenanceResponse.geojsonFeature, PointFeature.class);
                    break;
                case GeoJson.POLYGON:
                    feature = (PolygonFeature) serializationManager.fromJson(monitorAndMaintenanceResponse.geojsonFeature, PolygonFeature.class);
                    break;
                case GeoJson.MULTI_POLYGON:
                    feature = (MultiPolygonFeature) serializationManager.fromJson(monitorAndMaintenanceResponse.geojsonFeature, MultiPolygonFeature.class);
                    break;
            }
            monitorAndMaintenance.setFeature(feature);
        }
        if (monitorAndMaintenanceResponse.points != null) {
            for (Point point : monitorAndMaintenanceResponse.points) {
                monitorAndMaintenance.addPoint(getMonitoringMaintenancePointFromResponse(monitorAndMaintenanceResponse.id, point));
            }
        }
        monitorAndMaintenance.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            Date dateStart = simpleDateFormat.parse(monitorAndMaintenanceResponse.dateStart);
            Date dateEnd = simpleDateFormat.parse(monitorAndMaintenanceResponse.dateDeadline);
            simpleDateFormat.applyPattern("dd/MM/yyyy");
            monitorAndMaintenance.setFromDate(simpleDateFormat.format(dateStart));
            monitorAndMaintenance.setToDate(simpleDateFormat.format(dateEnd));

            Date date = new Date();
            monitorAndMaintenance.setDueDateStatus(getDueDateStatus(date, dateEnd));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return monitorAndMaintenance;
    }

    private static MonitorAndMaintenanceCommentPoint getMonitoringMaintenancePointFromResponse(String monitorAndMaintenanceId, Point point) {
        MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint = new MonitorAndMaintenanceCommentPoint();
        monitorAndMaintenanceCommentPoint.setId(String.valueOf(point.id));
        monitorAndMaintenanceCommentPoint.setMonitorAndMaintenanceId(monitorAndMaintenanceId);
        if (point.comments != null && point.comments.size() > 0) {
            monitorAndMaintenanceCommentPoint.setComment(point.comments.get(0).description);
        }
        if (point.coordinate != null && point.coordinate.length() > 6) {
            Location location = new Location("");
            List<String> coordinates = Arrays.asList(point.coordinate.split(","));
            location.setLatitude(Double.parseDouble(coordinates.get(0)));
            location.setLongitude(Double.parseDouble(coordinates.get(1)));
            monitorAndMaintenanceCommentPoint.setLocation(location);
        }
        return monitorAndMaintenanceCommentPoint;
    }

    public static List<Item> accionesResponseToActionsList(List<ActionResponse> accionesResponse) {
        List<Item> acciones = new ArrayList<>();
        for (ActionResponse accionResponse : accionesResponse) {
            Action action = new Action();
            action.setId(accionResponse.id);
            action.setTitle(accionResponse.name);
            action.setAccionType(accionResponse.type);
            action.setColor(accionResponse.color);
            action.setFillColor(accionResponse.color_fill);
            action.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);
            acciones.add(action);
        }
        return acciones;
    }

    public static List<Province> provincesResponseToProvincesList(List<ProvinceResponse> provincesResponse) {
        List<Province> provinces = new ArrayList<>();
        for (ProvinceResponse provinceResponse : provincesResponse) {
            Province province = new Province();
            province.setId(provinceResponse.id);
            province.setName(provinceResponse.name);
            provinces.add(province);
        }
        return provinces;
    }

    public static List<PredioPotencial> prediosPotencialesResponseToPrediosPotencialesList(List<PredioPotencialResponse> prediosPotencialesResponse) {
        List<PredioPotencial> prediosPotenciales = new ArrayList<>();
        for (PredioPotencialResponse predioPotencialResponse : prediosPotencialesResponse) {
            if (!TextUtils.isEmpty(predioPotencialResponse.mainCoordinate) && predioPotencialResponse.archiveLoad != 200) {
                PredioPotencial predioPotencial = new PredioPotencial();
                predioPotencial.setRemoteId(Long.parseLong(predioPotencialResponse.remoteId));
                predioPotencial.setName(predioPotencialResponse.propertyName);
                predioPotencial.setType(AppViewsFactory.PREDIOS_LIST_ITEM_VIEW);

                List<String> coordinatesList = Arrays.asList(predioPotencialResponse.mainCoordinate.split(","));

                if (coordinatesList.size() == 2) {
                    Location location = new Location("");
                    location.setLatitude(Double.parseDouble(coordinatesList.get(0)));
                    location.setLongitude(Double.parseDouble(coordinatesList.get(1)));
                    predioPotencial.setLocation(location);
                }

                prediosPotenciales.add(predioPotencial);
            }
        }
        return prediosPotenciales;
    }

    public static List<Municipality> municipalitiesResponseToProvincesList(List<MunicipalityResponse> municipalitiesResponses) {
        List<Municipality> municipalities = new ArrayList<>();
        for (MunicipalityResponse provinceResponse : municipalitiesResponses) {
            Municipality municipality = new Municipality();
            municipality.setId(provinceResponse.id);
            municipality.setName(provinceResponse.name);
            municipalities.add(municipality);
        }
        return municipalities;
    }

    public static List<Municipality> municipalitiesResponseToMunicipalitiesList(List<MunicipalityResponse> municipalitiesResponses) {
        List<Municipality> municipalities = new ArrayList<>();
        for (MunicipalityResponse municipalityResponse : municipalitiesResponses) {
            Municipality municipality = new Municipality();
            municipality.setId(municipalityResponse.id);
            municipality.setName(municipalityResponse.name);
            municipality.setProvinceId(municipalityResponse.municipalityId);
            municipalities.add(municipality);
        }
        return municipalities;
    }

    public static List<Item> dependencyResponseToDependencyList(List<DependencyResponse> dependenciesResponse) {
        List<Item> dependencies = new ArrayList<>();
        for (DependencyResponse dependencyResponse : dependenciesResponse) {
            Dependency dependency = new Dependency();
            dependency.setId(String.valueOf(dependencyResponse.id));
            dependency.setTitle(dependencyResponse.name);
            dependency.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);
            dependencies.add(dependency);
        }
        return dependencies;
    }

    public static List<Item> pqrsTypeResponseToPqrsTypeList(List<PQRSTypeResponse> pqrsTypeResponsesq) {
        List<Item> pqrsTypes = new ArrayList<>();
        for (PQRSTypeResponse pqrsTypeResponse : pqrsTypeResponsesq) {
            PQRSType pqrsType = new PQRSType();
            pqrsType.setId(String.valueOf(pqrsTypeResponse.id));
            pqrsType.setTitle(pqrsTypeResponse.name);
            pqrsType.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);
            pqrsTypes.add(pqrsType);
        }
        return pqrsTypes;
    }

    public static List<Item> materialsActionResponseToMaterialsList(List<MaterialActionResponse> materialsActionResponse) {
        List<Item> materials = new ArrayList<>();
        for (MaterialActionResponse materialActionResponse : materialsActionResponse) {
            for (MaterialResponse materialResponse : materialActionResponse.materials) {
                Material material = new Material();
                material.setId(String.valueOf(materialResponse.id));
                material.setTitle(materialResponse.name);
                material.setMaterialType(materialResponse.type);
                material.addActionId(String.valueOf(materialActionResponse.action));
                material.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);
                materials.add(material);
            }
        }
        HashMap<String, Material> materialsMap = new HashMap<>();
        for (Item material : materials) {
            Material materialFromMap = materialsMap.get(((Material) material).getId());
            if (materialFromMap != null) {
                ((Material) material).setActionIds(materialFromMap.getActionIds());
            }
            materialsMap.put(((Material) material).getId(), (Material) material);
        }
        return new ArrayList<>(materialsMap.values());
    }

    public static Survey surveyResponseToSurvey(CuencaVerdeResponse response) {
        Survey survey = new Survey();
        survey.setPropertyName(((SurveyResponse) response).name);
        EconomicActivityInTheProperty economicActivityInTheProperty = new EconomicActivityInTheProperty();
        economicActivityInTheProperty.setLatitude(String.valueOf(((SurveyResponse) response).lat));
        economicActivityInTheProperty.setLongitude(String.valueOf(((SurveyResponse) response).lng));
        survey.setEconomicActivityInTheProperty(economicActivityInTheProperty);
        return survey;
    }

    public static com.orquitech.development.cuencaVerde.data.model.survey.Contact contactResponseToContact(Contact contact) {
        com.orquitech.development.cuencaVerde.data.model.survey.Contact contactResult = new com.orquitech.development.cuencaVerde.data.model.survey.Contact();
        if (contact != null) {
            contactResult.setContactEmail(contact.contactEmail);
            contactResult.setContactIdCardNumber(contact.contactIdCardNumber);
            contactResult.setContactLandLineNumber(contact.contactLandLineNumber);
            contactResult.setContactMobileNumber(contact.contactMobileNumber);
            contactResult.setContactName(contact.contactName);
        }
        return contactResult;
    }

    public static Location coordinateResponseToLocation(Coordinate coordinate) {
        Location location = new Location("");
        location.setLatitude(0.0);
        location.setLongitude(0.0);
        if (coordinate != null) {
            location.setLatitude(coordinate.lat);
            location.setLongitude(coordinate.lon);
        }
        return location;
    }

    public static GeoJson setGeoJsonFeatures(SerializationManager serializationManager, GeoJson geoJson) {
        if (geoJson.features != null) {
            for (Object geoJsonFeature : geoJson.features) {
                if (geoJsonFeature != null) {
                    String featureString = serializationManager.toJson(geoJsonFeature);
                    Feature feature = (Feature) serializationManager.fromJson(featureString, Feature.class);
                    if (feature != null && !TextUtils.isEmpty(feature.getProperties().getFeatureType())) {
                        switch (feature.getProperties().getFeatureType()) {
                            case GeoJson.MULTI_LINE_STRING:
                                MultiLineStringFeature multiLineStringFeature = (MultiLineStringFeature) serializationManager.fromJson(featureString, MultiLineStringFeature.class);
                                geoJson.addMultiLineStringFeature(multiLineStringFeature);
                                break;
                            case GeoJson.POINT:
                                PointFeature pointFeature = (PointFeature) serializationManager.fromJson(featureString, PointFeature.class);
                                geoJson.addPointFeature(pointFeature);
                                break;
                            case GeoJson.POLYGON:
                                PolygonFeature polygonFeature = (PolygonFeature) serializationManager.fromJson(featureString, PolygonFeature.class);
                                geoJson.addPolygonFeature(polygonFeature);
                                break;
                            case GeoJson.MULTI_POLYGON:
                                MultiPolygonFeature multiPolygonFeature = (MultiPolygonFeature) serializationManager.fromJson(featureString, MultiPolygonFeature.class);
                                geoJson.addMultiPolygonFeature(multiPolygonFeature);
                                break;
                        }
                    }
                }
            }
        }
        return geoJson;
    }
}
