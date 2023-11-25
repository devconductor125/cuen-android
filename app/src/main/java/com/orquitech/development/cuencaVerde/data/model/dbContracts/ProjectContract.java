package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class ProjectContract implements BaseColumns {

    public static class ProjectEntry implements BaseColumns {

        public static final String TABLE_NAME = "Projects";
        public static final String COLUMN_PROCEDURE_ID = "projectId";
        public static final String COLUMN_DATA = "projectData";
    }
}
