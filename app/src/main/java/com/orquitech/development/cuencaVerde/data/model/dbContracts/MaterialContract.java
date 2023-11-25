package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class MaterialContract implements BaseColumns {

    public static class MaterialEntry implements BaseColumns {

        public static final String TABLE_NAME = "Materials";
        public static final String COLUMN_MATERIAL_ID = "materialId";
        public static final String COLUMN_MATERIAL_NAME = "name";
        public static final String COLUMN_MATERIAL_PRICE = "price";
        public static final String COLUMN_MATERIAL_MEASUREMENT = "measurement";
        public static final String COLUMN_MATERIAL_TYPE = "type";
        public static final String COLUMN_MATERIAL_UNIT = "unit";
        public static final String COLUMN_MATERIAL_ACTION = "action";
    }
}
