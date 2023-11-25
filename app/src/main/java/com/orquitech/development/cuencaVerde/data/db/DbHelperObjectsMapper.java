package com.orquitech.development.cuencaVerde.data.db;

import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import androidx.annotation.NonNull;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
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
import com.orquitech.development.cuencaVerde.data.model.dbContracts.AccionContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.CartaIntencionContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ContractSiembraContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ContractorEvaluationContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ContractorFormContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.CroquisContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.DependencyContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.GeoJsonContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.GeometryCommentContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.MaintenanceControlContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.MaterialContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.MonitorAndMaintenanceCommentPointContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.MonitorAndMaintenanceContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.MonitorAndMaintenancePhotoContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.MunicipalityContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.PQRSContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.PQRSTypeContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.PhotographicRegistryPhotoContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.PredioContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.PredioFollowUpContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.PredioPotencialContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.PrediosManagementLayerGeoJsonContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ProgramContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ProjectContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.StardMonitorAndMaintenanceContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.StardSheetContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.SurveyContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.TaskCommentContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.TaskContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.VegetalMaintenanceContract;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.model.geoJson.PointFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineStringFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon.MultiPolygonFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.polygon.PolygonFeature;
import com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance.StardMonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.stardSheetForm.StardSheetForm;
import com.orquitech.development.cuencaVerde.data.model.survey.Survey;
import com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance.VegetalMaintenance;
import com.orquitech.development.cuencaVerde.data.utils.FilesUtils;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DbHelperObjectsMapper extends BaseDbHelper {

    public DbHelperObjectsMapper(App app, SerializationManager serializationManager) {
        super(app, serializationManager);
    }

    @NonNull
    protected String[] getPredioProjection() {
        return new String[]{
                PredioContract.PredioEntry.COLUMN_PREDIO_ID,
                PredioContract.PredioEntry.COLUMN_GEOJSON
        };
    }

    @NonNull
    protected String[] getAccionProjection() {
        return new String[]{
                AccionContract.AccionEntry.COLUMN_ACCION_ID,
                AccionContract.AccionEntry.COLUMN_ACCION_NAME,
                AccionContract.AccionEntry.COLUMN_ACCION_TYPE,
                AccionContract.AccionEntry.COLUMN_ACCION_COLOR,
                AccionContract.AccionEntry.COLUMN_ACCION_FILL_COLOR
        };
    }

    @NonNull
    protected String[] getProgramProjection() {
        return new String[]{
                ProgramContract.ProgramEntry._ID,
                ProgramContract.ProgramEntry.COLUMN_NAME
        };
    }

    @NonNull
    protected String[] getTaskCommentsProjection() {
        return new String[]{
                TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_ID,
                TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_CONTENT,
                TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_AUTHOR,
                TaskCommentContract.TaskCommentEntry.COLUMN_TASK_ID
        };
    }

    @NonNull
    protected String[] getMunicipalityProjection() {
        return new String[]{
                MunicipalityContract.MunicipalityEntry.COLUMN_MUNICIPALITY_ID,
                MunicipalityContract.MunicipalityEntry.COLUMN_MUNICIPALITY_NAME,
                MunicipalityContract.MunicipalityEntry.COLUMN_PROVINCE_ID
        };
    }

    @NonNull
    protected String[] getPrediosManagementLayerProjection() {
        return new String[]{
                PrediosManagementLayerGeoJsonContract.PrediosManagementLayerGeoJsonEntry.DATA_ID,
                PrediosManagementLayerGeoJsonContract.PrediosManagementLayerGeoJsonEntry.COLUMN_DATA
        };
    }

    @NonNull
    protected String[] getMonitorAndMaintenancePhotoProjection() {
        return new String[]{
                MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.COLUMN_MONITOR_AND_MAINTENANCE_PHOTO_NAME,
                MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID
        };
    }

    @NonNull
    protected String[] getPhotographicRegistryPhotoProjection() {
        return new String[]{
                PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry._ID,
                PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_NAME,
                PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_COMMENT,
                PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_FOREIGN_ID
        };
    }

    @NonNull
    protected String[] getMaterialProjection() {
        return new String[]{
                MaterialContract.MaterialEntry.COLUMN_MATERIAL_ID,
                MaterialContract.MaterialEntry.COLUMN_MATERIAL_NAME,
                MaterialContract.MaterialEntry.COLUMN_MATERIAL_PRICE,
                MaterialContract.MaterialEntry.COLUMN_MATERIAL_MEASUREMENT,
                MaterialContract.MaterialEntry.COLUMN_MATERIAL_TYPE,
                MaterialContract.MaterialEntry.COLUMN_MATERIAL_UNIT,
                MaterialContract.MaterialEntry.COLUMN_MATERIAL_ACTION
        };
    }

    @NonNull
    protected String[] getMonitorAndMaintenanceProjection() {
        return new String[]{
                MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID,
                MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_TITLE,
                MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_PROCEDURE_ID,
                MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_DUE_DATE,
                MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_DUE_DATE_STATUS,
                MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_JSON_FEATURE,
                MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT
        };
    }

    @NonNull
    protected String[] getGeoJsonProjection() {
        return new String[]{
                GeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID,
                GeoJsonContract.GeoJsonEntry.COLUMN_DATA
        };
    }

    @NonNull
    protected String[] getCroquisProjection() {
        return new String[]{
                CroquisContract.CroquisEntry.COLUMN_REMOTE_ID,
                CroquisContract.CroquisEntry.COLUMN_PREDIO_ID,
                CroquisContract.CroquisEntry.COLUMN_DATA
        };
    }

    @NonNull
    protected String[] getMonitorAndMaintenanceCommentPointProjection() {
        return new String[]{
                MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID,
                MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID,
                MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_COMMENT,
                MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_LAT,
                MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_LNG,
                MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_FROM_SERVICE
        };
    }

    @NonNull
    protected String[] getStardMonitorAndMaintenanceProjection() {
        return new String[]{
                StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID,
                StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_DATA,
                StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT
        };
    }

    @NonNull
    protected String[] getContractSiembraProjection() {
        return new String[]{
                ContractSiembraContract.ContractSiembraEntry.COLUMN_ID,
                ContractSiembraContract.ContractSiembraEntry.COLUMN_DATA,
                ContractSiembraContract.ContractSiembraEntry.COLUMN_PENDING_TO_BE_SENT
        };
    }


    @NonNull
    protected String[] getVegetalMaintenanceProjection() {
        return new String[]{
                VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID,
                VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_DATA,
                VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT
        };
    }

    @NonNull
    protected String[] getPredioFollowUpProjection() {
        return new String[]{
                PredioFollowUpContract.PredioFollowUpEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID,
                PredioFollowUpContract.PredioFollowUpEntry.COLUMN_DATA,
                PredioFollowUpContract.PredioFollowUpEntry.COLUMN_PENDING_TO_BE_SENT
        };
    }

    @NonNull
    protected String[] getContractorEvaluationProjection() {
        return new String[]{
                ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID,
                ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_DATA,
                ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_PENDING_TO_BE_SENT
        };
    }

    @NonNull
    protected String[] getStardSheetProjection() {
        return new String[]{
                StardSheetContract.StardSheetEntry.COLUMN_TASK_ID,
                StardSheetContract.StardSheetEntry.COLUMN_DATA,
                StardSheetContract.StardSheetEntry.COLUMN_PENDING_TO_BE_SENT
        };
    }

    @NonNull
    protected String[] getGeometryCommentProjection() {
        return new String[]{
                GeometryCommentContract.GeometryCommentEntry._ID,
                GeometryCommentContract.GeometryCommentEntry.COLUMN_TASK_ID,
                GeometryCommentContract.GeometryCommentEntry.COLUMN_HASH,
                GeometryCommentContract.GeometryCommentEntry.COLUMN_COMMENT
        };
    }

    @NonNull
    protected String[] getContractorFormProjection() {
        return new String[]{
                ContractorFormContract.ContractorEntry.COLUMN_SIEMBRA_ID,
                ContractorFormContract.ContractorEntry.COLUMN_DATA,
                ContractorFormContract.ContractorEntry.COLUMN_PENDING_TO_BE_SENT
        };
    }

    @NonNull
    protected String[] getMaintenanceControlProjection() {
        return new String[]{
                MaintenanceControlContract.MaintenanceControlEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID,
                MaintenanceControlContract.MaintenanceControlEntry.COLUMN_DATA,
                MaintenanceControlContract.MaintenanceControlEntry.COLUMN_PENDING_TO_BE_SENT
        };
    }

    @NonNull
    protected String[] getCartaIntencionProjection() {
        return new String[]{
                CartaIntencionContract.CartaIntencionEntry.COLUMN_PREDIO_POTENCIAL_ID,
                CartaIntencionContract.CartaIntencionEntry.COLUMN_DATA
        };
    }

    @NonNull
    protected String[] getPQRSProjection() {
        return new String[]{
                PQRSContract.PQRSEntry.COLUMN_PQRS_ID,
                PQRSContract.PQRSEntry.COLUMN_DATA
        };
    }

    @NonNull
    protected String[] getDependencyProjection() {
        return new String[]{
                DependencyContract.DependencyEntry.COLUMN_DEPENDENCY_ID,
                DependencyContract.DependencyEntry.COLUMN_DEPENDENCY_NAME
        };
    }

    @NonNull
    protected String[] getPQRSTypeProjection() {
        return new String[]{
                PQRSTypeContract.PQRSTypeEntry.COLUMN_PQRS_TYPE_ID,
                PQRSTypeContract.PQRSTypeEntry.COLUMN_DEPENDENCY_ID,
                PQRSTypeContract.PQRSTypeEntry.COLUMN_PQRS_TYPE_NAME
        };
    }

    @NonNull
    protected String[] getPredioPotencialProjection() {
        return new String[]{
                PredioPotencialContract.PredioPotencialEntry._ID,
                PredioPotencialContract.PredioPotencialEntry.COLUMN_PREDIO_POTENCIAL_REMOTE_ID,
                PredioPotencialContract.PredioPotencialEntry.COLUMN_NAME,
                PredioPotencialContract.PredioPotencialEntry.COLUMN_LAT,
                PredioPotencialContract.PredioPotencialEntry.COLUMN_LNG
        };
    }

    @NonNull
    protected String[] getProjectProjection() {
        return new String[]{
                ProjectContract.ProjectEntry.COLUMN_PROCEDURE_ID,
                ProjectContract.ProjectEntry.COLUMN_DATA
        };
    }

    @NonNull
    protected String[] getTaskProjection() {
        return new String[]{
                TaskContract.TaskEntry._ID,
                TaskContract.TaskEntry.COLUMN_DATA,
                TaskContract.TaskEntry.COLUMN_PROCEDURE_ID
        };
    }

    @NonNull
    protected String[] getSurveyProjection() {
        return new String[]{
                SurveyContract.SurveyEntry.COLUMN_PREDIO_POTENCIAL_ID,
                SurveyContract.SurveyEntry.COLUMN_DATA
        };
    }

    protected Procedure getProjectObject(Cursor cursor) {
        String infoString = cursor.getString(cursor.getColumnIndexOrThrow(ProjectContract.ProjectEntry.COLUMN_DATA));
        Procedure procedure = (Procedure) serializationManager.fromJson(infoString, Procedure.class);
        procedure.setType(AppViewsFactory.PROJECTS_LIST_ITEM_VIEW);
        return procedure;
    }

    protected Dependency getDependencyObject(Cursor cursor) {

        Dependency dependency = new Dependency();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DependencyContract.DependencyEntry.COLUMN_DEPENDENCY_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DependencyContract.DependencyEntry.COLUMN_DEPENDENCY_NAME));

        dependency.setId(String.valueOf(id));
        dependency.setTitle(name);
        dependency.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

        return dependency;
    }

    protected PQRSType getPQRSTypeObject(Cursor cursor) {

        PQRSType pqrsType = new PQRSType();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(PQRSTypeContract.PQRSTypeEntry.COLUMN_PQRS_TYPE_ID));
        long dependencyId = cursor.getLong(cursor.getColumnIndexOrThrow(PQRSTypeContract.PQRSTypeEntry.COLUMN_DEPENDENCY_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(PQRSTypeContract.PQRSTypeEntry.COLUMN_PQRS_TYPE_NAME));

        pqrsType.setId(String.valueOf(id));
        pqrsType.setDependencyId(String.valueOf(dependencyId));
        pqrsType.setTitle(name);
        pqrsType.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

        return pqrsType;
    }

    protected PQRS getPQRSObject(Cursor cursor) {
        String infoString = cursor.getString(cursor.getColumnIndexOrThrow(PQRSContract.PQRSEntry.COLUMN_DATA));
        return (PQRS) serializationManager.fromJson(infoString, PQRS.class);
    }

    protected PredioPotencial getPredioPotencialObject(Cursor cursor) {

        PredioPotencial predioPotencial = new PredioPotencial();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(PredioPotencialContract.PredioPotencialEntry._ID));
        long remoteId = cursor.getLong(cursor.getColumnIndexOrThrow(PredioPotencialContract.PredioPotencialEntry.COLUMN_PREDIO_POTENCIAL_REMOTE_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(PredioPotencialContract.PredioPotencialEntry.COLUMN_NAME));
        double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(PredioPotencialContract.PredioPotencialEntry.COLUMN_LAT));
        double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(PredioPotencialContract.PredioPotencialEntry.COLUMN_LNG));

        Location location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        predioPotencial.setId(id);
        predioPotencial.setRemoteId(remoteId);
        predioPotencial.setName(name);
        predioPotencial.setLocation(location);

        predioPotencial.setType(AppViewsFactory.PREDIOS_LIST_ITEM_VIEW);

        return predioPotencial;
    }

    protected Predio getPredioObject(Cursor cursor) {

        Predio predio = new Predio();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(PredioContract.PredioEntry.COLUMN_PREDIO_ID));

        String infoString = cursor.getString(cursor.getColumnIndexOrThrow(PredioContract.PredioEntry.COLUMN_GEOJSON));
        GeoJson geoJson = (GeoJson) serializationManager.fromJson(infoString, GeoJson.class);

        predio.setId(id);
        predio.setGeoJson(geoJson);

        return predio;
    }

    protected StardMonitorAndMaintenance getStardMonitorAndMaintenanceObject(Cursor cursor) {
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_DATA));
        return (StardMonitorAndMaintenance) serializationManager.fromJson(dataString, StardMonitorAndMaintenance.class);
    }

    protected ContractSiembra getContractSiembraObject(Cursor cursor) {
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_DATA));
        return (ContractSiembra) serializationManager.fromJson(dataString, ContractSiembra.class);
    }

    protected VegetalMaintenance getVegetalMaintenanceObject(Cursor cursor) {
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_DATA));
        return (VegetalMaintenance) serializationManager.fromJson(dataString, VegetalMaintenance.class);
    }

    protected PredioFollowUp getPredioFollowUpObject(Cursor cursor) {
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(PredioFollowUpContract.PredioFollowUpEntry.COLUMN_DATA));
        return (PredioFollowUp) serializationManager.fromJson(dataString, PredioFollowUp.class);
    }

    protected Croquis getCroquisObject(Cursor cursor) {
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(CroquisContract.CroquisEntry.COLUMN_DATA));
        return (Croquis) serializationManager.fromJson(dataString, Croquis.class);
    }

    protected StardSheetForm getStardSheetObject(Cursor cursor) {
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(StardSheetContract.StardSheetEntry.COLUMN_DATA));
        return (StardSheetForm) serializationManager.fromJson(dataString, StardSheetForm.class);
    }

    protected ContractorForm getContractorFormObject(Cursor cursor) {
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(ContractorFormContract.ContractorEntry.COLUMN_DATA));
        return (ContractorForm) serializationManager.fromJson(dataString, ContractorForm.class);
    }

    protected GeometryComment getGeometryCommentObject(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(GeometryCommentContract.GeometryCommentEntry._ID));
        String taskId = cursor.getString(cursor.getColumnIndexOrThrow(GeometryCommentContract.GeometryCommentEntry.COLUMN_TASK_ID));
        String hash = cursor.getString(cursor.getColumnIndexOrThrow(GeometryCommentContract.GeometryCommentEntry.COLUMN_HASH));
        String comment = cursor.getString(cursor.getColumnIndexOrThrow(GeometryCommentContract.GeometryCommentEntry.COLUMN_COMMENT));

        GeometryComment geometryComment = new GeometryComment();
        geometryComment.setId(String.valueOf(id));
        geometryComment.setTaskId(taskId);
        geometryComment.setHash(hash);
        geometryComment.setComment(comment);
        return geometryComment;
    }

    protected Program getProgramObject(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(ProgramContract.ProgramEntry._ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(ProgramContract.ProgramEntry.COLUMN_NAME));

        Program program = new Program();
        program.setId(String.valueOf(id));
        program.setName(name);
        program.setType(AppViewsFactory.CHECKBOX_ITEM_VIEW);
        return program;
    }

    protected MaintenanceControl getMaintenanceControlObject(Cursor cursor) {
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(MaintenanceControlContract.MaintenanceControlEntry.COLUMN_DATA));
        return (MaintenanceControl) serializationManager.fromJson(dataString, MaintenanceControl.class);
    }

    protected CartaIntencion getCartaIntencionObject(Cursor cursor) {
        String infoString = cursor.getString(cursor.getColumnIndexOrThrow(CartaIntencionContract.CartaIntencionEntry.COLUMN_DATA));
        return (CartaIntencion) serializationManager.fromJson(infoString, CartaIntencion.class);
    }

    protected ContractorEvaluation getContractorEvaluationObject(Cursor cursor) {
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_DATA));
        return (ContractorEvaluation) serializationManager.fromJson(dataString, ContractorEvaluation.class);
    }

    protected Action getAccionObject(Cursor cursor) {

        Action accion = new Action();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(AccionContract.AccionEntry.COLUMN_ACCION_ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(AccionContract.AccionEntry.COLUMN_ACCION_NAME));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(AccionContract.AccionEntry.COLUMN_ACCION_TYPE));
        String color = cursor.getString(cursor.getColumnIndexOrThrow(AccionContract.AccionEntry.COLUMN_ACCION_COLOR));
        String fillColor = cursor.getString(cursor.getColumnIndexOrThrow(AccionContract.AccionEntry.COLUMN_ACCION_FILL_COLOR));

        accion.setId(String.valueOf(id));
        accion.setTitle(title);
        accion.setAccionType(type);
        accion.setColor(color);
        accion.setFillColor(fillColor);

        accion.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

        return accion;
    }

    protected TaskComment getTaskCommentObject(Cursor cursor) {

        TaskComment taskComment = new TaskComment();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_ID));
        String content = cursor.getString(cursor.getColumnIndexOrThrow(TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_CONTENT));
        String author = cursor.getString(cursor.getColumnIndexOrThrow(TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_AUTHOR));

        taskComment.setId(String.valueOf(id));
        taskComment.setContent(content);
        taskComment.setAuthor(author);

        taskComment.setType(AppViewsFactory.TASK_COMMENT_ITEM_VIEW);

        return taskComment;
    }

    protected Municipality getMunicipalityObject(Cursor cursor) {

        Municipality municipality = new Municipality();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(MunicipalityContract.MunicipalityEntry.COLUMN_MUNICIPALITY_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(MunicipalityContract.MunicipalityEntry.COLUMN_MUNICIPALITY_NAME));
        long provinceId = cursor.getLong(cursor.getColumnIndexOrThrow(MunicipalityContract.MunicipalityEntry.COLUMN_PROVINCE_ID));

        municipality.setId(String.valueOf(id));
        municipality.setName(name);
        municipality.setProvinceId(String.valueOf(provinceId));

        return municipality;
    }

    protected MonitorAndMaintenancePhoto getMonitorAndMaintenancePhotoObject(Cursor cursor) {

        MonitorAndMaintenancePhoto monitorAndMaintenancePhoto = new MonitorAndMaintenancePhoto();

        String photoName = cursor.getString(cursor.getColumnIndexOrThrow(MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.COLUMN_MONITOR_AND_MAINTENANCE_PHOTO_NAME));
        int monitorAndMaintenanceId = cursor.getInt(cursor.getColumnIndexOrThrow(MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID));

        monitorAndMaintenancePhoto.setPhotoName(photoName);
        monitorAndMaintenancePhoto.setBitmap(FilesUtils.getOutputMediaBitmap(app.getApplicationContext(), photoName));
        monitorAndMaintenancePhoto.setMonitorAndMaintenanceCommentPointId(String.valueOf(monitorAndMaintenanceId));

        monitorAndMaintenancePhoto.setType(AppViewsFactory.MONITOR_MAINTENANCE_PHOTO_ITEM_VIEW);

        return monitorAndMaintenancePhoto;
    }

    protected PhotographicRegistryPhoto getPhotographicRegistryPhotoObject(Cursor cursor) {

        PhotographicRegistryPhoto photographicRegistryPhoto = new PhotographicRegistryPhoto();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry._ID));

        String photoName = cursor.getString(cursor.getColumnIndexOrThrow(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_NAME));
        String comment = cursor.getString(cursor.getColumnIndexOrThrow(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_COMMENT));
        int taskId = cursor.getInt(cursor.getColumnIndexOrThrow(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_FOREIGN_ID));

        photographicRegistryPhoto.setId(String.valueOf(id));
        photographicRegistryPhoto.setPhotoName(photoName);
        photographicRegistryPhoto.setComment(comment);
        File file = FilesUtils.getOutputMediaFile(app.getApplicationContext(), photoName);
        if (file != null) {
            Uri uri = Uri.fromFile(file);
            photographicRegistryPhoto.setUri(uri);
        }
        photographicRegistryPhoto.setTaskId(String.valueOf(taskId));

        photographicRegistryPhoto.setType(AppViewsFactory.PHOTOGRAPHIC_REGISTRY_PHOTO_ITEM_VIEW);

        return photographicRegistryPhoto;
    }

    protected Material getMaterialObject(Cursor cursor) {

        Material material = new Material();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(MaterialContract.MaterialEntry.COLUMN_MATERIAL_ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(MaterialContract.MaterialEntry.COLUMN_MATERIAL_NAME));
        String price = cursor.getString(cursor.getColumnIndexOrThrow(MaterialContract.MaterialEntry.COLUMN_MATERIAL_PRICE));
        String measurement = cursor.getString(cursor.getColumnIndexOrThrow(MaterialContract.MaterialEntry.COLUMN_MATERIAL_MEASUREMENT));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(MaterialContract.MaterialEntry.COLUMN_MATERIAL_TYPE));
        String unit = cursor.getString(cursor.getColumnIndexOrThrow(MaterialContract.MaterialEntry.COLUMN_MATERIAL_UNIT));
        String actionIdString = cursor.getString(cursor.getColumnIndexOrThrow(MaterialContract.MaterialEntry.COLUMN_MATERIAL_ACTION));

        material.setId(String.valueOf(id));
        material.setTitle(title);
        material.setPrice(price);
        material.setMeasurement(measurement);
        material.setMaterialType(type);
        material.setUnit(unit);

        List<String> actionIds = Arrays.asList(actionIdString.split("\\s*,\\s*"));
        material.setActionIds(actionIds);

        material.setType(AppViewsFactory.ACTIONS_LIST_ITEM_VIEW);

        return material;
    }

    protected MonitorAndMaintenance getMonitorAndMaintenanceObject(Cursor cursor) {

        MonitorAndMaintenance monitorAndMaintenance = new MonitorAndMaintenance();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_TITLE));
        String procedureId = cursor.getString(cursor.getColumnIndexOrThrow(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_PROCEDURE_ID));
        String dueDate = cursor.getString(cursor.getColumnIndexOrThrow(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_DUE_DATE));
        int dueDateStatus = cursor.getInt(cursor.getColumnIndexOrThrow(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_DUE_DATE_STATUS));
        String jsonFeature = cursor.getString(cursor.getColumnIndexOrThrow(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_JSON_FEATURE));

        monitorAndMaintenance.setId(String.valueOf(id));
        monitorAndMaintenance.setTitle(title);
        monitorAndMaintenance.setProcedureId(procedureId);
        monitorAndMaintenance.setDueDate(dueDate);
        monitorAndMaintenance.setDueDateStatus(dueDateStatus);
        monitorAndMaintenance.setJsonFeature(jsonFeature);

        Feature feature = (Feature) serializationManager.fromJson(jsonFeature, Feature.class);
        if (feature != null) {
            switch (feature.getProperties().getFeatureType()) {
                case GeoJson.MULTI_LINE_STRING:
                    feature = (MultiLineStringFeature) serializationManager.fromJson(jsonFeature, MultiLineStringFeature.class);
                    break;
                case GeoJson.POINT:
                    feature = (PointFeature) serializationManager.fromJson(jsonFeature, PointFeature.class);
                    break;
                case GeoJson.POLYGON:
                    feature = (PolygonFeature) serializationManager.fromJson(jsonFeature, PolygonFeature.class);
                    break;
                case GeoJson.MULTI_POLYGON:
                    feature = (MultiPolygonFeature) serializationManager.fromJson(jsonFeature, MultiPolygonFeature.class);
                    break;
            }
            monitorAndMaintenance.setFeature(feature);
        }

        monitorAndMaintenance.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);

        return monitorAndMaintenance;
    }

    protected MonitorAndMaintenanceCommentPoint getMonitorAndMaintenanceCommentPointObject(Cursor cursor) {

        MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint = new MonitorAndMaintenanceCommentPoint();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID));
        String monitorAndMaintenanceId = cursor.getString(cursor.getColumnIndexOrThrow(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID));
        String comment = cursor.getString(cursor.getColumnIndexOrThrow(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_COMMENT));
        double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_LAT));
        double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_LNG));
        int fromService = cursor.getInt(cursor.getColumnIndexOrThrow(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_FROM_SERVICE));

        Location location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        monitorAndMaintenanceCommentPoint.setId(String.valueOf(id));
        monitorAndMaintenanceCommentPoint.setMonitorAndMaintenanceId(monitorAndMaintenanceId);
        monitorAndMaintenanceCommentPoint.setComment(comment);
        monitorAndMaintenanceCommentPoint.setLocation(location);
        if (fromService != 0) {
            monitorAndMaintenanceCommentPoint.setFromService();
        }

        return monitorAndMaintenanceCommentPoint;
    }

    protected Survey getSurveyObject(Cursor cursor) {
        String infoString = cursor.getString(cursor.getColumnIndexOrThrow(SurveyContract.SurveyEntry.COLUMN_DATA));
        return (Survey) serializationManager.fromJson(infoString, Survey.class);
    }

    protected Task getTaskObject(Cursor cursor) {
        String infoString = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DATA));
        Task task = (Task) serializationManager.fromJson(infoString, Task.class);
        task.setType(AppViewsFactory.TASKS_LIST_ITEM_VIEW);
        return task;
    }

    protected GeoJson getPrediosManagementGeoJsonObject(Cursor cursor) {
        String infoString = cursor.getString(cursor.getColumnIndexOrThrow(PrediosManagementLayerGeoJsonContract.PrediosManagementLayerGeoJsonEntry.COLUMN_DATA));
        return (GeoJson) serializationManager.fromJson(infoString, GeoJson.class);
    }

    protected GeoJson getGeoJsonObject(Cursor cursor) {
        String infoString = cursor.getString(cursor.getColumnIndexOrThrow(GeoJsonContract.GeoJsonEntry.COLUMN_DATA));
        return (GeoJson) serializationManager.fromJson(infoString, GeoJson.class);
    }
}
