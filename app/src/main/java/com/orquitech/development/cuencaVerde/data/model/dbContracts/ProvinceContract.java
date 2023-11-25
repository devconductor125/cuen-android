package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class ProvinceContract implements BaseColumns {

    public static class ProvinceEntry implements BaseColumns {

        public static final String TABLE_NAME = "Provinces";
        public static final String COLUMN_PROVINCE_ID = "provinceId";
        public static final String COLUMN_PROVINCE_NAME = "name";
    }
}
