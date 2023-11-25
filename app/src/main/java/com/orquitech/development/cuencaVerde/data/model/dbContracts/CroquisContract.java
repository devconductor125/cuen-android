package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class CroquisContract implements BaseColumns {

    public static class CroquisEntry implements BaseColumns {

        public static final String TABLE_NAME = "Croquis";
        public static final String COLUMN_REMOTE_ID = "remoteId";
        public static final String COLUMN_PREDIO_ID = "predioId";
        public static final String COLUMN_DATA = "data";
    }
}
