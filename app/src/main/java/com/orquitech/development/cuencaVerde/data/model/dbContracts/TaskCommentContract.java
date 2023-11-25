package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class TaskCommentContract implements BaseColumns {

    public static class TaskCommentEntry implements BaseColumns {

        public static final String TABLE_NAME = "TaskComments";
        public static final String COLUMN_TASK_COMMENT_ID = "taskCommentId";
        public static final String COLUMN_TASK_COMMENT_CONTENT = "content";
        public static final String COLUMN_TASK_COMMENT_AUTHOR = "author";
        public static final String COLUMN_TASK_ID = "taskId";
    }
}
