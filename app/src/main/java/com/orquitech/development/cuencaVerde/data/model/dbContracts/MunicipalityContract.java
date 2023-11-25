package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class MunicipalityContract implements BaseColumns {

    public static class MunicipalityEntry implements BaseColumns {

        public static final String TABLE_NAME = "Municipalities";
        public static final String COLUMN_MUNICIPALITY_ID = "municipalityId";
        public static final String COLUMN_MUNICIPALITY_NAME = "name";
        public static final String COLUMN_PROVINCE_ID = "provinceId";
    }
}
