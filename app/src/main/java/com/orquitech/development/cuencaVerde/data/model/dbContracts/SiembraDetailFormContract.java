package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class SiembraDetailFormContract implements BaseColumns {

    public static class SiembraDetailEntry implements BaseColumns {

        public static final String TABLE_NAME = "SiembraDetailForm";
        public static final String COLUMN_HASH = "hash";
        public static final String COLUMN_TASK_ID = "taskId";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_PENDING_TO_BE_SENT = "pendingToBeSent";
    }
}
