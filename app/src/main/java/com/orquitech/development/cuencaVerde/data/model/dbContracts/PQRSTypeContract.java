package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class PQRSTypeContract implements BaseColumns {

    public static class PQRSTypeEntry implements BaseColumns {

        public static final String TABLE_NAME = "PqrsType";
        public static final String COLUMN_PQRS_TYPE_ID = "pqrsTypeId";
        public static final String COLUMN_DEPENDENCY_ID = "dependencyId";
        public static final String COLUMN_PQRS_TYPE_NAME = "name";
    }
}
