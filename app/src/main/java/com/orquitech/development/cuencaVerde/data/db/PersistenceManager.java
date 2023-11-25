package com.orquitech.development.cuencaVerde.data.db;

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
import com.orquitech.development.cuencaVerde.presentation.factories.Item;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;

public interface PersistenceManager {

    void getPredios(int offset, int limit, ManagerCallback persistenceManagerCallback);

    Observable<Boolean> getOnClearedDatabaseObservable();

    void savePredio(Predio predio, ManagerCallback callback);

    void getPrediosPotenciales(int offset, int limit, ManagerCallback callback);

    void saveProcedure(Procedure project);

    void saveProcedure(Procedure project, ManagerCallback callback);

    void saveTask(Task task, ManagerCallback callback);

    void saveProjects(List<Item> projects, ManagerCallback callback);

    void getProcedures(int offset, int limit, ManagerCallback callback);

    void getProcedureById(String projectId, ManagerCallback callback);

    void getTasks(String procedureId, ManagerCallback callback);

    void saveSurvey(Survey survey);

    void saveSurvey(Survey survey, ManagerCallback callback);

    void getSurveyByPredioPotencialId(String taskId, ManagerCallback callback);

    void getDueTasks(ManagerCallback persistenceManagerCallback);

    void savePredioPotencial(PredioPotencial predioPotencial, ManagerCallback callback);

    void deletePredioByRemoteId(long remoteId);

    void savePrediosPotenciales(List<PredioPotencial> predios, ManagerCallback callback);

    void deletePrediosPotenciales();

    void deleteSurvey(Survey survey);

    void saveAcciones(List<Item> acciones);

    void getAcciones(String type, int offset, int limit, ManagerCallback callback);

    void saveMaterials(List<Item> materials);

    void getMaterials(int offset, int limit, ManagerCallback callback);

    void saveMonitorAndMaintenances(List<Item> monitorAndMaintenances);

    void saveTasks(List<Item> tasks);

    void getMonitoringAndMaintenances(String procedureId, ManagerCallback ManagerCallback);

    void saveMonitorAndMaintenancePhoto(MonitorAndMaintenancePhoto monitorAndMaintenancePhoto);

    void savePhotographicRegistryPhoto(PhotographicRegistryPhoto photographicRegistryPhoto, ManagerCallback callback);

    void updatePhotographicRegistry(PhotographicRegistryPhoto photographicRegistryPhoto);

