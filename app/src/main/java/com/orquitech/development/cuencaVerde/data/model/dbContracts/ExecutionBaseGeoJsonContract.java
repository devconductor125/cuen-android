package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class ExecutionBaseGeoJsonContract implements BaseColumns {

    public static class GeoJsonEntry implements BaseColumns {

        public static final String TABLE_NAME = "ExecutionBaseGeoJson";
        public static final String COLUMN_TASK_ID = "taskId";
        public static final String COLUMN_DATA = "data";
    }
}
