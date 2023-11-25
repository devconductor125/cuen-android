package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class PredioFollowUpContract implements BaseColumns {

    public static class PredioFollowUpEntry implements BaseColumns {

        public static final String TABLE_NAME = "PredioFollowUp";
        public static final String COLUMN_MONITOR_AND_MAINTENANCE_ID = "monitorAndMaintenanceID";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_PENDING_TO_BE_SENT = "pendingToBeSent";
    }
}
