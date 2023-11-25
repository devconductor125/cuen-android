package com.orquitech.development.cuencaVerde.data.db.dbObjectHelpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.MeetingsWithActorsFormContract;
import com.orquitech.development.cuencaVerde.data.model.dbContracts.StardSheetContract;
import com.orquitech.development.cuencaVerde.data.model.meetingsWithActors.MeetingsWithActorsForm;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;

import io.reactivex.Observable;

public class MeetingsWithActorsHelper {

    @NonNull
    private static String[] getMeetingWithActorsFormProjection() {
        return new String[]{
                MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry._ID,
                MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.COLUMN_TASK_ID,
                MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.COLUMN_DATA,
        };
    }

    private static MeetingsWithActorsForm getMeetingsWithActorsFormObject(Cursor cursor, SerializationManager serializationManager) {
        String dataString = cursor.getString(cursor.getColumnIndexOrThrow(MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.COLUMN_DATA));
        return (MeetingsWithActorsForm) serializationManager.fromJson(dataString, MeetingsWithActorsForm.class);
    }

    public static Observable<MeetingsWithActorsForm> saveMeetingsWithActorsForm(SQLiteDatabase db, SerializationManager serializationManager, MeetingsWithActorsForm meetingsWithActorsForm, String taskId) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                ContentValues values = new ContentValues();

                if (!TextUtils.isEmpty(meetingsWithActorsForm.getId())) {
                    values.put(StardSheetContract.StardSheetEntry._ID, Long.parseLong(meetingsWithActorsForm.getId()));
                }

                values.put(MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.COLUMN_TASK_ID, taskId);
                values.put(MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.COLUMN_DATA, serializationManager.toJson(meetingsWithActorsForm));

                db.insertWithOnConflict(MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                db.setTransactionSuccessful();

                subscriber.onNext(meetingsWithActorsForm);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }

    public static Observable<Boolean> deleteMeetingsWithActorsForm(SQLiteDatabase db, String taskId) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                String whereClause = MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.COLUMN_TASK_ID + " = ?";
                String[] whereArgs = {
                        taskId
                };

                int deleted = db.delete(MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.TABLE_NAME, whereClause, whereArgs);
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

    public static Observable<MeetingsWithActorsForm> getMeetingsWithActorsForm(App app, SQLiteDatabase db, SerializationManager serializationManager, String taskId, int offset, int limit) {
        return Observable.create(subscriber -> {

            db.beginTransactionNonExclusive();

            try {

                String[] projection = getMeetingWithActorsFormProjection();
                String queryOffsetAndLimit = TextUtil.getString(app.getApplicationContext(), R.string.query_list_skip_offset, offset, limit);
                String selection = MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.COLUMN_TASK_ID + " = ?";
                String[] selectionArgs = {
                        taskId
                };

                Cursor cursor = db.query(
                        MeetingsWithActorsFormContract.MeetingsWithActorsFormEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null,
                        queryOffsetAndLimit
                );

                MeetingsWithActorsForm meetingsWithActorsForm = new MeetingsWithActorsForm();

                while (cursor.moveToNext()) {
                    meetingsWithActorsForm = getMeetingsWithActorsFormObject(cursor, serializationManager);
                    meetingsWithActorsForm.setTaskId(taskId);
                }

                cursor.close();
                db.setTransactionSuccessful();

                subscriber.onNext(meetingsWithActorsForm);
                subscriber.onComplete();

            } catch (Exception ex) {
                subscriber.onError(ex);
            } finally {
                db.endTransaction();
            }
        });
    }
}
