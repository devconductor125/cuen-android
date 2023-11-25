package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class GeometryCommentContract implements BaseColumns {

    public static class GeometryCommentEntry implements BaseColumns {

        public static final String TABLE_NAME = "GeometryComment";
        public static final String COLUMN_TASK_ID = "taskId";
        public static final String COLUMN_HASH = "hash";
        public static final String COLUMN_COMMENT = "comment";
    }
}
