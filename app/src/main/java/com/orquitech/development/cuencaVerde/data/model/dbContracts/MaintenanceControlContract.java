package com.orquitech.development.cuencaVerde.data.model.dbContracts;

        import android.provider.BaseColumns;

public class MaintenanceControlContract implements BaseColumns {

    public static class MaintenanceControlEntry implements BaseColumns {

        public static final String TABLE_NAME = "MaintenanceControl";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_ID = "monitorAndMaintenanceId";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_PENDING_TO_BE_SENT = "pendingToBeSent";
    }
}
