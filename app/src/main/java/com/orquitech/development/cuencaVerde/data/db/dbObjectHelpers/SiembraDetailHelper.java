package com.orquitech.development.cuencaVerde.data.db.dbObjectHelpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.SiembraDetailFormContract;
import com.orquitech.development.cuencaVerde.data.model.siembraDetailForm.SiembraDetailForm;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;

public class SiembraDetailHelper {

    @NonNull
    private static String[] getSiembraDetailProjection() {
        return new String[]{
                SiembraDetailFormContract.SiembraDetailEntry._ID,
                SiembraDetailFormContract.SiembraDetailEntry.COLUMN_HASH,
                SiembraDetailFormContract.SiembraDetailEntry.COLUMN_TASK_ID,
                SiembraDetailFormContract.SiembraDetailEntry.COLUMN_DATA,
                SiembraDetailFormContract.SiembraDetailEntry.COLUMN_PENDING_TO_BE_SENT
        };
    }

    private static SiembraDetailForm getSiembraDetailFormObject(Cursor cursor, SerializationManager serializationManager) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(SiembraDetailFormContract.SiembraDetailEntry._ID));
        String hash = cursor.getString(cursor.getColumnIndexOrThrow(SiembraDetailFormContract.SiembraDetailEntry.COLUMN_HASH));
        String taskId = cursor.getString(cursor.getColumnIndexOrThrow(SiembraDetailFormContract.SiembraDetailEntry.COLUMN_TASK_ID));
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(SiembraDetailFormContract.SiembraDetailEntry.COLUMN_DATA));

        SiembraDetailForm siembraDetailForm = (SiembraDetailForm) serializationManager.fromJson(dataString, SiembraDetailForm.class);
        siembraDetailForm.setId(String.valueOf(id));
        siembraDetailForm.setTaskId(taskId);
        siembraDetailForm.initNullFields();
        return siembraDetailForm;
    }

    public static Observable<List<SiembraDetailForm>> getSiembraDetailsByTaskId(App app, SQLiteDatabase db, SerializationManager serializationManager, String taskId, int offset, int limit) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                String[] projection = getSiembraDetailProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = SiembraDetailFormContract.SiembraDetailEntry.COLUMN_TASK_ID + " = ?";
                String[] selectionArgs = {
                        taskId
                };

                Cursor cursor = db.query(
                        SiembraDetailFormContract.SiembraDetailEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<SiembraDetailForm> siembraDetails = new ArrayList<>();

                while (cursor.moveToNext()) {
                    siembraDetails.add(getSiembraDetailFormObject(cursor, serializationManager));
                }

                cursor.close();
                db.setTransactionSuccessful();

                Collections.reverse(siembraDetails);

                subscriber.onNext(siembraDetails);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public static Observable<List<SiembraDetailForm>> getSiembraDetails(App app, SQLiteDatabase db, SerializationManager serializationManager, String hash, int offset, int limit) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                String[] projection = getSiembraDetailProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = SiembraDetailFormContract.SiembraDetailEntry.COLUMN_HASH + " = ?";
                String[] selectionArgs = {
                        hash
                };

                Cursor cursor = db.query(
                        SiembraDetailFormContract.SiembraDetailEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                List<SiembraDetailForm> siembraDetails = new ArrayList<>();

                while (cursor.moveToNext()) {
                    siembraDetails.add(getSiembraDetailFormObject(cursor, serializationManager));
                }

                cursor.close();
                db.setTransactionSuccessful();

                Collections.reverse(siembraDetails);

                subscriber.onNext(siembraDetails);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public static Observable<SiembraDetailForm> getSiembraDetailForm(App app, SQLiteDatabase db, SerializationManager serializationManager, String siembraDetailId) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, 0, 100);
                String selection = SiembraDetailFormContract.SiembraDetailEntry._ID + " = ?";
                String[] selectionArgs = {
                        siembraDetailId
                };

                Cursor cursor = db.query(
                        SiembraDetailFormContract.SiembraDetailEntry.TABLE_NAME,
                        getSiembraDetailProjection(),
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                SiembraDetailForm siembraDetailForm = new SiembraDetailForm();

                while (cursor.moveToNext()) {
                    siembraDetailForm = getSiembraDetailFormObject(cursor, serializationManager);
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

    public static Observable<SiembraDetailForm> saveSiembraDetailForm(SQLiteDatabase db, SerializationManager serializationManager, SiembraDetailForm siembraDetail, String hash, String taskId) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                if (!TextUtils.isEmpty(siembraDetail.getId())) {
                    values.put(SiembraDetailFormContract.SiembraDetailEntry._ID, Long.parseLong(siembraDetail.getId()));
                }

                values.put(SiembraDetailFormContract.SiembraDetailEntry.COLUMN_HASH, hash);
                values.put(SiembraDetailFormContract.SiembraDetailEntry.COLUMN_TASK_ID, taskId);
                values.put(SiembraDetailFormContract.SiembraDetailEntry.COLUMN_DATA, serializationManager.toJson(siembraDetail));
                values.put(SiembraDetailFormContract.SiembraDetailEntry.COLUMN_PENDING_TO_BE_SENT, siembraDetail.isPendingToBeSent());

                db.insertWithOnConflict(SiembraDetailFormContract.SiembraDetailEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                subscriber.onNext(siembraDetail);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public static Observable<Boolean> deleteSiembraDetailForms(SQLiteDatabase db, Set<String> hashes) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                for (String hash : hashes) {
                    String whereClause = SiembraDetailFormContract.SiembraDetailEntry.COLUMN_HASH + " = ?";
                    String[] whereArgs = {
                            hash
                    };

                    int deleted = db.delete(SiembraDetailFormContract.SiembraDetailEntry.TABLE_NAME, whereClause, whereArgs);
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
