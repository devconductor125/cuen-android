package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class StardMonitorAndMaintenanceContract implements BaseColumns {

    public static class StardMonitorAndMaintenanceEntry implements BaseColumns {

        public static final String TABLE_NAME = "StardMonitorAndMaintenance";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_ID = "monitorAndMaintenanceID";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_PENDING_TO_BE_SENT = "pendingToBeSent";
    }
}
