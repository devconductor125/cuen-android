package com.orquitech.development.cuencaVerde.data.db.dbObjectHelpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.SamplingPointFormContract;
import com.orquitech.development.cuencaVerde.data.model.samplingPoint.SamplingPointForm;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;

public class SamplingPointHelper {

    @NonNull
    private static String[] getSamplingPointProjection() {
        return new String[]{
                SamplingPointFormContract.SamplingPointEntry._ID,
                SamplingPointFormContract.SamplingPointEntry.COLUMN_HASH,
                SamplingPointFormContract.SamplingPointEntry.COLUMN_TASK_ID,
                SamplingPointFormContract.SamplingPointEntry.COLUMN_DATA,
                SamplingPointFormContract.SamplingPointEntry.COLUMN_PENDING_TO_BE_SENT
        };
    }

    private static SamplingPointForm getSamplingPointFormObject(Cursor cursor, SerializationManager serializationManager) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(SamplingPointFormContract.SamplingPointEntry._ID));
        String hash = cursor.getString(cursor.getColumnIndexOrThrow(SamplingPointFormContract.SamplingPointEntry.COLUMN_HASH));
        String taskId = cursor.getString(cursor.getColumnIndexOrThrow(SamplingPointFormContract.SamplingPointEntry.COLUMN_TASK_ID));
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(SamplingPointFormContract.SamplingPointEntry.COLUMN_DATA));

        SamplingPointForm samplingPointForm = (SamplingPointForm) serializationManager.fromJson(dataString, SamplingPointForm.class);
        samplingPointForm.setId(String.valueOf(id));
        samplingPointForm.setTaskId(taskId);
        samplingPointForm.setType(AppViewsFactory.SAMPLING_POINT_LIST_ITEM_VIEW);
        return samplingPointForm;
    }

    public static Observable<List<SamplingPointForm>> getSamplingPointsByTaskId(App app, SQLiteDatabase db, SerializationManager serializationManager, String taskId, int offset, int limit) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                String[] projection = getSamplingPointProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = SamplingPointFormContract.SamplingPointEntry.COLUMN_TASK_ID + " = ?";
                String[] selectionArgs = {
                        taskId
                };

                Cursor cursor = db.query(
                        SamplingPointFormContract.SamplingPointEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<SamplingPointForm> samplingPoints = new ArrayList<>();

                while (cursor.moveToNext()) {
                    samplingPoints.add(getSamplingPointFormObject(cursor, serializationManager));
                }

                cursor.close();
                db.setTransactionSuccessful();

                Collections.reverse(samplingPoints);

                subscriber.onNext(samplingPoints);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public static Observable<List<SamplingPointForm>> getSamplingPoints(App app, SQLiteDatabase db, SerializationManager serializationManager, String hash, int offset, int limit) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                String[] projection = getSamplingPointProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = SamplingPointFormContract.SamplingPointEntry.COLUMN_HASH + " = ?";
                String[] selectionArgs = {
                        hash
                };

                Cursor cursor = db.query(
                        SamplingPointFormContract.SamplingPointEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<SamplingPointForm> samplingPoints = new ArrayList<>();

                while (cursor.moveToNext()) {
                    samplingPoints.add(getSamplingPointFormObject(cursor, serializationManager));
                }

                cursor.close();
                db.setTransactionSuccessful();

                Collections.reverse(samplingPoints);

                subscriber.onNext(samplingPoints);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public static Observable<SamplingPointForm> getSamplingPointForm(App app, SQLiteDatabase db, SerializationManager serializationManager, String samplingPointFormId) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection = SamplingPointFormContract.SamplingPointEntry._ID + " = ?";
                String[] selectionArgs = {
                        samplingPointFormId
                };

                Cursor cursor = db.query(
                        SamplingPointFormContract.SamplingPointEntry.TABLE_NAME,
                        getSamplingPointProjection(),
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                SamplingPointForm siembraDetailForm = new SamplingPointForm();

                while (cursor.moveToNext()) {
                    siembraDetailForm = getSamplingPointFormObject(cursor, serializationManager);
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(siembraDetailForm);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public static Observable<SamplingPointForm> saveSamplingPointForm(SQLiteDatabase db, SerializationManager serializationManager, SamplingPointForm samplingPointForm, String hash, String taskId) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                if (!TextUtils.isEmpty(samplingPointForm.getId())) {
                    values.put(SamplingPointFormContract.SamplingPointEntry._ID, Long.parseLong(samplingPointForm.getCleanedId()));
                }

                values.put(SamplingPointFormContract.SamplingPointEntry.COLUMN_HASH, hash);
                values.put(SamplingPointFormContract.SamplingPointEntry.COLUMN_TASK_ID, taskId);
                values.put(SamplingPointFormContract.SamplingPointEntry.COLUMN_DATA, serializationManager.toJson(samplingPointForm));
                values.put(SamplingPointFormContract.SamplingPointEntry.COLUMN_PENDING_TO_BE_SENT, samplingPointForm.isPendingToBeSent());

                db.insertWithOnConflict(SamplingPointFormContract.SamplingPointEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                subscriber.onNext(samplingPointForm);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public static Observable<Boolean> deleteSamplingPointForms(SQLiteDatabase db, Set<String> hashes) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                for (String hash : hashes) {
                    String whereClause = SamplingPointFormContract.SamplingPointEntry.COLUMN_HASH + " = ?";
                    String[] whereArgs = {
                            hash
                    };

                    int deleted = db.delete(SamplingPointFormContract.SamplingPointEntry.TABLE_NAME, whereClause, whereArgs);
                    db.setTransactionSuccessful();
                }

                subscriber.onNext(true);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }
}
