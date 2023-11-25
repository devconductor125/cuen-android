package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class ExecutionEditedGeoJsonContract implements BaseColumns {

    public static class GeoJsonEntry implements BaseColumns {

        public static final String TABLE_NAME = "ExecutionEditedGeoJson";
        public static final String COLUMN_TASK_ID = "taskId";
        public static final String COLUMN_DATA = "data";
    }
}
