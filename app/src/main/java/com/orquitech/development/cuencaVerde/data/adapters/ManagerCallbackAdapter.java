package com.orquitech.development.cuencaVerde.data.adapters;

import com.orquitech.development.cuencaVerde.data.ManagerCallback;
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

public abstract class ManagerCallbackAdapter implements ManagerCallback {

    @Override
    public void onPredios(List<Predio> predios) {
    }

    @Override
    public void onPrediosPotenciales(List<PredioPotencial> prediosPotenciales) {
    }

    @Override
    public void onPredio(Predio predio) {
    }

    @Override
    public void onProcedure(Procedure project) {
    }

    @Override
    public void onProcedures(List<Item> procedures) {
    }

    @Override
    public void onTasks(List<Item> tasks) {
    }

    @Override
    public void onSurvey(Survey survey) {
    }

    @Override
    public void onSaved() {
    }

    @Override
    public void onAcciones(List<Action> acciones) {
    }

    @Override
    public void onMaterials(List<Material> materials) {
    }

    @Override
    public void onMonitorAndMaintenanceList(List<MonitorAndMaintenance> monitorAndMaintenanceList) {
    }

    @Override
    public void onMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
    }

    @Override
    public void onMonitorAndMaintenanceCommentPoints(List<MonitorAndMaintenanceCommentPoint> monitorAndMaintenanceCommentPoint) {
    }

    @Override
    public void onMonitorAndMaintenancePhotos(List<MonitorAndMaintenancePhoto> monitorAndMaintenancePhotos) {
    }

    @Override
    public void onPhotographicRegistryPhotos(List<PhotographicRegistryPhoto> photographicRegistryPhotos) {
    }

    @Override
    public void onPhotographicRegistryPhoto(PhotographicRegistryPhoto photographicRegistryPhoto) {
    }

    @Override
    public void onMonitorAndMaintenanceDeleted() {
    }

    @Override
    public void onMonitorAndMaintenance(MonitorAndMaintenance monitorAndMaintenance) {
    }

    @Override
    public void onPQRSList(List<PQRS> pqrs) {
    }

    @Override
    public void onDependencies(List<Dependency> dependencies) {
    }

    @Override
    public void onPqrsTypes(List<PQRSType> pqrsTypes) {
    }

    @Override
    public void onStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance) {
    }

    @Override
    public void onContractSiembra(ContractSiembra contractSiembra) {
    }

    @Override
    public void onStardMonitorAndMaintenances(List<StardMonitorAndMaintenance> stardMonitorAndMaintenanceList) {
    }

    @Override
    public void onVegetalMaintenance(VegetalMaintenance vegetalMaintenance) {
    }

    @Override
    public void onVegetalMaintenances(List<VegetalMaintenance> vegetalMaintenanceList) {
    }

    @Override
    public void onPredioFollowUp(PredioFollowUp predioFollowUp) {
    }

    @Override
    public void onPredioFollowUps(List<PredioFollowUp> predioFollowUpList) {
    }

    @Override
    public void onContractorEvaluation(ContractorEvaluation contractorEvaluation) {
    }

    @Override
    public void onContractorEvaluations(List<ContractorEvaluation> contractorEvaluationList) {
    }

    @Override
    public void onGeoJson(GeoJson geoJson) {
    }

    @Override
    public void onCartaIntencion(CartaIntencion cartaIntencion) {
    }

    @Override
    public void onCartasIntencion(List<CartaIntencion> cartasIntencion) {
    }

    @Override
    public void onTask(Task task) {
    }

    @Override
    public void onForm(StardSheetForm stardSheetForm) {
    }

    @Override
    public void onMaintenanceControl(MaintenanceControl maintenanceControl) {
    }

    @Override
    public void onTaskComments(List<TaskComment> taskComments) {
    }

    @Override
    public void onMunicipalities(List<Municipality> municipalities) {
    }

    @Override
    public void onCroquis(Croquis croquis) {
    }

    @Override
    public void onCroquisList(List<Croquis> croquis) {
    }

    @Override
    public void onPrediosManagementGeoJson(GeoJson geoJson) {
    }

    @Override
    public void onContractorForm(ContractorForm contractorForm) {
    }

    @Override
    public void onSiembraDetailList(List<SiembraDetailForm> siembraDetailList) {
    }

    @Override
    public void onSiembraDetailForm(SiembraDetailForm siembraDetailForm) {
    }

    @Override
    public void onMeetingsWithActorsForm(MeetingsWithActorsForm meetingsWithActorsForm) {
    }

    @Override
    public void onGeometryComment(GeometryComment geometryComment) {
    }

    @Override
    public void onPrograms(List<Program> programs) {
    }

    @Override
    public void onSamplingPointForm(SamplingPointForm samplingPointForm) {
    }

    @Override
    public void onSamplingPoints(List<SamplingPointForm> samplingPointFormList) {
    }

    @Override
    public void onErosiveProcessForm(ErosiveProcessForm erosiveProcessForm) {
    }

    @Override
    public void onErosiveProcessForms(List<ErosiveProcessForm> erosiveProcessFormList) {
    }
}