    void saveMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint, ManagerCallback callback);

    void getMonitorAndMaintenancePoints(String monitorAndMaintenanceId, ManagerCallbackAdapter managerCallbackAdapter);

    void deleteMonitorAndMaintenanceCommentPoint(String monitorAndMaintenanceIdCommentPointId);

    void deletePhotographicRegistry(List<String> photographicRegistryIds);

    void deletePhotographicRegistryByTaskId(String taskId);

    void getMonitorAndMaintenancePhotos(String monitorAndMaintenanceCommentPointId, ManagerCallbackAdapter managerCallbackAdapter);

    void getPhotographicRegistryPhotos(String taskId, ManagerCallback callback);

    void deleteMonitorAndMaintenanceCommentPointByMonitorAndMaintenanceId(String monitorAndMaintenanceId);

    void getPendingMonitorAndMaintenances(int offset, int limit, ManagerCallback callback);

    void getMonitorAndMaintenanceById(String monitorAndMaintenanceId, ManagerCallback callback);

    void saveMonitorAndMaintenance(MonitorAndMaintenance monitorAndMaintenance);

    void deleteMonitorAndMaintenanceById(String monitorAndMaintenanceId, ManagerCallback callback);

    void savePQRS(PQRS pqrs);

    void getAllPQRS(ManagerCallback callback);

    void deleteAllPQRS();

    void saveDependencies(List<Item> dependencies);

    void getDependencies(ManagerCallback callback);

    void savePqrsTypes(List<Item> pqrsTypes);

    void getPqrsTypes(String dependencyId, ManagerCallback callback);

    void saveStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance, ManagerCallback callback);

    void saveContractSiembra(ContractSiembra contractSiembra, ManagerCallback callback);

    void getStardMonitorAndMaintenance(String monitorAndMaintenanceId, ManagerCallbackAdapter callback);

    void getStardMonitorAndMaintenances(int offset, int limit, ManagerCallback callback);

    void deleteStardMonitorAndMaintenance(String monitorAndMaintenanceId);

    void saveVegetalMaintenance(VegetalMaintenance vegetalMaintenance, ManagerCallback callback);

    void getVegetalMaintenanceByMonitorAndMaintenanceId(String monitorAndMaintenanceId, ManagerCallbackAdapter callback);

    void getPendingVegetalMaintenances(int offset, int limit, ManagerCallback callback);

    void deleteVegetalMaintenance(String monitorAndMaintenanceId);

    void savePredioFollowUp(PredioFollowUp predioFollowUp, ManagerCallback callback);

    void getPredioFollowUpByMonitorAndMaintenanceId(String monitorAndMaintenanceId, ManagerCallbackAdapter callback);

    void getPendingPredioFollowUps(int offset, int limit, ManagerCallback callback);

    void deletePredioFollowUp(String monitorAndMaintenanceId);

    void deleteCartaIntencion(String cartaIntencionId);

    void saveContractorEvaluation(ContractorEvaluation contractorEvaluation, ManagerCallback callback);

    void getContractorEvaluationByMonitorAndMaintenanceId(String monitorAndMaintenanceId, ManagerCallbackAdapter callback);

    void getPendingContractorEvaluations(int offset, int limit, ManagerCallback callback);

    void deleteContractorEvaluation(String monitorAndMaintenanceId);

    void saveCartaIntencion(CartaIntencion cartaIntencion, ManagerCallback callback);

    void getCartaIntencion(String cartaIntencionId, ManagerCallback callback);

    void saveExecutionTaskBaseGeoJson(GeoJson geoJson, String taskId, ManagerCallback callback);

    void saveExecutionTaskEditedGeoJson(GeoJson geoJson, String taskId, ManagerCallback callback);

    void getExecutionTaskBaseGeoJson(String taskId, ManagerCallback callback);

    void getExecutionTaskEditedGeoJson(String taskId, ManagerCallback callback);

    void getGeoJson(String taskId, ManagerCallback callback);

    void saveGeoJson(GeoJson geoJson, String taskId, ManagerCallback callback);

    void deleteGeoJson(String taskId);

    void deleteExecutionTaskBaseGeoJson(String taskId);

    void deleteExecutionTaskEditedGeoJson(String taskId);

    void getStardSheet(Task task, ManagerCallback callback);

    void getContractorForm(String contractSiembraId, ManagerCallback callback);

    void getSiembraDetailForm(String hash, ManagerCallback callback);

    void saveStardSheet(StardSheetForm stardSheetForm, String taskId, ManagerCallback callback);

    void saveContractorForm(ContractorForm contractorForm, String contractSiembraId, ManagerCallback callback);

    void saveSiembraDetailForm(SiembraDetailForm siembraDetailForm, String hash, String taskId, ManagerCallback callback);

    void deleteStardSheet(String taskId);

    void deleteContractorForm(String taskId);

    void deleteContractSiembra(String contractSiembraId);

    void getContractSiembra(String contractSurveyId, ManagerCallback callback);

    void getMaintenanceControl(MonitorAndMaintenance monitorAndMaintenance, ManagerCallback callback);

    void saveMaintenanceControl(MaintenanceControl maintenanceControl, String monitorAndMaintenanceId, ManagerCallback callback);

    void deleteMaintenanceControl(String monitorAndMaintenanceId);

    void clearTables();

    void getTaskComments(String taskId, ManagerCallback callback);

    void saveTaskComments(List<Item> taskComments, String taskId);

    void saveProvinces(List<Province> provinces);

    void getMunicipalities(ManagerCallback callback);

    void saveMunicipalities(List<Municipality> municipalities);

    void getCroquis(String predioId, ManagerCallback callback);

    void saveCroquisList(List<Croquis> croquisList, String predioId);

    void getPrediosManagementLayer(ManagerCallback callback);

    void savePrediosManagementLayer(GeoJson json);

    void getSiembraDetails(String contractSiembraId, ManagerCallback callback);

    void getSiembraDetailsByTaskId(String taskId, ManagerCallback callback);

    void deleteSiembraDetailForms(Set<String> hashes);

    void getMeetingsWithActorsForm(String taskId, ManagerCallback callback);

    void saveMeetingsWithActorsForm(MeetingsWithActorsForm meetingsWithActorsForm, String taskId, ManagerCallback callback);

    void deleteMeetingsWithActorsForm(String taskId);

    void saveGeometryComment(String taskId, GeometryComment geometryComment);

    void getPrograms(ManagerCallback callback);

    void getGeometryComment(String taskId, String hash, ManagerCallback callback);

    void savePrograms(List<Item> programs);

    void saveSamplingPointForm(SamplingPointForm samplingPointForm, String hash, String taskId, ManagerCallback callback);

    void getSamplingPointsByTaskId(String taskId, ManagerCallback callback);

    void getSamplingPoints(String hash, ManagerCallback callback);

    void getSamplingPointForm(String samplingPointFormId, ManagerCallback callback);

    void deleteSamplingPointForms(Set<String> hashes);

    void saveErosiveProcessForm(ErosiveProcessForm erosiveProcessForm, String hash, String taskId, ManagerCallback callback);

    void getErosiveProcessFormsByTaskId(String taskId, ManagerCallback callback);

    void getErosiveProcessForms(String hash, ManagerCallback callback);

    void getErosiveProcessForm(String samplingPointFormId, ManagerCallback callback);

    void deleteErosiveProcessForms(Set<String> hashes);
}
