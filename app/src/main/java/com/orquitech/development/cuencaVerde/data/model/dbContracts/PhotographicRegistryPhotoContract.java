package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class PhotographicRegistryPhotoContract implements BaseColumns {

    public static class PhotographicRegistryPhotoEntry implements BaseColumns {

        public static final String TABLE_NAME = "PhotographicRegistryPhoto";
        public static final String COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_NAME = "photoName";
        public static final String COLUMN_PHOTOGRAPHIC_REGISTRY_PHOTO_COMMENT = "comment";
        public static final String COLUMN_FOREIGN_ID = "taskId";
    }
}
