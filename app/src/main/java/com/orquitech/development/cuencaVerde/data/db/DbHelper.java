package com.orquitech.development.cuencaVerde.data.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.db.dbObjectHelpers.ErosiveProcessFormHelper;
import com.orquitech.development.cuencaVerde.data.db.dbObjectHelpers.MeetingsWithActorsHelper;
import com.orquitech.development.cuencaVerde.data.db.dbObjectHelpers.SamplingPointHelper;
import com.orquitech.development.cuencaVerde.data.db.dbObjectHelpers.SiembraDetailHelper;
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
import com.orquitech.development.cuencaVerde.data.model.Province;
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
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ExecutionBaseGeoJsonContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ExecutionEditedGeoJsonContract;
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
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ProvinceContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.StardMonitorAndMaintenanceContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.StardSheetContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.SurveyContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.TaskCommentContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.TaskContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.VegetalMaintenanceContract;
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
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DbHelper extends DbHelperObjectsMapper {

    @Inject
    public DbHelper(App app, SerializationManager serializationManager) {
        super(app, serializationManager);
    }

    Observable<List<Predio>> savePredios(List<Predio> predios) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                for (Predio predio : predios) {

                    values.put(PredioContract.PredioEntry.COLUMN_PREDIO_ID, predio.getId());
                    values.put(PredioContract.PredioEntry.COLUMN_GEOJSON, serializationManager.toJson(predio.getGeoJson()));

                    db.insertWithOnConflict(PredioContract.PredioEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                    Log.d(getClass().getName(), "Saved predio in DB -> " + predio.getId());
                }

                db.setTransactionSuccessful();

                subscriber.onNext(predios);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Item>> saveProjects(List<Item> projects) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                for (Item project : projects) {

                    values.put(ProjectContract.ProjectEntry.COLUMN_PROCEDURE_ID, ((Procedure) project).getId());
                    values.put(ProjectContract.ProjectEntry.COLUMN_DATA, serializationManager.toJson(project));

                    db.insertWithOnConflict(ProjectContract.ProjectEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                    Log.d(getClass().getName(), "Saved project in DB -> " + ((Procedure) project).getId());
                }

                db.setTransactionSuccessful();

                subscriber.onNext(projects);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Predio> savePredio(Predio predio) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(PredioContract.PredioEntry.COLUMN_PREDIO_ID, predio.getId());
                values.put(PredioContract.PredioEntry.COLUMN_GEOJSON, serializationManager.toJson(predio.getGeoJson()));

                db.insertWithOnConflict(PredioContract.PredioEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved predio in DB -> " + predio.getId());

                subscriber.onNext(predio);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Predio>> getPredios(int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getPredioProjection();
                String sortOrder = PredioContract.PredioEntry.COLUMN_PREDIO_ID + " DESC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);

                Cursor cursor = db.query(
                        PredioContract.PredioEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<Predio> predios = new ArrayList<>();

                while (cursor.moveToNext()) {
                    predios.add(getPredioObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(predios);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Predio> getPredioById(String id) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getPredioProjection();
                String sortOrder = PredioContract.PredioEntry.COLUMN_PREDIO_ID + " DESC";
                String selection = PredioContract.PredioEntry.COLUMN_PREDIO_ID + " = ?";
                String[] selectionArgs = {
                        id
                };

                Cursor cursor = db.query(
                        PredioContract.PredioEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                Predio predio = new Predio();

                while (cursor.moveToNext()) {
                    predio = getPredioObject(cursor);
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(predio);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Procedure> getProjectById(String id) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getProjectProjection();
                String sortOrder = ProjectContract.ProjectEntry.COLUMN_PROCEDURE_ID + " DESC";
                String selection = ProjectContract.ProjectEntry.COLUMN_PROCEDURE_ID + " = ?";
                String[] selectionArgs = {
                        id
                };

                Cursor cursor = db.query(
                        ProjectContract.ProjectEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                Procedure project = new Procedure();

                while (cursor.moveToNext()) {
                    project = getProjectObject(cursor);
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(project);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Predio>> searchPredios(String searchTerm) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String selectQuery = " SELECT * FROM " + PredioContract.PredioEntry.TABLE_NAME + " WHERE " + PredioContract.PredioEntry.COLUMN_GEOJSON + " LIKE ";
                String[] split = searchTerm.split("\\s+");

                if (split.length > 0) {
                    selectQuery += "'%" + split[0] + "%'";
                }

                for (String word : split) {
                    selectQuery += " OR " + PredioContract.PredioEntry.COLUMN_GEOJSON + " LIKE '%" + word + "%'";
                }

                Cursor cursor = db.rawQuery(selectQuery, null);

                List<Predio> predios = new ArrayList<>();

                while (cursor.moveToNext()) {
                    predios.add(getPredioObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(predios);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Procedure> saveProcedure(Procedure procedure) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                String procedureString = serializationManager.toJson(procedure.clone());
                String predioSearch = TextUtil.stripAccents(procedure.getId() + procedure.getName()).toLowerCase(); // TODO For local searches, enable?

                values.put(ProjectContract.ProjectEntry.COLUMN_PROCEDURE_ID, procedure.getId());
                values.put(ProjectContract.ProjectEntry.COLUMN_DATA, procedureString);

                db.insertWithOnConflict(ProjectContract.ProjectEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved project in DB -> " + procedure.getId());

                subscriber.onNext(procedure);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Task> saveTask(Task task) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                String dataString = serializationManager.toJson(task);

                values.put(TaskContract.TaskEntry._ID, task.getId());
                values.put(TaskContract.TaskEntry.COLUMN_DATA, dataString);

                db.insertWithOnConflict(TaskContract.TaskEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved task in DB -> " + task.getId());

                subscriber.onNext(task);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Item>> saveTasks(List<Item> tasks) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                for (Item task : tasks) {
                    if (Integer.valueOf(((Task) task).getCleanedId()) > 0) {
                        String dataString = serializationManager.toJson(task);

                        values.put(TaskContract.TaskEntry._ID, ((Task) task).getId());
                        values.put(TaskContract.TaskEntry.COLUMN_DATA, dataString);
                        values.put(TaskContract.TaskEntry.COLUMN_PROCEDURE_ID, ((Task) task).getProcedureId());

                        db.insertWithOnConflict(TaskContract.TaskEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                        Log.d(getClass().getName(), "Saved task in DB -> " + ((Task) task).getId());
                    }
                }

                db.setTransactionSuccessful();

                subscriber.onNext(tasks);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<GeoJson> saveGeoJson(GeoJson geoJson, String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                if (geoJson != null) {
                    ContentValues values = new ContentValues();
                    String geoJsonString = serializationManager.toJson(geoJson);

                    if (!TextUtils.isEmpty(geoJsonString)) {
                        values.put(GeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID, taskId);
                        values.put(GeoJsonContract.GeoJsonEntry.COLUMN_DATA, geoJsonString);

                        db.insertWithOnConflict(GeoJsonContract.GeoJsonEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                        db.setTransactionSuccessful();

                        Log.d(getClass().getName(), "Saved geoJson in DB -> " + taskId);
                        subscriber.onNext(geoJson);
                    }
                }

                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<GeoJson> saveExecutionTaskBaseGeoJson(GeoJson geoJson, String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                if (geoJson != null) {
                    ContentValues values = new ContentValues();
                    String geoJsonString = serializationManager.toJson(geoJson);

                    if (!TextUtils.isEmpty(geoJsonString)) {
                        values.put(ExecutionBaseGeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID, taskId);
                        values.put(ExecutionBaseGeoJsonContract.GeoJsonEntry.COLUMN_DATA, geoJsonString);

                        db.insertWithOnConflict(ExecutionBaseGeoJsonContract.GeoJsonEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                        db.setTransactionSuccessful();

                        Log.d(getClass().getName(), "Saved execution base geoJson in DB -> " + taskId);
                        subscriber.onNext(geoJson);
                    }
                }

                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<GeoJson> saveExecutionTaskEditedGeoJson(GeoJson geoJson, String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                if (geoJson != null) {
                    ContentValues values = new ContentValues();
                    String geoJsonString = serializationManager.toJson(geoJson);

                    if (!TextUtils.isEmpty(geoJsonString)) {
                        values.put(ExecutionEditedGeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID, taskId);
                        values.put(ExecutionEditedGeoJsonContract.GeoJsonEntry.COLUMN_DATA, geoJsonString);

                        db.insertWithOnConflict(ExecutionEditedGeoJsonContract.GeoJsonEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                        db.setTransactionSuccessful();

                        Log.d(getClass().getName(), "Saved execution edited geoJson in DB -> " + taskId);
                        subscriber.onNext(geoJson);
                    }
                }

                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Item>> getProjects(int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getProjectProjection();
                String sortOrder = ProjectContract.ProjectEntry.COLUMN_PROCEDURE_ID + " ASC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);

                Cursor cursor = db.query(
                        ProjectContract.ProjectEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<Item> projects = new ArrayList<>();

                while (cursor.moveToNext()) {
                    projects.add(getProjectObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(projects);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Item>> getTasks(String procedureId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getTaskProjection();
                String sortOrder = TaskContract.TaskEntry._ID + " ASC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);

                String selection = null;
                String selectionArgsItem = null;
                if (!TextUtils.isEmpty(procedureId)) {
                    selection = TaskContract.TaskEntry.COLUMN_PROCEDURE_ID + " = ?";
                    selectionArgsItem = procedureId;
                }
                String[] selectionArgs = {
                        selectionArgsItem
                };

                Cursor cursor = db.query(
                        TaskContract.TaskEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgsItem != null ? selectionArgs : null,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<Item> tasks = new ArrayList<>();

                while (cursor.moveToNext()) {
                    tasks.add(getTaskObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(tasks);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Survey> saveSurvey(Survey survey) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                String surveyString = serializationManager.toJson(survey);

                values.put(SurveyContract.SurveyEntry.COLUMN_PREDIO_POTENCIAL_ID, survey.getPredioPotencialId());
                values.put(SurveyContract.SurveyEntry.COLUMN_DATA, surveyString);

                db.insertWithOnConflict(SurveyContract.SurveyEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved survey in DB");

                subscriber.onNext(survey);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    private Survey getLocalSurveyByPredioPotencialId(String predioPotencialId) {

        Survey survey = null;

        SQLiteDatabase db = getReadableDatabase();
        db.beginTransactionNonExclusive();

        try {

            String[] projection = getSurveyProjection();
            String sortOrder = SurveyContract.SurveyEntry.COLUMN_PREDIO_POTENCIAL_ID + " DESC";
            String selection = SurveyContract.SurveyEntry.COLUMN_PREDIO_POTENCIAL_ID + " = ?";
            String[] selectionArgs = {
                    predioPotencialId
            };

            Cursor cursor = db.query(
                    SurveyContract.SurveyEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );

            while (cursor.moveToNext()) {
                survey = getSurveyObject(cursor);
            }

            cursor.close();
            db.setTransactionSuccessful();

        } catch (Exception ex) {
            Log.e(getClass().getSimpleName(), ex.getMessage());
        } finally {
            db.endTransaction();
        }

        return survey;
    }

    Observable<Survey> getSurveyByPredioPotencialId(String predioPotencialId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getSurveyProjection();
                String sortOrder = SurveyContract.SurveyEntry.COLUMN_PREDIO_POTENCIAL_ID + " DESC";
                String selection = SurveyContract.SurveyEntry.COLUMN_PREDIO_POTENCIAL_ID + " = ?";
                String[] selectionArgs = {
                        predioPotencialId
                };

                Cursor cursor = db.query(
                        SurveyContract.SurveyEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                Survey survey = new Survey();

                while (cursor.moveToNext()) {
                    survey = getSurveyObject(cursor);
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(survey);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Item>> getDueTasks(int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getTaskProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);

                Cursor cursor = db.query(
                        TaskContract.TaskEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<Item> tasks = new ArrayList<>();

                while (cursor.moveToNext()) {
                    tasks.add(getTaskObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(tasks);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<PredioPotencial> savePredioPotencial(PredioPotencial predioPotencial) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                PredioPotencial foundPredio;

                if (predioPotencial.getRemoteId() > 0) {
                    foundPredio = getSavedPredioPotencialName(predioPotencial.getName());
                    if (foundPredio != null) {
                        values.put(PredioPotencialContract.PredioPotencialEntry._ID, foundPredio.getId());
                    }
                }

                values.put(PredioPotencialContract.PredioPotencialEntry.COLUMN_PREDIO_POTENCIAL_REMOTE_ID, predioPotencial.getRemoteId());
                values.put(PredioPotencialContract.PredioPotencialEntry.COLUMN_NAME, predioPotencial.getName());
                values.put(PredioPotencialContract.PredioPotencialEntry.COLUMN_LAT, predioPotencial.getLatitude());
                values.put(PredioPotencialContract.PredioPotencialEntry.COLUMN_LNG, predioPotencial.getLongitude());

                db.insertWithOnConflict(PredioPotencialContract.PredioPotencialEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved predio potencial in DB -> predioPotencial: " + predioPotencial.getName());

                subscriber.onNext(predioPotencial);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<PredioPotencial>> savePrediosPotenciales(List<PredioPotencial> remotePredios) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                for (PredioPotencial remotePredioPotencial : remotePredios) {
                    savePredioPotencial(remotePredioPotencial).subscribe();
                }

                clearAlreadySentPredios(remotePredios);
                clearPrediosDeletedInRemote(remotePredios);

                db.setTransactionSuccessful();

                subscriber.onNext(remotePredios);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    private void clearAlreadySentPredios(List<PredioPotencial> remotePredios) {

        SQLiteDatabase db = getReadableDatabase();
        db.beginTransactionNonExclusive();

        try {

            String[] projection = getPredioPotencialProjection();
            String sortOrder = PredioPotencialContract.PredioPotencialEntry._ID + " DESC";
            String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 1000);

            Cursor cursor = db.query(
                    PredioPotencialContract.PredioPotencialEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    sortOrder,
                    queryOffsetAndLimit
            );

            List<PredioPotencial> localPredios = new ArrayList<>();

            while (cursor.moveToNext()) {
                PredioPotencial localPredio = getPredioPotencialObject(cursor);
                Survey survey = getLocalSurveyByPredioPotencialId(localPredio.getId());
                CartaIntencion cartaIntencion = getLocalCartaIntencionByPredioPotencialId(localPredio.getId());
                if ((survey != null && survey.isHasBeenSent())
                        && cartaIntencion != null && cartaIntencion.isHasBeenSent()) {
                    localPredios.add(localPredio);
                }
            }

            cursor.close();

            for (PredioPotencial localPredio : localPredios) {
                boolean foundLocally = false;
                for (PredioPotencial remotePredio : remotePredios) {
                    if (remotePredio.getRemoteId() == localPredio.getRemoteId()) {
                        foundLocally = true;
                    }
                }
                if (!foundLocally) {
                    deletePredioByRemoteId(localPredio.getRemoteId());
                }
            }

            db.setTransactionSuccessful();

        } catch (Exception ex) {
            Log.e(getClass().getSimpleName(), ex.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    private void clearPrediosDeletedInRemote(List<PredioPotencial> remotePredios) {

        SQLiteDatabase db = getReadableDatabase();
        db.beginTransactionNonExclusive();

        try {

            String[] projection = getPredioPotencialProjection();
            String sortOrder = PredioPotencialContract.PredioPotencialEntry._ID + " DESC";
            String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 1000);

            Cursor cursor = db.query(
                    PredioPotencialContract.PredioPotencialEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    sortOrder,
                    queryOffsetAndLimit
            );

            List<PredioPotencial> localPredios = new ArrayList<>();

            while (cursor.moveToNext()) {
                PredioPotencial localPredio = getPredioPotencialObject(cursor);
                if (localPredio.getRemoteId() > 0) {
                    localPredios.add(localPredio);
                }
            }

            cursor.close();

            for (PredioPotencial localPredio : localPredios) {
                boolean foundRemotely = false;
                for (PredioPotencial remotePredio : remotePredios) {
                    if (remotePredio.getRemoteId() == localPredio.getRemoteId()) {
                        foundRemotely = true;
                    }
                }
                if (!foundRemotely) {
                    deletePredioByRemoteId(localPredio.getRemoteId());
                }
            }

            db.setTransactionSuccessful();

        } catch (Exception ex) {
            Log.e(getClass().getSimpleName(), ex.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    public void deletePredioByRemoteId(long remoteId) {

        SQLiteDatabase db = getReadableDatabase();
        db.beginTransactionNonExclusive();

        try {

            String selection = PredioPotencialContract.PredioPotencialEntry.COLUMN_PREDIO_POTENCIAL_REMOTE_ID + " = ?";
            String[] selectionArgs = {
                    String.valueOf(remoteId)
            };

            int deleted = db.delete(PredioPotencialContract.PredioPotencialEntry.TABLE_NAME, selection, selectionArgs);
            db.setTransactionSuccessful();

            Log.d(getClass().getSimpleName(), "Deleted " + deleted + " predio potencial by remoteId: " + remoteId);

        } catch (Exception ex) {
            Log.e(getClass().getSimpleName(), ex.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    Observable<Boolean> deletePredioByRemoteIdSubscription(long remoteId) {
        return Observable.create(subscriber -> {
            deletePredioByRemoteId(remoteId);
            subscriber.onNext(true);
            subscriber.onComplete();
        });
    }

    private PredioPotencial getSavedPredioPotencialName(String predioName) {

        PredioPotencial predioPotencial = null;

        SQLiteDatabase db = getReadableDatabase();
        db.beginTransactionNonExclusive();

        try {

            String[] projection = getPredioPotencialProjection();
            String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 1);
            String selection = PredioPotencialContract.PredioPotencialEntry.COLUMN_NAME + " = ?";
            String[] selectionArgs = {
                    predioName
            };

            Cursor cursor = db.query(
                    PredioPotencialContract.PredioPotencialEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null,
                    queryOffsetAndLimit
            );

            while (cursor.moveToNext()) {
                predioPotencial = getPredioPotencialObject(cursor);
            }

            cursor.close();
            db.setTransactionSuccessful();

        } catch (Exception ex) {
            Log.e(getClass().getSimpleName(), ex.getMessage());
        } finally {
            db.endTransaction();
        }

        return predioPotencial;
    }

    Observable<List<PredioPotencial>> getPrediosPotenciales(int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getPredioPotencialProjection();
                String sortOrder = PredioPotencialContract.PredioPotencialEntry._ID + " DESC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);

                Cursor cursor = db.query(
                        PredioPotencialContract.PredioPotencialEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<PredioPotencial> prediosPotenciales = new ArrayList<>();

                while (cursor.moveToNext()) {
                    prediosPotenciales.add(getPredioPotencialObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(prediosPotenciales);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<PredioPotencial>> deletePrediosPotenciales() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                db.execSQL("DELETE FROM " + PredioPotencialContract.PredioPotencialEntry.TABLE_NAME);
                db.setTransactionSuccessful();

                subscriber.onNext(new ArrayList<>());
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Survey> deleteSurvey(int predioPotencialId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String selection = SurveyContract.SurveyEntry.COLUMN_PREDIO_POTENCIAL_ID + " = ?";
                String[] selectionArgs = {
                        String.valueOf(predioPotencialId)
                };

                db.delete(SurveyContract.SurveyEntry.TABLE_NAME, selection, selectionArgs);
                db.setTransactionSuccessful();

                subscriber.onNext(new Survey());
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Item>> saveAcciones(List<Item> acciones) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                for (Item accion : acciones) {

                    values.put(AccionContract.AccionEntry.COLUMN_ACCION_ID, ((Action) accion).getId());
                    values.put(AccionContract.AccionEntry.COLUMN_ACCION_NAME, ((Action) accion).getTitle());
                    values.put(AccionContract.AccionEntry.COLUMN_ACCION_TYPE, ((Action) accion).getAccionType());
                    values.put(AccionContract.AccionEntry.COLUMN_ACCION_COLOR, ((Action) accion).getColor());
                    values.put(AccionContract.AccionEntry.COLUMN_ACCION_FILL_COLOR, ((Action) accion).getFillColor());

                    db.insertWithOnConflict(AccionContract.AccionEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                    Log.d(getClass().getName(), "Saved accion in DB -> " + ((Action) accion).getId());
                }

                db.setTransactionSuccessful();

                subscriber.onNext(acciones);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Action>> getAcciones(String type, int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getAccionProjection();
                String sortOrder = AccionContract.AccionEntry.COLUMN_ACCION_ID + " ASC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = AccionContract.AccionEntry.COLUMN_ACCION_TYPE + " = ?";
                String[] selectionArgs = {
                        type
                };

                Cursor cursor = db.query(
                        AccionContract.AccionEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<Action> acciones = new ArrayList<>();

                while (cursor.moveToNext()) {
                    acciones.add(getAccionObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(acciones);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Item>> saveMaterials(List<Item> materials) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                for (Item material : materials) {

                    values.put(MaterialContract.MaterialEntry.COLUMN_MATERIAL_ID, ((Material) material).getId());
                    values.put(MaterialContract.MaterialEntry.COLUMN_MATERIAL_NAME, ((Material) material).getTitle());
                    values.put(MaterialContract.MaterialEntry.COLUMN_MATERIAL_PRICE, ((Material) material).getPrice());
                    values.put(MaterialContract.MaterialEntry.COLUMN_MATERIAL_MEASUREMENT, ((Material) material).getMeasurement());
                    values.put(MaterialContract.MaterialEntry.COLUMN_MATERIAL_TYPE, ((Material) material).getMaterialType());
                    values.put(MaterialContract.MaterialEntry.COLUMN_MATERIAL_UNIT, ((Material) material).getUnit());

                    StringBuilder commaSepValueBuilder = new StringBuilder();
                    for (int i = 0; i < ((Material) material).getActionIds().size(); i++) {
                        commaSepValueBuilder.append(((Material) material).getActionIds().get(i));
                        if (i != ((Material) material).getActionIds().size() - 1) {
                            commaSepValueBuilder.append(", ");
                        }
                    }
                    values.put(MaterialContract.MaterialEntry.COLUMN_MATERIAL_ACTION, commaSepValueBuilder.toString());

                    db.insertWithOnConflict(MaterialContract.MaterialEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                    Log.d(getClass().getName(), "Saved material in DB -> " + ((Material) material).getId());
                }

                db.setTransactionSuccessful();

                subscriber.onNext(materials);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Material>> getMaterials(int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getMaterialProjection();
                String sortOrder = MaterialContract.MaterialEntry.COLUMN_MATERIAL_NAME + " ASC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);

                Cursor cursor = db.query(
                        MaterialContract.MaterialEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<Material> materials = new ArrayList<>();

                while (cursor.moveToNext()) {
                    materials.add(getMaterialObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(materials);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<MonitorAndMaintenancePhoto> saveMonitorAndMaintenancePhoto(MonitorAndMaintenancePhoto monitorAndMaintenancePhoto) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.COLUMN_MONITOR_AND_MAINTENANCE_PHOTO_NAME, monitorAndMaintenancePhoto.getPhotoName());
                values.put(MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID, monitorAndMaintenancePhoto.getMonitorAndMaintenanceCommentPointId());

                db.insertWithOnConflict(MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                Log.d(getClass().getName(), "Saved monitorAndMaintenancePhoto in DB -> " + monitorAndMaintenancePhoto.getPhotoName());

                db.setTransactionSuccessful();

                subscriber.onNext(monitorAndMaintenancePhoto);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<PhotographicRegistryPhoto> savePhotographicRegistry(PhotographicRegistryPhoto photographicRegistryPhoto) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_NAME, photographicRegistryPhoto.getPhotoName());
                values.put(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_COMMENT, photographicRegistryPhoto.getComment());
                values.put(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_FOREIGN_ID, photographicRegistryPhoto.getTaskId());

                long id = db.insertWithOnConflict(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                Log.d(getClass().getName(), "Saved photographicRegistryPhoto in DB -> " + photographicRegistryPhoto.getPhotoName());

                db.setTransactionSuccessful();

                photographicRegistryPhoto.setId(String.valueOf(id));

                subscriber.onNext(photographicRegistryPhoto);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<PhotographicRegistryPhoto> updatePhotographicRegistry(PhotographicRegistryPhoto photographicRegistryPhoto) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry._ID, photographicRegistryPhoto.getId());
                values.put(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_NAME, photographicRegistryPhoto.getPhotoName());
                values.put(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_COMMENT, photographicRegistryPhoto.getComment());
                values.put(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_FOREIGN_ID, photographicRegistryPhoto.getTaskId());

                long id = db.insertWithOnConflict(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                Log.d(getClass().getName(), "Updated photographicRegistryPhoto in DB -> " + photographicRegistryPhoto.getPhotoName());

                db.setTransactionSuccessful();

                photographicRegistryPhoto.setId(String.valueOf(id));

                subscriber.onNext(photographicRegistryPhoto);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deletePhotographicRegistry(List<String> photographicRegistryIds) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                for (String photographicRegistryId : photographicRegistryIds) {
                    String whereClause = PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry._ID + " = CAST(? AS INTEGER)";
                    String[] whereArgs = {
                            photographicRegistryId
                    };

                    int numberOfPhotosDeleted = db.delete(PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.TABLE_NAME, whereClause, whereArgs);
                    Log.d(getClass().getSimpleName(), "deletePhotographicRegistry: numberOfPhotosDeleted -> " + numberOfPhotosDeleted);
                }

                db.setTransactionSuccessful();

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<MonitorAndMaintenancePhoto>> getMonitorAndMaintenancePhotos(String monitorAndMaintenanceCommentPointId, int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getMonitorAndMaintenancePhotoProjection();
                String sortOrder = MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.COLUMN_MONITOR_AND_MAINTENANCE_PHOTO_NAME + " ASC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID + " = ?";
                String[] selectionArgs = {
                        monitorAndMaintenanceCommentPointId
                };

                Cursor cursor = db.query(
                        MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<MonitorAndMaintenancePhoto> monitorAndMaintenancePhotos = new ArrayList<>();

                while (cursor.moveToNext()) {
                    monitorAndMaintenancePhotos.add(getMonitorAndMaintenancePhotoObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(monitorAndMaintenancePhotos);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<PhotographicRegistryPhoto>> getPhotographicRegistryPhotos(String taskId, int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getPhotographicRegistryPhotoProjection();
                String sortOrder = PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_NAME + " DESC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.COLUMN_FOREIGN_ID + " = ?";
                String[] selectionArgs = {
                        taskId
                };

                Cursor cursor = db.query(
                        PhotographicRegistryPhotoContract.PhotographicRegistryPhotoEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<PhotographicRegistryPhoto> photographicRegistryPhotos = new ArrayList<>();

                while (cursor.moveToNext()) {
                    photographicRegistryPhotos.add(getPhotographicRegistryPhotoObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(photographicRegistryPhotos);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Item>> saveMonitorAndMaintenances(List<Item> monitorAndMaintenanceList) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                for (Item monitorAndMaintenance : monitorAndMaintenanceList) {

                    values.put(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID, ((MonitorAndMaintenance) monitorAndMaintenance).getId());
                    values.put(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_TITLE, ((MonitorAndMaintenance) monitorAndMaintenance).getTitle());
                    values.put(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_PROCEDURE_ID, ((MonitorAndMaintenance) monitorAndMaintenance).getProcedureId());
                    values.put(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_DUE_DATE, ((MonitorAndMaintenance) monitorAndMaintenance).getDueDate());
                    values.put(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_DUE_DATE_STATUS, ((MonitorAndMaintenance) monitorAndMaintenance).getDueDateStatus());

                    db.insertWithOnConflict(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                    Log.d(getClass().getName(), "Saved vegetalMaintenance in DB -> " + ((MonitorAndMaintenance) monitorAndMaintenance).getId());
                }

                db.setTransactionSuccessful();

                subscriber.onNext(monitorAndMaintenanceList);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<MonitorAndMaintenance>> getMonitorAndMaintenanceList(String procedureId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getMonitorAndMaintenanceProjection();
                String sortOrder = MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " ASC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection;
                String selectionArgsItem;
                if (TextUtils.isEmpty(procedureId)) {
                    selection = MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT + " !=CAST(? AS INTEGER) ";
                    selectionArgsItem = "1";
                } else {
                    selection = MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_PROCEDURE_ID + " = ?";
                    selectionArgsItem = procedureId;
                }
                String[] selectionArgs = {
                        selectionArgsItem
                };

                Cursor cursor = db.query(
                        MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<MonitorAndMaintenance> monitorAndMaintenanceList = new ArrayList<>();

                while (cursor.moveToNext()) {
                    monitorAndMaintenanceList.add(getMonitorAndMaintenanceObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(monitorAndMaintenanceList);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<MonitorAndMaintenanceCommentPoint> saveMonitorAndMaintenanceCommentPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                if (!TextUtils.isEmpty(monitorAndMaintenanceCommentPoint.getId())) {
                    values.put(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID, monitorAndMaintenanceCommentPoint.getCleanedId());
                }
                values.put(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID, monitorAndMaintenanceCommentPoint.getMonitorAndMaintenanceId());
                values.put(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_COMMENT, monitorAndMaintenanceCommentPoint.getComment());
                values.put(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_LAT, monitorAndMaintenanceCommentPoint.getLocation().getLatitude());
                values.put(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_LNG, monitorAndMaintenanceCommentPoint.getLocation().getLongitude());
                values.put(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_FROM_SERVICE, monitorAndMaintenanceCommentPoint.isFromService());

                long newRowId = db.insertWithOnConflict(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                monitorAndMaintenanceCommentPoint.setId(String.valueOf(newRowId));

                Log.d(getClass().getName(), "Saved monitorAndMaintenanceCommentPoint in DB -> " + monitorAndMaintenanceCommentPoint.getId());

                subscriber.onNext(monitorAndMaintenanceCommentPoint);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<MonitorAndMaintenanceCommentPoint>> getMonitorAndMaintenanceCommentPoints(String monitorAndMaintenanceId, int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getMonitorAndMaintenanceCommentPointProjection();
                String sortOrder = MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID + " ASC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] selectionArgs = {
                        monitorAndMaintenanceId
                };

                Cursor cursor = db.query(
                        MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<MonitorAndMaintenanceCommentPoint> monitorAndMaintenanceCommentPointList = new ArrayList<>();

                while (cursor.moveToNext()) {
                    monitorAndMaintenanceCommentPointList.add(getMonitorAndMaintenanceCommentPointObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(monitorAndMaintenanceCommentPointList);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteMonitorAndMaintenanceCommentPoint(String monitorAndMaintenanceIdCommentPointId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID + " = ?";
                String[] whereArgs = {
                        monitorAndMaintenanceIdCommentPointId
                };

                int numberOfPhotosDeleted = db.delete(MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.TABLE_NAME, whereClause, whereArgs);

                String whereClause2 = MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID + " = ?";
                String[] whereArgs2 = {
                        monitorAndMaintenanceIdCommentPointId
                };

                int numberOfPhotoCommentPointsDeleted = db.delete(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.TABLE_NAME, whereClause2, whereArgs2);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleteMonitorAndMaintenanceCommentPoint: numberOfPhotosDeleted -> " + numberOfPhotosDeleted);
                Log.d(getClass().getSimpleName(), "deleteMonitorAndMaintenanceCommentPoint: numberOfPhotoCommentPointsDeleted -> " + numberOfPhotoCommentPointsDeleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteMonitorAndMaintenanceCommentPointByMonitorAndMaintenanceId(String monitorAndMaintenanceId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getMonitorAndMaintenanceCommentPointProjection();
                String sortOrder = MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID + " ASC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection = MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] selectionArgs = {
                        monitorAndMaintenanceId
                };

                Cursor cursor = db.query(
                        MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<MonitorAndMaintenanceCommentPoint> monitorAndMaintenanceCommentPointList = new ArrayList<>();

                while (cursor.moveToNext()) {
                    monitorAndMaintenanceCommentPointList.add(getMonitorAndMaintenanceCommentPointObject(cursor));
                }

                int numberOfPhotosDeleted = 0;
                int numberOfPhotoCommentPointsDeleted = 0;
                int numberOfMonitorAndMaintenancesDeleted = 0;

                for (MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint : monitorAndMaintenanceCommentPointList) {

                    String whereClause = MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID + " = ?";
                    String[] whereArgs = {
                            monitorAndMaintenanceCommentPoint.getId()
                    };

                    numberOfPhotosDeleted = db.delete(MonitorAndMaintenancePhotoContract.MonitorAndMaintenancePhotoEntry.TABLE_NAME, whereClause, whereArgs);
                }

                String whereClause2 = MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] whereArgs2 = {
                        monitorAndMaintenanceId
                };

                numberOfPhotoCommentPointsDeleted = db.delete(MonitorAndMaintenanceCommentPointContract.MonitorAndMaintenanceCommentPointEntry.TABLE_NAME, whereClause2, whereArgs2);

                String whereClause3 = MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] whereArgs3 = {
                        monitorAndMaintenanceId
                };

                numberOfMonitorAndMaintenancesDeleted = db.delete(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.TABLE_NAME, whereClause3, whereArgs3);

                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleteMonitorAndMaintenanceCommentPoint: numberOfPhotosDeleted -> " + numberOfPhotosDeleted);
                Log.d(getClass().getSimpleName(), "deleteMonitorAndMaintenanceCommentPoint: numberOfPhotoCommentPointsDeleted -> " + numberOfPhotoCommentPointsDeleted);
                Log.d(getClass().getSimpleName(), "deleteMonitorAndMaintenanceCommentPoint: numberOfMonitorAndMaintenanceDeleted -> " + numberOfMonitorAndMaintenancesDeleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<MonitorAndMaintenance>> getPendingMonitorAndMaintenances() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getMonitorAndMaintenanceProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 1000);
                String selection = MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT + " =CAST(? AS INTEGER) ";
                String[] selectionArgs = {
                        "1"
                };

                Cursor cursor = db.query(
                        MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<MonitorAndMaintenance> monitorAndMaintenances = new ArrayList<>();

                while (cursor.moveToNext()) {
                    monitorAndMaintenances.add(getMonitorAndMaintenanceObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(monitorAndMaintenances);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<GeoJson> getGeoJson(String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getGeoJsonProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection = GeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID + " = ?";
                String[] selectionArgs = {
                        taskId
                };

                Cursor cursor = db.query(
                        GeoJsonContract.GeoJsonEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                GeoJson geoJson = new GeoJson();

                while (cursor.moveToNext()) {
                    geoJson = getGeoJsonObject(cursor);
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(geoJson);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteGeoJson(String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = GeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID + " = ?";
                String[] whereArgs = {
                        taskId
                };

                int deleted = db.delete(GeoJsonContract.GeoJsonEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted geoJson: -> " + deleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<GeoJson> getExecutionBaseGeoJson(String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getGeoJsonProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection = ExecutionBaseGeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID + " = ?";
                String[] selectionArgs = {
                        taskId
                };

                Cursor cursor = db.query(
                        ExecutionBaseGeoJsonContract.GeoJsonEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                GeoJson geoJson = new GeoJson();

                while (cursor.moveToNext()) {
                    geoJson = getGeoJsonObject(cursor);
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(geoJson);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteExecutionBaseGeoJson(String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = ExecutionBaseGeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID + " = ?";
                String[] whereArgs = {
                        taskId
                };

                int deleted = db.delete(ExecutionBaseGeoJsonContract.GeoJsonEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted execution base geoJson: -> " + deleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<GeoJson> getExecutionEditedGeoJson(String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getGeoJsonProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection = ExecutionEditedGeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID + " = ?";
                String[] selectionArgs = {
                        taskId
                };

                Cursor cursor = db.query(
                        ExecutionEditedGeoJsonContract.GeoJsonEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                GeoJson geoJson = new GeoJson();

                while (cursor.moveToNext()) {
                    geoJson = getGeoJsonObject(cursor);
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(geoJson);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteExecutionEditedGeoJson(String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = ExecutionEditedGeoJsonContract.GeoJsonEntry.COLUMN_TASK_ID + " = ?";
                String[] whereArgs = {
                        taskId
                };

                int deleted = db.delete(ExecutionEditedGeoJsonContract.GeoJsonEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted execution edited geoJson: -> " + deleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<MonitorAndMaintenance> getMonitorAndMaintenanceById(String monitorAndMaintenanceId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getMonitorAndMaintenanceProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection = MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " =CAST(? AS INTEGER) ";
                String[] selectionArgs = {
                        monitorAndMaintenanceId
                };

                Cursor cursor = db.query(
                        MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                while (cursor.moveToNext()) {
                    subscriber.onNext(getMonitorAndMaintenanceObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Item> saveMonitorAndMaintenance(Item monitorAndMaintenance) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID, ((MonitorAndMaintenance) monitorAndMaintenance).getId());
                values.put(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_TITLE, ((MonitorAndMaintenance) monitorAndMaintenance).getTitle());
                values.put(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_DUE_DATE, ((MonitorAndMaintenance) monitorAndMaintenance).getDueDate());
                values.put(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_DUE_DATE_STATUS, ((MonitorAndMaintenance) monitorAndMaintenance).getDueDateStatus());
                values.put(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_JSON_FEATURE, ((MonitorAndMaintenance) monitorAndMaintenance).getJsonFeature());
                values.put(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT, ((MonitorAndMaintenance) monitorAndMaintenance).isPendingToBeSent());

                db.insertWithOnConflict(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved vegetalMaintenance in DB -> " + ((MonitorAndMaintenance) monitorAndMaintenance).getId());

                subscriber.onNext(monitorAndMaintenance);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteMonitorAndMaintenanceById(String monitorAndMaintenanceId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] whereArgs = {
                        monitorAndMaintenanceId
                };

                int numberMonitorAndMaintenanceDeleted = db.delete(MonitorAndMaintenanceContract.MonitorAndMaintenanceEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted monitorAndMaintenanceById: numberMonitorAndMaintenanceDeleted -> " + numberMonitorAndMaintenanceDeleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> savePQRS(PQRS pqrs) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                String pqrsString = serializationManager.toJson(pqrs);

                values.put(PQRSContract.PQRSEntry.COLUMN_DATA, pqrsString);

                db.insertWithOnConflict(PQRSContract.PQRSEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved PQRS in DB");

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<PQRS>> getAllPQRS() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getPQRSProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 500);

                Cursor cursor = db.query(
                        PQRSContract.PQRSEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<PQRS> pqrsList = new ArrayList<>();

                while (cursor.moveToNext()) {
                    pqrsList.add(getPQRSObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(pqrsList);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<PredioPotencial>> deleteAllPQRS() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                db.execSQL("DELETE FROM " + PQRSContract.PQRSEntry.TABLE_NAME);
                db.setTransactionSuccessful();

                subscriber.onNext(new ArrayList<>());
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> saveDependencies(List<Item> dependencies) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                for (Item dependency : dependencies) {

                    values.put(DependencyContract.DependencyEntry.COLUMN_DEPENDENCY_ID, ((Dependency) dependency).getId());
                    values.put(DependencyContract.DependencyEntry.COLUMN_DEPENDENCY_NAME, ((Dependency) dependency).getTitle());

                    db.insertWithOnConflict(DependencyContract.DependencyEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                    Log.d(getClass().getName(), "Saved dependency in DB -> " + ((Dependency) dependency).getId());
                }

                db.setTransactionSuccessful();

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Dependency>> getDependencies() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getDependencyProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 500);

                Cursor cursor = db.query(
                        DependencyContract.DependencyEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<Dependency> dependencies = new ArrayList<>();

                while (cursor.moveToNext()) {
                    dependencies.add(getDependencyObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(dependencies);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> savePQRSTypes(List<Item> pqrsTypes) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                for (Item pqrsType : pqrsTypes) {

                    values.put(PQRSTypeContract.PQRSTypeEntry.COLUMN_PQRS_TYPE_ID, ((PQRSType) pqrsType).getId());
                    values.put(PQRSTypeContract.PQRSTypeEntry.COLUMN_DEPENDENCY_ID, ((PQRSType) pqrsType).getDependencyId());
                    values.put(PQRSTypeContract.PQRSTypeEntry.COLUMN_PQRS_TYPE_NAME, ((PQRSType) pqrsType).getTitle());

                    db.insertWithOnConflict(PQRSTypeContract.PQRSTypeEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                    Log.d(getClass().getName(), "Saved pqrsType in DB -> " + ((PQRSType) pqrsType).getId());
                }

                db.setTransactionSuccessful();

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<PQRSType>> getPQRSTypes(String dependencyId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getPQRSTypeProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 500);
                String selection = PQRSTypeContract.PQRSTypeEntry.COLUMN_DEPENDENCY_ID + " =CAST(? AS INTEGER) ";
                String[] selectionArgs = {
                        dependencyId
                };

                Cursor cursor = db.query(
                        PQRSTypeContract.PQRSTypeEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<PQRSType> dependencies = new ArrayList<>();

                while (cursor.moveToNext()) {
                    dependencies.add(getPQRSTypeObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(dependencies);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<StardMonitorAndMaintenance> saveStardMonitorAndMaintenance(StardMonitorAndMaintenance stardMonitorAndMaintenance) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID, Long.parseLong(stardMonitorAndMaintenance.getMonitorAndMaintenanceId()));
                values.put(StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_DATA, serializationManager.toJson(stardMonitorAndMaintenance));
                values.put(StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT, stardMonitorAndMaintenance.isPendingToBeSent());

                db.insertWithOnConflict(StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved stardMonitorAndMaintenance in DB -> stardMonitorAndMaintenanceId: " + stardMonitorAndMaintenance.getMonitorAndMaintenanceId());

                subscriber.onNext(stardMonitorAndMaintenance);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<StardMonitorAndMaintenance> getStardMonitorAndMaintenance(String monitorAndMaintenanceId, int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getStardMonitorAndMaintenanceProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] selectionArgs = {
                        monitorAndMaintenanceId
                };

                Cursor cursor = db.query(
                        StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                StardMonitorAndMaintenance stardMonitorAndMaintenance = new StardMonitorAndMaintenance();

                while (cursor.moveToNext()) {
                    stardMonitorAndMaintenance = getStardMonitorAndMaintenanceObject(cursor);
                    stardMonitorAndMaintenance.initNullFields();
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(stardMonitorAndMaintenance);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteStardMonitorAndMaintenance(String stardMonitorAndMaintenanceId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] whereArgs = {
                        stardMonitorAndMaintenanceId
                };

                int deleted = db.delete(StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted stardMonitorAndMaintenance: -> " + deleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<StardMonitorAndMaintenance>> getPendingStardMonitorAndMaintenances() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getStardMonitorAndMaintenanceProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 1000);
                String selection = StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT + " =CAST(? AS INTEGER) ";
                String[] selectionArgs = {
                        "1"
                };

                Cursor cursor = db.query(
                        StardMonitorAndMaintenanceContract.StardMonitorAndMaintenanceEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<StardMonitorAndMaintenance> stardMonitorAndMaintenances = new ArrayList<>();

                while (cursor.moveToNext()) {
                    stardMonitorAndMaintenances.add(getStardMonitorAndMaintenanceObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(stardMonitorAndMaintenances);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<ContractSiembra> saveContractSiembra(ContractSiembra contractSiembra) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(ContractSiembraContract.ContractSiembraEntry.COLUMN_ID, contractSiembra.getId());
                values.put(ContractSiembraContract.ContractSiembraEntry.COLUMN_DATA, serializationManager.toJson(contractSiembra));
                values.put(ContractSiembraContract.ContractSiembraEntry.COLUMN_PENDING_TO_BE_SENT, contractSiembra.isPendingToBeSent());

                db.insertWithOnConflict(ContractSiembraContract.ContractSiembraEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved contractSiembra in DB -> contractSiembraId: " + contractSiembra.getId());

                subscriber.onNext(contractSiembra);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<ContractSiembra> getContractSiembra(String contractSiembraId, int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getContractSiembraProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = ContractSiembraContract.ContractSiembraEntry.COLUMN_ID + " = ?";
                String[] selectionArgs = {
                        contractSiembraId
                };

                Cursor cursor = db.query(
                        ContractSiembraContract.ContractSiembraEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                ContractSiembra contractSiembra = new ContractSiembra();

                while (cursor.moveToNext()) {
                    contractSiembra = getContractSiembraObject(cursor);
                    contractSiembra.initNullFields();
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(contractSiembra);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteContractSiembra(String contractSiembraId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = ContractSiembraContract.ContractSiembraEntry.COLUMN_ID + " = ?";
                String[] whereArgs = {
                        contractSiembraId
                };

                int deleted = db.delete(ContractSiembraContract.ContractSiembraEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted contractSiembra: -> " + deleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<ContractSiembra>> getPendingContractSiembras() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getContractSiembraProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 1000);
                String selection = ContractSiembraContract.ContractSiembraEntry.COLUMN_PENDING_TO_BE_SENT + " =CAST(? AS INTEGER) ";
                String[] selectionArgs = {
                        "1"
                };

                Cursor cursor = db.query(
                        ContractSiembraContract.ContractSiembraEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<ContractSiembra> contractSiembras = new ArrayList<>();

                while (cursor.moveToNext()) {
                    contractSiembras.add(getContractSiembraObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(contractSiembras);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<VegetalMaintenance> saveVegetalMaintenance(VegetalMaintenance vegetalMaintenance) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID, Long.parseLong(vegetalMaintenance.getMonitorAndMaintenanceId()));
                values.put(VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_DATA, serializationManager.toJson(vegetalMaintenance));
                values.put(VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT, vegetalMaintenance.isPendingToBeSent());

                db.insertWithOnConflict(VegetalMaintenanceContract.VegetalMaintenanceEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved vegetalMaintenance in DB -> vegetalMaintenanceId: " + vegetalMaintenance.getMonitorAndMaintenanceId());

                subscriber.onNext(vegetalMaintenance);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<VegetalMaintenance> getVegetalMaintenance(String monitorAndMaintenanceId, int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getVegetalMaintenanceProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] selectionArgs = {
                        monitorAndMaintenanceId
                };

                Cursor cursor = db.query(
                        VegetalMaintenanceContract.VegetalMaintenanceEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                VegetalMaintenance vegetalMaintenance = new VegetalMaintenance();

                while (cursor.moveToNext()) {
                    vegetalMaintenance = getVegetalMaintenanceObject(cursor);
                    vegetalMaintenance.initNullFields();
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(vegetalMaintenance);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteVegetalMaintenance(String vegetalMaintenanceId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] whereArgs = {
                        vegetalMaintenanceId
                };

                int deleted = db.delete(VegetalMaintenanceContract.VegetalMaintenanceEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted vegetalMaintenance: -> " + deleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<VegetalMaintenance>> getPendingVegetalMaintenances() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getVegetalMaintenanceProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 1000);
                String selection = VegetalMaintenanceContract.VegetalMaintenanceEntry.COLUMN_PENDING_TO_BE_SENT + " =CAST(? AS INTEGER) ";
                String[] selectionArgs = {
                        "1"
                };

                Cursor cursor = db.query(
                        VegetalMaintenanceContract.VegetalMaintenanceEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<VegetalMaintenance> vegetalMaintenances = new ArrayList<>();

                while (cursor.moveToNext()) {
                    vegetalMaintenances.add(getVegetalMaintenanceObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(vegetalMaintenances);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<PredioFollowUp> savePredioFollowUp(PredioFollowUp predioFollowUp) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(PredioFollowUpContract.PredioFollowUpEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID, Long.parseLong(predioFollowUp.getMonitorAndMaintenanceId()));
                values.put(PredioFollowUpContract.PredioFollowUpEntry.COLUMN_DATA, serializationManager.toJson(predioFollowUp));
                values.put(PredioFollowUpContract.PredioFollowUpEntry.COLUMN_PENDING_TO_BE_SENT, predioFollowUp.isPendingToBeSent());

                db.insertWithOnConflict(VegetalMaintenanceContract.VegetalMaintenanceEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved predioFollowUp in DB -> predioFollowUpId: " + predioFollowUp.getMonitorAndMaintenanceId());

                subscriber.onNext(predioFollowUp);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<PredioFollowUp> getPredioFollowUp(String monitorAndMaintenanceId, int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getPredioFollowUpProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = PredioFollowUpContract.PredioFollowUpEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] selectionArgs = {
                        monitorAndMaintenanceId
                };

                Cursor cursor = db.query(
                        PredioFollowUpContract.PredioFollowUpEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                PredioFollowUp predioFollowUp = new PredioFollowUp();

                while (cursor.moveToNext()) {
                    predioFollowUp = getPredioFollowUpObject(cursor);
                    predioFollowUp.initNullFields();
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(predioFollowUp);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deletePredioFollowUp(String predioFollowUpId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = PredioFollowUpContract.PredioFollowUpEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] whereArgs = {
                        predioFollowUpId
                };

                int deleted = db.delete(PredioFollowUpContract.PredioFollowUpEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted vegetalMaintenance: -> " + deleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteCartaIntencion(String cartaIntencionId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = CartaIntencionContract.CartaIntencionEntry._ID + " = ?";
                String[] whereArgs = {
                        cartaIntencionId
                };

                int deleted = db.delete(CartaIntencionContract.CartaIntencionEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted cartaIntencion: -> " + deleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<PredioFollowUp>> getPendingPredioFollowUps() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getPredioFollowUpProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 1000);
                String selection = PredioFollowUpContract.PredioFollowUpEntry.COLUMN_PENDING_TO_BE_SENT + " =CAST(? AS INTEGER) ";
                String[] selectionArgs = {
                        "1"
                };

                Cursor cursor = db.query(
                        PredioFollowUpContract.PredioFollowUpEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<PredioFollowUp> predioFollowUps = new ArrayList<>();

                while (cursor.moveToNext()) {
                    predioFollowUps.add(getPredioFollowUpObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(predioFollowUps);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<CartaIntencion> saveCartaIntencion(CartaIntencion cartaIntencion) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                String cartaIntencionString = serializationManager.toJson(cartaIntencion);

                values.put(CartaIntencionContract.CartaIntencionEntry.COLUMN_PREDIO_POTENCIAL_ID, cartaIntencion.getPredioPotencialId());
                values.put(CartaIntencionContract.CartaIntencionEntry.COLUMN_DATA, cartaIntencionString);

                db.insertWithOnConflict(CartaIntencionContract.CartaIntencionEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved cartaIntencion in DB");

                subscriber.onNext(cartaIntencion);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<CartaIntencion> getCartaIntencionByPredioPotencialId(String predioPotencialId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getCartaIntencionProjection();
                String sortOrder = CartaIntencionContract.CartaIntencionEntry.COLUMN_PREDIO_POTENCIAL_ID + " DESC";
                String selection = CartaIntencionContract.CartaIntencionEntry.COLUMN_PREDIO_POTENCIAL_ID + " = ?";
                String[] selectionArgs = {
                        predioPotencialId
                };

                Cursor cursor = db.query(
                        CartaIntencionContract.CartaIntencionEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                CartaIntencion cartaIntencion = new CartaIntencion();
                cartaIntencion.initNullFields();

                while (cursor.moveToNext()) {
                    cartaIntencion = getCartaIntencionObject(cursor);
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(cartaIntencion);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    private CartaIntencion getLocalCartaIntencionByPredioPotencialId(String predioPotencialId) {

        CartaIntencion cartaIntencion = null;

        SQLiteDatabase db = getReadableDatabase();
        db.beginTransactionNonExclusive();

        try {

            String[] projection = getCartaIntencionProjection();
            String sortOrder = CartaIntencionContract.CartaIntencionEntry.COLUMN_PREDIO_POTENCIAL_ID + " DESC";
            String selection = CartaIntencionContract.CartaIntencionEntry.COLUMN_PREDIO_POTENCIAL_ID + " = ?";
            String[] selectionArgs = {
                    predioPotencialId
            };

            Cursor cursor = db.query(
                    CartaIntencionContract.CartaIntencionEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );

            while (cursor.moveToNext()) {
                cartaIntencion = getCartaIntencionObject(cursor);
                cartaIntencion.initNullFields();
            }

            cursor.close();
            db.setTransactionSuccessful();

        } catch (Exception ex) {
            Log.e(getClass().getSimpleName(), ex.getMessage());
        } finally {
            db.endTransaction();
        }

        return cartaIntencion;
    }

    Observable<ContractorEvaluation> saveContractorEvaluation(ContractorEvaluation contractorEvaluation) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID, Long.parseLong(contractorEvaluation.getMonitorAndMaintenanceId()));
                values.put(ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_DATA, serializationManager.toJson(contractorEvaluation));
                values.put(ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_PENDING_TO_BE_SENT, contractorEvaluation.isPendingToBeSent());

                db.insertWithOnConflict(ContractorEvaluationContract.ContractorEvaluationEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved contractorEvaluation in DB -> contractorEvaluationId: " + contractorEvaluation.getMonitorAndMaintenanceId());

                subscriber.onNext(contractorEvaluation);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<ContractorEvaluation> getContractorEvaluation(String monitorAndMaintenanceId, int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getContractorEvaluationProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] selectionArgs = {
                        monitorAndMaintenanceId
                };

                Cursor cursor = db.query(
                        ContractorEvaluationContract.ContractorEvaluationEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                ContractorEvaluation contractorEvaluation = new ContractorEvaluation();

                while (cursor.moveToNext()) {
                    contractorEvaluation = getContractorEvaluationObject(cursor);
                    contractorEvaluation.initNullFields();
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(contractorEvaluation);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteContractorEvaluation(String monitorAndMaintenanceId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] whereArgs = {
                        monitorAndMaintenanceId
                };

                int deleted = db.delete(ContractorEvaluationContract.ContractorEvaluationEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted vegetalMaintenance: -> " + deleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<ContractorEvaluation>> getPendingContractorEvaluations() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getContractorEvaluationProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 1000);
                String selection = ContractorEvaluationContract.ContractorEvaluationEntry.COLUMN_PENDING_TO_BE_SENT + " =CAST(? AS INTEGER) ";
                String[] selectionArgs = {
                        "1"
                };

                Cursor cursor = db.query(
                        ContractorEvaluationContract.ContractorEvaluationEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<ContractorEvaluation> contractorEvaluations = new ArrayList<>();

                while (cursor.moveToNext()) {
                    contractorEvaluations.add(getContractorEvaluationObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(contractorEvaluations);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<StardSheetForm> getStardSheet(String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getStardSheetProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection = StardSheetContract.StardSheetEntry.COLUMN_TASK_ID + " = ?";
                String[] selectionArgs = {
                        taskId
                };

                Cursor cursor = db.query(
                        StardSheetContract.StardSheetEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                StardSheetForm stardSheetForm = new StardSheetForm();

                while (cursor.moveToNext()) {
                    stardSheetForm = getStardSheetObject(cursor);
                    stardSheetForm.initNullFields();
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(stardSheetForm);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<ContractorForm> getContractorForm(String contractorSiembraId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getContractorFormProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection = ContractorFormContract.ContractorEntry.COLUMN_SIEMBRA_ID + " = ?";
                String[] selectionArgs = {
                        contractorSiembraId
                };

                Cursor cursor = db.query(
                        ContractorFormContract.ContractorEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                ContractorForm contractorForm = new ContractorForm();

                while (cursor.moveToNext()) {
                    contractorForm = getContractorFormObject(cursor);
                    contractorForm.initNullFields();
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(contractorForm);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<StardSheetForm> saveStardSheet(StardSheetForm stardSheetForm, String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(StardSheetContract.StardSheetEntry.COLUMN_TASK_ID, Long.parseLong(taskId));
                values.put(StardSheetContract.StardSheetEntry.COLUMN_DATA, serializationManager.toJson(stardSheetForm));
                values.put(StardSheetContract.StardSheetEntry.COLUMN_PENDING_TO_BE_SENT, stardSheetForm.isPendingToBeSent());

                db.insertWithOnConflict(StardSheetContract.StardSheetEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved stardSheetForm in DB -> stardSheetFormId: " + stardSheetForm.getId());

                subscriber.onNext(stardSheetForm);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<ContractorForm> saveContractorForm(ContractorForm contractorForm, String contractSiembraId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(ContractorFormContract.ContractorEntry.COLUMN_SIEMBRA_ID, contractSiembraId);
                values.put(ContractorFormContract.ContractorEntry.COLUMN_DATA, serializationManager.toJson(contractorForm));
                values.put(ContractorFormContract.ContractorEntry.COLUMN_PENDING_TO_BE_SENT, contractorForm.isPendingToBeSent());

                db.insertWithOnConflict(ContractorFormContract.ContractorEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved contractorForm in DB -> contractSiembraId: " + contractSiembraId);

                subscriber.onNext(contractorForm);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteStardSheet(String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = StardSheetContract.StardSheetEntry.COLUMN_TASK_ID + " = ?";
                String[] whereArgs = {
                        taskId
                };

                int deleted = db.delete(StardSheetContract.StardSheetEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted stardSheet: -> " + deleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteContractorForm(String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = ContractorFormContract.ContractorEntry.COLUMN_SIEMBRA_ID + " = ?";
                String[] whereArgs = {
                        taskId
                };

                int deleted = db.delete(ContractorFormContract.ContractorEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted contractForm: -> " + deleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<MaintenanceControl> getMaintenanceControl(String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getMaintenanceControlProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection = MaintenanceControlContract.MaintenanceControlEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] selectionArgs = {
                        taskId
                };

                Cursor cursor = db.query(
                        MaintenanceControlContract.MaintenanceControlEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                MaintenanceControl maintenanceControl = new MaintenanceControl();

                while (cursor.moveToNext()) {
                    maintenanceControl = getMaintenanceControlObject(cursor);
                    maintenanceControl.initNullFields();
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(maintenanceControl);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<MaintenanceControl> saveMaintenanceControl(MaintenanceControl maintenanceControl, String monitorAndMaintenanceId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(MaintenanceControlContract.MaintenanceControlEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID, Long.parseLong(monitorAndMaintenanceId));
                values.put(MaintenanceControlContract.MaintenanceControlEntry.COLUMN_DATA, serializationManager.toJson(maintenanceControl));
                values.put(MaintenanceControlContract.MaintenanceControlEntry.COLUMN_PENDING_TO_BE_SENT, maintenanceControl.isPendingToBeSent());

                db.insertWithOnConflict(MaintenanceControlContract.MaintenanceControlEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved maintenanceControl in DB -> maintenanceControlId: " + maintenanceControl.getId());

                subscriber.onNext(maintenanceControl);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<Boolean> deleteMaintenanceControl(String monitorAndMaintenanceId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String whereClause = MaintenanceControlContract.MaintenanceControlEntry.COLUMN_MONITOR_AND_MAINTENANCE_ID + " = ?";
                String[] whereArgs = {
                        monitorAndMaintenanceId
                };

                int deleted = db.delete(MaintenanceControlContract.MaintenanceControlEntry.TABLE_NAME, whereClause, whereArgs);
                db.setTransactionSuccessful();

                Log.d(getClass().getSimpleName(), "deleted maintenanceControl: -> " + deleted);

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Item>> saveTaskComments(List<Item> taskComments, String taskId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                for (Item taskComment : taskComments) {

                    values.put(TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_ID, ((TaskComment) taskComment).getId());
                    values.put(TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_CONTENT, ((TaskComment) taskComment).getContent());
                    values.put(TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_AUTHOR, ((TaskComment) taskComment).getAuthor());
                    values.put(TaskCommentContract.TaskCommentEntry.COLUMN_TASK_ID, taskId);

                    db.insertWithOnConflict(TaskCommentContract.TaskCommentEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                    Log.d(getClass().getName(), "Saved taskComment in DB -> " + ((TaskComment) taskComment).getId());
                }

                db.setTransactionSuccessful();

                subscriber.onNext(taskComments);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<TaskComment>> getTaskComments(String taskId, int offset, int limit) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getTaskCommentsProjection();
                String sortOrder = TaskCommentContract.TaskCommentEntry.COLUMN_TASK_COMMENT_ID + " ASC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = TaskCommentContract.TaskCommentEntry.COLUMN_TASK_ID + " = ?";
                String[] selectionArgs = {
                        taskId
                };

                Cursor cursor = db.query(
                        TaskCommentContract.TaskCommentEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<TaskComment> taskComments = new ArrayList<>();

                while (cursor.moveToNext()) {
                    taskComments.add(getTaskCommentObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(taskComments);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public Observable<Boolean> clearTables() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.close();
            db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                onUpgrade(db, 0, 0);
                db.setTransactionSuccessful();

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public Observable<Boolean> saveProvinces(List<Province> provinces) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                for (Item province : provinces) {

                    values.put(ProvinceContract.ProvinceEntry.COLUMN_PROVINCE_ID, ((Province) province).getId());
                    values.put(ProvinceContract.ProvinceEntry.COLUMN_PROVINCE_NAME, ((Province) province).getName());

                    db.insertWithOnConflict(ProvinceContract.ProvinceEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                    Log.d(getClass().getName(), "Saved province in DB -> " + ((Province) province).getId());
                }

                db.setTransactionSuccessful();

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Municipality>> getMunicipalities() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getMunicipalityProjection();
                String sortOrder = MunicipalityContract.MunicipalityEntry.COLUMN_MUNICIPALITY_ID + " ASC";
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 2000);

                Cursor cursor = db.query(
                        MunicipalityContract.MunicipalityEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortOrder,
                        queryOffsetAndLimit
                );

                List<Municipality> municipalities = new ArrayList<>();

                while (cursor.moveToNext()) {
                    municipalities.add(getMunicipalityObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(municipalities);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public Observable<Boolean> saveMunicipalities(List<Municipality> municipalities) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                for (Item municipality : municipalities) {

                    values.put(MunicipalityContract.MunicipalityEntry.COLUMN_MUNICIPALITY_ID, ((Municipality) municipality).getId());
                    values.put(MunicipalityContract.MunicipalityEntry.COLUMN_MUNICIPALITY_NAME, ((Municipality) municipality).getName());
                    values.put(MunicipalityContract.MunicipalityEntry.COLUMN_PROVINCE_ID, ((Municipality) municipality).getProvinceId());

                    db.insertWithOnConflict(MunicipalityContract.MunicipalityEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                }

                db.setTransactionSuccessful();

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public Observable<Boolean> saveCroquisList(List<Croquis> croquisList, String predioId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                for (Croquis croquis : croquisList) {

                    values.put(CroquisContract.CroquisEntry.COLUMN_REMOTE_ID, croquis.getRemoteId());
                    values.put(CroquisContract.CroquisEntry.COLUMN_PREDIO_ID, predioId);
                    values.put(CroquisContract.CroquisEntry.COLUMN_DATA, serializationManager.toJson(croquis));

                    db.insertWithOnConflict(CroquisContract.CroquisEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                    Log.d(getClass().getName(), "Saved croquis in DB -> " + predioId);
                }

                db.setTransactionSuccessful();

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Croquis>> getCroquis(String predioId) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getCroquisProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection = CroquisContract.CroquisEntry.COLUMN_PREDIO_ID + " = ?";
                String[] selectionArgs = {
                        predioId
                };

                Cursor cursor = db.query(
                        CroquisContract.CroquisEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<Croquis> croquisList = new ArrayList<>();

                while (cursor.moveToNext()) {
                    croquisList.add(getCroquisObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(croquisList);
                subscriber.onComplete();


            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public Observable<Boolean> savePrediosManagementLayer(GeoJson json) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                values.put(PrediosManagementLayerGeoJsonContract.PrediosManagementLayerGeoJsonEntry.DATA_ID, 1);
                values.put(PrediosManagementLayerGeoJsonContract.PrediosManagementLayerGeoJsonEntry.COLUMN_DATA, serializationManager.toJson(json));
                db.insertWithOnConflict(PrediosManagementLayerGeoJsonContract.PrediosManagementLayerGeoJsonEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                Log.d(getClass().getName(), "Saved onPrediosManagementLayer in DB");

                db.setTransactionSuccessful();

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<GeoJson> getPrediosManagementLayer() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getPrediosManagementLayerProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 1);

                Cursor cursor = db.query(
                        PrediosManagementLayerGeoJsonContract.PrediosManagementLayerGeoJsonEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                GeoJson geoJson = new GeoJson();

                while (cursor.moveToNext()) {
                    geoJson = getPrediosManagementGeoJsonObject(cursor);
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(geoJson);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<GeometryComment> saveGeometryComment(String taskId, GeometryComment
            geometryComment) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                if (!TextUtils.isEmpty(geometryComment.getId())) {
                    values.put(GeometryCommentContract.GeometryCommentEntry._ID, Long.parseLong(geometryComment.getId()));
                }

                values.put(GeometryCommentContract.GeometryCommentEntry.COLUMN_TASK_ID, taskId);
                values.put(GeometryCommentContract.GeometryCommentEntry.COLUMN_HASH, geometryComment.getHash());
                values.put(GeometryCommentContract.GeometryCommentEntry.COLUMN_COMMENT, geometryComment.getComment());

                db.insertWithOnConflict(GeometryCommentContract.GeometryCommentEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                Log.d(getClass().getName(), "Saved meetingsWithActorsForm in DB -> taskId: " + taskId);

                subscriber.onNext(geometryComment);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Item>> savePrograms(List<Item> programs) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            db.execSQL("DELETE FROM " + ProgramContract.ProgramEntry.TABLE_NAME);

            try {

                for (Item program : programs) {
                    if (!TextUtils.isEmpty(((Program) program).getId())) {

                        ContentValues values = new ContentValues();
                        values.put(ProgramContract.ProgramEntry._ID, Long.parseLong(((Program) program).getId()));
                        values.put(ProgramContract.ProgramEntry.COLUMN_NAME, ((Program) program).getName());

                        db.insertWithOnConflict(ProgramContract.ProgramEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                        Log.d(getClass().getName(), "Saved program in DB -> programId: " + ((Program) program).getId());
                    }
                }

                db.setTransactionSuccessful();

                subscriber.onNext(programs);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<List<Program>> getPrograms() {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getProgramProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 1000);

                Cursor cursor = db.query(
                        ProgramContract.ProgramEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<Program> programs = new ArrayList<>();

                while (cursor.moveToNext()) {
                    programs.add(getProgramObject(cursor));
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(programs);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    Observable<GeometryComment> getGeometryComment(String taskId, String hash) {
        return Observable.create(subscriber -> {

            SQLiteDatabase db = getReadableDatabase();
            db.beginTransactionNonExclusive();

            try {

                String[] projection = getGeometryCommentProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 1);

                Cursor cursor = db.query(
                        GeometryCommentContract.GeometryCommentEntry.TABLE_NAME,
                        projection,
                        GeometryCommentContract.GeometryCommentEntry.COLUMN_TASK_ID + " = ? AND " + GeometryCommentContract.GeometryCommentEntry.COLUMN_HASH + " = ?",
                        new String[]{taskId, hash},
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                GeometryComment geometryComment = new GeometryComment();

                while (cursor.moveToNext()) {
                    geometryComment = getGeometryCommentObject(cursor);
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(geometryComment);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    /***
     *
     * Siembra detail form CRUD
     *
     */

    Observable<SiembraDetailForm> saveSiembraDetailForm(SiembraDetailForm siembraDetail, String hash, String taskId) {
        return SiembraDetailHelper.saveSiembraDetailForm(getReadableDatabase(), serializationManager, siembraDetail, hash, taskId);
    }

    Observable<List<SiembraDetailForm>> getSiembraDetailsByTaskId(String taskId, int offset, int limit) {
        return SiembraDetailHelper.getSiembraDetailsByTaskId(app, getReadableDatabase(), serializationManager, taskId, offset, limit);
    }

    Observable<List<SiembraDetailForm>> getSiembraDetails(String hash, int offset, int limit) {
        return SiembraDetailHelper.getSiembraDetails(app, getReadableDatabase(), serializationManager, hash, offset, limit);
    }

    Observable<SiembraDetailForm> getSiembraDetailForm(String siembraDetailId) {
        return SiembraDetailHelper.getSiembraDetailForm(app, getReadableDatabase(), serializationManager, siembraDetailId);
    }

    Observable<Boolean> deleteSiembraDetailForms(Set<String> hashes) {
        return SiembraDetailHelper.deleteSiembraDetailForms(getReadableDatabase(), hashes);
    }

    /***
     *
     * Erosive process form CRUD
     *
     */

    Observable<ErosiveProcessForm> saveErosiveProcessForm(ErosiveProcessForm erosiveProcessForm, String hash, String taskId) {
        return ErosiveProcessFormHelper.saveErosiveProcessForm(getReadableDatabase(), serializationManager, erosiveProcessForm, hash, taskId);
    }

    Observable<List<ErosiveProcessForm>> getErosiveProcessFormsByTaskId(String taskId, int offset, int limit) {
        return ErosiveProcessFormHelper.getErosiveProcessFormsByTaskId(app, getReadableDatabase(), serializationManager, taskId, offset, limit);
    }

    Observable<List<ErosiveProcessForm>> getErosiveProcessForms(String hash, int offset, int limit) {
        return ErosiveProcessFormHelper.getErosiveProcessForms(app, getReadableDatabase(), serializationManager, hash, offset, limit);
    }

    Observable<ErosiveProcessForm> getErosiveProcessForm(String erosiveProcessFormId) {
        return ErosiveProcessFormHelper.getErosiveProcessForm(app, getReadableDatabase(), serializationManager, erosiveProcessFormId);
    }

    Observable<Boolean> deleteErosiveProcessForms(Set<String> hashes) {
        return ErosiveProcessFormHelper.deleteErosiveProcessForms(getReadableDatabase(), hashes);
    }

    /***
     *
     * Sampling point form CRUD
     *
     */

    Observable<SamplingPointForm> saveSamplingPointForm(SamplingPointForm samplingPointForm, String hash, String taskId) {
        return SamplingPointHelper.saveSamplingPointForm(getReadableDatabase(), serializationManager, samplingPointForm, hash, taskId);
    }

    Observable<List<SamplingPointForm>> getSamplingPointsByTaskId(String taskId, int offset, int limit) {
        return SamplingPointHelper.getSamplingPointsByTaskId(app, getReadableDatabase(), serializationManager, taskId, offset, limit);
    }

    Observable<List<SamplingPointForm>> getSamplingPoints(String hash, int offset, int limit) {
        return SamplingPointHelper.getSamplingPoints(app, getReadableDatabase(), serializationManager, hash, offset, limit);
    }

    Observable<SamplingPointForm> getSamplingPointForm(String samplingPointFormId) {
        return SamplingPointHelper.getSamplingPointForm(app, getReadableDatabase(), serializationManager, samplingPointFormId);
    }

    Observable<Boolean> deleteSamplingPointForms(Set<String> hashes) {
        return SamplingPointHelper.deleteSamplingPointForms(getReadableDatabase(), hashes);
    }

    /***
     *
     * Meetings with actors form CRUD
     *
     */

    Observable<MeetingsWithActorsForm> saveMeetingsWithActorsForm(MeetingsWithActorsForm meetingsWithActorsForm, String taskId) {
        return MeetingsWithActorsHelper.saveMeetingsWithActorsForm(getReadableDatabase(), serializationManager, meetingsWithActorsForm, taskId);
    }

    Observable<MeetingsWithActorsForm> getMeetingsWithActorsForm(String taskId, int offset, int limit) {
        return MeetingsWithActorsHelper.getMeetingsWithActorsForm(app, getReadableDatabase(), serializationManager, taskId, offset, limit);
    }

    Observable<Boolean> deleteMeetingsWithActorsForm(String taskId) {
        return MeetingsWithActorsHelper.deleteMeetingsWithActorsForm(getReadableDatabase(), taskId);
    }
}
