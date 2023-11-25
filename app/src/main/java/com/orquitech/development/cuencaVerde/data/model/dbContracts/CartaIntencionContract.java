package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class CartaIntencionContract implements BaseColumns {

    public static class CartaIntencionEntry implements BaseColumns {

        public static final String TABLE_NAME = "CartaIntencion";
        public static final String COLUMN_PREDIO_POTENCIAL_ID = "predioPotencialId";
        public static final String COLUMN_DATA = "data";
    }
}
