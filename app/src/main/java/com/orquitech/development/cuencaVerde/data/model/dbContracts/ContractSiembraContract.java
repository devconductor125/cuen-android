package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class ContractSiembraContract implements BaseColumns {

    public static class ContractSiembraEntry implements BaseColumns {

        public static final String TABLE_NAME = "ContractSiembra";
        public static final String COLUMN_ID = "contractSiembraId";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_PENDING_TO_BE_SENT = "pendingToBeSent";
    }
}
