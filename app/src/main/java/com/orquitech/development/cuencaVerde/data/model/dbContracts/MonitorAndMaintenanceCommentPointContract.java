package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class MonitorAndMaintenanceCommentPointContract implements BaseColumns {

    public static class MonitorAndMaintenanceCommentPointEntry implements BaseColumns {

        public static final String TABLE_NAME = "MonitorAndMaintenanceCommentPoint";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_ID = "monitorAndMaintenanceCommentPointId";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_ID = "monitorAndMaintenanceId";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_COMMENT = "comment";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_LAT = "lat";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_LNG = "lng";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_COMMENT_POINT_FROM_SERVICE = "formService";
    }
}
