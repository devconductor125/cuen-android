package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class SurveyContract implements BaseColumns {

    public static class SurveyEntry implements BaseColumns {

        public static final String TABLE_NAME = "Surveys";
        public static final String COLUMN_PREDIO_POTENCIAL_ID = "predioPotencialId";
        public static final String COLUMN_DATA = "data";
    }
}
