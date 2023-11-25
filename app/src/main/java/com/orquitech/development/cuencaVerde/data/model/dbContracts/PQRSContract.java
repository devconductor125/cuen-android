package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class PQRSContract implements BaseColumns {

    public static class PQRSEntry implements BaseColumns {

        public static final String TABLE_NAME = "Pqrs";
        public static final String COLUMN_PQRS_ID = "pqrsId";
        public static final String COLUMN_DATA = "pqrsData";
    }
}
