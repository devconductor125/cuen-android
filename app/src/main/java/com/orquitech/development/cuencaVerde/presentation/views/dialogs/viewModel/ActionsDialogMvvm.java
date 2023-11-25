package com.orquitech.development.cuencaVerde.presentation.views.dialogs.viewModel;

import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.BaseDialogListItem;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.AppListView;

public interface ActionsDialogMvvm {

    interface View extends AppListView {

        void onItemClicked(BaseDialogListItem action);

        int getItemsType();

        Parcelable getPayload();

        void setPayload(Parcelable payload);
    }

    interface ViewModel {

        void clearSubscriptions();
    }
}
