package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class DependencyContract implements BaseColumns {

    public static class DependencyEntry implements BaseColumns {

        public static final String TABLE_NAME = "Dependency";
        public static final String COLUMN_DEPENDENCY_ID = "dependencyId";
        public static final String COLUMN_DEPENDENCY_NAME = "name";
    }
}
