package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class PredioPotencialContract implements BaseColumns {

    public static class PredioPotencialEntry implements BaseColumns {

        public static final String TABLE_NAME = "PredioPotencial";
        public static final String COLUMN_PREDIO_POTENCIAL_REMOTE_ID = "predioPotencialRemoteId";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LAT = "lat";
        public static final String COLUMN_LNG = "lng";
    }
}
