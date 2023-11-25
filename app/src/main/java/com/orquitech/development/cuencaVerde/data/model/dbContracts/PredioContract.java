package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class PredioContract implements BaseColumns {

    public static class PredioEntry implements BaseColumns {

        public static final String TABLE_NAME = "Properties";
        public static final String COLUMN_PREDIO_ID = "propertyId";
        public static final String COLUMN_GEOJSON = "propertyGeoJson";
    }
}
