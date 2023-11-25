package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class MeetingsWithActorsFormContract implements BaseColumns {

    public static class MeetingsWithActorsFormEntry implements BaseColumns {

        public static final String TABLE_NAME = "MeetingsWithActors";
        public static final String COLUMN_TASK_ID = "taskId";
        public static final String COLUMN_DATA = "data";
    }
}
