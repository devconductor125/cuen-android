package com.orquitech.development.cuencaVerde.data.model.dbContracts;

import android.provider.BaseColumns;

public class ContractorFormContract implements BaseColumns {

    public static class ContractorEntry implements BaseColumns {

        public static final String TABLE_NAME = "ContractorForm";
        public static final String COLUMN_SIEMBRA_ID = "contractorSiembraId";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_PENDING_TO_BE_SENT = "pendingToBeSent";
    }
}
