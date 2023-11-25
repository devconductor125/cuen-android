package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class ProgramContract implements BaseColumns {

    public static class ProgramEntry implements BaseColumns {

        public static final String TABLE_NAME = "Programs";
        public static final String COLUMN_NAME = "name";
    }
}
