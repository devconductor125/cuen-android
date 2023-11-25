package com.orquitech.development.cuencaVerde.logic;

import android.location.Location;

import com.orquitech.development.cuencaVerde.data.model.ContractSiembra;
import com.orquitech.development.cuencaVerde.data.model.ContractorEvaluation;
import com.orquitech.development.cuencaVerde.data.model.Croquis;
import com.orquitech.development.cuencaVerde.data.model.GeometryComment;
import com.orquitech.development.cuencaVerde.data.model.MaintenanceControl;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.PQRS;
import com.orquitech.development.cuencaVerde.data.model.Predio;
import com.orquitech.development.cuencaVerde.data.model.PredioFollowUp;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
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
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import io.reactivex.Observable;

public interface PrediosManager {

    void getPredios();

    Predio getPredio(long predioId);

    void addTrackToPredio(long predioId, String trackName, List<LatLng> track);

    Observable<List<Item>> getPrediosObservable();

    Observable<Predio> getPredioObservable();

    Observable<Predio> getSavedPredioObservable();

    Observable<PQRS> getSentPQRSObservable();

    Observable<Long> getSentPredioPotencialObservable();

    Observable<StardMonitorAndMaintenance> getStardMonitorAndMaintenanceObservable();

    Observable<GeoJson> getPrediosManagementLayerSubjectObservable();

    void clearAfterPredioSaved();

    void checkForStardFormToBeSent();

    void checkForVegetalMaintenanceFormToBeSent();

    void checkForPredioFollowUpFormToBeSent();

    Observable<StardMonitorAndMaintenance> getSentStardMonitorAndMaintenanceObservable();

    void getStardMonitorAndMaintenanceByMonitorAndMaintenanceId(String monitorAndMaintenanceId);

    void getStardMonitorAndMaintenance(String monitorAndMaintenanceId);

