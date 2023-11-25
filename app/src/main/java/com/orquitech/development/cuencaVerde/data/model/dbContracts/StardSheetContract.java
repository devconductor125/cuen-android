package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class StardSheetContract implements BaseColumns {

    public static class StardSheetEntry implements BaseColumns {

        public static final String TABLE_NAME = "StardSheetForm";
        public static final String COLUMN_TASK_ID = "taskId";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_PENDING_TO_BE_SENT = "pendingToBeSent";
    }
}
