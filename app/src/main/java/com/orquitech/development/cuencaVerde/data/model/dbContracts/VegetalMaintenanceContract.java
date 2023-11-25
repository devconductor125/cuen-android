package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class VegetalMaintenanceContract implements BaseColumns {

    public static class VegetalMaintenanceEntry implements BaseColumns {

        public static final String TABLE_NAME = "VegetalMaintenance";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_ID = "monitorAndMaintenanceID";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_PENDING_TO_BE_SENT = "pendingToBeSent";
    }
}
