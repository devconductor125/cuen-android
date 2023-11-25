package com.orquitech.development.cuencaVerde.logic;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.adapters.ManagerCallbackAdapter;
import com.orquitech.development.cuencaVerde.data.api.model.contractor.post.Data;
import com.orquitech.development.cuencaVerde.data.api.model.contractor.post.SiembraDetailsRequest;
import com.orquitech.development.cuencaVerde.data.api.model.erosiveProcess.post.ErosiveProcessData;
import com.orquitech.development.cuencaVerde.data.api.model.erosiveProcess.post.ErosiveProcessRequest;
import com.orquitech.development.cuencaVerde.data.api.model.recursoHidricoSampling.post.RecursoHidricoRequest;
import com.orquitech.development.cuencaVerde.data.api.model.recursoHidricoSampling.post.RecursoHidricoSamplingData;
import com.orquitech.development.cuencaVerde.data.api.model.tasks.post.OpenTaskCommentRequest;
import com.orquitech.development.cuencaVerde.data.db.PersistenceManager;
import com.orquitech.development.cuencaVerde.data.managers.ApiManager;
import com.orquitech.development.cuencaVerde.data.managers.ConnectivityStatusManager;
import com.orquitech.development.cuencaVerde.data.model.ContractSiembra;
import com.orquitech.development.cuencaVerde.data.model.ContractorEvaluation;
import com.orquitech.development.cuencaVerde.data.model.Croquis;
import com.orquitech.development.cuencaVerde.data.model.ErosiveProcess;
import com.orquitech.development.cuencaVerde.data.model.GeometryComment;
import com.orquitech.development.cuencaVerde.data.model.MaintenanceControl;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.MonitoreoRecursoHidricoProcess;
import com.orquitech.development.cuencaVerde.data.model.Municipality;
import com.orquitech.development.cuencaVerde.data.model.PQRS;
import com.orquitech.development.cuencaVerde.data.model.PhotographicRegistryPhoto;
import com.orquitech.development.cuencaVerde.data.model.Predio;
import com.orquitech.development.cuencaVerde.data.model.PredioFollowUp;
import com.orquitech.development.cuencaVerde.data.model.PredioPotencial;
import com.orquitech.development.cuencaVerde.data.model.Province;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.data.model.contractorForm.ContractorForm;
import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GenericFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.model.geoJson.PointFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineStringFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineStringPoint;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon.MultiPolygonFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon.MultiPolygonPoint;
import com.orquitech.development.cuencaVerde.data.model.geoJson.polygon.PolygonFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.polygon.PolygonPoint;
import com.orquitech.development.cuencaVerde.data.model.meetingsWithActors.MeetingsWithActorsForm;
import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.orquitech.development.cuencaVerde.data.model.siembraDetailForm.SiembraDetailForm;
import com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance.StardMonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.stardSheetForm.StardSheetForm;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance.VegetalMaintenance;
import com.orquitech.development.cuencaVerde.data.utils.FilesUtils;
import com.orquitech.development.cuencaVerde.data.utils.MapperUtils;
import com.orquitech.development.cuencaVerde.data.utils.SHAUtils;
import com.orquitech.development.cuencaVerde.data.utils.ServicesUtils;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.fragments.BaseFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class AppPrediosManager extends BaseManager implements PrediosManager {

    private final App app;
    private final Bus bus;
    private final PersistenceManager persistenceManager;
    private final ConnectivityStatusManager connectivityStatusManager;
    private final ApiManager apiManager;
    private final SerializationManager serializationManager;

    private ArrayMap<Long, Predio> predios;
    private final PublishSubject<List<Item>> prediosSubject = PublishSubject.create();
    private final BehaviorSubject<Predio> predioSubject = BehaviorSubject.create();
    private final BehaviorSubject<Predio> savedPredioSubject = BehaviorSubject.create();
    private final BehaviorSubject<PQRS> savedPQRSSubject = BehaviorSubject.create();
    private final PublishSubject<StardMonitorAndMaintenance> sentStardMonitorAndMaintenanceSubject = PublishSubject.create();
    private final PublishSubject<StardMonitorAndMaintenance> stardMonitorAndMaintenanceSubject = PublishSubject.create();
    private final PublishSubject<VegetalMaintenance> sentVegetalMaintenanceSubject = PublishSubject.create();
    private final PublishSubject<VegetalMaintenance> vegetalMaintenanceSubject = PublishSubject.create();
    private final PublishSubject<List<Croquis>> croquisSubject = PublishSubject.create();
    private final PublishSubject<List<Item>> siembraDetailsObservable = PublishSubject.create();
    private final PublishSubject<List<Item>> samplingPointsObservable = PublishSubject.create();
    private final PublishSubject<List<Item>> erosiveProcessFormsObservable = PublishSubject.create();
    private final PublishSubject<PredioFollowUp> sentPredioFollowUpSubject = PublishSubject.create();
    private final PublishSubject<MaintenanceControl> sentMaintenanceControlSubject = PublishSubject.create();
    private final PublishSubject<CartaIntencion> sentCartaIntencionSubject = PublishSubject.create();
    private final PublishSubject<Survey> sentSurveySubject = PublishSubject.create();
    private final BehaviorSubject<Boolean> sentSurveyAndPollSubject = BehaviorSubject.create();
    private final PublishSubject<ContractSiembra> sentContractSiembraSubject = PublishSubject.create();
    private final PublishSubject<GeoJson> contractSiembraGeoJsonSubject = PublishSubject.create();
    private final PublishSubject<List<Province>> provincesSubject = PublishSubject.create();
    private final PublishSubject<List<Municipality>> municipalitiesSubject = PublishSubject.create();
    private final PublishSubject<PredioFollowUp> predioFollowUpSubject = PublishSubject.create();
    private final PublishSubject<CartaIntencion> cartaIntencionSubject = PublishSubject.create();
    private final PublishSubject<StardSheetForm> stardSheetFormSubject = PublishSubject.create();
    private final PublishSubject<ContractorForm> contractorFormSubject = PublishSubject.create();
    private final PublishSubject<SiembraDetailForm> siembraDetailFormSubject = PublishSubject.create();
    private final PublishSubject<SamplingPointForm> samplingPointFormSubject = PublishSubject.create();
    private final PublishSubject<ErosiveProcessForm> erosiveProcessFormSubject = PublishSubject.create();
    private final PublishSubject<MeetingsWithActorsForm> meetingWithActorsFormSubject = PublishSubject.create();
    private final PublishSubject<Boolean> sentMeetingsWithActorsFormSubject = PublishSubject.create();
    private final PublishSubject<GeometryComment> savedGeometryCommentSubject = PublishSubject.create();
    private final PublishSubject<MaintenanceControl> maintenanceControlFormSubject = PublishSubject.create();
    private final PublishSubject<StardSheetForm> sentStardSheetFormSubject = PublishSubject.create();
    private final PublishSubject<ContractorForm> sentContractorFormSubject = PublishSubject.create();
    private final PublishSubject<Boolean> sentSiembraDetailsFormSubject = PublishSubject.create();
    private final PublishSubject<Boolean> sentSamplingPointFormsSubject = PublishSubject.create();
    private final PublishSubject<Boolean> sentErosiveProcessFormsSubject = PublishSubject.create();
    private final PublishSubject<SiembraDetailForm> savedSiembraDetailFormSubject = PublishSubject.create();
    private final PublishSubject<SamplingPointForm> savedSamplingPointFormSubject = PublishSubject.create();
    private final PublishSubject<ErosiveProcessForm> savedErosiveProcessFormSubject = PublishSubject.create();
    private final PublishSubject<MaintenanceControl> sentMonitorAndMaintenanceSubject = PublishSubject.create();
    private final PublishSubject<Boolean> validGeoJsonObservable = PublishSubject.create();
    private final PublishSubject<Boolean> validStardSheetFormObservable = PublishSubject.create();
    private final PublishSubject<Boolean> validMaintenanceControlObservable = PublishSubject.create();
    private final PublishSubject<Boolean> validContractSiembraObservable = PublishSubject.create();
    private final PublishSubject<GeoJson> geoJsonUpSubject = PublishSubject.create();
    private final PublishSubject<GeoJson> taskHasGeoJsonSubject = PublishSubject.create();
    private final PublishSubject<ContractorForm> taskHasContractorFormSubject = PublishSubject.create();
    private final PublishSubject<List<SamplingPointForm>> taskHasSamplingPointFormSubject = PublishSubject.create();
    private final PublishSubject<List<ErosiveProcessForm>> taskHasErosiveProcessFormSubject = PublishSubject.create();
    private final PublishSubject<MeetingsWithActorsForm> taskHasMeetingWithActorsFormSubject = PublishSubject.create();
    private final PublishSubject<GeoJson> geoJsonExecutionBaseSubject = PublishSubject.create();
    private final PublishSubject<GeoJson> geoJsonExecutionEditedSubject = PublishSubject.create();
    private final PublishSubject<ContractorEvaluation> sentContractorEvaluationSubject = PublishSubject.create();
    private final PublishSubject<ContractorEvaluation> contractorEvaluationSubject = PublishSubject.create();
    private final PublishSubject<GeoJson> prediosManagementLayerSubject = PublishSubject.create();
    private final PublishSubject<Long> sentPredioPotencialObservable = PublishSubject.create();
    private boolean sendingPQRS;
    private boolean sendingPrediosPotenciales;
    private boolean sendingStard;
    private boolean sendingVegetableMaintenance;
    private boolean sendingPredioFollowUp;
    private boolean sendingCartaIntencion;
    private final String PREDIOS_MANAGEMENT_FILE = "predios_management.json";
    private GeoJson prediosManagementLayer;
    private String chosenActionType;
    private boolean isPaused;

    public AppPrediosManager(App app, Bus bus, PersistenceManager persistenceManager, ApiManager apiManager, ConnectivityStatusManager connectivityStatusManager, SerializationManager serializationManager) {
        super(bus);
        this.app = app;
        this.bus = bus;
        this.persistenceManager = persistenceManager;
        this.connectivityStatusManager = connectivityStatusManager;
        this.apiManager = apiManager;
        this.serializationManager = serializationManager;
        this.predios = new ArrayMap<>();
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPredios() {
        if (connectivityStatusManager.isConnected()) {
            apiManager.getPrediosPotenciales()
                    .onErrorReturn(ServicesUtils::getPrediosPotencialesExceptionList)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        List<PredioPotencial> predios = MapperUtils.prediosPotencialesResponseToPrediosPotencialesList(response);
                        getCartasAndSurveys(predios);
                        persistenceManager.savePrediosPotenciales(predios, new ManagerCallbackAdapter() {
                            @Override
                            public void onSaved() {
                                persistenceManager.getPrediosPotenciales(0, 1000, new ManagerCallbackAdapter() {
                                    @Override
                                    public void onPrediosPotenciales(List<PredioPotencial> localPredios) {
                                        List<Item> result = new ArrayList<>(localPredios);
                                        prediosSubject.onNext(result);
                                    }
                                });
                            }
                        });
                    });
        } else {
            persistenceManager.getPrediosPotenciales(0, 1000, new ManagerCallbackAdapter() {
                @Override
                public void onPrediosPotenciales(List<PredioPotencial> localPredios) {
                    List<Item> result = new ArrayList<>(localPredios);
                    prediosSubject.onNext(result);
                }
            });
        }
    }

    @SuppressLint("CheckResult")
    private void getCartasAndSurveys(List<PredioPotencial> predios) {
        for (PredioPotencial predioPotencial : predios) {
            apiManager.getCartasAndSurveys(String.valueOf(predioPotencial.getRemoteId()))
                    .observeOn(AndroidSchedulers.mainThread())
                    .onErrorReturn(ServicesUtils::getCartasAndSurveysResponseException)
                    .subscribe(cartaAndSurveyResponse -> {
                        if (cartaAndSurveyResponse.infoGeneral != null) {
                            Survey survey = (Survey) serializationManager.fromJson(cartaAndSurveyResponse.infoGeneral, Survey.class);
                            if (survey != null) {
                                persistenceManager.saveSurvey(survey);
                            }
                        }
                        if (cartaAndSurveyResponse.formLetter != null) {
                            CartaIntencion cartaIntencion = (CartaIntencion) serializationManager.fromJson(cartaAndSurveyResponse.formLetter, CartaIntencion.class);
                            if (cartaIntencion != null) {
                                persistenceManager.saveCartaIntencion(cartaIntencion, null);
                            }
                        }
                    });
        }
    }

    private List<Item> getUniquePredios(List<Item> predios) {
        ArrayList<Item> filteredPredios = new ArrayList<>();
        for (Item predio : predios) {
            boolean isUnique = true;
            for (Item filteredPredio : filteredPredios) {
                boolean haveSameName = ((PredioPotencial) predio).getName().equals(((PredioPotencial) filteredPredio).getName());
                boolean haveSameLatitude = ((PredioPotencial) predio).getLocation().getLatitude() == ((PredioPotencial) filteredPredio).getLocation().getLatitude();
                boolean haveSameLongitude = ((PredioPotencial) predio).getLocation().getLongitude() == ((PredioPotencial) filteredPredio).getLocation().getLongitude();
                if (haveSameName && haveSameLatitude && haveSameLongitude) {
                    isUnique = false;
                }
            }
            if (isUnique) {
                filteredPredios.add(predio);
            }
        }
        return filteredPredios;
    }

    @Override
    public Predio getPredio(long predioId) {
        Predio predio = predios.get(predioId);
        if (predio == null) {
            predio = new Predio();
            predio.setId(predioId);
            predios.put(predioId, predio);
        }
        return predio;
    }

    @Override
    public void addTrackToPredio(long predioId, String trackName, List<LatLng> track) {
        Predio predio = getPredio(predioId);
        predio.addFeatureFromCoordinates(trackName, track);
        persistenceManager.savePredio(predio, new ManagerCallbackAdapter() {
            @Override
            public void onPredio(Predio predio) {
                updatePredio(predio);
                savedPredioSubject.onNext(predio);
            }
        });
    }

    @Override
    public Observable<List<Item>> getPrediosObservable() {
        return prediosSubject;
    }

    @Override
    public Observable<Predio> getPredioObservable() {
        return predioSubject;
    }

    @Override
    public Observable<Predio> getSavedPredioObservable() {
        return savedPredioSubject;
    }

    @Override
    public Observable<PQRS> getSentPQRSObservable() {
        return savedPQRSSubject;
    }

    @Override
    public Observable<Long> getSentPredioPotencialObservable() {
        return sentPredioPotencialObservable;
    }

    @Override
    public Observable<StardMonitorAndMaintenance> getSentStardMonitorAndMaintenanceObservable() {
        return sentStardMonitorAndMaintenanceSubject;
    }

    @Override
    public Observable<StardMonitorAndMaintenance> getStardMonitorAndMaintenanceObservable() {
        return stardMonitorAndMaintenanceSubject;
    }

    @Override
    public Observable<VegetalMaintenance> getSentVegetalMaintenanceObservable() {
        return sentVegetalMaintenanceSubject;
    }

    @Override
    public Observable<VegetalMaintenance> getVegetalMaintenanceObservable() {
        return vegetalMaintenanceSubject;
    }

    @Override
    public Observable<List<Croquis>> getCroquisObservable() {
        return croquisSubject;
    }

    @Override
    public Observable<List<Item>> getSiembraDetailsObservable() {
        return siembraDetailsObservable;
    }

    @Override
    public Observable<List<Item>> getSamplingPointsObservable() {
        return samplingPointsObservable;
    }

    @Override
    public Observable<List<Item>> getErosiveFormsObservable() {
        return erosiveProcessFormsObservable;
    }

    @Override
    public Observable<PredioFollowUp> getSentPredioFollowUpObservable() {
        return sentPredioFollowUpSubject;
    }

    @Override
    public Observable<SiembraDetailForm> getSavedSiembraDetailFormSubject() {
        return savedSiembraDetailFormSubject;
    }

    @Override
    public Observable<SamplingPointForm> getSavedSamplingPointFormSubject() {
        return savedSamplingPointFormSubject;
    }

    @Override
    public Observable<ErosiveProcessForm> getSavedErosiveProcessFormSubject() {
        return savedErosiveProcessFormSubject;
    }

    @Override
    public Observable<CartaIntencion> getSentCartaIntencionObservable() {
        return sentCartaIntencionSubject;
    }

    @Override
    public Observable<Boolean> getSentSurveyAndPollObservable() {
        return sentSurveyAndPollSubject;
    }

    @Override
    public void resetSurveyAndPollObservable() {
        sentSurveyAndPollSubject.onNext(false);
    }

    @Override
    public Observable<Survey> getSentSurveyObservable() {
        return sentSurveySubject;
    }

    @Override
    public Observable<GeoJson> getContractSiembraGeoJSonSubject() {
        return contractSiembraGeoJsonSubject;
    }

    @Override
    public Observable<ContractSiembra> getSentContractSiembraObservable() {
        return sentContractSiembraSubject;
    }

    @Override
    public Observable<PredioFollowUp> getPredioFollowUpObservable() {
        return predioFollowUpSubject;
    }

    @Override
    public Observable<CartaIntencion> getCartaIntencionObservable() {
        return cartaIntencionSubject;
    }

    @Override
    public Observable<StardSheetForm> getStardSheetFormObservable() {
        return stardSheetFormSubject;
    }

    @Override
    public Observable<ContractorForm> getContractorFormObservable() {
        return contractorFormSubject;
    }

    @Override
    public Observable<SiembraDetailForm> getSiembraDetailFormObservable() {
        return siembraDetailFormSubject;
    }

    @Override
    public Observable<SamplingPointForm> getSamplingPointFormSubject() {
        return samplingPointFormSubject;
    }

    @Override
    public Observable<ErosiveProcessForm> getErosiveProcessFormSubject() {
        return erosiveProcessFormSubject;
    }

    @Override
    public Observable<MaintenanceControl> getMaintenanceControlFormObservable() {
        return maintenanceControlFormSubject;
    }

    @Override
    public Observable<StardSheetForm> getSentStardSheetFormObservable() {
        return sentStardSheetFormSubject;
    }

    @Override
    public Observable<ContractorForm> getSentContractorFormSubjectObservable() {
        return sentContractorFormSubject;
    }

    @Override
    public Observable<Boolean> getSentSiembraDetailsFormSubjectObservable() {
        return sentSiembraDetailsFormSubject;
    }

    @Override
    public Observable<Boolean> getSentSamplingPointFormsSubjectObservable() {
        return sentSamplingPointFormsSubject;
    }

    @Override
    public Observable<Boolean> getSentErosiveProcessFormsSubjectObservable() {
        return sentErosiveProcessFormsSubject;
    }

    @Override
    public Observable<Boolean> getSentMeetingWithActorsFormSubjectObservable() {
        return sentMeetingsWithActorsFormSubject;
    }

    @Override
    public Observable<GeometryComment> getSavedGeometryCommentSubjectObservable() {
        return savedGeometryCommentSubject;
    }

    @Override
    public Observable<MaintenanceControl> getSentMaintenanceControlObservable() {
        return sentMaintenanceControlSubject;
    }

    @Override
    public Observable<Boolean> getValidMaintenanceControlObservable() {
        return validMaintenanceControlObservable;
    }

    @Override
    public Observable<Boolean> getValidContractSiembraObservable() {
        return validContractSiembraObservable;
    }

    @Override
    public Observable<Boolean> getValidGeoJsonObservable() {
        return validGeoJsonObservable;
    }

    @Override
    public Observable<Boolean> getValidStardSheetFormObservable() {
        return validStardSheetFormObservable;
    }

    @Override
    public Observable<List<Municipality>> getMunicipalitiesObservable() {
        return municipalitiesSubject;
    }

    @Override
    public void validateStardSheetForm(Task task) {
        persistenceManager.getStardSheet(task, new ManagerCallbackAdapter() {
            @Override
            public void onForm(StardSheetForm stardSheet) {
                validStardSheetFormObservable.onNext(stardSheet.isValid());
            }
        });
    }

    @Override
    public void validateMaintenanceControlForm(MonitorAndMaintenance monitorAndMaintenance) {
        persistenceManager.getMaintenanceControl(monitorAndMaintenance, new ManagerCallbackAdapter() {
            @Override
            public void onMaintenanceControl(MaintenanceControl maintenanceControl) {
                validMaintenanceControlObservable.onNext(maintenanceControl.isValid());
            }
        });
    }

    @Override
    public void validateContractSiembra(ContractSiembra contractSiembra) {
        /*persistenceManager.getMaintenanceControl(monitorAndMaintenance, new ManagerCallbackAdapter() {
            @Override
            public void onMaintenanceControl(MaintenanceControl maintenanceControl) {
                validMaintenanceControlObservable.onNext(maintenanceControl.isValid());
            }
        });*/
    }

    @Override
    public Observable<GeoJson> getGeoJsonObservable() {
        return geoJsonUpSubject;
    }

    @Override
    public Observable<GeoJson> getTaskHasGeoJsonSubjectObservable() {
        return taskHasGeoJsonSubject;
    }

    @Override
    public Observable<ContractorForm> getTaskHasContractorFormSubjectObservable() {
        return taskHasContractorFormSubject;
    }

    @Override
    public Observable<List<SamplingPointForm>> getTaskHasSamplingPointFormSubjectObservable() {
        return taskHasSamplingPointFormSubject;
    }

    @Override
    public Observable<List<ErosiveProcessForm>> getTaskHasErosiveProcessFormSubjectObservable() {
        return taskHasErosiveProcessFormSubject;
    }

    @Override
    public Observable<MeetingsWithActorsForm> getHasMeetingWithActorsFormSubjectObservable() {
        return taskHasMeetingWithActorsFormSubject;
    }

    @Override
    public Observable<GeoJson> getExecutionBaseGeoJsonObservable() {
        return geoJsonExecutionBaseSubject;
    }

    @Override
    public Observable<GeoJson> getExecutionEditedGeoJsonObservable() {
        return geoJsonExecutionEditedSubject;
    }

    @Override
    public Observable<ContractorEvaluation> getSentContractorEvaluationObservable() {
        return sentContractorEvaluationSubject;
    }

    @Override
    public Observable<ContractorEvaluation> getContractorEvaluationObservable() {
        return contractorEvaluationSubject;
    }

    @Override
    public Observable<GeoJson> getPrediosManagementLayerSubjectObservable() {
        return prediosManagementLayerSubject;
    }

    @Override
    public void clearAfterPredioSaved() {
        savedPredioSubject.onNext(new Predio());
    }

    private void updatePredio(Predio predio) {
        predios.put(Long.valueOf(predio.getId()), predio);
    }

    @Override
    public void checkForStardFormToBeSent() {
        if (!sendingStard) {
            sendingStard = true;
            Log.d(getClass().getSimpleName(), "Checking for checkForStardFormToBeSent");
            persistenceManager.getStardMonitorAndMaintenances(0, 100, new ManagerCallbackAdapter() {
                @Override
                public void onStardMonitorAndMaintenances(List<StardMonitorAndMaintenance> stardMonitorAndMaintenanceList) {
                    if (stardMonitorAndMaintenanceList.size() > 0) {
                        for (StardMonitorAndMaintenance stardMonitorAndMaintenance : stardMonitorAndMaintenanceList) {
                            apiManager.sendStardMonitorAndMaintenance(stardMonitorAndMaintenance);
                            persistenceManager.deleteStardMonitorAndMaintenance(stardMonitorAndMaintenance.getMonitorAndMaintenanceId());
                        }
                        String message;
                        if (stardMonitorAndMaintenanceList.size() == 1) {
                            message = TextUtil.getString(app.getApplicationContext(), R.string.stard_form_notification_message);
                        } else {
                            message = TextUtil.getString(app.getApplicationContext(), R.string.stard_forms_notification_message, stardMonitorAndMaintenanceList.size());
                        }
                        app.notifyUser(TextUtil.getString(app.getApplicationContext(), R.string.form_notification_title), message);
                    }
                    sendingStard = false;
                }
            });
        }
    }

    @Override
    public void checkForVegetalMaintenanceFormToBeSent() {
        if (!sendingVegetableMaintenance) {
            sendingVegetableMaintenance = true;
            Log.d(getClass().getSimpleName(), "Checking for checkForVegetalMaintenanceFormToBeSent");
            persistenceManager.getPendingVegetalMaintenances(0, 100, new ManagerCallbackAdapter() {
                @Override
                public void onVegetalMaintenances(List<VegetalMaintenance> vegetalMaintenanceList) {
                    if (vegetalMaintenanceList.size() > 0) {
                        for (VegetalMaintenance vegetalMaintenance : vegetalMaintenanceList) {
                            apiManager.sendVegetalMaintenance(vegetalMaintenance);
                            persistenceManager.deleteVegetalMaintenance(vegetalMaintenance.getMonitorAndMaintenanceId());
                        }
                        String message;
                        if (vegetalMaintenanceList.size() == 1) {
                            message = TextUtil.getString(app.getApplicationContext(), R.string.vegetal_maintenance_notification_message);
                        } else {
                            message = TextUtil.getString(app.getApplicationContext(), R.string.vegetal_maintenances_notification_message, vegetalMaintenanceList.size());
                        }
                        app.notifyUser(TextUtil.getString(app.getApplicationContext(), R.string.form_notification_title), message);
                    }
                    sendingVegetableMaintenance = false;
                }
            });
        }
    }

    @Override
    public void checkForPredioFollowUpFormToBeSent() {
        if (!sendingPredioFollowUp) {
            sendingPredioFollowUp = true;
            Log.d(getClass().getSimpleName(), "Checking for checkForPredioFollowUpFormToBeSent");
            persistenceManager.getPendingPredioFollowUps(0, 100, new ManagerCallbackAdapter() {
                @Override
                public void onPredioFollowUps(List<PredioFollowUp> predioFollowUpList) {
                    if (predioFollowUpList.size() > 0) {
                        for (PredioFollowUp predioFollowUp : predioFollowUpList) {
                            apiManager.sendPredioFollowUp(predioFollowUp);
                            persistenceManager.deletePredioFollowUp(predioFollowUp.getMonitorAndMaintenanceId());
                        }
                        String message;
                        if (predioFollowUpList.size() == 1) {
                            message = TextUtil.getString(app.getApplicationContext(), R.string.predio_follow_up_notification_message);
                        } else {
                            message = TextUtil.getString(app.getApplicationContext(), R.string.predio_follow_ups_notification_message, predioFollowUpList.size());
                        }
                        app.notifyUser(TextUtil.getString(app.getApplicationContext(), R.string.form_notification_title), message);
                    }
                    sendingPredioFollowUp = false;
                }
            });
        }
    }

    @Override
    public void saveCartaIntencion(CartaIntencion cartaIntencion) {
        persistenceManager.saveCartaIntencion(cartaIntencion, null);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getContractSiembra(Task task) {
        String taskId = task.getCleanedId();
        if (connectivityStatusManager.isConnected()) {
            apiManager.getContractSiembraGeoJson(taskId)
                    .observeOn(AndroidSchedulers.mainThread())
                    // .flatMap(apiManager::getStardMonitorAndMaintenance) TODO Get siembra details by contract Id
                    //.observeOn(AndroidSchedulers.mainThread())
                    .subscribe(geoJsonResponse -> {
                        geoJsonResponse = MapperUtils.setGeoJsonFeatures(serializationManager, geoJsonResponse);
                        if (geoJsonResponse.hasValidFeatures()) {
                            GeoJson geoJson = MapperUtils.setGeoJsonFeatures(serializationManager, geoJsonResponse);
                            persistenceManager.saveGeoJson(geoJson, task.getId(), null);
                            contractSiembraGeoJsonSubject.onNext(geoJson);
                        }
                    });
        } else {
            persistenceManager.getGeoJson(task.getId(), new ManagerCallbackAdapter() {
                @Override
                public void onGeoJson(GeoJson geoJson) {
                    contractSiembraGeoJsonSubject.onNext(geoJson);
                }
            });
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void getStardMonitorAndMaintenance(String monitorAndMaintenanceId) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.getStardMonitorAndMaintenanceForm(monitorAndMaintenanceId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(apiManager::getStardMonitorAndMaintenance)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(stardMonitorAndMaintenance -> {
                        if (Integer.valueOf(stardMonitorAndMaintenance.getMonitorAndMaintenanceId()) >= 0) {
                            persistenceManager.saveStardMonitorAndMaintenance(stardMonitorAndMaintenance, null);
                            stardMonitorAndMaintenanceSubject.onNext(stardMonitorAndMaintenance);
                        }
                    });
        } else {
            persistenceManager.getStardMonitorAndMaintenance(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
                @Override
                public void onStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance) {
                    stardMonitorAndMaintenanceSubject.onNext(stardMonitorAndMaintenance);
                }
            });
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.sendStardMonitorAndMaintenance(stardMonitorAndMaintenance)
                    .onErrorReturn(ServicesUtils::getResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getCode() > 0 && response.getCode() != 400) {
                            publishServiceError(response);
                        } else {
                            sentStardMonitorAndMaintenanceSubject.onNext(stardMonitorAndMaintenance);
                            persistenceManager.deleteStardMonitorAndMaintenance(stardMonitorAndMaintenance.getMonitorAndMaintenanceId());
                        }
                    });
        } else {
            stardMonitorAndMaintenance.setAsPendingToBeSent();
            persistenceManager.saveStardMonitorAndMaintenance(stardMonitorAndMaintenance, null);
            sentStardMonitorAndMaintenanceSubject.onNext(stardMonitorAndMaintenance);
        }
    }

    @Override
    public void saveStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance) {
        persistenceManager.saveStardMonitorAndMaintenance(stardMonitorAndMaintenance, null);
    }

    @Override
    public void saveContractSiembra(ContractSiembra contractSiembra) {
        persistenceManager.saveContractSiembra(contractSiembra, null);
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendContractSiembra(ContractSiembra contractSiembra) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.sendContractSiembra(contractSiembra)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getCode() > 0 && response.getCode() != 400) {
                            publishServiceError(response);
                        } else {
                            sentContractSiembraSubject.onNext(contractSiembra);
                            persistenceManager.deleteContractSiembra(contractSiembra.getId());
                        }
                    });
        } else {
            persistenceManager.saveContractSiembra(contractSiembra, null);
        }
    }

    @Override
    public void getStardMonitorAndMaintenanceByMonitorAndMaintenanceId(String monitorAndMaintenanceId) {
        persistenceManager.getStardMonitorAndMaintenance(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
            @Override
            public void onStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance) {
                stardMonitorAndMaintenanceSubject.onNext(stardMonitorAndMaintenance);
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendStardSheetForm(StardSheetForm stardSheetForm, String taskId) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.sendStardSheetForm(stardSheetForm, taskId)
                    .onErrorReturn(ServicesUtils::getResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getCode() > 0 && response.getCode() != 400) {
                            publishServiceError(response);
                        } else {
                            sentStardSheetFormSubject.onNext(stardSheetForm);
                            // persistenceManager.deleteStardSheet(taskId);
                        }
                    });
        } else {
            stardSheetForm.setAsPendingToBeSent();
            persistenceManager.saveStardSheet(stardSheetForm, taskId, null);
            sentStardSheetFormSubject.onNext(stardSheetForm);
        }
    }

    @Override
    public void saveStardSheetForm(StardSheetForm stardSheetForm, String taskId) {
        persistenceManager.saveStardSheet(stardSheetForm, taskId, null);
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendContractorForm(Task task) {
        persistenceManager.getContractorForm(task.getId(), new ManagerCallbackAdapter() {
            @Override
            public void onContractorForm(ContractorForm contractorForm) {
                if (connectivityStatusManager.isConnected()) {
                    apiManager.sendContractorForm(contractorForm, task.getCleanedId())
                            .onErrorReturn(ServicesUtils::getResponseException)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(response -> {
                                sentContractorFormSubject.onNext(contractorForm);
                                // persistenceManager.deleteContractorForm(task.getCleanedId());
                            });

                    sendTaskComment(task, 2);
                }
            }
        });
    }

    @Override
    public void sendSiembraDetailForms(Task task) {
        persistenceManager.getSiembraDetailsByTaskId(task.getId(), new ManagerCallbackAdapter() {
            @SuppressLint("CheckResult")
            @Override
            public void onSiembraDetailList(List<SiembraDetailForm> siembraDetailList) {
                if (siembraDetailList.size() > 0) {

                    SiembraDetailsRequest siembraDetailsRequest = new SiembraDetailsRequest();
                    siembraDetailsRequest.taskId = task.getCleanedId();
                    siembraDetailsRequest.data = new ArrayList<>();

                    Map<String, Parcelable> formsByHash = new HashMap<>();
                    for (SiembraDetailForm siembraDetailForm : siembraDetailList) {
                        formsByHash.put(siembraDetailForm.getFeature().getProperties().getHash(), siembraDetailForm);
                    }

                    for (String hash : formsByHash.keySet()) {
                        Data data = new Data();
                        data.forms = new ArrayList<>();
                        data.hash = hash;
                        for (SiembraDetailForm siembraDetailForm : siembraDetailList) {
                            if (siembraDetailForm.getFeature().getProperties().getHash().equals(hash)) {
                                siembraDetailForm.setFeature(null);
                                data.forms.add(siembraDetailForm);
                            }
                        }
                        siembraDetailsRequest.data.add(data);
                    }

                    apiManager.sendSiembraDetailForms(siembraDetailsRequest)
                            .onErrorReturn(ServicesUtils::getResponseException)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(response -> {
                                if (response.getCode() > 0 && response.getCode() != 200) {
                                    publishServiceError(response);
                                } else {
                                    // persistenceManager.deleteSiembraDetailForms(formsByHash.keySet());
                                    sentSiembraDetailsFormSubject.onNext(true);
                                }
                            });
                } else {
                    sentSiembraDetailsFormSubject.onNext(true);
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendHydrologicalProcessForms(Task task) {
        persistenceManager.getSamplingPointsByTaskId(task.getId(), new ManagerCallbackAdapter() {
            @Override
            public void onSamplingPoints(List<SamplingPointForm> samplingPointForms) {
                if (samplingPointForms.size() > 0) {

                    RecursoHidricoRequest recursoHidricoRequest = new RecursoHidricoRequest();
                    recursoHidricoRequest.taskId = task.getCleanedId();
                    recursoHidricoRequest.data = new ArrayList<>();

                    Map<String, Parcelable> formsByHash = new HashMap<>();
                    for (SamplingPointForm samplingPointForm : samplingPointForms) {
                        samplingPointForm.setHash(SHAUtils.getSha256(serializationManager.toJson(samplingPointForm)));
                        formsByHash.put(samplingPointForm.getFeature().getProperties().getHash(), samplingPointForm);
                    }

                    for (String hash : formsByHash.keySet()) {
                        RecursoHidricoSamplingData data = new RecursoHidricoSamplingData();
                        data.forms = new ArrayList<>();
                        data.hash = hash;
                        for (SamplingPointForm samplingPointForm : samplingPointForms) {
                            if (samplingPointForm.getFeature() != null && samplingPointForm.getFeature().getProperties() != null && samplingPointForm.getFeature().getProperties().getHash().equals(hash)) {
                                samplingPointForm.setFeature(null);
                                data.forms.add(samplingPointForm);
                            }
                        }
                        recursoHidricoRequest.data.add(data);
                    }

                    persistenceManager.getGeoJson(task.getId(), new ManagerCallbackAdapter() {
                        @SuppressLint("CheckResult")
                        @Override
                        public void onGeoJson(GeoJson geoJson) {
                            if (geoJson.hasValidFeatures()) {
                                geoJson.mergeFeatures();
                                recursoHidricoRequest.geojson = geoJson;
                                apiManager.sendRecursoHidricoForms(recursoHidricoRequest)
                                        .onErrorReturn(ServicesUtils::getResponseException)
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(response -> {
                                            if (response.getCode() > 0 && response.getCode() != 200) {
                                                publishServiceError(response);
                                            } else {
                                                for (SamplingPointForm samplingPointForm : samplingPointForms) {
                                                    sendFormPhotographicRegistry(samplingPointForm.getId() + "_sampling_point", samplingPointForm.getHash());
                                                }
                                                // persistenceManager.deleteGeoJson(task.getId());
                                                // persistenceManager.deleteSamplingPointForms(formsByHash.keySet());
                                                sentSamplingPointFormsSubject.onNext(true);
                                            }
                                        });

                                sendTaskComment(task, 2);
                            }
                        }
                    });
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendErosiveProcessForms(Task task) {
        persistenceManager.getErosiveProcessFormsByTaskId(task.getId(), new ManagerCallbackAdapter() {
            @Override
            public void onErosiveProcessForms(List<ErosiveProcessForm> erosiveProcessForms) {
                if (erosiveProcessForms.size() > 0) {

                    ErosiveProcessRequest erosiveProcessRequest = new ErosiveProcessRequest();
                    erosiveProcessRequest.taskId = task.getCleanedId();
                    erosiveProcessRequest.data = new ArrayList<>();

                    Map<String, Parcelable> formsByHash = new HashMap<>();
                    for (ErosiveProcessForm erosiveProcessForm : erosiveProcessForms) {
                        erosiveProcessForm.setHash(SHAUtils.getSha256(serializationManager.toJson(erosiveProcessForm)));
                        formsByHash.put(erosiveProcessForm.getFeature().getProperties().getHash(), erosiveProcessForm);
                    }

                    for (String hash : formsByHash.keySet()) {
                        ErosiveProcessData data = new ErosiveProcessData();
                        data.forms = new ArrayList<>();
                        data.hash = hash;
                        for (ErosiveProcessForm erosiveProcessForm : erosiveProcessForms) {
                            if (erosiveProcessForm.getFeature().getProperties().getHash().equals(hash)) {
                                erosiveProcessForm.setFeature(null);
                                data.forms.add(erosiveProcessForm);
                            }
                        }
                        erosiveProcessRequest.data.add(data);
                    }

                    persistenceManager.getGeoJson(task.getId(), new ManagerCallbackAdapter() {
                        @SuppressLint("CheckResult")
                        @Override
                        public void onGeoJson(GeoJson geoJson) {
                            if (geoJson.hasValidFeatures()) {
                                geoJson.mergeFeatures();
                                erosiveProcessRequest.geojson = geoJson;
                                apiManager.sendErosiveProcessForms(erosiveProcessRequest)
                                        .onErrorReturn(ServicesUtils::getResponseException)
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(response -> {
                                            if (response.getCode() > 0 && response.getCode() != 200) {
                                                publishServiceError(response);
                                            } else {
                                                for (ErosiveProcessForm erosiveProcessForm : erosiveProcessForms) {
                                                    sendFormPhotographicRegistry(erosiveProcessForm.getId() + "_erosive_process_point", erosiveProcessForm.getHash());
                                                }
                                                // persistenceManager.deleteGeoJson(task.getId());
                                                // persistenceManager.deleteErosiveProcessForms(formsByHash.keySet());
                                                sentErosiveProcessFormsSubject.onNext(true);
                                            }
                                        });

                                sendTaskComment(task, 2);
                            }
                        }
                    });
                }
            }
        });
    }

    private void sendFormPhotographicRegistry(String id, String hash) {
        if (connectivityStatusManager.isConnected()) {
            persistenceManager.getPhotographicRegistryPhotos(id, new ManagerCallbackAdapter() {
                @SuppressLint("CheckResult")
                @Override
                public void onPhotographicRegistryPhotos(List<PhotographicRegistryPhoto> photographicRegistryPhotos) {
                    if (photographicRegistryPhotos.size() > 0) {
                        apiManager.uploadPhotosWithHash(app.getApplicationContext().getString(R.string.send_form_images_by_hash_endpoint), hash, photographicRegistryPhotos)
                                .onErrorReturn(ServicesUtils::getResponseException)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(uploadResponse -> {
                                    if (uploadResponse.getCode() > 0 && uploadResponse.getCode() != 200) {
                                        publishServiceError(uploadResponse);
                                    } else {
                                        // deletePhotographicRegistryFiles(photographicRegistryPhotos);
                                    }
                                });
                    }
                }
            });
        }
    }

    private void deletePhotographicRegistryFiles(List<PhotographicRegistryPhoto> photographicRegistryPhotos) {
        List<String> photographicRegistryIds = new ArrayList<>();
        for (PhotographicRegistryPhoto photo : photographicRegistryPhotos) {
            photographicRegistryIds.add(photo.getId());
            FilesUtils.deleteFile(app.getApplicationContext(), photo.getPhotoName());
        }
        persistenceManager.deletePhotographicRegistry(photographicRegistryIds);
    }

    @Override
    public void saveContractorForm(ContractorForm contractorForm, String contractorSiembraId) {
        persistenceManager.saveContractorForm(contractorForm, contractorSiembraId, null);
    }

    @Override
    public void saveSiembraDetailForm(SiembraDetailForm siembraDetailForm, String taskId) {
        if (siembraDetailForm != null) {
            Feature feature = siembraDetailForm.getFeature();
            if (feature != null && feature.getProperties() != null && !TextUtils.isEmpty(feature.getProperties().getHash())) {
                persistenceManager.saveSiembraDetailForm(siembraDetailForm, feature.getProperties().getHash(), taskId, new ManagerCallbackAdapter() {
                    @Override
                    public void onSiembraDetailForm(SiembraDetailForm siembraDetailForm) {
                        savedSiembraDetailFormSubject.onNext(siembraDetailForm);
                    }
                });
            }
        }
    }

    @Override
    public void saveSamplingPointForm(SamplingPointForm samplingPointForm, String taskId) {
        if (samplingPointForm != null) {
            Feature feature = samplingPointForm.getFeature();
            if (feature != null && feature.getProperties() != null && !TextUtils.isEmpty(feature.getProperties().getHash())) {
                persistenceManager.saveSamplingPointForm(samplingPointForm, feature.getProperties().getHash(), taskId, new ManagerCallbackAdapter() {
                    @Override
                    public void onSamplingPointForm(SamplingPointForm siembraDetailForm) {
                        savedSamplingPointFormSubject.onNext(siembraDetailForm);
                    }
                });
            }
        }
    }

    @Override
    public void saveErosiveProcessForm(ErosiveProcessForm erosiveProcessForm, String taskId) {
        if (erosiveProcessForm != null) {
            Feature feature = erosiveProcessForm.getFeature();
            if (feature != null && feature.getProperties() != null && !TextUtils.isEmpty(feature.getProperties().getHash())) {
                persistenceManager.saveErosiveProcessForm(erosiveProcessForm, feature.getProperties().getHash(), taskId, new ManagerCallbackAdapter() {
                    @Override
                    public void onErosiveProcessForm(ErosiveProcessForm erosiveProcessForm) {
                        savedErosiveProcessFormSubject.onNext(erosiveProcessForm);
                    }
                });
            }
        }
    }

    @Override
    public void sendPQRS(PQRS pqrs) {
        apiManager.sendPQRS(pqrs);
    }

    @Override
    public void savePQRS(PQRS pqrs) {
        persistenceManager.savePQRS(pqrs);
    }

    @Override
    public void checkForPQRS() {
        if (!sendingPQRS) {
            sendingPQRS = true;
            persistenceManager.getAllPQRS(new ManagerCallbackAdapter() {
                @Override
                public void onPQRSList(List<PQRS> pqrsList) {
                    if (pqrsList.size() > 0) {
                        for (PQRS pqrs : pqrsList) {
                            apiManager.sendPQRS(pqrs);
                        }
                        String message = TextUtil.getString(app.getApplicationContext(), R.string.pqrs_notification_message, pqrsList.size());
                        app.notifyUser(TextUtil.getString(app.getApplicationContext(), R.string.pqrs_notification_title), message);
                        persistenceManager.deleteAllPQRS();
                    }
                    sendingPQRS = false;
                }
            });
        }
    }

    @Override
    public void saveVegetalMaintenance(VegetalMaintenance vegetalMaintenance) {
        persistenceManager.saveVegetalMaintenance(vegetalMaintenance, null);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getVegetalMaintenance(String monitorAndMaintenanceId) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.getVegetalMaintenance(monitorAndMaintenanceId)
                    .onErrorReturn(ServicesUtils::getResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getCode() > 0 && response.getCode() != 400) {
                            publishServiceError(response);
                        } else {
                            if (response.data != null) {
                                vegetalMaintenanceSubject.onNext((VegetalMaintenance) serializationManager.fromJson(response.data, VegetalMaintenance.class));
                            }
                        }
                    });
        } else {
            // TODO get from DB
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCroquis(String predioId) {
        persistenceManager.getCroquis(predioId, new ManagerCallbackAdapter() {
            @Override
            public void onCroquisList(List<Croquis> croquis) {
                croquisSubject.onNext(croquis);
            }
        });
    }

    @Override
    public void getVegetalMaintenanceByMonitorAndMaintenanceId(String monitorAndMaintenanceId) {
        persistenceManager.getVegetalMaintenanceByMonitorAndMaintenanceId(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
            @Override
            public void onVegetalMaintenance(VegetalMaintenance vegetalMaintenance) {
                vegetalMaintenanceSubject.onNext(vegetalMaintenance);
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendVegetalMaintenance(VegetalMaintenance vegetalMaintenance) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.sendVegetalMaintenance(vegetalMaintenance)
                    .onErrorReturn(ServicesUtils::getResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getCode() > 0 && response.getCode() != 400) {
                            publishServiceError(response);
                        } else {
                            sentVegetalMaintenanceSubject.onNext(vegetalMaintenance);
                            persistenceManager.deleteVegetalMaintenance(vegetalMaintenance.getMonitorAndMaintenanceId());
                        }
                    });
        } else {
            vegetalMaintenance.setAsPendingToBeSent();
            persistenceManager.saveVegetalMaintenance(vegetalMaintenance, null);
            sentVegetalMaintenanceSubject.onNext(vegetalMaintenance);
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPredioFollowUp(String monitorAndMaintenanceId) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.getPredioFollowUp(monitorAndMaintenanceId)
                    .onErrorReturn(ServicesUtils::getResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getCode() > 0 && response.getCode() != 400) {
                            publishServiceError(response);
                        } else {
                            if (response.data != null) {
                                predioFollowUpSubject.onNext((PredioFollowUp) serializationManager.fromJson(response.data, PredioFollowUp.class));
                            }
                        }
                    });
        } else {
            persistenceManager.getPredioFollowUpByMonitorAndMaintenanceId(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
                @Override
                public void onPredioFollowUp(PredioFollowUp predioFollowUp) {
                    predioFollowUpSubject.onNext(predioFollowUp);
                }
            });
        }
    }

    @Override
    public void savePredioFollowUp(PredioFollowUp predioFollowUp) {
        persistenceManager.savePredioFollowUp(predioFollowUp, null);
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendPredioFollowUp(PredioFollowUp predioFollowUp) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.sendPredioFollowUp(predioFollowUp)
                    .onErrorReturn(ServicesUtils::getResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getCode() > 0 && response.getCode() != 400) {
                            publishServiceError(response);
                        } else {
                            sentPredioFollowUpSubject.onNext(predioFollowUp);
                            persistenceManager.deletePredioFollowUp(predioFollowUp.getMonitorAndMaintenanceId());
                        }
                    });
        } else {
            predioFollowUp.setAsPendingToBeSent();
            persistenceManager.savePredioFollowUp(predioFollowUp, null);
            sentPredioFollowUpSubject.onNext(predioFollowUp);
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendSurvey(PredioPotencial predioPotencial, Survey survey) {
        if (survey.isValid()) {
            apiManager.sendSurvey(String.valueOf(predioPotencial.getRemoteId()), survey)
                    .onErrorReturn(ServicesUtils::getSurveyResponseExceptionList)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getCode() != 200) {
                            publishServiceError(response);
                        } else {
                            survey.setHasBeenSent();
                            persistenceManager.saveSurvey(survey, new ManagerCallbackAdapter() {
                                @Override
                                public void onSurvey(Survey survey) {
                                    sentSurveySubject.onNext(survey);
                                }
                            });
                            if (String.valueOf(response.response.poll).equals("200")
                                    && String.valueOf(response.response.letter).equals("200")) {
                                sentSurveyAndPollSubject.onNext(true);
                                persistenceManager.deletePredioByRemoteId(predioPotencial.getRemoteId());
                            }
                        }
                    });
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendCartaIntencion(PredioPotencial predioPotencial, CartaIntencion cartaIntencion) {
        if (cartaIntencion.isValid()) {
            //venus
            persistenceManager.getPhotographicRegistryPhotos(cartaIntencion.getPropertyName(), new ManagerCallbackAdapter() {
                @Override
                public void onPhotographicRegistryPhotos(List<PhotographicRegistryPhoto> photographicRegistryPhotos) {
                    apiManager.sendCartaIntencion(String.valueOf(predioPotencial.getRemoteId()), cartaIntencion, photographicRegistryPhotos)
                        .onErrorReturn(ServicesUtils::getSurveyResponseExceptionList)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            if (response.getCode() > 0 && response.getCode() != 200) {
                                publishServiceError(response);
                            } else {
                                cartaIntencion.setHasBeenSent();
                                persistenceManager.saveCartaIntencion(cartaIntencion, new ManagerCallbackAdapter() {
                                    @Override
                                    public void onCartaIntencion(CartaIntencion cartaIntencion) {
                                        sentCartaIntencionSubject.onNext(cartaIntencion);
                                    }
                                });
                                if (String.valueOf(response.response.poll).equals("200")
                                        && String.valueOf(response.response.letter).equals("200")) {
                                    sentSurveyAndPollSubject.onNext(true);
                                    persistenceManager.deletePredioByRemoteId(predioPotencial.getRemoteId());
                                }
                            }
                        });
                }
            });
        }
    }

    @Override
    public void getPredioFollowUpByMonitorAndMaintenanceId(String monitorAndMaintenanceId) {
        persistenceManager.getPredioFollowUpByMonitorAndMaintenanceId(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
            @Override
            public void onPredioFollowUp(PredioFollowUp predioFollowUp) {
                predioFollowUpSubject.onNext(predioFollowUp);
            }
        });
    }

    @Override
    public void getCartaIntencionByPredioPotencialId(String cartaIntencionId) {
        persistenceManager.getCartaIntencion(cartaIntencionId, new ManagerCallbackAdapter() {
            @Override
            public void onCartaIntencion(CartaIntencion cartaIntencion) {
                cartaIntencionSubject.onNext(cartaIntencion);
            }
        });
    }

    @Override
    public void getStardSheetForm(Task task) {
        persistenceManager.getStardSheet(task, new ManagerCallbackAdapter() {
            @Override
            public void onForm(StardSheetForm stardSheetForm) {
                if (!stardSheetForm.isValidObject() && connectivityStatusManager.isConnected()) {
                    /*apiManager.getStardSheet(task.getId()
                            .onErrorReturn(ServicesUtils::getCartaIntencionResponseException)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(response -> {
                                if (response.getCode() > 0 && response.getCode() != 400) {
                                    publishServiceError(response);
                                } else {
                                    if (response.data != null) {
                                        stardSheetFormSubject.onNext(MapperUtils.cartaIntencionResponseToCartaIntencion(response));
                                    }
                                }
                            });*/
                } else {
                    stardSheetFormSubject.onNext(stardSheetForm);
                }
            }
        });
    }

    @Override
    public void getContractorForm(Task task) {
        persistenceManager.getContractorForm(task.getId(), new ManagerCallbackAdapter() {
            @Override
            public void onContractorForm(ContractorForm contractorForm) {
                contractorFormSubject.onNext(contractorForm);
            }
        });
    }

    @Override
    public void getSiembraDetailForm(String siembraDetailFormId) {
        persistenceManager.getSiembraDetailForm(siembraDetailFormId, new ManagerCallbackAdapter() {
            @Override
            public void onSiembraDetailForm(SiembraDetailForm siembraDetailForm) {
                siembraDetailFormSubject.onNext(siembraDetailForm);
            }
        });
    }

    @Override
    public void getSamplingPointForm(String samplingPointFormId) {
        persistenceManager.getSamplingPointForm(samplingPointFormId, new ManagerCallbackAdapter() {
            @Override
            public void onSamplingPointForm(SamplingPointForm siembraDetailForm) {
                samplingPointFormSubject.onNext(siembraDetailForm);
            }
        });
    }

    @Override
    public void getErosivePointForm(String erosivePointFormId) {
        persistenceManager.getErosiveProcessForm(erosivePointFormId, new ManagerCallbackAdapter() {
            @Override
            public void onErosiveProcessForm(ErosiveProcessForm erosiveProcessForm) {
                erosiveProcessFormSubject.onNext(erosiveProcessForm);
            }
        });
    }

    @Override
    public void getGeoJson(Task task) {
        persistenceManager.getGeoJson(task.getId(), new ManagerCallbackAdapter() {
            @SuppressLint("CheckResult")
            @Override
            public void onGeoJson(GeoJson geoJson) {
                if (geoJson.hasValidFeatures()) {
                    geoJsonUpSubject.onNext(geoJson);
                } else {
                    if (connectivityStatusManager.isConnected()) {
                        String endPoint = app.getApplicationContext().getString(R.string.geo_json_endpoint);
                        if (task instanceof ErosiveProcess ||
                                task instanceof MonitoreoRecursoHidricoProcess ||
                                task.getTaskType().equals(BaseFragment.PSA)) {
                            endPoint = app.getApplicationContext().getString(R.string.geo_json_open_task_endpoint);
                        }
                        apiManager.getGeoJson(task.getId(), endPoint)
                                .onErrorReturn(ServicesUtils::getGeoJsonResponseException)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(response -> {
                                    if (response.getCode() > 0 && response.getCode() != 400) {
                                        publishServiceError(response);
                                    } else {
                                        if (response.getType() != null) {
                                            GeoJson geoJsonResponse = MapperUtils.setGeoJsonFeatures(serializationManager, response);
                                            geoJsonUpSubject.onNext(geoJsonResponse);
                                            persistenceManager.saveGeoJson(geoJsonResponse, task.getId(), null);
                                        }
                                    }
                                });
                    }
                }
            }
        });
    }

    @Override
    public void taskHasGeoJson(Task task) {
        persistenceManager.getGeoJson(task.getId(), new ManagerCallbackAdapter() {
            @SuppressLint("CheckResult")
            @Override
            public void onGeoJson(GeoJson geoJson) {
                taskHasGeoJsonSubject.onNext(geoJson);
            }
        });
    }

    @Override
    public void taskHasContractorForm(Task task) {
        persistenceManager.getContractorForm(task.getId(), new ManagerCallbackAdapter() {
            @Override
            public void onContractorForm(ContractorForm contractorForm) {
                taskHasContractorFormSubject.onNext(contractorForm);
            }
        });
    }

    @Override
    public void taskHasHydrologicalProcessForms(Task task) {
        persistenceManager.getSamplingPointsByTaskId(task.getId(), new ManagerCallbackAdapter() {
            @Override
            public void onSamplingPoints(List<SamplingPointForm> samplingPointForms) {
                taskHasSamplingPointFormSubject.onNext(samplingPointForms);
            }
        });
    }

    @Override
    public void taskHasErosiveProcessForms(Task task) {
        persistenceManager.getErosiveProcessFormsByTaskId(task.getId(), new ManagerCallbackAdapter() {
            @Override
            public void onErosiveProcessForms(List<ErosiveProcessForm> erosiveProcessFormList) {
                taskHasErosiveProcessFormSubject.onNext(erosiveProcessFormList);
            }
        });
    }

    @Override
    public void taskHasMeetingWithActorsForm(Task task) {
        persistenceManager.getMeetingsWithActorsForm(task.getId(), new ManagerCallbackAdapter() {
            @Override
            public void onMeetingsWithActorsForm(MeetingsWithActorsForm meetingsWithActorsForm) {
                taskHasMeetingWithActorsFormSubject.onNext(meetingsWithActorsForm);
            }
        });
    }

    @Override
    public void getExecutionTaskBaseGeoJson(Task task) {
        persistenceManager.getExecutionTaskBaseGeoJson(task.getId(), new ManagerCallbackAdapter() {
            @SuppressLint("CheckResult")
            @Override
            public void onGeoJson(GeoJson geoJson) {
                if (geoJson.hasValidFeatures()) {
                    geoJsonExecutionBaseSubject.onNext(geoJson);
                }
            }
        });
    }

    @Override
    public void getExecutionTaskEditedGeoJson(Task task) {
        persistenceManager.getExecutionTaskEditedGeoJson(task.getId(), new ManagerCallbackAdapter() {
            @SuppressLint("CheckResult")
            @Override
            public void onGeoJson(GeoJson geoJson) {
                if (geoJson.hasValidFeatures()) {
                    geoJsonExecutionEditedSubject.onNext(geoJson);
                }
            }
        });
    }

    @Override
    public void saveContractorEvaluation(ContractorEvaluation contractorEvaluation) {
        persistenceManager.saveContractorEvaluation(contractorEvaluation, null);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getContractorEvaluation(String monitorAndMaintenanceId) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.getContractorEvaluation(monitorAndMaintenanceId)
                    .onErrorReturn(ServicesUtils::getResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getCode() > 0 && response.getCode() != 400) {
                            publishServiceError(response);
                        } else {
                            if (response.data != null) {
                                contractorEvaluationSubject.onNext((ContractorEvaluation) serializationManager.fromJson(response.data, ContractorEvaluation.class));
                            }
                        }
                    });
        } else {
            persistenceManager.getContractorEvaluationByMonitorAndMaintenanceId(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
                @Override
                public void onContractorEvaluation(ContractorEvaluation contractorEvaluation) {
                    contractorEvaluationSubject.onNext(contractorEvaluation);
                }
            });
        }
    }

    @Override
    public void getContractorEvaluationByMonitorAndMaintenanceId(String monitorAndMaintenanceId) {
        persistenceManager.getContractorEvaluationByMonitorAndMaintenanceId(monitorAndMaintenanceId, new ManagerCallbackAdapter() {
            @Override
            public void onContractorEvaluation(ContractorEvaluation contractorEvaluation) {
                contractorEvaluationSubject.onNext(contractorEvaluation);
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendContractorEvaluation(ContractorEvaluation contractorEvaluation) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.sendContractorEvaluation(contractorEvaluation)
                    .onErrorReturn(ServicesUtils::getResponseException)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.getCode() > 0 && response.getCode() != 400) {
                            publishServiceError(response);
                        } else {
                            sentContractorEvaluationSubject.onNext(contractorEvaluation);
                            persistenceManager.deleteContractorEvaluation(contractorEvaluation.getMonitorAndMaintenanceId());
                        }
                    });
        } else {
            contractorEvaluation.setAsPendingToBeSent();
            persistenceManager.saveContractorEvaluation(contractorEvaluation, null);
            sentContractorEvaluationSubject.onNext(contractorEvaluation);
        }
    }

    @Override
    public void getMaintenanceControl(MonitorAndMaintenance monitorAndMaintenance) {
        persistenceManager.getMaintenanceControl(monitorAndMaintenance, new ManagerCallbackAdapter() {
            @Override
            public void onMaintenanceControl(MaintenanceControl maintenanceControl) {
                maintenanceControlFormSubject.onNext(maintenanceControl);
            }
        });
    }

    @Override
    public void sendMaintenanceControlForm(MonitorAndMaintenance monitorAndMaintenance, String monitorAndMaintenanceId) {
        persistenceManager.getMaintenanceControl(monitorAndMaintenance, new ManagerCallbackAdapter() {
            @Override
            public void onMaintenanceControl(MaintenanceControl maintenanceControl) {
                apiManager.sendMaintenanceControl(maintenanceControl, monitorAndMaintenanceId)
                        .subscribe();
            }
        });
    }

    @Override
    public void saveMaintenanceControl(MaintenanceControl maintenanceControl, String monitorAndMaintenanceId) {
        if (maintenanceControl.isValid()) {
            persistenceManager.saveMaintenanceControl(maintenanceControl, monitorAndMaintenanceId, null);
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void getProvinces() {
        apiManager.getProvinces()
                .onErrorReturn(ServicesUtils::getProvincesExceptionList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.size() > 0) {
                        List<Province> provinces = MapperUtils.provincesResponseToProvincesList(response);
                        persistenceManager.saveProvinces(provinces);
                        getMunicipalities(2);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPrediosManagementLayer() {
        apiManager.getPrediosManagementLayer()
                .onErrorReturn(error -> getErrorResponseBody())
                .doOnNext(responseBody -> FilesUtils.saveFileFromStream(app.getApplicationContext(), responseBody, PREDIOS_MANAGEMENT_FILE))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d(getClass().getSimpleName(), "Saved predios Layer"))
                .subscribe();
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMunicipalities(int provinceId) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.getMunicipalities(provinceId)
                    .onErrorReturn(ServicesUtils::geMunicipalitiesExceptionList)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.size() > 0) {
                            List<Municipality> municipalities = MapperUtils.municipalitiesResponseToProvincesList(response);
                            persistenceManager.saveMunicipalities(municipalities);
                        }
                    });
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void getLocalMunicipalities() {
        persistenceManager.getMunicipalities(new ManagerCallbackAdapter() {
            @Override
            public void onMunicipalities(List<Municipality> municipalities) {
                municipalitiesSubject.onNext(municipalities);
            }
        });
    }

    @Override
    public Observable<List<SiembraDetailForm>> getSiembraDetails(String hash) {
        return Observable.create((ObservableEmitter<List<SiembraDetailForm>> subscriber) -> {
            persistenceManager.getSiembraDetails(hash, new ManagerCallbackAdapter() {
                @Override
                public void onSiembraDetailList(List<SiembraDetailForm> siembraDetailList) {
                    List<Item> result = new ArrayList<>(siembraDetailList);
                    siembraDetailsObservable.onNext(result);
                }
            });
        });
    }

    @Override
    public Observable<List<SamplingPointForm>> getSamplingPoints(String hash) {
        return Observable.create((ObservableEmitter<List<SamplingPointForm>> subscriber) -> {
            persistenceManager.getSamplingPoints(hash, new ManagerCallbackAdapter() {
                @Override
                public void onSamplingPoints(List<SamplingPointForm> samplingPointFormList) {
                    List<Item> result = new ArrayList<>(samplingPointFormList);
                    samplingPointsObservable.onNext(result);
                }
            });
        });
    }

    @Override
    public Observable<List<ErosiveProcessForm>> getErosiveProcessForms(String hash) {
        return Observable.create((ObservableEmitter<List<ErosiveProcessForm>> subscriber) -> {
            persistenceManager.getErosiveProcessForms(hash, new ManagerCallbackAdapter() {
                @Override
                public void onErosiveProcessForms(List<ErosiveProcessForm> erosiveProcessFormList) {
                    List<Item> result = new ArrayList<>(erosiveProcessFormList);
                    erosiveProcessFormsObservable.onNext(result);
                }
            });
        });
    }

    @Override
    public void deleteContractorForm(String taskId) {
        persistenceManager.deleteContractorForm(taskId);
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendContractorTask(String taskId) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.endContractorTask(taskId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                    });
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void endCommunicationsTask(Task task) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.endCommunicationsTask(task.getCleanedId())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> persistenceManager.deletePhotographicRegistryByTaskId(task.getId()));
        }
    }

    @Override
    public void endPsaTask(Task task) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.endPsaTask(task.getCleanedId())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> persistenceManager.deletePhotographicRegistryByTaskId(task.getId()));
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void endSpecialTask(Task task) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.endSpecialTask(task.getCleanedId())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> sendTaskComment(task, 2));
        }
    }

    @Override
    public void sendDocuments(PredioPotencial predio) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void sendPredioPotencial(PredioPotencial predioPotencial) {
        if (connectivityStatusManager.isConnected()) {
            apiManager.sendPredioPotencial(predioPotencial)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(predioPotencialResponse -> {
                        if (predioPotencialResponse.getCode() > 0 && predioPotencialResponse.getCode() == 200) {
                            sentPredioPotencialObservable.onNext(Long.valueOf(predioPotencialResponse.remoteId));
                            getPredios();
                        }
                    });
        } else {
            persistenceManager.savePredioPotencial(predioPotencial, null);
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void endExecutionTask(Task task) {
        String taskId = task.getCleanedId();

        apiManager.endExecutionTask(taskId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> persistenceManager.deleteGeoJson(task.getId()));

        sendTaskComment(task, 1);
    }

    private ResponseBody getErrorResponseBody() {
        return new ResponseBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public long contentLength() {
                return 0;
            }

            @Override
            public BufferedSource source() {
                return null;
            }
        };
    }

    @Override
    public void clearPrediosManagementLayer() {
        prediosManagementLayer = null;
    }

    @Override
    public void getPrediosManagementLayerFromFile(Location newLocation) {
        if ((prediosManagementLayer != null && prediosManagementLayer.features == null) || (prediosManagementLayer != null && prediosManagementLayer.features.size() == 0)) {
            prediosManagementLayer = null;
        }
        if (prediosManagementLayer == null) {
            Observable.just(newLocation)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.newThread())
                    .flatMap(location -> {
                        String result = FilesUtils.readFromFile(app.getApplicationContext(), PREDIOS_MANAGEMENT_FILE);
                        GeoJson geoJson = null;
                        if (!TextUtils.isEmpty(result)) {
                            geoJson = (GeoJson) serializationManager.fromJson(result, GeoJson.class);
                        }
                        if (geoJson != null) {
                            getClosePredios(geoJson, location);
                        } else {
                            geoJson = new GeoJson();
                        }
                        return Observable.just(geoJson);
                    })
                    .doOnError(error -> Log.e(getClass().getSimpleName(), error.getMessage()))
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(geoJson -> {
                        prediosManagementLayer = geoJson;
                        prediosManagementLayerSubject.onNext(geoJson);
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> Log.d(getClass().getSimpleName(), "Got predios Layer"))
                    .subscribe();
        } else {
            prediosManagementLayerSubject.onNext(prediosManagementLayer);
        }
    }

    private void getClosePredios(GeoJson geoJson, Location location) {
        double offsetValue = 0.0015;
        List<Object> filteredFeatures = new ArrayList<>();
        for (Object object : geoJson.features) {
            String featureString = serializationManager.toJson(object);
            GenericFeature feature = (GenericFeature) serializationManager.fromJson(featureString, GenericFeature.class);
            if (feature != null) {
                switch (feature.geometry.getType()) {
                    case GeoJson.MULTI_LINE_STRING:
                        featureString = serializationManager.toJson(object);
                        MultiLineStringFeature feature1 = (MultiLineStringFeature) serializationManager.fromJson(featureString, MultiLineStringFeature.class);
                        if (isInBoundingBox(feature1, location, offsetValue)) {
                            filteredFeatures.add(feature1);
                        }
                        break;
                    case GeoJson.POINT:
                        featureString = serializationManager.toJson(object);
                        PointFeature feature2 = (PointFeature) serializationManager.fromJson(featureString, PointFeature.class);
                        if (isInBoundingBox(feature2, location, offsetValue)) {
                            filteredFeatures.add(feature2);
                        }
                        break;
                    case GeoJson.POLYGON:
                        featureString = serializationManager.toJson(object);
                        PolygonFeature feature3 = (PolygonFeature) serializationManager.fromJson(featureString, PolygonFeature.class);
                        if (isInBoundingBox(feature3, location, offsetValue)) {
                            filteredFeatures.add(feature3);
                        }
                        break;
                    case GeoJson.MULTI_POLYGON:
                        featureString = serializationManager.toJson(object);
                        MultiPolygonFeature feature4 = (MultiPolygonFeature) serializationManager.fromJson(featureString, MultiPolygonFeature.class);
                        if (isInBoundingBox(feature4, location, offsetValue)) {
                            filteredFeatures.add(feature4);
                        }
                        break;
                }
            }
        }

        geoJson.features = filteredFeatures;
    }

    private boolean isInBoundingBox(MultiPolygonFeature feature, Location location, double offsetValue) {
        boolean result = false;
        if (feature.geometry.coordinates.size() > 0) {
            if (feature.geometry.coordinates.get(0) != null) {
                if (feature.geometry.coordinates.get(0).get(0) != null) {
                    if (feature.geometry.coordinates.get(0).get(0).get(0) != null) {
                        MultiPolygonPoint multiPolygonPoint = feature.geometry.coordinates.get(0).get(0).get(0);
                        if (multiPolygonPoint.size() > 1) {
                            double longitude = multiPolygonPoint.get(0);
                            double latitude = multiPolygonPoint.get(1);
                            result = validLatitudeAndLongitude(location, latitude, longitude, offsetValue);
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean isInBoundingBox(PolygonFeature feature, Location location, double offsetValue) {
        boolean result = false;
        if (feature.geometry.coordinates.size() > 0) {
            if (feature.geometry.coordinates.get(0) != null) {
                if (feature.geometry.coordinates.get(0).get(0) != null) {
                    PolygonPoint polygonPoint = feature.geometry.coordinates.get(0).get(0);
                    if (polygonPoint.size() > 1) {
                        double longitude = polygonPoint.get(0);
                        double latitude = polygonPoint.get(1);
                        result = validLatitudeAndLongitude(location, latitude, longitude, offsetValue);
                    }
                }
            }
        }
        return result;
    }

    private boolean isInBoundingBox(PointFeature feature, Location location, double offsetValue) {
        boolean result = false;
        if (feature.geometry.coordinates.size() > 1) {
            double longitude = feature.geometry.coordinates.get(0);
            double latitude = feature.geometry.coordinates.get(1);
            result = validLatitudeAndLongitude(location, latitude, longitude, offsetValue);
        }
        return result;
    }

    private boolean isInBoundingBox(MultiLineStringFeature feature, Location location, double offsetValue) {
        boolean result = false;
        if (feature.geometry.coordinates.size() > 0) {
            if (feature.geometry.coordinates.get(0) != null) {
                if (feature.geometry.coordinates.get(0).get(0) != null) {
                    MultiLineStringPoint multiLineStringPoint = feature.geometry.coordinates.get(0).get(0);
                    if (multiLineStringPoint.size() > 1) {
                        double longitude = multiLineStringPoint.get(0);
                        double latitude = multiLineStringPoint.get(1);
                        result = validLatitudeAndLongitude(location, latitude, longitude, offsetValue);
                    }
                }
            }
        }
        return result;
    }

    private boolean validLatitudeAndLongitude(Location location, double pointLatitude, double pointLongitude, double offsetValue) {
        double offset = location.getLatitude() * offsetValue;
        double maxLat = location.getLatitude() - offset;
        double minLat = location.getLatitude() + offset;
        double maxLng = location.getLongitude() - offset;
        double minLng = location.getLongitude() + offset;

        boolean isValid = false;
        if (pointLongitude > maxLng && pointLongitude < minLng) {
            if (pointLatitude > maxLat && pointLatitude < minLat) {
                isValid = true;
            }
        }
        return isValid;
    }

    @Override
    public void getMeetingsWithActorsForm(String taskId) {
        persistenceManager.getMeetingsWithActorsForm(taskId, new ManagerCallbackAdapter() {
            @Override
            public void onMeetingsWithActorsForm(MeetingsWithActorsForm meetingsWithActorsForm) {
                meetingWithActorsFormSubject.onNext(meetingsWithActorsForm);
            }
        });
    }

    @Override
    public Observable<MeetingsWithActorsForm> getMeetingsWithActorsFormObservable() {
        return meetingWithActorsFormSubject;
    }

    @Override
    public void saveMeetingsWithActorsForm(MeetingsWithActorsForm meetingsWithActorsForm, Task task) {
        persistenceManager.saveMeetingsWithActorsForm(meetingsWithActorsForm, task.getId(), null);
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendMeetingsWithActorsForm(Task task) {
        persistenceManager.getMeetingsWithActorsForm(task.getId(), new ManagerCallbackAdapter() {
            @Override
            public void onMeetingsWithActorsForm(MeetingsWithActorsForm meetingsWithActorsForm) {
                if (connectivityStatusManager.isConnected()) {
                    apiManager.sendMeetingsWithActorsForm(meetingsWithActorsForm, task.getCleanedId())
                            .onErrorReturn(ServicesUtils::getResponseException)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(response -> {
                                if (response.code > 0 && response.code == 200) {
                                    sentMeetingsWithActorsFormSubject.onNext(true);
                                    // persistenceManager.deleteMeetingsWithActorsForm(task.getId());
                                }
                            });

                    sendTaskComment(task, 2);
                }
            }
        });
    }

    @Override
    public void saveGeometryComment(String taskId, GeometryComment geometryComment) {
        persistenceManager.saveGeometryComment(taskId, geometryComment);
    }

    @Override
    public void getGeometryComment(String taskId, String hash) {
        persistenceManager.getGeometryComment(taskId, hash, new ManagerCallbackAdapter() {
            @Override
            public void onGeometryComment(GeometryComment geometryComment) {
                savedGeometryCommentSubject.onNext(geometryComment);
            }
        });
    }

    @Override
    public void setChosenActionType(String chosenActionType) {
        this.chosenActionType = chosenActionType;
    }

    @Override
    public String getChosenActionType() {
        return chosenActionType;
    }

    @Override
    public void setIsMeasurementPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    @Override
    public boolean isMeasurementPaused() {
        return isPaused;
    }

    private void sendTaskComment(Task task, int type) {
        if (!TextUtils.isEmpty(task.getNewComment())) {
            OpenTaskCommentRequest openTaskComment = new OpenTaskCommentRequest();
            openTaskComment.taskId = Integer.parseInt(task.getCleanedId());
            openTaskComment.type = type;
            openTaskComment.description = task.getNewComment();
            apiManager.sendOpenTaskComment(openTaskComment).subscribe();
        }
    }
}