    void sendStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance);

    void saveStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance);

    void sendStardSheetForm(StardSheetForm stardSheetForm, String taskId);

    void saveStardSheetForm(StardSheetForm stardSheetForm, String taskId);

    void sendContractorForm(Task taskId);

    void saveContractorForm(ContractorForm contractorForm, String contractorSiembraId);

    void sendPQRS(PQRS pqrs);

    void savePQRS(PQRS pqrs);

    void checkForPQRS();

    Observable<VegetalMaintenance> getSentVegetalMaintenanceObservable();

    Observable<VegetalMaintenance> getVegetalMaintenanceObservable();

    Observable<List<Croquis>> getCroquisObservable();

    Observable<PredioFollowUp> getSentPredioFollowUpObservable();

    Observable<SamplingPointForm> getSavedSamplingPointFormSubject();

    Observable<ErosiveProcessForm> getSavedErosiveProcessFormSubject();

    Observable<CartaIntencion> getSentCartaIntencionObservable();

    Observable<Boolean> getSentSurveyAndPollObservable();

    void resetSurveyAndPollObservable();

    Observable<Survey> getSentSurveyObservable();

    Observable<GeoJson> getContractSiembraGeoJSonSubject();

    Observable<ContractSiembra> getSentContractSiembraObservable();

    Observable<PredioFollowUp> getPredioFollowUpObservable();

    Observable<CartaIntencion> getCartaIntencionObservable();

    Observable<StardSheetForm> getStardSheetFormObservable();

    Observable<ContractorForm> getContractorFormObservable();

    Observable<MaintenanceControl> getMaintenanceControlFormObservable();

    Observable<StardSheetForm> getSentStardSheetFormObservable();

    Observable<ContractorForm> getSentContractorFormSubjectObservable();

    Observable<GeometryComment> getSavedGeometryCommentSubjectObservable();

    Observable<MaintenanceControl> getSentMaintenanceControlObservable();

    Observable<Boolean> getValidMaintenanceControlObservable();

    Observable<Boolean> getValidContractSiembraObservable();

    Observable<Boolean> getValidGeoJsonObservable();

    void validateContractSiembra(ContractSiembra contractSiembra);

    Observable<GeoJson> getGeoJsonObservable();

    Observable<GeoJson> getTaskHasGeoJsonSubjectObservable();

    Observable<ContractorForm> getTaskHasContractorFormSubjectObservable();

    Observable<List<SamplingPointForm>> getTaskHasSamplingPointFormSubjectObservable();

    Observable<List<ErosiveProcessForm>> getTaskHasErosiveProcessFormSubjectObservable();

    Observable<MeetingsWithActorsForm> getHasMeetingWithActorsFormSubjectObservable();

    Observable<GeoJson> getExecutionBaseGeoJsonObservable();

    Observable<GeoJson> getExecutionEditedGeoJsonObservable();

    Observable<ContractorEvaluation> getSentContractorEvaluationObservable();

    Observable<ContractorEvaluation> getContractorEvaluationObservable();

    void getVegetalMaintenance(String monitorAndMaintenanceId);

    void getCroquis(String predioId);

    void getVegetalMaintenanceByMonitorAndMaintenanceId(String monitorAndMaintenanceId);

    void sendVegetalMaintenance(VegetalMaintenance vegetalMaintenance);

    void saveVegetalMaintenance(VegetalMaintenance vegetalMaintenance);

    void sendSurvey(PredioPotencial predioPotencial, Survey survey);

    void getPredioFollowUpByMonitorAndMaintenanceId(String monitorAndMaintenanceId);

    void getCartaIntencionByPredioPotencialId(String cartaIntencionId);

    void getStardSheetForm(Task task);

    void getContractorForm(Task task);

    void getGeoJson(Task task);

    void taskHasGeoJson(Task task);

    void taskHasContractorForm(Task task);

    void taskHasMeetingWithActorsForm(Task task);

    void getPredioFollowUp(String monitorAndMaintenanceId);

    void savePredioFollowUp(PredioFollowUp predioFollowUp);

    void sendPredioFollowUp(PredioFollowUp predioFollowUp);

    void sendCartaIntencion(PredioPotencial predioPotencial, CartaIntencion cartaIntencion);

    void saveCartaIntencion(CartaIntencion cartaIntencion);

    void getExecutionTaskBaseGeoJson(Task task);

    void getExecutionTaskEditedGeoJson(Task task);

    void saveContractorEvaluation(ContractorEvaluation contractorEvaluation);

    void getContractorEvaluation(String monitorAndMaintenanceId);

    void getContractorEvaluationByMonitorAndMaintenanceId(String monitorAndMaintenanceId);

    void sendContractorEvaluation(ContractorEvaluation contractorEvaluation);

    Observable<Boolean> getValidStardSheetFormObservable();

    Observable<List<Municipality>> getMunicipalitiesObservable();

    void validateStardSheetForm(Task task);

    void validateMaintenanceControlForm(MonitorAndMaintenance monitorAndMaintenance);

    void getMaintenanceControl(MonitorAndMaintenance monitorAndMaintenance);

    void sendMaintenanceControlForm(MonitorAndMaintenance monitorAndMaintenance, String monitorAndMaintenanceId);

    void saveMaintenanceControl(MaintenanceControl maintenanceControl, String monitorAndMaintenanceId);

    void getProvinces();

    void getPrediosManagementLayer();

    void getMunicipalities(int provinceId);

    void getLocalMunicipalities();

    void sendDocuments(PredioPotencial predio);

    void sendPredioPotencial(PredioPotencial predioPotencial);

    void endExecutionTask(Task task);

    void clearPrediosManagementLayer();

    void getPrediosManagementLayerFromFile(Location location);

    void saveContractSiembra(ContractSiembra contractSiembra);

    void sendContractSiembra(ContractSiembra contractSiembra);

    void getContractSiembra(Task task);

    void deleteContractorForm(String taskId);

    void sendContractorTask(String taskId);

    void endCommunicationsTask(Task task);

    void endPsaTask(Task task);

    void endSpecialTask(Task task);

    Observable<List<SiembraDetailForm>> getSiembraDetails(String hash);

    Observable<List<SamplingPointForm>> getSamplingPoints(String hash);

    Observable<List<ErosiveProcessForm>> getErosiveProcessForms(String hash);

    Observable<Boolean> getSentSiembraDetailsFormSubjectObservable();

    Observable<Boolean> getSentSamplingPointFormsSubjectObservable();

    Observable<Boolean> getSentErosiveProcessFormsSubjectObservable();

    Observable<Boolean> getSentMeetingWithActorsFormSubjectObservable();

    Observable<List<Item>> getSiembraDetailsObservable();

    Observable<List<Item>> getSamplingPointsObservable();

    Observable<List<Item>> getErosiveFormsObservable();

    void sendSiembraDetailForms(Task task);

    void sendHydrologicalProcessForms(Task task);

    void sendErosiveProcessForms(Task task);

    void getSiembraDetailForm(String siembraDetailFormId);

    void getSamplingPointForm(String samplingPointFormId);

    void getErosivePointForm(String erosivePointFormId);

    Observable<SiembraDetailForm> getSiembraDetailFormObservable();

    Observable<SamplingPointForm> getSamplingPointFormSubject();

    Observable<ErosiveProcessForm> getErosiveProcessFormSubject();

    void saveSiembraDetailForm(SiembraDetailForm siembraDetailForm, String taskId);

    void saveSamplingPointForm(SamplingPointForm samplingPointForm, String taskId);

    void saveErosiveProcessForm(ErosiveProcessForm erosiveProcessForm, String taskId);

    Observable<SiembraDetailForm> getSavedSiembraDetailFormSubject();

    void getMeetingsWithActorsForm(String taskId);

    Observable<MeetingsWithActorsForm> getMeetingsWithActorsFormObservable();

    void saveMeetingsWithActorsForm(MeetingsWithActorsForm meetingsWithActorsForm, Task task);

    void sendMeetingsWithActorsForm(Task task);

    void saveGeometryComment(String taskId, GeometryComment geometryComment);

    void getGeometryComment(String taskId, String hash);

    void setChosenActionType(String chosenActionType);

    String getChosenActionType();

    void setIsMeasurementPaused(boolean isPaused);

    boolean isMeasurementPaused();

    void taskHasHydrologicalProcessForms(Task task);

    void taskHasErosiveProcessForms(Task task);
}
