package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class MonitorAndMaintenancePhotoContract implements BaseColumns {

    public static class MonitorAndMaintenancePhotoEntry implements BaseColumns {

        public static final String TABLE_NAME = "MonitorAndMaintenancePhoto";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_PHOTO_NAME = "photoName";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID = "monitorAndMaintenanceCommentPointId";
    }
}
