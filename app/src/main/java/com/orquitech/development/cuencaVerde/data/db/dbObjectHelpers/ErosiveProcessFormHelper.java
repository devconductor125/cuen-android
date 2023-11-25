package com.orquitech.development.cuencaVerde.data.db.dbObjectHelpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.ErosiveProcessFormContract;
import com.orquitech.development.cuencaVerde.data.model.erosiveProcess.ErosiveProcessForm;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;

public class ErosiveProcessFormHelper {

    @NonNull
    private static String[] getSamplingPointProjection() {
        return new String[]{
                ErosiveProcessFormContract.ErosiveProcessEntry._ID,
                ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_HASH,
                ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_TASK_ID,
                ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_DATA,
                ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_PENDING_TO_BE_SENT
        };
    }

    private static ErosiveProcessForm getErosiveProcessFormObject(Cursor cursor, SerializationManager serializationManager) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(ErosiveProcessFormContract.ErosiveProcessEntry._ID));
        String hash = cursor.getString(cursor.getColumnIndexOrThrow(ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_HASH));
        String taskId = cursor.getString(cursor.getColumnIndexOrThrow(ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_TASK_ID));
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_DATA));

        ErosiveProcessForm erosiveProcessForm = (ErosiveProcessForm) serializationManager.fromJson(dataString, ErosiveProcessForm.class);
        erosiveProcessForm.setId(String.valueOf(id));
        erosiveProcessForm.setType(AppViewsFactory.EROSIVE_PROCESS_LIST_ITEM_VIEW);
        return erosiveProcessForm;
    }

    public static Observable<List<ErosiveProcessForm>> getErosiveProcessFormsByTaskId(App app, SQLiteDatabase db, SerializationManager serializationManager, String taskId, int offset, int limit) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                String[] projection = getSamplingPointProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_TASK_ID + " = ?";
                String[] selectionArgs = {
                        taskId
                };

                Cursor cursor = db.query(
                        ErosiveProcessFormContract.ErosiveProcessEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<ErosiveProcessForm> erosiveProcessForms = new ArrayList<>();

                while (cursor.moveToNext()) {
                    erosiveProcessForms.add(getErosiveProcessFormObject(cursor, serializationManager));
                }

                cursor.close();
                db.setTransactionSuccessful();

                Collections.reverse(erosiveProcessForms);

                subscriber.onNext(erosiveProcessForms);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public static Observable<List<ErosiveProcessForm>> getErosiveProcessForms(App app, SQLiteDatabase db, SerializationManager serializationManager, String hash, int offset, int limit) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                String[] projection = getSamplingPointProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_HASH + " = ?";
                String[] selectionArgs = {
                        hash
                };

                Cursor cursor = db.query(
                        ErosiveProcessFormContract.ErosiveProcessEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<ErosiveProcessForm> erosiveProcessForms = new ArrayList<>();

                while (cursor.moveToNext()) {
                    erosiveProcessForms.add(getErosiveProcessFormObject(cursor, serializationManager));
                }

                cursor.close();
                db.setTransactionSuccessful();

                Collections.reverse(erosiveProcessForms);

                subscriber.onNext(erosiveProcessForms);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public static Observable<ErosiveProcessForm> getErosiveProcessForm(App app, SQLiteDatabase db, SerializationManager serializationManager, String samplingPointFormId) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection = ErosiveProcessFormContract.ErosiveProcessEntry._ID + " = ?";
                String[] selectionArgs = {
                        samplingPointFormId
                };

                Cursor cursor = db.query(
                        ErosiveProcessFormContract.ErosiveProcessEntry.TABLE_NAME,
                        getSamplingPointProjection(),
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                ErosiveProcessForm siembraDetailForm = new ErosiveProcessForm();

                while (cursor.moveToNext()) {
                    siembraDetailForm = getErosiveProcessFormObject(cursor, serializationManager);
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

    public static Observable<ErosiveProcessForm> saveErosiveProcessForm(SQLiteDatabase db, SerializationManager serializationManager, ErosiveProcessForm samplingPointForm, String hash, String taskId) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                if (!TextUtils.isEmpty(samplingPointForm.getId())) {
                    values.put(ErosiveProcessFormContract.ErosiveProcessEntry._ID, Long.parseLong(samplingPointForm.getCleanedId()));
                }

                values.put(ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_HASH, hash);
                values.put(ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_TASK_ID, taskId);
                values.put(ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_DATA, serializationManager.toJson(samplingPointForm));

                db.insertWithOnConflict(ErosiveProcessFormContract.ErosiveProcessEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
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

    public static Observable<Boolean> deleteErosiveProcessForms(SQLiteDatabase db, Set<String> hashes) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                for (String hash : hashes) {
                    String whereClause = ErosiveProcessFormContract.ErosiveProcessEntry.COLUMN_HASH + " = ?";
                    String[] whereArgs = {
                            hash
                    };

                    int deleted = db.delete(ErosiveProcessFormContract.ErosiveProcessEntry.TABLE_NAME, whereClause, whereArgs);
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
