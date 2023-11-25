package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class MonitorAndMaintenanceContract implements BaseColumns {

    public static class MonitorAndMaintenanceEntry implements BaseColumns {

        public static final String TABLE_NAME = "MonitorAndMaintenance";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_ID = "monitorAndMaintenanceId";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_TITLE = "title";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_PROCEDURE_ID = "procedureId";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_DUE_DATE = "dueDate";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_DUE_DATE_STATUS = "dueDateStatus";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_JSON_FEATURE = "jsonFeature";
        public static final String COLUMN_PENDING_TO_BE_SENT = "pendingToBeSent";
    }
}
