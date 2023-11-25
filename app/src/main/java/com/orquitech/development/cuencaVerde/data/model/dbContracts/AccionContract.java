package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class AccionContract implements BaseColumns {

    public static class AccionEntry implements BaseColumns {

        public static final String TABLE_NAME = "Acciones";
        public static final String COLUMN_ACCION_ID = "acciónId";
        public static final String COLUMN_ACCION_NAME = "name";
        public static final String COLUMN_ACCION_TYPE = "type";
        public static final String COLUMN_ACCION_COLOR = "color";
        public static final String COLUMN_ACCION_FILL_COLOR = "fillColor";
    }
}
