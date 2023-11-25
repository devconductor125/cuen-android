package com.orquitech.development.cuencaVerde.data;

import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.ContractSiembra;
import com.orquitech.development.cuencaVerde.data.model.ContractorEvaluation;
import com.orquitech.development.cuencaVerde.data.model.Croquis;
import com.orquitech.development.cuencaVerde.data.model.Dependency;
import com.orquitech.development.cuencaVerde.data.model.GeometryComment;
import com.orquitech.development.cuencaVerde.data.model.MaintenanceControl;
import com.orquitech.development.cuencaVerde.data.model.Material;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenancePhoto;
import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.PQRS;
import com.orquitech.development.cuencaVerde.data.model.PQRSType;
import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.data.model.Predio;
import com.orquitech.development.cuencaVerde.data.model.PredioFollowUp;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.data.model.Procedure;
import com.orquitech.development.cuencaVerde.data.model.Program;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.TaskComment;
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

public interface ManagerCallback {

    void onPredios(List<Predio> predios);

    void onPrediosPotenciales(List<PredioPotencial> prediosPotenciales);

    void onPredio(Predio predio);

    void onProcedure(Procedure project);

    void onProcedures(List<Item> projects);

    void onTasks(List<Item> tasks);

    void onSurvey(Survey survey);

    void onSaved();

    void onAcciones(List<Action> acciones);

    void onMaterials(List<Material> materials);

    void onMonitorAndMaintenanceList(List<MonitorAndMaintenance> monitorAndMaintenanceList);

    void onGeoJson(GeoJson geoJson);

    void onMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint);

    void onMonitorAndMaintenanceCommentPoints(List<MonitorAndMaintenanceCommentPoint> monitorAndMaintenanceCommentPoint);

    void onMonitorAndMaintenancePhotos(List<MonitorAndMaintenancePhoto> monitorAndMaintenancePhotos);

    void onPhotographicRegistryPhotos(List<PhotographicRegistryPhoto> photographicRegistryPhotos);

    void onPhotographicRegistryPhoto(PhotographicRegistryPhoto photographicRegistryPhoto);

    void onMonitorAndMaintenanceDeleted();

    void onMonitorAndMaintenance(MonitorAndMaintenance monitorAndMaintenance);

    void onPQRSList(List<PQRS> pqrs);

    void onDependencies(List<Dependency> dependencies);

    void onPqrsTypes(List<PQRSType> pqrsTypes);

    void onStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance);

    void onContractSiembra(ContractSiembra contractSiembra);

    void onStardMonitorAndMaintenances(List<StardMonitorAndMaintenance> stardMonitorAndMaintenanceList);

    void onVegetalMaintenance(VegetalMaintenance vegetalMaintenance);

    void onVegetalMaintenances(List<VegetalMaintenance> vegetalMaintenanceList);

    void onPredioFollowUp(PredioFollowUp predioFollowUp);

    void onPredioFollowUps(List<PredioFollowUp> predioFollowUpList);

    void onContractorEvaluation(ContractorEvaluation contractorEvaluation);

    void onContractorEvaluations(List<ContractorEvaluation> contractorEvaluationList);

    void onCartaIntencion(CartaIntencion cartaIntencion);

    void onCartasIntencion(List<CartaIntencion> cartasIntencion);

    void onTask(Task task);

    void onForm(StardSheetForm stardSheetForm);

    void onMaintenanceControl(MaintenanceControl maintenanceControl);

    void onTaskComments(List<TaskComment> taskComments);

    void onMunicipalities(List<Municipality> municipalities);

    void onCroquis(Croquis croquis);

    void onCroquisList(List<Croquis> croquis);

    void onPrediosManagementGeoJson(GeoJson geoJson);

    void onContractorForm(ContractorForm contractorForm);

    void onSiembraDetailList(List<SiembraDetailForm> siembraDetailList);

    void onSiembraDetailForm(SiembraDetailForm siembraDetailForm);

    void onMeetingsWithActorsForm(MeetingsWithActorsForm meetingsWithActorsForm);

    void onGeometryComment(GeometryComment geometryComment);

    void onPrograms(List<Program> programs);

    void onSamplingPointForm(SamplingPointForm samplingPointForm);

    void onSamplingPoints(List<SamplingPointForm> samplingPointFormList);

    void onErosiveProcessForm(ErosiveProcessForm erosiveProcessForm);

    void onErosiveProcessForms(List<ErosiveProcessForm> erosiveProcessFormList);
}
