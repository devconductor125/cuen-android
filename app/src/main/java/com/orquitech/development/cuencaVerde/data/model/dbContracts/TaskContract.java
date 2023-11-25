package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class TaskContract implements BaseColumns {

    public static class TaskEntry implements BaseColumns {

        public static final String TABLE_NAME = "Tasks";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_PROCEDURE_ID = "procedureId";
    }
}
