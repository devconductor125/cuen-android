package com.orquitech.development.cuencaVerde.data.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.AccionContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.CartaIntencionContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ContractSiembraContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ContractorEvaluationContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ContractorFormContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.CroquisContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.DependencyContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ErosiveProcessFormContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ExecutionBaseGeoJsonContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ExecutionEditedGeoJsonContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.GeoJsonContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.GeometryCommentContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.MaintenanceControlContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.MaterialContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.MeetingsWithActorsFormContract;
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
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ProvinceContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.SamplingPointFormContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.SiembraDetailFormContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.StardMonitorAndMaintenanceContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.StardSheetContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.SurveyContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.TaskCommentContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.TaskContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.VegetalMaintenanceContract;

public abstract class BaseDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 85;

    private static final String DATABASE_NAME = "CuencaVerde.db";

    private static final String SQL_CREATE_PREDIOS_TABLE =
            "CREATE TABLE " + PredioContract.PredioEntry.TABLE_NAME + " (" +
                    PredioContract.PredioEntry.COLUMN_PREDIO_ID + " TEXT UNIQUE PRIMARY KEY," +
                    PredioContract.PredioEntry.COLUMN_GEOJSON + " TEXT)";

    private static final String SQL_CREATE_TASKS_TABLE =
            "CREATE TABLE " + TaskContract.TaskEntry.TABLE_NAME + " (" +
                    TaskContract.TaskEntry._ID + " TEXT UNIQUE PRIMARY KEY," +
                    TaskContract.TaskEntry.COLUMN_DATA + " TEXT," +
                    TaskContract.TaskEntry.COLUMN_PROCEDURE_ID + " TEXT)";

    private static final String SQL_CREATE_PROJECTS_TABLE =
            "CREATE TABLE " + ProjectContract.ProjectEntry.TABLE_NAME + " (" +
                    ProjectContract.ProjectEntry.COLUMN_PROCEDURE_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    ProjectContract.ProjectEntry.COLUMN_DATA + " TEXT)";

    private static final String SQL_CREATE_SURVEYS_TABLE =
            "CREATE TABLE " + SurveyContract.SurveyEntry.TABLE_NAME + " (" +
                    SurveyContract.SurveyEntry.COLUMN_PREDIO_POTENCIAL_ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                    SurveyContract.SurveyEntry.COLUMN_DATA + " TEXT)";

    private static final String SQL_CREATE_PREDIOS_POTENCIALES_TABLE =
            "CREATE TABLE " + PredioPotencialContract.PredioPotencialEntry.TABLE_NAME + " (" +
                    PredioPotencialContract.PredioPotencialEntry._ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                    PredioPotencialContract.PredioPotencialEntry.COLUMN_PREDIO_POTENCIAL_REMOTE_ID + " INTEGER," +
                    PredioPotencialContract.PredioPotencialEntry.COLUMN_NAME + " TEXT," +
                    PredioPotencialContract.PredioPotencialEntry.COLUMN_LAT + " DOUBLE," +
                    PredioPotencialContract.PredioPotencialEntry.COLUMN_LNG + " DOUBLE)";

    private static final String SQL_CREATE_ACCIONES_TABLE =
            "CREATE TABLE " + AccionContract.AccionEntry.TABLE_NAME + " (" +
                    AccionContract.AccionEntry.COLUMN_ACCION_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    AccionContract.AccionEntry.COLUMN_ACCION_NAME + " TEXT," +
                    AccionContract.AccionEntry.COLUMN_ACCION_TYPE + " TEXT," +
                    AccionContract.AccionEntry.COLUMN_ACCION_COLOR + " TEXT," +
                    AccionContract.AccionEntry.COLUMN_ACCION_FILL_COLOR + " TEXT)";

    private static final String SQL_CREATE_MATERIALS_TABLE =
            "CREATE TABLE " + MaterialContract.MaterialEntry.TABLE_NAME + " (" +
                    MaterialContract.MaterialEntry.COLUMN_MATERIAL_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    MaterialContract.MaterialEntry.COLUMN_MATERIAL_NAME + " TEXT," +
                    MaterialContract.MaterialEntry.COLUMN_MATERIAL_PRICE + " TEXT," +
                    MaterialContract.MaterialEntry.COLUMN_MATERIAL_MEASUREMENT + " TEXT," +
                    MaterialContract.MaterialEntry.COLUMN_MATERIAL_TYPE + " TEXT," +
                    MaterialContract.MaterialEntry.COLUMN_MATERIAL_UNIT + " TEXT," +
                    MaterialContract.MaterialEntry.COLUMN_MATERIAL_ACTION + " TEXT)";

    private static final String SQL_CREATE_MONITOR_AND_MAINTENANCE_TABLE =
            "CREATE TABLE " + MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.TABLE_NAME + " (" +
                    MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_TITLE + " TEXT," +
                    MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_PROCEDURE_ID + " TEXT," +
                    MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_DUE_DATE + " TEXT," +
                    MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_DUE_DATE_STATUS + " INT," +
                    MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_JSON_FEATURE + " TEXT," +
                    MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT + " BIT)";

    private static final String SQL_CREATE_MONITOR_AND_MAINTENANCE_PHOTO_TABLE =
            "CREATE TABLE " + MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.TABLE_NAME + " (" +
                    MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.COLUMN_MONITOR_AND_MAINTENANCE_PHOTO_NAME + " TEXT UNIQUE PRIMARY KEY," +
                    MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID + " TEXT)";

    private static final String SQL_CREATE_MONITOR_AND_MAINTENANCE_COMMENT_POINT_TABLE =
            "CREATE TABLE " + MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.TABLE_NAME + " (" +
                    MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                    MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " TEXT," +
                    MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_COMMENT + " TEXT," +
                    MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_LAT + " DOUBLE," +
                    MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_LNG + " DOUBLE," +
                    MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_FROM_SERVICE + " BIT)";

    private static final String SQL_CREATE_PQRS_TABLE =
            "CREATE TABLE " + PQRSContract.PQRSEntry.TABLE_NAME + " (" +
                    PQRSContract.PQRSEntry.COLUMN_PQRS_ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                    PQRSContract.PQRSEntry.COLUMN_DATA + " TEXT)";

    private static final String SQL_CREATE_DEPENDENCY_TABLE =
            "CREATE TABLE " + DependencyContract.DependencyEntry.TABLE_NAME + " (" +
                    DependencyContract.DependencyEntry.COLUMN_DEPENDENCY_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    DependencyContract.DependencyEntry.COLUMN_DEPENDENCY_NAME + " TEXT)";

    private static final String SQL_CREATE_PQRS_TYPE_TABLE =
            "CREATE TABLE " + PQRSTypeContract.PQRSTypeEntry.TABLE_NAME + " (" +
                    PQRSTypeContract.PQRSTypeEntry.COLUMN_PQRS_TYPE_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    PQRSTypeContract.PQRSTypeEntry.COLUMN_DEPENDENCY_ID + " LONG," +
                    PQRSTypeContract.PQRSTypeEntry.COLUMN_PQRS_TYPE_NAME + " TEXT)";

    private static final String SQL_CREATE_STARD_MONITOR_AND_MAINTENANCE_TABLE =
            "CREATE TABLE " + StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.TABLE_NAME + " (" +
                    StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_DATA + " TEXT," +
                    StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT + " BIT)";

    private static final String SQL_CREATE_VEGETAL_MAINTENANCE_TABLE =
            "CREATE TABLE " + VegetalMaintenanceContract.VegetalMaintenanceEntry.TABLE_NAME + " (" +
                    VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_DATA + " TEXT," +
                    VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT + " BIT)";

    private static final String SQL_CREATE_PREDIO_FOLLOW_UP_TABLE =
            "CREATE TABLE " + PredioFollowUpContract.PredioFollowUpEntry.TABLE_NAME + " (" +
                    PredioFollowUpContract.PredioFollowUpEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    PredioFollowUpContract.PredioFollowUpEntry.COLUMN_DATA + " TEXT," +
                    PredioFollowUpContract.PredioFollowUpEntry.COLUMN_PENDING_TO_BE_SENT + " BIT)";

    private static final String SQL_CREATE_CONTRACTOR_EVALUATION_TABLE =
            "CREATE TABLE " + ContractorEvaluationContract.ContractorEvaluationEntry.TABLE_NAME + " (" +
                    ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_DATA + " TEXT," +
                    ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_PENDING_TO_BE_SENT + " BIT)";

    private static final String SQL_CREATE_CARTA_INTENCION_TABLE =
            "CREATE TABLE " + CartaIntencionContract.CartaIntencionEntry.TABLE_NAME + " (" +
                    CartaIntencionContract.CartaIntencionEntry.COLUMN_PREDIO_POTENCIAL_ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                    CartaIntencionContract.CartaIntencionEntry.COLUMN_DATA + " TEXT)";

    private static final String SQL_CREATE_GEO_JSON_TABLE =
            "CREATE TABLE " + GeoJsonContract.GeoJsonEntry.TABLE_NAME + " (" +
                    GeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID + " TEXT UNIQUE PRIMARY KEY," +
                    GeoJsonContract.GeoJsonEntry.COLUMN_DATA + " TEXT)";

    private static final String SQL_CREATE_EXECUTION_BASE_GEO_JSON_TABLE =
            "CREATE TABLE " + ExecutionBaseGeoJsonContract.GeoJsonEntry.TABLE_NAME + " (" +
                    ExecutionBaseGeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID + " TEXT UNIQUE PRIMARY KEY," +
                    ExecutionBaseGeoJsonContract.GeoJsonEntry.COLUMN_DATA + " TEXT)";

    private static final String SQL_CREATE_EXECUTION_EDITED_GEO_JSON_TABLE =
            "CREATE TABLE " + ExecutionEditedGeoJsonContract.GeoJsonEntry.TABLE_NAME + " (" +
                    ExecutionEditedGeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID + " TEXT UNIQUE PRIMARY KEY," +
                    ExecutionEditedGeoJsonContract.GeoJsonEntry.COLUMN_DATA + " TEXT)";

    private static final String SQL_CREATE_STARD_SHEET_TABLE =
            "CREATE TABLE " + StardSheetContract.StardSheetEntry.TABLE_NAME + " (" +
                    StardSheetContract.StardSheetEntry.COLUMN_TASK_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    StardSheetContract.StardSheetEntry.COLUMN_DATA + " TEXT," +
                    StardSheetContract.StardSheetEntry.COLUMN_PENDING_TO_BE_SENT + " BIT)";

    private static final String SQL_CREATE_MAINTENANCE_CONTROL_TABLE =
            "CREATE TABLE " + MaintenanceControlContract.MaintenanceControlEntry.TABLE_NAME + " (" +
                    MaintenanceControlContract.MaintenanceControlEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    MaintenanceControlContract.MaintenanceControlEntry.COLUMN_DATA + " TEXT," +
                    MaintenanceControlContract.MaintenanceControlEntry.COLUMN_PENDING_TO_BE_SENT + " BIT)";

    private static final String SQL_CREATE_CONTRACT_SIEMBRA_TABLE =
            "CREATE TABLE " + ContractSiembraContract.ContractSiembraEntry.TABLE_NAME + " (" +
                    ContractSiembraContract.ContractSiembraEntry.COLUMN_ID + " TEXT UNIQUE PRIMARY KEY," +
                    ContractSiembraContract.ContractSiembraEntry.COLUMN_DATA + " TEXT," +
                    ContractSiembraContract.ContractSiembraEntry.COLUMN_PENDING_TO_BE_SENT + " BIT)";

    private static final String SQL_CREATE_CONTRACTOR_FORM_TABLE =
            "CREATE TABLE " + ContractorFormContract.ContractorEntry.TABLE_NAME + " (" +
                    ContractorFormContract.ContractorEntry.COLUMN_SIEMBRA_ID + " TEXT UNIQUE PRIMARY KEY," +
                    ContractorFormContract.ContractorEntry.COLUMN_DATA + " TEXT," +
                    ContractorFormContract.ContractorEntry.COLUMN_PENDING_TO_BE_SENT + " BIT)";

    private static final String SQL_CREATE_CROQUIS_TABLE =
            "CREATE TABLE " + CroquisContract.CroquisEntry.TABLE_NAME + " (" +
                    CroquisContract.CroquisEntry.COLUMN_REMOTE_ID + " TEXT UNIQUE," +
                    CroquisContract.CroquisEntry.COLUMN_PREDIO_ID + " LONG," +
                    CroquisContract.CroquisEntry.COLUMN_DATA + " TEXT)";

    private static final String SQL_CREATE_TASK_COMMENTS_TABLE =
            "CREATE TABLE " + TaskCommentContract.TaskCommentEntry.TABLE_NAME + " (" +
                    TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_CONTENT + " TEXT," +
                    TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_AUTHOR + " TEXT," +
                    TaskCommentContract.TaskCommentEntry.COLUMN_TASK_ID + " TEXT)";

    private static final String SQL_CREATE_PROVINCES_TABLE =
            "CREATE TABLE " + ProvinceContract.ProvinceEntry.TABLE_NAME + " (" +
                    ProvinceContract.ProvinceEntry.COLUMN_PROVINCE_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    ProvinceContract.ProvinceEntry.COLUMN_PROVINCE_NAME + " TEXT)";

    private static final String SQL_CREATE_MUNICIPALITIES_TABLE =
            "CREATE TABLE " + MunicipalityContract.MunicipalityEntry.TABLE_NAME + " (" +
                    MunicipalityContract.MunicipalityEntry.COLUMN_MUNICIPALITY_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    MunicipalityContract.MunicipalityEntry.COLUMN_MUNICIPALITY_NAME + " TEXT," +
                    MunicipalityContract.MunicipalityEntry.COLUMN_PROVINCE_ID + " LONG)";

    private static final String SQL_CREATE_PREDIOS_MANAGEMENT_LAYER_TABLE =
            "CREATE TABLE " + PrediosManagementLayerGeoJsonContract.PrediosManagementLayerGeoJsonEntry.TABLE_NAME + " (" +
                    PrediosManagementLayerGeoJsonContract.PrediosManagementLayerGeoJsonEntry.DATA_ID + " INTEGER UNIQUE PRIMARY KEY," +
                    PrediosManagementLayerGeoJsonContract.PrediosManagementLayerGeoJsonEntry.COLUMN_DATA + " TEXT)";

    private static final String SQL_CREATE_SIEMBRA_DETAIL_TABLE =
            "CREATE TABLE " + SiembraDetailFormContract.SiembraDetailEntry.TABLE_NAME + " (" +
                    SiembraDetailFormContract.SiembraDetailEntry._ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                    SiembraDetailFormContract.SiembraDetailEntry.COLUMN_HASH + " TEXT," +
                    SiembraDetailFormContract.SiembraDetailEntry.COLUMN_TASK_ID + " TEXT," +
                    SiembraDetailFormContract.SiembraDetailEntry.COLUMN_DATA + " TEXT," +
                    SiembraDetailFormContract.SiembraDetailEntry.COLUMN_PENDING_TO_BE_SENT + " BIT)";

    private static final String SQL_CREATE_MEETINGS_WITH_ACTORS_TABLE =
            "CREATE TABLE " + MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.TABLE_NAME + " (" +
                    MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry._ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                    MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.COLUMN_TASK_ID + " TEXT," +
                    MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.COLUMN_DATA + " TEXT)";

    private static final String SQL_CREATE_PHOTOGRAPHIC_REGISTRY_TABLE =
            "CREATE TABLE " + PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.TABLE_NAME + " (" +
                    PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry._ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                    PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_NAME + " TEXT UNIQUE," +
                    PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_COMMENT + " TEXT," +
                    PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_FOREIGN_ID + " TEXT)";

    private static final String SQL_CREATE_GEOMETRY_COMMENT_TABLE =
            "CREATE TABLE " + GeometryCommentContract.GeometryCommentEntry.TABLE_NAME + " (" +
                    GeometryCommentContract.GeometryCommentEntry._ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                    GeometryCommentContract.GeometryCommentEntry.COLUMN_TASK_ID + " TEXT," +
                    GeometryCommentContract.GeometryCommentEntry.COLUMN_HASH + " TEXT," +
                    GeometryCommentContract.GeometryCommentEntry.COLUMN_COMMENT + " TEXT)";

    private static final String SQL_CREATE_PROGRAM_TABLE =
            "CREATE TABLE " + ProgramContract.ProgramEntry.TABLE_NAME + " (" +
                    ProgramContract.ProgramEntry._ID + " INTEGER UNIQUE PRIMARY KEY," +
                    ProgramContract.ProgramEntry.COLUMN_NAME + " TEXT)";

    private static final String SQL_SAMPLING_POINT_TABLE =
            "CREATE TABLE " + SamplingPointFormContract.SamplingPointEntry.TABLE_NAME + " (" +
                    SamplingPointFormContract.SamplingPointEntry._ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                    SamplingPointFormContract.SamplingPointEntry.COLUMN_HASH + " TEXT," +
                    SamplingPointFormContract.SamplingPointEntry.COLUMN_TASK_ID + " TEXT," +
                    SamplingPointFormContract.SamplingPointEntry.COLUMN_DATA + " TEXT," +
                    SamplingPointFormContract.SamplingPointEntry.COLUMN_PENDING_TO_BE_SENT + " BIT)";

    private static final String SQL_EROSIVE_PROCESS_TABLE =
            "CREATE TABLE " + ErosiveProcessFormContract.ErosiveProcessEntry.TABLE_NAME + " (" +
                    ErosiveProcessFormContract.ErosiveProcessEntry._ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT," +
                    ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_HASH + " TEXT," +
                    ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_TASK_ID + " TEXT," +
                    ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_DATA + " TEXT," +
                    ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_PENDING_TO_BE_SENT + " BIT)";

    private static final String SQL_DELETE_PREDIOS = "DROP TABLE IF EXISTS " + PredioContract.PredioEntry.TABLE_NAME;
    private static final String SQL_DELETE_TASKS = "DROP TABLE IF EXISTS " + TaskContract.TaskEntry.TABLE_NAME;
    private static final String SQL_DELETE_PROJECTS = "DROP TABLE IF EXISTS " + ProjectContract.ProjectEntry.TABLE_NAME;
    private static final String SQL_DELETE_SURVEYS = "DROP TABLE IF EXISTS " + SurveyContract.SurveyEntry.TABLE_NAME;
    private static final String SQL_DELETE_PREDIOS_POTENCIALES = "DROP TABLE IF EXISTS " + PredioPotencialContract.PredioPotencialEntry.TABLE_NAME;
    private static final String SQL_DELETE_ACCIONES = "DROP TABLE IF EXISTS " + AccionContract.AccionEntry.TABLE_NAME;
    private static final String SQL_DELETE_MATERIALS = "DROP TABLE IF EXISTS " + MaterialContract.MaterialEntry.TABLE_NAME;
    private static final String SQL_DELETE_MONITOR_AND_MAINTENANCE = "DROP TABLE IF EXISTS " + MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.TABLE_NAME;
    private static final String SQL_DELETE_MONITOR_AND_MAINTENANCE_PHOTO = "DROP TABLE IF EXISTS " + MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.TABLE_NAME;
    private static final String SQL_DELETE_MONITOR_AND_MAINTENANCE_COMMENT_POINT_TABLE = "DROP TABLE IF EXISTS " + MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.TABLE_NAME;
    private static final String SQL_DELETE_PQRS_TABLE = "DROP TABLE IF EXISTS " + PQRSContract.PQRSEntry.TABLE_NAME;
    private static final String SQL_DELETE_DEPENDENCY_TABLE = "DROP TABLE IF EXISTS " + DependencyContract.DependencyEntry.TABLE_NAME;
    private static final String SQL_DELETE_PQRS_TYPE_TABLE = "DROP TABLE IF EXISTS " + PQRSTypeContract.PQRSTypeEntry.TABLE_NAME;
    private static final String SQL_DELETE_STARD_MONITOR_AND_MAINTENANCE_TABLE = "DROP TABLE IF EXISTS " + StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.TABLE_NAME;
    private static final String SQL_DELETE_VEGETAL_MAINTENANCE_TABLE = "DROP TABLE IF EXISTS " + VegetalMaintenanceContract.VegetalMaintenanceEntry.TABLE_NAME;
    private static final String SQL_DELETE_PREDIO_FOLLOW_UP_TABLE = "DROP TABLE IF EXISTS " + PredioFollowUpContract.PredioFollowUpEntry.TABLE_NAME;
    private static final String SQL_DELETE_CONTRACTOR_EVALUATION_TABLE = "DROP TABLE IF EXISTS " + ContractorEvaluationContract.ContractorEvaluationEntry.TABLE_NAME;
    private static final String SQL_DELETE_GEO_JSON_TABLE = "DROP TABLE IF EXISTS " + GeoJsonContract.GeoJsonEntry.TABLE_NAME;
    private static final String SQL_DELETE_GEO_JSON_EXECUTION_BASE_TABLE = "DROP TABLE IF EXISTS " + ExecutionBaseGeoJsonContract.GeoJsonEntry.TABLE_NAME;
    private static final String SQL_DELETE_GEO_JSON_EXECUTION_EDITED_TABLE = "DROP TABLE IF EXISTS " + ExecutionEditedGeoJsonContract.GeoJsonEntry.TABLE_NAME;
    private static final String SQL_DELETE_CARTA_INTENCION_TABLE = "DROP TABLE IF EXISTS " + CartaIntencionContract.CartaIntencionEntry.TABLE_NAME;
    private static final String SQL_DELETE_STARD_SHEET_TABLE = "DROP TABLE IF EXISTS " + StardSheetContract.StardSheetEntry.TABLE_NAME;
    private static final String SQL_DELETE_MAINTENANCE_CONTROL_TABLE = "DROP TABLE IF EXISTS " + MaintenanceControlContract.MaintenanceControlEntry.TABLE_NAME;
    private static final String SQL_DELETE_TASK_COMMENTS_TABLE = "DROP TABLE IF EXISTS " + TaskCommentContract.TaskCommentEntry.TABLE_NAME;
    private static final String SQL_DELETE_PROVINCES_TABLE = "DROP TABLE IF EXISTS " + ProvinceContract.ProvinceEntry.TABLE_NAME;
    private static final String SQL_DELETE_MUNICIPALITIES_TABLE = "DROP TABLE IF EXISTS " + MunicipalityContract.MunicipalityEntry.TABLE_NAME;
    private static final String SQL_DELETE_CROQUIS_TABLE = "DROP TABLE IF EXISTS " + CroquisContract.CroquisEntry.TABLE_NAME;
    private static final String SQL_DELETE_PREDIOS_MANAGEMENT_LAYER_TABLE = "DROP TABLE IF EXISTS " + PrediosManagementLayerGeoJsonContract.PrediosManagementLayerGeoJsonEntry.TABLE_NAME;
    private static final String SQL_DELETE_CONTRACT_SIEMBRA_TABLE = "DROP TABLE IF EXISTS " + ContractSiembraContract.ContractSiembraEntry.TABLE_NAME;
    private static final String SQL_DELETE_CONTRACTOR_FORM_TABLE = "DROP TABLE IF EXISTS " + ContractorFormContract.ContractorEntry.TABLE_NAME;
    private static final String SQL_DELETE_SIEMBRA_DETAIL_TABLE = "DROP TABLE IF EXISTS " + SiembraDetailFormContract.SiembraDetailEntry.TABLE_NAME;
    private static final String SQL_DELETE_PHOTOGRAPHIC_REGISTRY_TABLE = "DROP TABLE IF EXISTS " + PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.TABLE_NAME;
    private static final String SQL_DELETE_MEETINGS_WITH_ACTORS_TABLE = "DROP TABLE IF EXISTS " + MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.TABLE_NAME;
    private static final String SQL_DELETE_GEOMETRY_COMMENT_TABLE = "DROP TABLE IF EXISTS " + GeometryCommentContract.GeometryCommentEntry.TABLE_NAME;
    private static final String SQL_DELETE_PROGRAM_TABLE = "DROP TABLE IF EXISTS " + ProgramContract.ProgramEntry.TABLE_NAME;
    private static final String SQL_DELETE_SAMPLING_POINT_TABLE = "DROP TABLE IF EXISTS " + SamplingPointFormContract.SamplingPointEntry.TABLE_NAME;
    private static final String SQL_DELETE_EROSIVE_PROCESS_TABLE = "DROP TABLE IF EXISTS " + ErosiveProcessFormContract.ErosiveProcessEntry.TABLE_NAME;

    protected final App app;
    protected final SerializationManager serializationManager;

    public BaseDbHelper(App app, SerializationManager serializationManager) {
        super(app.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
        this.app = app;
        this.serializationManager = serializationManager;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PREDIOS_TABLE);
        db.execSQL(SQL_CREATE_TASKS_TABLE);
        db.execSQL(SQL_CREATE_PROJECTS_TABLE);
        db.execSQL(SQL_CREATE_SURVEYS_TABLE);
        db.execSQL(SQL_CREATE_PREDIOS_POTENCIALES_TABLE);
        db.execSQL(SQL_CREATE_ACCIONES_TABLE);
        db.execSQL(SQL_CREATE_MATERIALS_TABLE);
        db.execSQL(SQL_CREATE_MONITOR_AND_MAINTENANCE_TABLE);
        db.execSQL(SQL_CREATE_MONITOR_AND_MAINTENANCE_PHOTO_TABLE);
        db.execSQL(SQL_CREATE_MONITOR_AND_MAINTENANCE_COMMENT_POINT_TABLE);
        db.execSQL(SQL_CREATE_PQRS_TABLE);
        db.execSQL(SQL_CREATE_DEPENDENCY_TABLE);
        db.execSQL(SQL_CREATE_PQRS_TYPE_TABLE);
        db.execSQL(SQL_CREATE_STARD_MONITOR_AND_MAINTENANCE_TABLE);
        db.execSQL(SQL_CREATE_VEGETAL_MAINTENANCE_TABLE);
        db.execSQL(SQL_CREATE_PREDIO_FOLLOW_UP_TABLE);
        db.execSQL(SQL_CREATE_CONTRACTOR_EVALUATION_TABLE);
        db.execSQL(SQL_CREATE_GEO_JSON_TABLE);
        db.execSQL(SQL_CREATE_EXECUTION_BASE_GEO_JSON_TABLE);
        db.execSQL(SQL_CREATE_EXECUTION_EDITED_GEO_JSON_TABLE);
        db.execSQL(SQL_CREATE_CARTA_INTENCION_TABLE);
        db.execSQL(SQL_CREATE_STARD_SHEET_TABLE);
        db.execSQL(SQL_CREATE_MAINTENANCE_CONTROL_TABLE);
        db.execSQL(SQL_CREATE_TASK_COMMENTS_TABLE);
        db.execSQL(SQL_CREATE_PROVINCES_TABLE);
        db.execSQL(SQL_CREATE_MUNICIPALITIES_TABLE);
        db.execSQL(SQL_CREATE_CROQUIS_TABLE);
        db.execSQL(SQL_CREATE_PREDIOS_MANAGEMENT_LAYER_TABLE);
        db.execSQL(SQL_CREATE_CONTRACT_SIEMBRA_TABLE);
        db.execSQL(SQL_CREATE_CONTRACTOR_FORM_TABLE);
        db.execSQL(SQL_CREATE_SIEMBRA_DETAIL_TABLE);
        db.execSQL(SQL_CREATE_PHOTOGRAPHIC_REGISTRY_TABLE);
        db.execSQL(SQL_CREATE_MEETINGS_WITH_ACTORS_TABLE);
        db.execSQL(SQL_CREATE_GEOMETRY_COMMENT_TABLE);
        db.execSQL(SQL_CREATE_PROGRAM_TABLE);
        db.execSQL(SQL_SAMPLING_POINT_TABLE);
        db.execSQL(SQL_EROSIVE_PROCESS_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PREDIOS);
        db.execSQL(SQL_DELETE_TASKS);
        db.execSQL(SQL_DELETE_PROJECTS);
        db.execSQL(SQL_DELETE_SURVEYS);
        db.execSQL(SQL_DELETE_PREDIOS_POTENCIALES);
        db.execSQL(SQL_DELETE_ACCIONES);
        db.execSQL(SQL_DELETE_MATERIALS);
        db.execSQL(SQL_DELETE_MONITOR_AND_MAINTENANCE);
        db.execSQL(SQL_DELETE_MONITOR_AND_MAINTENANCE_PHOTO);
        db.execSQL(SQL_DELETE_MONITOR_AND_MAINTENANCE_COMMENT_POINT_TABLE);
        db.execSQL(SQL_DELETE_PQRS_TABLE);
        db.execSQL(SQL_DELETE_DEPENDENCY_TABLE);
        db.execSQL(SQL_DELETE_PQRS_TYPE_TABLE);
        db.execSQL(SQL_DELETE_STARD_MONITOR_AND_MAINTENANCE_TABLE);
        db.execSQL(SQL_DELETE_VEGETAL_MAINTENANCE_TABLE);
        db.execSQL(SQL_DELETE_PREDIO_FOLLOW_UP_TABLE);
        db.execSQL(SQL_DELETE_CONTRACTOR_EVALUATION_TABLE);
        db.execSQL(SQL_DELETE_GEO_JSON_TABLE);
        db.execSQL(SQL_DELETE_GEO_JSON_EXECUTION_BASE_TABLE);
        db.execSQL(SQL_DELETE_GEO_JSON_EXECUTION_EDITED_TABLE);
        db.execSQL(SQL_DELETE_CARTA_INTENCION_TABLE);
        db.execSQL(SQL_DELETE_STARD_SHEET_TABLE);
        db.execSQL(SQL_DELETE_MAINTENANCE_CONTROL_TABLE);
        db.execSQL(SQL_DELETE_TASK_COMMENTS_TABLE);
        db.execSQL(SQL_DELETE_PROVINCES_TABLE);
        db.execSQL(SQL_DELETE_MUNICIPALITIES_TABLE);
        db.execSQL(SQL_DELETE_CROQUIS_TABLE);
        db.execSQL(SQL_DELETE_PREDIOS_MANAGEMENT_LAYER_TABLE);
        db.execSQL(SQL_DELETE_CONTRACT_SIEMBRA_TABLE);
        db.execSQL(SQL_DELETE_CONTRACTOR_FORM_TABLE);
        db.execSQL(SQL_DELETE_SIEMBRA_DETAIL_TABLE);
        db.execSQL(SQL_DELETE_PHOTOGRAPHIC_REGISTRY_TABLE);
        db.execSQL(SQL_DELETE_MEETINGS_WITH_ACTORS_TABLE);
        db.execSQL(SQL_DELETE_GEOMETRY_COMMENT_TABLE);
        db.execSQL(SQL_DELETE_PROGRAM_TABLE);
        db.execSQL(SQL_DELETE_SAMPLING_POINT_TABLE);
        db.execSQL(SQL_DELETE_EROSIVE_PROCESS_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
